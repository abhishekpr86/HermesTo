package com.capgemini.onboarding.dto;

import java.util.List;

import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.model.State;

public class EmployeeDTO {
	
	
	private String repFirstName;
	private String corpId;
	private String firstName;
	private String lastName;
	private String email;
	//private GlobalGrade globalGrade;
	private Integer globalGrade;
	private String empId;
	private String ggId;
	private Country country;
	private String capgemEntity;
	private String manager;
	private String managerEmail;
	private String pcSerialNumber; //dkaushik
	private String employeeTypes;
	
	
	//private State location;


	public String getRepFirstName() {
		return repFirstName;
	}  

	public void setRepFirstName(String repFirstName) {
		this.repFirstName = repFirstName;
	}
	

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGlobalGrade() {
		return globalGrade;
	}

	public void setGlobalGrade(Integer globalGrade) {
		this.globalGrade = globalGrade;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getGgId() {
		return ggId;
	}

	public void setGgId(String ggId) {
		this.ggId = ggId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCapgemEntity() {
		return capgemEntity;
	}

	public void setCapgemEntity(String capgemEntity) {
		this.capgemEntity = capgemEntity;
	}
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getEmployeeTypes() {
		return employeeTypes;
	}

	public void setEmployeeTypes(String employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

	public String getPcSerialNumber() {
		return pcSerialNumber;
	}

	public void setPcSerialNumber(String pcSerialNumber) {
		this.pcSerialNumber = pcSerialNumber;
	}

	


	
	
	
	
	

}
