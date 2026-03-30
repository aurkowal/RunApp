package pl.coderslab.runapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.runapp.DTO.route.RouteByDistanceRequestDto;
import pl.coderslab.runapp.DTO.route.RouteRequestDto;
import pl.coderslab.runapp.DTO.route.RouteResponseDto;
import pl.coderslab.runapp.service.RouteService;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/generate")
    public RouteResponseDto generate(@RequestBody RouteRequestDto request) {
        return routeService.generateRoute(request);
    }

    @PostMapping("/loop")
    public RouteResponseDto generateLoop(@RequestBody RouteByDistanceRequestDto request) {
        return routeService.generateLoopRoute(request);
    }
}
