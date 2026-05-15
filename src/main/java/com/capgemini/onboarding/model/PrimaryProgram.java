package com.capgemini.onboarding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name="primary_program")
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Proxy(lazy = false)
public class PrimaryProgram implements Comparable<PrimaryProgram>{
	
	private int primaryProgramId;
	private String primaryProgramName;
	private Boolean moodleEnrolReq;
	private Boolean welcomeEmailReq;
	private Boolean confluenceaccess;
	private Boolean isDeleted;
	
	/*@ManyToMany
	@JoinTable(
			name = "pp_bis_mapping",
			joinColumns = @JoinColumn(table="primary_program",name ="pp_id", referencedColumnName="primary_program_id"),
			inverseJoinColumns = @JoinColumn(table="bis", name ="bis_id", referencedColumnName="bis_id"))*/
	private List<Bis> bisList;// = new ArrayList<Bis>(0);;

	

	/**
	 * @return the primaryProgramId
	 */
	@Id
	@GeneratedValue
	@Column(name = "primary_program_id",nullable = false)
	public int getPrimaryProgramId() {
		return primaryProgramId;
	}

	/**
	 * @param primaryProgramId the primaryProgramId to set
	 */
	
	public void setPrimaryProgramId(int primaryProgramId) {
		this.primaryProgramId = primaryProgramId;
	}
	
	/**
	 * @return the primaryProgramName
	 */
	
    @Column(name = "primary_program_name", nullable = false)
	public String getPrimaryProgramName() {
		return primaryProgramName;
	}

    /**
	 * @param primaryProgramName the primaryProgramName to set
	 */
	public void setPrimaryProgramName(String primaryProgramName) {
		this.primaryProgramName = primaryProgramName;
	}
	
	@Column(name = "moodleenrolreq", nullable = true)
	public Boolean getMoodleEnrolReq() {
		return moodleEnrolReq;
	}

	public void setMoodleEnrolReq(Boolean moodleEnrolReq) {
		this.moodleEnrolReq = moodleEnrolReq;
	}

	@Column(name = "welcomeemailreq", nullable = true)
	public Boolean getWelcomeEmailReq() {
		return welcomeEmailReq;
	}

	public void setWelcomeEmailReq(Boolean welcomeEmailReq) {
		this.welcomeEmailReq = welcomeEmailReq;
	}

	@Column(name = "confluenceaccess", nullable = true)
	public Boolean getConfluenceaccess() {
		return confluenceaccess;
	}

	public void setConfluenceaccess(Boolean confluenceaccess) {
		this.confluenceaccess = confluenceaccess;
	}
	
	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinTable(name = "pp_bis_mapping",
			joinColumns = @JoinColumn(table="primary_program",name ="primary_program_id", referencedColumnName="primary_program_id", nullable = false, updatable = false),
			inverseJoinColumns = @JoinColumn(table="bis", name="bis_id", referencedColumnName="bis_id"))
	public List<Bis> getBisList() {
		return bisList;
	}

	public void setBisList(List<Bis> bisList) {
		this.bisList = new ArrayList<Bis> (bisList);
	}
	
	@Override
	public int compareTo(PrimaryProgram primProg) {
		return this.getPrimaryProgramName().compareTo(primProg.getPrimaryProgramName());
	}

	
}
