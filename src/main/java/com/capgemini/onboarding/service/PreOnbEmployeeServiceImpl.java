package com.capgemini.onboarding.service;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.GenericExcel;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dao.EmployeeDAO;
import com.capgemini.onboarding.dao.PreOnbEmployeeDAO;
import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.JiraIDCreator;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.model.Users;

@Service
public class PreOnbEmployeeServiceImpl implements PreOnbEmployeeService{

	private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private PreOnbEmployeeDAO preOnbEmployeeDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public void addPreOnbEmployee(PreOnbEmployee p) {
		this.preOnbEmployeeDAO.addPreOnbEmployee(p);
		
	}

	@Override
	@Transactional
	public void updatePreOnbEmployee(PreOnbEmployee p) {
		this.preOnbEmployeeDAO.updatePreOnbEmployee(p);
	}

	@Override
	@Transactional
	public PreOnbEmployee getPreOnbEmployeeDAOById(Long id) {
		
		return this.preOnbEmployeeDAO.getPreOnbEmployeeById(id);
	}

	@Override
	@Transactional
	public List<Employee> listManagers() {
		
		return this.preOnbEmployeeDAO.listManagers();
	}

	@Override
	@Transactional
	public List<Employee> listManagersNew(int pp) {//Engg
		
		return this.employeeDAO.listEm(pp);
	}
	
	@Override
	@Transactional
	public List<Employee> listManagersByBundleEm() {
		// TODO Auto-generated method stub
		return this.preOnbEmployeeDAO.listManagersByBundleEm();
	}

	@Override
	@Transactional
	public List<Employee> listBuddyEmp() {//mehens-buddy
		return this.preOnbEmployeeDAO.listBuddyEmp();
	}

	
	@Override
	@Transactional
	public List<Employee> listManagersByEm() {
		
		return this.employeeDAO.listManagersByEm("Active");
	}
	
	
	@Override
	@Transactional
	public List<Employee> listEm(int pp) {//Engg
		
		return this.employeeDAO.listEm(pp);
	}
	

	@Override
	@Transactional
	public List<PreOnbEmployee> getPreOnbEmployeeByGrade(List<Integer> gradeList) {
		
		return this.preOnbEmployeeDAO.getPreOnbEmployeeByGrade(gradeList);
	}

	@Override
	@Transactional
	public List<PreOnbEmployee> getAllPreOnbEmployee() {
		
		return this.preOnbEmployeeDAO.getAllPreOnbEmployee();
	}

	@Override
	@Transactional
	public boolean checkCorpIdExistsInPreOnb(String corpId, String empId, String empType, String pageType) {
		
		return this.preOnbEmployeeDAO.checkCorpIdExists(corpId, empId, empType, pageType);
	}

	@Override
	@Transactional
	public boolean checkEmployeeEmailExists(String email, String empId, String empType) {
		
		return this.preOnbEmployeeDAO.checkEmployeeEmailExists(email, empId, empType);
	}

	/*@Override
	@Transactional
	public List<Users> getAllEM() {
		
		return this.preOnbEmployeeDAO.getAllEM();
	}*/

	@Override
	@Transactional
	public PreOnbEmployee getPreOnbEmployeeByCorpId(String corpID) {
		
		return this.preOnbEmployeeDAO.getPreOnbEmployeeByCorpId(corpID);
	}

	@Override
	public List<PreOnbEmployee> getAllEmployees() {
		
		return this.preOnbEmployeeDAO.getAllEmployees();
	}

	@Transactional
	public List<PreOnbEmployee> modifyEmployeeList(List<PreOnbEmployee> empList) {
		
		List<PreOnbEmployee> newEmpList = new ArrayList<>();
		for(PreOnbEmployee emp : empList) {
			String startDate = null;
			String endDate = null;
			String actualEndDate = null;
			String billingate = null;
			String dob = null;

			String shadowDate = null;
			if (emp.getJoiningDate() != null) {
			startDate = OnboardingConstants.DATE_FORMAT.format(emp.getJoiningDate());
			}
			if (emp.getEndDate() != null) {
			endDate = OnboardingConstants.DATE_FORMAT.format(emp.getEndDate());
			}
			if(emp.getBillingDate() != null) {
				billingate = OnboardingConstants.DATE_FORMAT.format(emp.getBillingDate());
			}
			/*if(emp.getDobDate() != null) {
			dob = OnboardingConstants.DATE_FORMAT.format(emp.getDobDate());
			}*/
		
			if(emp.getDob() != null) {
				dob = OnboardingConstants.DATE_FORMAT.format(emp.getDob());
			}

			
			emp.setOnboardingDate(startDate);
			emp.setOffboardingDate(endDate);
			emp.setBillingStartDate(billingate);
			emp.setDobDate(dob);
			
			newEmpList.add(emp);
		}
		return newEmpList;
	}

