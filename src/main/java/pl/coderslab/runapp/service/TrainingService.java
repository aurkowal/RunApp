package pl.coderslab.runapp.service;

import org.springframework.stereotype.Service;
import pl.coderslab.runapp.DTO.location.LocationCreateRequestDto;
import pl.coderslab.runapp.DTO.location.LocationResponseDto;
import pl.coderslab.runapp.DTO.training.TrainingCreateRequestDto;
import pl.coderslab.runapp.DTO.training.TrainingResponseDto;
import pl.coderslab.runapp.entity.RunRoute;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.entity.Training;
import pl.coderslab.runapp.repository.RunRouteRepository;
import pl.coderslab.runapp.repository.RunnerRepository;
import pl.coderslab.runapp.repository.TrainingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final RunnerRepository runnerRepository;
    private final RunRouteRepository runRouteRepository;

    public TrainingService(TrainingRepository trainingRepository, RunnerRepository runnerRepository, RunRouteRepository runRouteRepository) {
        this.trainingRepository = trainingRepository;
        this.runnerRepository = runnerRepository;
        this.runRouteRepository = runRouteRepository;
    }

    public TrainingResponseDto addNewTraining(Long runnerId, TrainingCreateRequestDto request) {
        Runner runner = runnerRepository.findById(runnerId).get();
        Training training = new Training();
        training.setRunner(runner);
        training.setDate(request.getDate());
        training.setTime(request.getTime());
        training.setDistance(request.getDistance());

        if (request.getRunRouteId() != null) {
            RunRoute route = runRouteRepository
                    .findById(request.getRunRouteId())
                    .get();
            training.setRunRoute(route);
        }

        // tempo w min/km
        double pace = (request.getTime() / 60.0) / (request.getDistance() / 1000.0);
        training.setPace(pace);

        Training saved = trainingRepository.save(training);
        return toDto(saved);
    }

    public List<TrainingResponseDto> findTrainingsByRunnerId(Long runnerId) {
        return trainingRepository.findByRunnerId(runnerId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public TrainingResponseDto findTrainingById(Long id) {
        return toDto(trainingRepository.findById(id).get());
    }

    public void deleteTrainingById(Long id) {
        trainingRepository.deleteById(id);
    }



    private TrainingResponseDto toDto(Training training) {
        return new TrainingResponseDto(
                training.getId(),
                training.getDate(),
                training.getDistance(),
                training.getTime(),
                training.getPace()
        );
    }

}
