# Patient Management

A simple RESTful Patient Management System built with Spring Boot and JPA. This project allows you to perform CRUD operations (Create, Read, Update, Delete) on Patient records with unique email validation and basic data validation.

---

## Features

- **CRUD Operations**: Create, read, update, and delete patient records.
- **Unique Email Validation**: Ensures each patient has a unique email address.
- **RESTful API**: Exposes endpoints to manage patients.
- **DTO Mapping**: Uses DTOs for request and response payloads.
- **Validation**: Leverages validation groups for request payloads.
- **Spring Boot**: Rapid backend development and easy integration.
- **JPA (Hibernate)**: Database integration via JPA repositories.

---

## Endpoints

All endpoints are prefixed with `/patients`:

| Method | Endpoint         | Description                 |
|--------|------------------|-----------------------------|
| GET    | `/get`           | Get all patients            |
| POST   | `/create`        | Create a new patient        |
| PUT    | `/{id}`          | Update a patient by ID      |
| DELETE | `/{id}`          | Delete a patient by ID      |

---

## Data Model

A patient record contains:

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
      └── DTO/                # Data Transfer Objects
```

---

## Author

- **Ayshi Shannidhya Panda**
- Initial implementation and core logic by Ankit.

---

## License

This source code is confidential and intended solely for internal use. Unauthorized copying, modification, distribution, or disclosure of this file, via any medium, is strictly prohibited.

---
