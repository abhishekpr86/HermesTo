/**
 * The MIT License
 *
 * Copyright (c) 2010-2012 www.myjeeva.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package com.capgemini.onboarding.activedirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

import com.capgemini.onboarding.ReportController;

/**
 * Sample program how to use ActiveDirectory class in Java
 * 
 * @filename SampleUsageActiveDirectory.java
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam Madanagopal</a>
 * @copyright &copy; 2010-2012 www.myjeeva.com
 */
public class SampleUsageActiveDirectory {

	private static Logger logger = Logger.getLogger(SampleUsageActiveDirectory.class);
	
	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException, IOException {
		logger.info("\n\nQuerying Active Directory Using Java");
		logger.info("------------------------------------");
		String domain ="";
		String username = "";
		String password = "";
		String choice = "";
		String searchTerm = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		logger.info("Provide username & password for connecting AD");
		logger.info("Enter Domain:");			
		domain = br.readLine();
	/*	logger.info("Enter username:");			
		username = br.readLine();			
		logger.info("Enter password:");
		password = br.readLine();*/
		logger.info("Search by username or email:");
		choice = br.readLine();
		logger.info("Enter search term:");
		searchTerm = br.readLine();
		
		//Creating instance of ActiveDirectory
        ActiveDirectory activeDirectory = new ActiveDirectory("SVC-IN-HERMESMUM", "Tdbj@3020","corp.capgemini.com");
         
        //Searching
        NamingEnumeration<SearchResult> result = activeDirectory.searchUser(searchTerm, choice, null);
        
        if(result.hasMore()) {
			SearchResult rs= (SearchResult)result.next();
			Attributes attrs = rs.getAttributes();
			String temp = attrs.get("samaccountname").toString();
			logger.info("Username	: " + temp.substring(temp.indexOf(":")+1));
			temp = attrs.get("givenname").toString();
			logger.info("Name         : " + temp.substring(temp.indexOf(":")+1));
			temp = attrs.get("sn").toString();
			logger.info("sn	: " + temp.substring(temp.indexOf(":")+1));
			temp = attrs.get("mail").toString();
			logger.info("Email ID	: " + temp.substring(temp.indexOf(":")+1));
			temp = attrs.get("cn").toString();
			logger.info("Display Name : " + temp.substring(temp.indexOf(":")+1)); 
			temp = attrs.get("distinguishedName").toString();
			logger.info("Distinguished name	: " + temp.substring(temp.indexOf(":")+1) + "\n\n");
			
			
		} else  {
			logger.info("No search result found!");
		}

		//Closing LDAP Connection
        activeDirectory.closeLdapConnection();
	}
}
