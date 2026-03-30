package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByRunnerId(Long runnerId);

    List<Training> findByRunRouteId(Long runRouteId);

    List<Training> findByDateBetween(LocalDate start, LocalDate end);


}
