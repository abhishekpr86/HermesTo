package com.capgemini.onboarding;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dto.DataInconsistenciesDTO;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.BisRotation;
import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.service.BisRotationService;
import com.capgemini.onboarding.service.BisService;
import com.capgemini.onboarding.service.CountryService;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.PreOnbEmployeeService;


@Controller
public class ReportController {

	private Logger logger = Logger.getLogger(ReportController.class);

	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private CountryService countryService;
	
	@Autowired(required = true)
	private BisService bisService;
	
	@Autowired(required = true)
	private BisRotationService bisRotationService;
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
	private HttpSession session;
	private String role_id;

	@RequestMapping(value = "/report/search", method = RequestMethod.POST)
	public String searchEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		

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
		int bis_id=0;
		String bis_Name =null;
		if(emp.getBis()!=null){
			bis_id  = emp.getBis().getBis_id();
			bis_Name =  emp.getBis().getBis_Name();
		
		}
		
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {	
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		}
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		
		//List<Employee> employeeSearchList = employeeService.searchEmployeeReport(emp, null); // Bhavna undo change
		List<Employee> employeeSearchList = employeeService.searchEmpReportWithoutPlannedOffBoardDate(emp, null);//mehens-new
		model.addAttribute("listCountry", this.countryService.listCountry());
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);

		
		//Checking radioButton(Internal,External,Active,AllResources) and countryName.
		
		String dynamicHeadingValue = "Employee List ";
        if(country_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")){
			model.addAttribute("DynamicHeadingValue",dynamicHeadingValue  + " ( " +country_Name  + " - " + resStatus +")   " );
	    }
       
        else if(country_id!=0){
			model.addAttribute("DynamicHeadingValue",dynamicHeadingValue  + " ( " + country_Name + " - " + empType + " - " + resStatus +")   " );
		}
        if(bis_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")){
			model.addAttribute("DynamicHeadingValue",dynamicHeadingValue  + " ( " +bis_Name  + " - " + resStatus +")   " );
	    }
       
        else if(bis_id!=0){
			model.addAttribute("DynamicHeadingValue",dynamicHeadingValue  + " ( " + bis_Name + " - " + empType + " - " + resStatus +")   " );
		}
		
		else if(empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")){
				
				model.addAttribute("DynamicHeadingValue",dynamicHeadingValue  + " ( " + resStatus +")   " );
		}
		else if(empType!= null || resStatus!=null){
				model.addAttribute("DynamicHeadingValue",dynamicHeadingValue  + " ( " + empType + " - " + resStatus +")   " );
		}
		

		List<Employee> empList = employeeService.modifyEmployeeList(employeeSearchList);
		
		
		//converting db id to String.
		for(int j=0; j < empList.size(); j++){/*
			int rollOffType = empList.get(j).getRollOffType();
			boolean replacementReq = empList.get(j).isReplacementRequired();
			int replacementType = empList.get(j).getReplacementType();
			
			if(rollOffType==0){
				empList.get(j).setRollOffTypeVal("");
			}
			if(rollOffType==1){
				empList.get(j).setRollOffTypeVal("End of work assignment");
			}
			if(rollOffType==2){
				empList.get(j).setRollOffTypeVal("Change in skills required");
			}
			if(rollOffType==3){
				empList.get(j).setRollOffTypeVal("Resignation");
			}
			if(rollOffType==4){
				empList.get(j).setRollOffTypeVal("Transitioned to Offshore");
			}
			if(rollOffType==5){
				empList.get(j).setRollOffTypeVal("Performance");
			}
			if(rollOffType==6){
				empList.get(j).setRollOffTypeVal("BGV / Abscondee");
			}
			if(rollOffType==7){
				empList.get(j).setRollOffTypeVal("Employee requested");
			}
			if(rollOffType==8){
				empList.get(j).setRollOffTypeVal("Rotation");
			}
			if(rollOffType==9){
				empList.get(j).setRollOffTypeVal("Hired as Capgemini resource");
			}
			if(rollOffType==10) {
				empList.get(j).setRollOffTypeVal("Maternity Leave");
			}
			if(rollOffType==11) {
				empList.get(j).setRollOffTypeVal("Medical reason");
			}
			if(rollOffType==12) {
				empList.get(j).setRollOffTypeVal("Probation De-confirm");
			}
			if(rollOffType==13) {
				empList.get(j).setRollOffTypeVal("End of assignment - COVID-19");
			}
			
			
			if(replacementReq){
				empList.get(j).setReplacementRequiredVal("Yes");
			}
				else{
					empList.get(j).setReplacementRequiredVal("No");
				}
			
			if(replacementType == 0){
				empList.get(j).setReplacementTypeVal("");
			}
			if(replacementType == 1){
				empList.get(j).setReplacementTypeVal("End of work assignment");
			}
			if(replacementType == 2){
				empList.get(j).setReplacementTypeVal("Change in skill");
			}
			if(replacementType == 3){
				empList.get(j).setReplacementTypeVal("Got mutualized within the team");
			}
			
			
		
		*/}
		
		for(int i=0 ; i <empList.size() ; i++){/*
			int heritgTypevlaue = empList.get(i).getHeritageType();
			boolean decisionValByGPVzalue = empList.get(i).isDecisionValByGP();
			if(decisionValByGPVzalue)
			{
				empList.get(i).setDecisionVal("Yes");
			}else{
				empList.get(i).setDecisionVal("No");
			}
			
			Bis bis = empList.get(i).getBis();
			if(bis!=null){
				int bisId = empList.get(i).getBis().getBis_id();
				if(bisId==1){
					bis.setBis_Name("");
					empList.get(i).setBis(bis);
				}
			}
			
			
			if(heritgTypevlaue==1){
				empList.get(i).setHeritageValue("Capgemini");;
			}
			else if(heritgTypevlaue==2){
				empList.get(i).setHeritageValue("FR HERMES");
			}
			else if(heritgTypevlaue==3){
				empList.get(i).setHeritageValue("Others");
			}
		*/}
		
		
		
		
		
		model.addAttribute("employeeSearchList", empList);
		if(employeeSearchList != null && employeeSearchList.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
		}
		return "report";
		//return "redirect:/employeeSearch";
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String employeeSearchList(Model model, HttpServletRequest request) {
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {		
			model.addAttribute("checkUserType", "ViewMode");
		}	
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			/*e.setUserManagement(true);*/
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			  
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		model.addAttribute("listCountry", this.countryService.listCountry());
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);
		model.addAttribute("employee", new Employee());		
		return "report";
	}
	
	
