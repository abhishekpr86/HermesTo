package com.capgemini.onboarding;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);
	

	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		
	model.addAttribute("error", "true");
	return "login";
	 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView login1(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("reset_password");

			return model;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("logout", model);
	}
	
	
	/*@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView("logout", model);
	}*/
	
	
}
