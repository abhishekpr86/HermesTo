package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "mdl_cohort_members")
public class MoodleCohortMembers implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	private long id;
	private MoodleCohort cohortid;
	private User userid;
	private long enrolmentTime;
	
	
	@Id
	@Column(name="id",nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="cohortid",nullable=false, foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	public MoodleCohort getCohortid() {
		return cohortid;
	}
	public void setCohortid(MoodleCohort cohortid) {
		this.cohortid = cohortid;
	}
	
	@OneToOne
	@JoinColumn(name = "userid", nullable = false, foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	
	@Column(name="timeadded",nullable=false)
	public long getEnrolmentTime() {
		return enrolmentTime;
	}
	public void setEnrolmentTime(long enrolmentTime) {
		this.enrolmentTime = enrolmentTime;
	}
	
	
}
