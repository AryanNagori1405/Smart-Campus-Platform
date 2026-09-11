package org.aryan.smart_campus_platform.controller;

import jakarta.validation.Valid;
import org.aryan.smart_campus_platform.dto.ProjectRequest;
import org.aryan.smart_campus_platform.dto.ProjectResponse;
import org.aryan.smart_campus_platform.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{studentId}/projects")
    public List<ProjectResponse> getProjectsByStudentId(@PathVariable int studentId) {
        return projectService.getProjectsByStudentId(studentId);
    }

    @PostMapping("/{studentId}/projects")
    public ProjectResponse createProject(
            @PathVariable int studentId,
            @RequestBody @Valid ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest, studentId);
    }
}
