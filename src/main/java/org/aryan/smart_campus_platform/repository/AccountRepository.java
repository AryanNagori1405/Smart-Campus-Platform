package org.aryan.smart_campus_platform.repository;

import org.aryan.smart_campus_platform.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);
}
