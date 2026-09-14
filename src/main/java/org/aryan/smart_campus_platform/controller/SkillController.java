package org.aryan.smart_campus_platform.controller;

import jakarta.validation.Valid;
import org.aryan.smart_campus_platform.dto.request.SkillRequest;
import org.aryan.smart_campus_platform.dto.response.SkillResponse;
import org.aryan.smart_campus_platform.service.SkillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public SkillResponse createSkill(
            @RequestBody @Valid SkillRequest skillRequest) {
        return skillService.createSkill(skillRequest);
    }


}
