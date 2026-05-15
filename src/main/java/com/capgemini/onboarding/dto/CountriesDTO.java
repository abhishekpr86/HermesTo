package com.capgemini.onboarding.dto;

import java.util.List;

import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.Grade;
import com.capgemini.onboarding.model.ResourceManager;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.model.State;
import com.capgemini.onboarding.model.Vendor;

public class CountriesDTO {


	private List<Vendor> vendorList;
	private Spoc spoc;
	List<State> stateList;
	private List<ResourceManager> resourceMgrList;
	List<Grade> gradeList;
	
	/**
	 * @return the gradeList
	 */
	public List<Grade> getGradeList() {
		return gradeList;
	}
	/**
	 * @param gradeList the gradeList to set
	 */
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	/**
	 * @return the vendorList
	 */
	public List<Vendor> getVendorList() {
		return vendorList;
	}
	/**
	 * @param vendorList the vendorList to set
	 */
	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	/**
	 * @return the spoc
	 */
	public Spoc getSpoc() {
		return spoc;
	}
	/**
	 * @param spoc the spoc to set
	 */
	public void setSpoc(Spoc spoc) {
		this.spoc = spoc;
	}
	/**
	 * @return the stateList
	 */
	public List<State> getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List<State> stateList) {
		this.stateList = stateList;

	}
	
	/**
	 * @return the resourceMgrList
	 */
	public List<ResourceManager> getResourceMgrList() {
		return resourceMgrList;
	}
	
	/**
	 * @return the resourceMgrList
	 */
	public void setResourceMgrList(List<ResourceManager> resourceMgrList) {
		this.resourceMgrList = resourceMgrList;
	}
	
	
	
	
	
		
}
