package com.capgemini.onboarding.constants;

public class OnBoardingSQLQueries {
	

	public static final String DashBoardGridForAllEmployee = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee where emp_id not in('NA') GROUP BY country_id "
			+ "UNION SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee where emp_id not in('NA') ";

	public static final String ListActiveEmpCountryWiseOld = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERe ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+ " OR ( actual_end_date is null and end_date is not null  AND end_date >= CURDATE() )) GROUP By country_id";
	
	//ListActiveEmpCountryWise-new
	public static final String ListActiveEmpCountryWise = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERe ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+ " OR ( actual_end_date is null )) GROUP By country_id";
	
	public static final String ListBillableEmpCountryWiseOld = "SELECT COUNT(*), country_id FROM `employee` "
			+ " Where ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+" OR (actual_end_date is null and (end_date is not null and end_date >= CURDATE() )))"
			+ " AND (billing_date is not null AND billing_date <= CURDATE() ) GROUP By country_id";
	
	public static final String ListBillableEmpCountryWise = "SELECT COUNT(*), country_id FROM `employee` "
			+ " Where ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+" OR (actual_end_date is null ))"
			+ " AND (billing_date is not null AND billing_date <= CURDATE() ) GROUP By country_id";
	
	public static final String ListActiveInternalEmpCountryWiseOld = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='Internal' " + " and   ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+ " OR ( actual_end_date is  null  and end_date is not null  AND end_date >= CURDATE())) GROUP By country_id   ";
	
	public static final String ListActiveInternalEmpCountryWise = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='Internal' " + " and   ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+ " OR ( actual_end_date is  null )) GROUP By country_id   ";

	public static final String ListBillableInternalEmpCountryWiseOld = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='Internal' " + "and   ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+" OR (actual_end_date is null and (end_date is not null and end_date >= CURDATE() )))"
			+ "AND (billing_date is not null AND billing_date <= CURDATE() ) GROUP By country_id";
	
	public static final String ListBillableInternalEmpCountryWise = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='Internal' " + "and   ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+" OR (actual_end_date is null ))"
			+ "AND (billing_date is not null AND billing_date <= CURDATE() ) GROUP By country_id";
	
	public static final String ListActiveExternalEmpCountryWiseOld = "   SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='External' " + " and emp_id not in('NA') and   ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+ " OR (actual_end_date is  null  and end_date is not null  AND end_date >= CURDATE())) GROUP By country_id   ";
	
	public static final String ListActiveExternalEmpCountryWise = "   SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='External' " + " and emp_id not in('NA') and   ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+ " OR (actual_end_date is  null )) GROUP By country_id   ";

	public static final String ListBillableExternalEmpCountryWiseOld = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='External' " + " and emp_id not in('NA') and  ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+" OR (actual_end_date is null and (end_date is not null and end_date >= CURDATE() )))"
			+ " AND (billing_date is not null AND billing_date <= CURDATE() ) GROUP By country_id";
	
	public static final String ListBillableExternalEmpCountryWise = "SELECT COUNT(*),country_id FROM `employee` "
			+ " WHERE emp_type='External' " + " and emp_id not in('NA') and  ((actual_end_date is not null AND actual_end_date >= CURDATE()) "
			+" OR (actual_end_date is null ))"
			+ " AND (billing_date is not null AND billing_date <= CURDATE() ) GROUP By country_id";
	
	public static final String DashBoardGridActiveEmployeeOld = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and   ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+ "OR ( actual_end_date is  null  and end_date is not null AND end_date >= CURDATE() )) GROUP BY country_id "
			+ "UNION "
			+ "SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+ "OR ( actual_end_date is  null  and end_date is not null AND end_date >= CURDATE() ))";
	
	public static final String DashBoardGridActiveEmployee = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and   ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+ "OR ( actual_end_date is  null   )) GROUP BY country_id "
			+ "UNION "
			+ "SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+ "OR ( actual_end_date is  null   ))";	
	
	//mehens - BIS
	public static final String DashBoardBISActiveEmployee = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA')  and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+ "OR ( actual_end_date is  null   )) GROUP BY country_id "
			+ "UNION "
			+ "SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA')  and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+ "OR ( actual_end_date is  null   ))";	
	
	
	public static final String DashBoardGridBillableEmployeeOld = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and   ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+"OR (actual_end_date is null and end_date is not null and end_date >= CURDATE() )) "
			+ "AND (billing_date is not null AND billing_date <= CURDATE()) GROUP BY country_id "
			+ "UNION "
			+ "SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+"OR (actual_end_date is null and end_date is not null and end_date >= CURDATE() )) "
			+ "AND ( billing_date is not null AND billing_date <= CURDATE())";
	
	public static final String DashBoardGridBillableEmployee = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and   ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+"OR (actual_end_date is null  )) "
			+ "AND (billing_date is not null AND billing_date <= CURDATE()) GROUP BY country_id "
			+ "UNION "
			+ "SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
			+ "WHERE emp_id not in('NA') and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
			+"OR (actual_end_date is null  )) "
			+ "AND ( billing_date is not null AND billing_date <= CURDATE())";			

	public static final String ListOfManager = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email`, `grade_id`, `country_id`, `state_id`, "
			+ "`tech_id`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`, `security_training`, `spoc_id`, "
			+ "`global_grade_id`, `vendor_id`, `emp_type`, `actual_end_date`, `entity_id`, `dob`, `psa_id`, `comment`, `heritage_type`, "
			+ "`emp_sex`, `cg_entity`, `manager_email`, `creation_date` FROM `employee` where   global_grade_id > 2 order by last_name";  //order by last name - add (actual_end_date is null OR actual_end_date >now()) to reduce the list only for Preonboarding Page
	
	public static final String ListOfActiveManager = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email`, `grade_id`, `country_id`, `state_id`, "
			+ "`tech_id`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`, `security_training`, `spoc_id`, " 
			+ "`global_grade_id`, `vendor_id`, `emp_type`, `actual_end_date`, `entity_id`, `dob`, `psa_id`, `comment`, `heritage_type`, "
			+ "`emp_sex`, `cg_entity`, `manager_email`, `creation_date` FROM `employee` where   global_grade_id > 2 and (actual_end_date is null OR (actual_end_date is not null and actual_end_date >CURDATE())) order by last_name";
	
	public static final String ListOfManagersByBundleEm = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email`, `grade_id`, `country_id`, `state_id`, "
			+ "`tech_id`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`, `security_training`, `spoc_id`, "
			+ "`global_grade_id`, `vendor_id`, `emp_type`, `actual_end_date`, `entity_id`, `dob`, `psa_id`, `comment`, `heritage_type`, "
			+ "`emp_sex`, `cg_entity`, `manager_email`, `creation_date` FROM `employee` where  global_grade_id IN (3,4,5,6)";
	
	//mehens-buddy - removed - and joining_date <= current_date()
	public static final String ListOfBuddyEmp = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email` "
			+ " FROM `employee` where  emp_type in ('Internal', 'External') and " 
			+ " (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null and end_date >= CURDATE()))";	
	
	public static final String ListOfManagersByEm = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email`, `grade_id`, `country_id`, `state_id`, "
			+ "`tech_id`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`, `security_training`, `spoc_id`, "
			+ "`global_grade_id`, `vendor_id`, `emp_type`, `actual_end_date`, `entity_id`, `dob`, `psa_id`, `comment`, `heritage_type`, "
			+ "`emp_sex`, `cg_entity`, `manager_email`, `creation_date` FROM `employee` where  global_grade_id IN (3,4,5,6) order by last_name"; //order by last name removed 2 from global grades
	
	public static final String ListOfManagersByEmActive = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email`, `grade_id`, `country_id`, `state_id`, "
			+ "`tech_id`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`, `security_training`, `spoc_id`, "
			+ "`global_grade_id`, `vendor_id`, `emp_type`, `actual_end_date`, `entity_id`, `dob`, `psa_id`, `comment`, `heritage_type`, "
			+ "`emp_sex`, `cg_entity`, `manager_email`, `creation_date` FROM `employee` where  global_grade_id IN (3,4,5,6) and (actual_end_date is null OR (actual_end_date is not null and actual_end_date >CURDATE())) order by last_name";

	//Engg - Start			
	/*public static final String ListEmEngg = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email` "
				+ " FROM `employee` where  global_grade_id IN (3,4,5,6) and isEngg = 1 and (actual_end_date is null OR (actual_end_date is not null and actual_end_date >CURDATE())) order by last_name";
				
				
	public static final String ListEmOthers = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email` "
				+ " FROM `employee` where  global_grade_id IN (3,4,5,6) and isEngg = 0 and (actual_end_date is null OR (actual_end_date is not null and actual_end_date >CURDATE())) order by last_name";*/				
				
	public static final String ListEmEngg = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email` "
			+ " FROM `employee` where  global_grade_id IN (3,4,5,6) and primary_Program_id = 10 and (actual_end_date is null OR (actual_end_date is not null and actual_end_date >CURDATE())) order by last_name";
			
			
	public static final String ListEmOthers = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email` "
			+ " FROM `employee` where  global_grade_id IN (3,4,5,6) and primary_Program_id != 10 and (actual_end_date is null OR (actual_end_date is not null and actual_end_date >CURDATE())) order by last_name";
	
	
	//Engg - Start	
	
	
	public static final String PyramidChart = "select global_grade_id , count(*) from employee ";
	
	public static final String listOfEM = "SELECT `username`, `password`, `enabled`, `role_id`  FROM `users` where username ='porathod' ";
	
	public static final String offShoreEMIndia = "SELECT `id`, `emp_id`, UPPER(first_name), UPPER(last_name), UPPER(corp_id), `email`, `grade_id`, `country_id`, `state_id`, "
			+ "`tech_id`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`, `security_training`, `spoc_id`, "
			+ "`global_grade_id`, `vendor_id`, `emp_type`, `actual_end_date`, `entity_id`, `dob`, `psa_id`, `comment`, `heritage_type`, "
			+ "`emp_sex`, `cg_entity`, `manager_email`, `creation_date` FROM `employee` where  global_grade_id IN (3,4,5,6) and country_id = 2 ";
	
	public static final String selectColumn = "id, emp_id, corp_id, first_name, last_name, email, grade_id, country_id, state_id, tech_id, joining_date, end_date, "
												+ "contact, entry_date, created_by, mgr_id, security_training, onboarding_checked, spoc_id, global_grade_id, vendor_id, emp_type,"
												+ " actual_end_date, entity_id, dob, psa_id, comment, heritage_type, emp_sex, cg_entity, manager_email, creation_date, EM, "
												+ "bundleEm_id, offshore_em_id, resource_mgr_id, gg_id, bis_id,decision_val_by_gp ";
	
	public static final String orphanManagerListOld = "  SELECT " + selectColumn
			+ " FROM employee WHERE  mgr_id in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate()) or (actual_end_date is null and (end_date is not null and end_date <=curdate()))) and archive_type is null) "
			+ " and id in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null and end_date is not null  AND end_date >= CURDATE() )) and archive_type is null)  "; // change

	//orphanManagerList-New
	public static final String orphanManagerList = "  SELECT " + selectColumn
				+ " FROM employee WHERE  manager_email in (SELECT email from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate())) and archive_type is null) "
				+ " and id in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null )) and archive_type is null)  ";
	
	public static final String orphanManagerListCountryWiseOld = "  SELECT " + selectColumn
			+ " FROM employee WHERE  mgr_id in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate()) or (actual_end_date is null and (end_date is not null and end_date <=curdate()))) and archive_type is null) "
			+ " and id  in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null and end_date is not null  AND end_date >= CURDATE() )) and archive_type is null)  "
			+ " and  country_id = :country_id ";
	
	//orphanManagerListCountryWise-New
	public static final String orphanManagerListCountryWise = "  SELECT " + selectColumn
			+ " FROM employee WHERE  manager_email in (SELECT email from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate())) and archive_type is null) "
			+ " and id  in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null  )) and archive_type is null)  "
			+ " and  country_id = :country_id ";
	
	public static final String orphanBundleEMListOld ="  SELECT " + selectColumn
			+ " FROM employee WHERE  bundleEm_id in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate()) or (actual_end_date is null and (end_date is not null and end_date <=curdate()))) and archive_type is null) "
			+ " and id in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null and end_date is not null  AND end_date >= CURDATE() )) and archive_type is null)  ";
	
	//orphanBundleEMList-New
	public static final String orphanBundleEMList ="  SELECT " + selectColumn
			+ " FROM employee WHERE  bundleEm_id in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate())) and archive_type is null) "
			+ " and id in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null )) and archive_type is null)  ";
	
	public static final String orphanEMListOld = "  SELECT " + selectColumn
			+ " FROM employee WHERE  EM in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate()) or (actual_end_date is null and (end_date is not null and end_date <=curdate()))) and archive_type is null) "
			+ " and id in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null and end_date is not null  AND end_date >= CURDATE() )) and archive_type is null)  ";
	//orphanEMList-New
	public static final String orphanEMList = "  SELECT " + selectColumn
			+ " FROM employee WHERE  EM in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate())) and archive_type is null) "
			+ " and id in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null )) and archive_type is null)  ";	
	
	public static final String orphanOffshoreEMListOld = "  SELECT " + selectColumn
			+ " FROM employee WHERE  offshore_em_id in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate()) or (actual_end_date is null and (end_date is not null and end_date <=curdate()))) and archive_type is null) "
			+ " and id  in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null and end_date is not null  AND end_date >= CURDATE() )) and archive_type is null)  ";
		
	//orphanOffshoreEMList-New
	public static final String orphanOffshoreEMList = "  SELECT " + selectColumn
			+ " FROM employee WHERE  offshore_em_id in (SELECT id from employee where global_grade_id >= 3 and ((actual_end_date is not null and actual_end_date<=curdate())) and archive_type is null) "
			+ " and id  in (SELECT id from employee where joining_date<= curdate() AND (( actual_end_date is not null AND actual_end_date >= CURDATE()) OR ( actual_end_date is  null )) and archive_type is null)  ";	
	
	public static final String roleMappingQuery = "SELECT role, role_name from role_mapping where role_id in (SELECT role_id FROM `users` WHERE username='" ;
	
	public static final String bisPmoSubmit = "SELECT emp.psa_id as psaId,emp.corp_id as empCorpId,pre.BISPMOSubmitDT as preSubmitDate,pre.res_status as preResourceStatus,pre.RMPMOSubmitDT as RmSubmitDate,pre.first_name as FirstName, pre.last_name as LastName, pr.primary_program_name as PrimaryProgram  from preonbemployee  pre,employee  emp , primary_program pr  where emp.corp_id=pre.corp_id and pr.primary_program_id=pre.primary_program_id  and pre.res_status in('BISPMOSubmitted','VMInProccess')and (psa_id is Null or psa_id=\"\")";

	public static final String emailId ="SELECT email from  employee where employee where corp_id='";
	
}
