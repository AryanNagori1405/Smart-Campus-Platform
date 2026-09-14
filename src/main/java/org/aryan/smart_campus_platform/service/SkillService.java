package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.SkillRequest;
import org.aryan.smart_campus_platform.dto.response.SkillResponse;
import org.aryan.smart_campus_platform.entity.Skill;
import org.aryan.smart_campus_platform.exception.SkillAlreadyExistsException;
import org.aryan.smart_campus_platform.mapper.SkillMapper;
import org.aryan.smart_campus_platform.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillService(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    public SkillResponse createSkill(SkillRequest skillRequest) {
        String name = skillRequest.getName().trim();
        String normalizedName = name.toLowerCase();

        Optional<Skill> existingSkill =
                skillRepository.findByNormalizedName(normalizedName);

        if (existingSkill.isPresent()) {
            throw new SkillAlreadyExistsException("Skill already exists!");
        }

        Skill skill = new Skill();
        skill.setName(name);
        skill.setNormalizedName(normalizedName);
        skillRepository.save(skill);

        return skillMapper.toResponse(skill);
    }
}
