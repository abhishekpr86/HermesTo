package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.State;

@Repository
public class StateDaoImpl implements StateDao {

	private static final Logger logger = LoggerFactory.getLogger(StateDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<State> listState(int countryId,boolean isBundleEMReq) {
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<State> query = session.createNamedQuery("listStateByCountry");		
		query.setParameter("countryId", countryId);
		
		Criteria criteria = session.createCriteria(State.class);
		if(isBundleEMReq) {
			criteria.add(Restrictions.isNull("isDeleted"));
		}
		criteria.add(Restrictions.eq("countryId", countryId));
		
		List<State> listState = criteria.list(); //query.getResultList();
		Collections.sort(listState);
		return listState;	
	}

	@Override
	public State getStateById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		State state = (State) session.load(State.class, new Integer(id));
		logger.info("state loaded successfully, state details=" + state);
		return state;
	}

	@Override
	public List VLANList() {
		Session session = this.sessionFactory.getCurrentSession();		
		Criteria criteria = session.createCriteria(State.class);
		ProjectionList projList = Projections.projectionList();
	    projList.add(Projections.property("stateId"));
	    criteria.add(Restrictions.eq("isVLANReq", true));
		//criteria.add(Restrictions.isNotNull("isVLANReq"));
		criteria.setProjection(projList);
		List VLANList = criteria.list();
		return VLANList;
	}
	
	@Override
	public List newStateList() {
		Session session = this.sessionFactory.getCurrentSession();		
		Criteria criteria = session.createCriteria(State.class);
		ProjectionList projList = Projections.projectionList();
	    projList.add(Projections.property("stateId"));
	    criteria.add(Restrictions.eq("stateName", "New Site"));
	    criteria.setProjection(projList);
		List newstateList = criteria.list();
		return newstateList;
	}

}
