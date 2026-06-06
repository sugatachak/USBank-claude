# Intent: Meeting Setup App

## Objectives

1) Create the application as following
   - Create a new directory named: `meeting-setup-app`
   - Generate a complete React + Java application using Vite. This application shall be used by US Bank to schedule the meetings on the staff calendars
   - Add recommended approach, the file based H2 database to the backend. Use Spring DAO to communicate with backend. Database should have single table `Members` that holds the meeting information in the fields like agenda, date, `starttime`, duration, `agendaitems`, restrictions
   - Add required configuration in the back end so that database can be seen in browser.

2) Then build/start the complete application
   - Start UI in a chrome tab
   - Start Spring Boot Backend
   - Show H2 database in chrome tab

---

## Functional Requirements

### 1. UI has 1 screen with guidelines below
- Use the logo and look and feel from https://www.usbank.com/index.html
- Send the state/details to Backend after SUBMIT button is clicked on every screen, so backend can maintain the state across page navigations.
- Screen 1 should have SUBMIT and CANCEL buttons at the bottom of the screen.
- Cancel button shall reset that specific page to initial state of the same page
- Submit button will send the user selected option to the Backend and takes you to the next screen in UI
- Screen 1 for INVITE: Meeting organizer enters all meeting details as listed below
  - agenda (in a textbox),
  - date (in a textbox with calendar as popup),
  - `starttime` (text box with clock as popup),
  - duration (drop down with valued as 15, 30, 45, 60),
  - textbox `agendaitems` (as text box),
  - restrictions (as text box)
- Screen 2 waits for the backend to finish the execution and shows spinning wheel while waiting for backend to finish the execution from previous step (which is step 1). If backend finishes successfully, display a message "Successfully scheduled". If backend faces any error, display a message "Error while Scheduling"

---

### 2. Backend
- Use JDK 21 as baseline Java
- Use Java Spring Boot with Actuator Health Checks
- Use Lombok Annotations
- Backend exposes REST API call to communicate with each screen in UI
- After screen 1 is submitted, it calls backend which connects to calendar identified below and schedules the meeting
  - sugatachak@gmail.com and app password is "democlaude"

---

### 3. State Management
- Backend and Front End manages the state as user navigates between all the screens that UI shows

---

### 4. Assets

Create:
- Use the logo and look and feel from https://www.usbank.com/index.html
- Every page should have above/same logo
- Use simple SVG for submit and cancel buttons.

---

### 5. Styling
- Centered layout
- Clean modern UI
- Responsive
- Use the logo and look and feel from https://www.usbank.com/index.html

---

### 6. Project Structure

```
meeting-setup-app/
```

---

### 7. Add
- README.md
