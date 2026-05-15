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
		@NamedQuery(name="listBisByBundleEm",query="from Bis_bundle_Mapping b where b.bis_bundleEm_id =:bis_bundleEm_id"),
		@NamedQuery(name="listBundleEmByBis",query="from Bis_bundle_Mapping b where b.bis_id =:bis_id")
	})

@Entity
@Table(name = "bis_bundleem")
@Audited
public class Bis_bundle_Mapping  {
	
	private int bis_bundleEm_id;
	private int  bis_id;
	private Boolean isDeleted;
	
	@Id
	@GeneratedValue
	@Column(name = "bis_bundleEm_id", nullable = true)
	
	public int getBis_bundleEm_id() {
		return bis_bundleEm_id;
	}


	public void setBis_bundleEm_id(int bis_bundleEm_id) {
		this.bis_bundleEm_id = bis_bundleEm_id;
	}


	@Column(name = "bis_id", nullable = true)
	public int getBis_id() {
		return bis_id;
	}


	public void setBis_id(int bis_id) {
		this.bis_id = bis_id;
	}

	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	}
