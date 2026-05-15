package com.capgemini.onboarding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.activedirectory.ActiveDirectory;
import com.capgemini.onboarding.activedirectory.PCHostnameFromActiveDirectory;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dao.EmployeeDAO;
import com.capgemini.onboarding.dto.BundleEmDTO;
import com.capgemini.onboarding.dto.CountriesDTO;
import com.capgemini.onboarding.dto.EmailDTO;
import com.capgemini.onboarding.dto.EmployeeDTO;
import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.onboarding.mail.MailUtility;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.EmailReport;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.EmployeeRoles;
import com.capgemini.onboarding.model.FieldCohortMapping;
import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.model.Grade;
import com.capgemini.onboarding.model.JiraIDCreator;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.PrimaryProgram;
import com.capgemini.onboarding.model.ResourceCohortMapping;
import com.capgemini.onboarding.model.ResourceManager;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.model.State;
import com.capgemini.onboarding.model.Training;
import com.capgemini.onboarding.model.Vendor;
import com.capgemini.onboarding.rest.MicrosoftFlowRest;
import com.capgemini.onboarding.service.BasService;
import com.capgemini.onboarding.service.BisService;
import com.capgemini.onboarding.service.BundleEmService;
import com.capgemini.onboarding.service.CfaoService;
import com.capgemini.onboarding.service.CgEntityService;
import com.capgemini.onboarding.service.CountryService;
import com.capgemini.onboarding.service.DemandTypeService;
import com.capgemini.onboarding.service.EmailReportService;
import com.capgemini.onboarding.service.EmployeeRoleService;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.EntityService;
import com.capgemini.onboarding.service.FieldCohortService;
import com.capgemini.onboarding.service.GlobalGradeService;
import com.capgemini.onboarding.service.GradeService;
import com.capgemini.onboarding.service.IndusGoalsService;
import com.capgemini.onboarding.service.MoodleService;
import com.capgemini.onboarding.service.OffshoreEmService;
import com.capgemini.onboarding.service.PSALibTypeService;
import com.capgemini.onboarding.service.PreOnbEmployeeService;
import com.capgemini.onboarding.service.PrimaryProgramService;
import com.capgemini.onboarding.service.ProjectService;
import com.capgemini.onboarding.service.ResourceCohortService;
import com.capgemini.onboarding.service.ResourceManagerService;
import com.capgemini.onboarding.service.RoleTechService;
import com.capgemini.onboarding.service.SpocService;
import com.capgemini.onboarding.service.StateService;
import com.capgemini.onboarding.service.TechnologyService;
import com.capgemini.onboarding.service.VendorService;
import com.capgemini.onboarding.util.PsaMailUtility;

@Controller
public class preOnboardingController {
	
	private Logger logger = Logger.getLogger(preOnboardingController.class);
	

	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
	@Autowired(required = true)
	private MoodleService moodleService;
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private GradeService gradeService;

	@Autowired(required = true)
	private TechnologyService technologyService;

	@Autowired(required = true)
	private CountryService countryService;


	@Autowired(required = true)
	private GlobalGradeService globalGradeService;
	
	@Autowired(required = true)
	private EntityService entityService;

	@Autowired(required = true)
	private CgEntityService cgEntityService;
	
	@Autowired(required = true)
	private OffshoreEmService offshoreEmService;
	
	@Autowired
	private BundleEmService bundleEmService;
	
	@Autowired(required = true)
	private StateService stateService;
	
	@Autowired(required = true)
	private ResourceManagerService resourceMgrService;
	
	@Autowired(required = true)
	private BisService bisService;
	
	@Autowired(required = true)
	private VendorService vendorService;
	
	@Autowired(required = true)
	private SpocService spocService;
	
	@Autowired(required = true)
	private PSALibTypeService psaLibTypeService;
	
	@Autowired(required = true)
	private EmployeeDAO empdao;
	
	@Autowired(required = true)
	private PrimaryProgramService primaryProgramService;
	
	@Autowired(required = true)
	private RoleTechService roleTechService;
	
	@Autowired(required = true)
	private IndusGoalsService indusGoalsService;
	
	@Autowired(required = true)
	private ProjectService projectService;
	
	@Autowired(required = true)
	private EmployeeRoleService employeeroleservice;
	
	@Autowired(required=true)
	private DemandTypeService demandtypeservice;
	
    @Autowired(required = true)
    private PsaMailUtility psaMailUtility;
    
    @Autowired(required = true)
    private ResourceCohortService resourceCohortService;
    
    @Autowired(required = true)
    private FieldCohortService fieldCohortService;
    
    //Engg - Start
    @Autowired(required = true)
	private BasService basService;
    
    @Autowired(required = true)
	private CfaoService cfaoService;
  //Engg - Start
    
    @Autowired(required = true)
    private MicrosoftFlowRest microsoftFlowRest;

	private HttpSession session;
	private String role_id;
	
	private List<Country> countryList; //this.countryService.listCountry();
	private List<EmployeeRoles> EmployeeRolesList;
	

	private final String url = "URL - http://frparhermesto:8081/onboarding/login";
	
	@Value("${ad.activeDirUsername}")
	private static String username;
	
	private static String password;
	private static String domain;
	
	 @Autowired(required = true)
	private EmailReportService emailReportService;
	
	/*@Autowired
	public preOnboardingController(@Value("${activeDirUsername}") String username, @Value("${activeDirPwd}") String password, @Value("${activeDirDomain}") String domain){
		
		preOnboardingController.username = username;
		preOnboardingController.password = password;
		preOnboardingController.domain = domain;
		
	}*/

	@RequestMapping(value = {"/addPreOnboardRec" }, method = RequestMethod.GET)
	public String listEmployees(Model model, HttpServletRequest request) {

		long curr =  System.currentTimeMillis();
		logger.info("Start of addPreOnboardRec method "+System.currentTimeMillis());
		
		PreOnbEmployee e = new PreOnbEmployee();
		
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
		else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
			
			model.addAttribute("checkUserType", "RM");
			
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			e.setEMReadOnly(true);//EM
 			model.addAttribute("checkUserType", "BundleEM");
 			
 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
 			  
 			model.addAttribute("checkUserType", "RM_PMO");
 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
 			
			model.addAttribute("checkUserType", "ASL");
		  }
		
		
		model.addAttribute("preonbemployee", e);
		model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(true));
		//model.addAttribute("listEmployees", this.preOnbEmpService.listManagers()); // sort by alphabetical order of Last name
		model.addAttribute("listEmployees", "");//Engg
		model.addAttribute("listEmployeesByBundleEm",this.preOnbEmpService.listManagersByBundleEm());
		//model.addAttribute("listEmployeesByEm",this.preOnbEmpService.listManagersByEm());  //EM List sort like manager list
		model.addAttribute("listEmployeesByEm","");//Engg
		model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
		model.addAttribute("listPSALib", this.psaLibTypeService.fullListOfPSALibType());
		model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
		model.addAttribute("listCountry", this.getcountryList());//this.countryService.listCountry());
		model.addAttribute("listTechnology", this.technologyService.listTechnology());
		//Engg - Start
		model.addAttribute("basList", this.basService.listBas());
		model.addAttribute("cfaoList", this.cfaoService.listCfao());
		//Engg - End
		model.addAttribute("listEntity", this.entityService.listEntity());
		model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
		model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEm());
		model.addAttribute("listBundleEm", this.bundleEmService.listBundleEmPreOnb());
		model.addAttribute("listIndusGoals",this.indusGoalsService.listIndusGoals());
		model.addAttribute("listRoleTech",this.roleTechService.listRoleTech());
		//model.addAttribute("listProjects", this.projectService.listProject());
		model.addAttribute("checkUserType", "BundleEM");
		model.addAttribute("VLANList", this.stateService.VLANList());
		model.addAttribute("newStateList", this.stateService.newStateList());
		model.addAttribute("demandTypeList", this.demandtypeservice.demandTypeList());
		
		long curr19 =  System.currentTimeMillis();
		model.addAttribute("listOfEmployees", this.listOfEmployees());
		logger.info("time for listOfEmployees "+ (System.currentTimeMillis() - curr19));
		
		if(e.getVendor() != null) {
			model.addAttribute("vendorListRejectCase",this.vendorService.listVendor(e.getCountry().getCountryId(), true));
		}
		
		long curr21 =  System.currentTimeMillis();
		List<PreOnbEmployee> employeeList = this.preOnbEmpService.getAllPreOnbEmployee();
		logger.info("time for getAllPreOnbEmployee "+ (System.currentTimeMillis() - curr21));
		
		//List<Employee> empList = preOnbEmpService.modifyEmployeeList(employeeList);
		List<Bis> fullListOfBis = this.bisService.fullListOfBis(true);
		
		model.addAttribute("fullListOfBis",fullListOfBis);
		model.addAttribute("employeeList", employeeList);
		
		logger.info("end of addPreOnboardRec method "+ System.currentTimeMillis());
		logger.info("Total time  "+( System.currentTimeMillis()-curr));
		return "addPreOnboardRec";
	}
	
