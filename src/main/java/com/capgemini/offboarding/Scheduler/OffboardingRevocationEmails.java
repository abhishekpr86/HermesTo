package com.capgemini.offboarding.Scheduler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.moodle.model.User;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.onboarding.mail.MailUtility;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.rest.MicrosoftFlowRest;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.MoodleService;
import com.capgemini.onboarding.service.PreOnbEmployeeService;
import com.capgemini.onboarding.service.UsersService;
import com.capgemini.onboarding.util.PsaMailUtility;



/*@EnableScheduling*/
public class OffboardingRevocationEmails {
	
	private Logger logger = Logger.getLogger(OffboardingRevocationEmails.class);
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
    @Autowired(required = true)
    private PsaMailUtility psaMailUtility;
    
    @Autowired(required = true)
	private MoodleService moodleService;
    
    @Autowired(required = true)
    private UsersService usersService;
    
    @Autowired(required = true)
    private MicrosoftFlowRest microsoftFlowRest;
	

	//@Scheduled(cron="0 05 15 * * ?")  //(cron = "${cron.expression}") http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
    public void OffboardingEmails() throws Exception{
        
		logger.info("OffboardingEmails() Method executed at  :: "+ new Date());
		Calendar cal = Calendar.getInstance();
		
		List<Employee> OffboardingList = new ArrayList<Employee>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = sdf.parse(sdf.format(new Date(cal.getTimeInMillis())));
		OffboardingList = employeeService.getOffboardingEmpList(dateWithoutTime);
		
		
		
		//for( Object employee : OffboardingList) {
			
		for(int employee = 0; employee < OffboardingList.size(); employee++) {
			Employee emp = (Employee) OffboardingList.get(employee);
			boolean isOnshore = false; boolean isOffshore = false;
			boolean hasPSAID = false;
			logger.info("Procress for "+emp.getCorpId());
			
			if(emp.getCountry().getCountryId() != 2) {
				isOnshore = true;
			}else {
				isOffshore = true;
			}
			//if(emp.getPsaId() != null) {
			if(!emp.getPsaId().isEmpty() && !emp.getPsaId().equalsIgnoreCase("NO PSA ID")) {
				hasPSAID = true;
			}
			
			if(isOnshore || hasPSAID) {
				logger.info("RM PMO offboarding mail - start");
				String RMPMOMailSubj = "[User Management Tool] Initiate Offboarding Process for user "+emp.getFirstName()+" "+emp.getLastName()+" ("+emp.getCorpId()+") on "+emp.getPrimaryprogram().getPrimaryProgramName();
				RmPMO rmPMO = preOnbEmpService.getRmPmoDetails();
				//MailSender.send(psaMailUtility.prepareSheduleMailId(rmPMO.getEmail()), MailUtility.RMPMOMail(rmPMO.getRmPmoName(),emp,isOnshore,hasPSAID) , RMPMOMailSubj);
				
				//BCC
				DLList dlBcc = preOnbEmpService.getDLListbyRole("bcc");
				MailSender.sendNew(psaMailUtility.prepareSheduleMailId(rmPMO.getEmail()),psaMailUtility.prepareSheduleMailId(dlBcc.getEmail()), MailUtility.RMPMOMail(rmPMO.getRmPmoName(),emp,isOnshore,hasPSAID) , RMPMOMailSubj);
				logger.info(RMPMOMailSubj+" sent to "+rmPMO.getEmail());
			}
			BisPMOMap bisPmo = employeeService.getBISFromPMO(emp.getBis().getBis_id());
            String bisPMOMailSubj = "[User Management Tool] Offboarding Process for user "+emp.getFirstName()+" "+emp.getLastName()+" ("+emp.getCorpId()+")";
			
            logger.info("Offboarding Mail to BIS PMO - Start");
			//MailSender.send(psaMailUtility.prepareSheduleMailId(bisPmo.getPmo_email()) ,MailUtility.BISPMOMail(emp,bisPmo), bisPMOMailSubj);
            //BCC
			DLList dlBcc1 = preOnbEmpService.getDLListbyRole("bcc");
            MailSender.sendNew(psaMailUtility.prepareSheduleMailId(bisPmo.getPmo_email()) ,psaMailUtility.prepareSheduleMailId(dlBcc1.getEmail()),MailUtility.BISPMOMail(emp,bisPmo), bisPMOMailSubj);
            
			logger.info(bisPMOMailSubj+" sent to "+bisPmo.getPmo_email());
			
			if(isOffshore) {
				
				
				switch(emp.getLocation().getStateId()){
					case 4:
					case 5:
					case 20:
					case 34:	
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
							logger.info("ODC access revocation");
							//String ICRESODCRevokeSubj="PSA Hermes ODC access revocation Request "+emp.getCorpId();
							String ICRESODCRevokeSubj="STELLANTIS HERMES ODC Access Revocation Request "+emp.getCorpId();
							List<DLList> recipient = preOnbEmpService.getMultipleDLListbyRole(emp.getLocation().getStateName()+"ICRES");
							//String[] ccList = {psaMailUtility.prepareSheduleMailId(bisPmo.getPmo_email())};
							String[] recipientList = new String[recipient.size()];
							for(int reci=0; reci < recipient.size(); reci++) {
								recipientList[reci] = psaMailUtility.prepareSheduleMailId(recipient.get(reci).getEmail());
							}
							//MailSender.send(recipientList, ccList, MailUtility.ICRESODCRevokeMail(emp), ICRESODCRevokeSubj);
							
							//BCC
							DLList dlBcc2 = preOnbEmpService.getDLListbyRole("bcc");
							String[] ccList = {psaMailUtility.prepareSheduleMailId(bisPmo.getPmo_email()),psaMailUtility.prepareSheduleMailId(dlBcc2.getEmail())};//new changes from BCC to CC
							logger.info("Changed BCC to CC");
							MailSender.send(recipientList, ccList, MailUtility.ICRESODCRevokeMail(emp), ICRESODCRevokeSubj);
							
							//MailSender.sendNew(recipientList,psaMailUtility.prepareSheduleMailId(dlBcc2.getEmail()),ccList, MailUtility.ICRESODCRevokeMail(emp), ICRESODCRevokeSubj);
							
							logger.info(ICRESODCRevokeSubj+" sent");
						}
						/*if(ODCAccessRequired) {
							logger.info("Seat revocation");
							String SPOCSubj="[User Management Tool] Seat revocation for corp id - "+emp.getCorpId();
							DLList SPOCrecipient = preOnbEmpService.getDLListbyRole(emp.getLocation().getStateName()+"SPOC");
							String[] SPOCrecipientList = {psaMailUtility.prepareMailId(SPOCrecipient.getEmail())};
							String[] SPOCccList = {psaMailUtility.prepareMailId(bisPmo.getPmo_email()),psaMailUtility.prepareMailId(emp.getEmail())};
							
							//MailSender.send(SPOCrecipientList, SPOCccList, MailUtility.SeatDesktopRevoke(emp, SPOCrecipient.getName()), SPOCSubj);
							
							//BCC
							DLList dlBcc3 = preOnbEmpService.getDLListbyRole("bcc");
							MailSender.sendNew(SPOCrecipientList,psaMailUtility.prepareMailId(dlBcc3.getEmail()), SPOCccList, MailUtility.SeatDesktopRevoke(emp, SPOCrecipient.getName()), SPOCSubj);
							logger.info(SPOCSubj+" sent");
						}*/
						break;
					default: logger.info("NO ODC revocation mail for "+emp.getCorpId());
				}
				
			
			}
			
			if(isOnshore) {
				
				String RMMailSubj = "[User Management Tool] Offboarding Process for corp id "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+" )";
				Spoc spocRec = this.employeeService.getSpocFromCountry(1); // Since mail should be triggered to Valerie for all onshore countries
				MailSender.send(psaMailUtility.prepareSheduleMailId(spocRec.getSpocEmail()), MailUtility.RMMail(emp,spocRec),RMMailSubj);
				logger.info("Onshore "+RMMailSubj+" "+spocRec.getSpocEmail());
				
				               
				
			}
			//  Changes done for Mail to Contract manager of external resource offboarded.
			 if(emp.getEmpType().equalsIgnoreCase("External")) {
					String ExternalMailSubj ="[User Management Tool] Subcontractor Offboarding for corp id "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+" )";
					DLList dlContractManager = preOnbEmpService.getDLListbyRole("Contract Manager");
					MailSender.send(psaMailUtility.prepareSheduleMailId(dlContractManager.getEmail()), MailUtility.ExternalMail(emp, dlContractManager), ExternalMailSubj);
					logger.info(ExternalMailSubj+" sent to "+dlContractManager.getEmail());
					
				}
			
		 
			if(emp.getPrimaryprogram().getConfluenceaccess() && (!emp.getCorpId().startsWith("x-") && !emp.getCorpId().startsWith("X-")) ) {
				//String JIRAIDConfluSubj = "[Onboarding Tool] Offboarding JIRA & Confluence ID - corp id "+emp.getCorpId();
				String JIRAIDConfluSubj = "[HERMES] Offboarding JIRA and Confluence ID - corp id "+emp.getCorpId()+", ("+emp.getFirstName()+" "+emp.getLastName()+")" ;
				DLList dl = preOnbEmpService.getDLListbyRole("JIRAID");
				
				//adding in CC
				String[] recipientList = {psaMailUtility.prepareSheduleMailId(dl.getEmail())};
				DLList dlHermesOnb = preOnbEmpService.getDLListbyRole("HermesOnb");
				String [] ccList = {psaMailUtility.prepareMailId(dlHermesOnb.getEmail())};
				
				//MailSender.send(psaMailUtility.prepareSheduleMailId(dl.getEmail()),MailUtility.JIRARevokeMail(emp,dl),JIRAIDConfluSubj);//already commented from the beginning
				//MailSender.send(recipientList, ccList,MailUtility.JIRARevokeMail(emp,dl),JIRAIDConfluSubj);
				
				//BCC
				DLList dlBcc4 = preOnbEmpService.getDLListbyRole("bcc");
				MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(dlBcc4.getEmail()) ,ccList,MailUtility.JIRARevokeMail(emp,dl),JIRAIDConfluSubj);
				logger.info(JIRAIDConfluSubj+" sent to "+dl.getEmail());
			}
			// VLAN revocation mail
			
