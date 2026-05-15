package com.capgemini.onboarding;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.onboarding.model.Employee;

@Controller
public class ResourceListController {
	
	//private Logger logger = Logger.getLogger(ResourceListController.class);

	@RequestMapping(value = { /* "/", */"/resourceList" }, method = RequestMethod.GET)
	public String resourceList(Model model) {		
		model.addAttribute("employee", new Employee());		
		return "resourceList";
		
	}
}
