package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.request.StudentRequest;
import org.aryan.smart_campus_platform.dto.response.StudentResponse;
import org.aryan.smart_campus_platform.service.StudentService;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Page<StudentResponse> getAllStudents(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam(required = false) String college,
            @RequestParam(required = false) Integer graduationYear) {

        return studentService.searchStudents(
                college,
                graduationYear,
                pageable
        );
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable int id,
                                 @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/graduating-after/{year}")
    public Page<StudentResponse> getStudentsGraduatingAfter(@PathVariable int year, Pageable pageable) {
        return studentService.getStudentsGraduatingAfter(year, pageable);
    }

    @GetMapping("/college/{college}")
    public Page<StudentResponse> getStudentByCollege(
            @PathVariable String college,
            Pageable pageable) {
        return studentService.getStudentByCollege(college, pageable);
    }
}
