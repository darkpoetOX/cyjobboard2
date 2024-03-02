package com.jobboard.cyjobboard2.repositories;

import com.jobboard.cyjobboard2.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByEmail(String email);

    Candidate findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}