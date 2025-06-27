# OpenPolls Backend – Microservices Architecture

## Overview

The OpenPolls Backend has been restructured from a monolithic application to a distributed **microservices architecture** using **Spring Boot**, **Spring Cloud**, and **Docker Compose**. Each service is responsible for a specific domain, enabling independent deployment, better scalability, and easier maintenance.

---

## Microservices Overview

| Service               | Responsibility                                          |
|-----------------------|----------------------------------------------------------|
| `api-gateway`         | Routes incoming requests and handles cross-cutting concerns like security via Spring Cloud Gateway |
| `auth-service`        | Handles authentication, authorization, and JWT generation/validation |
| `users-service`       | Manages user data, roles, and access control             |
| `polls-service`       | Responsible for poll creation, editing, and deletion     |
| `submissions-service` | Handles vote submissions and enforces response limits    |
| `config-server`       | Centralized configuration management via Spring Cloud Config |
| `discovery-server`    | Service discovery and registration using Eureka          |
| `kafka` *(optional)*  | Asynchronous communication across services               |

---

## Key Features

- **Poll Management:** Create, update, and delete polls via REST endpoints.
- **Response Validation:** Prevent duplicate submissions based on IP or email.
- **JWT-Based Security:** Role-based access control (admin, editor, viewer).
- **Centralized Configuration:** All services load config from a centralized source.
- **API Gateway Routing:** Unified entry point to the system with filtering and path rewriting.
- **Service Discovery:** Dynamic resolution and registration using Eureka.
- **Containerized Deployment:** Fully dockerized for fast local development and CI/CD.

---

## Technologies Used

- **Spring Boot** – Java framework for microservice creation
- **Spring Cloud Gateway** – API Gateway with routing, filtering, and path rewriting
- **Spring Cloud Config** – Centralized externalized configuration
- **Spring Cloud Eureka** – Service registration and discovery
- **Spring Security + JWT** – Secure, stateless authentication and authorization
- **Spring Data JPA** – Simplified data access layer
- **Kafka** – Event streaming and asynchronous communication
- **Docker + Docker Compose** – Environment orchestration
- **Lombok** – Reduces Java boilerplate
- **PostgreSQL / H2** – Per-service database management

---

## Running the Project

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker and Docker Compose

### Run with Docker Compose

```bash
docker-compose up --build
```

### Access Points

- **API Gateway:** [http://localhost:5000](http://localhost:5000)
- **Eureka Dashboard:** [http://localhost:8761](http://localhost:8761)

Each microservice runs on its own internal port but is accessed via the API Gateway for external communication.

---

## Development Profiles

Spring profiles are used to separate local development from containerized environments.

- **application.yml** – Base configuration
- **application-local.yml** – Used when running locally via IDE (e.g., STS)
- **application-docker.yml** – Used when running inside Docker (`SPRING_PROFILES_ACTIVE=docker`)
