package com.service;

import com.entity.Job;
import com.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository JobRepository;
    

    //POST
    public Job saveJob(Job Job) {
        System.out.println(Job.toString());
        return this.JobRepository.save(Job);
    }
    
    //SAVEALL
    public List<Job> saveJobs(List<Job> Jobs) {
        return JobRepository.saveAll(Jobs);
    }

    //GET
    public List<Job> getJobs() {
        return JobRepository.findAll();
    }
    public Job getJobById(int id) {
        return JobRepository.findById(id).orElse(null);
    }
    public Job getJobByName(String name) {
        return JobRepository.findByName(name);
    }

    //PUT
    public Job updateJob(Job Job) {
        System.out.println("updates");
        Job existing_Job = JobRepository.findById(Job.getId()).orElse(null);
        existing_Job.setName(Job.getName());
        existing_Job.setDescription(Job.getDescription());
        existing_Job.setStatus(Job.getStatus());
        return JobRepository.save(existing_Job);
    }

    //DELETE
    public String deleteJob(int id) {
        JobRepository.deleteById(id);
        return id + " id -> Job removed/completed";
    }

}