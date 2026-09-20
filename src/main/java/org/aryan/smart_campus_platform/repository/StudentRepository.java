package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository
        extends JpaRepository<Student, Integer>,
                JpaSpecificationExecutor<Student> {

    @Query("""
       SELECT s
       FROM Student s
       WHERE s.graduationYear > :year
       ORDER BY s.name ASC
       """)
    Page<Student> findStudentsGraduatingAfter(
            @Param("year") int year,
            Pageable pageable
    );

    @Query("""
        SELECT s
        FROM Student s
        WHERE s.college LIKE CONCAT('%', :college, '%')
    """)
    Page<Student> findStudentsByCollege(
            @Param("college") String college,
            Pageable pageable
    );
}