/*	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}*/
	
	@RequestMapping(value = "/checkPreObEmployeeEmailExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkEmployeeEmailExists(
			@RequestParam(value = "email", required = true) String email)
			
	{
		String empType = null;
		String txtPSAID = null;
		boolean isEmployeeExist = false;
		if(email!=null && !email.equals("")){
			
			 isEmployeeExist = this.preOnbEmpService.checkEmployeeEmailExists(email,txtPSAID,empType);		
		}
		return isEmployeeExist;
	}

	
	
	@RequestMapping(value = "/addPreOnboardRec/add", method = RequestMethod.POST)
	public String addPreOnbEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("preonbemployee") PreOnbEmployee emp) {
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		try {
			 
			final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			emp.setCreatedBy(loggedUser);
			//mehens - buddy
			if(emp.getOnboardingEmail() == null || emp.getOnboardingEmail().equals("") || emp.getOnboardingEmail().isEmpty()) {
				emp.setBuddy(null);
				emp.setOnboardingEmail(null);
			}
			else if(emp.getOnboardingEmail() != null || !emp.getOnboardingEmail().equals("")) {
				Employee buddy = employeeService.getEmployeeByEmail(emp.getOnboardingEmail());
				emp.setBuddy(buddy);
			}
			
			logger.info("Before format "+emp.getOnboardingDate());
			
		//	TimeZone.setDefault(TimeZone.getTimeZone("CET"));
			//Engg - Start
			if(emp.getPrimaryprogram().getPrimaryProgramId() == 10) {logger.info("set Engg as True for "+emp.getCorpId()+" id "+emp.getId());
				emp.setEngg(true);
			}
			else {
				emp.setEngg(false);
			}
			//Engg - End
			
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(emp.getOnboardingDate());
			emp.setJoiningDate(date1);
			logger.info("After format "+date1);
			
			emp.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(emp.getOffboardingDate()));
			emp.setBillingDate(new SimpleDateFormat("dd/MM/yyyy").parse(emp.getBillingStartDate()));
			 if(!emp.getDobDate().isEmpty()) {
			emp.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(emp.getDobDate()));
			}
			Date date = new Date();
			if((emp.getCreationdateString().isEmpty()) || emp.getCreationdateString() == null) {
				emp.setCreationdate(date);
			}else {
				emp.setCreationdate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(emp.getCreationdateString()));
			}
			emp.setEMSubmitDT(date);
			logger.info("loggedUser" +loggedUser);
			Integer countryId = emp.getCountry().getCountryId();
			Employee loggedUserEmp = this.employeeService.getEmployeeByCorpId(loggedUser);
			
			emp.setRequestor(loggedUserEmp); 
			
			Boolean isRejectedCase = (emp.getResourceStatus() != null) ? true : false;
			
			emp.setResourceStatus(OnboardingConstants.statusInitial);
			
			
			
			redirectAttributes.addFlashAttribute("successMsg", "Resource pre on-boarding submitted to RM.");
			//return "redirect:/addPreOnboardRec";
			if(!emp.getDobDate().isEmpty()) {
			emp.setPSAIdReq(true);
			}
			else {
				emp.setPSAIdReq(false);
			
			}
			
			this.preOnbEmpService.addPreOnbEmployee(emp);
			
			
			if(emp.getEmpType().equalsIgnoreCase("External")) {
				
				String vendor = emp.getVendor().getVendorName();
				// Mail to Contract manager and Security Team
				DLList dlFRHermesSecurity = preOnbEmpService.getDLListbyRole("DL FR HERMES Security");
				DLList dlContractManager = preOnbEmpService.getDLListbyRole("Contract Manager");
				String[] ccList = {psaMailUtility.prepareMailId(dlFRHermesSecurity.getEmail())};
				String[] recipientList = {psaMailUtility.prepareMailId(dlContractManager.getEmail())};
				String SubjExternalRes = "[User Management Tool] New subcontractor "+emp.getFirstName()+" "+emp.getLastName()+" from "+vendor+" in "+emp.getPrimaryprogram().getPrimaryProgramName(); //"Added contract resource with Corp Id "+emp.getCorpId(); 
				
				//PrimaryProgram priprog = emp.getPrimaryprogram();
				//String primaryProgram = this.primaryProgramService.getPrimaryProgramById().getPrimaryProgramName();
				
				MailSender.send(recipientList,ccList, MailUtility.contractorEmail(emp, dlContractManager, vendor) , SubjExternalRes);
				logger.info(SubjExternalRes+"  mail sent to "+dlContractManager.getEmail());
			}
			
			
			
			List<PreOnbEmployee> employeeList = preOnbEmpService.getAllPreOnbEmployee();
			List<PreOnbEmployee> empList = preOnbEmpService.modifyEmployeeList(employeeList);
			model.addAttribute("preOnbemployeeList", empList);
			String subject = "[User Management Tool] Please Validate "+emp.getPrimaryprogram().getPrimaryProgramName()+" Onboarding Request";
			
			Spoc spocRec = this.employeeService.getSpocFromCountry(countryId);
			logger.info("Mail To RM - start");
			/*MailSender.send(psaMailUtility.prepareMailId(spocRec.getSpocEmail()), MailUtility.createResourceMgrEMail(spocRec.getSpocName(),
					emp.getCorpId()+ " ("+emp.getFirstName()+" "+emp.getLastName()+") ", "from BIS "+emp.getBis().getBis_Name(),loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment()) , subject);*/	//
			//spocRec.getSpocEmail(); 
			//BCC
			DLList dlBcc = preOnbEmpService.getDLListbyRole("bcc");
			MailSender.sendNew(psaMailUtility.prepareMailId(spocRec.getSpocEmail()),psaMailUtility.prepareMailId(dlBcc.getEmail()), MailUtility.createResourceMgrEMail(spocRec.getSpocName(), emp,
					emp.getCorpId()+ " ("+emp.getFirstName()+" "+emp.getLastName()+") ", "from BIS "+emp.getBis().getBis_Name(),loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment()) , subject);
			
			logger.info("mail sent to RM for "+emp.getCorpId()+ " ("+emp.getFirstName()+" "+emp.getLastName()+") ");
			
		
		}
		catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("errorMsg", "Resource entry already exists.");
			model.addAttribute("preonbemployee", emp);
			return "redirect:/addPreOnboardRec";
		} catch (MessagingException e) {
			//logger.error("Exceptions in addEmployee " + e.getMessage(), e);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while sending mail.");
			model.addAttribute("preonbemployee", emp);
			return "redirect:/addPreOnboardRec";
		}catch (Exception e) {
			//logger.error("Exceptions in addEmployee " + e.getMessage(), e);
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage(),e);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while saving employee."+e.getMessage());
			model.addAttribute("preonbemployee", emp);
			return "redirect:/addPreOnboardRec";
		}
		
		
		return "redirect:/addPreOnboardRec";
	}
	
	@RequestMapping(value = "/addPreOnboardRec/update1", method = RequestMethod.POST)
	public String updatePreOnbEmployee1(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") PreOnbEmployee emp) {
		
		return "redirect:/preOnboardingSearch";
	}
	
	@RequestMapping(value = "/addPreOnboardRec/update", method = RequestMethod.POST)
	public String updatePreOnbEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("preonbemployee") PreOnbEmployee emp) {
		

		try {
			final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			Employee requestor = this.employeeService.getEmployeeByCorpId(emp.getCreatedBy());

			//Employee loggedUserEmp = this.employeeService.getEmployeeByCorpId(loggedUser);
			Employee loggedUserEmp = this.employeeService.getActiveEmployeeByCorpId(loggedUser);//new method for logged user
			
			
			PreOnbEmployee preOnbEmp = this.preOnbEmpService.getPreOnbEmployee(emp.getCorpId());
			/*if( preOnbEmp.getOnboardingEmail() !=null && preOnbEmp.getOnboardingEmail()!="" &&!(preOnbEmp.getOnboardingEmail().isEmpty())){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmp.getOnboardingEmail());
				preOnbEmp.setBuddy(employessss);
				}*/
			preOnbEmp.setRMSubmitDT(new Date()); // RM submission date irrespective of approved or rejected. Latest will be stored
			preOnbEmp.setComment(emp.getComment());
			preOnbEmp.setJiraNumber(emp.getJiraNumber());
			preOnbEmp.setStaffingRR(emp.getStaffingRR());
			preOnbEmp.setPcSerialNumber(emp.getPcSerialNumber());
			logger.info("Before format in update"+emp.getJoiningDate());
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			emp.setJoiningDate(sm.parse(emp.getOnboardingDate()));
			emp.setEndDate(sm.parse(emp.getOffboardingDate()));
			emp.setBillingDate(sm.parse(emp.getBillingStartDate()));
			if(emp.isPSAIdReq()) {
				emp.setDob(sm.parse(emp.getDobDate()));
			}
			if(emp.getResourceStatus().equals("Approve")){
				preOnbEmp.setResourceStatus(OnboardingConstants.statusRMApproved);
				preOnbEmp.setCreatedBy(loggedUser);
				
				logger.info("After update"+emp.getJoiningDate());
				
				redirectAttributes.addFlashAttribute("successMsg", "Pre-onboarding submitted to BIS PMO");
				
				//EmailDTO emaildto = new EmailDTO();
				//ArrayList<String> toList = new ArrayList<String>();
				//ArrayList<String> ccLists =new ArrayList<String>();
				//HashMap<String,String> paramValue = new HashMap<>(); 
		
				String requester = requestor.getFirstName()+" "+requestor.getLastName();
				String mngr =emp.getManager().getFirstName()+" "+emp.getManager().getLastName();
				String em =emp.getEM().getFirstName()+" "+emp.getEM().getLastName();
				String bem = emp.getBundleEM().getBundleEmName(); 
				String location = emp.getLocation().getStateName();
				//String joiningdate= emp.getJoiningDate().toString(); 
				String onboardingDate = emp.getOnboardingDate(); // R 8.2 change
				String enddate = emp.getOffboardingDate(); // R 8.2 change
				String bis = emp.getBis().getBis_Name();
				String firstname = emp.getFirstName();
				String lastname = emp.getLastName();
				
				/*paramValue.put("Requestor", requester);
				paramValue.put("mgnr", mngr);
				paramValue.put("EM",em);
				paramValue.put("BEM",bem);
				paramValue.put("onbdate",onboardingDate); //change bhavna
				paramValue.put("pdate",enddate);
				paramValue.put("loc",location);
				paramValue.put("BIS", bis);
				paramValue.put("FirstName", firstname);
				paramValue.put("LastName", lastname);
				paramValue.put("MAILTEMPID","P001NN2");*/
				
				String mgnremail = emp.getManagerEmail();
				String ememail = emp.getEM().getEmail();
				Integer bemid = emp.getBundleEM().getBundleEmId();
				String bememail = empdao.bememail(bemid);
			
				/*toList.add(mgnremail);
				ccLists.add(ememail);
				ccLists.add(bememail);
				
				emaildto.setToList(toList);
				emaildto.setCcList(ccLists);
				emaildto.setParamValue(paramValue);*/
				
				logger.info("before sending to Bundle EM");
				//Engg - start
				//psaMailUtility.senMail(emaildto);
				String subject1 = "[User Management Tool] Onboarding of "+firstname+" "+lastname+" in BIS "+bis+" on "+emp.getPrimaryprogram().getPrimaryProgramName();
				String[] toList1 = {psaMailUtility.prepareMailId(mgnremail)};
				String [] ccList1 = {psaMailUtility.prepareMailId(ememail), psaMailUtility.prepareMailId(bememail)};
				//BCC
				DLList bccDl1 = preOnbEmpService.getDLListbyRole("bcc");
				logger.info(subject1);
				MailSender.sendNew(toList1, psaMailUtility.prepareMailId(bccDl1.getEmail()),ccList1, MailUtility.createManagerEmail(emp, requester, mngr, em, bem, onboardingDate, enddate, location, bis), subject1);
				//Engg - end
				logger.info("after sending to Bundle EM");
				
				/**Mail to BIS-PMO to complete onboarding start*/
				logger.info("Onboarding Mail to BIS PMO - start for "+emp.getCorpId());
				String subject = "[User Management Tool] Please On-Board the Resource to BIS "+emp.getBis().getBis_Name()+" in "+emp.getPrimaryprogram().getPrimaryProgramName();
				BisPMOMap bisPMOMap = this.employeeService.getBISFromPMO(emp.getBis().getBis_id());
				
				/*MailSender.send(psaMailUtility.prepareMailId(bisPMOMap.getPmo_email()), MailUtility.createBISPMOEMail(bisPMOMap.getPmo_name(),
						emp.getCorpId(),loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment()) , subject);*/	//bisPMOMap.getPmo_name()
				
				String userName = emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+")";
				String bisName = emp.getBis().getBis_Name()+" PMO";
				//BCC
				DLList dlBcc1 = preOnbEmpService.getDLListbyRole("bcc");
				MailSender.sendNew(psaMailUtility.prepareMailId(bisPMOMap.getPmo_email()),psaMailUtility.prepareMailId(dlBcc1.getEmail()), MailUtility.createBISPMOEMail(bisName,
						userName,loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment()) , subject);
				
				/**Mail to BIS-PMO to complete onboarding end*/
				logger.info("Mail to BIS PMO sent: "+subject+" for "+emp.getCorpId());
				
				
			/**Mail to Alester for JIRA and Confluence ID start*/
			/*if(emp.getPrimaryprogram().getConfluenceaccess() && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-")))	{
				JiraIDCreator jiraCreator = preOnbEmpService.getJiraCreatorDetails();
				if(jiraCreator != null) {
					logger.info("Start of JIRA and Confluence mail - start");
					DLList dlCC = preOnbEmpService.getDLListbyRole("CC");
					DLList dlHermesOnb = preOnbEmpService.getDLListbyRole("HermesOnb");
					//String empName = emp.getFirstName()+" "+emp.getLastName()+" (CORP ID: "+emp.getCorpId()+")";
					String empName = emp.getFirstName()+" "+emp.getLastName();
					//String jiraSubject = "[HERMES] Creation of JIRA and Confluence ID for "+empName +" ("+emp.getCorpId()+"), "+emp.getLocation().getStateName();
					String jiraSubject = "[HERMES] Creation of JIRA and Confluence ID for "+empName +" ("+emp.getCorpId()+")";
					
					//String [] ccList = {psaMailUtility.prepareMailId(emp.getManagerEmail()),psaMailUtility.prepareMailId(dlCC.getEmail())};
					String [] ccList = {psaMailUtility.prepareMailId(dlHermesOnb.getEmail())};
					String[] recipientList = {psaMailUtility.prepareMailId(jiraCreator.getEmail())};
					MailSender.send(recipientList, ccList, MailUtility.createJiraIDMail(jiraCreator.getName(), empName, emp.getEmpId(),emp.getCorpId())
							, jiraSubject);
					
					//BCC
					DLList dlBcc2 = preOnbEmpService.getDLListbyRole("bcc");
					MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(dlBcc2.getEmail()), ccList, MailUtility.createJiraIDMail(jiraCreator.getName(), empName, emp.getEmpId(),emp.getCorpId(), emp)
							, jiraSubject);
					logger.info("Creation of JIRA and Confluence mail sent for "+empName+" "+emp.getCorpId());
				}
			}*/
			/**Mail to PPaaS team & cc Alester for JIRA and Confluence end*/
				
				//HRA Access mail -- done by ROBOT
				
				/*if(emp.getHRAReq()) {
					
					String HRASubject = "HRA Access <"+emp.getFirstName()+" "+emp.getLastName()+" ("+emp.getCorpId()+")> "+emp.getCountry().getCountryName()+" "+emp.getLocation().getStateName();
					
					
					
					String sentTo = preOnbEmpService.getDLListbyRole("BISPMO").getEmail(); // BIS PMO DL
					
					MailSender.send(psaMailUtility.prepareMailId(sentTo), MailUtility.createHRAReqMail(emp), HRASubject); 
					
					
					
				}*/
			
				/*if(emp.getLocation().getIsVLANReq() && (!emp.getIsVLANmailDone())) {
						
						String ITHelpDeskSubj = "Acces VLAN "+emp.getLocation().getStateName()+ " Corp Id "+emp.getCorpId();
						String IndiaVLAN = "VLAN access - "+emp.getLocation().getStateName();
						DLList dlItHelp = null;
						
						String[] recipientList = new String[1];
						if(emp.getCountry().getCountryId() == 2) {
							//String [] ccList = new String[1];
							//dlItHelp = preOnbEmpService.getDLListbyRole("IndiaVLAN");
							//ccList[0] = psaMailUtility.prepareMailId(emp.getEmail());
							//String recipient = psaMailUtility.prepareMailId(dlItHelp.getEmail());
							//MailSender.send(recipient, MailUtility.VLANIndiaMail(emp), IndiaVLAN);
						}else {
							dlItHelp = preOnbEmpService.getDLListbyRole("IT Help");
							DLList dlFRHermesInfra = preOnbEmpService.getDLListbyRole("DL FR HERMES INFRA CAPGEMINI");
							String [] ccList = new String[1];
							ccList[0] = psaMailUtility.prepareMailId(dlFRHermesInfra.getEmail());
							//ccList[1] = psaMailUtility.prepareMailId(emp.getEmail());
							recipientList[0] = psaMailUtility.prepareMailId(dlItHelp.getEmail());
							//MailSender.send(recipientList, ccList, MailUtility.VLANMail(emp) , ITHelpDeskSubj);
							
							//BCC
							DLList dlBcc6 = preOnbEmpService.getDLListbyRole("bcc");
							MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(dlBcc6.getEmail()), ccList, MailUtility.VLANMail(emp) , ITHelpDeskSubj);
							logger.info(ITHelpDeskSubj+" mail sent");
						}
						preOnbEmp.setIsVLANmailDone(true);  
					//}
				}*/
				
				//ODC Access mail & Seat and Desktop allocation.
				boolean isIndia = (emp.getCountry().getCountryName().equalsIgnoreCase("India"))? true : false;
				
				/*if(isIndia) {
					switch(emp.getLocation().getStateId()){
					case 4:
					case 5:
					case 20:
					case 34:	
						BisPMOMap bisPmo = employeeService.getBISFromPMO(emp.getBis().getBis_id());
						Boolean ODCAccessRequired = true;
						Boolean isOPEL = false;
						logger.info("For location "+emp.getLocation().getStateName()+" and Corp Id "+emp.getCorpId());
						if(emp.getPrimaryprogram().getPrimaryProgramName().equalsIgnoreCase("OPEL")) {
							isOPEL = true;
						}
						//ODCAccessRequired = (emp.getBis().getBis_id() == 18 ||emp.getBis().getBis_id() == 19 || emp.getBis().getBis_id() == 20)? false: true;
						
						//ODCAccessRequired = (emp.getPrimaryprogram().getPrimaryProgramId() == 1)? true : false;
						ODCAccessRequired = (emp.getPrimaryprogram().getPrimaryProgramId() == 1 || emp.getPrimaryprogram().getPrimaryProgramId() == 5)? true : false;
						
						//if(ODCAccessRequired && !isOPEL) {
						
						if(ODCAccessRequired) {
							logger.info("ODC access req");
							
							String ODCAccessSubj = "STELLANTIS HERMES ODC access request - "+emp.getCorpId();
							List<DLList> recipient = preOnbEmpService.getMultipleDLListbyRole(emp.getLocation().getStateName()+"ICRES");
							//String[] ccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getEmail())};
							String[] recipientList = new String[recipient.size()];
							for(int reci=0; reci < recipient.size(); reci++) {
								recipientList[reci] = psaMailUtility.prepareMailId(recipient.get(reci).getEmail());
							}
							//MailSender.send(recipientList, ccList, MailUtility.ODCAccessBISPMOMail(emp) , ODCAccessSubj);
							
							//BCC
							DLList dlBcc5 = preOnbEmpService.getDLListbyRole("bcc");
							String[] ccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getEmail()),psaMailUtility.prepareMailId(dlBcc5.getEmail())};//new change from BCC to CC
							logger.info("Changed BCC to CC");
							MailSender.send(recipientList, ccList, MailUtility.ODCAccessBISPMOMail(emp) , ODCAccessSubj);
							
							//MailSender.sendNew(recipientList, psaMailUtility.prepareMailId(dlBcc5.getEmail()), ccList, MailUtility.ODCAccessBISPMOMail(emp) , ODCAccessSubj);
							logger.info(ODCAccessSubj+" mail sent for: "+emp.getCorpId());
						}
						
						if(ODCAccessRequired) {
							logger.info("to new resource for ODC");
							String resSubj="Welcome to the Stellantis Team!";
							String[] resRecipientList = {psaMailUtility.prepareMailId(emp.getEmail())};
							String[] resCCList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getManager().getEmail()),psaMailUtility.prepareMailId(emp.getRequestor().getEmail())};
							//BCC
							DLList dlBccRes = preOnbEmpService.getDLListbyRole("bcc");
							
							MailSender.sendNew(resRecipientList,psaMailUtility.prepareMailId(dlBccRes.getEmail()) ,resCCList,MailUtility.resODCMail(emp),resSubj);
							logger.info(resSubj+" mail sent for: "+emp.getCorpId());
						}
						
						if(ODCAccessRequired) {
							logger.info("seat req");
							String SPOCSubj="[User Management Tool] Request for Seat for corp id - "+emp.getCorpId();
							DLList SPOCrecipient = preOnbEmpService.getDLListbyRole(emp.getLocation().getStateName()+"SPOC");
							String[] SPOCrecipientList = {psaMailUtility.prepareMailId(SPOCrecipient.getEmail())};
							String[] SPOCccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getEmail())};
							
							//MailSender.send(SPOCrecipientList, SPOCccList, MailUtility.SeatDesktopRequest(emp, SPOCrecipient.getName()), SPOCSubj);
							
							//BCC
							DLList dlBcc7 = preOnbEmpService.getDLListbyRole("bcc");
							MailSender.sendNew(SPOCrecipientList,psaMailUtility.prepareMailId(dlBcc7.getEmail()), SPOCccList, MailUtility.SeatDesktopRequest(emp, SPOCrecipient.getName()), SPOCSubj);
							logger.info(SPOCSubj+" mail sent to: "+SPOCrecipient.getEmail());
						}
						break;
					default: System.out.println("NO ODC access & Seat mail allocation for "+emp.getCorpId());
					}
				}*/
				
				
				this.preOnbEmpService.updatePreOnbEmployee(preOnbEmp);
				redirectAttributes.addFlashAttribute("successMsg", "Pre-onboarding submitted to BIS PMO");
			}else {
				preOnbEmp.setResourceStatus(OnboardingConstants.statusRMRejected);
				
				this.preOnbEmpService.updatePreOnbEmployee(preOnbEmp);
				String corpName = emp.getFirstName()+" "+emp.getLastName()+" ("+emp.getCorpId()+")";
				String subject = "[User Management Tool] Onboarding Request rejected for "+emp.getFirstName()+" "+emp.getLastName() +" ("+emp.getCorpId()+")";
				
				MailSender.send(psaMailUtility.prepareMailId(requestor.getEmail()), MailUtility.createResMgrRejectEMail(requestor.getFirstName(),
						corpName,loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment()) , subject);	//sivaraman.s
				
				redirectAttributes.addFlashAttribute("successMsg", "Onboarding Request Rejected & sent to EM/BundleEM"); //mehens new message
			}
			
		}
		catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("errorMsg", "Resource entry already exists.");
			model.addAttribute("preonbemployee", emp);
			return "redirect:/preOnboardingSearch";
		} catch (MessagingException e) {
			
			redirectAttributes.addFlashAttribute("errorMsg", "Error while sending mail.");
			model.addAttribute("preonbemployee", emp);
			return "redirect:/preOnboardingSearch";
		}catch (Exception e) {
			
			logger.info(e.getMessage(),e);
			logger.error(" Some Problem occurs :- "+e.getMessage(),e);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while saving employee."+e.getMessage());
			model.addAttribute("preonbemployee", emp);
			return "redirect:/preOnboardingSearch";
		}
		return "redirect:/preOnboardingSearch";
		
	}
	
	@RequestMapping(value = "/checkPreOnBEmployeeExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkEmployeeExists(
			@RequestParam(value = "empId", required = true) String empId,
			@RequestParam(value = "extrnalSelectn", required = true) boolean  extrnalSelectn,
			@RequestParam(value = "internalSelectn", required = true) boolean internalSelectn)
	
	{
		boolean isEmployeeExist = false;
		if(empId!=null && !empId.equals("")){
				if(extrnalSelectn){
					isEmployeeExist = preOnbEmpService.checkPreOnbEmployeeExists(empId , "External" );
				}
				else if(internalSelectn){
					isEmployeeExist = preOnbEmpService.checkPreOnbEmployeeExists(empId , "Internal");
				}
			 		
		}

		return isEmployeeExist;
	}
	
	@RequestMapping(value = "/newCountryStateMailRequest", method = RequestMethod.GET)
	public @ResponseBody boolean newCountryStateMail(
			@RequestParam(value = "newRequest", required = true) String newRequest,
			@RequestParam(value = "corpid", required = true) String  corpid,
			@RequestParam(value = "country", required = true) int  country) {
		
		final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee loggedUserEmp = this.employeeService.getEmployeeByCorpId(loggedUser);
		Spoc spoc = this.spocService.fetchSpoc(country);
		Country coun = this.countryService.getCountryById(country);
		String subj = "[User Management Tool] Request raised for new country / site by "+loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName()+"("+loggedUserEmp.getCorpId()+")";
		//DLList dlFRHermesSecurity = preOnbEmpService.getDLListbyRole("DL FR HERMES Security");
		DLList dlFRHermesSecurity = preOnbEmpService.getDLListbyRole("DL FR HERMES INFRA CAPGEMINI");
		String[] recipientList = {psaMailUtility.prepareMailId(dlFRHermesSecurity.getEmail())};
		String[] ccList ={psaMailUtility.prepareMailId(loggedUserEmp.getEmail()),psaMailUtility.prepareMailId(spoc.getSpocEmail())};
		try {
			MailSender.send(recipientList, ccList, MailUtility.newCountryLocationMail(loggedUserEmp, corpid, newRequest , coun), subj);
		} catch (Exception e) {
		
			logger.error(" Some Problem occurs :- "+e.getMessage());
			return false;
		}	
		
		
		return true;
		
	}
	
	@RequestMapping(value = "/PreOnbBis", method = RequestMethod.GET)
	public @ResponseBody BundleEmDTO bisValue(			
			@RequestParam(value = "id", required = true) int bundleEmId) {
		BundleEmDTO bundleemdto = new BundleEmDTO();
		List<Bis> bisList = this.bisService.bisList(bundleEmId); 
		bundleemdto.setBisList(bisList);
		return bundleemdto;
	}
	
	
	@RequestMapping(value = "/findBundleEMfromBIS", method = RequestMethod.GET)
	public @ResponseBody BundleEmDTO bundleEMList(			
			@RequestParam(value = "id", required = true) int bisId) {
		BundleEmDTO bundleEmDTO = new BundleEmDTO();
		BundleEm bundleEM = this.bundleEmService.getBundleEMListForBIS(bisId);
	     if(bisId == OnboardingConstants.Five)
	     {
	    	 List<Bis> getBisList =getBisList(OnboardingConstants.One);
	    	 bundleEmDTO.setBisList(getBisList);
	     }
		bundleEmDTO.setBundleEM(bundleEM);
		
		
		return bundleEmDTO;
	}
	
	
	@RequestMapping(value = "/checkBillingDate", method = RequestMethod.GET)
	public @ResponseBody boolean checkBillingDate(
			@RequestParam (value = "billingDateString", required = true) String billingDate,
			@RequestParam (value = "plannedEndDate", required = true) String plannedDate,
			@RequestParam (value = "joiningDate", required = true) String joiningDate) {
		
		boolean isDateProper = false;
		Date billingDateObj, plannedEndDate, joiningDateObj = null;
		try {
			billingDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(billingDate);
			plannedEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(plannedDate);
			joiningDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
			if(((billingDateObj.after(joiningDateObj)) || (billingDateObj.equals(joiningDateObj))) && (billingDateObj.before(plannedEndDate) || billingDateObj.equals(plannedEndDate))){
				
				isDateProper = true;
			}
		}
		catch(ParseException pe) {
			logger.info("Exception in billing Date comparison function");
			logger.error(" Some Problem occurs :- "+pe.getMessage());
		}
		return isDateProper;
	}
	
	@RequestMapping(value="/checkDob" , method=RequestMethod.GET)
	public @ResponseBody boolean checkDob(@RequestParam(value="dobDate", required=true) String dob) {
		boolean isBefore18 = false;
		
		try {
			Date dobDate = null;
			LocalDate date = LocalDate.now(); 
			//Date currentDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        LocalDate date18YearsAgo = date.minusYears(18);
	        Date date18YearsAgoAsDate = Date.from(date18YearsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
			dobDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			//isBefore18 = (dobDate.before(date18YearsAgoAsDate));
			isBefore18 = !(dobDate.after(date18YearsAgoAsDate));
		}
		catch(ParseException e) {
			logger.info("Exception in DOB check function");
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		return isBefore18;
	}
	
	@RequestMapping(value = "/checkPlannedEndDate", method = RequestMethod.GET)
	public @ResponseBody boolean checkPlannedEndDate(
			@RequestParam (value = "plannedEndDate", required = true) String plannedDate,
			@RequestParam (value = "joiningDate", required = true) String joiningDate) {
		
		boolean isPlannedDateGreater = false;
		Date plannedEndDate = null;
		Date joiningDateObj = null;
		try {
			plannedEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(plannedDate);
			joiningDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
			isPlannedDateGreater = (plannedEndDate.after(joiningDateObj));
		} catch (ParseException e) {
			logger.info("Exception in Planned and joining Date compare function");
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		
		return isPlannedDateGreater;
		
	}
	
	
	@RequestMapping(value = "/checkPlannedJoiningDate", method = RequestMethod.GET)
	public @ResponseBody boolean checkPlannedJoiningDate(
			@RequestParam (value = "joiningDate", required = true) String joiningDate)
			 {
		
		boolean isPlannedDateGreater = false;
		
		Date joiningDateObj = null;
		LocalDate date = LocalDate.now(); 
		Date currentDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		try {
			//plannedEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(plannedDate);
			joiningDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
			isPlannedDateGreater = (joiningDateObj.before(currentDate) || joiningDateObj.equals(currentDate));
		} catch (ParseException e) {
			logger.info("Exception in current date and onboarding date function");
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		
		return isPlannedDateGreater;
		
	}
	
	@RequestMapping(value = "/checkCorpIdExistsInPreOnb", method = RequestMethod.GET)
	public @ResponseBody boolean checkCorpIdExistsInPreOnb(
			@RequestParam(value = "corpId", required = true) String corpId) throws NamingException
	
	{
		//Employee empDetailsFromAD = new Employee();
		String empType= null;
		String txtPSAID = null;
		boolean isEmployeeExist = false;
		logger.info("checkCorpIdExistsInPreOnb1");
		if(corpId!=null && !corpId.equals("")){
			 isEmployeeExist = preOnbEmpService.checkCorpIdExistsInPreOnb(corpId,txtPSAID,empType,"add");		
		}
		
			
  		return isEmployeeExist;
	}
	
	@RequestMapping(value = "/checkCorpIdExistsEditInPreOnb", method = RequestMethod.GET)
	public @ResponseBody boolean checkCorpIdExistsInPreOnbEdit(
			@RequestParam(value = "corpId", required = true) String corpId , @RequestParam(value = "txtPSAID", required = true) String txtPSAID,
			@RequestParam(value = "empType", required = true) String empType,@RequestParam(value = "txthiddenCorpID", required = true) String txthiddenCorpID)

	{
		boolean isEmployeeExist = false;
		if(corpId.equalsIgnoreCase(txthiddenCorpID)){
			logger.info("This is the existing corp Id for the edited resource");
		}
		else{
			if(corpId!=null && !corpId.equals("")){
				if(empType.equalsIgnoreCase("Internal")){
					 isEmployeeExist = preOnbEmpService.checkCorpIdExistsInPreOnb(corpId,txtPSAID,"Internal",txthiddenCorpID);
				}
				else
				{
					 isEmployeeExist = preOnbEmpService.checkCorpIdExistsInPreOnb(corpId,txtPSAID,"External",txthiddenCorpID);		
				}		
			}
		}
	
		return isEmployeeExist;
	}
	
	@RequestMapping(value = "/getDetailsByCorpIDFromActiveDirectoryPreOnb", method = RequestMethod.GET)
	public @ResponseBody EmployeeDTO getDetailsFromActiveDirectory(
			@RequestParam(value = "corpId", required = true) String corpId) throws NamingException {
		
		//Session session = this.sessionFactory.getCurrentSession();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		boolean corpIdExits = false;
		logger.info("Corp Id - "+ corpId);
		corpIdExits = checkCorpIdExistsInPreOnb(corpId); // check also for preonboarding req based on status
		if(corpIdExits){
			employeeDTO.setFirstName("");
			employeeDTO.setLastName("");
			employeeDTO.setEmail("");
			employeeDTO.setEmpId("");
			//employeeDTO.setCountry("");
			employeeDTO.setGgId("");
			//employeeDTO.setGlobalGrade("");
			//employeeDTO.setLocation("");
			logger.info("corpId already exits");
		}
		
		else{
			
			try {
			
				ActiveDirectory activeDirectory;
				PCHostnameFromActiveDirectory pcHostname = new PCHostnameFromActiveDirectory(); //dkaushik for pchostname
				//activeDirectory = new ActiveDirectory("SVC-IN-HERMESMUM","Tdbj@3020","corp.capgemini.com");    // used for dtsum n all not working consistently // nikhil's team -- group it resource Satish Panchal.
				System.out.println("======username====="+username);
				activeDirectory = new ActiveDirectory("SVC-FR-HERMESACCOUNT","n9giejA8CY42Q4","corp.capgemini.com"); //Hermes20100
				//old password before till 13/11/2024 was n9giejA8CY42Q2
				//activeDirectory = new ActiveDirectory("SVC-IN-HERMESBOT","wqdGKbySE&ZB75","corp.capgemini.com"); //Hermes20100
		        //Hermes20210331
		
				NamingEnumeration<SearchResult> result;
				result = activeDirectory.searchUser(corpId,"username",null);
			if(result.hasMoreElements()) {
				  
					SearchResult rs= (SearchResult)result.next();
					Attributes attrs = rs.getAttributes();	
				if(attrs!=null){
					  
				    
					String temp = attrs.get("samaccountname").toString();
					logger.info("Username:" + temp.substring(temp.indexOf(":")+1));
					/*if(attrs.get("member") != null && attrs.get("member").get(0) != null) {
						Attributes newAttr = (Attributes) attrs.clone();
						attrs.put(attrID, val)
					}*/
					
					if(attrs.get("givenname") != null && attrs.get("givenname").get(0) != null){
						temp = attrs.get("givenname").get(0).toString();
						//logger.info("Name         :" + temp.substring(temp.indexOf(":")+1).trim());
						employeeDTO.setFirstName(temp.substring(temp.indexOf(":")+1).trim());	
					}
					//start dkaushik for pchostname
					if(attrs.get("distinguishedName") != null && attrs.get("distinguishedName").get(0) != null){
						temp = attrs.get("distinguishedName").get(0).toString();
						logger.info("temp "+temp);
						pcHostname.newConnection();
						String hostname = pcHostname.getUser(temp);
						logger.info(hostname);
						//logger.info("capgemini-employeetypes         :" + temp.substring(temp.indexOf(":")+1).trim());
						employeeDTO.setPcSerialNumber(hostname);	
					}
					//end dkaushik for pchostname
					if(attrs.get("sn") != null && attrs.get("sn").get(0) != null){
						temp = attrs.get("sn").get(0).toString();
						//logger.info("sn	:" + temp.substring(temp.indexOf(":")+1));
						employeeDTO.setLastName(temp.substring(temp.indexOf(":")+1).trim());
					}
					
					if(attrs.get("employeeNumber") != null && attrs.get("employeeNumber").get(0) != null){
						temp = attrs.get("employeeNumber").get(0).toString();
						//logger.info("employeeNumber         :" + temp.substring(temp.indexOf(":")+1).trim());
						employeeDTO.setEmpId(temp.substring(temp.indexOf(":")+1).trim());	
					}
					
					if(attrs.get("capgemini-GlobalID") != null && attrs.get("capgemini-GlobalID").get(0) != null){
						temp = attrs.get("capgemini-GlobalID").get(0).toString();
						//logger.info("capgemini-GlobalID         :" + temp.substring(temp.indexOf(":")+1).trim());
						employeeDTO.setGgId(temp.substring(temp.indexOf(":")+1).trim());	
					}
					if(attrs.get("employeeType") != null && attrs.get("employeeType").get(0) != null){
						temp = attrs.get("employeeType").get(0).toString();
						//logger.info("capgemini-employeetypes         :" + temp.substring(temp.indexOf(":")+1).trim());
						employeeDTO.setEmployeeTypes(temp.substring(temp.indexOf(":")+1).trim());	
					}
					
					if(attrs.get("capgemini-Grade") != null && attrs.get("capgemini-Grade").get(0) != null){
						temp = attrs.get("capgemini-Grade").get(0).toString();
						//logger.info("capgemini-Grade	:" + temp.substring(temp.indexOf(":")+1));
						ListIterator<GlobalGrade> itr = this.globalGradeService.listGlobalGrades().listIterator();
						String gradeName = temp.substring(temp.indexOf(":")+1);
						GlobalGrade globalgradeCheck = null;
						while(itr.hasNext()) {
							globalgradeCheck = itr.next();
							if(globalgradeCheck.getName().matches(gradeName)) {
								employeeDTO.setGlobalGrade(globalgradeCheck.getGlobalGradeId());
								break;
							}
						}
						//employeeDTO.setGlobalGrade(temp.substring(temp.indexOf(":")+1).trim());
					}
					
					
					if(attrs.get("co") != null && attrs.get("co").get(0) != null){
						temp = attrs.get("co").get(0).toString(); // get country code based on country name new function
						//logger.info("co	:" + temp.substring(temp.indexOf(":")+1));
						String countryName = temp.substring(temp.indexOf(":")+1);
						ListIterator<Country> itr = this.countryList.listIterator();
						Country countryChk = null;
						while(itr.hasNext()) {
							countryChk = itr.next();
							if(countryChk.getCountryName().matches(countryName)) {
								employeeDTO.setCountry(countryChk);
								break;
							}
						}
						//employeeDTO.setCountry(new Country().getCountryName((temp.substring(temp.indexOf(":")+1).trim()));
					}
					
					if(attrs.get("capgemini-EntityLevel3") != null && attrs.get("capgemini-EntityLevel3").get(0) != null){
						temp = attrs.get("capgemini-EntityLevel3").get(0).toString();
						employeeDTO.setCapgemEntity(temp.substring(temp.indexOf(":")+1).trim());
					}
					
					if(attrs.get("manager") != null && attrs.get("manager").get(0) != null) {
						temp = attrs.get("manager").get(0).toString(); // first sub string from : then from = to get corp id
						temp=temp.substring(temp.indexOf("=")+1,temp.indexOf(",")).trim();
						Employee cgManager = this.employeeService.getEmployeeByCorpId(temp);
						if(cgManager != null) {
							employeeDTO.setManager(cgManager.getFirstName()+" "+cgManager.getLastName());
							employeeDTO.setManagerEmail(cgManager.getEmail());
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
										employeeDTO.setManager(temp);
												
									}
									if(attrs1.get("sn") != null && attrs1.get("sn").get(0) != null){ // LAST NAME
										temp = attrs1.get("sn").get(0).toString().substring(temp.indexOf(":")+1).trim();
										employeeDTO.setManager(employeeDTO.getManager()+" "+temp);
									}
									if(attrs1.get("mail") != null && attrs1.get("mail").get(0) != null){
										temp = attrs1.get("mail").get(0).toString().substring(temp.indexOf(":")+1).trim();
										employeeDTO.setManagerEmail(temp);
									}
								}
							} // 2nd CORP DIR call ends
							
							
						}
					}
					
					if(attrs.get("mail") != null && attrs.get("mail").get(0) != null){
						temp = attrs.get("mail").get(0).toString();
						employeeDTO.setEmail(temp.substring(temp.indexOf(":")+1).trim());
					}
					else temp = "";
					
				}
				
			
				
			} else  {
				logger.info("No search result found!");
				employeeDTO.setFirstName(null);
				employeeDTO.setLastName(null);
				employeeDTO.setEmail(null);
				
				logger.info(employeeDTO);
				logger.info("corpId not exits in active directory");
			}
		   activeDirectory.closeLdapConnection();
		}
		
		
			catch (NamingException e ) {
				String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
				
				logger.info(loggedInUser);
				e.getExplanation();
				logger.error(" Some Problem occurs :- "+e.getMessage());
			
				
			}
			
		}
		logger.info("checkCorpIdExistsInPreOnb "+employeeDTO);
		   return employeeDTO;
	}
	
	
		
	

	//Mohini adding start
	
	@RequestMapping(value = {"/preOnboardingSearch"}, method = RequestMethod.GET)
	public String preOnboardingList(Model model, HttpServletRequest request) {

		Employee e = new Employee();
		model.addAttribute("employee", e);
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
        role_id = (String) session.getAttribute("RoleName");
			if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
				e.setUserReadOnly(true);
				model.addAttribute("checkUserType", "ViewMode");
			}else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
					e.setUserManagement(true);
					model.addAttribute("checkUserTypeforUM", "UserManagement");
			}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
				
				model.addAttribute("checkUserType", "RM");
				
			}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
				e.setEMReadOnly(true);//EM
	 			model.addAttribute("checkUserType", "BundleEM");
	 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
	 			  
	 			model.addAttribute("checkUserType", "RM_PMO");
	 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
    			  
	 			model.addAttribute("checkUserType", "ASL");
    		  }
		
		return "preOnboardingSearch";
	}
	
	
		@RequestMapping(value = "/preOnbSearch", method = RequestMethod.POST)
		public String preOnboardingSearch(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
				@ModelAttribute("employee") Employee emp) {
			
			//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			session = request.getSession();
			role_id = (String) session.getAttribute("RoleName");
			
			List<PreOnbEmployee> employeeSearchList = preOnbEmpService.preOnboardingSearch(emp, role_id);
			
			model.addAttribute("employeeList", employeeSearchList);
			
			
			if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
				
			
				model.addAttribute("checkUserType", "RM");
				
			}else if(role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) { //BIS PMO role
				
				
				model.addAttribute("checkUserTypeforUM", "UserManagement");
				
			}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
				
	 			model.addAttribute("checkUserType", "BundleEM");
	 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
	 			  
	 			model.addAttribute("checkUserType", "RM_PMO");
	 		}else if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
               
                model.addAttribute("checkUserType", "ViewMode");
			}else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
    			  
				model.addAttribute("checkUserType", "ASL");
    		}
 		 
			
			
			for(int i=0 ; i < employeeSearchList.size() ; i++){  //??? doubt
				
				if(employeeSearchList.get(i).getBis() != null){
					int bisId = employeeSearchList.get(i).getBis().getBis_id();
					if(bisId == 1){
						employeeSearchList.get(i).getBis().setBis_Name("");
					}
				
				}
			}
			
			if(employeeSearchList != null && employeeSearchList.size() <= 0) {
				model.addAttribute("successMsg", "No Records found.");
			}
			

			return "preOnboardingSearch";
		}
		
		@RequestMapping(value = {"/editPreOnBoardingPage/{corpId}"}, method = RequestMethod.GET)
		public String editPreOnBoarding(@PathVariable("corpId") String corpId, Model model, HttpServletRequest request) {
			logger.info("CorpId :: "+corpId);
			//TimeZone.setDefault(TimeZone.getTimeZone("CET"));
			//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			session = request.getSession();
			role_id = (String) session.getAttribute("RoleName");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			
			if(role_id.equalsIgnoreCase(OnboardingConstants.RM) || role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers) || role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
				PreOnbEmployee preOnbEmployee = this.preOnbEmpService.getPreOnbEmployee(corpId);
				if( preOnbEmployee.getOnboardingEmail() !=null && preOnbEmployee.getOnboardingEmail()!="" &&!(preOnbEmployee.getOnboardingEmail().isEmpty())){
					Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmployee.getOnboardingEmail());
					preOnbEmployee.setBuddy(employessss);
					}
				
			    preOnbEmployee.setBillingStartDate(formatter.format(preOnbEmployee.getBillingDate()));  
			    preOnbEmployee.setOnboardingDate(formatter.format(preOnbEmployee.getJoiningDate()));
			    preOnbEmployee.setOffboardingDate(formatter.format(preOnbEmployee.getEndDate()));
			    if(preOnbEmployee.getDob() != null) {
			    	preOnbEmployee.setDobDate(formatter.format(preOnbEmployee.getDob()));
			    }
				model.addAttribute("employee", preOnbEmployee);
				if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
					model.addAttribute("checkUserType", "RM"); 	
				}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
					model.addAttribute("checkUserType", "RM_PMO"); 
				}else{
					model.addAttribute("checkUserType", "ViewMode"); 
				}
				model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(true));
				model.addAttribute("listCountry", this.getcountryList()); //this.countryService.listCountry());
				model.addAttribute("listTechnology", this.technologyService.listTechnology());
				model.addAttribute("listEmployees", this.employeeService.listManagers());
				//Engg - Start
				model.addAttribute("basList", this.basService.listBas());
				model.addAttribute("cfaoList", this.cfaoService.listCfao());
				//Engg - End
				model.addAttribute("listEmployeesByBundleEm",this.employeeService.listManagersByBundleEm());
				model.addAttribute("listEmployeesByEm",this.employeeService.listManagersByEm());
				model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
				model.addAttribute("listOffshoreEmIndia",this.employeeService.listOffshoreEmIndia());
				model.addAttribute("listGrades", this.gradeService.listGrades(preOnbEmployee.getCountry().getCountryId()));
				model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
				//model.addAttribute("listEntity", this.entityService.listEntity());
				model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
				model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEm());
				model.addAttribute("listBundleEm", this.bundleEmService.listBundleEmPreOnb());
				model.addAttribute("rescourceStatus" ,preOnbEmployee.getResourceStatus());
				model.addAttribute("VLANList", this.stateService.VLANList());
				List<Bis> fullListOfBis = this.bisService.fullListOfBis();
				
				long curr19 =  System.currentTimeMillis();
				model.addAttribute("listOfEmployees", this.listOfEmployees());
				logger.info("time for listOfEmployees "+ (System.currentTimeMillis() - curr19));
				
				//int trialBis = fullListOfBis.stream().filter(i -> i.getBis_id() >2).mapToInt(i -> i.getBis_id()).sum(); //practice
				//System.out.println("trialBis "+trialBis);
				model.addAttribute("fullListOfBis",fullListOfBis);
				
				return "PreOnbRMEdit";
				
			}else if(role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
				Employee employee = this.preOnbEmpService.getEmpFromPreOnboarding(corpId);
				
				if( employee.getOnboardingEmail() !=null && employee.getOnboardingEmail()!="" &&!(employee.getOnboardingEmail().isEmpty())){
					Employee employessss = this.preOnbEmpService.getPreOnbEmployees(employee.getOnboardingEmail());
					employee.setBuddy(employessss);
					}
				PreOnbEmployee preOnbEmp = this.preOnbEmpService.getPreOnbEmployee(corpId);
				if( preOnbEmp.getOnboardingEmail() !=null && preOnbEmp.getOnboardingEmail()!="" &&!(preOnbEmp.getOnboardingEmail().isEmpty())){
					Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmp.getOnboardingEmail());
					preOnbEmp.setBuddy(employessss);
					}
				employee.setPreonbemp(preOnbEmp);
				//Engg - Start
				employee.setEngg(preOnbEmp.getEngg());
				model.addAttribute("psaIdFlag", preOnbEmp.isPSAIdReq());
				model.addAttribute("enggFlag", preOnbEmp.getEngg());
				model.addAttribute("basList", this.basService.listBas());
				model.addAttribute("cfaoList", this.cfaoService.listCfao());
				//Engg - End
				//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				employee.setBillingStartDate(formatter.format(employee.getBillingDate()));  
				employee.setOnboardingDate(formatter.format(employee.getJoiningDate()));
				employee.setOffboardingDate(formatter.format(employee.getEndDate()));
				if(employee.getPSAIdReq()) {
					employee.setDobDate(formatter.format(employee.getDob()));
				}
				//logger.info("employee +++++++++"+employee.getHRAReq());
				model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(true));
				model.addAttribute("listCountry", this.getcountryList()); //this.countryService.listCountry());
				model.addAttribute("listTechnology", this.technologyService.listTechnology());
				model.addAttribute("listEmployees", this.employeeService.listManagers());
				model.addAttribute("listEmployeesByBundleEm",this.employeeService.listManagersByBundleEm());
				model.addAttribute("listEmployeesByEm",this.employeeService.listManagersByEm());
				model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
				model.addAttribute("listOffshoreEmIndia",this.employeeService.listOffshoreEmIndia());
				model.addAttribute("listGrades", this.gradeService.listGrades(employee.getCountry().getCountryId()));
				model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
				//model.addAttribute("listEntity", this.entityService.listEntity());
				model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
				model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEmPreonb());
				
				model.addAttribute("listBundleEm", this.bundleEmService.listBundleEmPreOnb());
				model.addAttribute("demandTypeList", this.demandtypeservice.demandTypeList());
				List<Bis> fullListOfBis = this.bisService.fullListOfBis();
				model.addAttribute("fullListOfBis",fullListOfBis);
				model.addAttribute("listOfEmployees", this.listOfEmployees());
				model.addAttribute("listPSALib", this.psaLibTypeService.fullListOfPSALibType());
				model.addAttribute("listIndusGoals",this.indusGoalsService.listIndusGoals());//new
				model.addAttribute("listRoleTech",this.roleTechService.listRoleTech());//new
				model.addAttribute("employee", employee);
				employee.setUserManagement(true);
				model.addAttribute("checkUserTypeforUM", "UserManagement");
			} else if(role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)){
				//employee.setUserReadOnly(true);
				model.addAttribute("checkUserType", "ViewMode");
			
			}else if (role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
				model.addAttribute("checkUserType", "RM_PMO");
				PreOnbEmployee preOnbEmployee = this.preOnbEmpService.getPreOnbEmployee(corpId);
				if( preOnbEmployee.getOnboardingEmail() !=null && preOnbEmployee.getOnboardingEmail()!=null){
					Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmployee.getOnboardingEmail());
					preOnbEmployee.setBuddy(employessss);
					}
				return "PreOnbRMEdit";
			}else if (role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) { //role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)
				model.addAttribute("checkUserType", "BundleEM");
				PreOnbEmployee preOnbEmployee = this.preOnbEmpService.getPreOnbEmployee(corpId);
				if( preOnbEmployee.getOnboardingEmail() !=null && preOnbEmployee.getOnboardingEmail()!=null){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmployee.getOnboardingEmail());
				preOnbEmployee.setBuddy(employessss);
				}
				
				model.addAttribute("preonbemployee", preOnbEmployee);
				
				
				//List<PreOnbEmployee> employeeSearchList = preOnbEmpService.preOnboardingSearch(emp, role_id);
				//model.addAttribute("employeeList", employeeSearchList);
				if(preOnbEmployee.getResourceStatus().equalsIgnoreCase(OnboardingConstants.statusRMRejected)) {
					// add page for edit all fields

					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					preOnbEmployee.setOnboardingDate(dateFormat.format(preOnbEmployee.getJoiningDate())); 
					preOnbEmployee.setBillingStartDate(dateFormat.format(preOnbEmployee.getBillingDate()));
					if(!(preOnbEmployee.getDob() == null)) {
					preOnbEmployee.setDobDate(dateFormat.format(preOnbEmployee.getDob()));
						
					}
					if( preOnbEmployee.getOnboardingEmail() !=null && preOnbEmployee.getOnboardingEmail()!=null){
						Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmployee.getOnboardingEmail());
						preOnbEmployee.setBuddy(employessss);
						}
					preOnbEmployee.setOffboardingDate(dateFormat.format(preOnbEmployee.getEndDate()));
					DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
					preOnbEmployee.setCreationdateString(dateFormat1.format(preOnbEmployee.getCreationdate()));
					preOnbEmployee.setEMSubmitDTString(dateFormat1.format(preOnbEmployee.getEMSubmitDT()));
					model.addAttribute("preonbemployee", preOnbEmployee);
					model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(true));
					model.addAttribute("listCountry", this.getcountryList()); //this.countryService.listCountry());
					model.addAttribute("listTechnology", this.technologyService.listTechnology());
					model.addAttribute("listEmployees", this.employeeService.listManagers());
					model.addAttribute("listEmployeesByBundleEm",this.employeeService.listManagersByBundleEm());
					model.addAttribute("listEmployeesByEm",this.employeeService.listManagersByEm());
					model.addAttribute("listResourceManager",this.resourceMgrService.listResourceManagerPreOnb(preOnbEmployee.getCountry().getCountryId())); //change
					model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
					model.addAttribute("listOffshoreEmIndia",this.employeeService.listOffshoreEmIndia());
					model.addAttribute("listGrades", this.gradeService.listGrades(preOnbEmployee.getCountry().getCountryId()));
					model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
					//model.addAttribute("listEntity", this.entityService.listEntity());
					model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
					model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEm());
					model.addAttribute("listBundleEm", this.bundleEmService.listBundleEmPreOnb());
					model.addAttribute("listState",this.stateService.listState(preOnbEmployee.getCountry().getCountryId(), true));
					model.addAttribute("listIndusGoals",this.indusGoalsService.listIndusGoals());
					model.addAttribute("listRoleTech",this.roleTechService.listRoleTech());
					//model.addAttribute("listProjects", this.projectService.listProject());
					model.addAttribute("listPSALib", this.psaLibTypeService.fullListOfPSALibType());
					model.addAttribute("VLANList", this.stateService.VLANList());
					model.addAttribute("newStateList", this.stateService.newStateList());
					model.addAttribute("demandTypeList", this.demandtypeservice.demandTypeList());
					List<Bis> fullListOfBis = this.bisService.fullListOfBis();
					model.addAttribute("fullListOfBis",fullListOfBis);
					
					//Engg - Start
					model.addAttribute("basList", this.basService.listBas());
					model.addAttribute("cfaoList", this.cfaoService.listCfao());
					//Engg - End
					
					//return "EditByBundleEM";
					return "addPreOnboardRec"; 
				}else /*if(preOnbEmployee.getResourceStatus().equalsIgnoreCase(OnboardingConstants.statusInitial) || preOnbEmployee.getResourceStatus().equalsIgnoreCase(OnboardingConstants.statusRMApproved))*/{
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					preOnbEmployee.setOnboardingDate(dateFormat.format(preOnbEmployee.getJoiningDate())); 
					preOnbEmployee.setBillingStartDate(dateFormat.format(preOnbEmployee.getBillingDate()));
					preOnbEmployee.setOffboardingDate(dateFormat.format(preOnbEmployee.getEndDate()));
					if( preOnbEmployee.getOnboardingEmail() !=null && preOnbEmployee.getOnboardingEmail()!=null){
						Employee employessss = this.preOnbEmpService.getPreOnbEmployees(preOnbEmployee.getOnboardingEmail());
						preOnbEmployee.setBuddy(employessss);
						}
					model.addAttribute("employee", preOnbEmployee);
					model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(true));
					model.addAttribute("listCountry", this.getcountryList()); //this.countryService.listCountry());
					model.addAttribute("listTechnology", this.technologyService.listTechnology());
					model.addAttribute("listEmployees", this.employeeService.listManagers());
					model.addAttribute("listEmployeesByBundleEm",this.employeeService.listManagersByBundleEm());
					model.addAttribute("listEmployeesByEm",this.employeeService.listManagersByEm());
					model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
					model.addAttribute("listOffshoreEmIndia",this.employeeService.listOffshoreEmIndia());
					model.addAttribute("listGrades", this.gradeService.listGrades(preOnbEmployee.getCountry().getCountryId()));
					model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
					//model.addAttribute("listEntity", this.entityService.listEntity());
					model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
					model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEm());
					model.addAttribute("listBundleEm", this.bundleEmService.listBundleEmPreOnb());
					model.addAttribute("VLANList", this.stateService.VLANList());
					//model.addAttribute("listOfEmployeedata", this.listOfEmployeedata());
					List<Bis> fullListOfBis = this.bisService.fullListOfBis();
					model.addAttribute("fullListOfBis",fullListOfBis);
					return "PreOnbRMEdit";
				}
			}
			model.addAttribute("VLANList", this.stateService.VLANList()); // new
			return "PreOnboardingEdit";
			
		}
		
		@RequestMapping(value = "/PreOnbStates", method = RequestMethod.GET)
		public @ResponseBody CountriesDTO statesForCountry(			
				@RequestParam(value = "countryId", required = true) int countryId) {
			CountriesDTO countriesDTO = new CountriesDTO();
			boolean isEMPage = true;
			List<State> stateList = this.stateService.listState(countryId, true);
			
			
			List<Vendor> vendorList = this.vendorService.listVendor(countryId, true);
			List<Grade> gradeList = this.gradeService.listGrades(countryId);
			List<ResourceManager> resourceMgrList = this.resourceMgrService.listResourceManagerPreOnb(countryId);//this.resourceMgrService.listResourceManager(countryId);
			Spoc spoc = this.spocService.fetchSpoc(countryId);
			countriesDTO.setStateList(stateList);
			countriesDTO.setVendorList(vendorList);
			countriesDTO.setSpoc(spoc);
			countriesDTO.setGradeList(gradeList);
			countriesDTO.setResourceMgrList(resourceMgrList);
			return countriesDTO;
		}
		
		@RequestMapping(value = "/getBisList", method = RequestMethod.GET)
		public @ResponseBody List<Bis> getBisList(@RequestParam(value="ppid", required = true) int ppid) {
			logger.info("inside getBisList");
			PrimaryProgram PP = this.primaryProgramService.getPrimaryProgramById(ppid);
			logger.info("inside getBisList "+PP.getBisList().size());
			return PP.getBisList();
		}
		
		
		@RequestMapping(value = "/getEMList", method = RequestMethod.GET)
		public @ResponseBody List<Employee> getEMList(@RequestParam(value="ppid", required = true) int ppid) {//Engg
			logger.info("inside getEMList");
			List<Employee> emList = this.preOnbEmpService.listEm(ppid);
			return emList;
		}
		
		@RequestMapping(value = "/getManagerListNew", method = RequestMethod.GET)
		public @ResponseBody List<Employee> getManagerListNew(@RequestParam(value="ppid", required = true) int ppid) {//Engg
			logger.info("inside getEMList");
			List<Employee> emList = this.preOnbEmpService.listManagersNew(ppid);
			return emList;
		}
		
		
		@RequestMapping(value = "/employee/insert", method = RequestMethod.POST)
		public String insertEmployee(Model model,HttpServletRequest request, RedirectAttributes redirectAttributes,
				@ModelAttribute("employee") Employee emp) {
			logger.info("Inside insertEmployee method for "+emp.getCorpId()+" PP : "+emp.getPrimaryprogram().getPrimaryProgramId());
			final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			emp.setCreatedBy(loggedUser);
			
			//Engg - Start
			if(emp.getPrimaryprogram().getPrimaryProgramId() == 10) {logger.info("Set Engg as true if PP is 10");
				emp.setEngg(true);
			}
			else {
				emp.setEngg(false);
			}
			//Engg - End
			
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			emp.setCreationdate(sqlDate);
			//mehens- buddy
			if(emp.getOnboardingEmail() == null || emp.getOnboardingEmail().equals("") || emp.getOnboardingEmail().isEmpty()) {
				emp.setBuddy(null);
				emp.setOnboardingEmail(null);
			}
			else if(emp.getOnboardingEmail() != null || !emp.getOnboardingEmail().equals("")) {
				Employee buddy = employeeService.getEmployeeByEmail(emp.getOnboardingEmail());
				emp.setBuddy(buddy);
			}
			
			try {
				EmployeeRoles emprole = this.employeeroleservice.getEmployeeRolesId(emp.getEmprole());
				String docPath = "http://frparhermesto/HERMES_Academy/learning.html";
				
				String welcomeSubject = "Welcome to HERMES PSA";
				String OPELWelcomeSubject = "PSA OPEL Welcome email – please read carefully";
				session = request.getSession();
				role_id = (String) session.getAttribute("RoleName");
				int cohortMappingInserted = 0;
					//String roleId = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
					if(role_id.equalsIgnoreCase(OnboardingConstants.UserManagement) || role_id.equalsIgnoreCase(OnboardingConstants.Other_users)) {
						
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						emp.setJoiningDate(df.parse(emp.getOnboardingDate()));
						emp.setEndDate(df.parse(emp.getOffboardingDate()));
						emp.setBillingDate(df.parse(emp.getBillingStartDate()));
						
						if(emp.getDobDate() != null && !"".equals(emp.getDobDate())) {
							emp.setDob(df.parse(emp.getDobDate()));
						}
						//TimeZone.setDefault(TimeZone.getTimeZone("CET"));
						this.preOnbEmpService.insertEmployee(emp);
						
						//if(emp.getPrimaryprogram().getMoodleEnrolReq() && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-"))) {
						//mehens - new condition added for OPEL primary program
						if((emp.getPrimaryprogram().getMoodleEnrolReq() && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-"))) || (emp.getPrimaryprogram().getPrimaryProgramId() == 8 && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-")) ) ) {
						
							if(emp.getEmpType().equals("Internal")) {//temporary change - no enrolment for External resources 
								System.out.println("Before Moodle User addition");
								this.moodleService.addUser(emp, "manual");
								System.out.println("After Moodle user addition");
							
								List<ResourceCohortMapping> cohortList = new ArrayList<ResourceCohortMapping>();
								ResourceCohortMapping cohortRecord;
								//int tnm = (emp.isTimeNMaterial())? 1 : 0;
								
								int tnm = 0;
								
								//if(emp.getTimeMat().equals("Time & Material") && (emp.getPrimaryprogram().getPrimaryProgramId() != 8)) {
								/*if(emp.getTimeMat().equals("Time & Material") && (emp.getPrimaryprogram().getPrimaryProgramId() == 1)) {
									//T&M cohort must be added only for Hermes resources if T&M dropdown value is Time & Material
									tnm = 1;
								}*/
									 
								List<FieldCohortMapping> cohortMappingList = this.fieldCohortService.getCohortForFields(tnm, emp);
								if(cohortMappingList != null) {
									for(FieldCohortMapping f : cohortMappingList) {
										cohortRecord = new ResourceCohortMapping();
										cohortRecord.setCorp_id(emp.getCorpId().toLowerCase());
										cohortRecord.setCreationDate(new Date());
										cohortRecord.setCohort(f.getCohort());
										cohortList.add(cohortRecord);
									}
								
									cohortMappingInserted = this.resourceCohortService.insert(cohortList);
								}
							}
						}
					}
		
					
					redirectAttributes.addFlashAttribute("successMsg", "Resource on-boarding data saved successfully.");
					if(cohortMappingInserted == 1) {
						redirectAttributes.addFlashAttribute("cohortMsg", "Cohort Mapped. Please download it from ..");
					}else if(emp.getPrimaryprogram().getMoodleEnrolReq()){
						redirectAttributes.addFlashAttribute("errorMsg", "Cohort Mapping failed...");
					}
					Map<String, String> inlineImages = new HashMap<String, String>();
					ArrayList<String> imgArr = new ArrayList<String>();
					//imgArr.add(0, request.getSession().getServletContext().getRealPath("/resources/images")+"\\mail1.png");
					//imgArr.add(1, request.getSession().getServletContext().getRealPath("/resources/images")+"\\mail2.png");
					inlineImages.put("image1", request.getSession().getServletContext().getRealPath("/resources/images")+"\\mail1.png"); 
					inlineImages.put("image2", request.getSession().getServletContext().getRealPath("/resources/images")+"\\mail2.jpg");
					
					// Welcome Email - Common as told by Marie
			        
			        //boolean isIndia = (emp.getCountry().getCountryName().equalsIgnoreCase("India"))? true : false;
					/*if(emp.getPrimaryprogram().getPrimaryProgramName().equalsIgnoreCase("OPEL")) {
						MailSender.sendImageMail(psaMailUtility.prepareMailId(emp.getEmail()), MailUtility.OPELWelcomeEmail1(emp.getFirstName()),OPELWelcomeSubject, null);
					}else {
			        MailSender.sendImageMail(psaMailUtility.prepareMailId(emp.getEmail()), MailUtility.createFirstWelcomeEmailCommon(emp.getFirstName(), docPath,
							emp.getSpoc().getSpocName(), emp.getSpoc().getSpocEmail(), isIndia, imgArr), welcomeSubject, inlineImages);
			        }*/
			        
			        if(emp.getPrimaryprogram().getWelcomeEmailReq() && !emp.getPrimaryprogram().getMoodleEnrolReq() && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-"))) { // for PP with their own welcome emails
			        	//send specific PP mail at time of onboarding
			        	logger.info("sendDynamicWelcomeEmail");
			        	psaMailUtility.sendDynamicWelcomeEmail(MailUtility.dynamicWelcomeEmail(emp), null);
			        	emp.setWelcomeEmailFlag(1);
			        	this.employeeService.updateEmployee(emp);
			        	logger.info("Welcome email sent at onboarding time to "+emp.getEmail());
			        	//add welcome email API call
			        }else if(!emp.getPrimaryprogram().getWelcomeEmailReq() || (emp.getCorpId().startsWith("x-") || emp.getCorpId().startsWith("X-"))) { //for PP with no welcome email
			        	//no welcome email from Tool
			        	logger.info("no welcome email from Tool");
			        	emp.setWelcomeEmailFlag(0);
			        	this.employeeService.updateEmployee(emp);
			        	logger.info("No Welcome email for "+emp.getEmail());
			        	
			        } // else welcomeEmailReq is yes and moodleEnrolReq is yes for PP, mail will be send by scheduler only after enrolment is done ; welcomeEmailFlag in employee is null;
					
					//PROD link - not in use
					//this.microsoftFlowRest.POSTRequest("https://prod-45.westeurope.logic.azure.com:443/workflows/f6a044852bd748efb631599294645588/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=6Cuk8t4HSWtvW8un_tNm9XdSYmU1xj8O7_hF6GSMIAU", emp.getEmail()); //PROD
					//Test Link - not in use
					//this.microsoftFlowRest.POSTRequest("https://prod-12.westeurope.logic.azure.com:443/workflows/a2d6a6a36d7a4d0a873b381c310baea9/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=zDrwLNEeAcGMNXtmoEDyHl9x5a9PFIAbZ3yqHhCkPEY", "bhavna.manjrekar@capgemini.com"); - Test
			        
			       //New link - Valerie 2023 - in use
			       //this.microsoftFlowRest.POSTRequest("https://prod-132.westeurope.logic.azure.com:443/workflows/97b3d0617084463b815b0dc78ee221e7/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=SfWVAxZvikVsqfKfZfheTfLhUr1YCVuL-7c_fIxF7DY", emp.getEmail());//new URL
			        
			        
			        /**Mail to PPaaS Team & cc for JIRA and Confluence ID start during BIS PMO Submittion - NEW - MEHENS*/
					if(emp.getPrimaryprogram().getConfluenceaccess() && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-")))	{
						JiraIDCreator jiraCreator = preOnbEmpService.getJiraCreatorDetails();
						if(jiraCreator != null) {
							logger.info("Start of JIRA and Confluence mail - start");
							DLList dlCC = preOnbEmpService.getDLListbyRole("CC");
							DLList dlHermesOnb = preOnbEmpService.getDLListbyRole("HermesOnb");
							//String empName = emp.getFirstName()+" "+emp.getLastName()+" (CORP ID: "+emp.getCorpId()+")";
							String empName = emp.getFirstName()+" "+emp.getLastName();
							//String jiraSubject = "[HERMES] Creation of JIRA and Confluence ID for "+empName +" ("+emp.getCorpId()+"), "+emp.getLocation().getStateName();
							String jiraSubject = "[HERMES] Creation of JIRA and Confluence ID for "+empName +" ("+emp.getCorpId()+")";
							
							//String [] ccList = {psaMailUtility.prepareMailId(emp.getManagerEmail()),psaMailUtility.prepareMailId(dlCC.getEmail())};
							String [] ccList = {psaMailUtility.prepareMailId(dlHermesOnb.getEmail())};
							String[] recipientList = {psaMailUtility.prepareMailId(jiraCreator.getEmail())};
							/*MailSender.send(recipientList, ccList, MailUtility.createJiraIDMail(jiraCreator.getName(), empName, emp.getEmpId(),emp.getCorpId())
									, jiraSubject);*/
							
							//BCC
							DLList dlBcc2 = preOnbEmpService.getDLListbyRole("bcc");
							MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(dlBcc2.getEmail()), ccList, MailUtility.createJiraIDMailNew(jiraCreator.getName(), empName, emp.getEmpId(),emp.getCorpId(), emp)
									, jiraSubject);
							logger.info("Creation of JIRA and Confluence mail sent for "+empName+" "+emp.getCorpId());
						}
					}
					/**Mail to PPaaS team & cc for JIRA and Confluence end*/
			        
			        //ICRES-NEW-START
					boolean isIndia = (emp.getCountry().getCountryName().equalsIgnoreCase("India"))? true : false;
					if(isIndia) {
						switch(emp.getLocation().getStateId()){
						case 4:
						case 5:
						case 20:
						case 34:	
							BisPMOMap bisPmo = employeeService.getBISFromPMO(emp.getBis().getBis_id());
							Boolean ODCAccessRequired = true;
							Boolean isOPEL = false;
							logger.info("For location "+emp.getLocation().getStateName()+" and Corp Id "+emp.getCorpId());
							if(emp.getPrimaryprogram().getPrimaryProgramName().equalsIgnoreCase("OPEL")) {
								isOPEL = true;
							}
							//ODCAccessRequired = (emp.getBis().getBis_id() == 18 ||emp.getBis().getBis_id() == 19 || emp.getBis().getBis_id() == 20)? false: true;
							
							//ODCAccessRequired = (emp.getPrimaryprogram().getPrimaryProgramId() == 1)? true : false;
							ODCAccessRequired = (emp.getPrimaryprogram().getPrimaryProgramId() == 1 || emp.getPrimaryprogram().getPrimaryProgramId() == 5)? true : false;
							
							//if(ODCAccessRequired && !isOPEL) {
							
							if(ODCAccessRequired) {
								logger.info("ODC access req");
								
								String ODCAccessSubj = "STELLANTIS HERMES ODC access request - "+emp.getCorpId();
								List<DLList> recipient = preOnbEmpService.getMultipleDLListbyRole(emp.getLocation().getStateName()+"ICRES");
								//String[] ccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getEmail())};
								String[] recipientList = new String[recipient.size()];
								for(int reci=0; reci < recipient.size(); reci++) {
									recipientList[reci] = psaMailUtility.prepareMailId(recipient.get(reci).getEmail());
								}
								//MailSender.send(recipientList, ccList, MailUtility.ODCAccessBISPMOMail(emp) , ODCAccessSubj);
								
								//BCC
								DLList dlBcc5 = preOnbEmpService.getDLListbyRole("bcc");
								String[] ccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getEmail()),psaMailUtility.prepareMailId(dlBcc5.getEmail())};//new change from BCC to CC
								logger.info("Changed BCC to CC");
								MailSender.send(recipientList, ccList, MailUtility.ODCAccessBISPMOMail(emp) , ODCAccessSubj);
								
								//MailSender.sendNew(recipientList, psaMailUtility.prepareMailId(dlBcc5.getEmail()), ccList, MailUtility.ODCAccessBISPMOMail(emp) , ODCAccessSubj);
								logger.info(ODCAccessSubj+" mail sent for: "+emp.getCorpId());
							}
							
							if(ODCAccessRequired) {
								logger.info("to new resource for ODC");
								String resSubj="Welcome to the Stellantis Team!";
								String[] resRecipientList = {psaMailUtility.prepareMailId(emp.getEmail())};
								String[] resCCList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getManager().getEmail()),psaMailUtility.prepareMailId(emp.getRequestor().getEmail())};
								//BCC
								DLList dlBccRes = preOnbEmpService.getDLListbyRole("bcc");
								
								MailSender.sendNew(resRecipientList,psaMailUtility.prepareMailId(dlBccRes.getEmail()) ,resCCList,MailUtility.resODCMail(emp),resSubj);
								logger.info(resSubj+" mail sent for: "+emp.getCorpId());
							}
							
							break;
						default: System.out.println("NO ODC access & Seat mail allocation for "+emp.getCorpId());
						}
					}
					//ICRES-NEW-END
			        
			        if(emp.getPSAIdReq()) {
			        	logger.info("only when PSA ID Request is true");
			        	// mail to RM PMO for initiating PSA ID
						
			        	Employee archivedEmp = this.employeeService.checkArchivedEmployeeExists(emp.getCorpId());
						String empName = emp.getFirstName()+" "+emp.getLastName()+"(CORP ID: "+emp.getCorpId()+")";
						String subject = "[User Management Tool] Stellantis ID Generation for "+empName+" on "+emp.getPrimaryprogram().getPrimaryProgramName();
						Employee loggedUserEmp = this.employeeService.getEmployeeByCorpId(loggedUser);	
						
						RmPMO rmPMO = preOnbEmpService.getRmPmoDetails();
						
						if(rmPMO != null) {
							/*MailSender.send(psaMailUtility.prepareMailId(rmPMO.getEmail()), MailUtility.createRmPmoEMail(rmPMO.getRmPmoName(),empName, " from BIS - "+emp.getBis().getBis_Name(),
									loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment(), archivedEmp) , subject);*/
							
							String indusGoalName = indusGoalsService.getIndusGoalsId(emp.getIndusGoals()).getIndusGoalName();
							String roleTech = roleTechService.getRoleTechId(emp.getRoleTech()).getRoleTechName();
							
							//BCC
							logger.info("RM PMO Onboarding mail start");
							DLList dlBcc3 = preOnbEmpService.getDLListbyRole("bcc");
							MailSender.sendNew(psaMailUtility.prepareMailId(rmPMO.getEmail()),psaMailUtility.prepareMailId(dlBcc3.getEmail()), MailUtility.createRmPmoEMail(rmPMO.getRmPmoName(),empName, " from BIS - "+emp.getBis().getBis_Name(),
									loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName(), url, emp.getComment(), archivedEmp, emp, indusGoalName, roleTech) , subject);
							logger.info("RM PMO onboarding mail sent: "+subject);
						}
						
			        }else { //mail triggered to Requestor about onboarding completed
			        	
			        	if(emp.getRequestor() != null) {
			        		String bill = emp.getBillingStartDate();
							String requestorSubj = "[User Management Tool]"+emp.getFirstName()+" "+emp.getLastName() +" (" +emp.getCorpId() +") – Onboarding completed ";
							String[] recipeintList = {psaMailUtility.prepareMailId(emp.getRequestor().getEmail()),psaMailUtility.prepareMailId(emp.getManagerEmail())};
							String[] ccList = {emp.getEM().getEmail()};//Engg
							//MailSender.send(recipeintList,ccList,MailUtility.createRequestorMail(emp, false), requestorSubj);
							//BCC
							DLList dlBcc4 = preOnbEmpService.getDLListbyRole("bcc");
							MailSender.sendNew(recipeintList,psaMailUtility.prepareMailId(dlBcc4.getEmail()),ccList,MailUtility.createRequestorMail(emp, false, emprole, bill), requestorSubj);
							logger.info("Onboarding completion mail to: "+emp.getRequestor().getEmail()+" for resource"+emp.getCorpId());
						}
			        	
			        }
			        
			        if(emp.getBuddy() != null) {//buddy - mail
			        	String sub = "[User Management Tool] Onboarding Buddy Details in "+emp.getPrimaryprogram().getPrimaryProgramName();
			        	String[] recipeintList = {psaMailUtility.prepareMailId(emp.getEmail())};
			        	String[] ccList = {psaMailUtility.prepareMailId(emp.getOnboardingEmail()),psaMailUtility.prepareMailId(emp.getManagerEmail())};
			        	MailSender.send(recipeintList,ccList,MailUtility.createBuddyMail(emp), sub);
			        	logger.info("buddy mail to resource "+emp.getEmail());
			        }
			 	
			} catch (Exception exception) {
				logger.error("Exceptions in updateEmployee " + exception.getMessage(), exception);
				redirectAttributes.addFlashAttribute("errorMsg", "Error while updating record.");
				model.addAttribute("employee", emp);
				return "redirect:/preOnboardingSearch";
			}
			return "redirect:/preOnboardingSearch";
		}
		
		
		
		//Mohini adding end
		
		public List<Country> getcountryList(){
			if(this.countryList == null) {
				this.countryList = this.countryService.listCountry();
			}
			
			return this.countryList;
		}

		public List<EmployeeRoles> getEmployeeRolesList() {
			if(this.EmployeeRolesList == null) {
				this.EmployeeRolesList = this.employeeroleservice.listEmployeeRoles();
			}
			return this.EmployeeRolesList;
		}


		/* @InitBinder
		    public void initBinder(WebDataBinder binder) {
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		        sdf.setLenient(true);
		        binder.registerCustomEditor(Date.class,"EMSubmitDT", new CustomDateEditor(sdf, true));
		        binder.registerCustomEditor(Date.class,"RMSubmitDT", new CustomDateEditor(sdf, true));
		        binder.registerCustomEditor(Date.class,"BISPMOSubmitDT", new CustomDateEditor(sdf, true));
		        binder.registerCustomEditor(Date.class,"RMPMOSubmitDT", new CustomDateEditor(sdf, true));
		        binder.registerCustomEditor(Date.class,"joiningDate", new CustomDateEditor(sdf1, true));
		        binder.registerCustomEditor(Date.class,"endDate", new CustomDateEditor(sdf1, true));
		        binder.registerCustomEditor(Date.class,"creationdate", new CustomDateEditor(sdf, true));
		    }*/
		

