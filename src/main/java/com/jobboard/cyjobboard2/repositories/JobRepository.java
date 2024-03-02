package com.jobboard.cyjobboard2.repositories;

import com.jobboard.cyjobboard2.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findBySector(JobSector sector);

    List<Job> findByCity(City city);

    List<Job> findByCountry(Country country);

    List<Job> findByType(JobType type);

    List<Job> findByFormat(JobFormat format);

}