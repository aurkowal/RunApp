package pl.coderslab.runapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.runapp.DTO.RouteRequestDto;
import pl.coderslab.runapp.DTO.RouteResponseDto;
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
}
