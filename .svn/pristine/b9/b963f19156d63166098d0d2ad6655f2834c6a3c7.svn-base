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
		@NamedQuery(name="SpocByCountry",
		query="from Spoc s where s.countryId =:countryId"
		)	
	})

@Entity
@Table(name="SPOC")
@Audited
public class Spoc {
	
	private Integer spocId;	
	private String spocName;
	private String spocEmail;
	private Integer countryId;

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
	/**
	 * @return the spocId
	 */
	@Id
	@Column(name="spoc_id", nullable=false)
	@GeneratedValue
	public Integer getSpocId() {
		return spocId;
	}
	/**
	 * @param spocId the spocId to set
	 */
	public void setSpocId(Integer spocId) {
		this.spocId = spocId;
	}
	/**
	 * @return the spocName
	 */
	@Column(name="spoc_name", nullable=false)
	public String getSpocName() {
		return spocName;
	}
	/**
	 * @param spocName the spocName to set
	 */
	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}
	/**
	 * @return the spocEmail
	 */
	@Column(name="spoc_email", nullable=false)
	public String getSpocEmail() {
		return spocEmail;
	}
	/**
	 * @param spocEmail the spocEmail to set
	 */
	public void setSpocEmail(String spocEmail) {
		this.spocEmail = spocEmail;
	}	
	

}
