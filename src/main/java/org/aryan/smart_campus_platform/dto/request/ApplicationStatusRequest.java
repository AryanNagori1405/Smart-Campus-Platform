package org.aryan.smart_campus_platform.dto.request;

import jakarta.validation.constraints.NotNull;
import org.aryan.smart_campus_platform.entity.ApplicationStatus;

public class ApplicationStatusRequest {

    @NotNull
    private ApplicationStatus status;

    public ApplicationStatusRequest() {}

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
