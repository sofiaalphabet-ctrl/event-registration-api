package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final EventService eventService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${app.course}")
    private String course;

    @Value("${app.semester}")
    private int semester;

    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", eventService.getWelcomeMessage()
        );
    }

    @GetMapping("/events")
    public List<EventInfo> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return Map.of(
                "application", applicationName,
                "course", course,
                "semester", semester
        );
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {

        EventInfo event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.status(404).body(
                    Map.of("message", "Event not found")
            );
        }

        return ResponseEntity.ok(event);
    }
}