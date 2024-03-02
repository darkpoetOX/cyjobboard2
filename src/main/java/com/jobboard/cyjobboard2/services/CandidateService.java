package com.jobboard.cyjobboard2.services;

import com.jobboard.cyjobboard2.models.Candidate;
import com.jobboard.cyjobboard2.repositories.CandidateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public Candidate updateCandidateDetails(Long id, Candidate updatedCandidate) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        if (optionalCandidate.isPresent()) {
            Candidate existingCandidate = optionalCandidate.get();

            // Update only  properties needing updating
            //selectively copies properties from updatedCandidate to existingCandidate while excluding certain properties.
            BeanUtils.copyProperties(updatedCandidate, existingCandidate, "id", "appliedJobs");

            // Save the updated candidate
            return candidateRepository.save(existingCandidate);
        } else {
            throw new IllegalArgumentException("Candidate not found with id: " + id);
        }
    }

    public Candidate verifyCandidateLogin(String email, String password) {
        return candidateRepository.findByEmailAndPassword(email, password);
    }

    public boolean candidateExists(String email) {
        return candidateRepository.existsByEmail(email);
    }

}