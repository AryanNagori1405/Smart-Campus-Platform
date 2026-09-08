package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElse(new Student());
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElse(new Student());

        existingStudent.setName(student.getName());
        existingStudent.setCollege(student.getCollege());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setGraduationYear(student.getGraduationYear());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

}
