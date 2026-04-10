package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
