/**
 * (c) R Systems International Ltd.
 */
package com.capgemini.onboarding.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.type.Type;


// TODO: Auto-generated Javadoc
/**
 * This Interface allows to add methods specific to modules which requires
 * execution SQL queries to achieve the functionality and provides Hibernate
 * interfaces to execute those SQL queries.
 * 
 * @author kaushik.sumit
 * 
 */
public interface SQLDAO {



	

	/**
	 * Execute batch_ fos_ratingcalculation.
	 *
	 * @throws Exception the exception
	 */
	public void executeBatch_Fos_ratingcalculation() throws Exception;


	/**
	 * Execute sql select.
	 *
	 * @param sqlQuery the sql query
	 * @return the list
	 */
	public List<?> executeSQLSelect(final String sqlQuery);

	/**
	 * Execute sql update query.
	 *
	 * @param sqlQuery the sql query
	 * @return 
	 * @throws HibernateException the hibernate exception
	 */
	public int executeSQLUpdateQuery(final String sqlQuery)
			throws HibernateException;

	/**
	 * Execute sql select.
	 *
	 * @param sqlQuery the sql query
	 * @param scalarMapping the scalar mapping
	 * @return the list
	 */
	public List<?> executeSQLSelect(final String sqlQuery,
			LinkedHashMap<String, Type> scalarMapping);

	public  List<?> getTechnologySummeryByStoredProc(String waveName);

}