	@Override
	@Transactional
	public Employee getResourceMgrEmpRec(Integer resourceMgrId) {
		
		return this.preOnbEmployeeDAO.getResourceMgrEmpRec(resourceMgrId);
	}

	@Override
	@Transactional
	public boolean checkPreOnbEmployeeExists(String empId, String empType) {
		
		return this.preOnbEmployeeDAO.checkPreOnbEmployeeExists(empId, empType);
	}

	//Mohini added start
		@Override
		@Transactional
		public List<PreOnbEmployee> preOnboardingSearch(Employee emp,  String roleId) {
			List<PreOnbEmployee> empList = preOnbEmployeeDAO.preOnboardingSearch(emp, roleId);
		
			for(PreOnbEmployee preOnEmp : empList) {
				String startDate = null;
				String endDate = null;
				String billingate = null;
				String dob = null;
				
				if (preOnEmp.getJoiningDate() != null) {
					startDate = OnboardingConstants.DATE_FORMAT.format(preOnEmp.getJoiningDate());
				}
				if (preOnEmp.getEndDate() != null) {
					endDate = OnboardingConstants.DATE_FORMAT.format(preOnEmp.getEndDate());
				}
				if(preOnEmp.getBillingDate() != null) {
					billingate = OnboardingConstants.DATE_FORMAT.format(preOnEmp.getBillingDate());
				}
				if(preOnEmp.getDobDate() != null) {
					dob = OnboardingConstants.DATE_FORMAT.format(preOnEmp.getDobDate());
				}
				preOnEmp.setOnboardingDate(startDate);
				preOnEmp.setOffboardingDate(endDate);
				preOnEmp.setBillingStartDate(billingate);
				preOnEmp.setDobDate(dob);
			}
		
			return empList;
		}
		
		@Override
		public Employee getEmpFromPreOnboarding(String corpId) {
			return this.preOnbEmployeeDAO.getEmpFromPreOnboarding(corpId);	
		}
		
		@Transactional
		@Override
		public void insertEmployee(Employee emp){
			this.preOnbEmployeeDAO.insertEmployee(emp);	
		}
	//Mohini added end

		@Override
		public PreOnbEmployee getPreOnbEmployee(String corpId) {
			
			return this.preOnbEmployeeDAO.getPreOnbEmployee(corpId);
		}

		
		
		
		@Transactional
		@Override
		public RmPMO getRmPmoDetails() {
			return this.preOnbEmployeeDAO.getRmPmoDetails();	
		}

		@Transactional
		@Override
		public JiraIDCreator getJiraCreatorDetails() {
			return this.preOnbEmployeeDAO.getJiraCreatorDetails();	
		}

		@Override
		public List<Role> roleList(String username) {
			// TODO Auto-generated method stub
			return this.preOnbEmployeeDAO.roleList(username);
		}
		
		@Override
		public Boolean isFirstLogin(String username){
			return this.preOnbEmployeeDAO.isFirstLogin(username);
		}

		@Override
		public List<Role> roleList() {
			
			return this.preOnbEmployeeDAO.roleList();
		}

		@Transactional
		@Override
		public DLList getDLListbyRole(String role) {
			
			return this.preOnbEmployeeDAO.getDLListbyRole(role);
		}
		
		@Transactional
		@Override
		public List<DLList> getMultipleDLListbyRole(String role) {
			
			return this.preOnbEmployeeDAO.getMultipleDLListbyRole(role);
		}



		@Override
		public Employee getPreOnbEmployees(String corpId) {
			
			return this.preOnbEmployeeDAO.getPreOnbEmployees(corpId);
		}

		
		//new - report - start
		
