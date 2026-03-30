package pl.coderslab.runapp.service;

import org.springframework.stereotype.Service;
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
        Runner runner = runnerRepository.findById(id).get();
        return toDto(runner);
    }

    public List<RunnerResponseDto> getAllRunners() {
        return runnerRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public RunnerResponseDto updateRunner(Long id, RunnerCreateRequestDto request) {
        Runner runner = runnerRepository.findById(id).get();
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
