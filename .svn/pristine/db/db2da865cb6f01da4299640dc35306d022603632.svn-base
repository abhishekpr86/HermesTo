package com.capgemini.onboarding.Scheduler;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.onboarding.mail.MailUtility;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.Users;
import com.capgemini.onboarding.service.EmployeeService;

@Service
public class MainScheduler implements Serializable  {
	
	private Logger logger = Logger.getLogger(MainScheduler.class);

	
	@Autowired(required = true)
	private EmployeeService employeeService;

	public Date subtractDay(Date date) {

	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_MONTH, -1);
	    return cal.getTime();
	}

@Transactional
	public void run() throws IOException {
		logger.info("employeeService is  : " +employeeService);
		
		Date date = new Date();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		int hours = cal1.get(Calendar.HOUR_OF_DAY);
		int minutes = cal1.get(Calendar.MINUTE);
		
		String Original_excel ="D:/Mail";
		DateFormat dt = new SimpleDateFormat("dd-MMM-yyyy");
		Date previousDate = subtractDay(date);
		String textFileName = Original_excel + "-" + dt.format(date) + ".txt";
		String prevTextFileName = Original_excel + "-" + dt.format(previousDate) + ".txt";
		
		if (hours == 16 && minutes > 00) {
			File newTextFile = new File(textFileName);
			if (newTextFile.exists() && !newTextFile.isDirectory()) {
				logger.info("Mail has been send already");
			}
			else{
				newTextFile.createNewFile();
				List<Users> emList = null; //employeeService.getAllEM();
				
				/*Employee emp = new Employee();
				Date actualEndDate = emp.getActualEndDate();
				Date updatedActualDate = emp.getActualEndDate();
				if((updatedActualDate!=null && actualEndDate == null) || (updatedActualDate!=null && updatedActualDate.compareTo(actualEndDate) != 0)) {
				logger.info("i m in date format");
					try {
						String actualEndDateStr = "&lt;blank value&gt;";
						String updatedActualDateStr = "&lt;blank value&gt;";
						if(actualEndDate!=null) {
						actualEndDateStr = OnboardingConstants.DATE_FORMAT.format(actualEndDate);
						}
						if(updatedActualDate!=null) {
							updatedActualDateStr = OnboardingConstants.DATE_FORMAT.format(updatedActualDate);
							}*/
				if (emList != null && emList.size() > 0) {
					for (int i = 0; i < emList.size(); i++) {
						Employee emDetails = employeeService.getEmployeeByCorpId(emList.get(i).getUserName());
						if (emDetails != null) {
							List<Employee> offBoardReportList = employeeService.getOffBoardingListForNext30Days(emDetails);
							try {
								if (offBoardReportList != null && offBoardReportList.size() > 0) {
									MailSender.sendOffBoardingReport(emDetails,MailUtility.offBoardingReportEmailBody(offBoardReportList),"Off-Boarding Report (Resources with Off-Boarding date within the next 30 days)");
								}

							} catch (Exception e) {
								logger.error(" Some Problem occurs :- "+e.getMessage());
						   }
						}
					}
				}
			}
				File oldTextFile = new File(prevTextFileName);
				oldTextFile.delete();
			}			
		}
    }
