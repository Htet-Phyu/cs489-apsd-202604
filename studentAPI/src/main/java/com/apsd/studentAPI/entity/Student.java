package com.apsd.studentAPI.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {
    /*
        @Schema → hides id in Swagger UI
        @JsonProperty → prevents client from sending id in requests
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Course is required")
    @Column(nullable = false)
    private String course;

    @NotBlank(message = "Major is required")
    @Column(nullable = false)
    private String major;

    @NotBlank(message = "StudentID is required")
    @Column(nullable = false, unique = true)
    private String studentID;

    public Student() {}

    public Student(Long id, String name, String email, String course, String major, String studentID) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.major = major;
        this.studentID = studentID;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
