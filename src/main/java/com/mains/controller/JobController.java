package com.mains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mains.entity.Job;
import com.mains.service.JobService;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobService JobService;
    //POST controller - single job
    @PostMapping("/addJob")
    public Job addJob(@RequestBody Job Job) {
    	return JobService.saveJob(Job);
    }
    //POST controller - multiple jobs 
    @PostMapping("/addJobs")
    public List<Job> addJobs(@RequestBody List<Job> Jobs) {
        return JobService.saveJobs(Jobs);
    }

    //GET - get all jobs controller
    @GetMapping("/Jobs")
    public List<Job> getAllJobs() {
        return JobService.getJobs();
    }
    //GET get job by id getter - id declared in path
    @GetMapping("/JobById/{id}")
    public Job findJobById(@PathVariable int id) {
        return JobService.getJobById(id);
    }
  //GET get job by name getter - name declared in path
    @GetMapping("/JobByName/{name}")
    public Job findJobByName(@PathVariable String name) {
        return JobService.getJobByName(name);
    }
    
    //GET feature branch -- sends the jobhours request -- this is where the custom exception is added 
    // prints exception to console currently - doesnt return job if none added - returns a question string
    // could have added a path variable and get job by ID or name method to get specifc job hours 
    @GetMapping("/JobHours")
    public String findJobHours(String jobHours2) {
		try {jobHours2 = JobService.getJobHours(jobHours2);
    	System.out.println(jobHours2);
		} catch (Exception e) {
			String forget = "Did you forget to add a job?";
			System.out.println(forget);
			System.out.println(e);
			return forget;
		}
        return jobHours2;
    }
   
    //PUT -- suimple update controller
    @PutMapping("/update")
    public Job updateJob(@RequestBody Job Job)
    {
        System.out.println("UPDATED");
        return JobService.updateJob(Job);
    }


    //DELETE - simple delete controller based on id sent in path
    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable int id) {
        return JobService.deleteJob(id);
    }

}