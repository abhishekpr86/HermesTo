package com.capgemini.onboarding.activedirectory;

import java.util.ArrayList;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;


public class PCHostnameFromActiveDirectory{//dkaushik
	private Logger logger = Logger.getLogger(PCHostnameFromActiveDirectory.class);
	DirContext connection;
	public void newConnection() {
		// TODO Auto-generated method stub

		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "LDAP://" + "corp.capgemini.com");
		env.put(Context.SECURITY_PRINCIPAL,"CORP\\" + "SVC-FR-HERMESACCOUNT" );
		env.put(Context.SECURITY_CREDENTIALS, "n9giejA8CY42Q4");
		try {
			connection = new InitialDirContext(env);
		}
		catch(AuthenticationException ex) {
			ex.printStackTrace();
		}
		catch (NamingException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (Exception ex) {
			logger.error(" Some Problem occurs :- "+ex.getMessage());
			throw ex;
			}
	}
    public String getFilter(String distinguishedName) {
    	String searchFilter = "(&((&(objectClass=User)(manager=";
       	return searchFilter += distinguishedName+"))))";
    	
    }
	public String getUser(String distinguishedName) throws NamingException
{
	String searchFilter = getFilter(distinguishedName);
	logger.info(searchFilter);
	//String searchFilter = "(&((&(objectClass=User)(manager=CN=mehens,OU=IN-BLR-UNDEFINE,OU=IN,OU=Employees,DC=corp,DC=capgemini,DC=com))))";
	String[] reqAtt = {"name","manager"};
	SearchControls controls = new SearchControls();
	controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	controls.setReturningAttributes(reqAtt);
	NamingEnumeration users = connection.search("ou=workstations,ou=resources,dc=corp,dc=capgemini,dc=com", searchFilter, controls);
    SearchResult result = null;
    String res = null;
    String temp = null;
    ArrayList<String> al = new ArrayList<String>(); 
	while(users.hasMoreElements())
	{
		result = (SearchResult) users.next();
		Attributes attr = result.getAttributes();
		logger.info(attr.get("name").size());
		if(attr.get("name") != null) {
			temp = attr.get("name").toString();
			logger.info(temp);
			res = temp.substring(temp.indexOf(":")+1).trim();
			logger.info(res);
		}
		al.add(res);
	}
	if(al.size() == 0)
	{
		res = null;
	}else if(al.size() == 1) {
		res = al.get(0);
	}else if(al.size() > 1) {
		res = null;
	}
	logger.info(res);
	return res;
}
/*public static void main(String[] args) throws NamingException {
	PCHostnameFromActiveDirectory pcHostnameFromActiveDirectory= new PCHostnameFromActiveDirectory();
	pcHostnameFromActiveDirectory.newConnection();
	//pcHostnameFromActiveDirectory.getUser("abc");
}*/
	
	public ArrayList<String> getPcFromLDAP(String distinguishedName) throws NamingException {
		String searchFilter = getFilter(distinguishedName);
		logger.info(searchFilter);
		//String searchFilter = "(&((&(objectClass=User)(manager=CN=mehens,OU=IN-BLR-UNDEFINE,OU=IN,OU=Employees,DC=corp,DC=capgemini,DC=com))))";
		String[] reqAtt = {"name","manager"};
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(reqAtt);
		NamingEnumeration users = connection.search("ou=workstations,ou=resources,dc=corp,dc=capgemini,dc=com", searchFilter, controls);
	    SearchResult result = null;
	    String res = null;
	    String temp = null;
	    ArrayList<String> pcList = new ArrayList<String>(); 
		while(users.hasMoreElements())
		{
			result = (SearchResult) users.next();
			Attributes attr = result.getAttributes();
			//logger.info(attr.get("name").size());
			if(attr.get("name") != null) {
				temp = attr.get("name").toString();
				//logger.info(temp);
				res = temp.substring(temp.indexOf(":")+1).trim();
				logger.info("PC: "+res);
			}
			pcList.add(res);
		}
		
		return pcList;
	}

public void closeLdapConnection(){
    try {
        if(connection != null)
        	connection.close();
    }
    catch (NamingException e) {
    	e.printStackTrace();            
    }
}
}