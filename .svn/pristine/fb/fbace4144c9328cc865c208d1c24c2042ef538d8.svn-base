package com.capgemini.onboarding.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.capgemini.onboarding.GenericExcel;
import com.capgemini.onboarding.preOnboardingController;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dao.EmployeeDAO;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.HeadCountReport;
import com.capgemini.onboarding.model.ReplacementType;
import com.capgemini.onboarding.model.RollOffType;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.model.Users;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;

	@Override
	@Transactional
	public void addEmployee(Employee p) {
		this.employeeDAO.addEmployee(p);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee p) {
		employeeDAO.updateEmployee(p);
	}
	
	@Override
	@Transactional
	public Employee getEmployeeById(Long id) {
		return this.employeeDAO.getEmployeeById(id);
	}
	
	@Override
	@Transactional
	public Employee getEmployee(Long id) {//mehens
		return this.employeeDAO.getEmployee(id);
	}

	@Override
	@Transactional
	public void removeEmployee(int id) {
		this.employeeDAO.removeEmployee(id);
	}

	@Override
	@Transactional
	public List<Employee> listManagers() {
		return employeeDAO.listManagers();
	}

	@Override
	@Transactional
	public List<Employee> getEmployeesByGrade(List<Integer> gradeList) {
		return this.employeeDAO.getEmployeesByGrade(gradeList);
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		return this.employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public List<Employee> searchEmployees(Employee emp) {
		return employeeDAO.searchEmployees(emp);
	}

	@Override
	@Transactional
	public void offboardEmployee(Date endDate, Long id , Employee e, Employee emp) {
		employeeDAO.offboardEmployee(endDate, id, e, emp);		
	}
	
	@Transactional
	public List<Employee> modifyEmployeeList(List<Employee> empList) {
		List<Employee> newEmpList = new ArrayList<>();
		for(Employee emp : empList) {
			String startDate = null;
			String endDate = null;
			String actualEndDate = null;
			String shadowDate = null;
			String billingStartDT = null;
			if (emp.getJoiningDate() != null) {
			startDate = OnboardingConstants.DATE_FORMAT.format(emp.getJoiningDate());
			}
			if (emp.getEndDate() != null) {
			endDate = OnboardingConstants.DATE_FORMAT.format(emp.getEndDate());
			}
			if (emp.getActualEndDate() != null) {
				actualEndDate = OnboardingConstants.DATE_FORMAT.format(emp.getActualEndDate());
			}
			if(emp.getBillingDate() != null) {
				billingStartDT = OnboardingConstants.DATE_FORMAT.format(emp.getBillingDate());
			}
			
			if(emp.getSecurityTraining() != null && emp.getSecurityTraining().equals(Boolean.TRUE)) {
				emp.setIsTrainingCompleted(OnboardingConstants.TRAINING_COMPLETED);
			}else{
				emp.setIsTrainingCompleted(OnboardingConstants.TRAINING_NOT_COMPLETED);
			}
			emp.setActualOffboardingDate(actualEndDate);
			emp.setOnboardingDate(startDate);
			emp.setOffboardingDate(endDate);
			emp.setBillingStartDate(billingStartDT);
			

			if(emp.getRepCorpId()!=null && !emp.getRepCorpId().equalsIgnoreCase("")){
				String corpId = emp.getRepCorpId();
				List<Employee> replacementEmpDetails = employeeDAO.getEmpNameByCorpId(corpId);
		        if(replacementEmpDetails != null && replacementEmpDetails.size() > 0){
		        	  emp.setRepfirstName(replacementEmpDetails.get(0).getFirstName() + " " + replacementEmpDetails.get(0).getLastName()) ;
		        }
			}
			
			newEmpList.add(emp);
		}
		return newEmpList;
	}
	
	public List<Employee> convertObjectToEmpList(List<Employee> empList) {
		
		
		return empList;
		
	}

	@Override
	@Transactional
	public List<Employee> searchEmployeeReport(Employee emp, Integer limit) {

		return employeeDAO.searchEmployeeReport(emp, limit);
	}
	
	@Override
	@Transactional
	public List<Employee> searchEmpReportWithoutPlannedOffBoardDate(Employee emp, Integer limit) {

		return employeeDAO.searchEmpReportWithoutPlannedOffBoardDate(emp, limit);
	}

	@Override
	@Transactional
	public boolean checkEmployeeExists(String empId , String empType) {
		return employeeDAO.checkEmployeeExists(empId , empType);
	}

	@Override
	@Transactional
	public Employee checkArchivedEmployeeExists(String corpId) {
		return employeeDAO.checkArchivedEmployeeExists(corpId);
	}
	
	
	
	@Override
	@Transactional
	public List<Employee> getAllEmployeesByCountry() {
		return this.employeeDAO.getAllEmployeesCountryWise();
	}

	@Override
	@Transactional
	public List<Employee> getInternalEmployeesCountryWise() {
		return this.employeeDAO.getInternalEmployeesCountryWise();
	}

	@Override
	@Transactional
	public List<Employee> getExternalEmployeesCountryWise() {
		return this.employeeDAO.getExternalEmployeesCountryWise();
	}

	@Override
	@Transactional
	public List<Employee> getDashBoardList() {
		return this.employeeDAO.getDashBoardList();
	}

	@Override
	@Transactional
	public List<Employee> getActiveEmployeesCountryWise() {
		return this.employeeDAO.getActiveEmployeesCountryWise();
	}

	@Override
	@Transactional
	public List<Employee> getActiveInternalEmployeesCountryWise() {
		return this.employeeDAO.getActiveInternalEmployeesCountryWise();
	}

	@Override
	@Transactional
	public List<Employee> getActiveExternalEmployeesCountryWise() {
		return this.employeeDAO.getActiveExternalEmployeesCountryWise();
	}

	@Override
	@Transactional
	public List<Employee> getDashBoardActiveEmpList() {
		return this.employeeDAO.getDashBoardActiveEmpList();
	}
	
	@Override
	@Transactional
	public List<Employee> getBISActiveEmpList(int bisId){//mehens - BIS
		return this.employeeDAO.getBISActiveEmpList(bisId);
	}

	@Override
	@Transactional
	public List<Employee> searchOffboardReport(Employee emp) {
		return this.employeeDAO.searchOffboardReport(emp);
	}

	@Override
	@Transactional
	public List<Employee> getPyramidChart(Integer countryId) {
		return this.employeeDAO.getPyramidChart(countryId);
	}

	@Override
	@Transactional
	public List<Employee> listManagersByBundleEm() {
		
		return this.employeeDAO.listManagersByBundleEm();
	}
	
	@Override
	@Transactional
	public List<Employee> listBuddyEmp() {//mehens-buddy
		return this.employeeDAO.listBuddyEmp();
	}

	@Override
	@Transactional
	public List<Employee> listManagersByEm() {
		
		return this.employeeDAO.listManagersByEm("");
	}

	/*@Override
	@Transactional
	public List<Users> getAllEM() {
		return this.employeeDAO.getAllEM();
	}*/

	@Override
	@Transactional
	public Employee getEmployeeByCorpId(String corpID) {
		return this.employeeDAO.getEmployeeByCorpId(corpID);
	}
	
	//new method for logged user
	@Override
	@Transactional
	public Employee getActiveEmployeeByCorpId(String corpID) {
		return this.employeeDAO.getActiveEmployeeByCorpId(corpID);
	}

	@Override
	@Transactional
	public List<Employee> getOffBoardingListForNext30Days(Employee emp) {
		return this.employeeDAO.getOffBoardingListForNext30Days(emp);
	}

	@Override
	@Transactional
	public List<Employee> listOffshoreEmIndia() {
		return this.employeeDAO.listOffshoreEmIndia();
	}

	@Override
	@Transactional
	public List<Float> headCountList(LocalDate current_month_startDate_local , LocalDate current_Month_Enddate__local ,  int country_Id) {
		return employeeDAO.headCountList(current_month_startDate_local , current_Month_Enddate__local , country_Id);
	}

	@Override
	@Transactional
	public List<Employee> getFTEList(LocalDate current_month_startDate_local, LocalDate current_Month_Enddate__local,int country_Id) {
		return employeeDAO.getFTEList(current_month_startDate_local, current_Month_Enddate__local, country_Id);
	}

	@Override
	@Transactional
	public List<Employee> getFTEListCountryWise(LocalDate current_month_startDate, LocalDate current_Month_Enddate,
			int country_Id) {
		return employeeDAO.getFTEListCountryWise(current_month_startDate, current_Month_Enddate, country_Id);
	}

	@Override
	@Transactional
	public List<Employee> getFTEListTotal(LocalDate current_month_startDate, LocalDate current_Month_Enddate,
			int country_Id) {
		return employeeDAO.getFTEListTotal(current_month_startDate, current_Month_Enddate, country_Id);
	}

	@Override
	@Transactional
	public boolean checkCorpIdExists(String corpId,String empId,String empType,String pageType) {
		return employeeDAO.checkCorpIdExists(corpId,empId,empType,pageType);
	}

	@Override
	@Transactional
	public boolean checkPsaIDExists(String psaId,String empId,String empType) {
		return employeeDAO.checkPsaIDExists(psaId,empId,empType);
	}

	@Override
	@Transactional
	public boolean checkEmployeeEmailExists(String email,String empId,String empType) {
		return employeeDAO.checkEmployeeEmailExists(email,empId,empType);
	}

	@Override
	@Transactional
	public List<Employee> getMissingData(String searchType , int country_id) {
		return employeeDAO.getMissingData(searchType,country_id);
	}

	@Override
	@Transactional
	public List<Employee> getOrphanList(int country_id, String res_status) {
		return employeeDAO.getOrphanList(country_id, res_status);
	}

	@Override
	@Transactional
	public List<Employee>  getEmpNameByCorpId(String corpId) {
		return employeeDAO.getEmpNameByCorpId(corpId);
	}

	@Override
	@Transactional
	public List<Employee> getReplacementEmployees(int country_id) {
	  return employeeDAO.getReplacementEmployees(country_id);
	}

	@Override
	@Transactional
	public List<Employee> emailForIndiaEmployee(int country_id, int bis_id, int location, String corpId) {
		
		return employeeDAO.emailForIndiaEmployee(country_id, bis_id, location,corpId);
	}

	@Override
	@Transactional
	public BisPMOMap getBISFromPMO(int bisId) {
	
		return employeeDAO.getBISFromPMO(bisId);
		
	}
	
	@Transactional
	@Override
	public boolean updatePSAID(Employee emp){
		return this.employeeDAO.updatePSAID(emp);	
	}
	
	@Transactional
	@Override
	public void updateGithub(Employee emp){
		this.employeeDAO.updateGithub(emp);	
	}
	
	@Transactional
	@Override
	public Employee getEmpForRmPMO(Long id) {
		
		Employee employee = employeeDAO.getEmpForRmPMO(id);	
		
		/*int phase = employee.getPhase();

        A String represents a string in the UTF-16 formatin which supplementary characters are represented by surrogatepairs (see the section UnicodeCharacter Representations in the Character class formore information).Index values refer to char code units, so a supplementarycharacter uses two positions in a String. 

		if(phase==1){
			employee.setPhasevalue("AppsTwo Delivery");
		}
		if(phase==2){
			employee.setPhasevalue("Transition");
		}
		if(phase==3){
			employee.setPhasevalue("AppsTwo Governance");
		}
		if(phase == 4){
			employee.setPhasevalue("Loaned-In");
		}*/
	
		int heritgTypevlaue = employee.getHeritageType();
		if(heritgTypevlaue==1){
			employee.setHeritageValue("Capgemini");;
		}
		else if(heritgTypevlaue==2){
			employee.setHeritageValue("FR HERMES");
		}
		else if(heritgTypevlaue==3){
			employee.setHeritageValue("Others");
		}
		
		boolean decisionValByGPVzalue = employee.isDecisionValByGP();
		if(decisionValByGPVzalue)
		{
			employee.setDecisionVal("Yes");
		}else{
			employee.setDecisionVal("No");
		}
		
		return employee;	
	}

	@Transactional
	@Override
	public Spoc getSpocFromCountry(Integer countryId) {
		
		return this.employeeDAO.getSpocFromCountry(countryId);
	}

	@Transactional
	@Override
	public List<Employee> getOffboardingEmpList(Date d) {
		
		return this.employeeDAO.getOffboardingEmpList(d);
	}

	@Override
	public List<Employee> getBillableEmployeesCountryWise() {
		
		return this.employeeDAO.getBillableEmployeesCountryWise();
	}

	@Override
	public List<Employee> getBillableInternalEmployeesCountryWise() {
		
		return this.employeeDAO.getBillableInternalEmployeesCountryWise();
	}

	@Override
	public List<Employee> getBillableExternalEmployeesCountryWise() {
		
		return this.employeeDAO.getBillableExternalEmployeesCountryWise();
	}

	@Override
	public List<Employee> getDashBoardBillableEmpList() {
		
		return this.employeeDAO.getDashBoardBillableEmpList();
	}

	@Override
	public List<RollOffType> getRollOffTypeList() {
		
		return this.employeeDAO.getRollOffTypeList();
		
	}

	@Override
	public List<ReplacementType> getReplacementTypeList() {
		
		return this.employeeDAO.getReplacementTypeList();
	}

	@Override
	public ReplacementType getReplacementTypeById(int id) {
		
		return this.employeeDAO.getReplacementTypeById(id);
	}

	@Override
	public RollOffType getRollOffTypeById(int id) {
		
		return this.employeeDAO.getRollOffTypeById(id);
	}

	@Override
	public List<Employee> getActiveResourcesList() {
		// TODO Auto-generated method stub
		return this.employeeDAO.getActiveResourcesList();
	}

	@Override
	@Transactional
	public List<Employee> empReportFiltering(Employee emp, Integer limit){//old 
		
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
		
		String resStatus = null;
		if(emp.getResourceStatus()!=null){
			resStatus = emp.getResourceStatus();
			
		}
		
		List<Employee> employeeSearchList = this.searchEmployeeReport(emp, limit);
		
		//Checking radioButton(Internal,External,Active,AllResources) and countryName.
		
		String dynamicHeadingValue = "Employee List ";
        if((country_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")) ||(country_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")) ){
        	dynamicHeadingValue = dynamicHeadingValue  + " ( " +country_Name  + " - " + resStatus +")   ";
	    }
       
        else if(country_id!=0){
        	dynamicHeadingValue = dynamicHeadingValue  + " ( " + country_Name + " - " + empType + " - " + resStatus +")   ";
		}
		else if(empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")){
				
			dynamicHeadingValue = dynamicHeadingValue  + " ( " + resStatus +")   ";
		}
		else if(empType!= null || resStatus!=null){
			dynamicHeadingValue = dynamicHeadingValue  + " ( " + empType + " - " + resStatus +")   ";
		}
		
		
		
		List<Employee> empList = this.modifyEmployeeList(employeeSearchList);
		/*for(int i=0; i<empList.size(); i++)
		{
			if(empList.get(i).getOnboardingEmail()!=null)
			{
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(empList.get(i).getOnboardingEmail());
				empList.get(i).setBuddy(employessss);
			}
		}*/
		
		return empList;
		
	}
	
	@Override
	@Transactional
	public void resourceReportFn(Employee emp, String filename, String excelFilePath) {
		
		try {
			
			List<Employee> empList = empReportFilteringNew(emp,null);
			logger.info("emplist : "+empList.size());
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			Sheet sheet = workbook.createSheet("Sheet1");
	        GenericExcel<Employee> g = new GenericExcel<Employee>();
	        
	        Class T = Class.forName("com.capgemini.onboarding.model.Employee");
	        String[] columnList = {"Primary Program","Emp ID",	"GG ID","Corp ID",	"PSA ID","VM Number","Emp Type","Emp First Name", "Emp Last Name","Country","Location","Email","Capgemini Entity","Primary Skill","Role","Vendor","Grade","Global Grade","Manager","Manager Email","INDUS GOAL","Staff JIRA","Staffing SO","On-boarding Date(YYYY-MM-DD)","Billing Date(YYYY-MM-DD)","Planned Off-boarding Date(YYYY-MM-DD)","Actual Off-boarding Date(YYYY-MM-DD)","Bundle EM","EM","Offshore EM","BIS","Resource Manager","Type Of demand","Replacement Required","Reason for Roll Off","Reason for No Replacement","Replacement Corp ID","Replacement Employee Name","PSA ID Req.","PSA ID Gen.","Upper V Cycle","Time & Material","Offboard Comment","EM Submit Date","RM Submit Date","BIS PMO Submit Date","RM PMO Submit Date","Gender","Capgemini Manager","Capgemini Manager Email","Capgemini Supervisor","Capgemini Supervisor Email","Allocation","Working BIS","Project","Replacement JIRA","Criticality","Secondary Skill","VCO","GitHub Copilot","Onboarding Buddy","Onboarding Buddy Email","PC Hostname","PCA Email","Comment"};
	        //"Core Team"}; //removed HRA Request and added INDUS GOAL
	        //,"Onboarding Buddy","Onboarding Buddy Mail"};
	        logger.info("before header");
	        g.writeHeaderLine(sheet,T,columnList);
	        logger.info("after header");
	        g.writeDataLines(empList, workbook, sheet, T);
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
	
	@Override
	@Transactional
	public boolean deleteReportFileFromDisk(String filePathName ) {
		
		File metricsReportFile = new File (filePathName);
		if(metricsReportFile.delete()) {
			logger.info("After File delete true");
			return true;
		}else {
			return false;
		}
	}
	@Override
	public String emailId(String  corpid) {
		
		return this.employeeDAO.emailId(corpid);
	}
	
	@Override
	@Transactional
	public List<Employee> empReportFilteringNew(Employee emp, Integer limit){ //new 
		
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
		
		String resStatus = null;
		if(emp.getResourceStatus()!=null){
			resStatus = emp.getResourceStatus();
			
		}
		
		//List<Employee> employeeSearchList = this.searchEmployeeReport(emp, limit);
		List<Employee> employeeSearchList = this.searchEmpReportWithoutPlannedOffBoardDate(emp, limit);//mehens-new
		
		//Checking radioButton(Internal,External,Active,AllResources) and countryName.
		
		String dynamicHeadingValue = "Employee List ";
        if((country_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")) ||(country_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")) ){
        	dynamicHeadingValue = dynamicHeadingValue  + " ( " +country_Name  + " - " + resStatus +")   ";
	    }
       
        else if(country_id!=0){
        	dynamicHeadingValue = dynamicHeadingValue  + " ( " + country_Name + " - " + empType + " - " + resStatus +")   ";
		}
		else if(empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")){
				
			dynamicHeadingValue = dynamicHeadingValue  + " ( " + resStatus +")   ";
		}
		else if(empType!= null || resStatus!=null){
			dynamicHeadingValue = dynamicHeadingValue  + " ( " + empType + " - " + resStatus +")   ";
		}
		
		
		
		List<Employee> empList = this.modifyEmployeeList(employeeSearchList);
		//commenting for test
		/*for(int i=0; i<empList.size(); i++)
		{
			if(empList.get(i).getOnboardingEmail() !=null && empList.get(i).getOnboardingEmail()!="" &&!(empList.get(i).getOnboardingEmail().isEmpty()))
			{
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(empList.get(i).getOnboardingEmail());
				empList.get(i).setBuddy(employessss);
			}
		}*/
		
		return empList;
		
	}
	
	@Override
	@Transactional
     public void resourceReportFunctn(Employee emp, String filename, String excelFilePath) {//new mehens
		
		try {
			
			List<Employee> empList = empReportFiltering(emp,null);
			logger.info("emplist : "+empList.size());
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			Sheet sheet = workbook.createSheet("Sheet1");
	        GenericExcel<Employee> g = new GenericExcel<Employee>();
	        
	        Class T = Class.forName("com.capgemini.onboarding.model.Employee");
	        String[] columnList = {"Primary Program","Emp ID",	"GG ID","Corp ID",	"PSA ID","VM Number","Emp Type","Emp First Name", "Emp Last Name","Country","Location","Email","Capgemini Entity","Primary Skill","Role","Vendor","Grade","Global Grade","Manager","Manager Email","INDUS GOAL","Staff JIRA","Staffing SO","On-boarding Date(YYYY-MM-DD)","Billing Date(YYYY-MM-DD)","Planned Off-boarding Date(YYYY-MM-DD)","Actual Off-boarding Date(YYYY-MM-DD)","Bundle EM","EM","Offshore EM","BIS","Resource Manager","Type Of demand","Replacement Required","Reason for Roll Off","Reason for No Replacement","Replacement Corp ID","Replacement Employee Name","PSA ID Req.","PSA ID Gen.","Upper V Cycle","Time & Material","Offboard Comment","EM Submit Date","RM Submit Date","BIS PMO Submit Date","RM PMO Submit Date","Gender","Capgemini Manager","Capgemini Manager Email","Capgemini Supervisor","Capgemini Supervisor Email","Allocation","Working BIS","Project","Replacement JIRA","Criticality","Secondary Skill","VCO","GitHub Copilot","Onboarding Buddy","Onboarding Buddy Email","PC Hostname","PCA Email","Comment"};
	        //"Core Team"}; //removed HRA Request and added INDUS GOAL 
	        //,"Onboarding Buddy","Onboarding Buddy Mail"};
	        logger.info("before header");
	        g.writeHeaderLine(sheet,T,columnList);
	        logger.info("after header");
	        g.writeDataLines(empList, workbook, sheet, T);
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

	@Override
	@Transactional
	public Employee getEmployeeByEmail(String email) {
			return this.employeeDAO.getEmployeeByEmail(email);
		
	}
	

}
