package pl.coderslab.runapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.route.RouteByDistanceRequestDto;
import pl.coderslab.runapp.DTO.route.RouteRequestDto;
import pl.coderslab.runapp.DTO.route.RouteResponseDto;
import pl.coderslab.runapp.DTO.route.RunRouteDetailsDto;
import pl.coderslab.runapp.service.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/generate")
    public RouteResponseDto generate(@RequestBody RouteRequestDto request) {
        return routeService.generateRoute(request);
    }

    @PostMapping("/loop")
    public RouteResponseDto generateLoop(@RequestBody RouteByDistanceRequestDto request) {
        return routeService.generateLoopRoute(request);
    }

    @GetMapping("/{id}")
    public RunRouteDetailsDto getRoute(@PathVariable("id") Long id) {
        return routeService.getRoute(id);
    }


//    GET /routes                  // (opcjonalnie admin / debug)
//    GET /runners/{id}/routes     // WSZYSTKIE trasy użytkownika
//    GET /routes/{id}             // szczegóły trasy

}
