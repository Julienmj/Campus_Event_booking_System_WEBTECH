# Quiz 2 â€” Campus Event Booking System

> ðŸ“Ž **For a better view, use the GitHub link:** [https://github.com/Julienmj/Campus_Event_booking_System_WEBTECH](https://github.com/Julienmj/Campus_Event_booking_System_WEBTECH)

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

- **Frontend** â€” Vue.js 3 + Tailwind CSS, running on `http://localhost:5173`
- **Backend** â€” Spring Boot REST API, running on `http://localhost:8080`
- **Database** â€” MySQL (`eventbooking_db`)
- **API Testing** â€” Swagger UI at `http://localhost:8080/swagger-ui.html`

---

## MVC Architecture

This project follows the **Model-View-Controller (MVC)** pattern across both the backend and frontend.

- **Model** â€” The data layer. On the backend, `Event` and `Registration` are JPA entities that map directly to MySQL tables. They define the structure of the data the application works with.

- **View** â€” The presentation layer. On the frontend, Vue.js components (`EventCard`, `EventDetailModal`, `EventsView`) are responsible for displaying data to the student and capturing their input. The view never talks to the database directly â€” it only communicates through the API.

- **Controller** â€” The logic layer. On the backend, `EventController` and `RegistrationController` receive HTTP requests, delegate business logic to the service layer, and return the appropriate HTTP responses. They act as the bridge between what the user does on the frontend and what gets stored in the database.

The service layer (`EventService`, `RegistrationService`) sits between the controller and the repository, handling business rules such as checking available seats before allowing a registration.

---

## Backend

### Tech Stack
- Java 21, Spring Boot 3.3, Spring Data JPA, MySQL, springdoc-openapi (Swagger)

### Structure

```
BACKEND/src/main/java/mj/Campus_event_system/
â”œâ”€â”€ config/
â”‚   â””â”€â”€ WebConfig.java               # CORS configuration (allows frontend origin)
â”œâ”€â”€ model/
â”‚   â”œâ”€â”€ Event.java                   # Event entity mapped to the events table
â”‚   â””â”€â”€ Registration.java            # Registration entity mapped to the registrations table
â”œâ”€â”€ repository/
â”‚   â”œâ”€â”€ EventRepository.java         # JPA repository for Event
â”‚   â””â”€â”€ RegistrationRepository.java  # JPA repository for Registration
â”œâ”€â”€ service/
â”‚   â”œâ”€â”€ EventService.java            # Interface defining event operations
â”‚   â”œâ”€â”€ EventServiceImpl.java        # Implementation
â”‚   â”œâ”€â”€ RegistrationService.java     # Interface defining registration operations
â”‚   â””â”€â”€ RegistrationServiceImpl.java # Implementation â€” validates seats, decrements on register
â”œâ”€â”€ controller/
â”‚   â”œâ”€â”€ EventController.java         # Handles /api/events endpoints
â”‚   â””â”€â”€ RegistrationController.java  # Handles /api/registrations endpoints
â””â”€â”€ CampusEventSystemApplication.java
```

### API Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| `GET` | `/api/events` | Get all events | 200 |
| `POST` | `/api/events` | Create a new event | 201 |
| `GET` | `/api/registrations` | Get all registrations | 200 |
| `POST` | `/api/registrations` | Register a student for an event | 201 |

### Backend Setup

![Backend setup](Screenshots/Back-end%20setup%20%26%20dependencies.png)

### MySQL Database

The database `eventbooking_db` is used. Tables are auto-created by Hibernate on first run â€” no SQL script is needed.

![MySQL DB](Screenshots/mysql%20eventbooking_db.png)

### Creating an Event via Swagger

Events are created through Swagger UI using the `POST /api/events` endpoint.

![Insert event via Swagger](Screenshots/inserting%20event%20student's%20ceremony%20event%20through%20swagger%20ui%20%20by%20post%20api.png)

A successful creation returns a **201 Created** response.

![201 response](Screenshots/inserting%20successfully%20with%20201%20http%20status.png)

### Viewing Events via Swagger

All created events can be retrieved using the `GET /api/events` endpoint.

![Get events Swagger](Screenshots/VIEWING%20posted%20event%20through%20swagger%20-get%20api.png)

### Viewing Registrations via Swagger

All student registrations can be retrieved using the `GET /api/registrations` endpoint.

![Get registrations Swagger](Screenshots/view%20registered%20students%20through%20swagger%20-%20get%20api.png)

---

## Frontend

### Tech Stack
- Vue.js 3, Vue Router, Tailwind CSS 4, Vite

### Structure

```
FRONTEND/src/
â”œâ”€â”€ services/
â”‚   â””â”€â”€ api.js               # All API calls to the backend
â”œâ”€â”€ components/
â”‚   â”œâ”€â”€ EventCard.vue         # Displays a single event card with "View & Register" button
â”‚   â””â”€â”€ EventDetailModal.vue  # Modal showing full event details and registration form
â”œâ”€â”€ views/
â”‚   â””â”€â”€ EventsView.vue        # Main page â€” fetches and lists all events from the API
â”œâ”€â”€ router/
â”‚   â””â”€â”€ index.js              # Vue Router config
â”œâ”€â”€ App.vue                   # Root component with header layout
â””â”€â”€ main.js                   # App entry point
```

### How it works

1. On load, the page calls `GET /api/events` and renders one card per event
2. Clicking **"View & Register"** opens a modal showing the full event details â€” title, location, date, and available seats
3. The student enters their name and submits â€” this calls `POST /api/registrations`
4. On success, the seat count decrements live on the page without a full refresh

### Frontend â€” Events Listed

![Frontend events](Screenshots/frontend%20view%20with%20posted%20events.png)

### Student Viewing Seats & Registering

![Student registering](Screenshots/student%20viewing%20available%20seats%20and%20registering.png)

### After Successful Registration on Student's ceremony event

![After booking](Screenshots/front-end%20interface%20after%20booking%20after%20a%20student%20registered%20for%20student%20ceremony.png)

---

## How to Clone & Run

### Prerequisites
- Java 21+
- Maven (use the included `mvnw`)
- Node.js 18+
- MySQL running locally

### 1. Clone the repository

```bash
git clone https://github.com/Julienmj/Campus_Event_booking_System_WEBTECH.git
cd Campus_Event_booking_System_WEBTECH
```

### 2. Create the database

Open MySQL and create the database:

```sql
CREATE DATABASE eventbooking_db;
```

### 3. Run the Backend

```bash
cd BACKEND
mvnw.cmd spring-boot:run      # Windows
./mvnw spring-boot:run        # Mac/Linux
```

Backend starts at `http://localhost:8080` â€” Swagger UI at `http://localhost:8080/swagger-ui.html`

### 4. Run the Frontend

```bash
cd FRONTEND
npm install
npm run dev
```

Frontend starts at `http://localhost:5173`

### 5. Add Events via Swagger

Go to `http://localhost:8080/swagger-ui.html` â†’ expand **POST /api/events** â†’ click **Try it out** â†’ fill in the event details and click **Execute**. The event will instantly appear on the frontend.

---

## Thank  you for reviewing our work🤍 !
