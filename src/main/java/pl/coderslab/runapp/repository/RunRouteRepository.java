package pl.coderslab.runapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.RunRoute;

public interface RunRouteRepository extends JpaRepository<RunRoute, Long> {
}
