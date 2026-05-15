package com.capgemini.onboarding.Scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.onboarding.mail.MailUtility;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.MoodleService;
import com.capgemini.onboarding.service.PreOnbEmployeeService;
import com.capgemini.onboarding.util.PsaMailUtility;

public class WelcomeEmails {
	
	  

	private Logger logger = Logger.getLogger(WelcomeEmails.class);
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
    @Autowired(required = true)
    private PsaMailUtility psaMailUtility;
    
    @Autowired(required = true)
	private MoodleService moodleService;
    
    
    public void welcomeEmailScheduler() {
    	logger.info("welcomeEmailScheduler() Method executed at  :: "+ new Date());
    	
    	Map<String, String> inlineImages = new HashMap<String, String>();
    	
    	List<Employee> WelcomeEmailList = new ArrayList<Employee>();
    	//C:\apache-tomcat-8.0.33\webapps\onboarding\resources\images\mail1.png
    	
    	//inlineImages.put("image1", "C:\\apache-tomcat-8.0.33\\webapps\\onboarding\\resources\\images\\mail1.png"); 
		//inlineImages.put("image2", "C:\\apache-tomcat-8.0.33\\webapps\\onboarding\\resources\\images\\mail2.jpg");
    	
    	inlineImages.put("image1", "C:\\apache-tomcat-9.0.56-windows-x64\\apache-tomcat-9.0.56\\webapps\\onboarding\\resources\\images\\mail1.png");
    	inlineImages.put("image1", "C:\\apache-tomcat-9.0.56-windows-x64\\apache-tomcat-9.0.56\\webapps\\onboarding\\resources\\images\\mail2.jpg");
    	
		ArrayList<String> imgArr = new ArrayList<String>();
		String docPath = "http://frparhermesto/HERMES_Academy/learning.html";
		
		String welcomeSubject = "Welcome to HERMES STELLANTIS";
		try {
    	//logger.info("inlineImages image1"+inlineImages.get("image1"));
    	WelcomeEmailList = this.moodleService.getPendingEnrolmentResource();
    	for (Employee employee : WelcomeEmailList) {
    		logger.info("Welcome email - start for: "+employee.getCorpId());
    		MailSender.sendImageMail(psaMailUtility.prepareMailId(employee.getEmail()), MailUtility.createFirstWelcomeEmailCommon(employee.getFirstName(), docPath, employee.getSpoc().getSpocName(), employee.getSpoc().getSpocEmail(), (employee.getCountry().getCountryName().equalsIgnoreCase("India"))? true : false, imgArr), welcomeSubject, inlineImages);
    		//psaMailUtility.sendDynamicWelcomeEmail(MailUtility.dynamicWelcomeEmail(employee),inlineImages);//commented from the beginning
    		
    		//BCC
			//DLList dlBcc = preOnbEmpService.getDLListbyRole("bcc");
			//MailSender.sendImageMailNew(psaMailUtility.prepareMailId(employee.getEmail()),psaMailUtility.prepareMailId(dlBcc.getEmail()), MailUtility.createFirstWelcomeEmailCommon(employee.getFirstName(), docPath, employee.getSpoc().getSpocName(), employee.getSpoc().getSpocEmail(), (employee.getCountry().getCountryName().equalsIgnoreCase("India"))? true : false, imgArr), welcomeSubject, inlineImages);
			
    		Employee emp = this.employeeService.getEmployeeById(employee.getId());
    		emp.setWelcomeEmailFlag(1);
    		this.employeeService.updateEmployee(emp);
    		logger.info("welcomeEmailScheduler - Welcome email sent to "+employee.getEmail());
		  }
		}
		catch(Exception e) {
			logger.info("Error in welcome emial scheduler "+e.getMessage());
		}
    }
}
