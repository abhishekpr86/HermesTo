package com.capgemini.onboarding.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.entity.ChangePasswordPojo;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.service.UserManagementService;
@Controller
public class UserManagementController {
	@Autowired
	private UserManagementService userManagementService;
	private static final Logger logger = Logger.getLogger(UserManagementController.class);
	
	private HttpSession session;
    private String role_id;
	
	@RequestMapping(value = "/changePassword.htm", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute("changePwd") ChangePasswordPojo changePasswordPojo,
			ModelAndView model, Principal user) {
		String newpassword = changePasswordPojo.getNewpassword();
		String oldPassword = changePasswordPojo.getOldPassword();
		try {
			int passwordUpdatedStatus = userManagementService.changeUserPassword(user.getName(), newpassword,
					oldPassword);
			if (passwordUpdatedStatus == 0) {
				model.addObject("passwordChangeStatus", "false");
			} else {
				model.addObject("passwordChangeStatus", "true");
			}

		} catch (Exception e) {
			logger.error(" Some Problem occurs :- "+e.getMessage());
			logger.error("Error occured in changing user password");
			model.addObject("passwordChangeStatus", "false");
		}
		model.setViewName("changePasswordSuccess");
		return model;
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView changePasswordRedirect(HttpServletRequest request,Model model) {

		Employee emp = new Employee();
		//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		session = request.getSession();
		role_id = (String) session.getAttribute("RoleName");
		
		if (role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
			emp.setUserReadOnly(true);
			model.addAttribute("checkUserType", "ViewMode");
		
		}
		else if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
			emp.setUserManagement(true);
			model.addAttribute("checkUserTypeforUM", "UserManagement");

			}
		else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
			emp.setEMReadOnly(true);//EM
  			model.addAttribute("checkUserType", "BundleEM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
  			  
  			model.addAttribute("checkUserType", "RM");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
  			  
  			model.addAttribute("checkUserType", "RM_PMO");
  		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
  			model.addAttribute("checkUserType", "ASL");
  		  }
		
		ChangePasswordPojo pojo=new ChangePasswordPojo();
		return new ModelAndView("main","changePwd",pojo);
	}
	
	@RequestMapping(value = {  "/", "/main" }, method = RequestMethod.POST)  //Bhavna
		public ModelAndView changePasswordRedirect(Model model, HttpServletRequest request, String rdAllEmp, @ModelAttribute("role") Role role) {
		try {
     	   logger.info("dashboard Role" +role.getRole_id());
     	   session = request.getSession();
     	   session.setAttribute("RoleName", role.getRole_id());
     	   
     	  model.addAttribute("checkUserType", "FirstLogin");
  		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(" Some Problem occurs :- "+e.getMessage());
		}
		ChangePasswordPojo pojo=new ChangePasswordPojo();
		return new ModelAndView("main","changePwd",pojo);
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session,RedirectAttributes attributes,ModelAndView model) {
		//attributes.addFlashAttribute("msg", "You have logout successfully");
		session.invalidate();
		model.setViewName("logout");
		return model;
	}
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView wrongUrl(HttpSession session,ModelAndView model) {
		session.invalidate();
		model.setViewName("error");
		return model;
	}
}
