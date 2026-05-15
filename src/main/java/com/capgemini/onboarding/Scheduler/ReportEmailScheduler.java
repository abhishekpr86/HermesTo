package com.capgemini.onboarding.Scheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.moodle.model.User;
import com.capgemini.onboarding.GenericExcel;
import com.capgemini.onboarding.MoodleController;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.EmailReport;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.ResourceCohortMapping;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.MoodleService;
import com.capgemini.onboarding.service.EmailReportService;
import com.capgemini.onboarding.service.PreOnbEmployeeService;
import com.capgemini.onboarding.service.ResourceCohortService;
import com.capgemini.onboarding.util.PsaMailUtility;
import com.capgemini.onboarding.mail.MailSender;


public class ReportEmailScheduler {
	
private Logger logger = Logger.getLogger(ReportEmailScheduler.class);
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
    @Autowired(required = true)
    private PsaMailUtility psaMailUtility;
    
    
    @Autowired(required = true)
    private EmailReportService emailReportService;
    
    @Autowired(required = true)
    private MoodleController moodleController;
    
   @Autowired(required = true)
    private HttpServletResponse httpResponse;
    
    
    @Autowired(required = true)
	private MoodleService moodleService;
    
    @Autowired(required = true)
    private ResourceCohortService resourceCohortService;
    
	@Value("${ad.activeDirUsername}")
	private static String username;
   
