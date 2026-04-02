package pl.coderslab.runapp.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.training.TrainingCreateRequestDto;
import pl.coderslab.runapp.DTO.training.TrainingResponseDto;
import pl.coderslab.runapp.service.TrainingService;

import java.util.List;

@RestController
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/runners/{runnerId}/trainings")
    public TrainingResponseDto addTrainingToRunner(@PathVariable("runnerId") Long runnerId, @Valid @RequestBody TrainingCreateRequestDto training) {
        return trainingService.addNewTraining(runnerId, training);
    }

    @GetMapping("/runners/{runnerId}/trainings")
    public List<TrainingResponseDto> findTrainingsForRunner(@PathVariable("runnerId") Long runnerId) {
        return trainingService.findTrainingsByRunnerId(runnerId);
    }

    @GetMapping("/trainings/{id}")
    public TrainingResponseDto findById(@PathVariable("id") Long id) {
        return trainingService.findTrainingById(id);
    }

    @DeleteMapping("/trainings/{id}")
    public void delete(@PathVariable("id") Long id) {
        trainingService.deleteTrainingById(id);
    }

}
