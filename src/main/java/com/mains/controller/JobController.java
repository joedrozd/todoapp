package com.mains.controller;

import com.mains.entity.Job;
import com.mains.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobService JobService;
    //POST
    @PostMapping("/addJob")
    public Job addJob(@RequestBody Job Job) {
    	return JobService.saveJob(Job);
    }

    @PostMapping("/addJobs")
    public List<Job> addJobs(@RequestBody List<Job> Jobs) {
        return JobService.saveJobs(Jobs);
    }

    //GET
    @GetMapping("/Jobs")
    public List<Job> getAllJobs() {
        return JobService.getJobs();
    }
    @GetMapping("/JobById/{id}")
    public Job findJobById(@PathVariable int id) {
        return JobService.getJobById(id);
    }
    @GetMapping("/JobByName/{name}")
    public Job findJobByName(@PathVariable String name) {
        return JobService.getJobByName(name);
    }
    @GetMapping("/JobHours")
    public String findJobHours(String jobHours) {
    	String jobHours2 = JobService.getJobHours(jobHours);
    	System.out.println(jobHours2);
        return jobHours2;
    }
   
    //PUT
    @PutMapping("/update")
    public Job updateJob(@RequestBody Job Job)
    {
        System.out.println("UPDATED");
        return JobService.updateJob(Job);
    }


    //DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable int id) {
        return JobService.deleteJob(id);
    }

}