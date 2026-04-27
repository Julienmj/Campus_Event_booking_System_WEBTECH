# Quiz 2 — Campus Event Booking System

> 📎 **For a better view, use the GitHub link:** [https://github.com/Julienmj/Campus_Event_booking_System_WEBTECH](https://github.com/Julienmj/Campus_Event_booking_System_WEBTECH)

---

## Group Members

| # | Name | Student ID |
|---|------|------------|
| 1 | Mugisha Julien | 26967 |
| 2 | Uwitonze Adeline | 26753 |
| 3 | Mutabazi Hans | 23710 |
| 4 | Gatabazi Uwera Getrude | 27074 |

> Members 3 and 4 joined the group later for communicated reasons.

---

## Project Overview

A full-stack single-page web application that allows AUCA students to view campus events and register for them online.

- **Frontend** — Vue.js 3 + Tailwind CSS, running on `http://localhost:5173`
- **Backend** — Spring Boot REST API, running on `http://localhost:8080`
- **Database** — MySQL (`eventbooking_db`)
- **API Testing** — Swagger UI at `http://localhost:8080/swagger-ui.html`

---

## Backend

### Tech Stack
- Java 21, Spring Boot 3.3, Spring Data JPA, MySQL, springdoc-openapi (Swagger)

### Structure

```
BACKEND/src/main/java/mj/Campus_event_system/
├── config/
│   └── WebConfig.java           # CORS configuration (allows frontend origin)
├── model/
│   ├── Event.java               # Event entity (eventId, title, location, date, availableSeats)
│   └── Registration.java        # Registration entity (regId UUID, name, eventId)
├── repository/
│   ├── EventRepository.java     # JPA repository for Event
│   └── RegistrationRepository.java  # JPA repository for Registration
├── service/
│   ├── EventService.java        # Interface: getAllEvents, getEventById, createEvent
│   ├── EventServiceImpl.java    # Implementation
│   ├── RegistrationService.java # Interface: register, getAllRegistrations
│   └── RegistrationServiceImpl.java # Implementation (validates seats, decrements on register)
├── controller/
│   ├── EventController.java     # Handles /api/events endpoints
│   └── RegistrationController.java  # Handles /api/registrations endpoints
└── CampusEventSystemApplication.java
```

### API Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| `GET` | `/api/events` | Get all events | 200 |
| `POST` | `/api/events` | Create a new event | 201 |
| `GET` | `/api/registrations` | Get all registrations | 200 |
| `POST` | `/api/registrations` | Register a student for an event | 201 |

### Data Models

**Event**
```json
{
  "title": "AUCA Tech Summit",
  "location": "Main Auditorium",
  "date": "2025-08-10",
  "availableSeats": 50
}
```

**Registration**
```json
{
  "name": "Mugisha Julien",
  "eventId": 1
}
```

### Configuration (`application.properties`)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/eventbooking_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```
> Tables are auto-created by Hibernate on first run. No SQL script needed.

---

## Frontend

### Tech Stack
- Vue.js 3, Vue Router, Tailwind CSS 4, Vite

### Structure

```
FRONTEND/src/
├── services/
│   └── api.js               # All API calls (fetchEvents, registerForEvent)
├── components/
│   ├── EventCard.vue         # Displays a single event card with "View & Register" button
│   ├── EventDetailModal.vue  # Modal showing full event details + registration form
│   └── RegistrationModal.vue # Standalone registration modal (backup)
├── views/
│   └── EventsView.vue        # Main page — fetches and lists all events from the API
├── router/
│   └── index.js              # Vue Router config (/ → /events)
├── App.vue                   # Root component with header layout
├── main.js                   # App entry point
└── style.css                 # Tailwind CSS import
```

### How it works
1. On load, `EventsView` calls `GET /api/events` and renders one `EventCard` per event
2. Clicking **"View & Register"** opens `EventDetailModal` showing full details (title, location, date, available seats)
3. The student enters their name and submits — calls `POST /api/registrations`
4. On success, the seat count decrements live on the page without a refresh

---

## Screenshots

### Backend Setup & Dependencies
![Backend setup](Screenshots/Back-end%20setup%20%26%20dependencies.png)

### MySQL Database
![MySQL DB](Screenshots/mysql%20eventbooking_db.png)

### Creating an Event via Swagger (POST)
![Insert event via Swagger](Screenshots/inserting%20event%20student's%20ceremony%20event%20through%20swagger%20ui%20%20by%20post%20api.png)

### Event Created Successfully (201)
![201 response](Screenshots/inserting%20successfully%20with%20201%20http%20status.png)

### Viewing Events via Swagger (GET)
![Get events Swagger](Screenshots/VIEWING%20posted%20event%20through%20swagger%20-get%20api.png)

### Frontend — Events Listed
![Frontend events](Screenshots/frontend%20view%20with%20posted%20events.png)

### Student Viewing Seats & Registering
![Student registering](Screenshots/student%20viewing%20available%20seats%20and%20registering.png)

### Frontend After Successful Registration
![After booking](Screenshots/front-end%20interface%20after%20booking%20after%20a%20student%20registered%20for%20student%20ceremony.png)

### Viewing Registrations via Swagger (GET)
![Get registrations Swagger](Screenshots/view%20registered%20students%20through%20swagger%20-%20get%20api.png)

---

## How to Clone & Run

### Prerequisites
- Java 21+
- Maven (or use the included `mvnw`)
- Node.js 18+
- MySQL running locally with `eventbooking_db` database created

### 1. Clone the repository
```bash
git clone <your-repo-url>
cd Campus_event_system
```

### 2. Create the database
Open MySQL and run:
```sql
CREATE DATABASE eventbooking_db;
```

### 3. Run the Backend
```bash
cd BACKEND
./mvnw spring-boot:run        # Mac/Linux
mvnw.cmd spring-boot:run      # Windows
```
Backend starts at `http://localhost:8080`
Swagger UI at `http://localhost:8080/swagger-ui.html`

### 4. Run the Frontend
```bash
cd FRONTEND
npm install
npm run dev
```
Frontend starts at `http://localhost:5173`

### 5. Add Events
Go to Swagger UI → **POST /api/events** → Try it out → paste:
```json
{
  "title": "AUCA Tech Summit",
  "location": "Main Auditorium",
  "date": "2025-08-10",
  "availableSeats": 50
}
```
Click **Execute**. The event will instantly appear on the frontend.
