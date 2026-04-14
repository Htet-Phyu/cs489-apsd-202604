package com.apsd.studentAPI.service;

import com.apsd.studentAPI.entity.Student;
import com.apsd.studentAPI.exception.ResourceNotFoundException;
import com.apsd.studentAPI.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setCourse(updatedStudent.getCourse());
        existing.setMajor(updatedStudent.getMajor());
        existing.setStudentID(updatedStudent.getStudentID());

        return studentRepository.save(existing);
    }

    public Student patchStudent(Long id, Map<String, Object> updates) {
        Student existing = getStudentById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> existing.setName((String) value);
                case "email" -> existing.setEmail((String) value);
                case "course" -> existing.setCourse((String) value);
                case "major" -> existing.setMajor((String) value);
                case "studentID" -> existing.setStudentID((String) value);
            }
        });

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }
}
