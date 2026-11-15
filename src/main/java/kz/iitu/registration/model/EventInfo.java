package kz.iitu.registration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventInfo {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String title;
    private String location;

    public EventInfo() {
    }

    public EventInfo(int id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}