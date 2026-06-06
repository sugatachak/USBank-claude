# Session Log — 2026-06-06

## What was accomplished

### 1. Project scaffolded from spec
- Read `Demo/intentMeetingSetup.md` (functional spec)
- Scaffolded `meeting-setup-app/` from scratch:
  - **Backend**: downloaded Spring Boot 3.5 starter from start.spring.io (Java 21, web, actuator, jdbc, h2, lombok) + added Google Calendar API dependencies
  - **Frontend**: generated React 18 + Vite project

### 2. Backend source files written
| File | Purpose |
|------|---------|
| `MeetingSetupApplication.java` | Entry point |
| `config/CorsConfig.java` | Allows `localhost:5173` |
| `controller/MeetingController.java` | `POST /api/meetings`, `GET /api/meetings/{id}` |
| `dao/MemberDao.java` | Spring JDBC DAO for `Members` table |
| `model/Member.java` | DB model (Lombok `@Data @Builder`) |
| `model/MeetingRequest.java` | POST body DTO |
| `model/MeetingResponse.java` | Response DTO |
| `service/MeetingService.java` | Orchestrates DB save + Calendar call |
| `service/CalendarService.java` | Google Calendar API OAuth2 integration |
| `resources/application.properties` | H2 file DB, CORS, Actuator, Calendar config |
| `resources/schema.sql` | `Members` table auto-created on startup |
| `resources/credentials.json.template` | Template for Google OAuth2 credentials |

### 3. Frontend source files written
| File | Purpose |
|------|---------|
| `src/App.jsx` | Screen router: `invite` → `status` |
| `src/components/USBankLogo.jsx` | SVG logo (red shield + navy wordmark) |
| `src/components/InviteScreen.jsx` | Meeting form, fires POST on submit, navigates immediately |
| `src/components/StatusScreen.jsx` | Shows spinner while POST resolves, then success/error |
| `src/index.css` | US Bank color scheme (red `#CC0000`, navy `#0C2340`, gold `#B9975B`) |
| `vite.config.js` | Proxy `/api` → `localhost:8080` |

### 4. Bug fixed
- `MemberDao.save()` used `Statement.RETURN_GENERATED_KEYS` which caused H2 to return both `ID` and `CREATED_AT` columns, making `keyHolder.getKey()` throw `InvalidDataAccessApiUsageException`
- Fix: changed to `new String[]{"ID"}` to request only the primary key column

### 5. Infrastructure setup
- Installed Java 21 (Temurin) from `.pkg` downloaded from adoptium.net
- Maven not installed — used `mvnw` wrapper bundled with Spring Initializr project
- Node.js v24.13.0 / npm 11.6.2 already present

### 6. Google Calendar integration
- Downloaded `credentials.json` from Google Cloud Console (OAuth2 Desktop app)
- Placed at `backend/src/main/resources/credentials.json` (gitignored)
- OAuth flow triggered on first `POST /api/meetings`
- Token cached at `backend/tokens/` after first authorization
- Live calendar event confirmed on `sugatachak@gmail.com` for 2026-07-15 10:00 AM "Q3 Planning Session"

### 7. Git + GitHub
- Git root: `/Users/sugatachakraborty/Documents` (pre-existing repo, no prior commits)
- Added `.gitignore` to exclude `credentials.json`, `tokens/`, `target/`, `.DS_Store`
- Initial commit `746cb96` — 44 files, 4387 insertions
- Remote: `https://github.com/sugatachak/USBank-claude`
- GitHub PAT stored in `~/.cursor/mcp.json` (updated during session)
- Pushed and verified — README rendering correctly on GitHub

## Running the app

```bash
# Backend (port 8080)
cd ~/Documents/GitHub/USBank-claude/meeting-setup-app/backend
./mvnw spring-boot:run

# Frontend (port 5173)
cd ~/Documents/GitHub/USBank-claude/meeting-setup-app/frontend
npm run dev
```

- H2 Console: http://localhost:8080/h2-console (JDBC: `jdbc:h2:file:./data/meetingdb`, user: `sa`, pass: `password`)
- Health: http://localhost:8080/actuator/health
- Google Calendar OAuth token is cached — no re-authorization needed for subsequent runs

## Key file locations
- Credentials template: `backend/src/main/resources/credentials.json.template`
- Credentials (gitignored): `backend/src/main/resources/credentials.json`
- OAuth token cache (gitignored): `backend/tokens/`
- GitHub repo: https://github.com/sugatachak/USBank-claude
