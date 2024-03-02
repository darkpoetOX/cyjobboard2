package com.jobboard.cyjobboard2.controllers;

import com.jobboard.cyjobboard2.models.Employer;
import com.jobboard.cyjobboard2.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping // GET /employers
    public ResponseEntity<List<Employer>> getAllEmployers() {
        List<Employer> employers = employerService.getAllEmployers();
        return new ResponseEntity<>(employers, HttpStatus.OK);
    }

    @GetMapping("/{id}") // GET /employers/{id}
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        Optional<Employer> optionalEmployer = employerService.getEmployerById(id);

        if (optionalEmployer.isPresent()) {
            return new ResponseEntity<>(optionalEmployer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/{id}") // PATCH /employers/{id}
    public ResponseEntity<Employer> updateEmployerDetails(@PathVariable Long id, @RequestBody Employer updatedEmployer) {
        try {
            Employer updatedEmployerDetails = employerService.updateEmployerDetails(id, updatedEmployer);
            return new ResponseEntity<>(updatedEmployerDetails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login") // POST /employers/login
    public ResponseEntity<Employer> loginEmployer(@RequestParam String email, @RequestParam String password) {
        Employer employer = employerService.verifyEmployerLogin(email, password);
        if (employer != null) {
            return ResponseEntity.ok(employer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register") // POST /employers/register
    public ResponseEntity<Object> registerEmployer(@RequestBody Employer employer) { //using 'Object' instead of 'Employer' as we return a string if already exists
        if (employerService.employerExists(employer.getEmail())) {
            return ResponseEntity.badRequest().body("Employer already exists!");
        } else {
            Employer savedEmployer = employerService.saveEmployer(employer);
            return ResponseEntity.ok(savedEmployer);
        }
    }

    @DeleteMapping("/{id}") // DELETE /employers/{id}
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        try {
            employerService.deleteEmployer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}