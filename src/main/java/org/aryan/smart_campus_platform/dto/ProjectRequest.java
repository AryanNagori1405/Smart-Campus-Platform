package org.aryan.smart_campus_platform.dto;

import jakarta.validation.constraints.NotBlank;

public class ProjectRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    public ProjectRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
