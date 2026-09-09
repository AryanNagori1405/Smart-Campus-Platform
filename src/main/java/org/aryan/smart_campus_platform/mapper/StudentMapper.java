package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.StudentRequest;
import org.aryan.smart_campus_platform.dto.StudentResponse;
import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCollege(request.getCollege());
        student.setCourse(request.getCourse());
        student.setGraduationYear(request.getGraduationYear());

        return student;
    }

    public StudentResponse toResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setCollege(student.getCollege());
        studentResponse.setCourse(student.getCourse());
        studentResponse.setGraduationYear(student.getGraduationYear());

        return studentResponse;
    }
}
