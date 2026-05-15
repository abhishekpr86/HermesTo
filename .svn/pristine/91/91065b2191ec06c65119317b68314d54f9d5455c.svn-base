package com.capgemini.onboarding;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.mail.MailSender;
import com.capgemini.onboarding.mail.MailUtility;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.model.Users;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.PreOnbEmployeeService;
import com.capgemini.onboarding.service.UsersService;
import com.capgemini.onboarding.util.CommonUtil;
import com.capgemini.onboarding.util.PsaMailUtility;


@Controller
public class RoleController {
	
	private Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired(required = true)
	private PreOnbEmployeeService preOnbEmpService;
	
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	@Autowired(required = true)
	private PsaMailUtility psaMailUtility;
	 
	@Autowired(required = true)
    private UsersService usersService;
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String passwordReset(Model model, HttpServletRequest request, String rdAllEmp) {
		logger.info("password reset : ");
		
		Users user = new Users();
		model.addAttribute("user", user);
		return "forgotPassword";
	}
	
	
	@RequestMapping(value = {  "/sendDefaultPasswordMail" }, method = RequestMethod.POST)
		public String resetPasswordSendMail(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, @ModelAttribute("user") Users user) {
		boolean doesExist = this.usersService.checkUserNameExists(user.getUserName(), null);
		String passwordSubj = "[Onboarding Tool] - Default password";
		if(doesExist) {
			
			user.setUserPassword(CommonUtil.getPasswordBcrypt("pass"));
			user.setIsFirstLogin(true);
			user.setEnabled(1);
			this.usersService.updateUsers(user);
			try {
				MailSender.send(this.psaMailUtility.prepareMailId((this.employeeService.getEmployeeByCorpId(user.getUserName()).getEmail())),MailUtility.defaultPasswordMail(),passwordSubj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			redirectAttributes.addFlashAttribute("successMsg", "Please check your mail");
		}else {
			redirectAttributes.addFlashAttribute("successMsg", "User does not exist please contact BIS PMO");
		}
		//return "login";
		
		return "redirect:/login";
	}
	
	 @RequestMapping(value = { /* "/", */"/role" }, method = RequestMethod.GET)
     public String roleDrpDwn(Model model, HttpServletRequest request, String rdAllEmp) throws IOException {
		 logger.info("role drop-down");
		 List<GrantedAuthority> authorities = (List<GrantedAuthority>)  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		 String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 List<Role> roleList = preOnbEmpService.roleList(username);
		 /*List<String> roleList = new ArrayList<>();
		 for (GrantedAuthority authority : authorities) {
			 roleList.add(authority.getAuthority().toString()); 
		 }*/
		 
		 //Check for first login - start
		 String isFirstLogin = "No";
		 if(preOnbEmpService.isFirstLogin(username)) {
			 isFirstLogin = "Yes";
		 }
		 
		 //Check for first login - end
		 model.addAttribute("isFirstLogin", isFirstLogin);
		model.addAttribute("roleList", roleList);
		logger.info("roleList : "+roleList);
		return "roleDropdown";
	 }
	 
	 
}
