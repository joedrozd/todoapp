package com.mains.service;

import com.mains.entity.Job;
import com.mains.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    
    private JobRepository JobRepository;
    
    @Autowired
    public JobService(JobRepository JobRepository) {
		this.JobRepository = JobRepository;
	}//constructor injection is used to avoid null pointer exceptions and allows us to create immutable objects. It also means all dependencies are available when compiling.
    // Connects Job service to specific repository or db.
    
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
    
    public String getJobHours(String jobHours) {
    	List<Job> hoursList = getJobs();
    	Job jobHoursInstance = hoursList.get(0);
    	int jobHoursNo = jobHoursInstance.getHours();
    	jobHours = "You have " + jobHoursNo + "left to go!";
    	return jobHours; 
        }
	//PUT
    public Job updateJob(Job Job) {
        System.out.println("updates");
        Job existing_Job = JobRepository.findById(Job.getId()).orElse(null);
        existing_Job.setName(Job.getName());
        existing_Job.setDescription(Job.getDescription());
        existing_Job.setStatus(Job.getStatus());
        existing_Job.setHours(Job.getHours());
        return JobRepository.save(existing_Job);
    }

    //DELETE
    public String deleteJob(int id) {
        JobRepository.deleteById(id);
        return id + " Job removed by ID";
    }


}