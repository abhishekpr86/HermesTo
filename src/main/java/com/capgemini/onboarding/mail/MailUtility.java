package com.capgemini.onboarding.mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dto.EmailDTO;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.EmployeeRoles;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.util.PsaMailUtility;

public class MailUtility {
	
	private static Logger logger = Logger.getLogger(MailUtility.class);

	
	public static String createOffboardingEmailBody(List<Employee> details , String empDetails, String managerDetails, String actualEndDate, String updatedActualDate){
		StringBuffer bodyMsg = new StringBuffer();
		Employee empOffDetails = details.get(0);
		bodyMsg.append("<div>");
		bodyMsg.append("Hi " + managerDetails + ", <br/><br/>");
		
	//	bodyMsg.append("This is an automatic notification from the HERMES On-boarding tool to inform you that the off-boarding date for " + empDetails );
		bodyMsg.append("This is an automatic notification from the HERMES On-boarding tool to inform you that the off-boarding date for " + empOffDetails.getFirstName() + "" + empOffDetails.getLastName());
		bodyMsg.append(" was updated in the tool from " + actualEndDate + " to " + updatedActualDate + ".<br/><br/>");
		bodyMsg.append("Regards, <br/> On-boarding Team");
		
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	
	public static String offBoardingReportEmailBody(List<Employee> details ) {
		StringBuffer bodyMsg = new StringBuffer();
		/*Employee emp =  new Employee();
		
		Date actualEndDate = emp.getActualEndDate();
		Date updatedActualDate = emp.getActualEndDate();
		if((updatedActualDate!=null && actualEndDate == null) || (updatedActualDate!=null && updatedActualDate.compareTo(actualEndDate) != 0)) {
			
				String actualEndDateStr = "&lt;blank value&gt;";
				String updatedActualDateStr = "&lt;blank value&gt;";
				if(actualEndDate!=null) {
				actualEndDateStr = OnboardingConstants.DATE_FORMAT.format(actualEndDate);
				}
				if(updatedActualDate!=null) {
				updatedActualDateStr = OnboardingConstants.DATE_FORMAT.format(updatedActualDate);
				}*/
		
		
		
		Employee empOffDetails = details.get(0);
		bodyMsg.append("<div>");
		bodyMsg.append("<br/>");
		bodyMsg.append("This is an automatic notification from the HERMES On-boarding tool to inform you that the off-boarding date for " + details.get(0).getFirstName() );
		bodyMsg.append("<br/><br/>");
		
		bodyMsg.append("<html><body>"
                + "<table style='border:1px solid black'>");
		
		
		// creating the label 
		bodyMsg.append("<table border=1>");
		bodyMsg.append("<tr colspan=1>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Emp ID");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Corp ID");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("PSA ID");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Emp Type");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Emp Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Country");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Location");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Email");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Capgemini Enitity");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Vendor");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Grade");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Global Grade");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Manager");
		bodyMsg.append("</th>");
		

		bodyMsg.append("<th>");
		bodyMsg.append("On-boarding Date");
		bodyMsg.append("</th>");
		

		bodyMsg.append("<th>");
		bodyMsg.append("Planned Off-boarding Date");
		bodyMsg.append("</th>");
		

		bodyMsg.append("<th>");
		bodyMsg.append("Actual Off-boarding Date");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th>");
		bodyMsg.append("Training Status");
		bodyMsg.append("</th>");

		bodyMsg.append("<th>");
		bodyMsg.append("Bundle EM");
		bodyMsg.append("</th>");
		

		bodyMsg.append("<th>");
		bodyMsg.append("EM");
		bodyMsg.append("</th>");
		
		bodyMsg.append("</tr>");
	
		
		
		for(int i=0; i<details.size(); i++){
			
			bodyMsg.append("<tr>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getEmpId());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getCorpId());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getPsaId());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getEmpType());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getFirstName() + " " + details.get(i).getLastName());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getCountry().getCountryName());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getLocation().getStateName());
			bodyMsg.append("</td>");
			
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getEmail());
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getCgEntity());
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			if(details.get(i).getVendor()!=null){
				bodyMsg.append(details.get(i).getVendor().getVendorName());
			}
			else{
				bodyMsg.append(" ");
			}
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getGrade().getName());
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getGlobalGrade().getName());
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getManager().getFirstName() + " " + details.get(i).getManager().getLastName());
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			
			String onboarding_date = details.get(i).getOnboardingDate();
			if(onboarding_date!=null){
				onboarding_date = OnboardingConstants.DATE_FORMAT.format(details.get(i).getOnboardingDate());
				bodyMsg.append(onboarding_date);
			}
			else{
				bodyMsg.append(onboarding_date);
			}
			bodyMsg.append("</td>");
			

			bodyMsg.append("<td>");
			String offboarding_date = details.get(i).getOffboardingDate();
			if(details.get(i).getOffboardingDate()!=null){
				offboarding_date = OnboardingConstants.DATE_FORMAT.format(details.get(i).getOffboardingDate());
				bodyMsg.append(details.get(i).getOffboardingDate());
			}
			else{
				bodyMsg.append(" ");
			}
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			Employee e = new Employee();
			String actualoffboarding_date = e.getActualOffboardingDate();
            if(details.get(i).getActualOffboardingDate()!=null){
            	actualoffboarding_date = OnboardingConstants.DATE_FORMAT.format(details.get(i).getActualOffboardingDate());
            	bodyMsg.append(actualoffboarding_date);
            }
            else{
            	bodyMsg.append(" ");
            }
			
			bodyMsg.append("</td>");

			bodyMsg.append("<td>");
			if(details.get(i).getIsTrainingCompleted()!=null){
				bodyMsg.append(details.get(i).getIsTrainingCompleted());
			}
			else{
				bodyMsg.append("NA");
			}
			
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getBundleEM());
			bodyMsg.append("</td>");
			
			bodyMsg.append("<td>");
			bodyMsg.append(details.get(i).getEM().getFirstName() + " " + details.get(i).getEM().getLastName());
			bodyMsg.append("</td>");
	
			bodyMsg.append("</tr>");
		}
		
		bodyMsg.append("</table></body></html>");
		bodyMsg.append("<br/><br/>");
		bodyMsg.append("Regards, <br/> On-boarding Team");
		
		bodyMsg.append("</div>");
		return bodyMsg.toString();
			
	}
	
	public static String createEmailBody(String firstName, String url, String spocName, String spocMail) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + firstName + ", <br/><br/>");
		
		/*bodyMsg.append("<img src=\"cid:hermes.png\">");*/
		/*bodyMsg.append("<img src=SpringMVCHibernate(2)/src/main/webapp/WEB-INF/images/hermes.png alt=hermes.png height=118 width=241><br/><br/>");*/
		bodyMsg.append("Welcome to the HERMES program!<br/><br/>");
    	bodyMsg.append("You should have received an email with your access identification. If you haven’t, please reach reach out to the following details to get access - <br/><br/>  "
    			
    			+ "<b>ASSP</b> - Ankita Mandloi at <a href=mailto:ankita.mandloi@capgemini.com?>ankita.mandloi@capgemini.com</a><br/> "
    			+ "<b>INDUS</b> - Viraj Samant at <a href=mailto:viraj.samant@capgemini.com?>viraj.samant@capgemini.com</a><br/>   "
    			+ "<b>BI & Big Data, DESM</b> - Rajas Sathe at <a href=mailto:rajas.sathe@capgemini.com?>rajas.sathe@capgemini.com</a><br/>"
    			+ "<b>RDMS Manuf, RDMS Eng</b> - Heena Dhameja at <a href=mailto:heena.dhameja@capgemini.com?>heena.dhameja@capgemini.com</a><br/>"
    			+ "<b>SOIS, VTIS</b> - Muthu Konar at <a href=mailto:easikimuthu.konar@capgemini.com?>easikimuthu.konar@capgemini.com</a><br/> "
    			+ "<b>VTIS OPV</b> - Safwan Mohammed at <a href=mailto:mohammed.safwan@capgemini.com?>mohammed.safwan@capgemini.com</a><br/><br/> ");
    	bodyMsg.append("Here are some useful links to get started. These include an introduction to the PSA client and the HERMES program, on-boarding & setup procedures to follow as well as processes & tools used. Please read these carefully and contact your local manager if you have any queries.<br/><br/>");
    	bodyMsg.append("<ol type=1>");
    	bodyMsg.append("<li><b>HERMES Academy:</b></li><br/>");
    	bodyMsg.append("To begin, please click <a href=http://frparhermesto/HERMES_Academy/index.html>HERMES Academy</a> which is our on-boarding & learning portal. Within this:");
    	bodyMsg.append("<ul>");
    	bodyMsg.append("<li>Please start with the <a href=http://frparhermesto/HERMES_Academy/overview.html>PSA Overview</a> tab and read through the client overview as well as the business area that you’re going to work on.</li>");
    	bodyMsg.append("<li>Post that, go to the <a href=http://frparhermesto/HERMES_Academy/learning.html> Learning & Training</a> Hub which is a repository of learning & training courses for everyone in the team, organized into 4 tabs. Please start with the On-boarding tab. Within this:");
    	bodyMsg.append("<ul>");
    	bodyMsg.append("<li><b>Pre-Induction</b> has a set of basic security & values related trainings that are mandatory for everyone. Please plan to cover these in the first two weeks.</li>");
    	bodyMsg.append("<li><b>Onboarding</b> has the history of HERMES, the Capgemini org structure and some general rules everyone needs to follow within the PSA account. Please plan to cover these in the first 2-3 days.</li>");
    	bodyMsg.append("<li><b>Processes & Methods</b> has the detailed processes and best practices to be followed. Based on your area of work, you can discuss with your manager and plan to cover these topics.</li>");
    	bodyMsg.append("<li><b>Tools</b> has an introduction to the set of tools you would need to use on the PSA side as well as Capgemini side. Again, based on your area of work, please cover the appropriate ones.</li>");
    	bodyMsg.append("</ul></li>");
    	bodyMsg.append("<li>On the remaining sections and tabs, you can discuss with your manager and accordingly decide to cover the applicable ones.</li></ul></ol>");
    
    	bodyMsg.append("<ol start=2><li><b>India TRoom:</b></li><br/>");
    	bodyMsg.append("The <a href=troom.capgemini.com>India TRoom</a> is a central repository of documents & data which is to be used by the India team. Within this, please go through the");
    	bodyMsg.append("<a href=https://troom.capgemini.com/sites/hermestransition/Shared%20Documents/Forms/AllItems.aspx?RootFolder=%2Fsites%2Fhermestransition%2FShared%20Documents%2FTech%20and%20Infra%2FAccess%20info%20consolidated&FolderCTID=0x012000B1B7337FA9DB304D861FA40AF6ACD8F8&View=%7b6AE6F82D-EDB1-40F3-A0AD-C9B3F8C6BB54>"
    			+ "    https://troom.capgemini.com/sites/hermestransition/Shared%20Documents/Forms/AllItems.aspx?RootFolder=%2Fsites%2Fhermestransition%2FShared%20Documents%2FTech%20and%20Infra%2FAccess%20info%20consolidated&FolderCTID=0x012000B1B7337FA9DB304D861FA40AF6ACD8F8&View=%7b6AE6F82D-EDB1-40F3-A0AD-C9B3F8C6BB54</a> to start your PSA on-boarding & access setup.<br/><br/>");
    	bodyMsg.append("Please use the below user name and password to access the same.<br/><br/>");
    	bodyMsg.append("User id = [your CORP ID in small-case]<br/>");
    	bodyMsg.append("Password = [your CORP ID in small-case]<br/><br/>");
    	bodyMsg.append("As first steps you will need to setup your desktop/laptop to be able to connect to client network. Please refer to this <a href=https://troom.capgemini.com> TROOM</a> link<br/>");
    	bodyMsg.append("<ol type=1>");
    	bodyMsg.append("<li>Ask your manager to raise an EXT ID request for the creation of PSA ID.Doc Name–EXT ID TEMPLATE</li>");
    	bodyMsg.append("<li>Set up to access PSA virtual machine.To set up VDI i.e.Doc name- HERMES-Connection to PSAlib via Vlan Port - India&nbsp;</li>");
    	bodyMsg.append("<li>If you face any issue related to VDI set up , network, software please raise an ITSM request Doc Name– HERMES- Raising ITSM ticket</li>");
    	bodyMsg.append("<li>If you want to install software in your VDI –Doc Name–HERMES Software installation on VDI</li>");
    	bodyMsg.append("<li>If you are required to be part of On-Call support / Connect to PSA outside Capgemini network Doc Name – On call connection process</li>");
    	bodyMsg.append("<li>To set up your system to be able to connect to client network outside Capgemini Network –Doc Name- HERMES – Connection to PSAlib for On-call Support</li>");
    	bodyMsg.append("</ol>");
    	bodyMsg.append("</ol>");
    	
    	bodyMsg.append("<ol start=3><li><b>Confluence:</b></li><br/>");
    	bodyMsg.append("In addition to the academy, we have an information portal in the form of <a href=http://frparconfl:8090/login.action?os_destination=%2Fpages%2Fviewpage.action%3FspaceKey%3DHERK%26title%3DEnter%2BHERMES&permissionViolation=true>Confluence</a>. Use below credentials to login for the first time to confluence. Some of the links in the <a href=http://frparhermesto/HERMES_Academy/index.html>HERMES Academy</a> also take you to Confluence, which would require you to login similarly:<br/><br/>");
    	bodyMsg.append("Please use the below user name and password to access the same.<br/><br/>");
    	bodyMsg.append("User id = [your CORP ID in small-case]<br/>");
        bodyMsg.append("Password = [your CORP ID in small-case]<br/><br/>");
    	bodyMsg.append("Here are some of the details you will find on Confluence:");
    	
    	bodyMsg.append("<ol type=1>");
    	bodyMsg.append("<li> <a href=http://frparconfl:8090/login.action?os_destination=%2Fpages%2Fviewpage.action%3FspaceKey%3DHERK%26title%3DProblem%2BSolver&permissionViolation=true>Troubleshooting steps on common VDI related issues</a></li>");
    	bodyMsg.append("<li><a href=http://frparconfl:8090/login.action?os_destination=%2Fpages%2Fviewpage.action%3FspaceKey%3DHERK%26title%3DFAQ%2527s&permissionViolation=true> FAQs</a></li>");
    	bodyMsg.append("<li>Trainings on <a href=http://frparconfl:8090/login.action?os_destination=%2Fpages%2Fviewpage.action%3FpageId%3D1378275&permissionViolation=true>Processes</a> and <a href=http://frparconfl:8090/login.action?os_destination=%2Fpages%2Fviewpage.action%3FspaceKey%3DHERK%26title%3DTools&permissionViolation=true> Tools</a></li>");
    	bodyMsg.append("<li>Other articles communicating about the HERMES progam and its teams</li></ol>");
    	bodyMsg.append("You can also use the search feature in the menu to search for specific areas.</ol><br/>");
    	
    	bodyMsg.append("<ol start=4><li><b>TeamForge:</b></li><br/>");
    	bodyMsg.append("<a href=coconet2.capgemini.com>TeamForge</a>  is a central repository which is primarily used to store video recordings of all the knowledge sessions regarding applications & tools. Please use below username and password to access the same. Some of the links from the Academy will also take you to <a href=coconet2.capgemini.com>TeamForge</a>.<br/><br/>");
    	bodyMsg.append("User id = [your CORP ID in small-case]<br/>");
    	bodyMsg.append("Password = [your CORP ID in small-case]<br/><br/>");
    	bodyMsg.append("If that’s not working, please reach out to the following details to get access - <br/><br/>  "
    			
    			+ "<b>ASSP</b> - Ankita Mandloi at <a href=mailto:ankita.mandloi@capgemini.com?>ankita.mandloi@capgemini.com</a><br/> "
    			+ "<b>INDUS</b> - Viraj Samant at <a href=mailto:viraj.samant@capgemini.com?>viraj.samant@capgemini.com</a><br/>   "
    			+ "<b>BI & Big Data, DESM</b> - Rajas Sathe at <a href=mailto:rajas.sathe@capgemini.com?>rajas.sathe@capgemini.com</a><br/>"
    			+ "<b>RDMS Manuf, RDMS Eng</b> - Heena Dhameja at <a href=mailto:heena.dhameja@capgemini.com?>heena.dhameja@capgemini.com</a><br/>"
    			+ "<b>SOIS, VTIS</b> - Muthu Konar at <a href=mailto:easikimuthu.konar@capgemini.com?>easikimuthu.konar@capgemini.com</a> ");
    	bodyMsg.append("</ol><br/><br/>");
    	
    	bodyMsg.append("<ol start=5><li><b>Team Forge and translation:</b></li><br/>");
    	bodyMsg.append("If you need to translate the documents then please find below the required information<br/><br/>");
    	bodyMsg.append("<ol type=1>");
    	bodyMsg.append("<li>The procedure for checking the word count of a particular document (Word, Excel, PowerPoint) can be found <a href=redir.aspx?SURL=xozK8kw7dqrKJp1N-_BSLY-z7nQNov6c1kdF2uiqU4F5JnJUW0PUCGgAdAB0AHAAcwA6AC8ALwBjAG8AYwBvAG4AZQB0ADIALgBjAGEAcABnAGUAbQBpAG4AaQAuAGMAbwBtAC8AcwBmAC8AZwBvAC8AZABvAGMAMwA5ADkANAA4ADQAOQA_AG4AYQB2AD0AMQA.&amp;URL=https%3a%2f%2fcoconet2.capgemini.com%2fsf%2fgo%2fdoc3994849%3fnav%3d1 target=_blank><b>here</b></a></li>");
    	bodyMsg.append("<li>In order to raise a document translation request, the consultant has to create an artifact on Teamforge <a href=redir.aspx?SURL=KB2aotynLysHVt6Sd_v8NSST8Po1BpSKiUlXEo7baH95JnJUW0PUCGgAdAB0AHAAcwA6AC8ALwBjAG8AYwBvAG4AZQB0ADIALgBjAGEAcABnAGUAbQBpAG4AaQAuAGMAbwBtAC8AcwBmAC8AZwBvAC8AZABvAGMAMwA5ADgAMAA5ADUAOAA.&amp;URL=https%3a%2f%2fcoconet2.capgemini.com%2fsf%2fgo%2fdoc3980958 target=_blank><b>Click here</b></a> to go to the document containing brief explanation on artifact creation procedure.</li>");
    	bodyMsg.append("<li>Exhaustive information on Teamforge structure and PSA folders along with the detailed procedure of artifact creation and data required for creating an artifact can be found <a href=redir.aspx?SURL=XFgi0DUDugE2ShKFV2kFhGkEW4kcFtrXYEUl6IT9Kbh5JnJUW0PUCGgAdAB0AHAAcwA6AC8ALwBjAG8AYwBvAG4AZQB0ADIALgBjAGEAcABnAGUAbQBpAG4AaQAuAGMAbwBtAC8AcwBmAC8AZwBvAC8AZABvAGMAMwA5ADgAMAA5ADUANgA_AG4AYQB2AD0AMQA.&amp;URL=https%3a%2f%2fcoconet2.capgemini.com%2fsf%2fgo%2fdoc3980956%3fnav%3d1 target=_blank><b>here</b></a></li></ol></ol><br/>");
    	
    	bodyMsg.append("We’re pleased to have you on board and wish you a great journey on HERMES!<br/><br/>");
    	bodyMsg.append("Thanks & regards,<br/>");
    	bodyMsg.append("HERMES PMO<br/><br/>");
    	/*bodyMsg.append("Note: Please do not reply to this email. In case you have queries, you can reach out to the " + spocName + "  " + spocMail + " .");*/
		bodyMsg.append("</div>");
    	bodyMsg.append("</font></span></font>");
    	bodyMsg.append("</div>");
  
		return bodyMsg.toString();

	
	}
	
	public static String createRmPmoEMail(String firstName,String corp_id, String bisName, String sender, String url, String comment, Employee archivedEmp, Employee emp, String indus, String roleTech) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + firstName + ", <br/><br/>");
		bodyMsg.append("Please initiate the Stellantis ID generation process for - "+ corp_id + bisName +" as requested by "+ sender +".<br/><br/>");
		bodyMsg.append(url+"<br/><br/>");
		bodyMsg.append("Following are the details:<br/>");
		bodyMsg.append("BIS - "+emp.getBis().getBis_Name()+"<br/>");
		bodyMsg.append("First Name - "+emp.getFirstName() +"<br/>");
		bodyMsg.append("Last Name - "+emp.getLastName() +"<br/>");
		bodyMsg.append("INDUS GOAL - "+indus +"<br/>");
		bodyMsg.append("Role/Technology - "+roleTech +"<br/>");
		
		if(!comment.isEmpty()) {
			bodyMsg.append("COMMENT - "+comment+" <br/><br/>");
		}
		if(archivedEmp != null) {
			bodyMsg.append("OLD PSA ID & offboarding date - "+archivedEmp.getPsaId()+" & "+ sdf.format(archivedEmp.getActualEndDate())+" (dd/MM/yyyy)" +"<br/><br/>");
		}
		bodyMsg.append("Thanks & regards,<br/><br/>"); 
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		//bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
		bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information." + "<br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}
	
	public static String createResMgrRejectEMail(String firstName,String corp_id, String sender, String url, String comment) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + firstName + ", <br/><br/>"); //EM / Bundle EM - Pre Onb requestor
		bodyMsg.append("Onboarding Request Rejected for resource: "+ corp_id +" by "+ sender +".<br/><br/>");
		bodyMsg.append(url+"<br/>");
		bodyMsg.append("Go to View/Edit Onboarding Requests to re-submit <br/><br/>");
		if(!comment.isEmpty()) {
			bodyMsg.append("COMMENT - "+comment+" <br/><br/>");
		}
		bodyMsg.append("Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		bodyMsg.append("Please contact "+sender+" for additional information." + "<br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}
	
	public static String createResourceMgrEMail(String firstName, PreOnbEmployee emp,String corp_id, String bisName, String sender, String url, String comment) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + firstName + ", <br/><br/>");
		bodyMsg.append("Please validate the Onboarding request of resource with CORP ID: "+ corp_id + bisName +" as requested by "+ sender +".<br/><br/>");
		bodyMsg.append(url+"<br/><br/>");
		if(!comment.isEmpty()) {
			bodyMsg.append("COMMENT - "+comment+" <br/><br/>");
		} 
		bodyMsg.append("Thanks & regards,<br/><br/>");
    	/*bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");*/
		bodyMsg.append("This mail is triggered automatically.<br/>");
		bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information. <br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}
	
	public static String createHRAReqMail(PreOnbEmployee preEmp) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi "+" <br/><br/>");
		bodyMsg.append("May you add this user in GP FR HERMES PSA Remote Access"+".<br/><br/>"); // DL GP FR HERMES PSA Remote Access
		bodyMsg.append("<li>Firstname : "+preEmp.getFirstName()+"<br/><br/>");
		bodyMsg.append("<li>Lastname : "+preEmp.getLastName()+"<br/><br/>");
		bodyMsg.append("<li>Country : "+preEmp.getCountry().getCountryName()+"<br/><br/>");
		bodyMsg.append("<li>Corp Id : "+preEmp.getCorpId()+"<br/><br/>");
		bodyMsg.append("<li>BIS : "+preEmp.getBis().getBis_Name()+"<br/><br/>");
		bodyMsg.append("Greetings,<br/>");
		bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}
	
	
	//Engg - start
	public static String createManagerEmail(PreOnbEmployee emp, String requestor, String mngr, String emName,String  bem, String onboardingDate, String enddate, String location, String bis) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello, <br/><br/>"); 
		bodyMsg.append("Please be informed that the onboarding process has started for "+ emp.getFirstName()+" "+emp.getLastName()+" on "+bis +".<br/><br/>");
		bodyMsg.append("Requestor - "+requestor+"<br/><br/>");
		bodyMsg.append("Manager - "+mngr+"<br/><br/>");
		bodyMsg.append("EM - "+emName+"<br/><br/>");
		bodyMsg.append("BEM - "+bem+"<br/><br/>");
		bodyMsg.append("Onboarding Date - "+onboardingDate+"<br/><br/>");
		bodyMsg.append("Planned Offboarding Date - "+enddate+"<br/><br/>");
		bodyMsg.append("location - "+location+" in country - "+emp.getCountry().getCountryName()+ "<br/><br/>");
		
		bodyMsg.append("<br/><br/>Thanks & regards,<br/><br/>");
    	/*bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");*/
		bodyMsg.append("This mail is triggered automatically.<br/>");
		bodyMsg.append("Please contact "+requestor+" for additional information. <br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}
	//Engg - End
	
	
	public static String createBISPMOEMail(String bisName,String corp_id, String sender, String url, String comment) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + bisName + ", <br/><br/>"); //BIS PMO name
		bodyMsg.append("Please on-Board resource with CORP ID: "+ corp_id +" as approved by "+ sender +".<br/><br/>");
		bodyMsg.append(url+"<br/><br/>");
		if(!comment.isEmpty()) {
			bodyMsg.append("COMMENT - "+comment+" <br/><br/>");
		} 
		bodyMsg.append("Thanks & regards,<br/><br/>");
    	/*bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");*/
		bodyMsg.append("This mail is triggered automatically.<br/>");
		bodyMsg.append("Please contact approver "+sender+" for additional information. <br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}


	public static String createFirstWelcomeEmailCommon(String firstName, String docPath, String spocName, String spocEmail, boolean isIndia, ArrayList<String> imgArr) throws MessagingException {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		
		
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		
		bodyMsg.append("<table style=\"border:1px #857d7d solid\" align=\"center\">");
		bodyMsg.append("<tbody>");
	    
		bodyMsg.append("<tr><td style=width:0.50;><img src=\"cid:image1\" width=\"50%\" height=\"25%\" /></td></tr>");
		
		bodyMsg.append("<tr><td style=width:0.50;>  </td></tr>");
		
		bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\">Hi " + firstName + ", </td></tr>");
		
		bodyMsg.append("<tr><td style=width:0.50;>  </td></tr>");
		
		bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\">Welcome to HERMES Program !</td></tr>");
		bodyMsg.append("<tr><td style=width:0.50;></td>  </tr>");
		bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\">Click <a href=https://frparvm39353055.corp.capgemini.com/mymoodle/my/><b>here</b></a> to learn more about automotive sector, our client STELLANTIS ( Merge between PSA and FCA ) and HERMES Program.</td></tr>");
		bodyMsg.append("<tr><td style=width:0.50;>  </td></tr>");
		
		bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\">Your onboarding journey will last 4 hours. Following this journey is mandatory to make your integration into the HERMES Program successful. </td></tr>");
		
		bodyMsg.append("<tr><td style=width:0.50;>  </td></tr>");
		
		bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\">Please note that you need a Capgemini VPN connection if you are outside Capgemini's network.</td></tr>");
		
		bodyMsg.append("<tr><td style=width:0.50;>  </td></tr>");
		
		bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\">We're pleased to have you onboard and wish you a great journey on HERMES!</td></tr>");
		
		bodyMsg.append("<tr><td style=width:0.50;>  </td></tr>");
		
		
    	bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\"><b>Kind Regards,</b></td></tr>");
    	bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\"><b>HERMES Knowledge Management Team </b></td></tr>");
    	bodyMsg.append("<tr><td style=\"width:0.50; font-family: Calibri, sans-serif; font-size: 14px;\"><a href=mailto:hermeskmspoc.fr@capgemini.com>hermeskmspoc.fr@capgemini.com</a> </td></tr>");
    	
    	bodyMsg.append("<tr><td style=width:0.50;></td>  </tr>");
    	
    	bodyMsg.append("<tr><td style=width:0.50;><img src=\"cid:image2\" width=\"50%\" height=\"25%\" /></td></tr>");
    	bodyMsg.append("</tbody></table>");
    	bodyMsg.append("</font></span></font>");
    	bodyMsg.append("</div>");
		return bodyMsg.toString();	
		
	}
	
	public static EmailDTO dynamicWelcomeEmail(Employee emp) {
		
		EmailDTO emaildto = new EmailDTO();
		ArrayList<String> toList = new ArrayList<String>();
		ArrayList<String> ccLists =new ArrayList<String>();
		HashMap<String,String> paramValue = new HashMap<>(); 
		toList.add(emp.getEmail());
		
		paramValue.put("firstName", emp.getFirstName());
		paramValue.put("MAILTEMPID",emp.getPrimaryprogram().getPrimaryProgramName());
		emaildto.setToList(toList);
		emaildto.setCcList(ccLists);
		emaildto.setParamValue(paramValue);
		logger.info("emaildto "+emaildto);
		return emaildto;
		
	}
	
	
	public static String createEmailBodyForAll(String url, String All, String firstName, String lastName, String bisName, String onboardingDate) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div> <font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi All, <br/><br/></div>Please welcome ");
		bodyMsg.append(firstName);
		bodyMsg.append(" ");
		bodyMsg.append(lastName + "&nbspto HERMES.<br/><br/>");
		bodyMsg.append(firstName + "&nbsphas joined the " + bisName);
		bodyMsg.append(" team starting " + onboardingDate + ".<br/>");
		bodyMsg.append("<br/>Wishing ");
		bodyMsg.append(firstName);
		bodyMsg.append(" all the best for the upcoming journey in HERMES.<br/><br/>");
		bodyMsg.append("Thanks & regards,<br/>");
    	bodyMsg.append("<b>HERMES PMO Team <a href:mailto:pmohermes.in@capgemini.com>(pmohermes.in@capgemini.com)</a> </b>");
		bodyMsg.append("</font></span></font>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	
public static String createJiraIDMail(String creatorName, String empName, String empId, String corpId, PreOnbEmployee preEmp) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + creatorName + ", <br/><br/>");
		//bodyMsg.append("Please create the JIRA and Confluence ID for "+empName + " having Employee Id - "+empId+".<br/><br/>");
		bodyMsg.append("Please activate the JIRA and Confluence user licence and add user "+empName + " (CORP ID: "+corpId+" - having Employee Id - "+empId+") to below groups:<br/>");
		bodyMsg.append("<ul><li>HermesPSAConfluenceRead</li>");
		bodyMsg.append("<li>HermesPSAJiraUsers</li></ul>");
		bodyMsg.append("<br/>Thanks & Regards,<br/><br/>");
    	//bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
		/*bodyMsg.append("HERMES PMO Team" + "<br/><br/>");*/
		bodyMsg.append("This mail is triggered automatically.<br/>");
		//bodyMsg.append("Please contact <a href=mailto:stellantishermesonboarding.fr@capgemini.com>FR, Stellantis HERMES Onboarding</a> for additional information.<br/><br/>");
		bodyMsg.append("Please contact requestor "+preEmp.getRequestor().getFirstName()+" "+preEmp.getRequestor().getLastName()+" for additional information.<br/><br/>");
    	bodyMsg.append("</div>");
    	return bodyMsg.toString();
	}


