package pl.coderslab.runapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.event.EventParticipantDto;
import pl.coderslab.runapp.DTO.event.EventResponseDto;
import pl.coderslab.runapp.entity.EventRegistration;
import pl.coderslab.runapp.service.EventRegistrationService;

import java.util.List;

@RestController
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;
    public EventRegistrationController(EventRegistrationService eventRegistrationService) {
        this.eventRegistrationService = eventRegistrationService;
    }

    @PostMapping("/events/{eventId}/register/{runnerId}")
    public void register(@PathVariable("eventId") Long eventId, @PathVariable("runnerId") Long runnerId) {
        eventRegistrationService.register(runnerId, eventId);
    }

    @DeleteMapping("/events/{eventId}/register/{runnerId}")
    public void unregister(@PathVariable("eventId") Long eventId, @PathVariable("runnerId") Long runnerId) {
        eventRegistrationService.unregister(runnerId, eventId);
    }

    @GetMapping("/runners/{runnerId}/events")
    public List<EventResponseDto> getEventsForRunner(@PathVariable("runnerId") Long runnerId) {
        return eventRegistrationService.getEventsForRunner(runnerId);
    }

    @GetMapping("/events/{eventId}/runners")
    public List<EventParticipantDto> getParticipants(@PathVariable("eventId") Long eventId) {
        return eventRegistrationService.getParticipants(eventId);
    }


}
