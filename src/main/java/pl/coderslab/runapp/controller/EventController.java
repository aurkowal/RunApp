package pl.coderslab.runapp.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.event.EventRequestDto;
import pl.coderslab.runapp.DTO.event.EventResponseDto;
import pl.coderslab.runapp.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    public final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventResponseDto createEvent(@Valid @RequestBody EventRequestDto request) {
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<EventResponseDto> findAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponseDto findEventById(@PathVariable("id") Long id) {
        return eventService.getEvent(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
    }

}
