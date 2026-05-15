package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.CGEntityDetail;

public interface CgEntityDao {

	public List<CGEntityDetail> listCgEntity();

	public CGEntityDetail getCgEntityById(int cgEntityId);

	public void updateCGEntity(CGEntityDetail cg);
}
