# Student API - Lab 6, Lab 7a, Lab 7b & Lab 9

## 📌 Overview
This project is a backend application built using **Spring Boot** that demonstrates:

- Lab 6: Data Persistence using Spring Data JPA  
- Lab 7a: RESTful Web API implementation  
- Lab 7b: GraphQL API implementation  
- Lab 9: Token-based Authentication & Role-based Authorization using Spring Security and JWT  

The system manages student records and supports REST, GraphQL, and secure API access.

---

## 🛠️ Technologies Used
- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Spring GraphQL  
- Spring Security  
- JWT  
- PostgreSQL  
- Hibernate  
- Swagger UI  
- GraphiQL  
- Maven  

---

## 📂 Project Structure

com.apsd.studentAPI
├── controller
│   ├── StudentController (REST)
│   └── StudentGraphQLController (GraphQL)
├── service
│   └── StudentService
├── repository
│   └── StudentRepository
├── entity
│   └── Student
├── exception
│   ├── ResourceNotFoundException
│   └── GlobalExceptionHandler
├── security
│   ├── auth
│   │   ├── AuthController
│   │   ├── AuthService
│   │   ├── LoginRequest
│   │   ├── RegisterRequest
│   │   └── AuthResponse
│   ├── config
│   │   ├── SecurityConfig
│   │   └── OpenApiConfig
│   ├── jwt
│   │   ├── JwtService
│   │   └── JwtAuthenticationFilter
│   └── user
│       ├── AppUser
│       ├── Role
│       ├── AppUserRepository
│       └── CustomUserDetailsService
└── StudentApiApplication

---

## 🗄️ Database Setup

CREATE DATABASE studentdb;

Start PostgreSQL:
brew services start postgresql

---

## 🚀 Run Application

mvn spring-boot:run

---

# 🌐 REST API (Lab 7a)

Swagger UI:
http://localhost:8080/swagger-ui.html

---

# 🔷 GraphQL API (Lab 7b)

GraphiQL:
http://localhost:8080/graphiql

---

# 🔐 Security (Lab 9)

## Authentication Flow

1. Register user  
2. Login → receive JWT token  
3. Click Authorize in Swagger  
4. Use token to access secured endpoints  

---

## Authentication Endpoints

### Register
POST /auth/register

{
  "name": "Admin User",
  "email": "admin@test.com",
  "password": "admin123",
  "role": "ADMIN"
}

---

### Login
POST /auth/login

{
  "email": "admin@test.com",
  "password": "admin123"
}

Response:
{
  "token": "JWT_TOKEN"
}

---

## Using JWT in Swagger

Click Authorize and enter:

Bearer YOUR_TOKEN

---

## Role-based Authorization

| Endpoint | Access |
|--------|--------|
| GET /api/students | USER, ADMIN |
| GET /api/students/{id} | USER, ADMIN |
| POST /api/students | ADMIN only |
| PUT /api/students/{id} | ADMIN only |
| PATCH /api/students/{id} | ADMIN only |
| DELETE /api/students/{id} | ADMIN only |

---

## 🧪 GraphQL Operations

### Get All Students
query {
  students {
    id
    name
    email
    course
    major
    studentID
  }
}

### Create Student
mutation {
  createStudent(
    name: "Moe"
    email: "hphyu@miu.edu"
    course: "ASD"
    major: "CS"
    studentID: "618073"
  ) {
    id
    name
  }
}

---

## 📸 Screenshots

REST (Lab 7a): screen-shorts/REST/  
GraphQL (Lab 7b): screen-shorts/GraphQL/  
Security (Lab 9): screen-shorts/Security/  

---

## 🎯 Conclusion

This project demonstrates REST, GraphQL, and secure APIs using JWT and role-based authorization.
