package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.EventRegistration;

import java.util.List;
import java.util.Optional;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    Optional<EventRegistration> findByRunnerIdAndEventId(Long runnerId, Long eventId);

    List<EventRegistration> findByRunnerId(Long runnerId);

    List<EventRegistration> findByEventId(Long eventId);


}
