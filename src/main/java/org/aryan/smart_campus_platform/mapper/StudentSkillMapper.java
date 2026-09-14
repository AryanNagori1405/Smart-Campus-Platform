package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.response.SkillResponse;
import org.aryan.smart_campus_platform.dto.response.StudentSkillResponse;
import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentSkillMapper {

    private final SkillMapper skillMapper;

    public StudentSkillMapper(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    public StudentSkillResponse toResponse(Student student) {
        StudentSkillResponse response = new StudentSkillResponse();

        response.setStudentId(student.getId());

        List<SkillResponse> skills = student.getSkills()
                .stream()
                .map(skillMapper::toResponse)
                .toList();

        response.setSkills(skills);

        return response;
    }
}
