package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByCompanyId(int companyId);
}
