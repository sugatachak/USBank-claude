# USBank-claude

US Bank Meeting Setup — a full-stack application that schedules meetings on staff Google Calendars.

## Stack

| Layer | Tech |
|-------|------|
| Frontend | React 18, Vite |
| Backend | Java 21, Spring Boot 3.5, Spring JDBC, Lombok |
| Database | H2 (file-based) |
| External | Google Calendar API (OAuth2) |

## Project

```
GitHub/USBank-claude/
  meeting-setup-app/
    frontend/    React + Vite UI (port 5173)
    backend/     Spring Boot API (port 8080)
  Demo/          Functional specification
  CLAUDE.md      AI assistant guidance
```

## Running

**Backend** (requires JDK 21 and `credentials.json` from Google Cloud Console):
```bash
cd GitHub/USBank-claude/meeting-setup-app/backend
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd GitHub/USBank-claude/meeting-setup-app/frontend
npm install
npm run dev
```

Open `http://localhost:5173` — fill in the meeting form and submit to schedule directly on Google Calendar.

## Google Calendar Setup

1. Enable the [Google Calendar API](https://console.cloud.google.com/apis/library/calendar-json.googleapis.com)
2. Create OAuth2 credentials (Desktop app) and download `credentials.json`
3. Place it at `meeting-setup-app/backend/src/main/resources/credentials.json`
4. On first run a browser window opens to authorize calendar access — token is cached after that
