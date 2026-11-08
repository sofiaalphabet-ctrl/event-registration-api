# Event Registration API

## Description

This project is a Spring Boot backend application for the Event Registration scenario. It provides REST API endpoints and stores event information in memory.

## Technologies

- Java 25
- Spring Boot 4.1.2
- Gradle
- Spring Web

## How to Run

1. Install JDK 17 or later.
2. Open the project in IntelliJ IDEA.
3. Open the terminal in the project directory.
4. Run the application:

```bash
.\gradlew.bat bootRun
```

5. Open http://localhost:8080/api/events in your browser.

## Project Structure

- EventRegistrationApiApplication – main Spring Boot application class.
- RegistrationController – handles HTTP requests and returns JSON responses.
- EventService – contains application logic and the in-memory event list.
- EventInfo – model containing event ID, title, and location.

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/hello | Returns a welcome message |
| GET | /api/events | Returns all events |
| GET | /api/info | Returns application information |
| GET | /api/events/{id} | Returns an event by ID |

## Dependency Injection

The RegistrationController receives EventService through its constructor. Spring automatically creates and injects the service object.

## Data Storage

Event information is stored in an in-memory ArrayList. The application does not use a database.