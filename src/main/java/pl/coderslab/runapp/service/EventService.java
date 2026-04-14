package pl.coderslab.runapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.runapp.DTO.event.EventRequestDto;
import pl.coderslab.runapp.DTO.event.EventResponseDto;
import pl.coderslab.runapp.entity.Event;
import pl.coderslab.runapp.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponseDto createEvent(EventRequestDto request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setDate(request.getDate());
        event.setCity(request.getCity());
        event.setDistance(request.getDistance());
        eventRepository.save(event);
        return toDto(event);

    }

    public EventResponseDto getEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Event not found"));
        return toDto(event);
    }

    public List<EventResponseDto> getAllEvents() {
        return eventRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    private EventResponseDto toDto(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getCity(),
                event.getDistance()
        );
    }

}
