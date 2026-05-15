package com.capgemini.onboarding.mail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;

import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.PreOnbEmployee;

public class MailSender {
	
	private static Logger logger = Logger.getLogger(MailSender.class);
	
	private static final String username = "SVC-FR-HERMESACCOUNT@capgemini.com";
	private static final String password = "n9giejA8CY42Q4";//old psw - Asdfghjkl1978% -- old psw - n9giejA8CY42Q3
	private static final String from = "donotreply@capgemini.com"; // Sender's email ID needs to be mentioned 
	private static final String host = "gmr10.capgemini.com"; // old "ismtp.corp.capgemini.com"; // Assuming you are sending email from localhost
	private static final String port = "25";
	
	public static void sendNew(String string, String bcc, String messageString, String msgSubject) throws Exception { //single

	      // new method for Bcc
	      
	      // Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication("SVC-FR-HERMESACCOUNT@capgemini.com", "n9giejA8CY42Q4");
	            }
	         });
	      
	      try{
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(string));
	          
	          // Set Bcc: header field of the header.
	          message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

	          // Set Subject: header field
	          message.setSubject(msgSubject);
	          
	          // Create the message part 
	          BodyPart messageBodyPart = new MimeBodyPart();

	          // Fill the message
	          messageBodyPart.setText(messageString);
	          messageBodyPart.setFileName("hermes.png");
	          
	          // Create a multi-part message
	          Multipart multipart = new MimeMultipart();

	          // Set text message part
	          multipart.addBodyPart(messageBodyPart);
	          message.setContent(messageString, "text/html; charset=utf-8");
	          
	          // Send message
	          logger.info("before sending");
	          Transport.send(message);
	          logger.info("after sending");
	       }catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
		
	}
	
	
	public static void sendNew(String[] recipient,String bcc, String[] ccList, String messageString, String msgSubject) throws Exception { // multiple

		 // new method for Bcc
		 // Get system properties
	     Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });
	      
	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         for(int reci=0; reci < recipient.length; reci++) {
	        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient[reci]));
	         }
	         for(int cc=0; cc < ccList.length; cc++) {
	        	 message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccList[cc]));
	       
	         }
	         
	         //Set Bcc: header field of the header.
	         message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
	         
	         // Set Subject: header field
	         message.setSubject(msgSubject);

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(messageString);
	         messageBodyPart.setFileName("hermes.png");
	         
	         // Create a multi-part message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(messageString, "text/html; charset=utf-8");
	        
	     	MimeMessage message1 = new MimeMessage(session);
	     	message1.setContent
	        ("<h1>This is a test</h1>" 
	         + "</images/hermes.png\">", 
	         "text/html");
	         
	         // Send message
	     	 logger.info("before sending");
	         Transport.send(message);
	         logger.info("after sending");
	         
	      }catch (MessagingException mex) {
	    	  
	         mex.printStackTrace();
	         
	      }
	   
	}
	
	
	public static void sendImageMailNew(String string,String bcc, String messageString, String msgSubject,
            Map<String, String> mapInlineImages) throws Exception {
		
		 // new method for Bcc
		// Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(string));
	         
	         
     	     //Set Bcc: header field of the header.
	         message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

	         // Set Subject: header field
	         message.setSubject(msgSubject);
	         message.setHeader("X-Priority", "1");
	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         messageBodyPart.setContent(messageString, "text/html");
	         // Fill the message
	         /*messageBodyPart.setText(messageString);
	         messageBodyPart.setFileName("hermes.png");*/
	         
	         // Create a multi-part message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         if (mapInlineImages != null && mapInlineImages.size() > 0) {
	             Set<String> setImageID = mapInlineImages.keySet();
	              
	             for (String contentId : setImageID) {
	                 MimeBodyPart imagePart = new MimeBodyPart();
	                 
	                 imagePart.setHeader("Content-ID", contentId );
	                 imagePart.setDisposition(MimeBodyPart.INLINE);
	                 
	                 String imageFilePath = mapInlineImages.get(contentId);
	                 try {
	                     imagePart.attachFile(imageFilePath);
	                 } catch (IOException ex) {
	                     ex.printStackTrace();
	                 }
	                 multipart.addBodyPart(imagePart);
	             }
	         }
	         message.setContent(multipart);
	        // message.setContent(messageString, "text/html; charset=utf-8");
	        
	         
	         // Send message
	         logger.info("before sending");
	         Transport.send(message);
	         logger.info("after sending");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      
	   }
	
	
	public static void send(String string, String messageString, String msgSubject) throws Exception { //single
		
	      // Assuming you are sending email from localhost
	      
	      // Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication("SVC-FR-HERMESACCOUNT@capgemini.com", "n9giejA8CY42Q4");
	            }
	         });
	      
	      try{
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(string));

	          // Set Subject: header field
	          message.setSubject(msgSubject);
	          
	          // Create the message part 
	          BodyPart messageBodyPart = new MimeBodyPart();

	          // Fill the message
	          messageBodyPart.setText(messageString);
	          messageBodyPart.setFileName("hermes.png");
	          
	          // Create a multi-part message
	          Multipart multipart = new MimeMultipart();

	          // Set text message part
	          multipart.addBodyPart(messageBodyPart);
	          message.setContent(messageString, "text/html; charset=utf-8");
	          
	          // Send message
	          logger.info("before sending");
	          Transport.send(message);
	          logger.info("after sending");
	       }catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
		
	}
	
	public static void sendReportAttachment(String[] recipient, String[] ccList, String messageString, String msgSubject, String filename) throws Exception{
		// Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });
	      
	      try{
		         // Create a default MimeMessage object.
	    	  
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         for(int reci=0; reci < recipient.length; reci++) {
		        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient[reci]));
		         }
		         for(int cc=0; cc < ccList.length; cc++) {
		        	 message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccList[cc]));
		       
		         }
		         // Set Subject: header field
		         message.setSubject(msgSubject);

		         // Create the message part 
		         BodyPart messageBodyPart = new MimeBodyPart();

		         // Fill the message
		         messageBodyPart.setText(messageString);
		        // messageBodyPart.setFileName("hermes.png");
		         
		         // Create a multi-part message
		         Multipart multipart = new MimeMultipart();

		         // Set text message part
		        
		         multipart.addBodyPart(messageBodyPart);
		         
		         MimeBodyPart attachPart = new MimeBodyPart();
		         
		         //DataSource source = new FileDataSource(filename);
		        // attachPart.setDataHandler(new DataHandler(source));
		         //attachPart.setFileName(new File(filename).getName());
		         attachPart.attachFile(filename);
		         multipart.addBodyPart(attachPart);
		        
		         message.setContent(multipart);
		        
		         // Send message
		         logger.info("before sending");
		         Transport.send(message);
		         logger.info("after sending");
		         
		      }catch (MessagingException mex) {
		    	  
		    	  logger.info("mex "+mex.getMessage());
		    	  
		         mex.printStackTrace();
		         
		      }
	}
	
	public static void send(String[] recipient, String[] ccList, String messageString, String msgSubject) throws Exception { // multiple


		// Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });
	      
	      try{
	    	  logger.info("inside send method");
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         for(int reci=0; reci < recipient.length; reci++) {
	        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient[reci]));
	         }
	         for(int cc=0; cc < ccList.length; cc++) {
	        	 message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccList[cc]));
	       
	         }
	         // Set Subject: header field
	         message.setSubject(msgSubject);

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(messageString);
	         messageBodyPart.setFileName("hermes.png");
	         
	         // Create a multi-part message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(messageString, "text/html; charset=utf-8");
	        
	     	MimeMessage message1 = new MimeMessage(session);
	     	message1.setContent
	        ("<h1>This is a test</h1>" 
	         + "</images/hermes.png\">", 
	         "text/html");
	         
	         // Send message
	     	 logger.info("before sending mail");
	         Transport.send(message);
	         logger.info("after sending mail");
	         
	      }catch (MessagingException mex) {
	    	  
	         mex.printStackTrace();
	         
	      }
	   
	}
	
	public static void sendImageMail(String string, String messageString, String msgSubject,
            Map<String, String> mapInlineImages) throws Exception {
		
		// Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(string));

	         // Set Subject: header field
	         message.setSubject(msgSubject);
	         message.setHeader("X-Priority", "1");
	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         messageBodyPart.setContent(messageString, "text/html");
	         // Fill the message
	         /*messageBodyPart.setText(messageString);
	         messageBodyPart.setFileName("hermes.png");*/
	         
	         // Create a multi-part message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         if (mapInlineImages != null && mapInlineImages.size() > 0) {
	             Set<String> setImageID = mapInlineImages.keySet();
	              
	             for (String contentId : setImageID) {
	                 MimeBodyPart imagePart = new MimeBodyPart();
	                 
	                 imagePart.setHeader("Content-ID", contentId );
	                 imagePart.setDisposition(MimeBodyPart.INLINE);
	                 
	                 String imageFilePath = mapInlineImages.get(contentId);
	                 try {
	                     imagePart.attachFile(imageFilePath);
	                 } catch (IOException ex) {
	                     ex.printStackTrace();
	                 }
	                 multipart.addBodyPart(imagePart);
	             }
	         }
	         message.setContent(multipart);
	        // message.setContent(messageString, "text/html; charset=utf-8");
	        
	         
	         // Send message
	         logger.info("before sending");
	         Transport.send(message);
	         logger.info("after sending");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      
	   }
   
    
	public static void sendOffBoardingReport(Employee emp, String messageString, String msgSubject) throws Exception {
		
		// Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });


	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(emp.getEmail()));
	       //  message.addRecipient(Message.RecipientType.CC, new InternetAddress(emp.getEM().getEmail()));
	        /* message.addRecipient(Message.RecipientType.CC, new InternetAddress(emp.getSpocEmail()));*/

	         // Set Subject: header field
	         message.setSubject(msgSubject);

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(messageString);
	         
	         // Create a multi-part message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         message.setContent(messageString, "text/html; charset=utf-8");
	        
	     	MimeMessage message1 = new MimeMessage(session);
	     	message1.setContent
	        ("<h1>This is a test</h1>" 
	         + "</images/hermes.png\">", 
	         "text/html");
	         
	         // Send message
	     	 logger.info("before sending");
	         Transport.send(message);
	         logger.info("after sending");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
	public static void main(String[] firstName)
    {
    	StringBuffer bodyMsg = new StringBuffer();
    	bodyMsg.append("<div>");  // <BODY style="font-size:11pt;font-family:Calibri">whatever</BODY> 
    	bodyMsg.append("<font face=Calibri,sans-serif> <span style=font-size:11pt><font color=#1F497D></font>");
 
    	bodyMsg.append("<img src=hermes.png alt=image style=width:128px;height:128px>");
    	bodyMsg.append("Hi ABC, <br/><br/>");
    	//bodyMsg.append("<img src=\"cid:hermes.png\">");
		
    	/*bodyMsg.append("<img src=http://localhost:8080/onboarding/images/hermes.png alt=hermes.png height=118 width=241><br/><br/>");*/
    	bodyMsg.append("Welcome to the HERMES project!<br/><br/>");
    	bodyMsg.append("You should have received an email with your access identification. If you haven’t, please reach out to Priyanka Jaiswal at <a href=mailto:priyanka.a.jaiswal@capgemini.com?>priyanka.a.jaiswal@capgemini.com</a><br/><br/>");
    	bodyMsg.append("Here are some useful links to get started. These include an introduction to the PSA client and the HERMES program, on-boarding & setup procedures to follow as well as processes & tools used. Please read these carefully and contact your local manager if you have any queries.<br/><br/>");
    	
    	bodyMsg.append("<ol type=1>");
    	bodyMsg.append("<li><b>HERMES Academy:</b></li><br/>");
    	bodyMsg.append("To begin, please click <a href=http://frparhermesto/HERMES_Academy/index.html>HERMES Academy</a> which is our on-boarding & learning portal. Within this:");
    	bodyMsg.append("<ul>");
    	bodyMsg.append("<li>Please start with the <a href=http://frparhermesto/HERMES_Academy/overview.html> PSA Overview</a> tab and read through the client overview as well as the business area that you’re going to work on.</li>");
    	bodyMsg.append("<li>Post that, go to the  <a href=http://frparhermesto/HERMES_Academy/learning.html> Learning & Training</a> Hub which is a repository of learning & training courses for everyone in the team, organized into 4 tabs. Please start with the On-boarding tab. Within this:");
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
    	bodyMsg.append("User id = [your CORP ID in small-case]");
    	bodyMsg.append("Password = [your CORP ID in small-case]<br/><br/>");
    	bodyMsg.append("As first steps you will need to setup your desktop/laptop to be able to connect to client network. Please refer to this <a href=https://troom.capgemini.com> TROOM</a> link<br/>");
    	bodyMsg.append("<ol type=1>");
    	bodyMsg.append("<li>Ask your manager to raise an EXT ID request for the creation of PSA ID.Doc Name–EXT ID TEMPLATE</li>");
    	bodyMsg.append("<li>Set up to access PSA virtual machine.To set up VDI i.e.Doc name- HERMES-Connection to PSAlib via Vlan Port - India&nbsp;</li>");
    	bodyMsg.append("<li>If you face any issue related to VDI set up , network, software please raise an ITSM request Doc Name– HERMES- Raising ITSM ticket</li>");
    	bodyMsg.append("<li>If you want to install software in your VDI –Doc Name–HERMES Software installation on VDI</li>");
    	bodyMsg.append("<li>If you are required to be part of On-Call support / Connect to PSA outside Capgemini network Doc Name – On call connection process</li>");
    	bodyMsg.append("<li>To set up your system to be able to connect to client network outside Capgemini Network –Doc Name- HERMES – Connection to PSAlib for On-call Support</li>");
    	bodyMsg.append("</ol></ol>");
    	
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
    	bodyMsg.append("You can also use the search feature in the menu to search for specific areas.<br/>");
    	
    	bodyMsg.append("<ol start=4><li><b>TeamForge:</b></li><br/>");
    	bodyMsg.append("<a href=coconet2.capgemini.com>TeamForge</a>  is a central repository which is primarily used to store video recordings of all the knowledge sessions regarding applications & tools. Please use below username and password to access the same. Some of the links from the Academy will also take you to <a href=coconet2.capgemini.com>TeamForge</a>.<br/><br/>");
    	bodyMsg.append("User id = [your CORP ID in small-case]<br/>");
    	bodyMsg.append("Password = [your CORP ID in small-case]<br/><br/>");
    	bodyMsg.append("If that’s not working, please reach out to Priyanka Jaiswal at <a href=mailto:priyanka.a.jaiswal@capgemini.com?>priyanka.a.jaiswal@capgemini.com</a> or Muthu Konar at <a href=mailto:easikimuthu.konar@capgemini.com?>easikimuthu.konar@capgemini.com</a> to get access.");
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
    	bodyMsg.append("Note: Please do not reply to this email. In case you have queries, you can reach out to the HERMES PMO team pmohermes.in@capgemini.com");
    	bodyMsg.append("</div>");
    	bodyMsg.append("</font></span></font>");
    	bodyMsg.append("</div>");
    	
		
    	
    }
	public static void createEmailBodyForAll(List<Employee> empList,Employee emp, String messageString, String msgSubject) throws Exception {

	 
		// Get system properties
	      Properties properties = new Properties();//System.getProperties();

	      // Setup mail server
	    		  
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", port);
	      properties.setProperty("mail.smtp.user", username);
	      properties.setProperty("mail.smtp.password", password);
		
	   // Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });


	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         if(empList!= null && empList.size() > 0){
	         for(int i=0; i<empList.size(); i++){
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(empList.get(i).getEmail()));
				}
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(emp.getEmail()));

	       //  message.addRecipient(Message.RecipientType.CC, new InternetAddress(emp.getEM().getEmail()));
	        /* message.addRecipient(Message.RecipientType.CC, new InternetAddress(emp.getSpocEmail()));*/

	         // Set Subject: header field
	         message.setSubject(msgSubject);

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(messageString);
	         
	         // Create a multi-part message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         message.setContent(messageString, "text/html; charset=utf-8");
	        
	     	MimeMessage message1 = new MimeMessage(session);
	     	message1.setContent
	        ("<h1>This is a test</h1>" 
	         + "</images/hermes.png\">", 
	         "text/html");
	         
	         // Send message
	         Transport.send(message);
	         }}
	      catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	

	
 }
	



