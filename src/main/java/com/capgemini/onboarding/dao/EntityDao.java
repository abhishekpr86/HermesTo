package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.EntityDetail;

public interface EntityDao {

	public List<EntityDetail> listEntity();
	
	public EntityDetail getEntityById(int entityId);
}
