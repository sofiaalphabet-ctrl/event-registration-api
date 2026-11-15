# Event Registration API

Spring Boot REST API for the Event Registration project. Lab 8 extends the Lab 7 application with full CRUD operations. Event data is stored in memory and resets when the application restarts.

## Technologies
- Java 25
- Spring Boot 4.1.2
- Gradle
- Spring Web MVC

## Run the application
1. Install JDK 25.
2. Open the project folder in IntelliJ IDEA or a terminal.
3. Run on Windows:
   ```powershell
   .\gradlew.bat bootRun
   ```
   On macOS/Linux:
   ```bash
   ./gradlew bootRun
   ```
4. The API runs at `http://localhost:8080`.

## Initial events
- ID 1 — Web Design Workshop — Almaty
- ID 2 — JavaScript Quiz — Astana
- ID 3 — IT Career Talk — Shymkent

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/hello` | Lab 7 welcome message |
| GET | `/api/info` | Lab 7 application information |
| GET | `/api/events` | Get all events |
| GET | `/api/events?title=java` | Search titles (case-insensitive substring) |
| GET | `/api/events/{id}` | Get one event; 404 if missing |
| POST | `/api/events` | Create an event; returns 201 |
| PUT | `/api/events/{id}` | Replace title and location; returns 200 or 404 |
| DELETE | `/api/events/{id}` | Delete; returns 204 or 404 |

## Example request body
POST and PUT accept JSON with title and location. The server assigns and manages IDs.

```json
{
  "title": "Spring Boot Workshop",
  "location": "Almaty"
}
```

Blank or missing title/location values return HTTP 400. PUT preserves the requested event ID and does not create a new event if the ID does not exist.

## Architecture
`RegistrationController` handles HTTP requests and delegates event operations to `EventService` through constructor injection. `EventInfo` is the model. The service stores events in an in-memory `ArrayList`; no database is used.
