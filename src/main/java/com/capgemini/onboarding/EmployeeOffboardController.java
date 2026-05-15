package com.capgemini.onboarding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dto.EmployeeDTO;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.service.CountryService;
import com.capgemini.onboarding.service.EmployeeService;

@Controller
public class EmployeeOffboardController {

	private Logger logger = Logger.getLogger(EmployeeOffboardController.class);
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private CountryService countryService;
	
	private HttpSession session;
	private String role_id;

	@RequestMapping(value = "/offboardEmployee/{id}", method = RequestMethod.GET)
	public String getOffboardEmployee(@PathVariable("id") Long id, Model model, HttpServletRequest request) {

		Employee emp = this.employeeService.getEmployeeById(id);
	
		model.addAttribute("listCountry", this.countryService.listCountry());
		model.addAttribute("listReplacementType",this.employeeService.getReplacementTypeList());
		
		model.addAttribute("listRollOffList",this.employeeService.getRollOffTypeList());
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if(role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)){
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
  		  }
		
		if(emp.getRepCorpId()!=null && !emp.getRepCorpId().equalsIgnoreCase("")){
			String corpId = emp.getRepCorpId();
			List<Employee> replacementEmpDetails = employeeService.getEmpNameByCorpId(corpId);
	        if(replacementEmpDetails != null && replacementEmpDetails.size() > 0){
	        	  emp.setRepfirstName(replacementEmpDetails.get(0).getFirstName() + " " + replacementEmpDetails.get(0).getLastName()) ;
	        }
	        
	        else  if(corpId == null){
	        	 emp.setRepfirstName(" ") ;
	        	// logger.info("CorpId is null " + corpId);
	        	
	        }
		}
		
	
		model.addAttribute("employee", emp);
		return "offboardEmployee";
	}
	
	@RequestMapping(value = "/employee/offboard", method = RequestMethod.POST)
	public String offboardEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		emp.setReplacementType(this.employeeService.getReplacementTypeById(emp.getReplacementTypeVal()));
		emp.setRollOffType(this.employeeService.getRollOffTypeById(emp.getRollOffTypeVal()));
		Employee e = employeeService.getEmployeeById(emp.getId());
		List<Employee> e1= employeeService.searchOffboardReport(emp);
		/*String e2=e1.get(0).toString();*/
		Date actualEndDate = e.getActualEndDate();
		Date updatedActualDate = emp.getActualEndDate();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if(role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)){
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
  		  }
		if((updatedActualDate!=null && actualEndDate == null) || (updatedActualDate!=null && updatedActualDate.compareTo(actualEndDate) != 0)) {
			try {
				String actualEndDateStr = "&lt;blank value&gt;";
				String updatedActualDateStr = "&lt;blank value&gt;";
				if(actualEndDate!=null) {
				actualEndDateStr = OnboardingConstants.DATE_FORMAT.format(actualEndDate);
				}
				if(updatedActualDate!=null) {
				updatedActualDateStr = OnboardingConstants.DATE_FORMAT.format(updatedActualDate);
				}
				String empDetails = e.getFirstName() + " " + e.getLastName() + " ( ID: " + e.getEmpId() + " )";
				String managerDetails =e.getManager().getFirstName();
				if(e1!=null && e1.size()>0){
					String msgSubject = "Off-boarding date change notification for " + empDetails;
					String msgSubject1 = "List of employee who are offboarded " + e1.get(0).getFirstName()  ;
					
				}
				
				}
			catch (Exception exception) {
					logger.error("Exceptions in offboardEmployee " + exception.getMessage(), exception);
					model.addAttribute("errorMsg", "Error while offboarding Employee record.");
					model.addAttribute("employee", emp);
					return "offboardEmployee";
				}
		}
		final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		e.setCreatedBy(loggedUser);
		Date date = new Date();
		e.setCreationdate(date);
		this.employeeService.offboardEmployee(emp.getActualEndDate(), emp.getId() , e,emp);
		model.addAttribute("successMsg", "Resource off-boarding data saved successfully.");
		return "offboardEmployee";//"redirect:/offboardEmployeeSuccess";
	}
	
	@RequestMapping(value = "/checkActualDate", method = RequestMethod.GET)
	public @ResponseBody boolean checkCorpIdExistsInPreOnb(
			@RequestParam(value = "actualDate", required = true) Date actualDate)
	
	{
		
		boolean isActualDatePast = false;
		logger.info("/checkActualDate");
		Date today = new Date();
		actualDate.setHours(17);
		actualDate.setMinutes(00);
		actualDate.setSeconds(00);
		
		if(actualDate.before(today)) {
			isActualDatePast = true;
		}
			
  		return isActualDatePast;
	}
	
	
	@RequestMapping(value = "/checkRepCorpIdExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkReplacementCorpIdExists(
			@RequestParam(value = "repCorpId", required = true) String corpId)
	
	{
		String empType= null;
		String txtPSAID = null;
		boolean isEmployeeExist = false;
		if(corpId!=null && !corpId.equals("")){
			 isEmployeeExist = employeeService.checkCorpIdExists(corpId,txtPSAID,empType,"offboard");	
		}
		return isEmployeeExist;
	}
	@RequestMapping(value = "/getReplacementNameByCorpID", method = RequestMethod.GET)
	public @ResponseBody EmployeeDTO getReplacementNameByCorpID(
			@RequestParam(value = "repCorpId", required = true) String corpId)
	
	{
		EmployeeDTO employeeDTO = new EmployeeDTO();
		//String empType= null;
		//String txtPSAID = null;
		//boolean isEmployeeExist = false;
		List<Employee> replacementEmpDetails = employeeService.getEmpNameByCorpId(corpId);
   
        if(replacementEmpDetails != null && replacementEmpDetails.size() > 0){
        	  replacementEmpDetails.get(0).getFirstName();
        	  employeeDTO.setRepFirstName(replacementEmpDetails.get(0).getFirstName() + " " + replacementEmpDetails.get(0).getLastName()) ;
        	  
        }
        
          if(corpId.equalsIgnoreCase("") ||  corpId == null){
        	
        	 employeeDTO.setRepFirstName("") ;
        	 logger.info("CorpId is null " + corpId);
        	
        }
      
		return employeeDTO;
	}
	
	
	@RequestMapping("/offboardEmployeeSuccess")
	public String offboardEmployeeSuccess(Model model) {
		
		return "offboardEmployee";
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}
