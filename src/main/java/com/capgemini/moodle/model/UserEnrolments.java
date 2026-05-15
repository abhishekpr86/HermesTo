package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_user_enrolments")
public class UserEnrolments implements Serializable {

	private long id;
	private long status;
	private long enrolid;
	private long userid;
	private long timecreated;
	
	@Id
	@Column(name="id",nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="status",nullable=false)
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	
	@Column(name="enrolid",nullable=false)
	public long getEnrolid() {
		return enrolid;
	}
	public void setEnrolid(long enrolid) {
		this.enrolid = enrolid;
	}
	
	@Column(name="userid",nullable=false)
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	@Column(name="timecreated")
	public long getTimecreated() {
		return timecreated;
	}
	public void setTimecreated(long timecreated) {
		this.timecreated = timecreated;
	}
	
	
}
