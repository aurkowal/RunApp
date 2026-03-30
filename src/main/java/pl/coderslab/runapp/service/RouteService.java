package pl.coderslab.runapp.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.runapp.DTO.RouteRequestDto;
import pl.coderslab.runapp.DTO.RouteResponseDto;
import pl.coderslab.runapp.entity.RunRoute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import pl.coderslab.runapp.repository.RunRouteRepository;

import java.util.Map;

@Service
public class RouteService {

    private final RestTemplate restTemplate;
    private final RunRouteRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${ors.api-key}")
    private String apiKey;

    public RouteService(RestTemplate restTemplate, RunRouteRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public RouteResponseDto generateRoute(RouteRequestDto request) {

        String url = "https://api.openrouteservice.org/v2/directions/foot-walking";

        // body do ORS
        Map<String, Object> body = Map.of(
                "coordinates", new double[][]{
                        {request.getStartLon(), request.getStartLat()},
                        {request.getEndLon(), request.getEndLat()}
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, entity, String.class);

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode summary = root.path("routes").get(0).path("summary");

            double distance = summary.path("distance").asDouble();
            double duration = summary.path("duration").asDouble();
            String geometry = root.path("routes").get(0).path("geometry").asText();

            RunRoute route = new RunRoute(distance, duration, geometry);
            RunRoute saved = repository.save(route);

            return new RouteResponseDto(
                    saved.getId(),
                    saved.getDistance(),
                    saved.getDuration()
            );

        } catch (Exception e) {
            throw new RuntimeException("Error while calling ORS API", e);
        }
    }
}
