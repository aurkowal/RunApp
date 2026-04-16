package pl.coderslab.runapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.runapp.DTO.event.EventParticipantDto;
import pl.coderslab.runapp.DTO.event.EventResponseDto;
import pl.coderslab.runapp.entity.Event;
import pl.coderslab.runapp.entity.EventRegistration;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.repository.EventRegistrationRepository;
import pl.coderslab.runapp.repository.EventRepository;
import pl.coderslab.runapp.repository.RunnerRepository;

import java.util.List;

@Service
public class EventRegistrationService {

    private final EventRegistrationRepository eventRegistrationRepository;
    private final RunnerRepository runnerRepository;
    private final EventRepository eventRepository;
    public EventRegistrationService(EventRegistrationRepository eventRegistrationRepository, RunnerRepository runnerRepository, EventRepository eventRepository) {
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.runnerRepository = runnerRepository;
        this.eventRepository = eventRepository;
    }

    public void register(Long runnerId, Long eventId) {
        if(eventRegistrationRepository.findByRunnerIdAndEventId(runnerId, eventId).isPresent()) {
            return;
        }

        Runner runner = runnerRepository.findById(runnerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Runner not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Event not found"));

        EventRegistration registration = new EventRegistration(runner, event);
        eventRegistrationRepository.save(registration);

    }

    public void unregister(Long runnerId, Long eventId) {
        eventRegistrationRepository.findByRunnerIdAndEventId(runnerId, eventId)
                .ifPresent(eventRegistrationRepository::delete);
    }

    public List<EventResponseDto> getEventsForRunner(Long runnerId) {
        return eventRegistrationRepository.findByRunnerId(runnerId).stream()
                .map(EventRegistration::getEvent)
                .map(event -> new EventResponseDto(
                        event.getId(),
                        event.getName(),
                        event.getDate(),
                        event.getCity(),
                        event.getDistance()
                )).toList();
    }

    public List<EventParticipantDto> getParticipants(Long eventId) {
        return eventRegistrationRepository.findByEventId(eventId).stream()
                .map(EventRegistration::getRunner)
                .map(runner -> new EventParticipantDto(
                        runner.getId(),
                        runner.getName()
                )).toList();
    }
}
