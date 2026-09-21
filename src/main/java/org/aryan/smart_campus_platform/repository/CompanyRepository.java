package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {}
