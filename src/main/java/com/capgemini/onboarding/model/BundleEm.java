package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "bundle_em")
@Audited
public class BundleEm implements Comparable<BundleEm> {
	
	private Integer bundleEmId;
	private String bundleEmName;
	private Boolean isDeleted;
	
	/**
	 * @return the bundleEmId
	 */
	@Id
	@Column(name = "bundleEm_id", nullable = false)
	@GeneratedValue	
	
	public Integer getBundleEmId() {
		return bundleEmId;
	}
	

	/**
	 * @param bundleEmId to set
	 *           
	 */
	
	public void setBundleEmId(Integer bundleEmId) {
		this.bundleEmId = bundleEmId;
	}
	
	@Column(name = "bundleEm_name", nullable = false)
	public String getBundleEmName() {
		return bundleEmName;
	}
	
	
	/**
	 * @param bundleEmName to set
	 *           
	 */
	public void setBundleEmName(String bundleEmName) {
		this.bundleEmName = bundleEmName;
	}

	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Override
	public int compareTo(BundleEm bundleEm) {
		return this.getBundleEmName().compareTo(bundleEm.getBundleEmName());
		
	}
}
