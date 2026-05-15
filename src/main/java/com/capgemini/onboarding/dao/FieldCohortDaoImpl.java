package com.capgemini.onboarding.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.FieldCohortMapping;
import com.capgemini.onboarding.model.PrimaryProgram;
import com.capgemini.onboarding.model.Technology;
import com.capgemini.onboarding.service.EmployeeRoleService;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.EmployeeRoles;

@Repository
public class FieldCohortDaoImpl implements FieldCohortDao {

	private Logger logger = Logger.getLogger(FieldCohortDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private EmployeeRoleService employeeRoleService;
	
	@Override
	public List<FieldCohortMapping> getCohortForFields(int tnm, Employee emp){
		//int tnm = (emp.isTimeNMaterial())? 1 : 0;
		int bisid  = emp.getBis().getBis_id();
		EmployeeRoles emprole = this.employeeRoleService.getEmployeeRolesId(emp.getEmprole());
		PrimaryProgram pp = emp.getPrimaryprogram();
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		//Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(FieldCohortMapping.class);
		LogicalExpression timeNMaterial = null;
		LogicalExpression primaryProgramExp = null;
		if(tnm == 1 && pp != null) {
			timeNMaterial = Restrictions.and(Restrictions.eq("fieldName", "timeNMaterial"),Restrictions.eq("fieldValue", pp.getPrimaryProgramId()));
		}else { // tnm = 0 - no mapping for this TnM No still kept this considering there can be mapping in future
			timeNMaterial = Restrictions.and(Restrictions.eq("fieldName", "timeNMaterial"),Restrictions.eq("fieldValue", tnm));
		}
		LogicalExpression bis = Restrictions.and(Restrictions.eq("fieldName", "bis"),Restrictions.eq("fieldValue", bisid));
		//LogicalExpression emproleExp = Restrictions.and(Restrictions.eq("fieldName", "emprole"),Restrictions.eq("fieldValue", emprole.getRef_id()));
		if(pp != null) {
			primaryProgramExp = Restrictions.and(Restrictions.eq("fieldName", "primaryprog"),Restrictions.eq("fieldValue", pp.getPrimaryProgramId()));
		}else {
			primaryProgramExp = Restrictions.and(Restrictions.eq("fieldName", "primaryprog"),Restrictions.eq("fieldValue", 0));
		}
		SimpleExpression rolePPExp = Restrictions.eq("fieldName", pp.getPrimaryProgramName()+"-"+emprole.getRef_value());
		SimpleExpression skillRoleExp = Restrictions.eq("fieldName", emp.getPrimaryprogram().getPrimaryProgramName()+"-"+emprole.getRef_value()+((emp.getPrimaryTechnology().getTechnologyValue() != null)?("-"+emp.getPrimaryTechnology().getTechnologyValue()) :""));
		//SimpleExpression skillRoleExp = Restrictions.eq("fieldName", ((emp.getPrimaryTechnology().getTechnologyValue() != null)?(emp.getPrimaryTechnology().getTechnologyValue()) :"")+"-"+emprole.getRef_value());
		criteria.add(Restrictions.or(timeNMaterial,bis,primaryProgramExp,rolePPExp,skillRoleExp));
		List<FieldCohortMapping> list = criteria.list();
		session.close();		
		return list;
	}

	@Override
	public List<FieldCohortMapping> getCohortForFields(int tnm, Employee emp, int newBis, int newEmpRole,
			PrimaryProgram newPP, Technology primTech) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		try {
		
		EmployeeRoles emprole = this.employeeRoleService.getEmployeeRolesId(emp.getEmprole());
		//Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(FieldCohortMapping.class);
		List<FieldCohortMapping> list = null;
		LogicalExpression timeNMaterial = null;
		LogicalExpression primaryProgramExp = null;
		SimpleExpression rolePPExp = null;
		SimpleExpression skillRoleExp = null;
		LogicalExpression bis = null;
		Disjunction dis = Restrictions.disjunction();
		if(tnm == 1 && newPP != null) {
			timeNMaterial = Restrictions.and(Restrictions.eq("fieldName", "timeNMaterial"),Restrictions.eq("fieldValue", newPP.getPrimaryProgramId()));
		}else if(tnm == 0){ // tnm = 0 - no mapping for this TnM No still kept this considering there can be mapping in future
			timeNMaterial = Restrictions.and(Restrictions.eq("fieldName", "timeNMaterial"),Restrictions.eq("fieldValue", tnm));
		}
		dis.add(Restrictions.or(timeNMaterial));
		if(newBis != 0) {
			bis = Restrictions.and(Restrictions.eq("fieldName", "bis"),Restrictions.eq("fieldValue", newBis));
			dis.add(Restrictions.or(bis));
		}
		//LogicalExpression emproleExp = Restrictions.and(Restrictions.eq("fieldName", "emprole"),Restrictions.eq("fieldValue", emprole.getRef_id()));
		if(newPP != null) {
			primaryProgramExp = Restrictions.and(Restrictions.eq("fieldName", "primaryprog"),Restrictions.eq("fieldValue", newPP.getPrimaryProgramId()));
			rolePPExp = Restrictions.eq("fieldName", emp.getPrimaryprogram().getPrimaryProgramName()+"-"+emprole.getRef_value());
			skillRoleExp = Restrictions.eq("fieldName", emp.getPrimaryprogram().getPrimaryProgramName()+"-"+emprole.getRef_value()+((emp.getPrimaryTechnology().getTechnologyValue() != null)?("-"+emp.getPrimaryTechnology().getTechnologyValue()) :""));
			dis.add(Restrictions.or(primaryProgramExp,rolePPExp,skillRoleExp));
		}else {
			primaryProgramExp = Restrictions.and(Restrictions.eq("fieldName", "primaryprog"),Restrictions.eq("fieldValue", 0));
			dis.add(Restrictions.or(primaryProgramExp));
		}
		if(newEmpRole != 0) {
			rolePPExp = Restrictions.eq("fieldName", emp.getPrimaryprogram().getPrimaryProgramName()+"-"+emprole.getRef_value());
			skillRoleExp = Restrictions.eq("fieldName", emp.getPrimaryprogram().getPrimaryProgramName()+"-"+emprole.getRef_value()+((emp.getPrimaryTechnology().getTechnologyValue() != null)?("-"+emp.getPrimaryTechnology().getTechnologyValue()) :""));
			dis.add(Restrictions.or(rolePPExp,skillRoleExp));
		}
		if(primTech != null && skillRoleExp == null) {
			skillRoleExp = Restrictions.eq("fieldName", emp.getPrimaryprogram().getPrimaryProgramName()+"-"+emprole.getRef_value()+((emp.getPrimaryTechnology().getTechnologyValue() != null)?("-"+emp.getPrimaryTechnology().getTechnologyValue()) :""));
			dis.add(Restrictions.or(skillRoleExp));
		}
		
		
		criteria.add(dis);
		list = criteria.list();
		logger.info("cohort mapping exist "+list.size());
		session.close();	
		return list;
	
		}
		catch(NullPointerException e) {
			session.close();
			return null;
			
		}
		catch(Exception e) {
			logger.info("Exception in FieldCohortDao "+e.getMessage());
			session.close();
			return null;
		}
	}

}
