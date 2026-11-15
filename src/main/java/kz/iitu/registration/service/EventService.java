package kz.iitu.registration.service;

import kz.iitu.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final List<EventInfo> events = new ArrayList<>();
    private int nextId = 4;

    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Almaty"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Astana"));
        events.add(new EventInfo(3, "IT Career Talk", "Shymkent"));
    }

    public String getWelcomeMessage() {
        return "Welcome to the Event Registration API";
    }

    public List<EventInfo> getAllEvents(String title) {
        if (title == null || title.isBlank()) {
            return events;
        }

        String search = title.trim().toLowerCase();
        return events.stream()
                .filter(event -> event.getTitle().toLowerCase().contains(search))
                .toList();
    }

    public EventInfo getEventById(int id) {
        for (EventInfo event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public EventInfo createEvent(String title, String location) {
        EventInfo event = new EventInfo(nextId++, title.trim(), location.trim());
        events.add(event);
        return event;
    }

    public EventInfo updateEvent(int id, String title, String location) {
        EventInfo event = getEventById(id);
        if (event == null) {
            return null;
        }
        event.setTitle(title.trim());
        event.setLocation(location.trim());
        return event;
    }

    public boolean deleteEvent(int id) {
        EventInfo event = getEventById(id);
        if (event == null) {
            return false;
        }
        events.remove(event);
        return true;
    }
}