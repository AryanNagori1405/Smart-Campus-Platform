package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.response.JobSkillResponse;
import org.aryan.smart_campus_platform.dto.response.SkillResponse;
import org.aryan.smart_campus_platform.entity.Job;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobSkillMapper {

    private final SkillMapper skillMapper;

    public JobSkillMapper(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    public JobSkillResponse toResponse(Job job) {
        JobSkillResponse response = new JobSkillResponse();

        response.setJobId(job.getId());

        List<SkillResponse> skills = job.getRequiredSkills()
                .stream()
                .map(skillMapper::toResponse)
                .toList();

        response.setSkills(skills);

        return response;
    }
}