				@Override
				@Transactional
				public List<PreOnbEmployee> searchPreOnbEmpReport(PreOnbEmployee emp, Integer limit) {

					return preOnbEmployeeDAO.searchPreOnbEmpReport(emp, limit);
				}
				
				
				@Override
				@Transactional
				public List<PreOnbEmployee> onbReqReportFilter(PreOnbEmployee emp, Integer limit){
					int country_id = 0;
					String country_Name =null;
					if(emp.getCountry()!=null){
						country_id  = emp.getCountry().getCountryId();
						country_Name =  emp.getCountry().getCountryName() ;
					}
					
					
					String empType = null;
					if(emp.getEmpType()!=null){
						empType = emp.getEmpType();
						
					}
					
					/*String resStatus = null;
					if(emp.getResourceStatus()!=null){
						resStatus = emp.getResourceStatus();
						
					}*/
					
					List<PreOnbEmployee> employeeSearchList = this.searchPreOnbEmpReport(emp, limit);
					
					
					String dynamicHeadingValue = "PreOnboarding Employee List ";
			        if(country_id!=0 && empType.equalsIgnoreCase("All")  ||(country_id!=0 && empType.equalsIgnoreCase("All")  ) ){
			        	dynamicHeadingValue = dynamicHeadingValue  + " ( " +country_Name +")   ";
				    }
			       
			        else if(country_id!=0){
			        	dynamicHeadingValue = dynamicHeadingValue  + " ( " + country_Name + " - " + empType + ")   ";
					}
					/*else if(empType.equalsIgnoreCase("All") ){
							
						dynamicHeadingValue = dynamicHeadingValue  + " ( " + resStatus +")   ";
					}*/
					else if(empType!= null ){
						dynamicHeadingValue = dynamicHeadingValue  + " ( " + empType + "    ";
					}
					
					
					
					List<PreOnbEmployee> empList = this.modifyEmployeeList(employeeSearchList);
			        //List<PreOnbEmployee> empList =employeeSearchList;
					
					return empList;
				}
				
				
				@Override
				@Transactional
				public void onbReqReport(PreOnbEmployee emp, String filename, String excelFilePath) {
					try {
						
						List<PreOnbEmployee> empList = onbReqReportFilter(emp,null);
						logger.info("PreOnbEmplist : "+empList.size());
						
						SXSSFWorkbook workbook = new SXSSFWorkbook();
						Sheet sheet = workbook.createSheet("Sheet1");
				        GenericExcel<PreOnbEmployee> g = new GenericExcel<PreOnbEmployee>();
				        
				        Class T = Class.forName("com.capgemini.onboarding.model.PreOnbEmployee");
				        String[] columnList = {"Primary_Program","Emp ID",	"GG ID","Corp_ID","Emp Type","Emp_First_Name", "Emp_Last_Name","Country","Location","Email","Capgemini Entity","Primary Skill","Role","Vendor","Grade","Global Grade","Manager","Manager Email","Staff JIRA","Staffing SO","On-boarding Date(YYYY-MM-DD)","Billing Date(YYYY-MM-DD)","Planned Off-boarding Date(YYYY-MM-DD)","Bundle EM","EM","BIS","Type Of demand","Time & Material","EM Submit Date","RM Submit Date","BIS PMO Submit Date","RM PMO Submit Date","Resource_Status","Gender","Allocation","Working BIS","Project","Criticality","Secondary Skill","VCO","Onboarding Buddy","Onboarding Buddy Email","PC Hostname","Comment","Requestor Email","Request for PSA ID","DOB","Request for VM","Technical/Development","Indus Goal","Role / Technology","Stellantis Order Giver","Business Application Services","CFAO Group","SDM (CAE)","Other Application Services"};

				        logger.info("before header");
				        g.writeHeaderLine(sheet,T,columnList);
				        logger.info("after header");
				        g.writeDataLinesPreOnb(empList, workbook, sheet, T);
				        logger.info("after cell update");
				        
				        FileOutputStream outputStream = new FileOutputStream(excelFilePath+filename);//(absolutePath);
				        logger.info("before workbook write");
				        workbook.write(outputStream);
				        outputStream.close();
				        logger.info("after workbook write & outputStream.close()");
				        workbook.close();
				        
				        logger.info("workbook close");
				        
						}
					catch(Exception e) {
						logger.info(e.getStackTrace());
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
				}

				//new - report - end

	
}
