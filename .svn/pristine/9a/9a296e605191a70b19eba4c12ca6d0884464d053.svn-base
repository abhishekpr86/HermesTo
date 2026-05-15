package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_enrol")
public class MoodleEnrolment implements Serializable {
	
	private long id;
	private String enrol;
	private long status;
	private long courseid;
	
	@Id
	@Column(name="id",nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="enrol",nullable=false)
	public String getEnrol() {
		return enrol;
	}
	public void setEnrol(String enrol) {
		this.enrol = enrol;
	}
	
	@Column(name="status",nullable=false)
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	
	@Column(name="courseid",nullable=false)
	public long getCourseid() {
		return courseid;
	}
	public void setCourseid(long courseid) {
		this.courseid = courseid;
	}
	

}
