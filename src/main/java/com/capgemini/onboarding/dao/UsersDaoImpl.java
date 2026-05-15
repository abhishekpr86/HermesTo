package com.capgemini.onboarding.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.model.UserRoles;
import com.capgemini.onboarding.model.Users;
import com.capgemini.onboarding.model.Vendor;
import com.capgemini.onboarding.service.CgEntityService;
import com.capgemini.onboarding.service.UsersService;

@Repository
public class UsersDaoImpl implements UsersDao{

	private static final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

    @Autowired
	private SessionFactory sessionFactory;
	
	
    @Autowired(required = true)
	private UsersService usersService;
	

	
	
	@Override
	public void addUsers(Users users) {
		Session session = this.sessionFactory.getCurrentSession();
		
			session.save(users);
			logger.info("user saved successfully, User Details=" + users);

	}


	@Override
	public Users getUsersById(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Users users = (Users) session.get(Users.class, id);
		logger.info("users loaded successfully, users details=" + users);
		return users;
		
	}
	
	



@Override
	public void updateUsers(Users users) {
		Session session = this.sessionFactory.getCurrentSession();
	
		session.clear();
		session.saveOrUpdate(users);
		logger.info("user saved successfully, Users Details="+ users);


	
	}



@Override
public List<UserRoles> searchhUsers(UserRoles users) {
	Session session = this.sessionFactory.getCurrentSession();
	Criteria criteria = session.createCriteria(UserRoles.class);//(Users.class);
	String userName = users.getUserName();
	boolean multiplecondition = false;
	/*Role role = users.getRole_id();
	String roleId = role.getRole_id();
	String queryCondition = "Select * from user_roles where ";*/
	
	if (userName != null && !userName.equalsIgnoreCase("")) {
		criteria.add(Restrictions.ilike("userName", userName, MatchMode.ANYWHERE));
		/*queryCondition = queryCondition + "username='"+userName+"' ";
		multiplecondition = true;*/
	}
	/*if (roleId != null && !roleId.equalsIgnoreCase("")) {
		//criteria.add(Restrictions.ilike("role.role_id", roleId, MatchMode.ANYWHERE));
		if(multiplecondition) {
			queryCondition = queryCondition+"and ";
		}
		queryCondition = queryCondition + "role_id='"+roleId+"';";
	}*/
	
	/*TypedQuery<UserRoles> query = session.createSQLQuery(queryCondition);
	return query.getResultList();*/
	
	//cr.createCriteria("testUnitType", "tut", Criteria.LEFT_JOIN, Restrictions.eq("tut.id", tutId));  

	return criteria.list();
}


@Override
public boolean checkUserNameExists(String userName , String userNameHidden) {
	
	Session session = this.sessionFactory.getCurrentSession();
	Criteria criteria = session.createCriteria(Users.class);
	boolean isExist = false;
	if(userName!=null && !userName.equalsIgnoreCase("") ){
		criteria.add(Restrictions.ilike("userName",userName));
		/*criteria.add(Restrictions.ne("userName",userName));*/
	}
	
	if(userNameHidden!=null && !userNameHidden.equalsIgnoreCase("") ){
		criteria.add(Restrictions.ne("userName",userNameHidden));
		
	}
	
	List<Users> usersList = criteria.list();
	if(usersList !=null && !usersList.isEmpty()){
		isExist = true;
	}
		return isExist;
	}


@Override
public void deleteUserRole(UserRoles users) {
	Session session = this.sessionFactory.getCurrentSession();
	Criteria criteria = session.createCriteria(UserRoles.class);
	session.delete(users);
}
	
@Override
public List<Role> addRoleList(String userName) {
	
	Session session = this.sessionFactory.getCurrentSession();
	Criteria criteria = session.createCriteria(UserRoles.class);
	if(userName!=null && !userName.equalsIgnoreCase("") ){
		criteria.add(Restrictions.ilike("userName",userName));
	}
	Projection projection = Projections.property("role_id"); 
	criteria.setProjection(projection);
	List<Role> roleList =  criteria.list();
	List<String> roleIdList = new ArrayList();
	for(int ele=0; ele < roleList.size(); ele++) {
		roleIdList.add(roleList.get(ele).getRole_id());
	}
	Criteria criteriaRole = session.createCriteria(Role.class);
	if(roleList.size() > 0) {
		criteriaRole.add(Restrictions.not(Restrictions.in("role_id", roleIdList)));
	}
	List<Role> addRoleList = criteriaRole.list();
	return addRoleList;
	 
}

@Override
public void disableUser(String corpid) { // Test R 8.1
	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	try {
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.ilike("userName", corpid));
		Users user = (Users) criteria.uniqueResult();//.list();
		user.setEnabled(0);
		session.saveOrUpdate(user);
		tx.commit();
	}catch(Exception e) {
		e.printStackTrace();
		logger.info(e.getMessage());
		logger.error(" Some Problem occurs :- "+e.getMessage());
		tx.rollback();
	}finally {
		if(session != null && session.isOpen()) {
			session.close();
		}
	}
	
}


@Override
public void addUserRole(UserRoles userRole) {
	
	Session session = this.sessionFactory.getCurrentSession();
	//Criteria criteria = session.createCriteria(UserRoles.class);
	session.save(userRole);
}


}
	
