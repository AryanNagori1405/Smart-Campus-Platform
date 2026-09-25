package org.aryan.smart_campus_platform.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.aryan.smart_campus_platform.entity.ApplicationStatus;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "studentId",
        "jobId",
        "status",
        "appliedAt"
})
public class ApplicationResponse {

    private int id;

    private int studentId;
    private int jobId;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;

    public ApplicationResponse() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