public static String createJiraIDMailNew(String creatorName, String empName, String empId, String corpId, Employee preEmp) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi " + creatorName + ", <br/><br/>");
	//bodyMsg.append("Please create the JIRA and Confluence ID for "+empName + " having Employee Id - "+empId+".<br/><br/>");
	/*bodyMsg.append("Please activate the JIRA and Confluence user licence and add user "+empName + " (CORP ID: "+corpId+" - having Employee Id - "+empId+") to below groups:<br/>");*/
	bodyMsg.append("Please activate the JIRA and Confluence user licence and add user "+empName + " (CORP ID: "+corpId+" - having GGID - "+preEmp.getGgId()+") to below groups:<br/>");
	bodyMsg.append("<ul><li>HermesPSAConfluenceRead</li>");
	bodyMsg.append("<li>HermesPSAJiraUsers</li></ul>");
	bodyMsg.append("<br/>Thanks & Regards,<br/><br/>");
	//bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
	/*bodyMsg.append("HERMES PMO Team" + "<br/><br/>");*/
	bodyMsg.append("This mail is triggered automatically.<br/>");
	//bodyMsg.append("Please contact <a href=mailto:stellantishermesonboarding.fr@capgemini.com>FR, Stellantis HERMES Onboarding</a> for additional information.<br/><br/>");
	bodyMsg.append("Please contact requestor "+preEmp.getRequestor().getFirstName()+" "+preEmp.getRequestor().getLastName()+" for additional information.<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
}
	

