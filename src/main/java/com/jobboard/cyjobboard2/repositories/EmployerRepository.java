package com.jobboard.cyjobboard2.repositories;

import com.jobboard.cyjobboard2.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByEmail(String email);

    Employer findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}