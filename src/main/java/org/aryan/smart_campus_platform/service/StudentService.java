package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.StudentRequest;
import org.aryan.smart_campus_platform.dto.response.StudentResponse;
import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.exception.StudentNotFoundException;
import org.aryan.smart_campus_platform.mapper.StudentMapper;
import org.aryan.smart_campus_platform.repository.StudentRepository;

import org.aryan.smart_campus_platform.specification.StudentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
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

    public Page<StudentResponse> searchStudents(
            String college,
            Integer graduationYear,
            Pageable pageable) {

        Specification<Student> specification = null;

        if (college != null && !college.isBlank()) {
            specification = StudentSpecification.collegeContains(college);
        }

        if (graduationYear != null) {
            Specification<Student> graduationSpecification =
                    StudentSpecification.graduationYearGreaterThan(graduationYear);

            if (specification == null) {
                specification = graduationSpecification;
            } else {
                specification = specification.and(graduationSpecification);
            }
        }

        return studentRepository.findAll(specification, pageable)
                .map(studentMapper::toResponse);
    }

    public Page<StudentResponse> getStudentsGraduatingAfter(int year, Pageable pageable) {
        return studentRepository
                .findStudentsGraduatingAfter(year, pageable)
                .map(studentMapper::toResponse);
    }

    public Page<StudentResponse> getStudentByCollege(String college, Pageable pageable) {
        return studentRepository.findStudentsByCollege(college, pageable)
                .map(studentMapper::toResponse);
    }
}
