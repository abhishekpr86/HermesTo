package com.capgemini.onboarding.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Service
public class MicrosoftFlowRest {
	
	private Logger logger = Logger.getLogger(MicrosoftFlowRest.class);
	
	//@RequestMapping(value = "/restCall", method = RequestMethod.GET)
	public void POSTRequest(String URL, String empEmail) throws IOException {
	    final String POST_PARAMS = "{\n" + "\"user\": \""+empEmail+"\",\r\n" + "\n}";
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(URL);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	   // postConnection.setRequestProperty("userId", "bmanjrek");
	    postConnection.setRequestProperty("Content-Type", "application/json");
	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
	    if (responseCode == HttpURLConnection.HTTP_ACCEPTED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();
	        // print result
	        System.out.println(response.toString());
	        logger.info("Microsoft POST Call Success"+empEmail);
	    } else {
	    	logger.info("Microsoft POST CALL NOT WORKED "+empEmail);
	        System.out.println("POST NOT WORKED"); //HTTP_BAD_REQUEST - RM PMO
	    }
	}
	
	
}
