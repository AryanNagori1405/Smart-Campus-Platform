package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.request.JobSkillRequest;
import org.aryan.smart_campus_platform.dto.response.JobSkillResponse;
import org.aryan.smart_campus_platform.entity.Job;
import org.aryan.smart_campus_platform.entity.Skill;
import org.aryan.smart_campus_platform.exception.JobNotFoundException;
import org.aryan.smart_campus_platform.exception.SkillAlreadyAssignedException;
import org.aryan.smart_campus_platform.exception.SkillNotFoundException;
import org.aryan.smart_campus_platform.mapper.JobSkillMapper;
import org.aryan.smart_campus_platform.repository.JobRepository;
import org.aryan.smart_campus_platform.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSkillService {

    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final JobSkillMapper jobSkillMapper;

    public JobSkillService(
            JobRepository jobRepository,
            SkillRepository skillRepository,
            JobSkillMapper jobSkillMapper) {

        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.jobSkillMapper = jobSkillMapper;
    }

    public JobSkillResponse assignSkills(
            int jobId,
            JobSkillRequest request) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + jobId
                ));

        List<Integer> skillIds = request.getSkillIds();
        List<Skill> skills = skillRepository.findAllById(skillIds);

        if (skillIds.size() != skills.size()) {
            throw new SkillNotFoundException("One or more skills not found");
        }

        for (Skill skill : skills) {
            boolean alreadyAssigned = job.getRequiredSkills()
                    .stream()
                    .anyMatch(existingSkill ->
                            existingSkill.getId() == skill.getId()
                    );

            if (alreadyAssigned) {
                throw new SkillAlreadyAssignedException(
                        "Skill already assigned to this job: " + skill.getName()
                );
            }
        }

        job.getRequiredSkills().addAll(skills);

        return jobSkillMapper.toResponse(jobRepository.save(job));
    }

    public JobSkillResponse getSkills(int jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + jobId
                ));

        return jobSkillMapper.toResponse(job);
    }

    public void removeSkill(int jobId, int skillId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(
                        "Job not found with id: " + jobId
                ));

        Optional<Skill> skillToRemove = job.getRequiredSkills()
                .stream()
                .filter(skill -> skill.getId() == skillId)
                .findFirst();

        if (skillToRemove.isEmpty()) {
            throw new SkillNotFoundException(
                    "Skill with id " + skillId +
                    " is not assigned to job " + jobId
            );
        }

        job.getRequiredSkills().remove(skillToRemove.get());

        jobRepository.save(job);
    }
}
