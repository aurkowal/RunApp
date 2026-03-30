package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.Runner;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