	private String absolutePath;
    
    
    public void sendReportsMails() {
    	
    	try {
    		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
    		String currentTimestamp = df.format(new Date());
    		//String filename="EmployeeList(";
    		String filename="";
    		Employee emp = new Employee();
    		
    		String[] recipient ={psaMailUtility.prepareSheduleMailId(this.preOnbEmpService.getDLListbyRole("ReportsMail").getEmail())}; String[] ccList = {};
    		String msgSubject = "#HermesToAutoReport#";
    		String messageString = "";
    		logger.info("inside resource report excel export function");
    		String excelFilePath = "C:/OnboardingToolExport/Scheduler/"; //Employee List  ( India - Internal - ActiveResources)
    		
    		// list of reports to be sent 
    		List<EmailReport> list = this.emailReportService.getEmailReportMapping();
    		
    		emp.setEmpType(OnboardingConstants.ALL);
    		String derivedFilename = "";
    		for(EmailReport rep : list) {
    			
    			switch(rep.getReport_name()) {
    			//switch(rep) {
    			case OnboardingConstants.EMP_RESOURCE_ACTIVE : emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_ACTIVE);
    									 //derivedFilename=filename+"EMP_RESOURCE_ACTIVE"+")"+ currentTimestamp + ".xlsx";
    									derivedFilename=filename+OnboardingConstants.EMP_RESOURCE_ACTIVE+".xlsx";
    									break;
    			case OnboardingConstants.EMP_RESOURCE_ALL : emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_ALL);
    								  //derivedFilename=filename+"EMP_RESOURCE_ALL"+")"+ currentTimestamp + ".xlsx";
    									derivedFilename=filename+OnboardingConstants.EMP_RESOURCE_ALL+".xlsx";
    									break;
    			default : break;
    			}
    			//this.employeeService.resourceReportFunctn(emp, derivedFilename,excelFilePath);
    			this.employeeService.resourceReportFn(emp, derivedFilename,excelFilePath);//mehens-new
        		logger.info("------- FILENAME -------"+excelFilePath+derivedFilename);
        		//now attach the available file in mail and sent.
        		
        		MailSender.sendReportAttachment(recipient, ccList, messageString, msgSubject, excelFilePath+derivedFilename);
        		
        		//delete file from disk location
        		if(!derivedFilename.equals("")) {
        		boolean fileDeleted = this.employeeService.deleteReportFileFromDisk(excelFilePath+derivedFilename);
        		logger.info(derivedFilename +" File deleted");
        		System.out.println("");
        		}
        		//filename rename
        		derivedFilename ="";
    		}
    		
    		
    	}
    	catch(Exception e) {
    		logger.info("Error in Report email scheduler "+e.getMessage());
    	}
    	
    }
    
    
    //New method to send onboarding Request Report via night scheduler
    public void sendOnboardingRequestReportMail() {
    	try {
    		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
    		String currentTimestamp = df.format(new Date());

    		String filename="";
    		String derivedFilename = "";
    		PreOnbEmployee emp = new PreOnbEmployee();
    		emp.setEmpType(OnboardingConstants.ALL);
    		
    		String[] recipient ={psaMailUtility.prepareSheduleMailId(this.preOnbEmpService.getDLListbyRole("ReportsMail").getEmail())}; String[] ccList = {};

    		String msgSubject = "#HermesToAutoReport#";
    		String messageString = "";
    		logger.info("inside Onboarding Request Report scheduler function");
    		//String excelFilePath = "C:/OnboardingToolExport/Scheduler/";
    		String excelFilePath = "C:/OnboardingToolExport/";
    		
    		derivedFilename=filename+OnboardingConstants.Onboarding_Request+".xlsx";
    		
    		this.preOnbEmpService.onbReqReport(emp, derivedFilename,excelFilePath);
    		logger.info("------- FILENAME -------"+ excelFilePath+derivedFilename);
    		
    		//now attach the available file in mail and sent.
    		MailSender.sendReportAttachment(recipient, ccList, messageString, msgSubject, excelFilePath+derivedFilename);
    		logger.info("onboarding request report mail sent");
    		
    		//delete file from disk location
    		if(!derivedFilename.equals("")) {
    			boolean fileDeleted = this.employeeService.deleteReportFileFromDisk(excelFilePath+derivedFilename);
    			logger.info(derivedFilename +" File deleted");
    		}
    		
    		//filename rename
    		derivedFilename ="";
    	}
    	catch(Exception e) {
    		logger.info("Error in Onboarding Request Report email scheduler "+e.getMessage());
    	}
    }
    
    /* Method to send Billable and Shadow report daily - mehens */
    public void sendBillableShadowReportsMails() {
    	try {
    		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
    		String currentTimestamp = df.format(new Date());

    		String filename="";
    		Employee emp = new Employee();
    		
    		String[] recipient ={psaMailUtility.prepareSheduleMailId(this.preOnbEmpService.getDLListbyRole("ReportsMail").getEmail())}; String[] ccList = {};

    		String msgSubject = "#HermesToAutoReport#";
    		String messageString = "";
    		logger.info("inside billable & shadow resource report scheduler function");
    		String excelFilePath = "C:/OnboardingToolExport/Scheduler/";
    		
    		// list of reports to be sent 
    		List<EmailReport> list = this.emailReportService.getEmailReportMapping();
    		
    		emp.setEmpType(OnboardingConstants.ALL);
    		String derivedFilename = "";
    		for(EmailReport rep : list) {
    			logger.info("report type - "+rep.getReport_name());
    			
    			switch(rep.getReport_name()) {
    			
    			case OnboardingConstants.EMP_RESOURCE_BILLABLE : emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_BILLABLE);
    									derivedFilename=filename+OnboardingConstants.EMP_RESOURCE_BILLABLE+".xlsx";
    									break;
    									
    			case OnboardingConstants.EMP_RESOURCE_SHADOW : emp.setResourceStatus(OnboardingConstants.EMP_RESOURCE_SHADOW);
    									derivedFilename=filename+OnboardingConstants.EMP_RESOURCE_SHADOW+".xlsx";
    									break;
    			default : break;
    			}
    			logger.info("emp status- "+emp.getResourceStatus());
    			//this.employeeService.resourceReportFunctn(emp, derivedFilename,excelFilePath);
    			this.employeeService.resourceReportFn(emp, derivedFilename,excelFilePath);//mehens-new
        		logger.info("------- FILENAME -------"+excelFilePath+derivedFilename);
        		
        		//now attach the available file in mail and sent.
        		MailSender.sendReportAttachment(recipient, ccList, messageString, msgSubject, excelFilePath+derivedFilename);
        		logger.info("report mail sent");
        		
        		//delete file from disk location
        		if(!derivedFilename.equals("")) {
        			boolean fileDeleted = this.employeeService.deleteReportFileFromDisk(excelFilePath+derivedFilename);
        			logger.info(derivedFilename +" File deleted");
        		}
        		
        		//filename rename
        		derivedFilename ="";
    		}
    		
    		
    	}
    	catch(Exception e) {
    		logger.info("Error in Billable & Shadow Report email scheduler "+e.getMessage());
    	}
    }
    
    
    
