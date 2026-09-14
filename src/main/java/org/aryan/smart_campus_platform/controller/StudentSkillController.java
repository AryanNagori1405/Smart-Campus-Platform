package org.aryan.smart_campus_platform.controller;

import jakarta.validation.Valid;
import org.aryan.smart_campus_platform.dto.request.StudentSkillsRequest;
import org.aryan.smart_campus_platform.dto.response.StudentSkillResponse;
import org.aryan.smart_campus_platform.service.StudentSkillService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentSkillController {

    private final StudentSkillService studentSkillService;

    public StudentSkillController(StudentSkillService studentSkillService) {
        this.studentSkillService = studentSkillService;
    }

    @PostMapping("/{studentId}/skills")
    public StudentSkillResponse assignSkills(
            @PathVariable int studentId,
            @RequestBody @Valid StudentSkillsRequest studentSkillsRequest) {

        return studentSkillService.assignSkills(
                studentId,
                studentSkillsRequest
        );
    }

    @GetMapping("/{studentId}/skills")
    public StudentSkillResponse getSkills(@PathVariable int studentId) {
        return studentSkillService.getSkills(studentId);
    }

    @DeleteMapping("/{studentId}/skills/{skillId}")
    public void removeSkill(@PathVariable int studentId,
                            @PathVariable int skillId) {
        studentSkillService.removeSkill(studentId, skillId);
    }
}
