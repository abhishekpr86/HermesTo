package com.capgemini.onboarding.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.activedirectory.ActiveDirectory;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.HeadCountReport;
import com.capgemini.onboarding.model.ReplacementType;
import com.capgemini.onboarding.model.RollOffType;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.model.Users;

public interface EmployeeDAO {

	public void addEmployee(Employee p);
	public void updateEmployee(Employee p);
	public List<Employee> listManagers();
	public List<Employee> listManagersByBundleEm();
	public List<Employee> listManagersByEm(String ActiveOrNot);
	public List<Employee> listOffshoreEmIndia();
	public Employee getEmployeeById(Long id);
	public Employee getEmployee(Long id);//mehens
	public Employee getEmployeeByEmail(String email);
	public void removeEmployee(int id);
	public List<Employee> getEmployeesByGrade(List<Integer> gradeList);
	public List<Employee> getAllEmployees();
	public List<Employee> searchEmployees(Employee emp);
	public void offboardEmployee(Date endDate, Long id , Employee e, Employee emp);
	public List<Employee> searchEmployeeReport(Employee emp, Integer limit);
	public List<Employee> searchEmpReportWithoutPlannedOffBoardDate(Employee emp, Integer limit);//mehens
	public List<Employee> searchOffboardReport(Employee emp);
	public List<Employee> addEmployeeDetailsExist(Employee emp);
	public List<Employee> editEmployeeDetailsExist(Employee emp);
	public boolean checkEmployeeExists(String empId , String empType);
	public Employee checkArchivedEmployeeExists(String corpId);
	public boolean checkCorpIdExists(String corpId,String empId,String empType, String pageType);
	
	public boolean checkPsaIDExists(String psaId,String empId,String empType);
	public boolean checkEmployeeEmailExists(String email,String empId,String empType);
	public List<Employee> getAllEmployeesCountryWise();
	public List<Employee> getInternalEmployeesCountryWise();
	public List<Employee> getExternalEmployeesCountryWise();
	public List<Employee> getDashBoardList();
	public List<Employee> getDashBoardActiveEmpList();
	public List<Employee> getDashBoardBillableEmpList();
	public List<Employee> getActiveEmployeesCountryWise();
	public List<Employee> getActiveInternalEmployeesCountryWise();
	public List<Employee> getActiveExternalEmployeesCountryWise();
	public List<Employee> getBillableEmployeesCountryWise();
	public List<Employee> getBillableInternalEmployeesCountryWise();
	public List<Employee> getBillableExternalEmployeesCountryWise();
	public List<Employee> getPyramidChart(Integer countryId);
	//public List<Users> getAllEM();
	public Employee getEmployeeByCorpId(String corpID);
	public Employee getActiveEmployeeByCorpId(String corpID);//new method for logged user
	public List<Employee> getOffBoardingListForNext30Days(Employee emp);
	public List<Float>  headCountList(LocalDate current_month_startDate_local , LocalDate current_Month_Enddate__local , int country_Id);
	public List<Employee> getFTEList(LocalDate current_month_startDate_local , LocalDate current_Month_Enddate__local , int country_Id);
	public List<Employee> getFTEListCountryWise(LocalDate current_month_startDate, LocalDate current_Month_Enddate,int country_Id);
	public List<Employee> getFTEListTotal(LocalDate current_month_startDate, LocalDate current_Month_Enddate,int country_Id);
	public List<Employee> getMissingData(String searchType , int country_id);
	public List<Employee> getOrphanList(int country_id, String res_status);
	public List<Employee> getEmpNameByCorpId(String corpId);
	public List<Employee> getReplacementEmployees(int country_id);
	public List<Employee> emailForIndiaEmployee(int country_id, int bis_id, int location, String corpId);
	
	public boolean updatePSAID(Employee emp);
	public Employee getEmpForRmPMO(Long id);
	public BisPMOMap getBISFromPMO(int bisId);
	public Spoc getSpocFromCountry(Integer countryId);
	public List<Employee> getOffboardingEmpList(Date d);
	String bememail(int id);
	public List<RollOffType> getRollOffTypeList();
	public List<ReplacementType> getReplacementTypeList();
	public ReplacementType getReplacementTypeById(int id);
	public RollOffType getRollOffTypeById(int id);
	public List<Employee> getActiveResourcesList();
	public String emailId(String corpid);
	public Long buddyId(String corpId);
	Employee buddyIds(String mailid);
	public String corpId(String sps);
	public List<Employee> listBuddyEmp();//mehens-buddy
	List<Employee> getBISActiveEmpList(int bisId);//mehens - BIS
	public List<Employee> listEm(int pp);//Engg
	public void updateGithub(Employee emp);//mehens-github
}
