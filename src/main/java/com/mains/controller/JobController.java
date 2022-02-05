package com.mains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mains.entity.Job;
import com.mains.service.JobService;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {
    @Autowired
    private JobService JobService;
    //POST controller - single job
    @PostMapping("/addJob")
    public ResponseEntity<Job> addJob(@RequestBody Job Job) {
        ResponseEntity<Job> jobRes = ResponseEntity.ok(JobService.saveJob(Job));
    	return jobRes;
    }
    //POST controller - multiple jobs 
    @PostMapping("/addJobs")
    public ResponseEntity<List<Job>> addJobs(@RequestBody List<Job> Jobs) {
        ResponseEntity<List<Job>> jobList = ResponseEntity.ok(JobService.saveJobs(Jobs));
        return jobList;
    }
    //After starting the testing I realised the response entity is much easier to test with
    // This is then where I have started working through the controller slowly changing over to responseentitys 
    // surrounding the type declaration.
    //GET - get all jobs controller
    @GetMapping("/Jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
    	ResponseEntity<List<Job>> jobList = ResponseEntity.ok(JobService.getJobs());
        return jobList;
    }
    //GET get job by id getter - id declared in path
    @GetMapping("/getJobById/{id}")
    public Optional<Job> findJobById(@PathVariable int id) {
        return JobService.getJobById(id);
    }
  //GET get job by name getter - name declared in path
    @GetMapping("/getJobByName/{name}")
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
			System.out.println(e);
			return forget;
		}
        return jobHours2;
    }
   
    //PUT -- suimple update controller
    @PutMapping("/updateJob/{id}")
    public Job updateJob(@RequestBody Job Job)
    {
        System.out.println("UPDATED");
        return JobService.updateJob(Job);
    }


    //DELETE - simple delete controller based on id sent in path
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Job> deleteJob(@PathVariable int id) throws Exception {
    	JobService.deleteJob(id);
		return new ResponseEntity<Job>(HttpStatus.ACCEPTED);
    }

}