package com.capgemini.onboarding.Scheduler;

import java.util.*;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.onboarding.activedirectory.ActiveDirectory;
import com.capgemini.onboarding.activedirectory.PCHostnameFromActiveDirectory;
import com.capgemini.onboarding.dto.EmployeeDTO;
import com.capgemini.onboarding.model.CGEntityDetail;
import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.service.CgEntityService;
import com.capgemini.onboarding.service.CountryService;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.GlobalGradeService;

public class DetailsUpdateScheduler {

	private Logger logger = Logger.getLogger(DetailsUpdateScheduler.class);
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private CgEntityService cgEntityService;
	
	@Autowired(required = true)
	private GlobalGradeService globalGradeService;
	
	@Autowired(required = true)
	private CountryService countryService;
	
	private List<Country> countryList;
	
	public void updateActiveResources() throws NamingException { // Pending close dir conn
		
		ActiveDirectory activeDirectory;
		
		this.countryList = this.countryService.listCountry();
		
		//activeDirectory = new ActiveDirectory("SVC-IN-HERMESMUM","Tdbj@3020","corp.capgemini.com");    // used for dtsum n all not working consistently // nikhil's team -- group it resource Satish Panchal.
		logger.info("Monthly CORP DIR Refresh" );
		activeDirectory = new ActiveDirectory("SVC-FR-HERMESACCOUNT","n9giejA8CY42Q4","corp.capgemini.com"); //Hermes20100

		String temp = "";

		NamingEnumeration<SearchResult> result;
		Employee updateEmp = null;
		EmployeeDTO employeeDTO = null;
		boolean updateRequired = false;
		List<Employee> activeList = this.employeeService.getActiveResourcesList();
		int iter = 0;
		logger.info("Count of active resources :"+activeList.size());
		for(Employee emp : activeList) {
			
			try {
			result = activeDirectory.searchUser(emp.getCorpId(),"username",null);
			logger.info("Checking corp id "+emp.getCorpId());
			if(result.hasMoreElements()) {
				SearchResult rs= (SearchResult)result.next();
				Attributes attrs = rs.getAttributes();
				if(attrs!=null){
					updateRequired = false;
					employeeDTO = new EmployeeDTO();
					
					if(attrs.get("givenname") != null && attrs.get("givenname").get(0) != null){ // FIRST NAME
						temp = attrs.get("givenname").get(0).toString();
						temp = temp.substring(temp.indexOf(":")+1).trim();
						if(!emp.getFirstName().equalsIgnoreCase(temp)) {
							employeeDTO.setFirstName(temp);
							updateRequired = true;
						}	
					}
					
					if(attrs.get("sn") != null && attrs.get("sn").get(0) != null){ // LAST NAME
						temp = attrs.get("sn").get(0).toString();
						temp = temp.substring(temp.indexOf(":")+1).trim();
						if(!emp.getLastName().equalsIgnoreCase(temp)) {
							employeeDTO.setLastName(temp);
							updateRequired = true;
						}
					}
					
					if(attrs.get("employeeNumber") != null && attrs.get("employeeNumber").get(0) != null){ //EMP ID
						temp = attrs.get("employeeNumber").get(0).toString();
						temp = temp.substring(temp.indexOf(":")+1).trim();
						if(!emp.getEmpId().equalsIgnoreCase(temp)) {
							employeeDTO.setEmpId(temp);	
							updateRequired = true;
						}	
					}
					
					if(attrs.get("capgemini-GlobalID") != null && attrs.get("capgemini-GlobalID").get(0) != null){ //GG ID
						temp = attrs.get("capgemini-GlobalID").get(0).toString();
						temp = temp.substring(temp.indexOf(":")+1).trim();
						if(!emp.getGgId().equalsIgnoreCase(temp)) {
							employeeDTO.setGgId(temp);
							updateRequired = true;
						}
							
					}
					
					if(attrs.get("capgemini-Grade") != null && attrs.get("capgemini-Grade").get(0) != null){  //GLOBAL GRADE
						temp = attrs.get("capgemini-Grade").get(0).toString();
						String gradeName = temp.substring(temp.indexOf(":")+1);
						
						if(this.globalGradeService.listGlobalGrades().contains(this.globalGradeService.getGlobalGradeByName(gradeName))) {
							if(!emp.getGlobalGrade().getName().matches(gradeName)) {
								employeeDTO.setGlobalGrade(this.globalGradeService.getGlobalGradeByName(gradeName).getGlobalGradeId());
								updateRequired = true;
							}
						}
						
					}
					
					//commented after country change requirement
					/*if(attrs.get("co") != null && attrs.get("co").get(0) != null){ // COUNTRY
						temp = attrs.get("co").get(0).toString();
						String countryName = temp.substring(temp.indexOf(":")+1);
						
						ListIterator<Country> itr = this.countryList.listIterator();
						Country countryChk = null;
						while(itr.hasNext()) {
							countryChk = itr.next();
							if(countryChk.getCountryName().matches(countryName) && !emp.getCountry().getCountryName().matches(countryName)) {
								
								employeeDTO.setCountry(countryChk);
								updateRequired = true;
								break;
							}
						}
					}*/
					
					if(attrs.get("capgemini-EntityLevel3") != null && attrs.get("capgemini-EntityLevel3").get(0) != null){ // CG ENTITY
						temp = attrs.get("capgemini-EntityLevel3").get(0).toString();
						temp = temp.substring(temp.indexOf(":")+1).trim();
						if(!emp.getCgEntity().equalsIgnoreCase(temp)) {
							employeeDTO.setCapgemEntity(temp);
							updateRequired = true;
						}
					}
					
					if(attrs.get("mail") != null && attrs.get("mail").get(0) != null){
						temp = attrs.get("mail").get(0).toString();
						temp = temp.substring(temp.indexOf(":")+1).trim();
						if(!emp.getEmail().equalsIgnoreCase(temp)) {
							employeeDTO.setEmail(temp);
							updateRequired = true;
						}
						
					}
					
					// CORP DIR call for capgemini manager
					
					if(attrs.get("manager") != null && attrs.get("manager").get(0) != null) {
						temp = attrs.get("manager").get(0).toString(); // first sub string from : then from = to get corp id
						temp=temp.substring(temp.indexOf("=")+1,temp.indexOf(",")).trim();
						String CorpDirManagerName = null;
						String CorpDirManagerEmail = null;
						Employee cgManager = this.employeeService.getEmployeeByCorpId(temp);
						if(cgManager != null) {
							CorpDirManagerName = cgManager.getFirstName()+" "+cgManager.getLastName();
							CorpDirManagerEmail = cgManager.getEmail();
						}
						else {
							// CORP DIR CALL
							NamingEnumeration<SearchResult> result1;
							result1 = activeDirectory.searchUser(temp,"username",null);
							if(result1.hasMoreElements()) {
								SearchResult rs1= (SearchResult)result1.next();
								Attributes attrs1 = rs1.getAttributes();
								if(attrs1!=null){
									if(attrs1.get("givenname") != null && attrs1.get("givenname").get(0) != null){ // FIRST NAME
										temp = attrs1.get("givenname").get(0).toString().substring(temp.indexOf(":")+1).trim();
										CorpDirManagerName = temp;
												
									}
									if(attrs1.get("sn") != null && attrs1.get("sn").get(0) != null){ // LAST NAME
										temp = attrs1.get("sn").get(0).toString().substring(temp.indexOf(":")+1).trim();
										CorpDirManagerName = CorpDirManagerName +" "+temp;
									}
									if(attrs1.get("mail") != null && attrs1.get("mail").get(0) != null){
										temp = attrs1.get("mail").get(0).toString().substring(temp.indexOf(":")+1).trim();
										CorpDirManagerEmail = temp;
									}
								}
							} // 2nd CORP DIR call ends
							
							
						}
						if(emp.getCapManager() == null) {
							employeeDTO.setManager(CorpDirManagerName);
							employeeDTO.setManagerEmail(CorpDirManagerEmail);
							updateRequired = true;
						}else if ( !emp.getCapManager().equalsIgnoreCase(CorpDirManagerName)) {
							employeeDTO.setManager(CorpDirManagerName);
							employeeDTO.setManagerEmail(CorpDirManagerEmail);
							updateRequired = true;
						}
					}
					
				}
				
			}
			if(updateRequired) { // Doubt send mail on change of country and global grade -- location and local grade will mismatch otherwise
				updateEmp = this.employeeService.getEmployeeById(emp.getId());
				updateEmp.setFirstName((employeeDTO.getFirstName() != null)? employeeDTO.getFirstName():updateEmp.getFirstName());
				updateEmp.setLastName((employeeDTO.getLastName() != null)? employeeDTO.getLastName():updateEmp.getLastName());
				updateEmp.setGgId((employeeDTO.getGgId() != null)? employeeDTO.getGgId():updateEmp.getGgId());
				updateEmp.setCgEntity((employeeDTO.getCapgemEntity() != null)? employeeDTO.getCapgemEntity():updateEmp.getCgEntity());
				updateEmp.setEmpId((employeeDTO.getEmpId() != null)? employeeDTO.getEmpId():updateEmp.getEmpId());
				updateEmp.setEmail((employeeDTO.getEmail() != null)? employeeDTO.getEmail():updateEmp.getEmail());
				logger.info("Country change commented");
				//updateEmp.setCountry((employeeDTO.getCountry() != null)?  employeeDTO.getCountry(): updateEmp.getCountry());
				updateEmp.setGlobalGrade((employeeDTO.getGlobalGrade() != null)?  this.globalGradeService.getGlobalGradeById(employeeDTO.getGlobalGrade()): updateEmp.getGlobalGrade());
				updateEmp.setCapManager((employeeDTO.getManager() != null)? employeeDTO.getManager() : updateEmp.getCapManager());
				updateEmp.setCapManagerEmail((employeeDTO.getManagerEmail() != null)? employeeDTO.getManagerEmail() : updateEmp.getCapManagerEmail());
				this.employeeService.updateEmployee(updateEmp);
				logger.info("CORP DIR Updates for corp id "+updateEmp.getCorpId());
				
			}
			else {
				logger.info("No CORP DIR Updates for corp id "+emp.getCorpId());
			}
			
			
		} //try end
		catch(Exception e) {
			logger.info("Exception while updating record "+emp.getCorpId());
		}
			iter++;
			logger.info("Iteration No :"+iter);
		}
		//close Active directory
		logger.info("Closing corp directory");
		activeDirectory.closeLdapConnection();
	}
	
