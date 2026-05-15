package com.capgemini.onboarding.service;

import java.util.Date;
import java.util.List;

import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.JiraIDCreator;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.model.Users;

public interface PreOnbEmployeeService {

	public void addPreOnbEmployee(PreOnbEmployee p);
	public void updatePreOnbEmployee(PreOnbEmployee p);
	public PreOnbEmployee getPreOnbEmployeeDAOById(Long id);
	
	public List<Employee> listManagers();
	public List<Employee> listManagersByBundleEm();
	public List<Employee> listManagersByEm();
	
	public List<PreOnbEmployee> getPreOnbEmployeeByGrade(List<Integer> gradeList);
	public List<PreOnbEmployee> getAllPreOnbEmployee();
	public boolean checkCorpIdExistsInPreOnb(String corpId, String empId,String empType, String pageType);
	public boolean checkEmployeeEmailExists(String email,String empId,String empType);
	
	//public List<Users> getAllEM();
	public List<PreOnbEmployee>  getAllEmployees();
	public List<PreOnbEmployee> modifyEmployeeList(List<PreOnbEmployee> empList);
	public PreOnbEmployee getPreOnbEmployeeByCorpId(String corpID);
	//public boolean checkCorpIdExists(String corpId, String empId, String empType, String pageType);
	public Employee getResourceMgrEmpRec(Integer resourceMgrId);
	public boolean checkPreOnbEmployeeExists(String empId, String empType);
	
	//Mohini added start
		public List<PreOnbEmployee> preOnboardingSearch(Employee emp, String roleId);
		public Employee getEmpFromPreOnboarding(String corpId);
		public void insertEmployee(Employee emp);
		
	//Mohini added end	
		public PreOnbEmployee getPreOnbEmployee(String corpId);
		public RmPMO getRmPmoDetails();
		public DLList getDLListbyRole(String role);
		public List<DLList> getMultipleDLListbyRole(String role);
		public JiraIDCreator getJiraCreatorDetails();
		
		public List<Role> roleList(String username);
		public Boolean isFirstLogin(String username); //new
		public List<Role> roleList(); // new
		Employee getPreOnbEmployees(String corpId);
		public List<Employee> listBuddyEmp();//mehens-buddy
		public List<Employee> listEm(int pp);//Engg
		public List<Employee> listManagersNew(int pp);//Engg
		
		//onboarding request report
		public void onbReqReport(PreOnbEmployee emp, String filename, String excelFilePath);
		public List<PreOnbEmployee> onbReqReportFilter(PreOnbEmployee emp, Integer limit);
		public List<PreOnbEmployee> searchPreOnbEmpReport(PreOnbEmployee emp, Integer limit) ;
}
