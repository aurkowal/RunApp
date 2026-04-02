package pl.coderslab.runapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.route.RouteByDistanceRequestDto;
import pl.coderslab.runapp.DTO.route.RouteRequestDto;
import pl.coderslab.runapp.DTO.route.RouteResponseDto;
import pl.coderslab.runapp.DTO.route.RunRouteDetailsDto;
import pl.coderslab.runapp.DTO.runner.RunnerRouteGeometryDto;
import pl.coderslab.runapp.service.RouteService;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/generate")
    public RouteResponseDto generate(@Valid @RequestBody RouteRequestDto request) {
        return routeService.generateRoute(request);
    }

    @PostMapping("/loop")
    public RouteResponseDto generateLoop(@Valid @RequestBody RouteByDistanceRequestDto request) {
        return routeService.generateLoopRoute(request);
    }

    @GetMapping("/{id}")
    public RunRouteDetailsDto getRoute(@PathVariable("id") Long id) {
        return routeService.getRoute(id);
    }

    @GetMapping("/runners/{runnerId}/routes")
    public ResponseEntity<List<RunnerRouteGeometryDto>> getRoutesForRunner(@PathVariable("runnerId") Long runnerId) {
        return ResponseEntity.ok(routeService.getAllRoutesForRunner(runnerId));
    }



//    GET /routes                  // (opcjonalnie admin / debug)
//    GET /runners/{id}/routes     // WSZYSTKIE trasy użytkownika
//    GET /routes/{id}             // szczegóły trasy

}
