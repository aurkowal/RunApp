package pl.coderslab.runapp.service;

import org.springframework.stereotype.Service;
import pl.coderslab.runapp.DTO.location.LocationCreateRequestDto;
import pl.coderslab.runapp.DTO.location.LocationResponseDto;
import pl.coderslab.runapp.entity.Location;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.repository.LocationRepository;
import pl.coderslab.runapp.repository.RunnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final RunnerRepository runnerRepository;

    public LocationService(LocationRepository locationRepository, RunnerRepository runnerRepository) {
        this.locationRepository = locationRepository;
        this.runnerRepository = runnerRepository;
    }

    public LocationResponseDto createLocation(Long runnerId, LocationCreateRequestDto request) {
        Runner runner = runnerRepository.findById(runnerId).get();
        Location location = new Location();
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setPlaceName(request.getPlaceName());
        location.setRunner(runner);

        Location saved = locationRepository.save(location);
        return toDto(saved);
    }

    public LocationResponseDto findLocationById(Long id) {
        Location location = locationRepository.findById(id).get();
        return toDto(location);
    }

    public List<LocationResponseDto> findAllLocationsByRunnerId(Long runnerId) {
        return locationRepository.findByRunnerId(runnerId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }






    private LocationResponseDto toDto(Location location) {
        return new LocationResponseDto(
                location.getId(),
                location.getPlaceName(),
                location.getLatitude(),
                location.getLongitude()
        );
    }

}
