package com.capgemini.onboarding.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.capgemini.onboarding.dto.EmailDTO;
import com.capgemini.onboarding.dto.IBaseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Component
public class PsaMailUtility {

	private HashMap<?, ?> mailConfig = null;
	private HashMap<?, ?> mailTemplate = null;
	private HashMap<?, ?> mailDetails = null;

	private String username = null;
	private String password = null;
	private String from = null; // Sender's email ID needs to be mentioned
	private String host = null; // old "ismtp.corp.capgemini.com"; // Assuming you are sending email from
								// localhost
	private String port = null;
	private String testMailId = null;
	private String tesSheduletMailId = null;
	private Properties properties = null;
	private PasswordAuthentication pswAuth = null;
	private static final Logger logger = Logger.getLogger(PsaMailUtility.class);
			
	public PsaMailUtility() {
		
		logger.info("Inside mail utility start");
				
		
				
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			File file = ResourceUtils.getFile("classpath:PSA_Config.yml");
			//Files.readAllLines(path, StandardCharsets.UTF_8);
			this.mailConfig = (HashMap<?, ?>) mapper.readValue(file, Map.class);
			this.mailTemplate = (HashMap<?, ?>) this.mailConfig.get("template");
			if(null != mailConfig.get("testMailId")) {
				this.testMailId = ""+mailConfig.get("testMailId");
			}
			if(null != mailConfig.get("testSheduleMailId")) {
				this.tesSheduletMailId = ""+mailConfig.get("testSheduleMailId");
			}
			
			// Get system properties
			properties = new Properties();// System.getProperties();

			this.username = "" + mailConfig.get("username");
			this.password = "" + mailConfig.get("password");
			this.from = "" + mailConfig.get("from");
			this.host = "" + mailConfig.get("host");
			this.port = "" + mailConfig.get("port");
			// Setup mail server

			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.host", this.host);
			properties.put("mail.smtp.port",this.port);
			properties.setProperty("mail.smtp.user", this.username);
			properties.setProperty("mail.smtp.password", this.password);
			
			 pswAuth = new PasswordAuthentication(this.username,this.password);
			 
			 logger.info("Inside mail utility end");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}

	}
	
	public void sendDynamicWelcomeEmail(IBaseDTO poMailInfo,Map<String, String> mapInlineImages) {
		try {
		logger.info("sendDynamicWelcomeEmail start");
		
		EmailDTO mailInfo = (EmailDTO)poMailInfo;
		String msgSubject = null;
		String messageString = null;
		String templateId = "DEFAULT";

		
		ArrayList<String> toList = mailInfo.getToList();
		ArrayList<String> ccList = mailInfo.getCcList();
		HashMap<String, String> paramValue = mailInfo.getParamValue();
		templateId = paramValue.get("MAILTEMPID");  
		if(templateId.isEmpty()) {
			templateId = "DEFAULT";
		}
		
		logger.info("sendDynamicWelcomeEmail "+this.testMailId+" ccList "+ccList+" toList "+toList+" templateId "+templateId);
		if(!(this.testMailId.isEmpty())) {
			toList.clear();
			//ccList.clear();
			toList.add(this.testMailId);
			//ccList.add(this.testMailId);
		}
		logger.info("sendDynamicWelcomeEmail 5");
		mailDetails = (HashMap<?, ?>)mailTemplate.get(templateId);
		logger.info("mailDetails "+mailDetails);
		messageString = ""+mailDetails.get("body");
		msgSubject = ""+mailDetails.get("subject");
		
		messageString = replaceParam(paramValue,messageString);
		msgSubject = replaceParam(paramValue,msgSubject);
		logger.info("after messageString msgSubject"+messageString+" "+msgSubject);
		
		// Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return pswAuth;
	            }
	         });
		
	      logger.info("sendDynamicWelcomeEmail "+session);
	      
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          toList.forEach(item->{
	      		if(null!= item && item.trim().length() > 0 ) {
	      			 try {
	      				 logger.info("send TO mail  "+item); 
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(item));
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
	      		}
	          });
	         
	       // Set To: header field of the header.
	          ccList.forEach(item->{
	      		if(null!= item && item.trim().length() > 0 ) {
	      			 try {
	      				 logger.info("send CC mail  "+item); 
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(item));
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
	      		}
	          });
	          

	          // Set Subject: header field
	          message.setSubject(msgSubject);
	          message.setHeader("X-Priority", "1");
		      // Create the message part 
		      BodyPart messageBodyPart = new MimeBodyPart();

		      messageBodyPart.setContent(messageString, "text/html");
	          
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
		      
	          // Send message
		      logger.info("before Sending");
	          Transport.send(message);
	          logger.info("Mail Sent");
	       }catch (MessagingException mex) {
	         // mex.printStackTrace();
	    	   logger.error(" Some Problem occurs on PSAMailUtility:- "+mex.getMessage());
	       }
		
	
	}

	public void senMail(IBaseDTO poMailInfo) {
		
		EmailDTO mailInfo = (EmailDTO)poMailInfo;
		String msgSubject = null;
		String messageString = null;
		String templateId = "DEFAULT";

		
		ArrayList<String> toList = mailInfo.getToList();
		ArrayList<String> ccList = mailInfo.getCcList();
		HashMap<String, String> paramValue = mailInfo.getParamValue();
		templateId = paramValue.get("MAILTEMPID");  
		if(templateId.isEmpty()) {
			templateId = "DEFAULT";
		}
		
		
		if(!(this.testMailId.isEmpty())) {
			toList.clear();
			ccList.clear();
			toList.add(this.testMailId);
			ccList.add(this.testMailId);
		}
		
		mailDetails = (HashMap<?, ?>)mailTemplate.get(templateId);
		messageString = ""+mailDetails.get("body");
		msgSubject = ""+mailDetails.get("subject");
		
		messageString = replaceParam(paramValue,messageString);
		msgSubject = replaceParam(paramValue,msgSubject);
		
		// Setup authentication, get session
	      Session session = Session.getInstance(properties,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return pswAuth;
	            }
	         });
	      try{
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          toList.forEach(item->{
	      		if(null!= item && item.trim().length() > 0 ) {
	      			 try {
	      				 logger.info("send TO mail  "+item); 
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(item));
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
	      		}
	          });
	         
	       // Set To: header field of the header.
	          /*ccList.forEach(item->{
	      		if(null!= item && item.trim().length() > 0 ) {
	      			 try {
	      				 logger.info("send CC mail  "+item); 
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(item));
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
	      		}
	          });*/
	          

	          // Set Subject: header field
	          message.setSubject(msgSubject);
	          
	          // Create the message part 
	          BodyPart messageBodyPart = new MimeBodyPart();

	          // Fill the message
	          messageBodyPart.setText(messageString);
	          logger.info("message body  "+messageString);
	          messageBodyPart.setFileName("hermes.png");
	          
	          // Create a multi-part message
	          Multipart multipart = new MimeMultipart();

	          // Set text message part
	          multipart.addBodyPart(messageBodyPart);
	          message.setContent(messageString, "text/html; charset=utf-8");
	          
	          // Send message
	          logger.info("before sending");
	          Transport.send(message);
	          logger.info("Mail Sent");
	       }catch (MessagingException mex) {
	         // mex.printStackTrace();
	    	   logger.error(" Some Problem occurs :- "+mex.getMessage());
	       }
		
	}

	private String replaceParam(HashMap<String,String> poParamList,String poInput) {
		String replace = null;
		//poInput = poInput.replaceAll("~EM~", "Girija");
		Iterator<String> itr = poParamList.keySet().iterator();
		String key = null;
		String value = null;
		while (itr.hasNext()) 
		{
			key = itr.next();
			value = poParamList.get(key);
			poInput=poInput.replaceAll("~"+key+"~", value);			
		}
		logger.info(poInput);
		
		return poInput;

	}
	
	public String prepareMailId(String poMailId) {
		// return poMailId;
		if(null != this.testMailId && this.testMailId.trim().length() > 0) {
			 return this.testMailId;
		}else {
			 return poMailId;
		}	
       
  }
  
  public String[] prepareMailId(String[] poMailId) {
        String[] mailTo = new String[1];
        mailTo[0] = this.testMailId;
        
        if(null != this.testMailId && this.testMailId.trim().length() > 0) {                   
               return mailTo;
        }
        return poMailId;
  }

  public String prepareSheduleMailId(String poMailId) {
      if(null != this.tesSheduletMailId && this.tesSheduletMailId.trim().length() > 0) {                   
             return this.tesSheduletMailId;
      }
      return poMailId;
}

public String[] prepareSheduleMailId(String[] poMailId) {
      String[] mailTo = new String[1];
      mailTo[0] = this.tesSheduletMailId;
      
      if(null != this.tesSheduletMailId && this.tesSheduletMailId.trim().length() > 0) {                  
             return mailTo;
      }
      return poMailId;
}


}
