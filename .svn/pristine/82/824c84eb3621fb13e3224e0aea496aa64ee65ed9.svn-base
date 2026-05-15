/**
 * (c) R Systems International Ltd.
 */
package com.capgemini.onboarding.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.dao.SQLDAO;



// TODO: Auto-generated Javadoc
/**
 * This class allows to add methods and allows to add SQL queries specific to
 * modules which requires execution SQL queries to achieve the functionality.
 * This provides Hibernate interfaces to execute those SQL queries.
 * 
 * @author kaushik.sumit
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SQLDAOImpl implements SQLDAO {

	/** Logger instance. **/
	private static final Logger logger = Logger.getLogger(SQLDAOImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;



	


//	@Autowired
//	private StatisticsDataStructure statisticsDataStructure;

	/** The formatter. */
private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	/* (non-Javadoc)
	 * @see com.capgemini.hermes.dao.SQLDAO#executeSQLSelect(java.lang.String)
	 */
	public List<?> executeSQLSelect(final String sqlQuery) {

		SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);

		logger.debug("SQL query to be executed is : " + query);

		try {
			return query.list();
		} catch (HibernateException hbExp) {
			logger.error(String.format(
					"Error occured while executing query without Scalar"
							+ " mapping - %s ", query.getQueryString()), hbExp);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.capgemini.hermes.dao.SQLDAO#executeSQLUpdateQuery(java.lang.String)
	 */
	public int executeSQLUpdateQuery(final String sqlQuery)
			throws HibernateException {

		SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);

		logger.debug("SQL query to be executed is : " + query);

		try {
			return query.executeUpdate();
		} catch (HibernateException hbExp) {
			logger.error(String.format(
					"Error occured while executing query without Scalar"
							+ " mapping - %s ", query.getQueryString()), hbExp);
			throw hbExp;
		}

	}

	/* (non-Javadoc)
	 * @see com.capgemini.hermes.dao.SQLDAO#executeSQLSelect(java.lang.String, java.util.LinkedHashMap)
	 */
	public List<?> executeSQLSelect(final String sqlQuery,
			LinkedHashMap<String, Type> scalarMapping) {

		List<?> resultList = null;

		SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);

		for (String columnName : scalarMapping.keySet()) {

			query.addScalar(columnName, scalarMapping.get(columnName));
		}

		logger.debug("SQL query to be executed with Scalar mapping is : "
				+ query);

		try {
			resultList = query.list();
		} catch (HibernateException hbExp) {
			logger.error(String.format(
					"Error occured while executing query - %s ",
					query.getQueryString()), hbExp);
		}

		return resultList;
	}

	/**
	 * Gets the current session.
	 *
	 * @return the current session
	 */
	protected final Session getCurrentSession() {

		try {
			
			 //new Configuration().configure().buildSessionFactory();
			return sessionFactory.getCurrentSession();
		} catch (HibernateException hbExp) {
			logger.error(
					"Error occured while obtaining current Hibernate Session"
							+ " to execute Query", hbExp);
		}
		return null;
	}

	/** The Constant SQL_GET_PARENT_GROUPS_GROUPWISE. */
	private static final StringBuilder SQL_GET_PARENT_GROUPS_GROUPWISE = new StringBuilder(
			"SELECT id, name, createdBy, is_group, mainImage, iconImage,parentId")
			.append(" FROM  groups ").append(" WHERE createdBy = %d ");

	/** The Constant SQL_GET_INDOORUNITIDS_BY_PARENTGROUPID. */
	private static final StringBuilder SQL_GET_INDOORUNITIDS_BY_PARENTGROUPID = new StringBuilder(
			"Select gouter.id,gu.IndoorUnit_id,gu.OutdoorUnit_id,ginner.path from groups ginner inner join groups gouter on ginner.parentid = gouter.id Inner Join groups_units gu On gu.Group_id =  ginner.id Where ginner.parentid =%d");

	/** The Constant SQL_GET_WORKHOURBREAKDOWN_STATISTICS. */
	private static final StringBuilder SQL_GET_WORKHOURBREAKDOWN_STATISTICS = new StringBuilder(
			"Select  centralAddress AS centralAddress,SUM(otHighFsThermOn)  AS otHighFsThermOn,SUM(otMediumFsThermOn)  AS otMediumFsThermOn,SUM(otLowFsThermOn) AS otLowFsThermOn,"
					+ "SUM(otHighFsThermOff)  AS otHighFsThermOff,SUM(otMediumFsThermOff)  AS otMediumFsThermOff,SUM(otLowFsThermOff)  AS otLowFsThermOff"
					+ ",ius.currentTime AS currentTime FROM IndoorUnitStatistics ius LEFT JOIN indoorunits iu ON iu.id=ius.indoorunit_id WHERE IndoorUnit_id IN (");

	/** The Constant SQL_GET_GROUPEFFICIENCY_STATISTICS. */
	private static final StringBuilder SQL_GET_GROUPEFFICIENCY_STATISTICS = new StringBuilder(
			"Select  centralAddress AS centralAddress, AVG(efficiency) AS efficiency,AVG(roomTemp)  AS roomTemp,AVG(OutDoorTemp)  AS OutDoorTemp,AVG(setPointTemp) AS setPointTemp,"
					+ "pcc.currentTime AS currentTime FROM POWER_CONSUMPTION_CAPACITY pcc LEFT JOIN indoorunits iu ON iu.id=pcc.IndoorUnit_id WHERE IndoorUnit_id IN (");

	/** The Constant SQL_GET_UNITEFFICIENCY_STATISTICS. */
	private static final StringBuilder SQL_GET_UNITEFFICIENCY_STATISTICS = new StringBuilder(
			"Select efficiency AS efficiency, roomTemp  AS roomTemp, OutDoorTemp AS OutDoorTemp,setPointTemp AS setPointTemp, ");

	/** The ids. */
	/*
	 * private static final StringBuilder SQL_GET_INDOORUNIT_GROUPIDWISE = new
	 * StringBuilder(
	 * "Select  IU.centralAddress,SUM(otHighFsThermOn) otHighFsThermOn,SUM(otMediumFsThermOn) otMediumFsThermOn,SUM(otLowFsThermOn) otLowFsThermOn,"
	 * +
	 * "SUM(otHighFsThermOff)otHighFsThermOff,SUM(otMediumFsThermOff)otMediumFsThermOff,SUM(otLowFsThermOff) otLowFsThermOff,IU.id as InddorUnitid"
	 * +
	 * ",IU.currentTime FROM indoorunits IU INNER JOIN indoorunitstatistics IUS ON IU.id = IUS.IndoorUnit_id"
	 * +
	 * "	Where IU.id IN (1,2,4)	AND IU.currentTime Between Date('2015-02-20') AND Date('2015-02-23')"
	 * + "	Group By IU.id");
	 */
	List<Long> ids = Arrays.asList(380L, 382L, 386L);
	
	/** The start date. */
	static String startDate = "2015-02-20";
	
	/** The end date. */
	static String endDate = "2015-02-23";
	// IN (:ids)

	// +
	// "	Where IU.id IN '"+ids"'	AND IU.currentTime Between Date('"+startDate+"') AND Date('"+endDate+"')"
	// + "	Group By IU.id");

	// SQL_GET_INDOORUNIT_GROUPIDDATEWISE

	/*
	 * private static final StringBuilder SQL_GET_INDOORUNIT_GROUPIDDATEWISE =
	 * new StringBuilder(
	 * "Select  IU.centralAddress,SUM(otHighFsThermOn) otHighFsThermOn,SUM(otMediumFsThermOn) otMediumFsThermOn,SUM(otLowFsThermOn) otLowFsThermOn,"
	 * +
	 * "SUM(otHighFsThermOff)otHighFsThermOff,SUM(otMediumFsThermOff)otMediumFsThermOff,SUM(otLowFsThermOff) otLowFsThermOff"
	 * +
	 * ",Date(IU.currentTime) FROM indoorunits IU INNER JOIN indoorunitstatistics IUS ON IU.id = IUS.IndoorUnit_id     AND IU.id IN ("
	 * ); // +
	 * "	Where IU.id IN '"+ids"'	AND Date(IU.currentTime)  Between Date('"
	 * +startDate+"') AND Date('"+endDate+"')" // +
	 * "	Group By Date(IU.currentTime)");
	 */

	/** The Constant SQL_GET_INDOORUNIT_GROUPIDDATEWISE. */
	private static final StringBuilder SQL_GET_INDOORUNIT_GROUPIDDATEWISE = new StringBuilder(
			"Select  IU.centralAddress,SUM(otHighFsThermOn) otHighFsThermOn,SUM(otMediumFsThermOn) otMediumFsThermOn,SUM(otLowFsThermOn) otLowFsThermOn,"
					+ "SUM(otHighFsThermOff)otHighFsThermOff,SUM(otMediumFsThermOff)otMediumFsThermOff,SUM(otLowFsThermOff) otLowFsThermOff"
					+ ",IU.currentTime FROM indoorunits IU INNER JOIN indoorunitstatistics IUS ON IU.id = IUS.IndoorUnit_id     AND IU.id IN (");
	// +
	// "	Where IU.id IN '"+ids"'	AND Date(IU.currentTime)  Between Date('"+startDate+"') AND Date('"+endDate+"')"
	// + "	Group By Date(IU.currentTime)");

	/** The Constant SQL_GET_POWERCONSUMPTION. */
	private static final StringBuilder SQL_GET_POWERCONSUMPTION = new StringBuilder(
			" SELECT SUM(power_consumed) power_consumed"
					+ " ,SUM(carbondioxide) carbondioxide,SUM(outdoor_temp) outdoor_temp  FROM powerconsumption "
					+ "  WHERE unit_id IN (");
	// +
	// " AND Date(IU.currentTime) Between Date('"+startDate+"') AND Date('"+endDate+"')"
	// + "	Group By Date(IU.currentTime)");

	/** The Constant SQL_GET_INDOORUNIT_CURRTEMP. */
	private static final StringBuilder SQL_GET_INDOORUNIT_CURRTEMP = new StringBuilder(
			"Select SUM(currentTemp) as TotalCurrentTemp,Date(IU.currentTime) as CurrentTime FROM indoorunits IU "
					+ " INNER JOIN indoorunitslog IUL ON IU.id = IUL.IndoorUnit_id"
					+ "  AND IU.id IN (");

	/*
	 * Select SUM(currentTemp) as TotalCurrentTemp ,Date(IU.currentTime) as
	 * CurrentTime FROM indoorunits IU INNER JOIN indoorunitslog IUL ON IU.id =
	 * IUL.IndoorUnit_id Where IU.id IN (1,2,4) AND Date(IU.currentTime) Between
	 * Date('2015-02-20') AND Date('2015-02-23') Group By Date(IU.currentTime);
	 */

	/*
	 * Select SUM(currentTemp) as TotalCurrentTemp ,Date(IU.currentTime) as
	 * CurrentTime FROM indoorunits IU INNER JOIN indoorunitslog IUL ON IU.id =
	 * IUL.IndoorUnit_id Where IU.id IN (1,2,4) AND Date(IU.currentTime) Between
	 * Date('2015-02-20') AND Date('2015-02-23') Group By Date(IU.currentTime);
	 */

	/** The Constant SQL_GET_GROUPUNITS_GROUPWISE. */
	private static final StringBuilder SQL_GET_GROUPUNITS_GROUPWISE = new StringBuilder(
			"SELECT id,IndoorUnit_id,Group_id,OutdoorUnit_id")
			.append(" FROM groups_units where group_id = %d ");

	/** The Constant SQL_GET_INDOORUNIT. */
	private static final StringBuilder SQL_GET_INDOORUNIT = new StringBuilder(
			"SELECT id,serialNumber,enabled,state, Adapters_Id,type,identifier,masterUnitId,")
			.append(" line,unitModelInformation_id,metaIndoorUnit_id,centralAddress,createdAt,updatedAt, ")
			.append(" createdBy,updatedBy FROM indoorunits where id=1 ");

	/** The Constant SQL_EXECUTE_FOS_RATINGCALCULATION. */
	private static final StringBuilder SQL_EXECUTE_FOS_RATINGCALCULATION = new StringBuilder(
			"CALL FOS_RATINGCALCULATION()");


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.SQLDAO#executeBatch_Fos_ratingcalculation()
	 */
	@Override
	public void executeBatch_Fos_ratingcalculation() throws Exception {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(
				SQL_EXECUTE_FOS_RATINGCALCULATION.toString());

		sqlQuery.executeUpdate();

	}

	@Override
	public List getTechnologySummeryByStoredProc(String waveName)
	{
		logger.info("In getTechnologySummeryByStoredProc waveName "+waveName);
		List<Object[]> result = null;
		try
		{
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery("Call technology_summary(:waveName)");
			sqlQuery.setString("waveName", waveName);
			result = sqlQuery.list();
		}
		catch(Exception e)
		{
			logger.error("Error occured while obtaining current Hibernate Session to execute Query", e);
		}
		return result;
	}

}