public static String createJiraIDMailUpdate(String creatorName, String empName, String empId, String corpId, Employee preEmp) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi " + creatorName + ", <br/><br/>");
	//bodyMsg.append("Please create the JIRA and Confluence ID for "+empName + " having Employee Id - "+empId+".<br/><br/>");
	/*bodyMsg.append("Please activate the JIRA and Confluence user licence and add user "+empName + " (CORP ID: "+corpId+" - having Employee Id - "+empId+") to below groups:<br/>");*/
	bodyMsg.append("Please activate the JIRA and Confluence user licence and add user "+empName + " (CORP ID: "+corpId+" - having GGID - "+preEmp.getGgId()+") to below groups:<br/>");
	bodyMsg.append("<ul><li>HermesPSAConfluenceRead</li>");
	bodyMsg.append("<li>HermesPSAJiraUsers</li></ul>");
	bodyMsg.append("<br/>Thanks & Regards,<br/><br/>");
	//bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
	/*bodyMsg.append("HERMES PMO Team" + "<br/><br/>");*/
	bodyMsg.append("This mail is triggered automatically.<br/>");
	//bodyMsg.append("Please contact <a href=mailto:stellantishermesonboarding.fr@capgemini.com>FR, Stellantis HERMES Onboarding</a> for additional information.<br/><br/>");
	bodyMsg.append("Please contact requestor "+preEmp.getRequestor().getFirstName()+" "+preEmp.getRequestor().getLastName()+" for additional information.<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
}


