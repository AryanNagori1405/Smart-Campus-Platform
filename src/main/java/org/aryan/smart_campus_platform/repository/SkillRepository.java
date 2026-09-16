package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    Optional<Skill> findByNormalizedName(String normalizedName);
}
