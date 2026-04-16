package com.apsd.studentAPI.controller;

import com.apsd.studentAPI.entity.Student;
import com.apsd.studentAPI.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
/*
    Lab7a: RESTFUL WEB API
 */
//@RestController
//@RequestMapping("/api/students")
//public class StudentController {
//    private List<Student> students = new ArrayList<>();
//    private Long nextId = 1L;
//
//    // GET all students
//    @GetMapping
//    public List<Student> getAllStudents() {
//        return students;
//    }
//
//    // GET student by id
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable Long id) {
//        return students.stream()
//                .filter(s -> s.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//    }
//
//    // POST create student
//    @PostMapping
//    public Student createStudent(@RequestBody Student student) {
//        student.setId(nextId++);
//        students.add(student);
//        return student;
//    }
//
//    // PUT update full student
//    @PutMapping("/{id}")
//    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
//        for (Student s : students) {
//            if (s.getId().equals(id)) {
//                s.setName(updatedStudent.getName());
//                s.setEmail(updatedStudent.getEmail());
//                s.setCourse(updatedStudent.getCourse());
//                s.setMajor(updatedStudent.getMajor());
//                s.setStudentID(updatedStudent.getStudentID());
//                return s;
//            }
//        }
//        throw new RuntimeException("Student not found");
//    }
//
//    // PATCH update partial student
//    @PatchMapping("/{id}")
//    public Student patchStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
//        Student student = students.stream()
//                .filter(s -> s.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        updates.forEach((key, value) -> {
//            switch (key) {
//                case "name" -> student.setName((String) value);
//                case "email" -> student.setEmail((String) value);
//                case "course" -> student.setCourse((String) value);
//                case "major" -> student.setMajor((String) value);
//                case "studentID" -> student.setStudentID((String) value);
//            }
//        });
//
//        return student;
//    }
//
//    // DELETE student
//    @DeleteMapping("/{id}")
//    public String deleteStudent(@PathVariable Long id) {
//        boolean removed = students.removeIf(s -> s.getId().equals(id));
//        if (!removed) {
//            throw new RuntimeException("Student not found");
//        }
//        return "Student deleted successfully";
//    }
//}
//---------------------------------------------------
/*
    Lab7b: GraphQL WEB API
 */
//@RestController
//@RequestMapping("/api/students")
//public class StudentController {
//
//    private final StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @Operation(summary = "Get all students")
//    @GetMapping
//    public ResponseEntity<List<Student>> getAllStudents() {
//        return ResponseEntity.ok(studentService.getAllStudents());
//    }
//
//    @Operation(summary = "Get student by id")
//    @GetMapping("/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//        return ResponseEntity.ok(studentService.getStudentById(id));
//    }
//
//    @Operation(summary = "Create a new student")
//    @PostMapping
//    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
//        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Update full student")
//    @PutMapping("/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
//                                                 @Valid @RequestBody Student student) {
//        return ResponseEntity.ok(studentService.updateStudent(id, student));
//    }
//
//    @Operation(summary = "Partially update student")
//    @PatchMapping("/{id}")
//    public ResponseEntity<Student> patchStudent(@PathVariable Long id,
//                                                @RequestBody Map<String, Object> updates) {
//        return ResponseEntity.ok(studentService.patchStudent(id, updates));
//    }
//
//    @Operation(summary = "Delete student")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
//        studentService.deleteStudent(id);
//        return ResponseEntity.noContent().build();
//    }
//}
//-----------------------------------------------------------
/*
    Lab9: Application Security
 */
@RestController
@RequestMapping("/api/students")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get all students")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Get student by id")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Create a new student")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @Operation(summary = "Update full student")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student
    ) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @Operation(summary = "Partially update student")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        return ResponseEntity.ok(studentService.patchStudent(id, updates));
    }

    @Operation(summary = "Delete student")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}


