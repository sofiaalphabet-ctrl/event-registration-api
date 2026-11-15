package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
        return Map.of("message", eventService.getWelcomeMessage());
    }

    @GetMapping("/events")
    public List<EventInfo> getAllEvents(@RequestParam(required = false) String title) {
        return eventService.getAllEvents(title);
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Event not found"));
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody EventInfo request) {
        if (!isValid(request)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Title and location must not be blank"));
        }

        EventInfo created = eventService.createEvent(request.getTitle(), request.getLocation());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable int id, @RequestBody EventInfo request) {
        if (!isValid(request)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Title and location must not be blank"));
        }

        EventInfo updated = eventService.updateEvent(id, request.getTitle(), request.getLocation());
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Event not found"));
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable int id) {
        if (!eventService.deleteEvent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Event not found"));
        }
        return ResponseEntity.noContent().build();
    }

    private boolean isValid(EventInfo event) {
        return event != null
                && event.getTitle() != null && !event.getTitle().isBlank()
                && event.getLocation() != null && !event.getLocation().isBlank();
    }
}