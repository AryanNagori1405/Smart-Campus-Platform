package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Page<Student> findByCollege(
            String college,
            Pageable pageable
    );

    Page<Student> findByCollegeContaining(
            String college,
            Pageable pageable
    );

    Page<Student> findByGraduationYearGreaterThan(
            int graduationYear,
            Pageable pageable
    );
}
