package com.mains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mains.entity.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findByName(String name);
    //finds job by name 
    
    
    //finds all jobs and creates a list of them
    List<Job> findAllByName(String name);

}