package pl.coderslab.runapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.RunRoute;

import java.util.List;

public interface RunRouteRepository extends JpaRepository<RunRoute, Long> {

    List<RunRoute> findAllByRunnerId(Long runnerId);

    List<RunRoute> findByStartLocationId(Long locationId);
}