public static String createRequestorMail(Employee emp, boolean isPSAID, EmployeeRoles role, String bill) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	//bodyMsg.append("Dear All" + ", <br/><br/>");
	bodyMsg.append("Dear "+emp.getManager().getFirstName()+" "+emp.getManager().getLastName()+", <br/><br/>");
	bodyMsg.append(emp.getFirstName() +" "+ emp.getLastName() +" has been onboarded on "+emp.getPrimaryprogram().getPrimaryProgramName()+" on BIS "+emp.getBis().getBis_Name()+" as "+role.getRef_name() +". <br/>");
	bodyMsg.append("Billing Start date is "+bill+" <br/>");
	
	if(isPSAID) {
		bodyMsg.append("Stellantis ID is generated and the details are shared with the user.");
	}
	bodyMsg.append("<br/><br/>Thanks & Regards,<br/><br/>");
	bodyMsg.append("This mail is triggered automatically." + "<br/>");
	bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information." + "<br/><br/>");
	bodyMsg.append("</font></span></font>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
	
}

public static String createBuddyMail(Employee emp) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hello "+emp.getFirstName()+" "+emp.getLastName()+", <br/><br/>");
	bodyMsg.append("Welcome to Stellantis account.");
	bodyMsg.append("To ease your on-boarding in "+emp.getPrimaryprogram().getPrimaryProgramName()+", we are assigning you an on-boarding buddy.");
	bodyMsg.append("The buddy will help you with any questions that you may have as a new joiner.");
	bodyMsg.append("The details of your on-boarding buddy are:<br/><br/>");
	bodyMsg.append("Buddy Name: "+emp.getBuddy().getFirstName()+" "+emp.getBuddy().getLastName()+"<br/>");
	bodyMsg.append("Buddy email: "+emp.getOnboardingEmail());
	bodyMsg.append("<br/><br/>Thanks and Regards,<br/><br/>");
	bodyMsg.append("This mail is triggered automatically." + "<br/>");
	bodyMsg.append("If you have  any question, you can contact your Buddy.<br/><br/>");
	bodyMsg.append("</font></span></font>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
	
}

public static String createWelcomeMail(Employee emp) {
	

	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi " + emp.getFirstName() + ", <br/><br/>");
	/*bodyMsg.append("Welcome to the HERMES program!<br/><br/>");*/
	
	bodyMsg.append("To work on Stellantis environment, you have a Virtual Machine (VM), named 'STLALib', and Stellantis login. <br/><br/>");
	
	/*if(emp.getPsaLibTypeID() != 0) { //not NO VM
		bodyMsg.append("<b>To work on Stellantis environment, you have a Virtual Machine (VM), named 'PSALib', and credentials on Stellantis infrastructure. </b><br/>");
		bodyMsg.append("First access is a bit complex and you must successfully connect your PSALib in order to take delivery commitment<br/><br/>");
	}*/
	
	bodyMsg.append("<b>Your credentials:</b><br/><br/>");
	
	bodyMsg.append("<html><body>"
            + "<table style='border:1px solid black'>");
	
	
	// creating the label 
	bodyMsg.append("<table border=1>");
	bodyMsg.append("<tr>");
	
	bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
	bodyMsg.append("FamilyName(Nom)");
	bodyMsg.append("</th>");
	
	bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
	bodyMsg.append("FirstName(Prénom)");
	bodyMsg.append("</th>");
	
	bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:100px;color:white;'>");
	bodyMsg.append("Stellantis ID");
	bodyMsg.append("</th>");
	
	/*bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:100px;color:white;'>");
	bodyMsg.append("Location");
	bodyMsg.append("</th>");
	
	bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:100px;color:white;'>");
	bodyMsg.append("Status");
	bodyMsg.append("</th>");*/
	
	//if(emp.getPsaLibTypeID() != 0) { //not NO VM
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:100px;color:white;'>");
		bodyMsg.append("VM Number");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);color:white;'>");
		bodyMsg.append("Stellantis Email");
		bodyMsg.append("</th>");
	//}
	
	bodyMsg.append("</tr>");

	
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLastName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getFirstName());
		bodyMsg.append("</td>");
		
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getPsaId());
		bodyMsg.append("</td>");
		
		/*bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getStateName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append("Done");
		bodyMsg.append("</td>");*/
		
		//if(emp.getPsaLibTypeID() != 0) { //not NO VM
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append("VM = "+emp.getVmNumber());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getPCAEmail());
		bodyMsg.append("</td>");
		//}

		bodyMsg.append("</tr>");
	
	bodyMsg.append("</table></body></html>");
	bodyMsg.append("<br/><br/>");
	
	/*bodyMsg.append("<span style='color:rgb(28, 67, 113);'>By default, the temporary password is composed by :</span><br/><br/>");
	bodyMsg.append("<span style='color:rgb(28, 67, 113);'>1st letter of first name (Upper Case)</span></br>");
	bodyMsg.append("<span style='color:rgb(28, 67, 113);'>1st letter of last name (Lower case)</span></br>");
	bodyMsg.append("<span style='color:rgb(28, 67, 113);'>Birthday (MMDDYY)</span><br/><br/>");
	
	bodyMsg.append("<b>Step1: Check your prerequisites</b><br/>");
	bodyMsg.append("       Check Cisco Anyconnect (outside Capgemini network, you can use shared mobile connection). In case of issue contact IT Help support<br/><br/>");
	
	bodyMsg.append("<b>Step2: Reach your PSALib/PSA VM following the information detailed here: <a https://e-3d-dc1.capgemini.com/confluence/display/HERK/How+to+Access+my+VDI>https://e-3d-dc1.capgemini.com/confluence/display/HERK/How+to+Access+my+VDI</a></b><br/>");
	bodyMsg.append("       This access to the PSALib is available from Capgemini Hermes dedicated and secured spaces.<br/>");
	bodyMsg.append("       From outside Capgemini, it will work only if you have a Hermes Remote Access (HRA) access, requested for you by your EM. You can use a shared mobile connection.<br/><br/>");
	
	bodyMsg.append("The following pages on Confluence may help you:<br/>");
	bodyMsg.append("<ul>");
	bodyMsg.append("<li><b>IT Solver:</b> <a href:https://e-3d-dc1.capgemini.com/confluence/display/HERK/HERMES-IT+Solver>https://e-3d-dc1.capgemini.com/confluence/display/HERK/HERMES-IT+Solver</a> and many tips <a href:https://e-3d-dc1.capgemini.com/confluence/display/HERK/How-to>https://e-3d-dc1.capgemini.com/confluence/display/HERK/How-to</a></li>");	
	bodyMsg.append("<li><b>Information on ASLs delegates, i.e. the Administrateur Sécurité Logique/Logical Security Administrators delegates,</b> to help you connect PSA systems  - <a href:https://e-3d-dc1.capgemini.com/confluence/display/HERK/Who+is+my+ASL+Delegate>https://e-3d-dc1.capgemini.com/confluence/display/HERK/Who+is+my+ASL+Delegate</a></li>");
	bodyMsg.append("</ul>");*/
	/*------------*/
	
	//bodyMsg.append("<b>Step1:</b> If you are not in office, <b>connect to Capgemini VPN</b><br/>");
	bodyMsg.append("Set your Stellantis password and connect to your STLALib following the detailed instructions here:<br/><br/>");
	
	bodyMsg.append("<a  href='https://apps.powerapps.com/play/e/default-76a2ae5a-9f00-4f6b-95ed-5d33d77c4d61/a/f33c6267-4063-49c9-93c4-5ff83749d9c9?tenantId=76a2ae5a-9f00-4f6b-95ed-5d33d77c4d61&source=sharebutton&sourcetime=1713197908228'>https://apps.powerapps.com/play/e/default-76a2ae5a-9f00-4f6b-95ed-5d33d77c4d61/a/f33c6267-4063-49c9-93c4-5ff83749d9c9?tenantId=76a2ae5a-9f00-4f6b-95ed-5d33d77c4d61&source=sharebutton&sourcetime=1713197908228</a>");
	
	bodyMsg.append("<br/><br/>");
	
	bodyMsg.append("Thanks & Regards,<br/><br/>");
	bodyMsg.append("This mail is triggered automatically.<br/>");
	bodyMsg.append("If you face any issue, please raise an incident in the <a href='https://apps.powerapps.com/play/e/default-76a2ae5a-9f00-4f6b-95ed-5d33d77c4d61/a/f33c6267-4063-49c9-93c4-5ff83749d9c9?tenantId=76a2ae5a-9f00-4f6b-95ed-5d33d77c4d61&source=sharebutton&sourcetime=1713197908228'>SOLVER Page</a> <br/><br/>");
	//bodyMsg.append("<b>HERMES PMO Team <a href:mailto:pmohermes.in@capgemini.com>(pmohermes.in@capgemini.com)</a> </b>");
	bodyMsg.append("</font></span></font>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();

}

