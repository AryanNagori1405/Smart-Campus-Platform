# Smart Campus & Placement Platform

A progressive full-stack application designed to demonstrate practical **Java backend engineering** from fundamentals to production readiness.

The project is being built incrementally — starting from a simple **Student CRUD API** and gradually evolving into a realistic campus placement platform with relational data, authentication, testing, deployment, and a React frontend.

---

## 🎯 Project Objective

The goal is not just to build a working application, but to understand **how a real backend evolves**.

The project follows this progression:

> **Basic Implementation → Clean Architecture → Domain Expansion → Optimization → Production Readiness**

Each feature is introduced to solve a real engineering problem rather than simply adding technologies or annotations.

---

## 🚀 Current Focus

**Java Backend Development**

The backend is currently being developed using:

- Spring Boot
- REST APIs
- JPA / Hibernate
- Spring Data JPA
- PostgreSQL

---

## 🛠️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring MVC / REST
- Spring Data JPA
- JPA / Hibernate
- PostgreSQL
- Maven

### Development & Testing

- Git / GitHub
- Postman

### Planned Technologies

- Spring Security
- JWT
- JUnit
- Mockito
- Docker
- Docker Compose
- React
- Cloud Deployment

---

# 📌 Project Status

## V1 — Foundation & Student API

**Status: ✅ Complete**

The initial Student module has been implemented and tested end-to-end.

### Implemented

- [x] Spring Boot project setup
- [x] Maven project configuration
- [x] PostgreSQL database integration
- [x] Student JPA entity
- [x] Spring Data JPA repository
- [x] Service layer
- [x] REST Controller
- [x] Complete Student CRUD operations
- [x] Request validation
- [x] Global exception handling
- [x] Custom `StudentNotFoundException`
- [x] Structured API error responses
- [x] Request DTO (`StudentRequest`)
- [x] Response DTO (`StudentResponse`)
- [x] DTO ↔ Entity mapping
- [x] Spring-managed `StudentMapper`
- [x] Constructor-based dependency injection
- [x] CRUD testing using Postman

---

## 🏗️ Current Architecture

### Request Flow

```text
HTTP Request
     ↓
Controller
     ↓
Request DTO
     ↓
Service
     ↓
Mapper
     ↓
Entity
     ↓
Repository
     ↓
Spring Data JPA
     ↓
Hibernate
     ↓
PostgreSQL
```

### Response Flow

```text
PostgreSQL
     ↓
Hibernate / JPA
     ↓
Entity
     ↓
Mapper
     ↓
Response DTO
     ↓
HTTP Response
```

### Layer Responsibilities

| Layer | Responsibility |
|---|---|
| **Controller** | Handles HTTP requests and API responses |
| **DTO** | Defines API request/response contracts |
| **Service** | Contains application/business logic |
| **Mapper** | Converts DTOs ↔ Entities |
| **Entity** | Represents persistent domain data |
| **Repository** | Handles data access |
| **JPA / Hibernate** | ORM and persistence management |
| **PostgreSQL** | Stores application data |

---

## 👨‍🎓 Current Student API

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/students` | Get all students |
| `GET` | `/students/{id}` | Get student by ID |
| `POST` | `/students` | Create a student |
| `PUT` | `/students/{id}` | Update a student |
| `DELETE` | `/students/{id}` | Delete a student |

> All current CRUD operations have been tested using Postman.

---

## 🛡️ Validation & Error Handling

The Student API currently includes request validation.

### Validation Rules

- Required fields cannot be blank
- Email must have a valid format
- Graduation year must fall within the configured range

Validation failures return structured API responses.

### Example Validation Response

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "course": "must not be blank"
  }
}
```

Missing students return **HTTP 404** with a consistent error response.

---

## 🔄 DTO Architecture

The API intentionally does **not expose the JPA entity directly**.

### Request Flow

