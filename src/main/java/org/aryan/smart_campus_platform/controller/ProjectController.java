package org.aryan.smart_campus_platform.controller;

import org.aryan.smart_campus_platform.dto.ProjectRequest;
import org.aryan.smart_campus_platform.dto.ProjectResponse;
import org.aryan.smart_campus_platform.service.ProjectService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
