package com.capgemini.onboarding.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OnboardingConstants {

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final String ALL = "All";
	public static final String EMP_TYPE_INTERNAL = "Internal";
	public static final String EMP_TYPE_EXTERNAL = "External";
	public static final String EMP_RESOURCE_ACTIVE = "ActiveResources";
	public static final String EMP_RESOURCE_ALL = "AllResources";
	public static final String EMP_RESOURCE_BILLABLE = "BillableResources";
	public static final String EMP_RESOURCE_SHADOW = "ShadowResources";
	public static final String TRAINING_COMPLETED = "Completed";
	public static final String TRAINING_NOT_COMPLETED = "Not Completed";
	public static final String Onboarding_Request ="OnboardingRequest";//new
	public static final String True = "true";
	public static final String False = "false";
	

	public static final int France = 1;
	public static final int India = 2;
	public static final int China = 3;
	public static final int Spain = 4;
	public static final int Morocco = 5;
	public static final int Germany = 6;
	public static final int UK = 7;
	public static final int AllCountry = 0;
	
	public static final int One =1;
	public static final int Five =5;
	
	public static final String EMP_MOODLE_REPORT = "MoodleReport";
	public static final String EMP_MOODLE_REPORT1 = "Moodle-Report";
	public static final String EMP_MOODLE_ACTIVE_REPORT1 = "Moodle-Active-Report";
	public static final String EMP_MOODLE_ENROL_REPORT = "MoodleEnrolment";//mehens-new
	
	
	/*public static final String RM = "[ROLE_3]"; //RM
	public static final String Bundle_EM = "[ROLE_2]";  //BundleEM
	public static final String Other_users = "[ROLE_1]"; //Admin
	public static final String ReadOnlyUsers = "[Role_4]"; //Read only
	public static final String UserManagement = "[Role_5]"; //BIS PMO
	public static final String RM_PMO = "[Role_6]"; //RM_PMO*/
	
	public static final String RM = "ROLE_3"; //RM
	public static final String Bundle_EM = "ROLE_2";  //BundleEM
	public static final String Other_users = "ROLE_1"; //Admin
	public static final String ReadOnlyUsers = "Role_4"; //Read only
	public static final String UserManagement = "Role_5"; //BIS PMO
	public static final String RM_PMO = "Role_6"; //RM_PMO
	public static final String ASL = "Role_7"; //ASL
	
	public static final String statusRMApproved = "RMApproved";
	public static final String statusBISPMOSubmit = "BISPMOSubmitted";
	public static final String statusInitial = "EMSubmitted"; //Initiated
	public static final String statusRMPMOSubmit = "OnboardingCompleted"; //"RMPMOSubmitted";
	public static final String statusRMRejected ="RMRejected";
	public static final String statusPSAIdRequested = "OnboardingInitiated"; //"PSAIdRequested";
	public static final String EM_Role_ID = "3";
	public static final String statusVMRequested = "VMInProccess";
}