@RequestMapping(value = "/listOfEmployees", method = RequestMethod.GET)
public String listOfEmployees() {
    		/*SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
    		String currentTimestamp = df.format(new Date());*/
    		Employee emp = new Employee();
    		List<Employee> empList =null;
    		List<String> empListFirstName =new ArrayList<String>(); 
    		StringBuilder firstName =new StringBuilder();
    		String listofEmployess= new String();
    		//List<EmailReport> list = this.emailReportService.getEmailReportMapping();
    		emp.setEmpType(OnboardingConstants.ALL);
    		emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_ACTIVE);
    		//empList =employeeService.empReportFiltering(emp,null);
    		empList = this.preOnbEmpService.listBuddyEmp();//mehens-buddy
    		//logger.info("Preonboarding Controller buddyList Size "+empList.size());
    		logger.info("Preonboarding Controller buddy method");
    		for(int i=0; i<empList.size() ; i++)
    			{
    			empListFirstName.add(empList.get(i).getFirstName()+" "+ empList.get(i).getLastName() +" "+"("+empList.get(i).getCorpId()+")");
    			}
    		for(int j=0;  j<empListFirstName.size(); j++)
    			{
    				firstName.append(empListFirstName.get(j));
    				firstName.append(",");
    			}
    			listofEmployess= firstName.toString();
    			listofEmployess= listofEmployess.substring(0, listofEmployess.length()-1);
    	     return	listofEmployess;
    }

@RequestMapping(value = "/managermails", method = RequestMethod.GET)
public @ResponseBody Employee managermails(
		
		@RequestParam(value = "corpid", required = true) String  corpid)
 {
	Employee emp = new Employee();

    String[] corpId = corpid.split(" ");
    if(corpId!=null ) {
    String corpIds=corpId[2];
    String empId =null;
  
if(corpIds.contains("("))
		{
	corpIds=corpIds.replace("(", "");
	corpIds=corpIds.replace(")", "");
    empId= empdao.emailId(corpIds);
		}
else
{
	String diffCorpId=corpId[3];
	diffCorpId=diffCorpId.replace("(", "");
	diffCorpId=diffCorpId.replace(")", "");
	empId = empdao.emailId(diffCorpId);
}
	emp.setEmail(empId);
    }
	return emp;
	
}

}
