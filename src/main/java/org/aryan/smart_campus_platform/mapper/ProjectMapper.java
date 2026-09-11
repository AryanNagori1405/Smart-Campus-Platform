package org.aryan.smart_campus_platform.mapper;

import org.aryan.smart_campus_platform.dto.ProjectRequest;
import org.aryan.smart_campus_platform.dto.ProjectResponse;
import org.aryan.smart_campus_platform.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest projectRequest) {
        Project project = new Project();

        project.setTitle(projectRequest.getTitle());
        project.setDescription(projectRequest.getDescription());

        return project;
    }

    public ProjectResponse toResponse(Project project) {
        ProjectResponse projectResponse = new ProjectResponse();

        projectResponse.setId(project.getId());
        projectResponse.setTitle(project.getTitle());
        projectResponse.setDescription(project.getDescription());
        projectResponse.setStudentId(project.getStudent().getId());

        return projectResponse;
    }
}