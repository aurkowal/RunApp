package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.EventRegistration;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {


}
