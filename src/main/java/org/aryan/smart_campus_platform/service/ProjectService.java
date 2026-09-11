package org.aryan.smart_campus_platform.service;

import org.aryan.smart_campus_platform.dto.ProjectRequest;
import org.aryan.smart_campus_platform.dto.ProjectResponse;
import org.aryan.smart_campus_platform.entity.Project;
import org.aryan.smart_campus_platform.entity.Student;
import org.aryan.smart_campus_platform.exception.StudentNotFoundException;
import org.aryan.smart_campus_platform.mapper.ProjectMapper;
import org.aryan.smart_campus_platform.repository.ProjectRepository;
import org.aryan.smart_campus_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(StudentRepository studentRepository,
                          ProjectRepository projectRepository,
                          ProjectMapper projectMapper) {
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectResponse> getProjectsByStudentId(int studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + studentId
                ));

        List<Project> projects = projectRepository.findByStudentId(studentId);

        return projects.stream()
                .map(projectMapper::toResponse)
                .toList();
    }

    public ProjectResponse createProject(ProjectRequest projectRequest,
                                         int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + studentId
                ));

        Project project = projectMapper.toEntity(projectRequest);
        project.setStudent(student);
        Project savedProject = projectRepository.save(project);

        return projectMapper.toResponse(savedProject);
    }
}
