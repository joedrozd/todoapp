package com.mains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mains.entity.Job;
import com.mains.repositories.JobRepository;

import java.util.List;

@Service
public class JobService {
    
    private JobRepository JobRepository;
    
    @Autowired
    public JobService(JobRepository JobRepository) {
		this.JobRepository = JobRepository;
	}//constructor injection is used to avoid null pointer exceptions and allows us to create immutable objects. It also means all dependencies are available when compiling.
    // Connects Job service to specific repository or db.
    
    //POST - saves 1 job and prints the object - i need to ovverride tostring at some point.
    public Job saveJob(Job Job) {
        return JobRepository.save(Job);
    }
    
    //SAVEALL - save a list of jobs to the repository
    public List<Job> saveJobs(List<Job> Jobs) {
        return JobRepository.saveAll(Jobs);
    }

    //GET
    public List<Job> getJobs() {
        return JobRepository.findAll();
    }
    // getter to find all jobs - useful to test if app is working 
    
    // gets jobs by ID and returns null if id not found.
    public Job getJobById(int id) {
        return JobRepository.findById(id).orElse(null);
    }
    
    
    // gets jobs by declared name - might be good point for custom exception - needs testing.
    public Job getJobByName(String name) {
        return JobRepository.findByName(name);
    }
    
    
    // jobhours feature branch  gets jobs as a list and then gets 1st positions hours and then sends a string back saying hours left.
    // could be a lot bigger and more features in full fledged application such as -get(0) replaced with a name input or id input for example. 
    // it wont find hours if there are no jobs so I made a custom exception that returns string reminding the user. 
    public String getJobHours(String jobHours) {
    	List<Job> hoursList = getJobs();
    	Job jobHoursInstance = hoursList.get(0);
    	int jobHoursNo = jobHoursInstance.getHours();
    	jobHours = "You have " + jobHoursNo + " left to go of Job 1!";
    	return jobHours; 
        }
	//PUT - updates job based on what you change within the argument - i think this could be compressed into a setter/getting single statement maybe 
    // then it saves the job
    public Job updateJob(Job Job) {
        System.out.println("updates");
        Job existing_Job = JobRepository.findById(Job.getId()).orElse(null);
        existing_Job.setName(Job.getName());
        existing_Job.setDescription(Job.getDescription());
        existing_Job.setStatus(Job.getStatus());
        existing_Job.setHours(Job.getHours());
        return JobRepository.save(existing_Job);
    }

    //DELETE - deleted job by id and returns which id was deleted. 
    public void deleteJob(int id) {
        JobRepository.deleteById(id);
        
    }


}