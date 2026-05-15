package com.capgemini.moodle.mail;

import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.onboarding.mail.MailSender;

public class MoodleMailUtility {

	private static Logger logger = Logger.getLogger(MoodleMailUtility.class);
	public static String MoodleFirstAlert(String resourceName, List<String> coursesPending) {
		StringBuffer bodyMsg = new StringBuffer();
		bodyMsg.append("<div>");
		bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
		bodyMsg.append("Hello "+resourceName+", <br/><br/>");
		bodyMsg.append("This is a Gentle Reminder to complete below courses asap. Thank you.<br/><br/>");
		bodyMsg.append("Click <a href=https://frparvm39353055.corp.capgemini.com/mymoodle/my/>here</a> to continue the Learning journey.<br/><br/>");
		//mehens - new
		bodyMsg.append("<span style='background-color:rgb(255, 255, 0);color:rgb(41, 128, 185)'>Make sure you are connected to Capgemini VPN if you are outside the office.</span><br/><br/>");
		bodyMsg.append("<html><body>"
                + "<table style='border:1px solid black'>");
		// creating the label 
				bodyMsg.append("<table border=1>");
				bodyMsg.append("<tr>");
				
				bodyMsg.append("<th>");
				bodyMsg.append("Sl.No.");
				bodyMsg.append("</th>");
				
				bodyMsg.append("<th>");
				bodyMsg.append("Course");
				bodyMsg.append("</th>");
				bodyMsg.append("</tr>");
				
				for(int i=0; i<coursesPending.size(); i++){
					bodyMsg.append("<tr>");
					bodyMsg.append("<td>");
					bodyMsg.append(i+1);
					bodyMsg.append("</td>");
					
					bodyMsg.append("<td>");
					bodyMsg.append(coursesPending.get(i));
					bodyMsg.append("</td>");
					bodyMsg.append("</tr>");
				}
				bodyMsg.append("</table></body></html>");
				bodyMsg.append("<br/><br/>");
			
				
			bodyMsg.append("If you face an issue or have any question on this topic, please contact your onboarding SPOC <a href:mailto:hermeskmspoc.fr@capgemini.com>hermeskmspoc.fr@capgemini.com</a><br/><br/>");
				
			bodyMsg.append("Kind Regards,<br/>");
		bodyMsg.append("HERMES Knowledge Management Team " + "<br/><br/>");
		bodyMsg.append("</div>");
		return bodyMsg.toString();
	}
}
