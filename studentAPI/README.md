# Student API - Lab 6, Lab 7a & Lab 7b

## 📌 Overview
This project is a backend application built using **Spring Boot** that demonstrates:

- Lab 6: Data Persistence using Spring Data JPA  
- Lab 7a: RESTful Web API implementation  
- Lab 7b: GraphQL API implementation  

The system manages student records and supports both REST and GraphQL APIs.

---

## 🛠️ Technologies Used
- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Spring GraphQL  
- PostgreSQL  
- Hibernate  
- Swagger UI  
- GraphiQL  
- Maven  

---

## 📂 Project Structure

com.apsd.studentAPI
├── controller (REST + GraphQL)
├── service
├── repository
├── entity
├── exception
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

Swagger:
http://localhost:8080/swagger-ui.html

---

# 🔷 GraphQL API (Lab 7b)

GraphiQL:
http://localhost:8080/graphiql

---

## 🧪 GraphQL Operations

### Get All
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

### Create
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

### Update
mutation {
  updateStudent(
    id: 1
    name: "Moe Updated"
    email: "hphyu@miu.edu"
    course: "ASD"
    major: "SE"
    studentID: "618073"
  ) {
    id
    name
  }
}

### Delete
mutation {
  deleteStudent(id: 1)
}

---

## 🖼️ Screenshots

### REST
Located in: screen-shorts/REST/

### GraphQL
Located in: screen-shorts/GraphQL/

---

## 🎯 Conclusion

This project demonstrates both REST and GraphQL APIs fulfilling Lab 6, Lab 7a, and Lab 7b.
