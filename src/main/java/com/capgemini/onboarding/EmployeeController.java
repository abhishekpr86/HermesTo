package com.capgemini.onboarding;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.query.QueryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

import com.capgemini.moodle.model.User;
//import com.aspose.cells.a.c.co;
import com.capgemini.onboarding.activedirectory.ActiveDirectory;
import com.capgemini.onboarding.constants.OnBoardingSQLQueries;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dao.EmployeeDAO;
import com.capgemini.onboarding.dto.BundleEmDTO;
import com.capgemini.onboarding.dto.CountriesDTO;
import com.capgemini.onboarding.dto.EmailDTO;
import com.capgemini.onboarding.dto.EmployeeDTO;
import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.onboarding.mail.MailUtility;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.BisRotation;
import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.EmailReport;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.EmployeeRoles;
import com.capgemini.onboarding.model.EmployeeTraining;
import com.capgemini.onboarding.model.EmployeeType;
import com.capgemini.onboarding.model.FieldCohortMapping;
import com.capgemini.onboarding.model.Grade;
import com.capgemini.onboarding.model.JiraIDCreator;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.PrimaryProgram;
import com.capgemini.onboarding.model.ResourceCohortMapping;
import com.capgemini.onboarding.model.ResourceManager;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.model.State;
import com.capgemini.onboarding.model.Technology;
import com.capgemini.onboarding.model.Training;
import com.capgemini.onboarding.model.Vendor;
import com.capgemini.onboarding.service.BasService;
import com.capgemini.onboarding.service.BisRotationService;
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
import com.capgemini.onboarding.service.PreOnbEmployeeService;
import com.capgemini.onboarding.service.PrimaryProgramService;
import com.capgemini.onboarding.service.ResourceCohortService;
import com.capgemini.onboarding.service.ResourceManagerService;
import com.capgemini.onboarding.service.RoleTechService;
import com.capgemini.onboarding.service.SpocService;
import com.capgemini.onboarding.service.StateService;
import com.capgemini.onboarding.service.TechnologyService;
import com.capgemini.onboarding.service.TrainingService;
import com.capgemini.onboarding.service.VendorService;
import com.capgemini.onboarding.util.PsaMailUtility;



@Controller
public class EmployeeController {

	private Logger logger = Logger.getLogger(EmployeeController.class);
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired(required = true)
	private GradeService gradeService;

	@Autowired(required = true)
	private TechnologyService technologyService;

	@Autowired(required = true)
	private CountryService countryService;

	@Autowired(required = true)
	private StateService stateService;

	@Autowired(required = true)
	private EmployeeRoleService employeeroleservice;
	
	@Autowired(required = true)
	private VendorService vendorService;

	@Autowired(required = true)
	private SpocService spocService;

	@Autowired(required = true)
	private GlobalGradeService globalGradeService;

	@Autowired(required = true)
	private TrainingService trainingService;
	
	@Autowired(required = true)
	private EntityService entityService;

	@Autowired(required = true)
	private CgEntityService cgEntityService;
	
	@Autowired(required = true)
	private OffshoreEmService offshoreEmService;
	
	@Autowired
	private BundleEmService bundleEmService;
	
	@Autowired(required=true)
	private DemandTypeService demandtypeservice;
	
	@Autowired(required = true)
	private ResourceManagerService resourceMgrService;
	
	@Autowired(required = true)
	private BisService bisService;
	
	@Autowired(required = true)
	private PrimaryProgramService primaryProgramService;
	
	@Autowired(required = true)
	private FieldCohortService fieldCohortService;
	
	@Autowired(required = true)
    private ResourceCohortService resourceCohortService;
	
	@Autowired(required = true)
	private BisRotationService bisRotationService;
	
	@Autowired(required = true)
	private MoodleService moodleService;
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
	private HttpSession session;
	
	private String role_id;
	
    @Autowired(required = true)
    private PsaMailUtility psaMailUtility;
	
	private List<EmployeeRoles> EmployeeRolesList;
	
    @Autowired(required = true)
    private EmailReportService emailReportService;
    
    @Autowired(required = true)
	private IndusGoalsService indusGoalsService;
    
    @Autowired(required = true)
	private RoleTechService roleTechService;
    
    @Autowired(required = true)
    private MoodleController moodleController;
    
   @Autowired(required = true)
    private HttpServletResponse httpResponse;
   
	@Value("${ad.activeDirUsername}")
	private static String username;
	
    @Autowired(required = true)
	private EmployeeDAO empdao;
	
    //Engg - Start
    @Autowired(required = true)
	private BasService basService;
    
    @Autowired(required = true)
	private CfaoService cfaoService;
    //Engg - Start



