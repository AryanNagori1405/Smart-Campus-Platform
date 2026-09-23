package org.aryan.smart_campus_platform.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"jobId", "skills"})
public class JobSkillResponse {

    private int jobId;
    private List<SkillResponse> skills;

    public JobSkillResponse() {}

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public List<SkillResponse> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponse> skills) {
        this.skills = skills;
    }
}
