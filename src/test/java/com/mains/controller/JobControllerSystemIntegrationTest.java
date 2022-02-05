package com.mains.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


import com.mains.entity.Job;
import com.mains.repositories.JobRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc // Configure the MockMvc object
@Transactional 
class JobControllerSystemIntegrationTest {
	// MockMvc is used for sending HTTP requests
		@Autowired
		private MockMvc mockMvc;

		// ObjectMappers are used for serializing and deserializing objects
		@Autowired
		private ObjectMapper objectMapper;

		@Autowired
		private JobRepository JobRepository;


		 private ArrayList<Job> jobsUpdate = new ArrayList<>();;
		  
		@BeforeEach 
		public void init() {
			jobsUpdate.addAll(List.of(new Job(1,"Job1", "Dance", "Not Done", 1), 
					  			 new Job(2, "Job2", "Juggle", "Not Done", 2),
					  			 new Job(3, "Job3", "Eat", "Not Done", 3)));
			JobRepository.saveAll(jobsUpdate);
		}

		  @AfterEach
		  public void teardown() {
		    JobRepository.deleteAll(jobsUpdate);
		  }
		@Test
		public void updateUserTest() throws Exception {
			// GIVEN
			Job detailsToUpdate = new Job(2, "JobJuggler", "Juggle", "Done", 244);
			Job expectedJob = new Job(2, "JobJuggler", "Juggle", "Done", 244);
			// Configure mock request
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/updateJob/2");
			mockRequest.contentType(MediaType.APPLICATION_JSON);
			mockRequest.content(objectMapper.writeValueAsString(detailsToUpdate));
			mockRequest.accept(MediaType.APPLICATION_JSON);
			String userJson = objectMapper.writeValueAsString(expectedJob);
			ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
			ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(userJson); // Send the request and assert
																							// the results where as expected
			mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		}
		
		@Test
		public void getJobByIdTest() throws Exception {
			//test job to check against 

			Job testJob = new Job(3, "Job3", "Eat", "Not Done", 3);
			
			// Create a mock http request builder
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getJobById/3");

			// Specify the Accept header for the expected returned content type (MediaType)
			mockRequest.accept(MediaType.APPLICATION_JSON); // Accept: application/json

			// Create expected JSON String from testjob using the ObjectMapper
			// instance
			String job = objectMapper.writeValueAsString(testJob);

			// Setup ResultMatchers
			// - used for comparing the result of the mockRequest with our own specified
			// values
			ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
			ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

			// Send the request and assert the results where as expected
			mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		}
		@Test
		public void getJobByNameTest() throws Exception {
			//test job to check against 

			Job testJob = new Job(1,"Job1", "Dance", "Not Done", 1);
			
			// Create a mock http request builder
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getJobByName/Job1");

			// Specify the Accept header for the expected returned content type (MediaType)
			mockRequest.accept(MediaType.APPLICATION_JSON); // Accept: application/json

			// Create expected JSON String from testjob using the ObjectMapper
			// instance
			String job = objectMapper.writeValueAsString(testJob);

			// Setup ResultMatchers
			// - used for comparing the result of the mockRequest with our own specified
			// values
			ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
			ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

			// Send the request and assert the results where as expected
			mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		}
}
