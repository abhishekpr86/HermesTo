package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_cohort")
public class MoodleCohort implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private long id;
	private String cohortName;
	
	@Id
	@Column(name="id",nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="name",nullable=false)
	public String getCohortName() {
		return cohortName;
	}
	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}
	
	
}
