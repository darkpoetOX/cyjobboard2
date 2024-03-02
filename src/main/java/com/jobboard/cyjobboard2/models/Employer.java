package com.jobboard.cyjobboard2.models;

import jakarta.persistence.*;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String address;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Job> jobPostings = new HashSet<>();


    // Constructors, getters, and setters

    public Employer() {
    }

    public Employer(String companyName, String email, String password, String address) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Job> getJobPostings() {
        return jobPostings;
    }

    public void setJobPostings(Set<Job> jobPostings) {
        this.jobPostings = jobPostings;
    }

}