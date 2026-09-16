package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.StudentSkillsRequest;
import org.aryan.smart_campus_platform.dto.response.StudentSkillResponse;
import org.aryan.smart_campus_platform.entity.Skill;
import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.exception.SkillAlreadyAssignedException;
import org.aryan.smart_campus_platform.exception.SkillNotFoundException;
import org.aryan.smart_campus_platform.exception.StudentNotFoundException;
import org.aryan.smart_campus_platform.mapper.StudentSkillMapper;
import org.aryan.smart_campus_platform.repository.SkillRepository;
import org.aryan.smart_campus_platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSkillService {

    private final StudentRepository studentRepository;
    private final SkillRepository skillRepository;
    private final StudentSkillMapper studentSkillMapper;

    public StudentSkillService(
            StudentRepository studentRepository,
            SkillRepository skillRepository,
            StudentSkillMapper studentSkillMapper) {
        this.studentRepository = studentRepository;
        this.skillRepository = skillRepository;
        this.studentSkillMapper = studentSkillMapper;
    }

    public StudentSkillResponse assignSkills(int studentId, StudentSkillsRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + studentId
                ));

        List<Integer> skillIds = request.getSkillIds();
        List<Skill> skills = skillRepository.findAllById(skillIds);

        if (skillIds.size() != skills.size()) {
            throw new SkillNotFoundException("One or more skills not found");
        }

        for (Skill skill : skills) {
            boolean alreadyAssigned = student.getSkills()
                    .stream()
                    .anyMatch(existingSkill ->
                            existingSkill.getId() == skill.getId()
                    );

            if (alreadyAssigned) {
                throw new SkillAlreadyAssignedException(
                        "Skill already assigned to this student: " + skill.getName()
                );
            }
        }

        student.getSkills().addAll(skills);

        return studentSkillMapper.toResponse(studentRepository.save(student));
    }

    public StudentSkillResponse getSkills(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + studentId
                ));

        return studentSkillMapper.toResponse(student);
    }

    public void removeSkill(int studentId, int skillId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + studentId
                ));

        Optional<Skill> skillToRemove = student.getSkills()
                .stream()
                .filter(skill -> skill.getId() == skillId)
                .findFirst();

        if (skillToRemove.isEmpty()) {
            throw new SkillNotFoundException(
                    "Skill with id " + skillId +
                    " is not assigned to student " + studentId
            );
        }

        student.getSkills().remove(skillToRemove.get());

        studentRepository.save(student);
    }
}
