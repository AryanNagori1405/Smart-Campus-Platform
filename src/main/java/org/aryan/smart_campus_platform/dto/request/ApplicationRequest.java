package org.aryan.smart_campus_platform.dto.request;

import jakarta.validation.constraints.Positive;

public class ApplicationRequest {

    @Positive
    private int studentId;

    @Positive
    private int jobId;

    public ApplicationRequest() {}

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
}
