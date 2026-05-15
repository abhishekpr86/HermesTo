package com.capgemini.onboarding.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.dao.UserManagementDao;
import com.capgemini.onboarding.util.CommonUtil;

/**
 * The Class UserManagementDaoImpl.
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserManagementDaoImpl implements UserManagementDao {

	private Logger logger = Logger.getLogger(UserManagementDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.capgemini.hermes.dao.UserManagementDao#changeUserPassword(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public int changeUserPassword(String username, String newpassword, String oldPassword) {

		Session session = sessionFactory.getCurrentSession();

		// Parameterised query — immune to SQL injection
		String storedPassword = (String) session
				.createNativeQuery("SELECT password FROM users WHERE username = :username")
				.setParameter("username", username)
				.uniqueResult();

		if (storedPassword == null || !CommonUtil.comparePassword(oldPassword, storedPassword)) {
			return 0;
		}

		String hashedNewPassword = CommonUtil.getPasswordBcrypt(newpassword);
		logger.info("changeUserPassword: hashing and updating password for user.");

		return session
				.createNativeQuery("UPDATE users SET password = :password, isFirstLogin = '0' WHERE username = :username")
				.setParameter("password", hashedNewPassword)
				.setParameter("username", username)
				.executeUpdate();
	}

}