public static String OPELWelcomeEmail1(String firstName) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi " + firstName + ", <br/><br/>");
	bodyMsg.append("<b>Welcome to Opel. Hope you will have a great time working with us. Welcome aboard new buddy!</b><br/><br/>");
	
	bodyMsg.append("You will find below useful link to access <b>various templates which will enable you to track the KT sessions</b>, as well as tools used on Opel and procedures to follow. Please read it carefully and contact your direct manager if you have any questions.<br/><br/>");
	
	bodyMsg.append("<a href=https://e-3d-jira.capgemini.com/confluence/display/HERK/Problem+Solver>KT-Kit Link </a><br/><br/>");
	bodyMsg.append("Also, do not hesitate to contact the PMO / Domain Service Delivery Leads who will be pleased to help you as much as s/he can:<br/><br/>");
	bodyMsg.append("<ul>");
	bodyMsg.append("<li>PMO : Vidya Joshi</li>");
	bodyMsg.append("<li>Manufacturing & SC Domain SDM : Manish Lokhande</li>");
	bodyMsg.append("<li>Engineering Domain SDM : Chhaya Shinde</li>");
	bodyMsg.append("<li>Sales & After sales Domain SDM : Kartik Iyer</li></ul><br/>");
	
	bodyMsg.append("You also need to complete the below mandatory trainings.  Please connect with your PMO in case of any doubts.<br/><br/>");
	
	bodyMsg.append("<ol>");
	bodyMsg.append("<li>ITIL Training</li>");
	bodyMsg.append("<li>CHROME training</li>");
	bodyMsg.append("<li>Process Training</li>");
	bodyMsg.append("<li>Transition kick off session : Classroom Training (happens once a month</li></ol><br/>");
	//bodyMsg.append("<br/>");
	
	bodyMsg.append("You also need to ensure if the below is in place in order to take delivery commitments.<br/>");
	bodyMsg.append("<ol>");
	bodyMsg.append("<li>Sharepoint access for all 3 entities GM, OV, CG - Please send an email to vidya.joshi@capgemini.com</li>");
	bodyMsg.append("<li>Sharepoint access for Opel and CG - Please send an email to vidya.joshi@capgemini.com</li>");
	bodyMsg.append("<li>GM ID creation - Please send an email to vidya.joshi@capgemini.com with the details of your DOB. <a href=http://sp2013-intern.ce.capgemini.com/CGD/Auto/OpelPSA/GMOV/Shared Documents/On Boarding Documents/User guide for activating the GMID.pptx>Please activate the GM ID within 1 week from the day you get the ID</a></li>");
	bodyMsg.append("<li>Laptop Request - Please raise a request in India Service Desk.</li>");
	bodyMsg.append("<li><a href=http://sp2013-intern.ce.capgemini.com/CGD/Auto/OpelPSA/GMOV/Shared Documents/On Boarding Documents/OV Acceleration Network connectivity v1.0.docx>Remote desktop connectivity</a></li>");
	bodyMsg.append("<li><a href=http://sp2013-intern.ce.capgemini.com/CGD/Auto/OpelPSA/GMOV/Shared Documents/On Boarding Documents/ITSM_Access and update user role.docx> Access to ITSM - Request to be raised in IT catalogue.</a></li>");
	bodyMsg.append("<li>Adding name to the Opel DLs - Ensure your name is added in respective DL as per your Domain. Please write to vidya.joshi@capgemini.com</li></ol><br/>");
	
	bodyMsg.append("<br/>");
	bodyMsg.append("Thanks & regards,<br/>");
	bodyMsg.append("Opel PMO team<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
	
}

public static String OPELWelcomeEmail2(String firstName) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi " + firstName + ", <br/><br/>");
	bodyMsg.append("Welcome to the Opel Program!<br/><br/>");
	bodyMsg.append("This second mail is about the various accesses you need to connect with Opel environment.  You need to ensure below is in place in order to take delivery commitments.<br/><br/>");
	bodyMsg.append("<ol>");
	bodyMsg.append("<li>Sharepoint access for all 3 entities GM, OV, CG  – Please send an email to vidya.joshi@capgemini.com</li>");
	bodyMsg.append("<li>Sharepoint access for Opel and CG – Please send an email to vidya.joshi@capgemini.com</li>");
	bodyMsg.append("<li>GM ID creation  – Please send an email to vidya.joshi@capgemini.com with the details of your DOB. <a href=http://sp2013-intern.ce.capgemini.com/CGD/Auto/OpelPSA/GMOV/Shared Documents/On Boarding Documents/User guide for activating the GMID.pptx>Please activate the GM ID within 1 week from the day you get the ID</a></li>");
	bodyMsg.append("<li>Laptop Request -  Please raise a request in India Service Desk.</li>");
	bodyMsg.append("<li><a href:http://sp2013-intern.ce.capgemini.com/CGD/Auto/OpelPSA/GMOV/Shared Documents/On Boarding Documents/OV Acceleration Network connectivity v1.0.docx>Remote desktop connectivity</a></li>");
	bodyMsg.append("<li><a href:http://sp2013-intern.ce.capgemini.com/CGD/Auto/OpelPSA/GMOV/Shared Documents/On Boarding Documents/ITSM_Access and update user role.docx> Access to ITSM – Request to be raised in IT catalogue.</a></li>");
	bodyMsg.append("<li>Adding name to the Opel DLs – Ensure your name is added in respective DL as per your Domain. Please write to vidya.joshi@capgemini.com</li></ol><br/>");
	
	bodyMsg.append("<br/>");
	bodyMsg.append("Thanks & regards,<br/>");
	bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
}


public static String RMPMOMail(String firstName,Employee emp, boolean isOnshore,boolean hasPSAID) {
	
	StringBuffer bodyMsg = new StringBuffer();
	
	
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi " + firstName + ", <br/><br/>");
	if(hasPSAID) {
		bodyMsg.append("Please revoke the Stellantis ID for corp id - "+ emp.getCorpId() +" ("+emp.getFirstName()+" "+emp.getLastName()+") as the resource's actual offboarding day is today "+".<br/><br/>");
	}
	/*if(isOnshore) {
		bodyMsg.append("Please remove "+ emp.getCorpId() +" ("+emp.getFirstName()+" "+emp.getLastName()+ ") from DL.<br/><br/>");
	}*/
	bodyMsg.append("Following are the details :<br/>");
	bodyMsg.append("BIS - "+emp.getBis().getBis_Name()+"<br/>");
	bodyMsg.append("First Name - "+emp.getFirstName()+"<br/>");
	bodyMsg.append("Last Name - "+emp.getLastName()+"<br/>");
	if(hasPSAID) {
		bodyMsg.append("Stellantis ID - "+emp.getPsaId()+"<br/>");
	}
	bodyMsg.append("<br/>");
	
	bodyMsg.append("Thanks & Regards,<br/><br/>");
	bodyMsg.append("This mail is triggered automatically." + "<br/>");
	//bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
}




