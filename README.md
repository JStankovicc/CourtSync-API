# Sports Court Reservation System

A microservices architecture for managing sports court reservations. The system enables users to register, search, and reserve time slots; trainers to manage groups and sessions; and administrators to control the entire system.

## Overview

The system consists of three main business services:

- **AuthService (User Service)** — authentication, authorization, and user management (JWT)
- **CourtReservationService** — management of courts, groups, trainers, and time slots
- **NotificationService** — email notifications for reservations, modifications, and cancellations

Infrastructure components:

- **Eureka Service Discovery** — service registration and discovery
- **API Gateway** — central entry point and request routing (Zuul)
- **MQService (ActiveMQ)** — message broker for asynchronous communication

---

## Infrastructure

### Architecture

```
                    ┌─────────────────┐
                    │   API Gateway   │  :8084
                    │     (Zuul)      │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
              ▼              ▼              ▼
    ┌─────────────┐ ┌─────────────────┐ ┌──────────────────┐
    │ AuthService │ │ CourtReservation│ │ Notification     │
    │   :8080     │ │    Service      │ │   Service        │
    │             │ │     :8081       │ │     :8082        │
    └──────┬──────┘ └────────┬────────┘ └────────┬─────────┘
           │                 │                    │
           │                 │                    │
           └─────────────────┼────────────────────┘
                             │
                    ┌────────┴────────┐
                    │ Eureka Server   │  :8761
                    │ (Discovery)     │
                    └─────────────────┘
                             │
                    ┌────────┴────────┐
                    │ ActiveMQ       │  :61616
                    │ (MQService)    │
                    └─────────────────┘
```

### Components

| Component | Folder | Port | Description |
|-----------|--------|------|-------------|
| **Eureka Service Discovery** | `EurekaServiceDiscovery` | 8761 | Netflix Eureka server for service registration and discovery |
| **API Gateway** | `ApiGateway` | 8084 | Zuul proxy — single public entry point for all API requests |
| **AuthService** | `AuthService` | 8080 | JWT authentication, registration, roles (client, trainer, administrator) |
| **CourtReservationService** | `CourtReservationService` | 8081 | Courts, sports groups, trainers, time slots, reservations |
| **NotificationService** | `NotificationService` | 8082 | Email notifications via ActiveMQ |
| **MQService** | `MQService` | 61616 | ActiveMQ message broker for asynchronous communication |

### Communication

- **Synchronous:** REST between services (e.g., AuthService → CourtReservationService when registering a trainer)
- **Asynchronous:** JMS/ActiveMQ for notifications (reservation, modification, cancellation)
- **Service Discovery:** all services register with Eureka; API Gateway routes by service name

### Databases

- **MySQL** — all services use appropriate databases (ms1, ms2, ms3)
- **JPA/Hibernate** — ORM for persistence

---

## Prerequisites

- Java 17
- Maven 3.6+
- MySQL 8+
- ActiveMQ (or embedded in MQService)

---

## Configuration

Before running, configure the following in `application.properties` for each service:

| Service | Property | Description |
|---------|----------|-------------|
| **AuthService** | `spring.datasource.username`, `spring.datasource.password` | MySQL credentials |
| **AuthService** | `jwt.secret` or `JWT_SECRET` env var | Base64-encoded secret for JWT (min 32 bytes). Dev placeholder included. |
| **CourtReservationService** | `spring.datasource.username`, `spring.datasource.password` | MySQL credentials |
| **NotificationService** | `spring.datasource.username`, `spring.datasource.password` | MySQL credentials |
| **NotificationService** | `spring.mail.username`, `spring.mail.password` | SMTP credentials for sending emails |

---

## Running the System

**Startup order:**

1. **MySQL** — start and create databases
2. **Eureka Server** — `EurekaServiceDiscovery`
3. **ActiveMQ / MQService** — `MQService`
4. **AuthService** — `AuthService`
5. **CourtReservationService** — `CourtReservationService`
6. **NotificationService** — `NotificationService`
7. **API Gateway** — `ApiGateway`

```bash
# Example for each service
cd EurekaServiceDiscovery && mvn spring-boot:run
cd MQService && mvn spring-boot:run
cd AuthService && mvn spring-boot:run
cd CourtReservationService && mvn spring-boot:run
cd NotificationService && mvn spring-boot:run
cd ApiGateway && mvn spring-boot:run
```

Users access the system exclusively through the **API Gateway** on port **8084**.

---

## API Overview

### AuthService
- `POST /auth/registracija` — user registration
- `POST /auth/prijava` — login and JWT token
- `POST /auth/uloge` — create roles
- `POST /auth/dodela-uloge` — assign roles to users
- `PUT /auth/korisnici/{id}` — update user
- `DELETE /auth/korisnici/{id}` — delete user

### CourtReservationService
- `POST /tereni` — add court
- `GET /tereni` — search courts
- `POST /grupe` — create sports group
- `GET /grupe` — search groups
- `GET /treneri` — search trainers
- `POST /termini` — add time slot
- `PUT /termini/{id}` — update time slot
- `DELETE /termini/{id}` — delete time slot
- `GET /termini` — search time slots
- `POST /rezervacije` — make reservation
- `DELETE /rezervacije/{id}` — cancel reservation

### NotificationService
- `POST /notifikacije/slanje` — send notification (via MQ)
- `GET /notifikacije/{korisnikId}` — get notifications for user
- `POST /notifikacije/tipovi` — define notification types
- `PUT /notifikacije/tipovi/{id}` — update notification type
- `DELETE /notifikacije/tipovi/{id}` — delete notification type

---

## Authentication and Authorization

- **JWT** — token-based authentication
- **Roles:** client, trainer, administrator
- Protected routes require a valid JWT token in the header

---

## License

Project for Microservices Components course — Union University, Faculty of Computer Science.
