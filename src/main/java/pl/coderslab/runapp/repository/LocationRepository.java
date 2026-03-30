package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByRunnerId(Long runnerId);
}
