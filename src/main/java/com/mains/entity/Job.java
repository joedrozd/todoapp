package com.mains.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Jobs_table")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    //Id is autogenerated. this allows the id to autoiterate when a new job is added. Also if you declare and ID it
    //should override this.
    
    // declares vars outside of id 

    private String name;
    private String description;
    private String status;
    private int hours;
	// my JPA was working without this defaut constructor but 
	// found during my tests to be an error so adding to make code better
   
	public Job() {
		super();
	}
    
    //getters and setters for everything
    
	public String getName() {
		return name;
	}
	// constructor job
	public Job(int id, String name, String description, String status, int hours) {
		super();
		this.id = id; 
		this.description = description;
		this.name = name; 
		this.status = status;
		this.hours = hours;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public int getHours() {
		return hours;
	}

	public int setHours(int hours) {
		return this.hours = hours;
	}

}