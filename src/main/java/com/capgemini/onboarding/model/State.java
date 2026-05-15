package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@NamedQueries(value = {		
		@NamedQuery(name="listStateByCountry",
		query="from State s where s.countryId =:countryId"
		)	
	})

@Entity
@Table(name="STATE")
@Audited
public class State implements Comparable<State>{
	
	private Integer stateId;	
	private String stateName;
	private Integer countryId;
	private Boolean isDeleted;
	private boolean isVLANReq;
	private String VLANName;
	
	/**
	 * @return the stateId
	 */
	@Id
	@Column(name="state_id", nullable=false)
	@GeneratedValue
	public Integer getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the stateName
	 */
	@Column(name="state_name", nullable=false)
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the countryId
	 */
	@Column(name="country_id", nullable=false)
	public Integer getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */	
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}	
	
	@Column(name="isDeleted", nullable=true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="isVLANReq", nullable=true)
	public boolean getIsVLANReq() {
		return isVLANReq;
	}
	public void setIsVLANReq(boolean isVLANReq) {
		this.isVLANReq = isVLANReq;
	}
	@Column(name="VLANName", nullable=true)
	public String getVLANName() {
		return VLANName;
	}
	public void setVLANName(String vLANName) {
		VLANName = vLANName;
	}
	@Override
    public int compareTo(State state) {
		return this.getStateName().compareTo(state.getStateName());
      }
}
