package kz.iitu.registration.service;

import kz.iitu.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final List<EventInfo> events = new ArrayList<>();

    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Almaty"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Astana"));
        events.add(new EventInfo(3, "IT Career Talk", "Shymkent"));
    }

    public String getWelcomeMessage() {
        return "Welcome to the Event Registration API";
    }

    public List<EventInfo> getAllEvents() {
        return events;
    }

    public EventInfo getEventById(int id) {
        for (EventInfo event : events) {
            if (event.getId() == id) {
                return event;
            }
        }

        return null;
    }
}