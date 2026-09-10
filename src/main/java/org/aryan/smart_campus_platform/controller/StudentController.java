package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.StudentRequest;
import org.aryan.smart_campus_platform.dto.StudentResponse;
import org.aryan.smart_campus_platform.service.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public StudentResponse createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@PathVariable int id,
                                 @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}
