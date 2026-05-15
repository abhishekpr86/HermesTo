package com.capgemini.onboarding.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.capgemini.onboarding.model.PrimaryProgram;

public class MoodleEnrolDataDTO {
	
	private String username;
	private String firstName;
	private String lastname;
	private String coursename;
	private Long userEnrolDate;
	private Long courseCompletionDate;
	//@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime userEnrolDateConvert;
	private String enrolDateString = "";
	//@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime courseCompletionDateConvert;
	private String completionDateString ="";
	private String status;
	private String bis;
	private String manager;
	private String EM;
	private String BM;
	private LocalDate onboardingDate;
	private LocalDate offboardingDate;
	private String userEmail;
	private String managerEmail;
	private String emMail;
	private String primaryProgram;
	private String resStatus;


	public MoodleEnrolDataDTO() {
	}
	
	public MoodleEnrolDataDTO(String username, String firstName, String lastName, String coursename, Long userEnrolDate, Long courseCompletionDate, String status) {
		
		this.username = username;
		this.firstName = firstName;
		this.lastname = lastName;
		this.coursename = coursename;
		this.userEnrolDate = userEnrolDate;
		this.courseCompletionDate = courseCompletionDate;
		this.status = status;
		this.userEnrolDateConvert = millsToLocalDateTime(userEnrolDate*1000);
		if(courseCompletionDate != null) {
			this.courseCompletionDateConvert = millsToLocalDateTime(courseCompletionDate*1000);
			this.completionDateString = stringDate(courseCompletionDateConvert);
		}
		this.enrolDateString = stringDate(userEnrolDateConvert);
		
	}
	
	public String getPrimaryProgram() {
		return primaryProgram;
	}

	public void setPrimaryProgram(String primaryProgram) {
		this.primaryProgram = primaryProgram;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Long getUserEnrolDate() {
		return userEnrolDate;
	}
	public void setUserEnrolDate(Long userEnrolDate) {
		this.userEnrolDate = userEnrolDate;
	}
	public Long getCourseCompletionDate() {
		return courseCompletionDate;
	}
	public void setCourseCompletionDate(Long courseCompletionDate) {
		this.courseCompletionDate = courseCompletionDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getUserEnrolDateConvert() {
		return userEnrolDateConvert;
	}

	public void setUserEnrolDateConvert(LocalDateTime userEnrolDateConvert) {
		this.userEnrolDateConvert = userEnrolDateConvert;
	}

	public LocalDateTime getCourseCompletionDateConvert() {
		return courseCompletionDateConvert;
	}

	public void setCourseCompletionDateConvert(LocalDateTime courseCompletionDateConvert) {
		this.courseCompletionDateConvert = courseCompletionDateConvert;
	}
	
	public String getBis() {
		return bis;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}
	
	public String getEM() {
		return EM;
	}

	public void setEM(String eM) {
		this.EM = eM;
	}

	public String getBM() {
		return BM;
	}

	public void setBM(String bM) {
		this.BM = bM;
	}
	
	public LocalDate getOnboardingDate() {
		return onboardingDate;
	}

	public void setOnboardingDate(Date onboardingDate) {
		this.onboardingDate = onboardingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//onboardingDate;
	}
	
	public LocalDate getOffboardingDate() {
		return offboardingDate;
	}

	public void setOffboardingDate(Date offboardingDate) {
		this.offboardingDate = offboardingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	public String getEmMail() {
		return emMail;
	}

	public void setEmMail(String emMail) {
		this.emMail = emMail;
	}
	
	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	
	public String getEnrolDateString() {
		return enrolDateString;
	}

	public void setEnrolDateString(String enrolDateString) {
		this.enrolDateString = enrolDateString;
	}

	public String getCompletionDateString() {
		return completionDateString;
	}

	public void setCompletionDateString(String completionDateString) {
		this.completionDateString = completionDateString;
	}

	public static LocalDateTime millsToLocalDateTime(long millis) {
	      Instant instant = Instant.ofEpochMilli(millis);
	      LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();//.toLocalDateTime();
	     
	     
	      return date;
	 }
	public String stringDate(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

}
