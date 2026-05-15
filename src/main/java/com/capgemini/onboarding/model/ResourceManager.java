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
		@NamedQuery(name = "listResourceManagerByCountry",
				query = "from ResourceManager r where r.countryId =:countryId")})

@Entity
@Table(name = "resource_mgr")
@Audited

public class ResourceManager implements java.io.Serializable, Comparable<ResourceManager>{

	private Integer resourceMgrId;
	private String resourceMgrName;
	private Integer countryId;
	private Boolean isDeleted;

	/**
	 * @return the resourceMgrId
	 */
	@Id
	@Column(name = "resource_mgr_id", nullable = false)
	@GeneratedValue
	public Integer getResourceMgrId() {
		return resourceMgrId;
	}

	/**
	 * @param resourceMgrId
	 *            the resourceMgrId to set
	 */

	public void setResourceMgrId(Integer resourceMgrId) {
		this.resourceMgrId = resourceMgrId;
	}

	/**
	 * @return the resourceMgrName
	 */
	@Column(name = "resource_mgr_name", nullable = false)
	public String getResourceMgrName() {
		return resourceMgrName;
	}

	/**
	 * @param resourceMgrName
	 *            the resourceMgrName to set
	 */

	public void setResourceMgrName(String resourceMgrName) {
		this.resourceMgrName = resourceMgrName;
	}

	/**
	 * @return the countryId
	 */
	@Column(name = "country_id", nullable = false)
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	@Override
	public int compareTo(ResourceManager rmgr) {
		return this.getResourceMgrName().compareTo(rmgr.getResourceMgrName());
	}

}
