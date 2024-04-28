package com.jobboard.cyjobboard2.components;

import com.jobboard.cyjobboard2.models.*;
import com.jobboard.cyjobboard2.repositories.CandidateRepository;
import com.jobboard.cyjobboard2.repositories.EmployerRepository;
import com.jobboard.cyjobboard2.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    JobRepository jobRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        Candidate candidate1 = new Candidate("John", "Clitheroe", "johnclitheroe@yahoo.com", "firefly", "123 Main St");
        Candidate candidate2 = new Candidate("Anne", "Boyle", "anneboyle@gmail.com", "password154", "456 Oak St");
        Candidate candidate3 = new Candidate("Mary", "Dash", "marydash@gmail.com", "pass1994red", "35 Tree St");



        Employer employer1 = new Employer("ABC Ltd", "abchr@abcgroup.com", "abcnhhhs2", "789 Business Ave");
        Employer employer2 = new Employer("LightHouse Corporation", "hrrecruit@lighthouseholdings.com", "charttyu1", "321 Enterprise St");

        employerRepository.saveAll(List.of(employer1, employer2));


        Job job1 = new Job("Software Developer", JobType.FULL_TIME, JobFormat.REMOTE, "Java Developer needed for client website development. Assessment: meet at the Farrindon building at 11:00 EST for registration, assessments starts at 11:30 EST.", employer1, JobSector.IT, City.NEW_YORK, Country.USA);
        Job job2 = new Job("Biochem Scientist", JobType.PART_TIME, JobFormat.IN_PERSON, "Bio-scientist with background in DNA sequencing. Assessment: arrive at reception 15 mins before assessment, which will begin at 9:15 EDT.", employer2, JobSector.HEALTHCARE, City.TORONTO, Country.CANADA);

        jobRepository.saveAll(List.of(job1, job2));

        // Associate candidates with jobs using the new methods
        candidate1.addJob(job1);
        candidate1.addJob(job2);
        candidate2.addJob(job2);

        // Save the changes
        candidateRepository.saveAll(List.of(candidate1, candidate2, candidate3));
    }


}
