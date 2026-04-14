# Student API - Lab 6 & Lab 7a

## 📌 Overview
This project is a RESTful Web API built using **Spring Boot**. It allows users to manage student records and demonstrates:

- **Lab 6:** Data Persistence using **Spring Data JPA**
- **Lab 7a:** Implementation of a **RESTful Web API** with all HTTP methods

The API supports full CRUD operations and uses **PostgreSQL** as the database.

---

## 🛠️ Technologies Used
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Hibernate
- Swagger (springdoc-openapi)
- Maven

---

## 📂 Project Structure

com.apsd.studentAPI
├── controller        → REST API endpoints
├── service           → business logic
├── repository        → database access (JPA)
├── entity            → database model (Student)
├── exception         → custom and global exception handling
└── StudentApiApplication   → main application

---

## 🗄️ Data Model

### Student Entity

| Field       | Type   | Description |
|------------|--------|------------|
| id         | Long   | Primary key (auto-generated) |
| name       | String | Student name |
| email      | String | Student email (validated) |
| course     | String | Course name |
| major      | String | Major |
| studentID  | String | Unique student ID |

Validation:
- `@NotBlank` for required fields
- `@Email` for valid email format

---

## ⚙️ Configuration

### application.properties

spring.application.name=studentAPI

spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

springdoc.swagger-ui.path=/swagger-ui.html

---

## 🗃️ Database Setup

### Start PostgreSQL
brew services start postgresql

### Create Database
CREATE DATABASE studentdb;

---

## 🚀 Running the Application

### Using IntelliJ
- Open `StudentApiApplication.java`
- Click Run

### Using Terminal
mvn spring-boot:run

---

## 📘 API Documentation (Swagger)

Open in browser:
http://localhost:8080/swagger-ui.html

---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|-------|--------|------------|
| GET | /api/students | Get all students |
| GET | /api/students/{id} | Get student by ID |
| POST | /api/students | Create new student |
| PUT | /api/students/{id} | Update full student |
| PATCH | /api/students/{id} | Partial update |
| DELETE | /api/students/{id} | Delete student |

---

## 🧪 API Testing Screenshots

The following screenshots demonstrate successful testing of all HTTP methods using Swagger UI:

- 1. APIs overview → screen-shorts/1.APIs.png  
- 2. POST (create student) → screen-shorts/2.POST.png  
- 3. GET (all students) → screen-shorts/3.GET (all students).png  
- 4. GET (by id) → screen-shorts/4.GET (by id).png  
- 5. PUT (update full student) → screen-shorts/5.PUT (update full student).png  
- 6. PATCH (update major only) → screen-shorts/6.PATCH (update major only).png  
- 7. DELETE → screen-shorts/7.DELETE.png  
- 8. DELETE again (404 not found) → screen-shorts/8. DELETE (id 1 again - 404 not found).png  

---

## 🧪 Example Request

### Create Student (POST)

{
  "name": "Moe",
  "email": "hphyu@miu.edu",
  "course": "Applied Software Development",
  "major": "Computer Science",
  "studentID": "618073"
}

---

## ⚠️ Notes

- `id` is auto-generated and should NOT be included in POST requests
- Validation errors return HTTP `400 Bad Request`
- Not found resources return HTTP `404 Not Found`
- All data is stored in PostgreSQL via JPA

---

## ✅ Features Implemented

- RESTful API with all HTTP methods (GET, POST, PUT, PATCH, DELETE)
- Layered architecture (Controller → Service → Repository)
- Data persistence using Spring Data JPA
- PostgreSQL database integration
- Input validation
- Global exception handling
- Swagger UI for API testing

---

## 🎯 Conclusion

This project demonstrates:
- Data persistence using JPA (Lab 6)
- REST API design and implementation (Lab 7a)

It follows best practices for clean architecture, validation, and API design.
