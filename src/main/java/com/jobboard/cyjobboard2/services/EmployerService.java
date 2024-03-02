package com.jobboard.cyjobboard2.services;

import com.jobboard.cyjobboard2.models.Employer;
import com.jobboard.cyjobboard2.repositories.EmployerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    public Optional<Employer> getEmployerById(Long id) {
        return employerRepository.findById(id);
    }

    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer updateEmployerDetails(Long id, Employer updatedEmployer) {
        Optional<Employer> optionalEmployer = employerRepository.findById(id);

        if (optionalEmployer.isPresent()) {
            Employer existingEmployer = optionalEmployer.get();
            BeanUtils.copyProperties(updatedEmployer, existingEmployer, "id", "jobPostings");
            return employerRepository.save(existingEmployer);
        } else {
            throw new IllegalArgumentException("Employer not found with id: " + id);
        }
    }



    public Employer verifyEmployerLogin(String email, String password) {
        return employerRepository.findByEmailAndPassword(email, password);
    }

    public boolean employerExists(String email) {
        return employerRepository.existsByEmail(email);
    }

    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
}