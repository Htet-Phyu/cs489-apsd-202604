package com.apsd.studentAPI.controller;

import com.apsd.studentAPI.entity.Student;
import com.apsd.studentAPI.service.StudentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentGraphQLController {
    private final StudentService studentService;

    public StudentGraphQLController(StudentService studentService) {
        this.studentService = studentService;
    }

    //all students
    @QueryMapping
    public List<Student> students() {
        return studentService.getAllStudents();
    }

    //get by id
    @QueryMapping
    public Student studentById(@Argument Long id) {
        return studentService.getStudentById(id);
    }

    //create student
    @MutationMapping
    public Student createStudent(
            @Argument String name,
            @Argument String email,
            @Argument String course,
            @Argument String major,
            @Argument String studentID
    ) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        student.setMajor(major);
        student.setStudentID(studentID);

        return studentService.createStudent(student);
    }

    //update student
    @MutationMapping
    public Student updateStudent(
            @Argument Long id,
            @Argument String name,
            @Argument String email,
            @Argument String course,
            @Argument String major,
            @Argument String studentID
    ) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        student.setMajor(major);
        student.setStudentID(studentID);

        return studentService.updateStudent(id, student);
    }

    //delete student
    @MutationMapping
    public String deleteStudent(@Argument Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
}
