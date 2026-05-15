package com.capgemini.onboarding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.moodle.model.MoodleCohortMembers;
import com.capgemini.moodle.model.User;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.ResourceCohortMapping;
import com.capgemini.onboarding.service.MoodleService;
import com.capgemini.onboarding.service.ResourceCohortService;

@Controller
public class MoodleController {

	private Logger logger = Logger.getLogger(MoodleController.class);

	/** Safe characters allowed in a downloaded filename. */
	private static final java.util.regex.Pattern SAFE_FILENAME = java.util.regex.Pattern.compile("[\\w\\-\\.]+");

	@Autowired(required = true)
    private ResourceCohortService resourceCohortService;

	@Autowired(required = true)
	private MoodleService moodleService;
	
	@RequestMapping(value = "/moodleUserEnrol", method = RequestMethod.GET)
	public String moodleEnrolsearch(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {		
			model.addAttribute("checkUserType", "ViewMode");
		}	
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			model.addAttribute("checkUserTypeforUM", "UserManagement");

		}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			  
  			model.addAttribute("checkUserType", "BundleEM");
  		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		}else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		}
  		else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
			  model.addAttribute("checkUserType", "ASL");
		}
		model.addAttribute("employee", new Employee());		
		model.addAttribute("startDate", new Date());
		return "moodleEnrol";
	}
	
	@RequestMapping(value = "/createFile", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String createFile(/*@RequestParam("startDate") String startDate, 
			@RequestParam("endDate") String endDate , */HttpServletResponse httpResponse) throws Exception {
		
    
		// Write to the JVM temp directory — never use a relative or hardcoded path.
		File file = new File(System.getProperty("java.io.tmpdir"), "Moodle-Enrolment.csv");
		logger.info("createFile: writing to " + file.getAbsolutePath());
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		List<ResourceCohortMapping> dataFromService = this.resourceCohortService.getCohortsforRange(null, null);
		httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + "Moodle-Enrolment" + ".csv\"");
		httpResponse.setContentType("application/csv");

		writer.write("username,cohort1"+"\n");
		if(dataFromService != null) {
		for (int i = 0; i < dataFromService.size(); i++) {
			ResourceCohortMapping data = dataFromService.get(i);
			writer.write(data.getCorp_id() + ","
					+ data.getCohort() + "\n"); 
		}
		}
		writer.close();

		logger.info("-----------");
		return file.getName();
	}
	
	@RequestMapping(value = "/downloadFile/{file}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("file") String file, HttpServletResponse response) throws Exception {

		// Validate: only safe characters permitted — prevents path traversal
		if (!SAFE_FILENAME.matcher(file).matches()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid filename");
			return;
		}

		File metricsReportFile = new File(System.getProperty("java.io.tmpdir"), file);
		// Canonical path check — belt-and-suspenders against traversal
		if (!metricsReportFile.getCanonicalPath().startsWith(
				new File(System.getProperty("java.io.tmpdir")).getCanonicalPath())) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		logger.info("downloadFile: serving " + metricsReportFile.getName());

		InputStream is = new FileInputStream(metricsReportFile);
		response.setHeader("Content-Disposition", "attachment; filename=" + metricsReportFile.getName());
		FileCopyUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
		metricsReportFile.delete();
	}
	
	@RequestMapping(value = "/downloadFiles/{file}", method = RequestMethod.GET)
	public void downloadFiles(@PathVariable("file") String file, HttpServletResponse response) throws Exception {

		// Validate: only safe characters permitted — prevents path traversal
		if (!SAFE_FILENAME.matcher(file).matches()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid filename");
			return;
		}

		File metricsReportFile = new File(System.getProperty("java.io.tmpdir"), file);
		// Canonical path check — belt-and-suspenders against traversal
		if (!metricsReportFile.getCanonicalPath().startsWith(
				new File(System.getProperty("java.io.tmpdir")).getCanonicalPath())) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		logger.info("downloadFiles: serving " + metricsReportFile.getName());

		InputStream is = new FileInputStream(metricsReportFile);
		response.setHeader("Content-Disposition", "attachment; filename=" + metricsReportFile.getName());
		FileCopyUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
		metricsReportFile.delete();
	}
	
	
	public void disableMoodleUser(User user) {
		
		this.moodleService.deleteUser(user);
	}
	
	
	@RequestMapping(value = "/moodleUserEnrolData", method = RequestMethod.GET)
	public String moodleUserEnrolData(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {		
			model.addAttribute("checkUserType", "ViewMode");
		}	
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
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
		model.addAttribute("moodle", new MoodleEnrolDataDTO());		
		return "moodleUserEnrolReport";
	}
	
	 
	@RequestMapping(value = "/moodleDataSearch", method = RequestMethod.POST)
	public String fetchUSerEnrolments(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute("moodle") MoodleEnrolDataDTO dto) {
		List<MoodleEnrolDataDTO> list = this.moodleService.fetchUSerEnrolments(dto);
		
		model.addAttribute("moodleSearchList", list);
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm");
		model.addAttribute("DynamicHeadingValue","MoodleCompletion_"+df.format(new Date()));
		if(list != null && list.size() <= 0) {
			model.addAttribute("successMsg", "No Records found.");
		}
		HttpSession session = request.getSession();
		String role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {		
			model.addAttribute("checkUserType", "ViewMode");
		}	
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
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
		return "moodleUserEnrolReport";
		
	}
	
	@RequestMapping(value = "/MoodleReportExcelExport", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String createMoodleReportFile(@RequestParam("username") String username,@RequestParam(value = "resStatus", defaultValue = "all") String resStatus, HttpServletResponse httpResponse) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
		String currentTimestamp = df.format(new Date());
		String fileName = "active".equalsIgnoreCase(resStatus)
				? "MoodleCompletion_ActiveResources_" + currentTimestamp
				: "MoodleCompletion_" + currentTimestamp;
		try {
			MoodleEnrolDataDTO dto = new MoodleEnrolDataDTO();
			if(!username.equalsIgnoreCase("")) {
				dto.setUsername(username);
			}
			dto.setResStatus(resStatus);
			List<MoodleEnrolDataDTO> list = this.moodleService.fetchUSerEnrolments(dto);
			logger.info("list " +list.size());
			String excelFilePath = System.getProperty("java.io.tmpdir") + java.io.File.separator + fileName + ".xlsx";
			//String excelFilePath =this.absolutePath+currentTimestamp+".xlsx";
					
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			Sheet sheet = workbook.createSheet("Sheet1");
	        GenericExcel<MoodleEnrolDataDTO> g = new GenericExcel<MoodleEnrolDataDTO>();
	        
	        Class T = Class.forName("com.capgemini.onboarding.dto.MoodleEnrolDataDTO");
	        String[] columnList = {"User Name","First Name","Last Name","Course Name","Onboarding Date","Actual Offboarding Date","User Enrolment Date","Course Completion Date","Status","Bundle","Manager Email","EM","Bundle EM","Primary Program","Email"};
	        g.writeHeaderLine(sheet,T,columnList);
	        g.writeDataLines(list, workbook, sheet, T);
	        logger.info("after cell update");
	        httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName+ ".xlsx\"");
			httpResponse.setContentType("application/xlsx");
	        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	        logger.info("before workbook write");
	        workbook.write(outputStream);
	        outputStream.close();
	        workbook.close();
	        logger.info("workbook close");
		}
		catch(Exception e) {
			logger.info(e.getStackTrace());
			logger.error(" Some Problem occurs Moodle Report:- "+e.getMessage());
		}
		
			return fileName+".xlsx";
			
		
	}
	
	public List<MoodleCohortMembers> fetchCohortMembersList(){

		return null;
	}
	

