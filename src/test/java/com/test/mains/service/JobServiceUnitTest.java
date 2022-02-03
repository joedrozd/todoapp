package com.test.mains.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mains.entity.Job;
import com.mains.repositories.JobRepository;
import com.mains.service.JobService;


@ExtendWith(MockitoExtension.class) // JUnit test runner
class JobServiceUnitTest {

	@Mock
	private JobRepository JobRepository;
	
	@InjectMocks 
	private JobService JobService;
	

	private List<Job> jobs;
	
	@BeforeEach // junit5 (jupiter) annotation to run this method before every test
	public void init() {
		jobs = new ArrayList<>();
		jobs.addAll(List.of(new Job(1,"Job1", "Dance", "Not Done", 1), 
				  			 new Job(2, "Job2", "Juggle", "Not Done", 2),
				  			 new Job(3, "Job3", "Eat", "Not Done", 3)));
	}
	@Test
	public void saveJobTest() {
		Job Job4 = new Job(4,"Job4", "Party", "Not Done", 123);
		    when(JobService.saveJob(Job4)).thenReturn(Job4);
	}
	
	@Test
	public void getJobsTest() {
		when(JobRepository.findAll()).thenReturn(jobs);
		assertThat(JobService.getJobs()).isEqualTo(jobs);
		verify(JobRepository).findAll();
	}
	


	@Test
	public void deleteJobTest() {
		// TODO: Implement me
		fail("Implement me");
	}

}
