package com.capgemini.moodle.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.moodle.mail.*;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.service.MoodleService;
import com.capgemini.onboarding.util.PsaMailUtility;


public class MoodleEmailAlerts {
	
	@Autowired(required = true)
	private MoodleService moodleService;
	
	@Autowired(required = true)
	private PsaMailUtility psaMailUtility;
	
	private Logger logger = Logger.getLogger(MoodleEmailAlerts.class);
	
	//private Map<Integer, LocalDate> holidayMap;
	
	public void enrolmentReminder()throws Exception{
		logger.info("enrolmentReminder() Method executed at  :: "+ new Date());
		Calendar cal = Calendar.getInstance();
		try {
		MoodleEnrolDataDTO dto = new MoodleEnrolDataDTO();
		List<MoodleEnrolDataDTO> list = this.moodleService.fetchMailUserEnrolments(dto);//fetchUSerEnrolments(dto);
		Map<String, List> MoodleEnrolDataDTOByUser = new HashMap<String, List>();
		MoodleEnrolDataDTO dto1 = null;
		LocalDate dateToBeConsidered = null;
		LocalDate LocalDateToday = LocalDate.now();
		LocalDate firstReminderDate = null;
		LocalDate secondReminderDate = null;
		LocalDate OneDayReminderDate = null;
		Map<String, List<MoodleEnrolDataDTO>> firstReminderMap = new HashMap<String, List<MoodleEnrolDataDTO>>();
		Map<String, List<MoodleEnrolDataDTO>> secondReminderMap = new HashMap<String, List<MoodleEnrolDataDTO>>();
		Map<String, List<MoodleEnrolDataDTO>> oneDayReminderMap = new HashMap<String, List<MoodleEnrolDataDTO>>();
		List<MoodleEnrolDataDTO> mailList = null;
		int dayCount = 0;
		//
		while(dayCount < 5) { //10
			if(LocalDateToday.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				LocalDateToday = LocalDateToday.minusDays(3);
				dayCount++;
			}else {
				LocalDateToday = LocalDateToday.minusDays(1);
				dayCount++;
			}
			
			if(dayCount == 1) {
				OneDayReminderDate = LocalDateToday;
			}else if(dayCount == 3) { //7
				firstReminderDate = LocalDateToday;
			}else if(dayCount == 5) { //10
				secondReminderDate = LocalDateToday;
			}
		}
		
		logger.info("Before checking list size :" + firstReminderDate +" "+secondReminderDate+" "+OneDayReminderDate);
		
		if(list.size() == 0) {
			logger.info("No mails triggered from daily reminder scheduler : " + firstReminderDate +" : "+secondReminderDate+ " : "+OneDayReminderDate);
		}
		/*OneDayReminderDate = LocalDate.of(2020, 03, 11);
		secondReminderDate = LocalDate.of(2020, 03, 11);*/
		
		for(int i=0; i<list.size(); i++) {
			//1. check for date onboarding date & enrollment date whichever is latest for pending status records +7 days/ 10 days is today
			dto1 = list.get(i);
			if(dto1.getStatus().equalsIgnoreCase("Pending") && dto1.getOnboardingDate() != null) {
				dateToBeConsidered = (dto1.getOnboardingDate().isAfter(dto1.getUserEnrolDateConvert().toLocalDate()))? dto1.getOnboardingDate():dto1.getUserEnrolDateConvert().toLocalDate() ;
			}
			
			if(dateToBeConsidered != null) {
				//2. now 1, 3 and 5 days list - Rithesh (7 days list & 10 days list) old
				if(dateToBeConsidered.isEqual(OneDayReminderDate)) {
					if(oneDayReminderMap.get(dto1.getUsername()) != null)
					{
						mailList = oneDayReminderMap.get(dto1.getUsername());
						mailList.add(dto1);
						oneDayReminderMap.put(dto1.getUsername(), mailList);
						mailList = null;
					}else {
						mailList = new ArrayList<MoodleEnrolDataDTO>();
						mailList.add(dto1);
						oneDayReminderMap.put(dto1.getUsername(), mailList);
						mailList= null;
					}
				}else if(dateToBeConsidered.isEqual(firstReminderDate)){
					if(firstReminderMap.get(dto1.getUsername()) != null)
					{
						mailList = firstReminderMap.get(dto1.getUsername());
						mailList.add(dto1);
						firstReminderMap.put(dto1.getUsername(), mailList);
						mailList = null;
					}else {
						mailList = new ArrayList<MoodleEnrolDataDTO>();
						mailList.add(dto1);
						firstReminderMap.put(dto1.getUsername(), mailList);
						mailList= null;
					}
				}else if(dateToBeConsidered.isEqual(secondReminderDate)) {
					if(secondReminderMap.get(dto1.getUsername()) != null)
					{
						mailList = secondReminderMap.get(dto1.getUsername());
						mailList.add(dto1);
						secondReminderMap.put(dto1.getUsername(), mailList);
						mailList= null;
					}else {
						mailList = new ArrayList<MoodleEnrolDataDTO>();
						mailList.add(dto1);
						secondReminderMap.put(dto1.getUsername(), mailList);
						mailList= null;
					}
				}
			}
			
			dateToBeConsidered = null;
		}
		

		//3. trigger mails - 3 days reminder - old was 7 days
		String reminderSubj = "Hermes Learning - Course Completion Reminder!";
		List<String> coursesPending = new ArrayList<String>();
		
		oneDayReminderMap.forEach((key,value) -> {
			coursesPending.clear();
			for(MoodleEnrolDataDTO v : value) {
				coursesPending.add(v.getCoursename());
			}
			try {
				MailSender.send(psaMailUtility.prepareSheduleMailId(value.get(0).getUserEmail()), MoodleMailUtility.MoodleFirstAlert(value.get(0).getFirstName()+" "+value.get(0).getLastname(),coursesPending) , reminderSubj);
				logger.info("1 days mail triggered to "+value.get(0).getUserEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("1 days mail not triggered to "+value.get(0).getUserEmail());
				e.printStackTrace();
			}
		});
		
		firstReminderMap.forEach((key,value) -> {
			coursesPending.clear();
			for(MoodleEnrolDataDTO v : value) {
				coursesPending.add(v.getCoursename());
			}
			try {
				MailSender.send(psaMailUtility.prepareSheduleMailId(value.get(0).getUserEmail()), MoodleMailUtility.MoodleFirstAlert(value.get(0).getFirstName()+" "+value.get(0).getLastname(),coursesPending) , reminderSubj);
				logger.info("3 days mail triggered to "+value.get(0).getUserEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("3 days mail not triggered to "+value.get(0).getUserEmail());
				e.printStackTrace();
			}
		});
		
		//4. trigger mails - 10 days reminder cc manager - now 5 days - Rithesh
		secondReminderMap.forEach((key,value) -> {
			coursesPending.clear();
			for(MoodleEnrolDataDTO v : value) {
				coursesPending.add(v.getCoursename());
			}
			try {
				String[] ccList = {psaMailUtility.prepareMailId(value.get(0).getManagerEmail())};
				String[] recipientList = {psaMailUtility.prepareMailId(value.get(0).getUserEmail())};
				MailSender.send(recipientList, ccList, MoodleMailUtility.MoodleFirstAlert(value.get(0).getFirstName()+" "+value.get(0).getLastname(),coursesPending) , reminderSubj);
				logger.info("5 days mail triggered to "+value.get(0).getUserEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("5 days mail not triggered to "+value.get(0).getUserEmail());
				e.printStackTrace();
			}
		});
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			logger.info("Exception thrown "+e.getMessage());
			System.out.println("Exception in enrolmentReminder() - " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void enrolmentWeeklyReminder()throws Exception{
		logger.info("enrolmentReminder() Method executed at  :: "+ new Date());
		Calendar cal = Calendar.getInstance();
		try {
		MoodleEnrolDataDTO dto = new MoodleEnrolDataDTO();
		List<MoodleEnrolDataDTO> list = this.moodleService.fetchMailUserEnrolments(dto);//.fetchUSerEnrolments(dto);
		Map<String, List> MoodleEnrolDataDTOByUser = new HashMap<String, List>();
		MoodleEnrolDataDTO dto1 = null;
		LocalDate dateToBeConsidered = null;
		LocalDate LocalDateToday = LocalDate.now();
		LocalDate firstReminderDate = null;
		Map<String, List<MoodleEnrolDataDTO>> firstReminderMap = new HashMap<String, List<MoodleEnrolDataDTO>>();
		List<MoodleEnrolDataDTO> mailList = null;
		int dayCount = 0;
		// 
		while(dayCount < 10) { //15
			if(LocalDateToday.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				LocalDateToday = LocalDateToday.minusDays(3);
				dayCount++;
			}else {
				LocalDateToday = LocalDateToday.minusDays(1);
				dayCount++;
			}
			
			if(dayCount == 10) { //15
				firstReminderDate = LocalDateToday;
			}
		}
		
		logger.info("Before checking list size :" + firstReminderDate);
		
		if(list.size() == 0) {
			logger.info("No mails triggered from weekly reminder scheduler");
		}
		
		//firstReminderDate = LocalDate.of(2020, 03, 11);
		
		for(int i=0; i<list.size(); i++) {
			//1. check for date onboarding date & enrollment date whichever is latest for pending status records +7 days/ 10 days is today
			dto1 = list.get(i);
			if(dto1.getStatus().equalsIgnoreCase("Pending") && dto1.getOnboardingDate() != null) {
				dateToBeConsidered = (dto1.getOnboardingDate().isAfter(dto1.getUserEnrolDateConvert().toLocalDate()))? dto1.getOnboardingDate():dto1.getUserEnrolDateConvert().toLocalDate() ;
			}
			
			//2. 15 days list - new is 10 days list
			if(dateToBeConsidered != null ) {
				if(dateToBeConsidered.isBefore(firstReminderDate)){
					if(firstReminderMap.get(dto1.getUsername()) != null)
					{
						mailList = firstReminderMap.get(dto1.getUsername());
						mailList.add(dto1);
						firstReminderMap.put(dto1.getUsername(), mailList);
						mailList = null;
					}else {
						mailList = new ArrayList<MoodleEnrolDataDTO>();
						mailList.add(dto1);
						firstReminderMap.put(dto1.getUsername(), mailList);
						mailList = null;
					}
				}
			}
			
			dateToBeConsidered = null;
		}
		//3. trigger mails - after 10 days reminder
				String firstReminderSubj = "Hermes Learning - Course Completion Reminder!";
				List<String> coursesPending = new ArrayList<String>();
				firstReminderMap.forEach((key,value) -> {
					coursesPending.clear();
					for(MoodleEnrolDataDTO v : value) {
						coursesPending.add(v.getCoursename());
					}
					try {
						String[] ccList = {psaMailUtility.prepareMailId(value.get(0).getManagerEmail())};
						String[] recipientList = {psaMailUtility.prepareMailId(value.get(0).getUserEmail())};
						MailSender.send(recipientList, ccList, MoodleMailUtility.MoodleFirstAlert(value.get(0).getFirstName()+" "+value.get(0).getLastname(),coursesPending) , firstReminderSubj);
						logger.info("10 days mail triggered to "+value.get(0).getUserEmail());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.info("10 days mail not triggered to "+value.get(0).getUserEmail());
						e.printStackTrace();
					}
				});
		
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			logger.info("Exception thrown "+e.getMessage());
			System.out.println("Exception in enrolmentWeeklyReminder() - " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
}