@Transactional
public @ResponseBody String createMoodleReportFiles( HttpServletResponse httpResponse,String filename, String excelFilePath,String resStatus) throws Exception {
		

	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
	String currentTimestamp = df.format(new Date());
	logger.info("Moodle Report Scheduler");
	try {
		MoodleEnrolDataDTO dto = new MoodleEnrolDataDTO();
		dto.setResStatus(resStatus);
		
		List<MoodleEnrolDataDTO> list = this.moodleService.fetchUSerEnrolments(dto);
		logger.info("list " +list.size());
		//String excelFilePath = "C:/OnboardingToolExport/MoodleCompletion_"+currentTimestamp+".xlsx";
		//String excelFilePath =this.absolutePath+currentTimestamp+".xlsx";
				
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
        GenericExcel<MoodleEnrolDataDTO> g = new GenericExcel<MoodleEnrolDataDTO>();
        
        Class T = Class.forName("com.capgemini.onboarding.dto.MoodleEnrolDataDTO");
        String[] columnList = {"User Name","First Name","Last Name","Course Name","Onboarding Date","Actual Offboarding Date","User Enrolment Date","Course Completion Date","Status","Bundle","Manager Email","EM","Bundle EM","Primary Program","Email"};
        g.writeHeaderLine(sheet,T,columnList);
        g.writeDataLines(list, workbook, sheet, T);
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
		logger.error(" Some Problem occurs Moodle Report:- "+e.getMessage());
	}
	return "MoodleCompletion_"+currentTimestamp+".xlsx";
	}

	
	
	

}