	@RequestMapping(value = { /*"/", */"/employees" }, method = RequestMethod.GET)
	public String listEmployees(Model model, HttpServletRequest request) {

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
		else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
			
			model.addAttribute("checkUserType", "RM");
			
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			e.setEMReadOnly(true);//EM
 			model.addAttribute("checkUserType", "BundleEM");
 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
 			  
 			model.addAttribute("checkUserType", "RM_PMO");
 		}
		
		
		model.addAttribute("employee", e);
		model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(false));
		model.addAttribute("listEmployees", this.employeeService.listManagers());
		model.addAttribute("listEmployeesByBundleEm",this.employeeService.listManagersByBundleEm());
		model.addAttribute("listEmployeesByEm",this.employeeService.listManagersByEm());
		model.addAttribute("listOffshoreEmIndia",this.employeeService.listOffshoreEmIndia());
		//model.addAttribute("listGrades", this.gradeService.listGrades());
		model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
		model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
		model.addAttribute("listCountry", this.countryService.listCountry());
		model.addAttribute("listTechnology", this.technologyService.listTechnology());
		model.addAttribute("listEntity", this.entityService.listEntity());
		model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
		model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEm());
		model.addAttribute("listBundleEm", this.bundleEmService.listBundleEm());
		List<Employee> employeeList = employeeService.getAllEmployees();
		List<Employee> empList = employeeService.modifyEmployeeList(employeeList);
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",fullListOfBis);
		model.addAttribute("employeeList", empList);
		model.addAttribute("listOfEmployeedata", this.listOfEmployeedata());
		
		
		return "addEmployee";
	}

	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public @ResponseBody CountriesDTO statesForCountry(			
			@RequestParam(value = "countryId", required = true) int countryId) {
		CountriesDTO countriesDTO = new CountriesDTO();
		List<State> stateList = this.stateService.listState(countryId, false);
		List<Vendor> vendorList = this.vendorService.listVendor(countryId, false);
		List<Grade> gradeList = this.gradeService.listGrades(countryId);
		List<ResourceManager> resourceMgrList = this.resourceMgrService.listResourceManager(countryId);
		Spoc spoc = this.spocService.fetchSpoc(countryId);
		countriesDTO.setStateList(stateList);
		countriesDTO.setVendorList(vendorList);
		countriesDTO.setSpoc(spoc);
		countriesDTO.setGradeList(gradeList);
		countriesDTO.setResourceMgrList(resourceMgrList);
		return countriesDTO;
	}
	
	@RequestMapping(value = "/bis", method = RequestMethod.GET)
	public @ResponseBody BundleEmDTO bisValue(			
			@RequestParam(value = "id", required = true) int bundleEmId) {
		BundleEmDTO bundleemdto = new BundleEmDTO();
		List<Bis> bisList = this.bisService.bisList(bundleEmId); 
		bundleemdto.setBisList(bisList);
		return bundleemdto;
	}
	
	
	
	
	
	@RequestMapping(value = "/checkEmployeeExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkEmployeeExists(
			@RequestParam(value = "empId", required = true) String empId,
			@RequestParam(value = "extrnalSelectn", required = true) boolean  extrnalSelectn,
			@RequestParam(value = "internalSelectn", required = true) boolean internalSelectn)
	
	{
		boolean isEmployeeExist = false;
		if(empId!=null && !empId.equals("")){
				if(extrnalSelectn){
					isEmployeeExist = employeeService.checkEmployeeExists(empId , "External" );
				}
				else if(internalSelectn){
					isEmployeeExist = employeeService.checkEmployeeExists(empId , "Internal");
				}
			 		
		}

		return isEmployeeExist;
	}
	
	
	@RequestMapping(value = "/checkCorpIdExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkCorpIdExists(
			@RequestParam(value = "corpId", required = true) String corpId) throws NamingException
	
	{
		Employee empDetailsFromAD = new Employee();
		String empType= null;
		String txtPSAID = null;
		boolean isEmployeeExist = false;
		if(corpId!=null && !corpId.equals("")){
			 isEmployeeExist = employeeService.checkCorpIdExists(corpId,txtPSAID,empType,"add");		
		}
		
			
  		return isEmployeeExist;
	}
	
	@RequestMapping(value = "/checkCorpIdExistsEdit", method = RequestMethod.GET)
	public @ResponseBody boolean checkCorpIdExistsEdit(
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
					 isEmployeeExist = employeeService.checkCorpIdExists(corpId,txtPSAID,"Internal",txthiddenCorpID);
				}
				else
				{
					 isEmployeeExist = employeeService.checkCorpIdExists(corpId,txtPSAID,"External",txthiddenCorpID);		
				}		
			}
		}
	
		return isEmployeeExist;
	}


	@RequestMapping(value = "/checkPsaIdExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkPsaIdExists(
			@RequestParam(value = "psaId", required = true) String psaId)
	
	{
		String empType = null;
		String txtPSAID = null;
		boolean isEmployeeExist = false;
		if(psaId!=null && !psaId.equals("")){
			if(!psaId.equalsIgnoreCase("No PSA id")) {
				
			 isEmployeeExist = employeeService.checkPsaIDExists(psaId,txtPSAID,empType);	
			 
			}
		}
		return isEmployeeExist;
	}
	
	
	@RequestMapping(value = "/checkPsaIdExistsEdit", method = RequestMethod.GET)
	public @ResponseBody boolean checkPsaIdExistsEdit(
		   @RequestParam(value = "psaId", required = true) String psaId,
	       @RequestParam(value = "txtPSAID", required = true) String txtPSAID,
	       @RequestParam(value = "empType", required = true) String empType)
	
	
	{
		boolean isEmployeeExist = false;
		if(psaId!=null && !psaId.equals("")){
			if(!psaId.equalsIgnoreCase("No PSA id")) {
				if(empType.equalsIgnoreCase("Internal")){
				 isEmployeeExist = employeeService.checkPsaIDExists(psaId,txtPSAID,"Internal");		
				}
			
				else{
				 isEmployeeExist = employeeService.checkPsaIDExists(psaId,txtPSAID,"External");
				
				}
			}
		}
		return isEmployeeExist;
	}
	
	@RequestMapping(value = "/checkEmployeeEmailExists", method = RequestMethod.GET)
	public @ResponseBody boolean checkEmployeeEmailExists(
			@RequestParam(value = "email", required = true) String email)
			
	
	{
		String empType = null;
		String txtPSAID = null;
		boolean isEmployeeExist = false;
		if(email!=null && !email.equals("")){
			
			 isEmployeeExist = employeeService.checkEmployeeEmailExists(email,txtPSAID,empType);		
		}
		return isEmployeeExist;
	}
	
	
	
	@RequestMapping(value = "/checkEmployeeEmailExistsEdit", method = RequestMethod.GET)
	public @ResponseBody boolean checkEmployeeEmailExistsEdit(
		   @RequestParam(value = "email", required = true) String email,
   		   @RequestParam(value = "txtPSAID", required = true) String txtPSAID,
   		   @RequestParam(value = "empType", required = true) String empType)
	
	{
		
		boolean isEmployeeExist = false;
		if(email!=null && !email.equals("")){
			isEmployeeExist = employeeService.checkEmployeeEmailExists(email,txtPSAID,empType);	
		}
			
		return isEmployeeExist;
	}
	
	
	

	@RequestMapping(value = { "/managers" }, method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> listManegers() {
		return this.employeeService.listManagers();
	}

	public List<Employee> listManagersByBundleEm(){
	return this.employeeService.listManagersByBundleEm();	
	}
	
	public List<Employee> listManagersByEm(){
		return this.employeeService.listManagersByBundleEm();	
	}
	
	// For add and update employee both
	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public String addEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		}else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
			
			model.addAttribute("checkUserType", "RM");
			
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM
 			model.addAttribute("checkUserType", "BundleEM");
 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
 			  
 			model.addAttribute("checkUserType", "RM_PMO");
 		}	
	

		
		// new employee, add it
		try {
			/*String docPath = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getServerPort()
					+ request.getContextPath() + "/resourceView/" + emp.getId() + "/";*/
			String docPath = "http://frparhermesto/HERMES_Academy/learning.html";
			List<Training> trainingList = trainingService.getTrainingList(emp.getGrade().getGradeId());

			List<EmployeeTraining> employeeTrainingList = new ArrayList<>();
			for (Training t : trainingList) {
				EmployeeTraining et = new EmployeeTraining();
				et.setTraining(t);
				et.setEmployee(emp);
				et.setAllocationDate(new Date());
				employeeTrainingList.add(et);
			}

			emp.setEmployeeTrainings(employeeTrainingList);
			
			if(emp.getEmpType().equals(EmployeeType.External.toString())){
				emp.setPsaId(emp.getEmpId());
				emp.setEmpId(emp.getExternalEmpId());
			}
			final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			emp.setCreatedBy(loggedUser);
			Date date = new Date();
			
			emp.setCreationdate(date);
			this.employeeService.addEmployee(emp);
			redirectAttributes.addFlashAttribute("successMsg", "Resource on-boarding data saved successfully.");
			List<Employee> employeeList = employeeService.getAllEmployees();
			List<Employee> empList = employeeService.modifyEmployeeList(employeeList);
			model.addAttribute("employeeList", empList);
			String subject = "Welcome to HERMES";
		
			if(emp.getCountry().getCountryId().equals(2) && emp.getEmpType().equalsIgnoreCase("Internal")){  // welcome mail to the resource - India 
				MailSender.send(psaMailUtility.prepareMailId(emp.getEmail()), MailUtility.createEmailBody(emp.getFirstName(), docPath,
				emp.getSpoc().getSpocName(), emp.getSpoc().getSpocEmail()), subject);		
			}
			
			if((emp.getCountry().getCountryId().equals(1) /*|| emp.getCountry().getCountryId().equals(4) || emp.getCountry().getCountryId().equals(5)*/ ) && emp.getEmpType().equalsIgnoreCase("Internal")){ // welcome mail to the resource 
				//MailSender.send(emp.getEmail(), MailUtility.createEmailForFrance(emp.getFirstName(), docPath,emp.getSpoc().getSpocName(), emp.getSpoc().getSpocEmail()), subject);
			}
			
/*			if(emp.getCountry().getCountryId()!=null){ // Mail to all in BIS
				String title = "Welcome to " + emp.getFirstName();
				String startDate = null;
				emp.setOnboardingDate(startDate);
				if (emp.getJoiningDate() != null) {
					startDate = OnboardingConstants.DATE_FORMAT.format(emp.getJoiningDate());
					}
				emp.setOnboardingDate(startDate);
				List<Employee> mailToPeers = employeeService.emailForIndiaEmployee(emp.getCountry().getCountryId(), emp.getBis().getBis_id(),emp.getLocation().getStateId(),emp.getCorpId());
			logger.info(mailToPeers);
				if(mailToPeers!= null && mailToPeers.size() > 0){
					//for(int i=0; i<mailToPeers.size(); i++){
						MailSender.createEmailBodyForAll(mailToPeers,emp, MailUtility.createEmailBodyForAll(docPath,"All",emp.getFirstName(),emp.getLastName(),emp.getBis().getBis_Name(),startDate), title);
						
					//}
					
				}
				
			}*/
			
			
			
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("errorMsg", "Resource entry already exists.");
			model.addAttribute("employee", emp);
			return "redirect:/employees";
		} catch (MessagingException e) {
			logger.error("Exceptions in addEmployee " + e.getMessage(), e);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while sending mail.");
			model.addAttribute("employee", emp);
			return "redirect:/employees";
		}catch (Exception e) {
			logger.error("Exceptions in addEmployee " + e.getMessage(), e);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while saving employee.");
			model.addAttribute("employee", emp);
			return "redirect:/employees";
		}
		// existing employee, call update

		/*return "redirect:/employees";*/
		return "redirect:/allResourceList";
	}

	@RequestMapping("/remove/{id}")
	public String removeEmployee(@PathVariable("id") int id) {
		this.employeeService.removeEmployee(id);
		return "redirect:/employees";
	}

	@RequestMapping(value = "/employee/{id}/{pageName}", method = RequestMethod.GET)
	public String getEmployee(@PathVariable("id") Long id,@PathVariable("pageName") String pageName, Model model, HttpServletRequest request) {
		logger.info("/employee/{id}/{pageName}");
		Employee emp = this.employeeService.getEmployeeById(id);
		if(emp.getOnboardingEmail() !=null && emp.getOnboardingEmail()!="" &&!(emp.getOnboardingEmail().isEmpty())){
			Employee employessss = this.preOnbEmpService.getPreOnbEmployees(emp.getOnboardingEmail());
			emp.setBuddy(employessss);
			}
		if(emp.getRollOffType() != null) {
			emp.setRollOffTypeVal(emp.getRollOffType().getId());
		}
		if(emp.getReplacementType() != null) {
			emp.setReplacementTypeVal(emp.getReplacementType().getId());
		}
		logger.info(pageName);
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
		else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
			/*Employee employee = this.employeeService.getEmpForRmPMO(id);
			if( employee.getOnboardingEmail() !=null && employee.getOnboardingEmail()!=null){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(employee.getOnboardingEmail());
				employee.setBuddy(employessss);
				}
			model.addAttribute("employee", employee);*/
 			model.addAttribute("checkUserType", "RM");
			
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			Employee employee = this.employeeService.getEmpForRmPMO(id);//EM
			employee.setEMReadOnly(true);//EM
			/*if( employee.getOnboardingEmail() !=null && employee.getOnboardingEmail()!=null){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(employee.getOnboardingEmail());
				employee.setBuddy(employessss);
				}*/
			model.addAttribute("employee", employee);//EM
 			model.addAttribute("checkUserType", "BundleEM");
 			return "RmPMO";//EM
 		}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
			/*Employee employee = this.employeeService.getEmpForRmPMO(id);
			if( employee.getOnboardingEmail() !=null && employee.getOnboardingEmail()!=null){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(employee.getOnboardingEmail());
				employee.setBuddy(employessss);
				}
			model.addAttribute("employee", employee);*/
			model.addAttribute("checkUserType", "RM_PMO");
			//return "RmPMO";	
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
			Employee employee = this.employeeService.getEmpForRmPMO(id);	
			if(employee.getOnboardingEmail() !=null && employee.getOnboardingEmail()!="" &&!(employee.getOnboardingEmail().isEmpty())){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(employee.getOnboardingEmail());
				employee.setBuddy(employessss);
				}
			model.addAttribute("employee", employee);
			model.addAttribute("checkUserType", "ASL");
			return "RmPMO";	
		}
		
		if(emp.getEmpType().equals(EmployeeType.External.toString())){
			emp.setExternalEmpId(emp.getEmpId());
			emp.setEmpId(emp.getPsaId());
		}
		
		/*if(emp.getPhase() == 0){
			emp.setPhase(1);
		}*/
		
	
		model.addAttribute("employee", emp);
		model.addAttribute("listPrimaryProgram",this.primaryProgramService.listPrimaryProgram(false));
		model.addAttribute("listCountry", this.countryService.listCountry());
		model.addAttribute("listTechnology", this.technologyService.listTechnology());
		model.addAttribute("listEmployees", this.employeeService.listManagers());
		model.addAttribute("listEmployeesByBundleEm",this.employeeService.listManagersByBundleEm());
		model.addAttribute("listEmployeesByEm",this.employeeService.listManagersByEm());
		model.addAttribute("listOffshoreEmIndia",this.employeeService.listOffshoreEmIndia());
		model.addAttribute("listEmployeeRoles", this.getEmployeeRolesList()); //Employee Roles
		model.addAttribute("listIndusGoals",this.indusGoalsService.listIndusGoals());//newly added
		model.addAttribute("listRoleTech",this.roleTechService.listRoleTech());//newly added
		model.addAttribute("listGrades", this.gradeService.listGrades(emp.getCountry().getCountryId()));
		model.addAttribute("listGlobalGrades", this.globalGradeService.listGlobalGrades());
		model.addAttribute("listEntity", this.entityService.listEntity());
		model.addAttribute("listCgEntity", this.cgEntityService.listCgEntity());
		model.addAttribute("listOffshoreEm",this.offshoreEmService.listOffshoreEm());
		model.addAttribute("listBundleEm", this.bundleEmService.listBundleEm());
		model.addAttribute("demandTypeList", this.demandtypeservice.demandTypeList());
		List<Bis> fullListOfBis = this.bisService.fullListOfBis();
		model.addAttribute("fullListOfBis",emp.getPrimaryprogram().getBisList());
	    model.addAttribute("listOfEmployeedata",this.listOfEmployeedata());
	    
	  //Engg - Start
	  	model.addAttribute("basList", this.basService.listBas());
	  	model.addAttribute("cfaoList", this.cfaoService.listCfao());
	  	//Engg - End
	    
		//model.addAttribute("fullListOfBis",fullListOfBis);//was commented from beginning
		if(pageName.equalsIgnoreCase("dataInconsistencyPage")){
			model.addAttribute("CheckReportType","dataInconsistencyPage");
		}
		/*if(pageName.equalsIgnoreCase("orphanMgrPage")){
			model.addAttribute("CheckReportTypeOM", "orphanMgrPage");
		}*/
		else{
			model.addAttribute("CheckReportType",null);
		}
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			return "editEmpBISPMORole";
		}
		
		
		return "editEmployee";
	}

	@RequestMapping(value = "/employee/edit", method = RequestMethod.POST)
	public String editEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		if(emp.getManager() == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Manager cannot be empty");
			return "redirect:/allResourceList";
		} 
		
		
		//Engg - Start
		if(emp.getPrimaryprogram().getPrimaryProgramId() == 10) {logger.info("set Engg as True for "+emp.getCorpId()+" id "+emp.getId());
			emp.setEngg(true);
		}
		else {
			emp.setEngg(false);
		}
		//Engg - End
		
		
		Employee existingEmp = employeeService.getEmployeeById(emp.getId()); // exiting database record
		emp.setGeneratedDate(existingEmp.getGeneratedDate());
		emp.setRequestedDate(existingEmp.getRequestedDate());
		//mehens - buddy
		if(emp.getOnboardingEmail() == null || emp.getOnboardingEmail().equals("") || emp.getOnboardingEmail().isEmpty()) {
			emp.setBuddy(null);
			emp.setOnboardingEmail(null);
		}
		else if(emp.getOnboardingEmail() != null || !emp.getOnboardingEmail().equals("")) {
			Employee buddy = employeeService.getEmployeeByEmail(emp.getOnboardingEmail());
			emp.setBuddy(buddy);
		}
		
		Date actualEndDate = existingEmp.getActualEndDate();
		Date updatedActualDate = emp.getActualEndDate();
		Date endDate =existingEmp.getEndDate();
		Date billingDate=existingEmp.getBillingDate();
		Date joiningDate=existingEmp.getJoiningDate();
		LocalDate dates = LocalDate.now(); 
		Date currentDate = Date.from(dates.atStartOfDay(ZoneId.systemDefault()).toInstant());
		if(joiningDate!=null && billingDate!=null) {
		if((joiningDate.before(currentDate)|| joiningDate.equals(currentDate))&& (billingDate.after(currentDate) || billingDate.equals(currentDate))) {
			emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_SHADOW);
			existingEmp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_SHADOW);
		}
		}
	/*	if( emp.getOnboardingEmail() !=null){
			Employee employessss = this.preOnbEmpService.getPreOnbEmployees(emp.getOnboardingEmail());
			emp.setBuddy(employessss);
			}
		if( existingEmp.getOnboardingEmail() !=null ){
			Employee employessss = this.preOnbEmpService.getPreOnbEmployees(existingEmp.getOnboardingEmail());
			existingEmp.setBuddy(employessss);
			*/
		
		
		BundleEm bundleEM=existingEmp.getBundleEM();
		String bundleEMS=null;
		if(bundleEM!=null) {
			
	    bundleEMS=bundleEM.getBundleEmName();
		}
		
		
		
		if(endDate!=null && emp.getEndDate()==null) {
			emp.setEndDate(endDate);
		}
		if(billingDate!=null && emp.getBillingDate()==null)
		{
			emp.setBillingDate(billingDate);
		}
		if(joiningDate!=null && emp.getJoiningDate()==null) {
			emp.setJoiningDate(joiningDate);
		}
		
		if(emp.getEmpType().equals(EmployeeType.External.toString())){
			emp.setPsaId(emp.getEmpId());
			emp.setEmpId(emp.getExternalEmpId());
		}
		if(actualEndDate!=null){
			emp.setActualEndDate(actualEndDate);
		}
		if(bundleEMS!=null && emp.getBundleEM()==null)
		{
	    emp.setBundleEM(bundleEM);
	    if(emp.getBundleEM()!=null) {
	    emp.getBundleEM().setBundleEmName(bundleEMS);
		}
		}
		

		final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//Engg - Start
		if(emp.getPrimaryprogram().getPrimaryProgramId() == 10) {logger.info("Set Engg as true if PP is 10"+emp.getCorpId());
			emp.setEngg(true);
		}
		//Engg - End
		
		emp.setCreatedBy(loggedUser);
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		emp.setRollOffType(this.employeeService.getRollOffTypeById(emp.getRollOffTypeVal()));
		emp.setReplacementType(this.employeeService.getReplacementTypeById(emp.getReplacementTypeVal()));
		emp.setCreationdate(sqlDate);
		try {
			boolean isBisRotation = false;
			int newBis=0;
			if(existingEmp.getBis().getBis_id() != emp.getBis().getBis_id()) {
			newBis = emp.getBis().getBis_id();
			isBisRotation = true;
			}
			if(isBisRotation) { // Bis Rotation Record Insertion Code if Change in BIS.
			//isBisRotation
				// Changes done for BIS Rotation
				// old record is existingEmp and new record is emp. Do following steps if BIS has change.
				//1. setting planned offboarding date for new record using existing record planned date.
				if(emp.getActualEndDate()!=null)//offboarding date
				{
					emp.setActualEndDate(null);
				}
				emp.setEndDate(existingEmp.getEndDate()); //Planned offboarding date
				//2. setting yesterday's date as planned offboarding date and actual offboarding date in existing record. set archivetype as 1. Update old record
				Date yesterdayDate = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
				existingEmp.setActualEndDate(yesterdayDate);
				existingEmp.setEndDate(yesterdayDate);
				existingEmp.setArchiveType(1);
				
				if(existingEmp.getRequestor()!=null) {
					if(existingEmp.getRequestor().getRollOffType()!=null) {
						existingEmp.getRequestor().getRollOffType().setId(15);
						existingEmp.getRequestor().getRollOffType().setType("BIS Change");
					}
				}
				/*if(existingEmp.getRollOffType()!=null)
				{
					existingEmp.getRollOffType().setId(15);
					existingEmp.getRollOffType().setType("BIS Change");
				}*/
				
				existingEmp.setRollOffType(this.employeeService.getRollOffTypeById(15));//mehens
				
				if((existingEmp.getResourceStatus()!=null) && (existingEmp.getResourceStatus().equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)))
				{
					if(existingEmp.getBillingDate().after(yesterdayDate))
					{
						existingEmp.setBillingDate(yesterdayDate);
					}
				}
				
				this.employeeService.updateEmployee(existingEmp);
				//3. setting oboarding date and billing date as today for new record. make id null. make preonbemp field null. and insert this new record in emp.
				emp.setJoiningDate(new Date());
				emp.setBillingDate(new Date());
				emp.setId(null);
				emp.setPreonbemp(null);
				emp.setRequestor(null);
				if((emp.getResourceStatus()!=null) && (emp.getResourceStatus().equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)))
				{
					if(emp.getBillingDate().after(currentDate))
					{
						emp.setBillingDate(billingDate);
					}else
					{
						emp.setBillingDate(currentDate);
					}
				}
				

				this.employeeService.addEmployee(emp);
				
			BisRotation rotationRecord = new BisRotation();
			rotationRecord.setCorpId(emp.getCorpId());
			rotationRecord.setId(emp.getId());
			rotationRecord.setIdCorpId(emp.getId()+""); // kept String if it requires changes back to combination of corp id and id
			rotationRecord.setNewBundle(emp.getBis());
			rotationRecord.setPrevBundle(existingEmp.getBis());
			rotationRecord.setRotationDate(LocalDate.now());
			this.bisRotationService.insertBisRotation(rotationRecord);
			}
			
			
			
		     
			if(emp.getPrimaryprogram().getMoodleEnrolReq() && emp.getEndDate().after(new Date())) { // will not enrol inactive resources.
				
				if(emp.getEmpType().equals("Internal")) {
					// start
					List<ResourceCohortMapping> cohortList = new ArrayList();
					ResourceCohortMapping cohortRecord;
					
					int newEmpRole=0;
					PrimaryProgram newPP = null;
					Technology primTech = null;
					boolean cohortEnrolRequired = false;
					
					int cohortMappingInserted = 0;
					if(isBisRotation) {
						cohortEnrolRequired = true;
						
					}
					if(!existingEmp.getEmprole().equals(emp.getEmprole())) {
						newEmpRole = emp.getEmprole();
						cohortEnrolRequired = true;
					}
					if(existingEmp.getPrimaryprogram().getPrimaryProgramId() != emp.getPrimaryprogram().getPrimaryProgramId()) {
						newPP = emp.getPrimaryprogram();
						cohortEnrolRequired = true;
					}
					if(!existingEmp.getPrimaryTechnology().getTechnologyId().equals(emp.getPrimaryTechnology().getTechnologyId())) {
						primTech = emp.getPrimaryTechnology();
						cohortEnrolRequired = true;
					}
					if(cohortEnrolRequired) {
						
						this.moodleService.addUser(emp, "manual");
							
						
						//List<FieldCohortMapping> cohortMappingList = this.fieldCohortService.getCohortForFields(0,emp);
						List<FieldCohortMapping> cohortMappingList = this.fieldCohortService.getCohortForFields(0,emp,newBis,newEmpRole,newPP,primTech);
						
						// Bhavna y PP is always null? - probably a mistake
						if(cohortMappingList != null) {
							//logger.info("cohortMappingList "+cohortMappingList.size());
							for(FieldCohortMapping f : cohortMappingList) {
								cohortRecord = new ResourceCohortMapping();
								cohortRecord.setCorp_id(emp.getCorpId().toLowerCase());
								cohortRecord.setCreationDate(new Date());
								cohortRecord.setCohort(f.getCohort());
								cohortList.add(cohortRecord);
							}
							
							cohortMappingInserted = this.resourceCohortService.insert(cohortList);
							//logger.info("cohortMappingInserted "+cohortMappingInserted);
						}
						
						
					}
					//end
				}
					}
				
					if((existingEmp.getPrimaryprogram().getPrimaryProgramId() != emp.getPrimaryprogram().getPrimaryProgramId()) && emp.getEndDate().after(new Date())){ // check if PP changed from conflu. to non conflu. 
						if(existingEmp.getPrimaryprogram().getConfluenceaccess() != emp.getPrimaryprogram().getConfluenceaccess()) {
							if(emp.getPrimaryprogram().getConfluenceaccess()) {
								JiraIDCreator jiraCreator = preOnbEmpService.getJiraCreatorDetails();
								if(jiraCreator != null) {
									DLList dlCC = preOnbEmpService.getDLListbyRole("CC");
									DLList dlHermesOnb = preOnbEmpService.getDLListbyRole("HermesOnb");
									//String empName = emp.getFirstName()+" "+emp.getLastName()+" (CORP ID: "+emp.getCorpId()+")";
									String empName = emp.getFirstName()+" "+emp.getLastName();
									//String jiraSubject = "[HERMES] Creation of JIRA and Confluence ID for "+empName +", "+emp.getLocation().getStateName();
									String jiraSubject = "[HERMES] Creation of JIRA and Confluence ID for "+empName+" ("+emp.getCorpId()+")";
									//String [] ccList = {psaMailUtility.prepareMailId(emp.getManagerEmail()),psaMailUtility.prepareMailId(dlCC.getEmail())};
									String [] ccList = {psaMailUtility.prepareMailId(emp.getManagerEmail()),psaMailUtility.prepareMailId(dlHermesOnb.getEmail())};
									String[] recipientList = {psaMailUtility.prepareMailId(jiraCreator.getEmail())};
									MailSender.send(recipientList, ccList, MailUtility.createJiraIDMailUpdate(jiraCreator.getName(), empName, emp.getEmpId(),emp.getCorpId(), emp),jiraSubject);
									logger.info("Primary program change Confluence access mail sent for resource "+emp.getCorpId());
								}
							}else {
								//String JIRAIDConfluSubj = "[Onboarding Tool] Offboarding JIRA & Confluence ID - corp id "+emp.getCorpId();
								//String JIRAIDConfluSubj = "[HERMES] Offboarding JIRA & Confluence ID - corp id "+emp.getCorpId();
								String JIRAIDConfluSubj = "[HERMES] Offboarding JIRA & Confluence ID - corp id "+emp.getCorpId()+", ("+emp.getFirstName()+" "+emp.getLastName()+")" ;
								DLList dl = preOnbEmpService.getDLListbyRole("JIRAID");
								
								//adding in CC
								String[] recipientList = {psaMailUtility.prepareSheduleMailId(dl.getEmail())};
								DLList dlHermesOnb = preOnbEmpService.getDLListbyRole("HermesOnb");
								String [] ccList = {psaMailUtility.prepareMailId(dlHermesOnb.getEmail())};
								
								//MailSender.send(psaMailUtility.prepareSheduleMailId(dl.getEmail()),MailUtility.JIRARevokeMail(emp,dl),JIRAIDConfluSubj);
								MailSender.send(recipientList, ccList,MailUtility.JIRARevokeMail(emp,dl),JIRAIDConfluSubj);
								logger.info("Primary program change Confluence revocation mail sent for resource "+emp.getCorpId());
							}
						}
					}
					// Changes done for without Bis rotation
					if(!isBisRotation) {
						//Country Change - start
						//Country newEmpCn =  emp.getCountry();
						boolean isCountryChanged = false;
						int existingC = existingEmp.getCountry().getCountryId();
						int newC = emp.getCountry().getCountryId();
						if(existingC != newC) {
							isCountryChanged = true;
						}
						
						if(isCountryChanged) { logger.info("Country Changed for - "+emp.getCorpId());
							// old record is existingEmp and new record is emp. Do following steps if country is change.
							//1. setting planned offboarding date for new record using existing record planned date.
							if(emp.getActualEndDate()!=null)//offboarding date
							{
								emp.setActualEndDate(null);
							}
							emp.setEndDate(existingEmp.getEndDate()); //Planned offboarding date
							//2. setting yesterday's date as planned offboarding date and actual offboarding date in existing record. set archivetype as 1. Update old record
							Date yesterdayDate = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
							existingEmp.setActualEndDate(yesterdayDate);
							existingEmp.setEndDate(yesterdayDate);
							existingEmp.setArchiveType(1);
							
							if(existingEmp.getRequestor()!=null) {
								if(existingEmp.getRequestor().getRollOffType()!=null) {
									existingEmp.getRequestor().getRollOffType().setId(17);
									existingEmp.getRequestor().getRollOffType().setType("Country Change");
								}
							}
							
							
							existingEmp.setRollOffType(this.employeeService.getRollOffTypeById(17));
							
							if((existingEmp.getResourceStatus()!=null) && (existingEmp.getResourceStatus().equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)))
							{
								if(existingEmp.getBillingDate().after(yesterdayDate))
								{
									existingEmp.setBillingDate(yesterdayDate);
								}
							}
							
							this.employeeService.updateEmployee(existingEmp);
							//3. setting oboarding date and billing date as today for new record. make id null. make preonbemp field null. and insert this new record in emp.
							emp.setJoiningDate(new Date());
							emp.setBillingDate(new Date());
							emp.setId(null);
							emp.setPreonbemp(null);
							emp.setRequestor(null);
							if((emp.getResourceStatus()!=null) && (emp.getResourceStatus().equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)))
							{
								if(emp.getBillingDate().after(currentDate))
								{
									emp.setBillingDate(billingDate);
								}else
								{
									emp.setBillingDate(currentDate);
								}
							}
							

							this.employeeService.addEmployee(emp);
						}
						else {
							this.employeeService.updateEmployee(emp);
						}
						//this.employeeService.updateEmployee(emp);
						//Country Change - end
					}
				if ((updatedActualDate != null && actualEndDate == null)
						|| (updatedActualDate != null && updatedActualDate.compareTo(actualEndDate) != 0)) {

					String actualEndDateStr = null;
					String updatedActualDateStr = null;
					if (actualEndDate != null) {
						actualEndDateStr = OnboardingConstants.DATE_FORMAT.format(actualEndDate);
					}
					if (updatedActualDate != null) {
						updatedActualDateStr = OnboardingConstants.DATE_FORMAT.format(updatedActualDate);
					}
					redirectAttributes.addFlashAttribute("successMsg", "Resource on-boarding data saved successfully.");
					String empDetails = emp.getFirstName() + " " + emp.getLastName() + " ( ID: " + emp.getEmpId()
							+ " )";
					String managerDetails = emp.getManager().getFirstName();
					String msgSubject = "Off-boarding date change notification for " + empDetails;

				}
				
				redirectAttributes.addFlashAttribute("successMsg", "Resource on-boarding data saved successfully.");
			//}
				
		} catch (Exception exception) {
			logger.error("Exceptions in updateEmployee " + exception.getMessage(), exception);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while updating record.");
			model.addAttribute("employee", emp);
			return "redirect:/allResourceList";
		}
		return "redirect:/allResourceList";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value = {"/allResourceList" }, method = RequestMethod.GET)
	public String resourceList(Model model, HttpServletRequest request) {

		Employee e = new Employee();
		model.addAttribute("employee", e);
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
		return "allResourceList";
	}
	
	@RequestMapping(value = "/empSearchCriteria", method = RequestMethod.POST)
	public String searchEmployee(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		List<Employee> employeeSearchList = employeeService.searchEmployees(emp);
		for(int j=0; j<employeeSearchList.size(); j++)
		{
			if( employeeSearchList.get(j).getOnboardingEmail() !=null && employeeSearchList.get(j).getOnboardingEmail()!="" &&!(employeeSearchList.get(j).getOnboardingEmail().isEmpty())){
				Employee employessss = this.preOnbEmpService.getPreOnbEmployees(employeeSearchList.get(j).getOnboardingEmail());
				employeeSearchList.get(j).setBuddy(employessss);
			}
		}
		
		List<Employee> empList = employeeService.modifyEmployeeList(employeeSearchList);
		model.addAttribute("employeeList", empList);
		
		
		for(int i=0 ; i <empList.size() ; i++){/*
			//start

			int rollOffType = empList.get(i).getRollOffType();
			boolean replacementReq = empList.get(i).isReplacementRequired();
			int replacementType = empList.get(i).getReplacementType();
			
			if(rollOffType==0){
				empList.get(i).setRollOffTypeVal("");
			}
			if(rollOffType==1){
				empList.get(i).setRollOffTypeVal("End of work assignment");
			}
			if(rollOffType==2){
				empList.get(i).setRollOffTypeVal("Change in skills required");
			}
			if(rollOffType==3){
				empList.get(i).setRollOffTypeVal("Resignation");
			}
			if(rollOffType==4){
				empList.get(i).setRollOffTypeVal("Transitioned to Offshore");
			}
			if(rollOffType==5){
				empList.get(i).setRollOffTypeVal("Performance");
			}
			if(rollOffType==6){
				empList.get(i).setRollOffTypeVal("BGV / Abscondee");
			}
			if(rollOffType==7){
				empList.get(i).setRollOffTypeVal("Employee requested");
			}
			if(rollOffType==8){
				empList.get(i).setRollOffTypeVal("Rotation");
			}
			if(rollOffType==9){
				empList.get(i).setRollOffTypeVal("Hired as Capgemini resource");
			}
			if(rollOffType==10) {
				empList.get(i).setRollOffTypeVal("Maternity Leave");
			}
			if(rollOffType==11) {
				empList.get(i).setRollOffTypeVal("Medical reason");
			}
			if(rollOffType==12) {
				empList.get(i).setRollOffTypeVal("Probation De-confirm");
			}
			if(rollOffType==13) {
				empList.get(i).setRollOffTypeVal("End of assignment - COVID-19");
			}
			
			if(replacementReq){
				empList.get(i).setReplacementRequiredVal("Yes");
			}
			else{
				empList.get(i).setReplacementRequiredVal("No");
			}
		
			if(replacementType == 0){
				empList.get(i).setReplacementTypeVal("");
			}
			if(replacementType == 1){
				empList.get(i).setReplacementTypeVal("End of work assignment");
			}
			if(replacementType == 2){
				empList.get(i).setReplacementTypeVal("Change in skill");
			}
			if(replacementType == 3){
				empList.get(i).setReplacementTypeVal("Got mutualized within the team");
			}
			//end
			
			int heritgTypevlaue = empList.get(i).getHeritageType();
			boolean decisionValByGPVzalue = empList.get(i).isDecisionValByGP();
			if(decisionValByGPVzalue)
			{
				empList.get(i).setDecisionVal("Yes");
			}else{
				empList.get(i).setDecisionVal("No");
			}
			
			if(heritgTypevlaue==1){
				empList.get(i).setHeritageValue("Capgemini");
			}
			else if(heritgTypevlaue==2){
				empList.get(i).setHeritageValue("FR HERMES");
			}
			else if(heritgTypevlaue==3){
				empList.get(i).setHeritageValue("Others");
			}
			
			
			
			Bis bis = empList.get(i).getBis();
			if(bis!=null){
				int bisId = empList.get(i).getBis().getBis_id();
				if(bisId==1){
					bis.setBis_Name("");
					empList.get(i).setBis(bis);
			}
			
			}
		*/}
		
		if(employeeSearchList != null && employeeSearchList.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
		}
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			model.addAttribute("checkUserType", "ViewMode");
		    }
		

		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
				model.addAttribute("checkUserTypeforUM", "UserManagement");
			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
			
			model.addAttribute("checkUserType", "RM");
			
		}else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			
 			model.addAttribute("checkUserType", "BundleEM");
 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
 			  
 			model.addAttribute("checkUserType", "RM_PMO");
 		}else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
 			
			  model.addAttribute("checkUserType", "ASL");
		  }
		return "allResourceList";
	}
	
	
	@RequestMapping(value = "/getDetailsByCorpIDFromActiveDirectory", method = RequestMethod.GET)
	public @ResponseBody EmployeeDTO getDetailsFromActiveDirectory(
			@RequestParam(value = "corpId", required = true) String corpId) throws NamingException {
		
		//Session session = this.sessionFactory.getCurrentSession();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		boolean corpIdExits = false;
		corpIdExits = checkCorpIdExists(corpId);
		if(corpIdExits){
			employeeDTO.setFirstName("");
			employeeDTO.setLastName("");
			employeeDTO.setEmail("");
			logger.info("corpId already exits");
		}
		
		else{
		  ActiveDirectory activeDirectory = new ActiveDirectory("SVC-IN-HERMESMUM","Tdbj@3020","corp.capgemini.com");
		  NamingEnumeration<SearchResult> result;
		try {
			result = activeDirectory.searchUser(corpId,"username",null);
			if(result.hasMoreElements()) {
				  
				SearchResult rs= (SearchResult)result.next();
				Attributes attrs = rs.getAttributes();
				if(attrs!=null){
					  
				    
					String temp = attrs.get("samaccountname").toString();
					logger.info("Username:" + temp.substring(temp.indexOf(":")+1));
					
					if(attrs.get("givenname") != null && attrs.get("givenname").get(0) != null){
						temp = attrs.get("givenname").get(0).toString();
						logger.info("Name         :" + temp.substring(temp.indexOf(":")+1).trim());
						employeeDTO.setFirstName(temp.substring(temp.indexOf(":")+1).trim());	
					}
					
				
					if(attrs.get("sn") != null && attrs.get("sn").get(0) != null){
					temp = attrs.get("sn").get(0).toString();
					logger.info("sn	:" + temp.substring(temp.indexOf(":")+1));
					employeeDTO.setLastName(temp.substring(temp.indexOf(":")+1).trim());
					}
					
					if(attrs.get("mail") != null && attrs.get("mail").get(0) != null){
						temp = attrs.get("mail").get(0).toString();
						logger.info("Email ID	:" + temp.substring(temp.indexOf(":")+1));
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
		
		
		catch (NamingException e) {
			String roleId = SecurityContextHolder.getContext().getAuthentication().toString();
			String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info(loggedInUser);
/*			TypedQuery<Employee> query = session.createNamedQuery("getLoggedUserID");
			query.setParameter("corpID",loggedInUser);
			Long loggedUserID = query.getResultList().get(0).getId();
			logger.info(loggedInUser);*/
		logger.info("The logged in user  is" + loggedInUser);
			e.getExplanation();
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		}
		  
		   return employeeDTO;
		}



	@RequestMapping(value = "/getDetailsByCorpIDFromActiveDirectoryExits", method = RequestMethod.GET)
	public @ResponseBody EmployeeDTO getDetailsFromActiveDirectoryExits(
			@RequestParam(value = "corpId", required = true) String corpId , @RequestParam(value = "txtPSAID", required = true) String txtPSAID,
			@RequestParam(value = "empType", required = true) String empType,@RequestParam(value = "txthiddenCorpID", required = true) String txthiddenCorpID)
            {
		
		//Session session = this.sessionFactory.getCurrentSession();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		boolean corpIdExits = false;
		corpIdExits = checkCorpIdExistsEdit(corpId, txtPSAID, empType, txthiddenCorpID);
		if(corpIdExits){
			employeeDTO.setFirstName("");
			employeeDTO.setLastName("");
			employeeDTO.setEmail("");
		//	employeeDTO.setFirstName("");
			logger.info("corpId already exits");
		}
		else {
		  ActiveDirectory activeDirectory = new ActiveDirectory("SVC-IN-HERMESMUM","Tdbj@3020","corp.capgemini.com");
		  NamingEnumeration<SearchResult> result;
		try {
			result = activeDirectory.searchUser(corpId,"username",null);
			 if(result.hasMoreElements()) {
					SearchResult rs= (SearchResult)result.next();
					Attributes attrs = rs.getAttributes();
					if(attrs!=null){
					  
						    
						String temp = attrs.get("samaccountname").toString();
						logger.info("Username:" + temp.substring(temp.indexOf(":")+1));
						
						if(attrs.get("givenname") != null && attrs.get("givenname").get(0) != null){
							temp = attrs.get("givenname").get(0).toString();
							logger.info("Name         :" + temp.substring(temp.indexOf(":")+1).trim());
							employeeDTO.setFirstName(temp.substring(temp.indexOf(":")+1).trim());	
						}
						
					
						if(attrs.get("sn") != null && attrs.get("sn").get(0) != null){
						temp = attrs.get("sn").get(0).toString();
						logger.info("sn	:" + temp.substring(temp.indexOf(":")+1));
						employeeDTO.setLastName(temp.substring(temp.indexOf(":")+1).trim());
						}
						
						if(attrs.get("mail") != null && attrs.get("mail").get(0) != null){
							temp = attrs.get("mail").get(0).toString();
							logger.info("Email ID	:" + temp.substring(temp.indexOf(":")+1));
							employeeDTO.setEmail(temp.substring(temp.indexOf(":")+1).trim());
						}
						
						
						else temp = "";
						
					}
					
					
				} else  {
					logger.info("No search result found!");
					employeeDTO.setFirstName(null);
					employeeDTO.setLastName(null);
					employeeDTO.setEmail(null);
					logger.info("corpId not exits in active directory");
				}
			   activeDirectory.closeLdapConnection();
			  
		} catch (NamingException e) {
			String role_value = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString(); 
			String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			
		/*	TypedQuery<Employee> query1 =session.createNamedQuery("getLoggedUserID");
			 query1.setParameter("corpID", loggedUser); 
			 Long loggedUserID = query1.getResultList().get(0).getId();*/
			logger.info(loggedUser);
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
	}
	return employeeDTO;
	}
	
	@RequestMapping(value="/checkPSAId" , method=RequestMethod.GET)
	public @ResponseBody boolean checkPSAId(@RequestParam(value="psaId", required=true) String psaId) {
		boolean isPsaId = false;
		
		try {
			if(psaId.matches("^[a-zA-Z0-9]+$") || psaId == "") {
				isPsaId=true;
			}
		}
		catch(Exception e) {
			logger.info("Exception in PsaId check function");
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		return isPsaId;
	}
	
	
	@Transactional
	@RequestMapping(value = "/employee/updatePSAID", method = RequestMethod.POST)
	public String updatePSAID(Model model,HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("employee") Employee emp) {
		
		try {
			
				final String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
				emp.setCreatedBy(loggedUser);
				Employee empTable = this.employeeService.getActiveEmployeeByCorpId(emp.getCorpId());
				EmployeeRoles emprole = this.employeeroleservice.getEmployeeRolesId(emp.getEmprole());
				if(emp.getPsaId() == "NOPSAID" || emp.getPsaId().equalsIgnoreCase("NOPSAID")) {
					emp.setPsaId("NO PSA ID");
				}
				
					boolean isExist = this.employeeService.updatePSAID(emp);
					//Employee archivedEmp = this.employeeService.checkArchivedEmployeeExists(emp.getCorpId());
					
					/*if(empTable.isBi() != emp.isBi()) {
						 this.employeeService.updateGithub(emp);
						 redirectAttributes.addFlashAttribute("successMsg", "Resource Github Copilot data saved successfully.");
					}*/
					

					String psaId1 = empTable.getPsaId() == null ? "" : empTable.getPsaId();
					String psaId2 = emp.getPsaId() == null ? "" : emp.getPsaId();
					String vm1 = empTable.getVmNumber() == null ? "" : empTable.getVmNumber();
					String vm2 = emp.getVmNumber() == null ? "" : emp.getVmNumber();
					
					
					if (!psaId1.equals(psaId2) || !vm1.equals(vm2)) {
					//if(empTable.getPsaId() != emp.getPsaId() && !(empTable.getPsaId().equals(emp.getPsaId())) ) {
						if((emp.getPsaId() != null && (!emp.getPsaId().equalsIgnoreCase("NO PSA ID"))) && !"".equalsIgnoreCase(emp.getPsaId())) { // No mail
							/*if(emp.getVmNumber() == null || emp.getVmNumber().isEmpty() ) {
								
								EmailDTO emaildto = new EmailDTO();
								ArrayList<String> toList = new ArrayList<String>();
								ArrayList<String> ccLists =new ArrayList<String>();
								HashMap<String,String> paramValue = new HashMap<>(); 
								
								String firstname = emp.getFirstName();
								String lastname = emp.getLastName();
								String location = emp.getLocation().getStateName();
								String vmnumber= emp.getVmNumber();
								String pcaid = emp.getPsaId();
								String psamail = emp.getPCAEmail();
								
								paramValue.put("firstName",firstname);
								paramValue.put("lastName",lastname);
								paramValue.put("location",location);
								paramValue.put("psaId",pcaid);
								paramValue.put("PCAEmail",psamail);
								if(emp.getPsaLibTypeID() == 0) {
									paramValue.put("MAILTEMPID","P001NN4");
								}else {
									paramValue.put("MAILTEMPID","P001NN3");
								}
								paramValue.put("emailNo", "-");
								
								String empemail = emp.getEmail();
								toList.add(empemail);
								ccLists.add(empemail);
								emaildto.setToList(toList);
								emaildto.setCcList(ccLists);
								emaildto.setParamValue(paramValue);
								
								psaMailUtility.senMail(emaildto);
							}*/
						
						if(emp.getPsaLibTypeID() == 0) {//Engg
							emp.setVmNumber("NO VM");
						}
						//	else {
						if(isExist) {
								String subject = "[User Management Tool] Your Stellantis credentials";
								Map<String, String> inlineImages = new HashMap<String, String>();
								//MailSender.sendImageMail(psaMailUtility.prepareMailId(emp.getEmail()), MailUtility.createWelcomeMail(emp), subject, inlineImages);
								
								//BCC
								DLList dlBcc = preOnbEmpService.getDLListbyRole("bcc");
								MailSender.sendImageMailNew(psaMailUtility.prepareMailId(emp.getEmail()),psaMailUtility.prepareMailId(dlBcc.getEmail()) , MailUtility.createWelcomeMail(emp), subject, inlineImages);
								logger.info("Stellantis credentials sent to - "+emp.getEmail());
						//	}		

							//if(emp.getRequestor() != null && ((emp.getPsaLibTypeID() == 0) || (emp.getPsaLibTypeID() != 0 && !emp.getPsaId().isEmpty()))) {
							//if(onboardingStatus.equalsIgnoreCase(OnboardingConstants.statusRMPMOSubmit)) {
								//if(isExist) {
								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
				                String bill = dateFormat.format(emp.getBillingDate());
								
								BisPMOMap bisPmo = employeeService.getBISFromPMO(emp.getBis().getBis_id());
								String requestorSubj = "[User Management Tool] "+emp.getFirstName()+" "+emp.getLastName() +" (" +emp.getCorpId() +") - Onboarding completed ";
								if(emp.getRequestor() != null) {
								String[] recipeintList = {psaMailUtility.prepareMailId(emp.getRequestor().getEmail())};
								String[] ccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()) , psaMailUtility.prepareMailId(emp.getEM().getEmail()) , psaMailUtility.prepareMailId(emp.getManagerEmail())};
								MailSender.send(recipeintList, ccList, MailUtility.createRequestorMail(emp, true, emprole, bill), requestorSubj);	
								logger.info("Mail sent: "+requestorSubj);
								}
							}
							
				//redirectAttributes.addFlashAttribute("successMsg", "Resource on-boarding data saved successfully.");
					}	
			}
				redirectAttributes.addFlashAttribute("successMsg", "Resource on-boarding data saved successfully.");		
					
		} catch (Exception exception) {
			logger.error("Exceptions in updateEmployee " + exception.getMessage(), exception);
			redirectAttributes.addFlashAttribute("errorMsg", "Error while updating record.");
			model.addAttribute("employee", emp);
			return "redirect:/allResourceList";
		}
		return "redirect:/allResourceList";
	}
	
	
	public List<EmployeeRoles> getEmployeeRolesList() {
		if(this.EmployeeRolesList == null) {
			this.EmployeeRolesList = this.employeeroleservice.listEmployeeRoles();
		}
		return this.EmployeeRolesList;
	}
	
	@RequestMapping(value = "/checkPlannedEndDateChange", method = RequestMethod.GET)
	public @ResponseBody boolean checkPlannedEndDate(
			@RequestParam (value = "billingDateString", required = true) String billingDate,
			@RequestParam (value = "plannedEndDate", required = true) String plannedDate,
			@RequestParam (value = "joiningDate", required = true) String joiningDate) {
		
		boolean isPlannedDateGreater = false;
		Date plannedEndDate = null;
		Date joiningDateObj = null;
		Date billingDateObj = null;
		try {
			plannedEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(plannedDate);
			joiningDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
			billingDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(billingDate);
			//isPlannedDateGreater = plannedEndDate.after(joiningDateObj);
			if(plannedEndDate.after(joiningDateObj) || plannedEndDate.after(billingDateObj)) {
				isPlannedDateGreater = true;
			}
		} catch (ParseException e) {
			logger.info("Exception in Planned and joining Date compare function");
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		
		return isPlannedDateGreater;
		
	}
	
	
	@RequestMapping(value = "/checkJoiningDate", method = RequestMethod.GET)
	public @ResponseBody boolean checkJoiningDate(
			@RequestParam (value = "billingDateString", required = true) String billingDate,
			@RequestParam (value = "plannedEndDate", required = true) String plannedDate,
			@RequestParam (value = "joiningDate", required = true) String joiningDate) {
		
		boolean isJoiningDateLesser = false;
		Date plannedEndDate = null;
		Date joiningDateObj = null;
		Date billingDateObj = null;
	
		try {
			plannedEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(plannedDate);
			joiningDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(joiningDate);
			billingDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(billingDate);
			//isPlannedDateGreater = plannedEndDate.after(joiningDateObj);
			if(joiningDateObj.before(plannedEndDate) || (joiningDateObj.before(billingDateObj) || joiningDateObj.equals(billingDateObj))) {
				isJoiningDateLesser = true;
			}
			
		} catch (ParseException e) {
			logger.info("Exception in Planned and joining Date compare function");
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		
		return isJoiningDateLesser;
		
	}

	@RequestMapping(value = "/listOfEmployeedata", method = RequestMethod.GET)
	public String listOfEmployeedata() {
	    		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
	    		String currentTimestamp = df.format(new Date());
	    		Employee emp = new Employee();
	    		List<Employee> empList =null;
	    		List<String> empListFirstName =new ArrayList<String>(); 
	    		StringBuilder firstName =new StringBuilder();
	    		String listofEmployess= new String();
	    		//List<EmailReport> list = this.emailReportService.getEmailReportMapping();
	    		emp.setEmpType(OnboardingConstants.ALL);
	    		emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_ACTIVE);
	    		//empList =employeeService.empReportFiltering(emp,null);
	    		empList = this.employeeService.listBuddyEmp();//mehens-buddy
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

	
	   
}
