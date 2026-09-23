package org.aryan.smart_campus_platform.controller;

import jakarta.validation.Valid;
import org.aryan.smart_campus_platform.dto.request.JobSkillRequest;
import org.aryan.smart_campus_platform.dto.response.JobSkillResponse;
import org.aryan.smart_campus_platform.service.JobSkillService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/jobs/{jobId}/skills")
public class JobSkillController {

    private final JobSkillService service;

    public JobSkillController(JobSkillService service) {
        this.service = service;
    }

    @GetMapping
    public JobSkillResponse getSkills(@PathVariable int jobId) {
        return service.getSkills(jobId);
    }

    @PostMapping
    public JobSkillResponse assignSkills(
            @PathVariable int jobId,
            @RequestBody @Valid JobSkillRequest request) {

        return service.assignSkills(jobId, request);
    }

    @DeleteMapping("/{skillId}")
    public void removeSkill(
            @PathVariable int jobId,
            @PathVariable int skillId) {

        service.removeSkill(jobId, skillId);
    }
}
