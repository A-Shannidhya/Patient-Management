# Patient Management

A simple RESTful Patient Management System built with Spring Boot and JPA. This project enables CRUD operations (Create, Read, Update, Delete) on patient records while ensuring unique email validation and robust data validation.

---

## Features

- **CRUD Operations**: Effortlessly create, read, update, and delete patient records.
- **Unique Email Validation**: Guarantees each patient has a unique email address.
- **RESTful API**: Provides endpoints for comprehensive patient management.
- **DTO Mapping**: Utilizes Data Transfer Objects for structured request and response payloads.
- **Validation**: Implements validation groups for request payload integrity.
- **Spring Boot**: Facilitates rapid backend development and seamless integration.
- **JPA (Hibernate)**: Enables reliable database operations through JPA repositories.
- **Kafka Integration**: Utilizes Apache Kafka for event-driven communication. CRUD actions on patients can produce events to Kafka topics, supporting scalable and decoupled microservices architectures.
- **gRPC Support**: Implements gRPC endpoints for high-performance, language-agnostic remote procedure calls, allowing other services to interact with the patient management system efficiently.

---

## Endpoints

All endpoints are prefixed with `/patients`:

| Method | Endpoint         | Description                 |
|--------|------------------|-----------------------------|
| GET    | `/get`           | Retrieve all patients       |
| POST   | `/create`        | Create a new patient        |
| PUT    | `/{id}`          | Update a patient by ID      |
| DELETE | `/{id}`          | Delete a patient by ID      |

---

## Event Streaming & RPC

- **Kafka Events**: When patient records are created, updated, or deleted, relevant events are published to Kafka topics. This supports real-time updates, integration with other systems, and audit trails.
- **gRPC Services**: Exposes core patient management actions (CRUD) via gRPC endpoints for efficient inter-service communication in distributed environments.

---

## Data Model

A patient record consists of:

- `id` (UUID, auto-generated)
- `name` (String, required)
- `email` (String, unique, required, validated)
- `address` (String, required)
- `dateOfBirth` (LocalDate, required)
- `registeredDate` (LocalDate, auto-set)

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Gradle
- Swagger/OpenAPI (for API documentation)
- **Apache Kafka** (event streaming)
- **gRPC** (remote procedure calls)

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/A-Shannidhya/Patient-Management.git
   ```

2. **Build the project:**
   ```bash
   ./gradlew build
   ```

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

4. **Access the API:**
   - Base URL: `http://localhost:8080/patients`
   - Swagger UI (if enabled): `http://localhost:8080/swagger-ui.html`

---

## Project Structure

```
patient-service/
  └── src/main/java/com/ankit/patientservice/
      ├── Controller/         # REST Controllers
      ├── model/              # JPA Entities
      ├── service/            # Business Logic
      ├── repository/         # Data Repositories
      ├── mapper/             # DTO Mappers
      ├── kafka/              # Kafka Publishers & Listeners
      ├── grpc/               # gRPC Service Implementations
      └── DTO/                # Data Transfer Objects
```

---

## Author

- **Ayshi Shannidhya Panda**  
  Core logic and initial implementation by Ayshi Shannidhya.

---

## License

This source code is confidential and intended solely for internal use. Unauthorized copying, modification, distribution, or disclosure of this file, via any medium, is strictly prohibited.

---
