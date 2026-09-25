package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    boolean existsByStudentIdAndJobId(int studentId, int jobId);
}
