package pl.coderslab.runapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.location.LocationCreateRequestDto;
import pl.coderslab.runapp.DTO.location.LocationResponseDto;
import pl.coderslab.runapp.service.LocationService;

import java.util.List;

@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/runners/{runnerId}/locations")
    public LocationResponseDto createLocation(@PathVariable("runnerId") Long runnerId, @RequestBody LocationCreateRequestDto location) {
        return locationService.createLocation(runnerId, location);
    }

    @GetMapping("/locations/{id}")
    public LocationResponseDto findLocationById(@PathVariable("id") Long id) {
        return locationService.findLocationById(id);
    }

    @GetMapping("/runners/{runnerId}/locations")
    public List<LocationResponseDto> findLocationsForRunner(@PathVariable("runnerId") Long runnerId) {
        return locationService.findAllLocationsByRunnerId(runnerId);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocationById(@PathVariable("id") Long id) {
        locationService.deleteLocation(id);
    }




//    DELETE /locations/{id}

}