//new function to separate repeatitive code
	public List<Employee> empReportFiltering(Employee emp, Integer limit) {
		
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
		
		List<Employee> employeeSearchList = employeeService.searchEmployeeReport(emp, limit);
		
		//Checking radioButton(Internal,External,Active,AllResources) and countryName.
		
		String dynamicHeadingValue = "Employee List ";
        if(country_id!=0 && empType.equalsIgnoreCase("All") && resStatus.equalsIgnoreCase("AllResources")){
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
		
		List<Employee> empList = employeeService.modifyEmployeeList(employeeSearchList);
		
		//converting db id to String.
		/*for(int j=0; j < empList.size(); j++){
			int rollOffType = empList.get(j).getRollOffType();
			boolean replacementReq = empList.get(j).isReplacementRequired();
			int replacementType = empList.get(j).getReplacementType();
			
			if(rollOffType==0){
				empList.get(j).setRollOffTypeVal("");
			}
			if(rollOffType==1){
				empList.get(j).setRollOffTypeVal("End of work assignment");
			}
			if(rollOffType==2){
				empList.get(j).setRollOffTypeVal("Change in skills required");
			}
			if(rollOffType==3){
				empList.get(j).setRollOffTypeVal("Resignation");
			}
			if(rollOffType==4){
				empList.get(j).setRollOffTypeVal("Transitioned to Offshore");
			}
			if(rollOffType==5){
				empList.get(j).setRollOffTypeVal("Performance");
			}
			if(rollOffType==6){
				empList.get(j).setRollOffTypeVal("BGV / Abscondee");
			}
			if(rollOffType==7){
				empList.get(j).setRollOffTypeVal("Employee requested");
			}
			if(rollOffType==8){
				empList.get(j).setRollOffTypeVal("Rotation");
			}
			if(rollOffType==9){
				empList.get(j).setRollOffTypeVal("Hired as Capgemini resource");
			}
			if(rollOffType==10) {
				empList.get(j).setRollOffTypeVal("Maternity Leave");
			}
			if(rollOffType==11) {
				empList.get(j).setRollOffTypeVal("Medical reason");
			}
			if(rollOffType==12) {
				empList.get(j).setRollOffTypeVal("Probation De-confirm");
			}
			if(rollOffType==13) {
				empList.get(j).setRollOffTypeVal("End of assignment - COVID-19");
			}
			
			
			if(replacementReq){
				empList.get(j).setReplacementRequiredVal("Yes");
			}
				else{
					empList.get(j).setReplacementRequiredVal("No");
				}
			
			if(replacementType == 0){
				empList.get(j).setReplacementTypeVal("");
			}
			if(replacementType == 1){
				empList.get(j).setReplacementTypeVal("End of work assignment");
			}
			if(replacementType == 2){
				empList.get(j).setReplacementTypeVal("Change in skill");
			}
			if(replacementType == 3){
				empList.get(j).setReplacementTypeVal("Got mutualized within the team");
			}
			
			
		
		}
		
		for(int i=0 ; i <empList.size() ; i++){
			int heritgTypevlaue = empList.get(i).getHeritageType();
			boolean decisionValByGPVzalue = empList.get(i).isDecisionValByGP();
			if(decisionValByGPVzalue)
			{
				empList.get(i).setDecisionVal("Yes");
			}else{
				empList.get(i).setDecisionVal("No");
			}
			
			Bis bis = empList.get(i).getBis();
			if(bis!=null){
				int bisId = empList.get(i).getBis().getBis_id();
				if(bisId==1){
					bis.setBis_Name("");
					empList.get(i).setBis(bis);
				}
			}
			
			if(heritgTypevlaue==1){
				empList.get(i).setHeritageValue("Capgemini");;
			}
			else if(heritgTypevlaue==2){
				empList.get(i).setHeritageValue("FR HERMES");
			}
			else if(heritgTypevlaue==3){
				empList.get(i).setHeritageValue("Others");
			}
		} */
		
		return empList;
	}
	@RequestMapping(value = "/reportOffBoarding", method = RequestMethod.GET)
	public String offBoardingemployeeSearchList(Model model, HttpServletRequest request) {
		model.addAttribute("listCountry", this.countryService.listCountry());
		model.addAttribute("employee", new Employee());
		model.addAttribute("listRollOffType", this.employeeService.getRollOffTypeList());
		model.addAttribute("listReplacementType", this.employeeService.getReplacementTypeList());
		Employee emp = new Employee();
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		
		}
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM 
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		return "offBoardingReport";
	
	
	
	}
	

	
	@RequestMapping(value = "/dataInconsistencies/searchType", method = RequestMethod.POST)
	public String missingData(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,@ModelAttribute("employee") Employee emp) {
		
		String searchType = emp.getIssueType();
		int country_id = 0;
		String country_Name =null;
		if(emp.getCountry()!=null){
			country_id  = emp.getCountry().getCountryId();
			country_Name =  emp.getCountry().getCountryName() ;
		}
		Employee e = new Employee();
		model.addAttribute("employee", e);
		
		List<Employee> employeeSearchList = employeeService.getMissingData(searchType, country_id);
		List<Employee> missingdata = employeeService.modifyEmployeeList(employeeSearchList);
		
	    for(int i =0; i < missingdata.size(); i++){
			boolean reprequired = missingdata.get(i).isReplacementRequired();
			if(reprequired){
				missingdata.get(i).setReplacementRequiredVal("Yes");
			}else{
				missingdata.get(i).setReplacementRequiredVal("No");
			}
			
		}
		
		model.addAttribute("listCountry", this.countryService.listCountry());
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);

		model.addAttribute("missingdata", missingdata);
	
		if(missingdata != null && missingdata.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
		}
		if(country_id!=0){
			model.addAttribute("DynamicHeadingValue","Data Inconsistencies Report for Missing " + searchType.toUpperCase() +" ( " + country_Name +" ) ");
		}
		
		else{
			model.addAttribute("DynamicHeadingValue","Data Inconsistencies Report for Missing " + searchType.toUpperCase() );
		}
		
		model.addAttribute("CheckReportType","dataInconsistencyPage");
		
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
			}
		
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");
			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM 
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		return "dataInconsistency";
	
	}

	
	@RequestMapping(value = "/dataInconsistenciesByCountry", method = RequestMethod.GET)
	public @ResponseBody DataInconsistenciesDTO countryWise(			
			@RequestParam(value = "countryId", required = true) String issueType) {
		DataInconsistenciesDTO datainconsistenciesdto = new DataInconsistenciesDTO();
		
		
		List<Country> countryList = this.countryService.getCountryListOrderWise(); 
		
		if(issueType.equalsIgnoreCase("empId")){
			countryList.remove(0);
		}

		datainconsistenciesdto.setCountryList(countryList);
		return datainconsistenciesdto;

	}
	
	
	@RequestMapping(value = "/dataInconsistencies", method = RequestMethod.GET)
	public String DataInconsistenciesforId(Model model, HttpServletRequest request) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		
		model.addAttribute("CheckReportType","/dataInconsistencies");
		
		
		model.addAttribute("listCountry", this.countryService.listCountry());
		model.addAttribute("DynamicHeadingValue","Data Inconsistencies Report for Missing CORPID ");
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			e.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		}

		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			e.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");
		}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			e.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		/*	for(int i=0 ; i <empList.size() ; i++){
		int heritgTypevlaue = empList.get(i).getHeritageType();
		boolean decisionValByGPVzalue = empList.get(i).isDecisionValByGP();
		if(decisionValByGPVzalue)
		{
			empList.get(i).setDecisionVal("Yes");
		}else{
			empList.get(i).setDecisionVal("No");
		}*/
		
	
		
		
		return "dataInconsistency";
	
	}
	
	
	@RequestMapping(value = "/hc&fteDynamicReport/{id}/{month}/{process}", method = RequestMethod.GET)
	public String hcfteDynamicReport(@PathVariable("id") int id, @PathVariable("month") String month, @PathVariable("process") String process, Model model, HttpServletRequest request) throws ParseException {

		
		
			int selected_country_Id = id ;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate selected_month_startDate = LocalDate.parse("01"+"-"+month, dtf);
			LocalDate selected_month_endDate = selected_month_startDate.with(lastDayOfMonth());
			String checkInputType = null;
			String selectedCountryName = "";
			String header =  null ;
			Country selectedCountry  = new Country();
			 if(selected_country_Id != 0){
			    	selectedCountry = countryService.getCountryById(selected_country_Id);
			    	selectedCountryName  = selectedCountry.getCountryName();
			 }
			 
			List<Employee> fteMonthlyListCountryWise = new ArrayList<>();
			if(process.equalsIgnoreCase("countryWise")){
				if(selected_country_Id == 0){
					fteMonthlyListCountryWise = employeeService.getFTEListTotal(selected_month_startDate , selected_month_endDate , selected_country_Id );
					checkInputType = process;
					header = "All Country" ;
					model.addAttribute("checkInputType", checkInputType );
				}
				else {
				fteMonthlyListCountryWise = employeeService.getFTEListCountryWise(selected_month_startDate , selected_month_endDate , selected_country_Id );
				checkInputType = process;
				header = selectedCountryName ;
				model.addAttribute("checkInputType", checkInputType );
				}
			}
			else{
				 fteMonthlyListCountryWise = employeeService.getFTEList(selected_month_startDate , selected_month_endDate , selected_country_Id );
				 model.addAttribute("checkInputType", null );
				 header = selectedCountryName + " " +  month ;
			}
			
			
		    model.addAttribute("fteMonthlyListCountryWise",fteMonthlyListCountryWise); 
		    hcfteReport(model, request);		
		    
		String selectedMonth = selected_month_startDate.getMonth().name().substring(0, 3);
		String second_month = selected_month_startDate.plusMonths(1).getMonth().name().substring(0, 3);
		String third_month = selected_month_startDate.plusMonths(2).getMonth().name().substring(0, 3);
		String fourth_month = selected_month_startDate.plusMonths(3).getMonth().name().substring(0, 3);
		String fifth_month = selected_month_startDate.plusMonths(4).getMonth().name().substring(0, 3);
		String sixth_month = selected_month_startDate.plusMonths(5).getMonth().name().substring(0, 3);

		    model.addAttribute("hdList", header );
		    model.addAttribute("selectedMonth", selectedMonth );
		    model.addAttribute("second_month", second_month );
		    model.addAttribute("third_month", third_month );
		    model.addAttribute("fourth_month", fourth_month );
		    model.addAttribute("fifth_month", fifth_month );
		    model.addAttribute("sixth_month", sixth_month );
		
		return "hcFteReport";
	}
	
	
	
	@RequestMapping(value = "/bisRotationReport/search", method = RequestMethod.POST)
	public String bisRotationReportbySearch(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {	
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		}
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM  
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		LocalDate date1 = null;
		LocalDate date2 = null;
		if(emp.getJoiningDate() != null) {
			date1 = Instant.ofEpochMilli(emp.getJoiningDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		}
		if(emp.getActualEndDate() != null) {
			date2 = Instant.ofEpochMilli(emp.getActualEndDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		}
		//LocalDate date2 = emp.getActualEndDate().toInstant().atZone(ZoneId.systemDefault().toLocalDate();
		List<BisRotation> bisRotationList = this.bisRotationService.getBisRotationFromDate(date1, emp.getCorpId(),date2);
		model.addAttribute("bisRotationSearchList", bisRotationList);
		if(bisRotationList != null && bisRotationList.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
		}
		model.addAttribute("listBis", this.bisService.fullListOfBis());
		model.addAttribute("DynamicHeadingValue","ResourceRotation-Export" );
		model.addAttribute("employee", emp);	
		return "bisRotationReport";
	}
	
	@RequestMapping(value = "/bisRotationReport", method = RequestMethod.GET)
	public String bisRotationReportSearchList(Model model, HttpServletRequest request) {
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {		
			model.addAttribute("checkUserType", "ViewMode");
		}	
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			/*e.setUserManagement(true);*/
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			  
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
			
		//model.addAttribute("listBis", this.bisService.fullListOfBis());
		model.addAttribute("employee", new Employee());	
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);
		return "bisRotationReport";
	}

	
	@RequestMapping(value = "/hc&fteReport", method = RequestMethod.GET)
	public String hcfteReport(Model model, HttpServletRequest request) throws ParseException {
		

		DecimalFormat df = new DecimalFormat("#.##");
		LocalDate current_date = LocalDate.now();
		List<String> monthList = new ArrayList<>();
		for(int month_count=0 ; month_count<=6 ; month_count++){
			
			LocalDate updateddate = current_date.plusMonths(month_count);
			
			DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			String format_1=(updateddate).format(formatter_1);		
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MMM-yyyy", Locale.ENGLISH);
			LocalDate ld = LocalDate.parse(format_1, dtf);

			String month_name = dtf2.format(ld);
			monthList.add(month_name);
		}	
		
        logger.info(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(current_date));
        LocalDate current_month_startDate = current_date.with(firstDayOfMonth());
        
        logger.info(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(current_month_startDate));
        LocalDate current_Month_Enddate = current_date.with(lastDayOfMonth());
        logger.info(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(current_Month_Enddate));
        
        //get the unique country id from the country table order by country 
        List<Country> countryList = countryService.getCountryListOrderWise();
        
        
        
        List<Float> franceCount = new ArrayList<>();
        List<Float> indiaCount = new ArrayList<>();
        List<Float> chinaCount = new ArrayList<>();
        List<Float> spainCount = new ArrayList<>();
        List<Float> moroccoCount = new ArrayList<>();
     
        
        for(int country_Index = 0 ; country_Index < countryList.size() ; country_Index++){
        	int country_Id = countryList.get(country_Index).getCountryId();
        	List<Float> hcListCountryWise = employeeService.headCountList(current_month_startDate , current_Month_Enddate , country_Id );
        	if(country_Id ==1 ){
        		franceCount.addAll(hcListCountryWise);
        	}
        	if(country_Id ==2 ){
        		indiaCount.addAll(hcListCountryWise);
        	}
        	if(country_Id ==3 ){
        		chinaCount.addAll(hcListCountryWise);
        	}
        	if(country_Id ==4 ){
        		spainCount.addAll(hcListCountryWise);
        	}
        	if(country_Id ==5 ){
        		moroccoCount.addAll(hcListCountryWise);
        	}

        }
        
        List<Float> totalCountPerMonthCountryWise =  new ArrayList<Float>();
        
        for(int index = 0 ; index < 6 ; index++){
        	
        	float totalCount = franceCount.get(index) + indiaCount.get(index) +  chinaCount.get(index) + spainCount.get(index) + moroccoCount.get(index) ;
        	totalCount = Float.parseFloat(df.format(totalCount));
        	totalCountPerMonthCountryWise.add(totalCount);
        }
    
        // adding the value TOTAL along with other country name in the list
        Country allCountry = new Country();
        allCountry.setCountryId(5);
        allCountry.setCountryName("Total");
        countryList.add(allCountry);
        
        model.addAttribute("countryList", countryList);
        model.addAttribute("monthList", monthList);
		model.addAttribute("franceCount", franceCount);
		model.addAttribute("indiaCount", indiaCount);
		model.addAttribute("chinaCount", chinaCount);
		model.addAttribute("spainCount", spainCount);
		model.addAttribute("moroccoCount", moroccoCount);
		model.addAttribute("totalCountPerMonthCountryWise", totalCountPerMonthCountryWise);
  
		Employee e = new Employee();
        
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			e.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");

		}
		

		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			e.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			e.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		
		return "hcFteReport";
	}
	
	@RequestMapping(value = "/orphanMgr", method = RequestMethod.GET)
	public String addUserByRoleId(Model model, HttpServletRequest request) {
	int country_id = 0;
	Employee emp = new Employee();
	model.addAttribute("employee", emp);
	
	//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

	session = request.getSession();
	role_id = (String) session.getAttribute("RoleName");
	
	if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
		emp.setUserReadOnly(true);
		model.addAttribute("checkUserType", "ViewMode");
		}
	
	else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
		emp.setUserManagement(true);
		model.addAttribute("checkUserTypeforUM", "UserManagement");
		}
	else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM	
			model.addAttribute("checkUserType", "BundleEM");
		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
			  
			model.addAttribute("checkUserType", "RM");
		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
			  
			model.addAttribute("checkUserType", "RM_PMO");
		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
	model.addAttribute("CheckReportTypeOM","/orphanMgr");
	model.addAttribute("listCountry", this.countryService.listCountry());
	List<Bis> fullListOfBis = this.bisService.fullListOfBis();
	model.addAttribute("fullListOfBis",fullListOfBis);
	
	
	
	return "orphanMgrForm";
}
	
	@RequestMapping(value = "/orphanMgr/searchMgr", method = RequestMethod.POST)
	public String getOrphanMgr(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,@ModelAttribute("employee") Employee emp) {
	
		int country_id = 0;
		String  country_Name = null;
		
		String resStatus = null;
		if(emp.getResourceStatus()!=null){
			resStatus = emp.getResourceStatus();
			
		}
		if(emp.getCountry()!=null){
			country_id  = emp.getCountry().getCountryId();
			country_Name =  emp.getCountry().getCountryName() ;
		}
		List<Employee> employeeSearchList = employeeService.getOrphanList(country_id, resStatus);
		List<Employee> employeeList = employeeService.modifyEmployeeList(employeeSearchList);
		
		model.addAttribute("listCountry", this.countryService.listCountry());
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);
		
		
		model.addAttribute("employeeList",employeeList);
		
		
		if(employeeList != null && employeeList.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
     	}

		if(country_id!=0){
			model.addAttribute("DynamicHeadingValue","Orphan Manager List"  + " ( " + country_Name + " ) " );
		}
		else{
			model.addAttribute("DynamicHeadingValue", "Orphan Manager List");
		}
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
			}
		
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");
			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
			
		//model.addAttribute("CheckReportTypeOM","orphanMgrPage");
		
		return "orphanMgrForm";
	}
	
	@RequestMapping(value = "/replacementEmployees/searchByCountry", method = RequestMethod.POST)
	public String getReportForRepEmployeesByCountry(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,@ModelAttribute("employee") Employee emp) {
		
		int country_id = 0;
		String countryName = null;
		
		if(emp.getCountry() != null){
		country_id = emp.getCountry().getCountryId();
		countryName = emp.getCountry().getCountryName();
		}
		
		List<Employee> empList = employeeService.getReplacementEmployees(country_id);
		List<Employee> replacementEmp = employeeService.modifyEmployeeList(empList);
		
		model.addAttribute("replacementEmp",replacementEmp);
		model.addAttribute("listCountry", this.countryService.listCountry());
		
		for(int i =0; i < replacementEmp.size(); i++){
			boolean reprequired = replacementEmp.get(i).isReplacementRequired();
			if(reprequired){
				replacementEmp.get(i).setReplacementRequiredVal("Yes");
			}else{
				replacementEmp.get(i).setReplacementRequiredVal("No");
			}
		}
		
		if(replacementEmp != null && replacementEmp.size() <= 0){
				model.addAttribute("successMsg", "No Records found.");
	     	}
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
			}
		
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");
			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM 
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
        if(country_id!= 0){
	       model.addAttribute("DynamicHeadingValue","Replacement Employee List" + " ( " + countryName + " ) ");
        }
        else{
 	       model.addAttribute("DynamicHeadingValue","Replacement Employee List");
	     }

		return "replacementEmployees";
	
	}
	
	
	
	@RequestMapping(value = "/replacementEmployees", method = RequestMethod.GET)
	public String reportForReplacementEmployees(Model model, HttpServletRequest request) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		model.addAttribute("listCountry", this.countryService.listCountry());
		
		model.addAttribute("DynamicHeadingValue","Replacement Employees ");
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			e.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		}

		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			e.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");
		}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			e.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		return "replacementEmployees";
	
	}
	@RequestMapping(value = "/ResReportExcelExport", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String createResourceReportFile(@RequestParam("country") int country, 
			@RequestParam("actualoffdate") Date actualoffdate ,@RequestParam("joinidate") Date joinidate,@RequestParam("resStatus") String resStatus, @RequestParam("empType") String empType,@RequestParam("bis") int bis,HttpServletResponse httpResponse) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
		String currentTimestamp = df.format(new Date());
		String filename="EmployeeList(";
		try {
		Employee emp = new Employee();
		logger.info("inside resource report excel export function");
		String excelFilePath = System.getProperty("java.io.tmpdir") + File.separator; //Employee List  ( India - Internal - ActiveResources)
		
		
		if(country != 90) {
			emp.setCountry(this.countryService.getCountryById(country));
			filename=filename+this.countryService.getCountryById(country).getCountryName()+"-";
		}
		
		if(bis != 0) {
			emp.setBis(this.bisService.getBisById(bis));
			filename=filename+this.bisService.getBisById(bis).getBis_Name()+"-";
		}
		if(!empType.equalsIgnoreCase(OnboardingConstants.ALL)) {
			emp.setEmpType(empType);
			filename=filename+empType+"-";
		}else {
			filename=filename+OnboardingConstants.ALL+"-";
		}
		emp.setResourceStatus(resStatus);//EMP_RESOURCE_ACTIVE
		filename=filename+resStatus+")";
		if(actualoffdate != null) {
			emp.setActualEndDate(actualoffdate);
		}
		if(joinidate != null) {
			emp.setJoiningDate(joinidate);
		}
		emp.setEmpType(OnboardingConstants.ALL);
		//List<Employee> empList = this.empReportFiltering(emp,null);
		//logger.info("emplist : "+empList.size());
		
		filename = filename + currentTimestamp + ".xlsx";
		
		httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + filename+"\"");
		httpResponse.setContentType("application/xlsx");
		this.employeeService.resourceReportFn(emp, filename,excelFilePath);
		logger.info("PROD CHECK - Generated excel file by /ResReportExcelExport call.");
		}
		catch(Exception e) {
			logger.info(e.getStackTrace());
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
        
        return filename;
		
	}
	@RequestMapping(value = "/bisRotationExcelExport", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String createFile(@RequestParam("fromdate") String fromdate, 
			@RequestParam("todate") String todate ,@RequestParam("corpid") String corpid, HttpServletResponse httpResponse) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate from = null; LocalDate to = null; 
		if(!fromdate.isEmpty()) {
			from = LocalDate.parse(fromdate, formatter);
		}
		if(!todate.isEmpty()) {
			to = LocalDate.parse(todate, formatter);
		}
		String excelFilePath = System.getProperty("java.io.tmpdir") + File.separator + "ResourceRotation-Export.xlsx";
		
		List<BisRotation> bisRotationList = this.bisRotationService.getBisRotationFromDate(from, corpid, to);
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("RotationList");
        GenericExcel<BisRotation> g = new GenericExcel<BisRotation>();
        
        Class T = Class.forName("com.capgemini.onboarding.model.BisRotation");
        g.writeHeaderLine(sheet, T, null);
        g.writeDataLines(bisRotationList, workbook, sheet, T);
       
        httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + "ResourceRotation-Export" + ".xlsx\"");
		httpResponse.setContentType("application/xlsx");
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);//(this.absolutePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        
        return "ResourceRotation-Export";
	}
	
	@RequestMapping(value = "/downloadReportFile/{file}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("file") String file, HttpServletResponse response) throws Exception {

		// Prevent path traversal: reject any path separator or parent-dir sequence
		if (file.contains("/") || file.contains("\\") || file.contains("..") || file.contains("\0")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid filename");
			return;
		}
		File metricsReportFile = new File(System.getProperty("java.io.tmpdir"), file + ".xlsx");
		// Canonical path check — belt-and-suspenders against traversal
		if (!metricsReportFile.getCanonicalPath().startsWith(
				new File(System.getProperty("java.io.tmpdir")).getCanonicalPath())) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		logger.info("downloadReportFile: serving " + metricsReportFile.getName());
		InputStream is = new FileInputStream(metricsReportFile);
		response.setHeader("Content-Disposition", "attachment; filename=" + metricsReportFile.getName());
		FileCopyUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
		is.close();
		if (metricsReportFile.delete()) {
			logger.info("After File delete true");
		}
		
	}
	
	
	

	@RequestMapping(value = "/reportoffboardingSearch", method = RequestMethod.POST)
	public String searchOffBoardingEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		List<Employee> offBoardReportList = employeeService.searchOffboardReport(emp);
		model.addAttribute("listCountry", this.countryService.listCountry());
		model.addAttribute("listRollOffType", this.employeeService.getRollOffTypeList());
		model.addAttribute("listReplacementType", this.employeeService.getReplacementTypeList());
		List<Employee> empList = employeeService.modifyEmployeeList(offBoardReportList);
		model.addAttribute("offBoardReportList", empList);
					
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		
		}
		
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		
		
		for(int j=0; j < empList.size(); j++){/*
			int rollOffType = empList.get(j).getRollOffType();
			boolean replacementReq = empList.get(j).isReplacementRequired();
			int replacementType = empList.get(j).getReplacementType();
			
			if(rollOffType==0){
				empList.get(j).setRollOffTypeVal("");
			}
			if(rollOffType==1){
				empList.get(j).setRollOffTypeVal("End of work assignment");
			}
			if(rollOffType==2){
				empList.get(j).setRollOffTypeVal("Change in skills required");
			}
			if(rollOffType==3){
				empList.get(j).setRollOffTypeVal("Resignation");
			}
			if(rollOffType==4){
				empList.get(j).setRollOffTypeVal("Transitioned to Offshore");
			}
			if(rollOffType==5){
				empList.get(j).setRollOffTypeVal("Performance");
			}
			if(rollOffType==6){
				empList.get(j).setRollOffTypeVal("BGV / Abscondee");
			}
			if(rollOffType==7){
				empList.get(j).setRollOffTypeVal("Employee requested");
			}
			if(rollOffType==8){
				empList.get(j).setRollOffTypeVal("Rotation");
			}
			if(rollOffType==9){
				empList.get(j).setRollOffTypeVal("Hired as Capgemini resource");
			}
			
			if(replacementReq){
				empList.get(j).setReplacementRequiredVal("Yes");
			}
				else{
					empList.get(j).setReplacementRequiredVal("No");
				}
			
			if(replacementType == 0){
				empList.get(j).setReplacementTypeVal("");
			}
			if(replacementType == 1){
				empList.get(j).setReplacementTypeVal("End of work assignment");
			}
			if(replacementType == 2){
				empList.get(j).setReplacementTypeVal("Change in skill");
			}
			if(replacementType == 3){
				empList.get(j).setReplacementTypeVal("Got mutualized within the team");
			}
		
		*/}
		
		for(int i=0 ; i <empList.size() ; i++){
			int heritgTypevlaue = empList.get(i).getHeritageType();
			boolean decisionValByGPVzalue = empList.get(i).isDecisionValByGP();
			if(decisionValByGPVzalue)
			{
				empList.get(i).setDecisionVal("Yes");
			}else{
				empList.get(i).setDecisionVal("No");
			}
			
			Bis bis = empList.get(i).getBis();
			if(bis!=null){
				int bisId = empList.get(i).getBis().getBis_id();
				if(bisId==1){
					bis.setBis_Name("");
					empList.get(i).setBis(bis);
				}
			}
			
			if(heritgTypevlaue==1){
				empList.get(i).setHeritageValue("Capgemini");;
			}
			else if(heritgTypevlaue==2){
				empList.get(i).setHeritageValue("FR HERMES");
			}
			else if(heritgTypevlaue==3){
				empList.get(i).setHeritageValue("Others");
			}
			
		}
		

		if(offBoardReportList != null && offBoardReportList.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
		}
		
		int countryId = 0;
		String countryName = null;
		
		if(emp.getCountry()!=null){
		countryId = emp.getCountry().getCountryId();
		countryName = emp.getCountry().getCountryName();
		}
		
		if(countryId!=0){
		model.addAttribute("DynamicHeadingValue","Employee List"  + " ( " + countryName  +" ) " );
		}
		else
		model.addAttribute("DynamicHeadingValue", "Employee List - All Country");
		
		
		
		return "offBoardingReport";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	//new method for onboarding request report
	@RequestMapping(value = "/OnbReqReportExcelExport", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String onbReqReportFile(@RequestParam("country") int country, 
			 @RequestParam("empType") String empType,@RequestParam("bis") int bis,HttpServletResponse httpResponse) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
		String currentTimestamp = df.format(new Date());
		String filename="OnboardingRequestEmployeeList(";
		try {
				PreOnbEmployee emp = new PreOnbEmployee();
			logger.info("inside onboarding request report excel export function");
			String excelFilePath = System.getProperty("java.io.tmpdir") + File.separator;
			
			
			if(country != 90) {
				emp.setCountry(this.countryService.getCountryById(country));
				filename=filename+this.countryService.getCountryById(country).getCountryName()+"-";
			}
			
			if(bis != 0) {
				emp.setBis(this.bisService.getBisById(bis));
				filename=filename+this.bisService.getBisById(bis).getBis_Name()+"-";
			}
			if(!empType.equalsIgnoreCase(OnboardingConstants.ALL)) {
				emp.setEmpType(empType);
				filename=filename+empType+"-";
			}else {
				emp.setEmpType(OnboardingConstants.ALL);
				filename=filename+OnboardingConstants.ALL+"-";
			}
			/*emp.setResourceStatus(resStatus);//EMP_RESOURCE_ACTIVE
			filename=filename+resStatus+")";
			if(actualoffdate != null) {
				emp.setActualEndDate(actualoffdate);
			}
			if(joinidate != null) {
				emp.setJoiningDate(joinidate);
			}*/
			//List<Employee> empList = this.empReportFiltering(emp,null);
			//logger.info("emplist : "+empList.size());
			
			filename = filename + currentTimestamp + ".xlsx";
			
			httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + filename+"\"");
			httpResponse.setContentType("application/xlsx");
			this.preOnbEmpService.onbReqReport(emp, filename,excelFilePath);
			logger.info("PROD CHECK - Generated excel file by /OnbReqReportExcelExport call.");
		}
		catch(Exception e) {
			logger.info(e.getStackTrace());
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
        
        return filename;
        
	}
	
	@RequestMapping(value = "/onboardingRequest", method = RequestMethod.GET)
	public String preOnbEmployeeSearchList(Model model, HttpServletRequest request) {
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {		
			model.addAttribute("checkUserType", "ViewMode");
		}	
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			/*e.setUserManagement(true);*/
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			  
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			  model.addAttribute("checkUserType", "ASL");
  		  }
		model.addAttribute("listCountry", this.countryService.listCountry());
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);
		model.addAttribute("employee", new Employee());		
		return "onboardingRequest";
	}

	
}
