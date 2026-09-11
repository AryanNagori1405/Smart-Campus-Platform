package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByStudentId(int studentId);
}
