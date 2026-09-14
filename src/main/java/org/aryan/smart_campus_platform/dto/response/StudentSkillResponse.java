package org.aryan.smart_campus_platform.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"studentId", "skills"})
public class StudentSkillResponse {

    private int studentId;
    private List<SkillResponse> skills;

    public StudentSkillResponse() {}

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<SkillResponse> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponse> skills) {
        this.skills = skills;
    }
}