public void sendMoodleReportsMails() {
    	
    	try {
    		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
    		String currentTimestamp = df.format(new Date());
    		//String filename="EmployeeList(";
    		String filename="";
    		//Employee emp = new Employee();
    		//emp.setEmpType(OnboardingConstants.ALL);
    			MoodleEnrolDataDTO dto = new MoodleEnrolDataDTO();
    			/*if(!username.equalsIgnoreCase("")) {
    				dto.setUsername(username);
    			}*/
    			String[] recipient ={psaMailUtility.prepareSheduleMailId(this.preOnbEmpService.getDLListbyRole("ReportsMail").getEmail())}; String[] ccList = {};
        		String msgSubject = "#HermesToAutoReport#";
        		String messageString = "";
        		logger.info("inside resource report excel export function");
        		String excelFilePath = "C:/OnboardingToolExport/Scheduler/"; 
        	
        		

        		// list of reports to be sent
        		List<EmailReport> list = this.emailReportService.getEmailReportMapping();
        		
        		
        		String derivedFilename = "";
        		for(EmailReport rep : list) {
        			
        			if(rep.getReport_name().equals(OnboardingConstants.EMP_MOODLE_REPORT)) {
        			//switch(rep) {
        	
        									 //derivedFilename=filename+"EMP_RESOURCE_ACTIVE"+")"+ currentTimestamp + ".xlsx";
                    derivedFilename=filename+OnboardingConstants.EMP_MOODLE_REPORT1+".xlsx";
                    User moodleUser = this.moodleService.getUserByUsername(dto.getUsername());
                    System.getProperty("user.name");
                    
        			this.moodleController.createMoodleReportFiles(httpResponse,derivedFilename,excelFilePath,"all");
            		logger.info("------- FILENAME -------"+excelFilePath+derivedFilename);
            		//now attach the available file in mail and sent.
            		
            		MailSender.sendReportAttachment(recipient, ccList, messageString, msgSubject, excelFilePath+derivedFilename);
            		
            		//delete file from disk location
            		boolean fileDeleted = this.employeeService.deleteReportFileFromDisk(excelFilePath+derivedFilename);
            		System.out.println("");
            		//filename rename
            		derivedFilename ="";
        									
        			
        			}
        			
    		}
    		}
    		catch(Exception e) {
    			logger.info(e.getStackTrace());
    			logger.error(" Some Problem occurs Moodle Report:- "+e.getMessage());
    		}
    
    }

public void sendMoodleActiveReportsMails() {

	try {
		String filename = "";
		MoodleEnrolDataDTO dto = new MoodleEnrolDataDTO();
		String[] recipient = {psaMailUtility.prepareSheduleMailId(this.preOnbEmpService.getDLListbyRole("ReportsMail").getEmail())};
		String[] ccList = {};
		String msgSubject = "#HermesToAutoReport#";
		String messageString = "";
		logger.info("inside moodle active resources report excel export function");
		String excelFilePath = "C:/OnboardingToolExport/Scheduler/";

		String derivedFilename = filename + OnboardingConstants.EMP_MOODLE_ACTIVE_REPORT1 + ".xlsx";
		this.moodleController.createMoodleReportFiles(httpResponse, derivedFilename, excelFilePath, "active");
		logger.info("------- FILENAME -------" + excelFilePath + derivedFilename);

		MailSender.sendReportAttachment(recipient, ccList, messageString, msgSubject, excelFilePath + derivedFilename);

		boolean fileDeleted = this.employeeService.deleteReportFileFromDisk(excelFilePath + derivedFilename);
	}
	catch(Exception e) {
		logger.info(e.getStackTrace());
		logger.error(" Some Problem occurs Moodle Active Report:- " + e.getMessage());
	}
}

	    /* Method to send Moodle Enrolment CSV report daily at 10 pm CET - mehens */
		public void sendMoodleEnrolmentReportMail() {
	
			try {
	    		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
	    		String currentTimestamp = df.format(new Date());
	    		logger.info("Inside moodle emrolment report csv export function "+currentTimestamp);

	    			String[] recipient ={psaMailUtility.prepareSheduleMailId(this.preOnbEmpService.getDLListbyRole("ReportsMail").getEmail())}; String[] ccList = {};
	        		String msgSubject = "#HermesToAutoReport#";
	        		String messageString = "";

	        		//String csvFilePath = "C:/OnboardingToolExport/Scheduler/"; 
	        	
	        		String derivedFilename = OnboardingConstants.EMP_MOODLE_ENROL_REPORT+".csv";
	        		
	        		File file = new File(derivedFilename);
	        		logger.info("----------file absolute Path---------"+file.getAbsolutePath());
	        		this.absolutePath = file.getAbsolutePath();
	        		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

	        		List<ResourceCohortMapping> dataFromService = this.resourceCohortService.getCohortsforRange(null, null);

	        		writer.write("username,cohort1"+"\n");
	        		if(dataFromService != null) {
		        		for (int i = 0; i < dataFromService.size(); i++) {
		        			ResourceCohortMapping data = dataFromService.get(i);
		        			writer.write(data.getCorp_id() + ","
		        					+ data.getCohort() + "\n"); 
		        		}
	        		}
	        		writer.close();
	        		
	        		//sending .csv file attached in mail to Valerie 
	        	    MailSender.sendReportAttachment(recipient, ccList, messageString, msgSubject, file.getAbsolutePath());
            		
            		//delete file from disk location
	        	  /*  if(!file.getAbsolutePath().equals("")) {
	            		boolean fileDeleted = this.employeeService.deleteReportFileFromDisk(file.getAbsolutePath());
	            		logger.info("is file Deleted: "+fileDeleted);
	        	    }*/
            		
	        	  //filename rename
            		derivedFilename ="";
            		
			}
			catch(Exception e) {
    			logger.info(e.getStackTrace());
    			logger.error(" Some Problem occurs Moodle Report:- "+e.getMessage());
    		}
		}
    
}
