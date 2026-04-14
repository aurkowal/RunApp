package pl.coderslab.runapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.runapp.DTO.runner.RunnerCreateRequestDto;
import pl.coderslab.runapp.DTO.runner.RunnerResponseDto;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.repository.RunnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;
    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public RunnerResponseDto createRunner(RunnerCreateRequestDto request) {

        Runner runner = new Runner();
        runner.setName(request.getName());
        runner.setEmail(request.getEmail());

        Runner saved = runnerRepository.save(runner);

        return toDto(saved);
    }

    public RunnerResponseDto getRunnerById(Long id) {
        Runner runner = runnerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Runner not found"));
        return toDto(runner);
    }

    public List<RunnerResponseDto> getAllRunners() {
        return runnerRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public RunnerResponseDto updateRunner(Long id, RunnerCreateRequestDto request) {
        Runner runner = runnerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Runner not found"));
        runner.setEmail(request.getEmail());
        runner.setName(request.getName());
        Runner saved = runnerRepository.save(runner);
        return toDto(saved);
    }

    public void deleteRunner(Long id) {
        runnerRepository.deleteById(id);
    }





    private RunnerResponseDto toDto(Runner runner) {
        return new RunnerResponseDto(
                runner.getId(),
                runner.getName(),
                runner.getEmail()
        );
    }


}
