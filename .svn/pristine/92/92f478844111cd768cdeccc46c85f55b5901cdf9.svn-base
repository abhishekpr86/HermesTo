package com.capgemini.onboarding.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "bis_rotation")
public class BisRotation implements java.io.Serializable, Comparable<BisRotation>{

	private long id;
	private String idCorpId; // emp table id
	private String corpId;
	private Bis prevBundle;
	private Bis newBundle;
	private LocalDate rotationDate;
	private String firstName;
	private String lastName;
	private EmployeeRoles role;
	private GlobalGrade grade;
	private PrimaryProgram primaryProgram;
	private Country country;
	private State location;
	
	public BisRotation() {
		
	}
	
	public BisRotation(long id, String idCorpId, String corpId, Bis prevBundle, Bis newBundle, LocalDate rotationDate, String firstName, String lastName, GlobalGrade grade, EmployeeRoles role, PrimaryProgram primaryProgram, Country country, State location) {
		
		this.id = id;
		this.idCorpId = idCorpId;
		this.corpId = corpId;
		this.prevBundle = prevBundle;
		this.newBundle = newBundle;
		this.rotationDate = rotationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.role = role;
		this.primaryProgram = primaryProgram;
		this.country = country;
		this.location = location;
	}
	
	

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "idCorpId", nullable = false)
	public String getIdCorpId() {
		return idCorpId;
	}
	
	public void setIdCorpId(String idCorpId) {
		this.idCorpId = idCorpId;
	}
	
	@Column(name = "corpId", nullable = false)
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@ManyToOne
	@JoinColumn(name= "prevBundle",referencedColumnName= "bis_id")
	public Bis getPrevBundle() {
		return prevBundle;
	}
	public void setPrevBundle(Bis prevBundle) {
		this.prevBundle = prevBundle;
	}
	
	@ManyToOne
	@JoinColumn(name="newBundle", referencedColumnName = "bis_id")
	public Bis getNewBundle() {
		return newBundle;
	}
	public void setNewBundle(Bis newBundle) {
		this.newBundle = newBundle;
	}
	
	@Column(name = "rotationDate", nullable = false)
	public LocalDate getRotationDate() {
		return rotationDate;
	}
	public void setRotationDate(LocalDate rotationDate) {
		this.rotationDate = rotationDate;
	}
	
	@Transient
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Transient
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Transient
	public EmployeeRoles getRole() {
		return role;
	}
	public void setRole(EmployeeRoles role) {
		this.role = role;
	}
	
	@Transient
	public GlobalGrade getGrade() {
		return grade;
	}
	public void setGrade(GlobalGrade grade) {
		this.grade = grade;
	}
	
	@Transient
	public PrimaryProgram getPrimaryProgram() {
		return primaryProgram;
	}

	public void setPrimaryProgram(PrimaryProgram primaryProgram) {
		this.primaryProgram = primaryProgram;
	}
	
	@Transient
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Transient
	public State getLocation() {
		return location;
	}

	public void setLocation(State location) {
		this.location = location;
	}

	@Override
	public int compareTo(BisRotation bisRotRec) {
		// TODO Auto-generated method stub
		return this.getIdCorpId().compareTo(bisRotRec.getIdCorpId());
	}
	
	
}
