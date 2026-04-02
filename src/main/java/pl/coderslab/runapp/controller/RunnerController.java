package pl.coderslab.runapp.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.runner.RunnerCreateRequestDto;
import pl.coderslab.runapp.DTO.runner.RunnerResponseDto;
import pl.coderslab.runapp.service.RunnerService;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    
    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }
    
    @PostMapping
    public RunnerResponseDto createRunner(@Valid @RequestBody RunnerCreateRequestDto request) {
        return runnerService.createRunner(request);
    }

    @GetMapping
    public List<RunnerResponseDto> findAllRunners() {
        return runnerService.getAllRunners();
    }

    @GetMapping("/{id}")
    public RunnerResponseDto findRunnerById(@PathVariable("id") Long id) {
        return runnerService.getRunnerById(id);
    }

    @PutMapping("/{id}")
    public RunnerResponseDto updateRunner(@PathVariable("id") Long id, @Valid @RequestBody RunnerCreateRequestDto runner) {
        return runnerService.updateRunner(id, runner);
    }

    @DeleteMapping("/{id}")
    public void deleteRunner(@PathVariable("id") Long id) {
        runnerService.deleteRunner(id);
    }
}
