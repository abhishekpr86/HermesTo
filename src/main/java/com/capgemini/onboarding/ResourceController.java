package com.capgemini.onboarding;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.model.Training;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.EmployeeTrainingService;
import com.capgemini.onboarding.service.TrainingService;

@Controller
public class ResourceController {

	private Logger logger = Logger.getLogger(ResourceController.class);

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired(required = true)
	private TrainingService trainingService;

	@Autowired(required = true)
	private EmployeeTrainingService employeeTrainingService;
	
	@RequestMapping("/resourceView/{empId}")
	public String resourceView(@PathVariable("empId") String empId, Model model, HttpServletRequest request) {
		Training training = null;
		String trainingName = null;
		int trainingCount = 0;
		int totalTrainingCount = 0;
		String docUrl = null;
		String docPath = null;

		List<Training> trainingList = trainingService.getEmpTrainingList(empId);
		if (trainingList != null && !trainingList.isEmpty()) {
			training = trainingList.get(0);
			trainingCount = trainingList.indexOf(training) + 1;
			totalTrainingCount = trainingList.size();
			trainingName = training.getName();

			if (training != null) {
				docUrl = training.getDocURL();

				try {
					docPath = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getServerPort()
							+ request.getContextPath() + docUrl;
				} catch (Exception e) {
					logger.error(" Some Problem occurs :- "+e.getMessage());
				} /*
					 * catch (UnknownHostException e) { logger.error(" Some Problem occurs :- "+e.getMessage()); }
					 */

			}
			model.addAttribute("trainingName", trainingName);
			model.addAttribute("trainingCount", trainingCount);
			model.addAttribute("totalTrainingCount", totalTrainingCount);
			model.addAttribute("empId", empId);
			model.addAttribute("docUrl", docPath);
		}else{
			model.addAttribute("message",
					"Congratulations! You have successfully completed all the security trainings currently assigned to you.");
		}
		
		return "resourceView";
	}

	@RequestMapping("/resourceViewSuccess")
	public String resourceViewSuccess(Model model) {
		// model.addAttribute("message","Data saved successfully.");
		return "resourceView";
	}

	@RequestMapping(value = "/resourceView/{empId}/", params = "btnSubmitAndExit")
	public String submitAndExit(@PathVariable("empId") String empId, Model model, RedirectAttributes redirectAttributes) {
		int totalTrainingCount = 0;
		Training training = null;
		List<Training> trainingList = trainingService.getEmpTrainingList(empId);
		if (trainingList != null && !trainingList.isEmpty()) {
			training = trainingList.get(0);
			if (training != null) {
				totalTrainingCount = trainingList.size();
				//Employee emp = this.employeeService.getEmployeeById(empId);
				//this.employeeService.updateEmployee(emp);
				employeeTrainingService.updateAttendedDate(new Date(), training.getTrainingId(), empId);
				totalTrainingCount = totalTrainingCount - 1;
			}
		}
		if (totalTrainingCount > 0) {
			redirectAttributes.addFlashAttribute("message", "You still have " + totalTrainingCount
					+ " training(s) remaining to complete. Please complete them by using the link on the email.");
		} else {
			redirectAttributes.addFlashAttribute("message",
					"Congratulations! You have successfully completed all the security trainings currently assigned to you.");
		}
		model.addAttribute("totalTrainingCount", totalTrainingCount);
		return "redirect:/resourceViewSuccess";
	}

	@RequestMapping(value = "/resourceView/{empId}/", params = "btnSubmitAndNext")
	public String submitAndNext(@PathVariable("empId") String empId, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		Training training = null;
		String trainingName = null;
		int trainingCount = 0;
		int totalTrainingCount = 0;
		String docUrl = null;
		String docPath = null;
		Training nextTraining = null;

		List<Training> trainingList = trainingService.getEmpTrainingList(empId);
		if (trainingList != null && !trainingList.isEmpty()) {
			training = trainingList.get(0);

			if (training != null) {
				employeeTrainingService.updateAttendedDate(new Date(), training.getTrainingId(), empId);

				List<Training> nexttrainingList = trainingService.getEmpTrainingList(empId);
				if (nexttrainingList != null && !nexttrainingList.isEmpty()) {
					nextTraining = nexttrainingList.get(0);
					docUrl = nextTraining.getDocURL();
					trainingCount = nexttrainingList.indexOf(nextTraining) + 1;
					totalTrainingCount = nexttrainingList.size();
					trainingName = nextTraining.getName();

					try {
						docPath = "http://" + InetAddress.getLocalHost().getHostAddress() + ":"
								+ request.getServerPort() + request.getContextPath() + docUrl;
					} catch (Exception e) {
						logger.error(" Some Problem occurs :- "+e.getMessage());
					} /*
						 * catch (UnknownHostException e) { logger.error(" Some Problem occurs :- "+e.getMessage());
						 * }
						 */

				}
			}
			model.addAttribute("trainingName", trainingName);
			model.addAttribute("trainingCount", trainingCount);
			model.addAttribute("totalTrainingCount", totalTrainingCount);
			model.addAttribute("empId", empId);
			model.addAttribute("docUrl", docPath);
			model.addAttribute("isAlreadySaved", false);
		}
		if (totalTrainingCount <= 0) { 
			model.addAttribute("message",
					"Congratulations! You have successfully completed all the security trainings currently assigned to you.");
		}
		
		return "resourceView";
		//return "redirect:/resourceViewSuccess";
	}

	@RequestMapping(value = "/resourceView/{empId}/", params = "btnCancel")
	public String cancel(@PathVariable("empId") int id, Model model, RedirectAttributes redirectAttributes) {
		// this.employeeService.updateEmployee(emp);
		redirectAttributes.addFlashAttribute("successMsg", "Training Cancelled");
		return "redirect:/employees";
	}

	@RequestMapping("/resourceView/{empId}/trainingDetails")
	private String getTrainingDetails(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("trainingDocuments", trainingService.getTrainingList(empId));
		model.addAttribute("empId", empId);
		return "training";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
