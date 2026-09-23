package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.request.StudentSkillsRequest;
import org.aryan.smart_campus_platform.dto.response.StudentSkillResponse;
import org.aryan.smart_campus_platform.service.StudentSkillService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students/{studentId}/skills")
public class StudentSkillController {

    private final StudentSkillService studentSkillService;

    public StudentSkillController(StudentSkillService studentSkillService) {
        this.studentSkillService = studentSkillService;
    }

    @PostMapping
    public StudentSkillResponse assignSkills(
            @PathVariable int studentId,
            @RequestBody @Valid StudentSkillsRequest studentSkillsRequest) {

        return studentSkillService.assignSkills(
                studentId,
                studentSkillsRequest
        );
    }

    @GetMapping
    public StudentSkillResponse getSkills(@PathVariable int studentId) {
        return studentSkillService.getSkills(studentId);
    }

    @DeleteMapping("/{skillId}")
    public void removeSkill(@PathVariable int studentId,
                            @PathVariable int skillId) {
        studentSkillService.removeSkill(studentId, skillId);
    }
}
