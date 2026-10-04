package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.StudentRequest;
import org.aryan.smart_campus_platform.dto.response.StudentResponse;
import org.aryan.smart_campus_platform.entity.Account;
import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.exception.StudentNotFoundException;
import org.aryan.smart_campus_platform.mapper.StudentMapper;
import org.aryan.smart_campus_platform.repository.AccountRepository;
import org.aryan.smart_campus_platform.repository.StudentRepository;
import org.aryan.smart_campus_platform.specification.StudentSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AccountRepository accountRepository;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper, AccountRepository accountRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.accountRepository = accountRepository;
    }

    public StudentResponse getStudentById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
        return studentMapper.toResponse(student);
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException(
                    "User is not authenticated"
            );
        }

        String email = authentication.getName();

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() ->
                        new AccessDeniedException(
                                "Account not found"
                        ));

        Student student = studentMapper.toEntity(studentRequest);

        student.setAccount(account);

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    public StudentResponse updateStudent(int id, StudentRequest studentRequest) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException(
                    "User is not authenticated"
            );
        }

        String email = authentication.getName();

        Student loggedInStudent =
                studentRepository.findByAccountEmail(email)
                                .orElseThrow(() -> new StudentNotFoundException((
                                            "Student profile not found for the authenticated user"
                                        )));

        if (loggedInStudent.getId() != id) {
            throw new AccessDeniedException(
                    "You are not allowed to update this student"
            );
        }

        existingStudent.setName(studentRequest.getName());
        existingStudent.setCollege(studentRequest.getCollege());
        existingStudent.setCourse(studentRequest.getCourse());
        existingStudent.setEmail(studentRequest.getEmail());
        existingStudent.setGraduationYear(studentRequest.getGraduationYear());

        Student savedStudent = studentRepository.save(existingStudent);

        return studentMapper.toResponse(savedStudent);
    }

    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        studentRepository.delete(student);
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
