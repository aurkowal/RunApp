package pl.coderslab.runapp.controller;

import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.runapp.entity.EventRegistration;
import pl.coderslab.runapp.service.EventRegistrationService;

@RestController
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;
    public EventRegistrationController(EventRegistrationService eventRegistrationService) {
        this.eventRegistrationService = eventRegistrationService;
    }


}
