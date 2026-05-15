package com.capgemini.onboarding.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BISCompositeKey implements Serializable {
	
	private int bis_id;
	private int bis_bundleEm_id;
	
	@Override
	public int hashCode() {
			
		return Objects.hash(getBis_id(), getBis_bundleEm_id());

	}
	
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) return true;
        if (!(o instanceof BISCompositeKey)) return false;
        BISCompositeKey that = (BISCompositeKey) o;
        return Objects.equals(getBis_id(), that.getBis_id()) &&
                Objects.equals(getBis_bundleEm_id(), that.getBis_bundleEm_id());
        
	}
	
	@Column(name = "bis_id", nullable = false , insertable=false , updatable = false)
	public int getBis_id() {
		return bis_id;
	}


	public void setBis_id(int bis_id) {
		this.bis_id = bis_id;
	}

	@Column(name = "bis_bundleEm_id", nullable = false  , insertable=false , updatable = false)
	public int getBis_bundleEm_id() {
		return bis_bundleEm_id;
	}


	public void setBis_bundleEm_id(int bis_bundleEm_id) {
		this.bis_bundleEm_id = bis_bundleEm_id;
	}
	
	
	
	
	
	

}
