package org.aryan.smart_campus_platform.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class StudentSkillsRequest {

    @NotEmpty
    private List<Integer> skillIds;

    public StudentSkillsRequest() {}

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}