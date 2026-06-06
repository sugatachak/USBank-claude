# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

US Bank Meeting Setup – a React + Java Spring Boot full-stack application for the US Bank to schedule meetings on staff Google Calendars. The project is currently in the **pre-development phase** (specification documents only; no implementation scaffolded yet).

## Planned Stack

- **Frontend**: React + Vite
- **Backend**: Java 21, Spring Boot with Actuator, Spring DAO, Lombok
- **Database**: H2 file-based, single `Members` table (fields: `agenda`, `date`, `starttime`, `duration`, `agendaitems`, `restrictions`)
- **External Integrations**: Google Calendar API (credentials in `demo/intentMeetingSetup.md` – move to env vars before use)

## Application Structure (to be created)

```
meeting-setup-app/
  frontend/   (React + Vite)
  backend/    (Spring Boot)
```

## UI Flow

- **Screen 1 (Invite)**: Form with agenda, date (calendar picker), starttime (clock picker), duration dropdown (15/30/45/60 min), agendaitems, restrictions. SUBMIT sends data to backend and navigates to Screen 2. CANCEL resets the form.
- **Screen 2 (Status)**: Spinner while backend processes; shows "Successfully Scheduled" or "Error while Scheduling".

## Build & Run Commands (once scaffolded)

**Frontend (React/Vite):**
```bash
cd meeting-setup-app/frontend
npm install
npm run dev        # development server
npm run build      # production build
npm run lint       # lint
```

**Backend (Spring Boot – Maven assumed):**
```bash
cd meeting-setup-app/backend
./mvnw spring-boot:run    # start backend
./mvnw test               # run tests
./mvnw test -Dtest=MyTest # run single test
```

**H2 Console**: available at `http://localhost:8080/h2-console` once backend is running (requires `spring.h2.console.enabled=true` in `application.properties`).

## Key Design Decisions

- Backend maintains state across screen navigations; frontend does not use local session storage for persisting form state between screens.
- Each UI screen corresponds to a dedicated REST endpoint in the backend.
- US Bank branding (logo, color scheme) sourced from https://www.usbank.com/index.html– submit/cancel buttons are SVGs.
- Spring Boot Actuator health checks must be included.

## Repository Layout

```
demo/
  intentMeetingSetup.md   – primary functional specification
intent/
  architecture.intent.md  – generic OTEL/Kubernetes template (not US Bank-specific, ignore)
  constraints.md          – generic constraints template (not US Bank-specific, ignore)
```