public static String BISPMODLMail(Employee emp) {
	
StringBuffer bodyMsg = new StringBuffer();
	
	
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi, <br/><br/>");
	
	bodyMsg.append("Please revoke HRA access for "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+")"+"<br/><br/>");
	
	bodyMsg.append("<li>Corp Id : "+emp.getCorpId()+"<br/>");
	bodyMsg.append("<li>Firstname : "+emp.getFirstName()+"<br/>");
	bodyMsg.append("<li>Lastname : "+emp.getLastName()+"<br/>");
	bodyMsg.append("<li>Country : "+emp.getCountry().getCountryName()+"<br/><br/>");
	bodyMsg.append("<li>BIS : "+emp.getBis().getBis_Name()+"<br/><br/>");
	
	bodyMsg.append("Thanks & regards,<br/>");
	bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
}

public static String copilotLicenseRevokMail(Employee emp) {//new mail for Github Copilot License revocation at the time of offboarding
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	
	bodyMsg.append("Hi GitHub Copilot license SPOC,<br/><br/>");
	bodyMsg.append("Please revoke the Stellantis GitHub Copilot license for corp id - "+ emp.getCorpId() +" ("+emp.getFirstName()+" "+emp.getLastName()+") as the resource's actual offboarding day is today"+".<br/><br/>");
	
	bodyMsg.append("Following are the details :<br/>");
	bodyMsg.append("BIS - "+emp.getBis().getBis_Name()+"<br/>");
	bodyMsg.append("Manager  - "+emp.getManager().getFirstName()+" "+emp.getManager().getLastName()+"<br/>");
	bodyMsg.append("Manager Email - "+emp.getManagerEmail()+"<br/>");
	if(!emp.getPsaId().isEmpty() && !emp.getPsaId().equalsIgnoreCase("NO PSA ID")) {
		bodyMsg.append("Stellantis ID - "+emp.getPsaId()+"<br/>");
	}
	
	bodyMsg.append("<br/>");
	
	bodyMsg.append("Thanks & Regards,<br/><br/>");
	bodyMsg.append("This mail is triggered automatically." + "<br/>");
	bodyMsg.append("For additional information, please contact the BIS PMO.<br/><br/>");
	bodyMsg.append("" + "<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
}


public static String BISPMOMail(Employee emp, BisPMOMap bisPmo) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	/*bodyMsg.append("Hi " + bisPmo.getPmo_name() + ", <br/><br/>");*/
	bodyMsg.append("Hi " + emp.getBis().getBis_Name() + " PMO, <br/><br/>");
	//bodyMsg.append("Please remove corp id : "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+") of bis :"+emp.getBis().getBis_Name()+" from DL."+"<br/><br/>");
	bodyMsg.append("Offboarding is done for corp id : "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+") of bis : "+emp.getBis().getBis_Name()+", Location : "+emp.getLocation().getStateName()+"<br/><br/>");
	switch(emp.getLocation().getStateId()) { // Chennai, Kolkata, Hyderabad, Gurgaon
		case 31:
		case 33:
		case 34:
		case 49:
			bodyMsg.append("And also revoke ODC access."+"<br/><br/>");
			break;
		default:
			System.out.println("ODC revocation mail will be trigerred to specific ICRES DL");
	}
	
	//start
	bodyMsg.append("<ol><li>If the offboarded resource is a manager or EM, make sure you update the new manager or EM name for his/her team in Hermesto</li>");
	bodyMsg.append("<li>Remove user from manually updated DL, if any (automated DL will be updated by the bot)</li>");
	bodyMsg.append("<li>Remove access to BIS sharepoints and Teams</li></ol>");
	//bodyMsg.append("<li>Remove user from Tempo projects : <a href=https://e-3d-dc1.capgemini.com/confluence/display/HERK/Open+a+user+management+request+in+I4U+portal>https://e-3d-dc1.capgemini.com/confluence/display/HERK/Open+a+user+management+request+in+I4U+portal</a></li></ol>");
	//end
	bodyMsg.append("<br/>Thanks & Regards,<br/><br/>");
	bodyMsg.append("This mail is triggered automatically." + "<br/>");
	//bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
	bodyMsg.append("Please contact <a href=mailto:hermesresourcemanagementpmo.in@capgemini.com>hermesresourcemanagementpmo.in@capgemini.com</a> for additional information.<br/><br/>");
	bodyMsg.append("" + "<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
		
	}



public static String ExternalMail(Employee emp, DLList dlContractManager ) {
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hi "  +dlContractManager.getName()+ ", <br/><br/>");
	bodyMsg.append("Subcontractor Corp id : "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+")  from Vendor : "+emp.getVendor().getVendorName()+" is offboarded today.<br/><br/>");
	bodyMsg.append("Thanks & regards,<br/>");
	bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
	bodyMsg.append("</div>");
	return bodyMsg.toString();
		
	}

	public static String ICRESODCRevokeMail(Employee emp) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello Team, <br/><br/>");
		bodyMsg.append("Please revoke STELLANTIS/PSA ODC access of the below resource.<br/><br/>");
		bodyMsg.append("GG ID - " +emp.getGgId()+"<br/>");
		bodyMsg.append("Name - "+emp.getFirstName()+ " "+emp.getLastName()+"<br/>");
		bodyMsg.append("Location - "+emp.getLocation().getStateName()+"<br/><br/>");

		bodyMsg.append("Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		//bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
		bodyMsg.append("Please contact <a href=mailto:hermesresourcemanagementpmo.in@capgemini.com>hermesresourcemanagementpmo.in@capgemini.com</a> for additional information.<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String ODCAccessBISPMOMail(Employee emp) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello Team, <br/><br/>");
		bodyMsg.append("Please grant STELLANTIS ODC access of the below resource.<br/><br/>");
		bodyMsg.append("GG ID - " +emp.getGgId()+"<br/>");
		bodyMsg.append("Name - "+emp.getFirstName()+ " "+emp.getLastName()+"<br/>");
		bodyMsg.append("Location - "+emp.getLocation().getStateName()+"<br/><br/>");

		bodyMsg.append("Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information." + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String resODCMail(Employee emp) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi "+emp.getFirstName()+ ", <br/><br/>");
		bodyMsg.append("Welcome to the Stellantis family!<br/><br/>");
		bodyMsg.append("As you are now part of the Stellantis account, please ensure that you work exclusively from the designated Stellantis ODC (Offshore Development Center).<br/>"); 
		bodyMsg.append("Working from outside the ODC is considered as compliance issue, so we kindly ask you not to attempt it under any circumstances.<br/>");
		bodyMsg.append("Please find your ODC location details below.<br/><br/>");

		
		switch(emp.getLocation().getStateId()){
		case 4:	bodyMsg.append("B6, 12th Floor, Airoli Campus<br/>");
				bodyMsg.append("Navi Mumbai, Maharashtra 400708<br/>");
				break;
				
		case 5:	bodyMsg.append("B5, 8th Floor, DivyaShree Tech park<br/>");
				bodyMsg.append("Kundalahalli, Whitefield, Bengaluru, Karnataka 560037<br/>");
				
				/*bodyMsg.append("<b>OR</b><br/><br/>");
				
				bodyMsg.append("6B, 2nd Floor, ECOSPACE<br/>");
				bodyMsg.append("Outer Ring Rd, Bellandur, Bengaluru, Karnataka 560103<br/>");*/
				break;
				
		case 20:bodyMsg.append("DB2, 2nd Floor<br/>");
				bodyMsg.append("Hinjewadi, Phase-3<br/>");
				bodyMsg.append("Pune, Maharashtra 411057<br/>");
				break;
				
		case 34:bodyMsg.append("Hyderabad Main campus<br/>");
				bodyMsg.append("Block - SB2, Floor - 2F - A wing<br/>");
				bodyMsg.append("HYD/SB2/2FA/WS-001 to HYD/SB2/2FA/WS-093F<br/>");
				bodyMsg.append("IT Park, Plot No 1, 115/32 and 35<br/>");
				bodyMsg.append("Nanakramguda, Gachibowli, Rangareddy, Gachibowli , Hyderabad , Telangana- 500032<br/>");
				break;
				
		default: System.out.println("NO ODC access for "+emp.getCorpId());
				
		}		
		
		bodyMsg.append("<br/>Once again, welcome aboard, and we look forward to a great journey together!<br/><br/>");
		bodyMsg.append("Regards,<br/>");
		bodyMsg.append("Stellantis Onboarding Team<br/>");
		return bodyMsg.toString();
	}
	
	public static String SeatDesktopRequest(PreOnbEmployee emp, String recipient) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello "+ recipient+", <br/><br/>");
		bodyMsg.append("Please arrange seat for: <br/><br/>");
		bodyMsg.append("Corp id - " +emp.getCorpId()+"<br/>");
		bodyMsg.append("Name - "+emp.getFirstName()+ " "+emp.getLastName()+"<br/>");
		bodyMsg.append("Location - "+emp.getLocation().getStateName()+"<br/>");
		bodyMsg.append("Bis - " +emp.getBis().getBis_Name()+"<br/>");
		bodyMsg.append("Manager - " +emp.getManager().getFirstName()+" "+emp.getManager().getLastName()+"<br/>");
		bodyMsg.append("Manager email - " +emp.getManagerEmail()+" <br/><br/>");
		bodyMsg.append("Thanks & regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically.<br/>");
		bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information." + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String SeatDesktopRevoke(Employee emp, String recipient) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello "+ recipient+", <br/><br/>");
		bodyMsg.append("Please revoke the seat assigned to <br/><br/>");
		bodyMsg.append("Corp id - " +emp.getCorpId()+"<br/>");
		bodyMsg.append("Name - "+emp.getFirstName()+ " "+emp.getLastName()+"<br/>");
		bodyMsg.append("Location - "+emp.getLocation().getStateName()+"<br/>");
		bodyMsg.append("Bis - " +emp.getBis().getBis_Name()+"<br/>");
		bodyMsg.append("Manager - " +emp.getManager().getFirstName()+" "+emp.getManager().getLastName()+"<br/>");
		bodyMsg.append("Manager email - " +emp.getManagerEmail()+" <br/><br/>");
		bodyMsg.append("Thanks & regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		//bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
		//bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information." + "<br/><br/>");
		bodyMsg.append("Please contact <a href=mailto:hermesresourcemanagementpmo.in@capgemini.com>hermesresourcemanagementpmo.in@capgemini.com</a> for additional information.<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
		
	}
	

	public static String RMMail(Employee emp, Spoc spocRec) {
	
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + spocRec.getSpocName() + ", <br/><br/>");
		
		bodyMsg.append("Please revoke ODC access for corp id : "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+").<br/><br/>");
		bodyMsg.append("Location : " + emp.getLocation().getStateName()+".<br/><br/>");		
		bodyMsg.append("Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically. <br/>");
		bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String JIRARevokeMail(Employee emp, DLList dl) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + dl.getName() + ", <br/><br/>");
		
		//bodyMsg.append("Please delete JIRA and Confluence ID for resource with corp id : "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+").<br/><br/>");
		bodyMsg.append("Please revoke access to JIRA and Confluence for resource with corp id : "+emp.getCorpId()+" ("+emp.getFirstName()+" "+emp.getLastName()+").<br/>");
		//start
		bodyMsg.append("<ul><li><b>STEP 1</b> : Detag the user from all Hermes projects (all projects which name starts with “PSAH” or “STE-HERMES”)</li>");
		bodyMsg.append("<b><li>STEP 2</b> : Remove user from all Hermes related usergroups:");
		bodyMsg.append("<ul><li>HermesPSAConfluenceRead</li>");
		bodyMsg.append("<li>HermesPSAJiraUsers</li>");
		bodyMsg.append("<li>HermesPSAConfluenceWrite</li>");
		bodyMsg.append("<li>HermesPSAFactoriesLeaders</li>");
		bodyMsg.append("<li>HermesPSAFactoriesMembers</li>");
		bodyMsg.append("<li>HermesPSAManagement</li>");
		bodyMsg.append("<li>HermesPSATestingBox</li>");
		bodyMsg.append("<li>HermesStaffingManagers</li>");
		bodyMsg.append("<li>Staffing Group</li>");
		//bodyMsg.append("<li>HermesPSAStaffing</li>");
		bodyMsg.append("<li>EM</li>");
		bodyMsg.append("<li>HermesDeliveryOffice</li>");
		bodyMsg.append("<li>ES_C1ST_TEAM</li></ul></li>");
		bodyMsg.append("<li><b>STEP 3</b> : Remove the user’s Jira licence (Usergroup: jira-software-users)</li></ul>");
		//end
		bodyMsg.append("<br/><br/>Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically.<br/>");
		//bodyMsg.append("Please contact <a href=mailto:stellantishermesonboarding.fr@capgemini.com>FR, Stellantis HERMES Onboarding</a> for additional information.<br/><br/>");
		bodyMsg.append("Please contact <a href=mailto:hermesresourcemanagementpmo.in@capgemini.com>hermesresourcemanagementpmo.in@capgemini.com</a> for additional information.<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String EMOffboardingMail(Employee emp, DLList dl, Employee EMRecord, Date dateWithoutTime) {
		
		StringBuffer bodyMsg = new StringBuffer();
		String offboardingDay = dateWithoutTime.getDate()+"/"+(dateWithoutTime.getMonth()+1)+"/"+(dateWithoutTime.getYear()+1900)+" (dd/MM/yyyy)";
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + EMRecord.getFirstName() + ", <br/><br/>");
		
		bodyMsg.append("This is to inform that offboarding for "+emp.getFirstName()+" "+emp.getLastName()+"("
		+emp.getCorpId()+") is on "+offboardingDay+", in case if there is any change in offboarding plan please get in touch with RM PMO "
		+dl.getName()+"( "+dl.getEmail()+"). Else all accesses will be revoked by "+offboardingDay +".<br/><br/>");
		
		bodyMsg.append("Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		bodyMsg.append("If you have  any question, you can contact hermesonboardingspoc.fr@capgemini.com" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
public static String MngrOffboardingMail(Employee emp, DLList dl, Date dateWithoutTime) {
		
		StringBuffer bodyMsg = new StringBuffer();
		String offboardingDay = dateWithoutTime.getDate()+"/"+(dateWithoutTime.getMonth()+1)+"/"+(dateWithoutTime.getYear()+1900)+" (dd/MM/yyyy)";
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello "+emp.getManager().getFirstName()+" "+emp.getManager().getLastName()+", <br/><br/>");
		//bodyMsg.append("Hi, <br/><br/>");
		bodyMsg.append("This is to inform that offboarding for "+emp.getFirstName()+" "+emp.getLastName()+"("
		+emp.getCorpId()+") is on "+offboardingDay+", in case if there is any change in offboarding plan please get in touch with "
		+dl.getName()+" (mailto:hermesresourcemanagementpmo.in@capgemini.com). Else all accesses will be revoked by "+offboardingDay +".<br/><br/>");
		
		bodyMsg.append("Thanks & regards,<br/><br/>"); 
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		bodyMsg.append("Please contact <a href=mailto:hermesresourcemanagementpmo.in@capgemini.com>hermesresourcemanagementpmo.in@capgemini.com</a> for additional information.<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String VLANIndiaMail(PreOnbEmployee emp) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi Team, <br/><br/>");
		bodyMsg.append("Please add below resource in the PSA Access Group.<br/><br/>");
		bodyMsg.append("Corp ID - "+emp.getCorpId()+"<br/>");
		bodyMsg.append("Name - "+emp.getFirstName()+" "+emp.getLastName()+"<br/>");
		bodyMsg.append("Location - "+emp.getLocation().getStateName()+"<br/><br/>");
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding Tool team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String RevokeVLANIndiaMail(Employee emp) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi Team, <br/><br/>");
		bodyMsg.append("Please remove below resource from PSA Access Group.<br/><br/>");
		bodyMsg.append("Corp ID - "+emp.getCorpId()+"<br/>");
		bodyMsg.append("Name - "+emp.getFirstName()+" "+emp.getLastName()+"<br/>");
		bodyMsg.append("Location - "+emp.getLocation().getStateName()+"<br/><br/>");
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding Tool team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}

	public static String VLANMail(PreOnbEmployee emp) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi Team, <br/><br/>");
		bodyMsg.append("Please add below resource in VLAN.<br/><br/>");
				
		bodyMsg.append("<html><body>"
	            + "<table style='border:1px solid black'>");
		
		// creating the label 
		bodyMsg.append("<table border=1>");
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Corp Id");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Location");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("VLAN");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("PC Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Mac Address");
		bodyMsg.append("</th>");
		
		bodyMsg.append("</tr>");

		
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getEmail());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getCorpId());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getStateName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getVLANName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getPcSerialNumber());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		//bodyMsg.append(emp.getMacAddress());
		bodyMsg.append("</td>");
		
		bodyMsg.append("</tr>");
		
		bodyMsg.append("</table></body></html>");
		bodyMsg.append("<br/><br/>");
		
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding / Offboarding team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String RevokeVLANMail(Employee emp){

		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi Team, <br/><br/>");
		bodyMsg.append("Please remove below resource from VLAN.<br/><br/>");
		
		bodyMsg.append("<html><body>"
	            + "<table style='border:1px solid black'>");
		
		
		// creating the label 
		bodyMsg.append("<table border=1>");
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Corp Id");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Location");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("VLAN");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("PC Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Mac Address");
		bodyMsg.append("</th>");
		
		bodyMsg.append("</tr>");

		
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getEmail());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getCorpId());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getStateName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getVLANName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getPcSerialNumber());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		//bodyMsg.append(emp.getMacAddress());
		bodyMsg.append("</td>");
		
		bodyMsg.append("</tr>");
		
		bodyMsg.append("</table></body></html>");
		bodyMsg.append("<br/><br/>");
				
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding / Offboarding team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	
	}
	
	public static String VLANMail(Employee emp) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi Team, <br/><br/>");
		bodyMsg.append("Please add below resource in VLAN.<br/><br/>");
		
		bodyMsg.append("<html><body>"
	            + "<table style='border:1px solid black'>");
		
		
		// creating the label 
		bodyMsg.append("<table border=1>");
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Corp Id");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Location");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("VLAN");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("PC Name");
		bodyMsg.append("</th>");
		
		bodyMsg.append("<th style='background-color:rgb(16, 144, 208);width:180px;color:white;'>");
		bodyMsg.append("Mac Address");
		bodyMsg.append("</th>");
		
		bodyMsg.append("</tr>");

		
		bodyMsg.append("<tr>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getEmail());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getCorpId());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getStateName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getLocation().getVLANName());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		bodyMsg.append(emp.getPcSerialNumber());
		bodyMsg.append("</td>");
		
		bodyMsg.append("<td style='background-color:rgb(249, 211, 17)'>");
		//bodyMsg.append(emp.getMacAddress());
		bodyMsg.append("</td>");
		
		bodyMsg.append("</tr>");
		
		bodyMsg.append("</table></body></html>");
		bodyMsg.append("<br/><br/>");
				
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding / Offboarding team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String VLANBISPMOMail(PreOnbEmployee emp, String bisPMOName) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi "+emp.getRequestor().getFirstName()+", <br/><br/>");
		/*bodyMsg.append("Please share PC Hostname & MAC Address of preonboarded resource "+emp.getFirstName()+" "+emp.getLastName()+"("+emp.getCorpId()
		+")with BIS PMO "+bisPMOName+".<br/><br/>");*/
		bodyMsg.append("Please ask preonboarded resource "+emp.getFirstName()+" "+emp.getLastName()+" to provide PC details & MAC address to the BIS PMO "
		+bisPMOName+ " to get added in VLAN.<br/><br/>");
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding / Offboarding team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
		
	}
	
	
	public static String newCountryLocationMail(Employee loggedUserEmp, String corpid, String newRequest , Country coun) {
		
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi Team, <br/><br/>");
		bodyMsg.append("Following request is raised by "+loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName()
		+ " for new resource with corp id "+corpid+ ".<br/><br/>");
		//bodyMsg.append(newRequest+"<br/><br/>");
		bodyMsg.append(newRequest);
		bodyMsg.append(" in Country "+coun.getCountryName()+".<br/><br/>");
		
		bodyMsg.append("Thanks & Regards,<br/><br/>");
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		bodyMsg.append("Please contact requestor "+loggedUserEmp.getFirstName()+" "+loggedUserEmp.getLastName()+" for additional information.<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
		
		
	}
	
	public static String defaultPasswordMail() {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi, <br/><br/>");
		bodyMsg.append("Your default password is pass<br/><br/>");
		bodyMsg.append("You will be asked to reset password on first login.<br/><br/>");
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
	
	public static String contractorEmail(PreOnbEmployee emp, DLList dlContractManager, String vendor) {
		StringBuffer bodyMsg = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date planOffboard =  null;
		Date onboardingDate = null;
		try {
			planOffboard = sdf.parse(sdf.format(emp.getEndDate()));
			onboardingDate = sdf.parse(sdf.format(emp.getJoiningDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		
		
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Dear "+dlContractManager.getName()+", <br/><br/>");
		bodyMsg.append("Subcontractor " +emp.getFirstName()+" "+emp.getLastName()+ " from "+vendor 
				+" is onboarded.<br/>");
		bodyMsg.append("Onboarding Date is "+onboardingDate+" <br/>");
		bodyMsg.append("Planned offboarding date is "+planOffboard+" <br/><br/>");
		//bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()+" for additional information. <br/><br/>");
		
		bodyMsg.append("Thanks & Regards,<br/><br/>");
		/*bodyMsg.append("Onboarding / Offboarding team" + "<br/><br/>");*/
		bodyMsg.append("This mail is triggered automatically." + "<br/>");
		bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()
				+" for additional information. <br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();

	}
	
	
	
	public static String contractoroffboardedEmail(Employee emp, DLList dlContractManager, String vendor) {
		StringBuffer bodyMsg = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date planOffboard =  null;
		Date onboardingDate = null;
		try {
			planOffboard = sdf.parse(sdf.format(emp.getEndDate()));
			onboardingDate = sdf.parse(sdf.format(emp.getJoiningDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		
		
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi "+dlContractManager.getName()+", <br/><br/>");
		bodyMsg.append("Subcontractor " +emp.getFirstName()+" "+emp.getLastName()+ " from Vendor "+vendor 
				+" is onboarded.<br/>");
		bodyMsg.append("Onboarding Date is "+onboardingDate+" <br/>");
		bodyMsg.append("Planned offboarding date is "+planOffboard+" <br/><br/>");
		bodyMsg.append("Please contact requestor "+emp.getRequestor().getFirstName()+" "+emp.getRequestor().getLastName()
				+" for additional information. <br/><br/>");
		
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("Onboarding / Offboarding team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();

	}
public static String RMOnMail( Spoc spocRec,PreOnbEmployee emp) {
		
		StringBuffer bodyMsg = new StringBuffer();
		
		
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + spocRec.getSpocName() + ", <br/><br/>");

			bodyMsg.append("Please validate the onboarding of the resource with corp id - "+ emp.getCorpId() +" ("+emp.getFirstName()+" "+emp.getLastName()+"), as the resource onboarding is pending "+".<br/><br/>");
		
			bodyMsg.append("EM last action date  for this corp id -"+emp.getEMSubmitDT()+"<br></br>");
		bodyMsg.append("<br/>");
		
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}


	public static String BISPMOOnMail(String firstName,PreOnbEmployee emp) {
		
	StringBuffer bodyMsg = new StringBuffer();
		
		
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hi " + firstName + ", <br/><br/>");

			bodyMsg.append("Please validate the onboarding of the resource with corp id - "+ emp.getCorpId() +" ("+emp.getFirstName()+" "+emp.getLastName()+"), as the resource onboarding is pending "+".<br/><br/>");
		
			bodyMsg.append("RM Last action date for this corp id -"+emp.getRMSubmitDT()+"<br></br>");
		bodyMsg.append("<br/>");
		
		bodyMsg.append("Thanks & regards,<br/>");
		bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
			
		
}
	public static String RMPMOOnMail(String firstName, PreOnbEmployee employee) {
		
		StringBuffer bodyMsg = new StringBuffer();
			bodyMsg.append("<div>");
			bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
			bodyMsg.append("Hi " + firstName + ", <br/><br/>");
			
				bodyMsg.append("Please validate the onboarding of the resource with corp id - "+ employee.getCorpId() +" ("+employee.getFirstName()+" "+employee.getLastName()+"), as the resource onboarding is pending "+".<br/><br/>");
				bodyMsg.append(" BISPMO Last action date for this corp id -"+employee.getBISPMOSubmitDT()+"<br></br>");
			bodyMsg.append("<br/>");
			
			bodyMsg.append("Thanks & regards,<br/>");
			bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
			bodyMsg.append("</div>");
			return bodyMsg.toString();
				
			
	}
	

public static String RMPMOOnMails(String name, List listOfIds,List listOfIdFirstName,List listOfIdLastName,List listOfIdPrimayprogram,List listOfSubmitDates) {
		
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hello "+name+", <br/><br/>");
	bodyMsg.append("This is a Reminder to complete the onboarding process for below corp-id's asap.<br/><br/>");
	bodyMsg.append("<html><body>"
            + "<table style='border:1px solid black'>");
	// creating the label 
			bodyMsg.append("<table border=1>");
			bodyMsg.append("<tr>");
			
			bodyMsg.append("<th>");
			bodyMsg.append("Sl.No.");
			bodyMsg.append("</th>");
			bodyMsg.append("<th>");
			bodyMsg.append("Corp_id.");
			bodyMsg.append("</th>");
			bodyMsg.append("<th>");
			bodyMsg.append("First Name");
			bodyMsg.append("</th>");
			bodyMsg.append("<th>");
			bodyMsg.append("Last Name");
			bodyMsg.append("</th>");
			bodyMsg.append("<th>");
			bodyMsg.append("Primary Program");
			bodyMsg.append("</th>");
			bodyMsg.append("<th>");
			bodyMsg.append("Last BISPMOAction Date");
			bodyMsg.append("</th>");
			bodyMsg.append("</tr>");
			
for(int i=0; i<listOfIds.size(); i++){
				
				bodyMsg.append("<tr>");
				bodyMsg.append("<td>");
				bodyMsg.append(i+1);
				bodyMsg.append("</td>");
				bodyMsg.append("<td>");
				bodyMsg.append(listOfIds.get(i));
				bodyMsg.append("</td>");
				bodyMsg.append("<td>");
				bodyMsg.append(listOfIdFirstName.get(i));
				bodyMsg.append("</td>");
				bodyMsg.append("<td>");
				bodyMsg.append(listOfIdLastName.get(i));
				bodyMsg.append("</td>");
				bodyMsg.append("<td>");
				bodyMsg.append(listOfIdPrimayprogram.get(i));
				bodyMsg.append("</td>");
				bodyMsg.append("<td>");
				bodyMsg.append(listOfSubmitDates.get(i));
				bodyMsg.append("</td>");
				bodyMsg.append("</tr>");
			}
         
			bodyMsg.append("</table></body></html>");
			bodyMsg.append("<br/><br/>");
		
			bodyMsg.append("Thanks & regards,<br/>");
			bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
			bodyMsg.append("</div>");
	       return bodyMsg.toString();
				
			
	}


public static String RMPMOVMMails(String name, List listOfCorpIds,List listOfCorpIdFirstName,List listOfCorpIdLastName,List listOfCorpIdPrimaryprogram, List listOfBisPmoSubmitDate) {
	
	StringBuffer bodyMsg = new StringBuffer();
	bodyMsg.append("<div>");
	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
	bodyMsg.append("Hello "+name+", <br/><br/>");
	bodyMsg.append("This is a Reminder to complete the onboarding process for below corp-id's asap.<br/><br/>");
	bodyMsg.append("<html><body>"
            + "<table style='border:1px solid black'>");
	
	// creating the label 
	bodyMsg.append("<table border=1>");
	bodyMsg.append("<tr>");
	
	bodyMsg.append("<th>");
	bodyMsg.append("Sl.No.");
	bodyMsg.append("</th>");
	bodyMsg.append("<th>");
	bodyMsg.append("Corp_id");
	bodyMsg.append("</th>");
	bodyMsg.append("<th>");
	bodyMsg.append("First Name");
	bodyMsg.append("</th>");
	bodyMsg.append("<th>");
	bodyMsg.append("Last Name.");
	bodyMsg.append("</th>");
	bodyMsg.append("<th>");
	bodyMsg.append("Primary Program");
	bodyMsg.append("</th>");
	bodyMsg.append("<th>");
	bodyMsg.append("Last BISPMOAction Date");
	bodyMsg.append("</th>");
	bodyMsg.append("</tr>");
	
for(int i=0; i<listOfCorpIds.size(); i++){
		
		bodyMsg.append("<tr>");
		bodyMsg.append("<td>");
		bodyMsg.append(i+1);
		bodyMsg.append("</td>");
		bodyMsg.append("<td>");
		bodyMsg.append(listOfCorpIds.get(i));
		bodyMsg.append("</td>");
		bodyMsg.append("<td>");
		bodyMsg.append(listOfCorpIdFirstName.get(i));
		bodyMsg.append("</td>");
		bodyMsg.append("<td>");
		bodyMsg.append(listOfCorpIdLastName.get(i));
		bodyMsg.append("</td>");
		bodyMsg.append("<td>");
		bodyMsg.append(listOfCorpIdPrimaryprogram.get(i));
		bodyMsg.append("</td>");
		bodyMsg.append("<td>");
		bodyMsg.append(listOfBisPmoSubmitDate.get(i));
		bodyMsg.append("</td>");
		bodyMsg.append("</tr>");
	}
	
			
			bodyMsg.append("</table></body></html>");
			bodyMsg.append("<br/><br/>");
			bodyMsg.append("Thanks & regards,<br/>");
			bodyMsg.append("On-boarding Tool Team" + "<br/><br/>");
			bodyMsg.append("</div>");
	        return bodyMsg.toString();
				
			
	}

}



	


	
	
	