			/* if(emp.getLocation().getIsVLANReq()) {
				String ITHelpDeskSubj = "Revoke VLAN "+emp.getLocation().getStateName()+ " Corp Id "+emp.getCorpId();
				String IndiaVLAN = "Revoke VLAN - "+emp.getLocation().getStateName();
				DLList dlItHelp = null;
				String[] ccList = {};
				String[] recipientList = new String[1];
				
				if(emp.getCountry().getCountryId() == 2) {
					
				}else {
					dlItHelp = preOnbEmpService.getDLListbyRole("IT Help");
					recipientList[0] = psaMailUtility.prepareMailId(dlItHelp.getEmail());
					//MailSender.send(recipientList, ccList, MailUtility.RevokeVLANMail(emp) , ITHelpDeskSubj);
					
					//BCC
					DLList dlBcc6 = preOnbEmpService.getDLListbyRole("bcc");
					MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(dlBcc6.getEmail()) ,ccList, MailUtility.RevokeVLANMail(emp) , ITHelpDeskSubj);
					logger.info("Revoke VLAN Mail sent corp id "+emp.getCorpId()+" sent to "+dlItHelp.getEmail());
				}
			} */
			
			
			if(emp.isBi()) {
				logger.info("Github Copilot License revocation mail - start");
				String emailSb = "[User Management Tool] GitHub Copilot license revocation for corp id - "+emp.getCorpId()+" - "+emp.getPrimaryprogram().getPrimaryProgramName();
				DLList dlRevoke = preOnbEmpService.getDLListbyRole("CopilotRevoke");
				String[] recipientList = {psaMailUtility.prepareSheduleMailId(dlRevoke.getEmail())};
				String [] ccList = {};
				//bcc
				DLList bccDl = preOnbEmpService.getDLListbyRole("bcc");
				MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(bccDl.getEmail()) ,ccList,MailUtility.copilotLicenseRevokMail(emp),emailSb);
				logger.info(emailSb+" mail sent");
				
			}
			
			
			//Moodle User deletion
			
			User moodleUser = this.moodleService.getUserByUsername(emp.getCorpId().toLowerCase());
			
			if(moodleUser != null) {
				this.moodleService.deleteUser(moodleUser);
				logger.info("User moodle deleted "+moodleUser.getUsername());
			}
			
			//disable Onboarding Tool user
			this.disableOnboardingUser(emp.getCorpId());
			
			//remove from Microsoft Stream Group - not in use
			//this.microsoftFlowRest.POSTRequest("https://prod-43.westeurope.logic.azure.com:443/workflows/7e564621a3dd4fa1997438cd0f010739/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=UmRaTBBAth9IVHrusweCY_kyxrtgyXBsQuuDovp5vQk", emp.getEmail());
			
			
			//new link to remove from Microsoft Stream Group - Valerie 2023 - in use
			//this.microsoftFlowRest.POSTRequest("https://prod-60.westeurope.logic.azure.com:443/workflows/683fb39365ae493f9632843792c6f7a7/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=UFxr5ZaFCCQjrtsE_A0M7L_YHLn33jTvOK2XfGLRHPg", emp.getEmail());
			
			//logger.info("User- "+emp.getCorpId()+" removed from Microsoft Stream Group");
			
		} 
		logger.info("OffboardingEmails() Method completed successfully at  :: "+ new Date());
    }
	

	//@Scheduled(cron="0 45 19 * * ?")  //(cron = "${cron.expression}") http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
    public void EMAlertEmails() throws Exception{
    	logger.info("Offboarding Date Reminder mail");
		Date todayDate = new Date();
		LocalDateTime localDateTimeToday = LocalDateTime.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
		
		TimeZone zone = TimeZone.getTimeZone("Europe/Paris");
		Calendar currentDay =  Calendar.getInstance(zone);
		currentDay.setTime(todayDate);
		
		
		switch(currentDay.get(Calendar.DAY_OF_WEEK)) { //assuming actual offboarding date is always a weekday.
			
			case Calendar.SATURDAY :	currentDay.add(Calendar.DAY_OF_MONTH, 8);
										break;
			
			case Calendar.SUNDAY : 		currentDay.add(Calendar.DAY_OF_MONTH, 9);
										break;
			
			default : 					currentDay.add(Calendar.DAY_OF_MONTH, 7);
										break;
		}
		
		Date actualOffboardingDate = currentDay.getTime();
		
		
		
		List<Employee> toBeOffboardingList = new ArrayList<Employee>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = sdf.parse(sdf.format(actualOffboardingDate));
		toBeOffboardingList = employeeService.getOffboardingEmpList(dateWithoutTime);
		
		Employee emp = null; Employee EMRecord = null; 
		for(Object employee : toBeOffboardingList) {
			
			emp = (Employee) employee;
			EMRecord = emp.getEM();
			String mngrEmail = emp.getManagerEmail();
			String EMMailSubj = "[User Management Tool] Offboarding will be initiated for resource "+emp.getFirstName()+" "+emp.getLastName()+" ("+emp.getCorpId()+")";
			DLList dl = preOnbEmpService.getDLListbyRole("RMPMO");
			String[] recipientList = new String[2];
			//Changes for add the RM PMO DL
			String[] ccList = {};
			recipientList[0] = psaMailUtility.prepareMailId(EMRecord.getEmail());
			recipientList[1] = psaMailUtility.prepareMailId(mngrEmail);
			// Changes 
			
			//MailSender.send(recipientList, ccList, MailUtility.MngrOffboardingMail(emp, dl,dateWithoutTime),EMMailSubj);
			
			//BCC
			logger.info(EMMailSubj+" mail sent to  EM and manager");
			DLList dlBcc5 = preOnbEmpService.getDLListbyRole("bcc");
			MailSender.sendNew(recipientList,psaMailUtility.prepareMailId(dlBcc5.getEmail()), ccList, MailUtility.MngrOffboardingMail(emp, dl,dateWithoutTime),EMMailSubj);
			
			logger.info("Offboarding Date Reminder mail for corp id "+emp.getCorpId()+" sent to "+EMRecord.getEmail()+", "+mngrEmail+" offboarding date "+emp.getActualEndDate());
		}
	
	}
    
    public void disableOnboardingUser(String corp_id) {
		boolean doesExist = this.usersService.checkUserNameExists(corp_id, null);
		if(doesExist) {
			this.usersService.disableUser(corp_id);
			logger.info("Disabled onboarding Tool user "+corp_id);
		}
	}
    
    
    public void activateUserMoodle() throws Exception{
    	logger.info("inside activateUserMoodle at "+ new Date());
    	User moodleUser = this.moodleService.getUserByUsername("cpagidal");//mention Corp id in lower case
		
		if(moodleUser != null) {
			this.moodleService.activateUser(moodleUser);
			logger.info("Moodle user activated "+moodleUser.getUsername());
		}
		else 
			logger.info("user not present");
    }
    
}
    
    
    