```text
StudentRequest
      ↓
StudentMapper
      ↓
Student Entity
```

### Response Flow

```text
Student Entity
      ↓
StudentMapper
      ↓
StudentResponse
```

This separates the API contract from the persistence model and reduces coupling between the API and database layers.

---

# 🚀 Project Roadmap

## V1 — Foundation

- [x] Spring Boot setup
- [x] Student CRUD
- [x] PostgreSQL integration
- [x] Validation
- [x] Exception handling
- [x] DTO architecture
- [x] Mapper + dependency injection

## V2 — Domain Expansion

- [ ] JPA relationships
- [ ] Student ↔ Project
- [ ] Student ↔ Skill
- [ ] Company
- [ ] Job
- [ ] Application
- [ ] Interview
- [ ] Pagination
- [ ] Sorting
- [ ] Filtering
- [ ] Custom queries / JPQL
- [ ] Database indexes
- [ ] Transactions

## V3 — Production Backend

- [ ] Spring Security
- [ ] Authentication
- [ ] Authorization
- [ ] Password hashing
- [ ] JWT
- [ ] Role-based access
- [ ] JUnit
- [ ] Mockito
- [ ] MockMvc
- [ ] Integration testing
- [ ] Logging
- [ ] Profiles
- [ ] Externalized configuration
- [ ] API documentation
- [ ] Health checks
- [ ] Docker
- [ ] Docker Compose
- [ ] Deployment

## V4 — Full Platform

- [ ] React frontend
- [ ] Student dashboard
- [ ] Company / recruiter dashboard
- [ ] Admin dashboard
- [ ] Authentication UI
- [ ] API integration
- [ ] Placement readiness
- [ ] Skill-to-job matching
- [ ] Job recommendations
- [ ] Final deployment and documentation

---

## 📚 Development Approach

The project is being developed **one meaningful feature at a time**.

The learning process for each feature is:

```text
Concept
   ↓
Why it exists
   ↓
Simple example
   ↓
Small implementation
   ↓
Important points
   ↓
Project usage
   ↓
Test
   ↓
Review
   ↓
Move forward
```

The objective is to understand the **engineering decisions behind the code** rather than simply memorizing framework annotations.

---

## 📖 Project Progress Documentation

Detailed documentation is maintained separately to preserve the evolution of the project.

| Version | Document | Purpose |
|---|---|---|
| v1.1 | `SCP - Project Documentation - v1.1` | Initial project blueprint and architecture |
| v1.2 | `SCP_Project_Documentation_V1.2_Progress.pdf` | Foundation, CRUD, validation, exception handling, DTO architecture and mapper milestone |
| v2 | `SCP_Project_Documentation_V2_Domain_Blueprint.pdf` | Upcoming relational domain expansion |
| v3–v4 | `SCP_Project_Documentation_V3_V4_Roadmap.pdf` | Production backend and full-platform roadmap |

The documentation is intentionally maintained as **historical snapshots** rather than continuously overwriting previous project stages.

---

# 🎯 Final Vision

The final goal is to build more than a CRUD application.

The **Smart Campus & Placement Platform** will eventually connect:

```text
Students
   ↓
Skills ─── Projects
   ↓
Placement Readiness
   ↓
Jobs ← Companies
   ↓
Applications
   ↓
Interviews
   ↓
Placement Outcomes
```

The completed project should demonstrate practical understanding of:

- Java backend development
- Spring Boot
- REST APIs
- Layered architecture
- DTOs and API contracts
- JPA / Hibernate
- Relational database design
- Transactions
- Security
- Automated testing
- Docker
- Deployment
- React integration
- Real-world backend engineering practices

---

## 👨‍💻 Project Owner

**Aryan Nagori**

`Java Backend Development` • `Spring Boot` • `JPA/Hibernate` • `PostgreSQL`

> **Build → Understand → Improve → Optimize**
>
> *One meaningful engineering problem at a time.*
