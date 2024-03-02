package com.jobboard.cyjobboard2.services;


import com.jobboard.cyjobboard2.models.*;
import com.jobboard.cyjobboard2.repositories.JobRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job updateJobDetails(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()) {
            Job existingJob = optionalJob.get();
            BeanUtils.copyProperties(updatedJob, existingJob, "id", "candidates");
            return jobRepository.save(existingJob);
        } else {
            throw new IllegalArgumentException("Job not found with id: " + id);
        }
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> filterJobsBySector(String sector) {
        JobSector foundSector = JobSector.findByName(sector);
        return jobRepository.findBySector(foundSector);
    }

    public List<Job> filterJobsByCity(String city) {
        City foundCity = City.findByName(city);
        return jobRepository.findByCity(foundCity);
    }

    public List<Job> filterJobsByCountry(String country) {
        Country foundCountry = Country.findByName(country);
        return jobRepository.findByCountry(foundCountry);
    }

    public List<Job> filterJobsByType(String type) {
        JobType foundType = JobType.findByName(type);
        return jobRepository.findByType(foundType);
    }

    public List<Job> filterJobsByFormat(String format) {
        JobFormat foundFormat = JobFormat.findByName(format);
        return jobRepository.findByFormat(foundFormat);
    }

}