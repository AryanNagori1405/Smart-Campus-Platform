package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository
        extends JpaRepository<Student, Integer>,
                JpaSpecificationExecutor<Student> {
}
