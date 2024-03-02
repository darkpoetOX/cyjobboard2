package com.jobboard.cyjobboard2.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String title;
    @Enumerated(EnumType.STRING)
    private JobType type; // Full-time, Part-time
    @Enumerated(EnumType.STRING)
    private JobFormat format; // In-person, Remote
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private JobSector sector;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private Country country;



    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.EAGER)
    private Set<Candidate> interestedCandidates  = new HashSet<>();


    // Constructors, getters, and setters

    public Job() {
    }

    public Job(String title, JobType type, JobFormat format, String description, Employer employer, JobSector sector, City city, Country country) {
        this.title = title;
        this.type = type;
        this.format = format;
        this.description = description;
        this.employer = employer;
        this.sector = sector;
        this.city = city;
        this.country = country;
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public JobFormat getFormat() {
        return format;
    }

    public void setFormat(JobFormat format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Set<Candidate> getCandidates() {
        return interestedCandidates;
    }

    public void setCandidates(Set<Candidate> candidates) {
        this.interestedCandidates = candidates;
    }

    // Add a convenience method to add interested candidates
    // Add a convenience method to add interested candidates
    public void addInterestedCandidate(Candidate candidate) {
        interestedCandidates.add(candidate);
        candidate.getAppliedJobs().add(this);
    }

    // Add a convenience method to remove interested candidates
    public void removeInterestedCandidate(Candidate candidate) {
        interestedCandidates.remove(candidate);
        candidate.getAppliedJobs().remove(this);
    }

    // Getter for interestedCandidates
    public Set<Candidate> getInterestedCandidates() {
        return interestedCandidates;

    }


    public JobSector getSector() {
        return sector;
    }

    public void setSector(JobSector sector) {
        this.sector = sector;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}