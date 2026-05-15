package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.EntityDao;
import com.capgemini.onboarding.model.EntityDetail;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private EntityDao entityDao;
	
	@Override
	@Transactional
	public List<EntityDetail> listEntity() {
		return entityDao.listEntity();
	}

	@Override
	@Transactional
	public EntityDetail getEntityById(int entityId) {
		return entityDao.getEntityById(entityId);
	}

}
