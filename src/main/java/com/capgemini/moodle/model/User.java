package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_user")
public class User implements Serializable /*, Comparable<T>*/ {

	private Long id;
	private String auth = "manual";
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private int confirmed =1;
	private int policyagreed=0;
	private int deleted=0;
	private int suspended=0;
	private int mnethostid=1;
	private int emailstop=0;
	
	
	@Id
	@Column(name="id",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "auth", nullable = false)
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "firstname", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "lastname", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "city", nullable = false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "confirmed", nullable = false)
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	
	@Column(name = "policyagreed", nullable = false)
	public int getPolicyagreed() {
		return policyagreed;
	}
	public void setPolicyagreed(int policyagreed) {
		this.policyagreed = policyagreed;
	}
	
	@Column(name = "deleted", nullable = false)
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Column(name = "suspended", nullable = false)
	public int getSuspended() {
		return suspended;
	}
	public void setSuspended(int suspended) {
		this.suspended = suspended;
	}
	
	@Column(name = "mnethostid", nullable = false)
	public int getMnethostid() {
		return mnethostid;
	}
	public void setMnethostid(int mnethostid) {
		this.mnethostid = mnethostid;
	}
	
	@Column(name = "emailstop", nullable = false)
	public int getEmailstop() {
		return emailstop;
	}
	public void setEmailstop(int emailstop) {
		this.emailstop = emailstop;
	}
	/*@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	
}
