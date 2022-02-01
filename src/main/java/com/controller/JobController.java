package com.controller;

import com.entity.Job;
import com.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:5000"})
@RestController
public class JobController {
    @Autowired
    private JobService JobService;
    //POST
    @PostMapping("/addJob")
    public Job addJob(@RequestBody Job Job) {
        com.entity.Job adder = Job;
        return JobService.saveJob(adder);
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