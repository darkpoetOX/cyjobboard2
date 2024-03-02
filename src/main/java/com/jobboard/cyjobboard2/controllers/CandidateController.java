package com.jobboard.cyjobboard2.controllers;

import com.jobboard.cyjobboard2.models.Candidate;
import com.jobboard.cyjobboard2.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping // GET /candidates
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/{id}") // GET /candidates/{id}
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Optional<Candidate> optionalCandidate = candidateService.getCandidateById(id);

        if (optionalCandidate.isPresent()) {
            return new ResponseEntity<>(optionalCandidate.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Candidate> loginCandidate(@RequestParam String email, @RequestParam String password) {
        Candidate verifiedCandidate = candidateService.verifyCandidateLogin(email, password);

        if (verifiedCandidate != null) {
            return ResponseEntity.ok(verifiedCandidate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<Object> registerCandidate(@RequestBody Candidate candidate) {
        if (candidateService.candidateExists(candidate.getEmail())) {
            return ResponseEntity.badRequest().body("User already exists!");
        } else {
            Candidate savedCandidate = candidateService.saveCandidate(candidate);
            return ResponseEntity.ok(savedCandidate);
        }
    }


    @PatchMapping("/{id}") // PATCH /candidates/{id}
    public ResponseEntity<Candidate> updateCandidateDetails(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {
        try {
            Candidate updated = candidateService.updateCandidateDetails(id, updatedCandidate);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // DELETE /candidates/{id}
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        try {
            candidateService.deleteCandidate(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
