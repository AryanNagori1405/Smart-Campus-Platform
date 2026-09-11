package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.StudentRequest;
import org.aryan.smart_campus_platform.dto.StudentResponse;
import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.exception.StudentNotFoundException;
import org.aryan.smart_campus_platform.mapper.StudentMapper;
import org.aryan.smart_campus_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    public StudentResponse getStudentById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
        return studentMapper.toResponse(student);
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
    }

    public StudentResponse updateStudent(int id, StudentRequest studentRequest) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        Student student = studentMapper.toEntity(studentRequest);

        existingStudent.setName(student.getName());
        existingStudent.setCollege(student.getCollege());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setGraduationYear(student.getGraduationYear());

        Student savedStudent = studentRepository.save(existingStudent);

        return studentMapper.toResponse(savedStudent);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
