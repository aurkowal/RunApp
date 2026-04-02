package pl.coderslab.runapp.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.runapp.DTO.route.RouteByDistanceRequestDto;
import pl.coderslab.runapp.DTO.route.RouteRequestDto;
import pl.coderslab.runapp.DTO.route.RouteResponseDto;
import pl.coderslab.runapp.DTO.route.RunRouteDetailsDto;
import pl.coderslab.runapp.DTO.runner.RunnerRouteGeometryDto;
import pl.coderslab.runapp.entity.Location;
import pl.coderslab.runapp.entity.RunRoute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.repository.LocationRepository;
import pl.coderslab.runapp.repository.RunRouteRepository;
import pl.coderslab.runapp.repository.RunnerRepository;

import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    private final RestTemplate restTemplate;
    private final RunRouteRepository runRouteRepository;
    private final RunnerRepository runnerRepository;
    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;

    @Value("${ors.api-key}")
    private String apiKey;

    public RouteService(RestTemplate restTemplate, RunRouteRepository runRouteRepository, RunnerRepository runnerRepository, LocationRepository locationRepository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.runRouteRepository = runRouteRepository;
        this.runnerRepository = runnerRepository;
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    // do narysowania mapy

    public RunRouteDetailsDto getRoute(Long id) {
        RunRoute runRoute = runRouteRepository.findById(id).get();
        return new RunRouteDetailsDto(
                runRoute.getId(),
                runRoute.getGeometry(),
                runRoute.getDistance()
        );
    }

    // do narysowania na mapie wszystkich tras danego biegacza

    public List<RunnerRouteGeometryDto> getAllRoutesForRunner(Long runnerId) {

        return runRouteRepository.findAllByRunnerId(runnerId)
                .stream()
                .filter(route -> route.getGeometry() != null)
                .map(route -> new RunnerRouteGeometryDto(
                        route.getId(),
                        route.getGeometry()
                ))
                .toList();
    }


    // TRASA z punktu A do B

    public RouteResponseDto generateRoute(RouteRequestDto request) {

        Runner runner = runnerRepository.findById(request.getRunnerId()).get();
        Location startLocation = locationRepository
                .findById(request.getStartLocationId()).get();
        Location endLocation = locationRepository
                .findById(request.getEndLocationId()).get();

        RunRoute route = callOrsAndCreateRoute(
                startLocation.getLatitude(),
                startLocation.getLongitude(),
                endLocation.getLatitude(),
                endLocation.getLongitude()
        );

        route.setRunner(runner);
        route.setStartLocation(startLocation);
        route.setEndLocation(endLocation);
        route.setPlannedDistance(request.getPlannedDistance());

        RunRoute saved = runRouteRepository.save(route);

        return new RouteResponseDto(
                saved.getId(),
                saved.getDistance(),
                saved.getDuration()
        );
    }


//     TRASA pętla
//     A -> B, B -> A

    public RouteResponseDto generateLoopRoute(RouteByDistanceRequestDto request) {

        Runner runner = runnerRepository.findById(request.getRunnerId()).get();
        Location startLocation = locationRepository.findById(request.getStartLocationId()).get();

        RunRoute loop = callOrsLoop(
                startLocation.getLatitude(),
                startLocation.getLongitude(),
                request.getTargetDistance()
        );

        loop.setRunner(runner);
        loop.setStartLocation(startLocation);
        loop.setEndLocation(startLocation);
        loop.setPlannedDistance(request.getTargetDistance());

        RunRoute saved = runRouteRepository.save(loop);

        return new RouteResponseDto(
                saved.getId(),
                saved.getDistance(),
                saved.getDuration()
        );
    }


    //Wywołanie ORS i utworzenie obiektu RunRoute  -> METODA POMOCNICZA DO INNYCH METOD

    private RunRoute callOrsAndCreateRoute(double startLat, double startLon, double endLat, double endLon) {

        String url = "https://api.openrouteservice.org/v2/directions/foot-walking";

        double[] waypoint = randomWaypoint(startLat, startLon, endLat, endLon);

        Map<String, Object> body = Map.of(
                "coordinates",
                new double[][]{
                        {startLon, startLat},
                        waypoint,
                        {endLon, endLat}
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode summary = root.path("routes").get(0).path("summary");

            double distance = summary.path("distance").asDouble();
            double duration = summary.path("duration").asDouble();
            String geometry = root.path("routes").get(0).path("geometry").asText();

            return new RunRoute(distance, duration, geometry);

        } catch (Exception e) {
            throw new RuntimeException("Error while calling ORS API", e);
        }
    }

    private RunRoute callOrsLoop(double lat, double lon, int targetDistance) {

        String url = "https://api.openrouteservice.org/v2/directions/foot-walking";

        Map<String, Object> body = Map.of(
                "coordinates", new double[][]{{lon, lat}},
                "options", Map.of(
                        "round_trip", Map.of(
                                "length", targetDistance,
                                "seed", (int) (Math.random() * 1000)
                        )
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode summary = root.path("routes").get(0).path("summary");

            double distance = summary.path("distance").asDouble();
            double duration = summary.path("duration").asDouble();
            String geometry = root.path("routes").get(0).path("geometry").asText();

            return new RunRoute(distance, duration, geometry);

        } catch (Exception e) {
            throw new RuntimeException("Error while calling ORS round-trip API", e);
        }
    }

    // ===== LOSOWOŚĆ TRASY A -> B =====

    private double randomOffset(double value, double range) {
        return value + (Math.random() - 0.5) * range;
    }

    private double[] randomWaypoint(
            double startLat, double startLon,
            double endLat, double endLon
    ) {
        double midLat = (startLat + endLat) / 2;
        double midLon = (startLon + endLon) / 2;

        // UWAGA: ORS wymaga [lon, lat]
        return new double[]{
                randomOffset(midLon, 0.003), // longitude
                randomOffset(midLat, 0.003)  // latitude
        };
    }
}
