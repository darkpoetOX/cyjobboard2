package com.jobboard.cyjobboard2.controllers;

import com.jobboard.cyjobboard2.models.Job;
import com.jobboard.cyjobboard2.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping // GET /jobs
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}") // GET /jobs/{id}
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> optionalJob = jobService.getJobById(id);

        if (optionalJob.isPresent()) {
            return new ResponseEntity<>(optionalJob.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter/by-sector")
    public ResponseEntity<List<Job>> filterJobsBySector(@RequestParam("sector") String sector) {
        List<Job> filteredJobs = jobService.filterJobsBySector(sector);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @GetMapping("/filter/by-city")
    public ResponseEntity<List<Job>> filterJobsByCity(@RequestParam("city") String city) {
        List<Job> filteredJobs = jobService.filterJobsByCity(city);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @GetMapping("/filter/by-country")
    public ResponseEntity<List<Job>> filterJobsByCountry(@RequestParam("country") String country) {
        List<Job> filteredJobs = jobService.filterJobsByCountry(country);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @GetMapping("/filter/by-type")
    public ResponseEntity<List<Job>> filterJobsByType(@RequestParam("type") String type) {
        List<Job> filteredJobs = jobService.filterJobsByType(type);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @GetMapping("/filter/by-format")
    public ResponseEntity<List<Job>> filterJobsByFormat(@RequestParam("format") String format) {
        List<Job> filteredJobs = jobService.filterJobsByFormat(format);
        return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
    }

    @PostMapping // POST /jobs
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    //for editing just parts not whole
    @PatchMapping("/{id}") // PATCH /jobs/{id}
    public ResponseEntity<Job> updateJobDetails(@PathVariable Long id, @RequestBody Job updatedJob) {
        try {
            Job updatedJobDetails = jobService.updateJobDetails(id, updatedJob);
            return new ResponseEntity<>(updatedJobDetails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // DELETE /jobs/{id}
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}