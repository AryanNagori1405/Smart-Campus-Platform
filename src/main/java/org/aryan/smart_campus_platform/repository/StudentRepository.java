package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {}
