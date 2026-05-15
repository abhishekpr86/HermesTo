package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_course_completions")
public class CourseCompletion implements Serializable {

	private long id;
	private long userid;
	private long course;
	private long timecompleted;
	
	@Id
	@Column(name="id",nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="userid",nullable=false)
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	@Column(name="course",nullable=false)
	public long getCourse() {
		return course;
	}
	public void setCourse(long course) {
		this.course = course;
	}
	
	@Column(name="timecompleted")
	public long getTimecompleted() {
		return timecompleted;
	}
	public void setTimecompleted(long timecompleted) {
		this.timecompleted = timecompleted;
	}
	
	
}