	public void updatePCForActiveResources() throws NamingException {
		
		logger.info("Weekly PC Hostname CORP DIR Refresh"+System.currentTimeMillis());
		long curr =  System.currentTimeMillis();
		
		ActiveDirectory activeDirectory;
		activeDirectory = new ActiveDirectory("SVC-FR-HERMESACCOUNT","n9giejA8CY42Q4","corp.capgemini.com"); 
		PCHostnameFromActiveDirectory pcHostname = new PCHostnameFromActiveDirectory();
		
		String temp = "";
		int iter = 0;
		ArrayList<String> pcFromLdap = new ArrayList<String>(); 
		NamingEnumeration<SearchResult> result;
		boolean updateRequired = false;
		boolean invalidFlag = false;
		boolean res = false;
		Employee updateEmp = null;
		List<Employee> activeList = this.employeeService.getActiveResourcesList();
		logger.info("Count of active resources: "+activeList.size());
		
		for(Employee emp : activeList) {
			try {
				updateEmp = this.employeeService.getEmployee(emp.getId());//mehens
				result = activeDirectory.searchUser(emp.getCorpId(),"username",null);
				logger.info("Checking corp id "+emp.getCorpId());
				if(result.hasMoreElements()) {
					SearchResult rs= (SearchResult)result.next();
					Attributes attrs = rs.getAttributes();
					if(attrs!=null){
							updateRequired = false;
							invalidFlag = false;
							res = false;
							if(attrs.get("distinguishedName") != null && attrs.get("distinguishedName").get(0) != null){
								temp = attrs.get("distinguishedName").get(0).toString();
								logger.info("temp: "+temp);
								pcHostname.newConnection();
								pcFromLdap = pcHostname.getPcFromLDAP(temp);
								logger.info("PC List Size: "+pcFromLdap.size());
							}
						}
				}
				String existingPc = emp.getPcSerialNumber();
				res = pcFromLdap.stream().anyMatch(i -> i.equalsIgnoreCase(existingPc));
				
				//if(pcFromLdap.contains(emp.getPcSerialNumber())) {
				if(res) {
					logger.info("PC Hostname: "+emp.getPcSerialNumber()+" present in LDAP, No update required for "+emp.getCorpId());
				}
				else {
					if(pcFromLdap.size() == 1) {
						updateRequired = true;
					}
					else {
						invalidFlag = true;
					}
				}
				
				if(updateRequired) {
					logger.info("Existing PC of "+emp.getCorpId()+" is "+emp.getPcSerialNumber());
					updateEmp.setPcSerialNumber(pcFromLdap.get(0));
					this.employeeService.updateEmployee(updateEmp);
					logger.info("CORP DIR Updates for corp id "+updateEmp.getCorpId()+" PC: "+pcFromLdap.get(0));
				}
				else if (invalidFlag) {
					if(!(emp.getPcSerialNumber().contains("INVALID_"))) {
						String pc = "INVALID_" + emp.getPcSerialNumber();
						updateEmp.setPcSerialNumber(pc);
						//logger.info("Corp Id- "+emp.getCorpId()+" PC- "+pc);
						this.employeeService.updateEmployee(updateEmp);
						logger.info("INVALID prefix added for corp id "+updateEmp.getCorpId()+" PC: "+pc);
					}
					else
						logger.info("INVALID PC already present in employee table for corp id "+updateEmp.getCorpId());
				}
			}
			catch(Exception e) {
				logger.info("Exception while updating record "+emp.getCorpId());
			}
				iter++;
				logger.info("Iteration No :"+iter);
		}
		logger.info("end of updatePCForActiveResources method "+ System.currentTimeMillis());
		logger.info("Total time  "+( System.currentTimeMillis() - curr));
		
		logger.info("Closing corp directory");
		activeDirectory.closeLdapConnection();
	}
	
}
