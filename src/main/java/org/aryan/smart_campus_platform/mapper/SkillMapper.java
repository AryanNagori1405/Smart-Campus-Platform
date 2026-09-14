package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.response.SkillResponse;
import org.aryan.smart_campus_platform.entity.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public SkillResponse toResponse(Skill skill) {
        SkillResponse skillResponse = new SkillResponse();

        skillResponse.setId(skill.getId());
        skillResponse.setName(skill.getName());

        return skillResponse;
    }
}
