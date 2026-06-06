# US Bank Meeting Setup App

Full-stack application for scheduling meetings on US Bank staff Google Calendars.

## Stack

| Layer | Tech |
|-------|------|
| Frontend | React 18, Vite |
| Backend | Java 21, Spring Boot 3.5, Spring JDBC, Lombok |
| Database | H2 (file-based) |
| External | Google Calendar API (OAuth2) |

## Structure

```
meeting-setup-app/
  frontend/    React + Vite UI (port 5173)
  backend/     Spring Boot API (port 8080)
```

## Prerequisites

- Node.js ≥ 18
- JDK 21 — install from https://adoptium.net/

## Google Calendar Setup (required before first run)

1. Go to [Google Cloud Console](https://console.cloud.google.com/) and create a project.
2. Enable the **Google Calendar API**.
3. Create **OAuth 2.0 credentials** → Desktop app → download `credentials.json`.
4. Place the file at `backend/src/main/resources/credentials.json`.
5. On first backend start a browser window opens to authorize `sugatachak@gmail.com`. After that the token is cached in `backend/tokens/`.

## Running the Backend

```bash
cd meeting-setup-app/backend
./mvnw spring-boot:run          # first run downloads all Maven deps (~2 min)
```

| URL | Purpose |
|-----|---------|
| `http://localhost:8080/actuator/health` | Health check |
| `http://localhost:8080/h2-console` | H2 browser console |
| `http://localhost:8080/api/meetings` | POST — schedule a meeting |
| `http://localhost:8080/api/meetings/{id}` | GET — meeting status |

**H2 console login:**
- JDBC URL: `jdbc:h2:file:./data/meetingdb`
- User: `sa` / Password: `password`

## Running the Frontend

```bash
cd meeting-setup-app/frontend
npm install
npm run dev
```

Open `http://localhost:5173`.

## API

### POST /api/meetings

```json
{
  "agenda": "Q3 Planning",
  "date": "2026-07-15",
  "starttime": "10:00",
  "duration": 60,
  "agendaitems": "Budget review, roadmap",
  "restrictions": "No external attendees"
}
```

Response:
```json
{ "id": 1, "status": "SCHEDULED", "message": "Successfully scheduled" }
```

Possible statuses: `PENDING`, `SCHEDULED`, `FAILED`.
