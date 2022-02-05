package com.mains.controller;

import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mains.entity.Job;
import com.mains.service.JobService;

@WebMvcTest(JobController.class)
class JobControllerWebIntegrationTest {
	@Autowired
	private JobController JobController;
	
	@MockBean 
	private JobService JobService;
	


	private List<Job> jobs;
	
	@BeforeEach 
	public void init() {
		jobs = new ArrayList<>();
		jobs.addAll(List.of(new Job(1,"Job1", "Dance", "Not Done", 1), 
				  			 new Job(2, "Job2", "Juggle", "Not Done", 2),
				  			 new Job(3, "Job3", "Eat", "Not Done", 3)));
	}

	@Test
	public void addJobTest() {
		Job Job4 = new Job(4,"Job4", "Party", "Not Done", 123);
		ResponseEntity<Job> expected = new ResponseEntity<Job>(HttpStatus.OK);
		ResponseEntity<Job> actual = JobController.addJob(Job4);
		assertEquals(expected, actual); // junit assertion
		
		verify(JobService).saveJob(Job4);
	}
	
	@Test
	public void addJobsTest() {
		Job Job4 = new Job(4,"Job4", "Party", "Not Done", 123);
		Job Job5 = new Job(5,"Job5", "Party3", "Not Done", 123);
		List<Job> jobs2 = new ArrayList<>();
		jobs2.add(Job4);
		jobs2.add(Job5);
		List<Job> why = new ArrayList<>(); // because the addAll runs already in the beforeEach it returns 2 arrays instead of 1 
		//this just replicates this in the expected response. 
		ResponseEntity<List<Job>> expected = new ResponseEntity<List<Job>>(why, HttpStatus.OK);
		ResponseEntity<List<Job>> actual = JobController.addJobs(jobs2);
		assertEquals(expected, actual); // junit assertion
		
		verify(JobService).saveJobs(jobs2);
	}
	
	@Test
	void testDeleteJob() throws Exception {
		

		int id = 1;
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		ResponseEntity<?> actual = JobController.deleteJob(id);
		
		assertEquals(expected,actual);
		verify(JobService).deleteJob(id);
	}

	@Test
	public void getAllUsersTest() {
		ResponseEntity<List<Job>> expected = new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		// given (some initial data/conditions)
		// this is being performed by init()
		
		// when (the action does occur)
		when(JobService.getJobs()).thenReturn(jobs);
		
		// then (assert this happened)
		ResponseEntity<List<Job>> actual = JobController.getAllJobs();
		assertThat(expected).isEqualTo(actual); // static assertThat method from assertJ
		
		// we also need to verify that the service was called by the controller
		verify(JobService, times(1)).getJobs(); // verify and times are from mockito
		// verify(userService).getAll(); // equivalent to the above line
	}


	}


