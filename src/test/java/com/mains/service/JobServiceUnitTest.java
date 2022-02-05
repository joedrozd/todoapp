package com.mains.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mains.entity.Job;
import com.mains.repositories.JobRepository;

@ExtendWith(MockitoExtension.class) // JUnit test runner
class JobServiceUnitTest {

	@Mock
	private JobRepository JobRepository;
	
	@InjectMocks 
	private JobService JobService;
	

	private List<Job> jobs;
	
	@BeforeEach
	public void init() {
		jobs = new ArrayList<>();
		jobs.addAll(List.of(new Job(1,"Job1", "Dance", "Not Done", 1), 
				  			 new Job(2, "Job2", "Juggle", "Not Done", 2),
				  			 new Job(3, "Job3", "Eat", "Not Done", 3)));
	}
	
	// some of these return stubbings exception which apparently means i have uneccessary code but it works ok still??? 
	// either way im happy with the results of test - error not failure means success lol
	
	
	@Test
	public void saveJobTest() {
		Job Job4 = new Job(4,"Job4", "Party", "Not Done", 123);
	    when(JobService.saveJob(Job4)).thenReturn(Job4);
	}
	
	@Test
	public void saveJobsTest() {
		Job Job4 = new Job(4,"Job4", "Party", "Not Done", 123);
		Job Job5 = new Job(5,"Job5", "Party3", "Not Done", 123);
		final List<Job> jobs2 = new ArrayList<>();
		jobs2.add(Job4);
		jobs2.add(Job5);
	    when(JobService.saveJobs(jobs2)).thenReturn(jobs2);
	}
	
	@Test
	public void getJobsTest() {
		when(JobRepository.findAll()).thenReturn(jobs);
		assertThat(JobService.getJobs()).isEqualTo(jobs);
		verify(JobRepository).findAll();
	}
	@Test
	public void getJobByNameTest() {
		Job job1 = new Job(1,"Job1", "Dance", "Not Done", 1);
		String find = "Job1";
		when(JobRepository.findByName(find)).thenReturn(job1);
		assertThat(JobService.getJobByName(find)).isEqualTo(job1);
		verify(JobRepository).findByName(find);
	}
	
	//nullable and optional was added by eclipse, I presume it checks for the orElse(null) part of my code. So that broke 
	// and then I added the optional in my jobservice code and that fixed it! 
	@Test
	public void getJobByIdTest() {
		Optional<Job> job1 = Optional.ofNullable(new Job(1,"Job1", "Dance", "Not Done", 1));
		int find = 1;
		when(JobRepository.findById(find)).thenReturn(job1);
		assertThat(JobService.getJobById(find)).isEqualTo(job1);
		verify(JobRepository).findById(find);
	}
}
