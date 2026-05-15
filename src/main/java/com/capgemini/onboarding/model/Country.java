package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="COUNTRY")
@Audited
public class Country implements Comparable<Country>{
	
	private Integer countryId;	
	private String countryName;
	/**
	 * @return the countryId
	 */
	@Id
	@Column(name="country_id", nullable=false)
	@GeneratedValue
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
	 * @return the countryName
	 */
	@Column(name="country_name", nullable=false)
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}	
	
	@Override
    public int compareTo(Country country) {
		return this.getCountryName().compareTo(country.getCountryName());
      }

}
