package com.capgemini.onboarding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.EmployeeTraining;
import com.capgemini.onboarding.model.Grade;
import com.capgemini.onboarding.model.Training;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.GradeService;
import com.capgemini.onboarding.service.TechnologyService;
import com.capgemini.onboarding.service.TrainingService;

@Controller
public class TrainingController {

	private Logger logger = Logger.getLogger(TrainingController.class);
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired(required = true)
	private TechnologyService technologyService;

	@RequestMapping(value = { "/trainingForms" }, method = RequestMethod.GET)
	public String listTrainings(Model model) {
		model.addAttribute("training", new Training());
		model.addAttribute("listGrades", this.gradeService.listGrades(2));
		model.addAttribute("listTechnology", this.technologyService.listTechnology());
		return "trainingForm";
	}

	// For add and update Training both
	@RequestMapping(value = "/training/add", method = RequestMethod.POST)
	public String addTraining(@ModelAttribute("training") Training training, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// new Training, add it
		try {
			// String docPath = "http://"
			// + InetAddress.getLocalHost().getHostAddress()
			// + ":"
			// + request.getServerPort()
			// + request.getContextPath() +
			// "/resourceView/"+training.getTrainingId()+"/";
			
			List<Integer> gradeList = new ArrayList<Integer>();
			for (Grade g : training.getGrades()) {
				
				gradeList.add(g.getGradeId());
			}
			
			List<Employee> employeeList =  employeeService.getEmployeesByGrade(gradeList);
			
			List<EmployeeTraining> employeeTrainingList =  new ArrayList<EmployeeTraining>();
			for(Employee emp : employeeList) {
				EmployeeTraining et= new EmployeeTraining();
				et.setTraining(training);
				et.setEmployee(emp);
				et.setAllocationDate(new Date());
				employeeTrainingList.add(et);
			}
			
			training.setEmployeeTrainings(employeeTrainingList);
			this.trainingService.addTraining(training);

			redirectAttributes.addFlashAttribute("successMsg","Training saved successfully.");
		} catch (DataIntegrityViolationException e) {
			logger.error("Exceptions in addTraining " + e.getMessage(), e);
			model.addAttribute("errorMsg", "Resource entry already exists.");
			model.addAttribute("Training", training);
			return "trainingForm";
		} catch (Exception e) {
			logger.error("Exceptions in addTraining " + e.getMessage(), e);
			model.addAttribute("errorMsg", "Error while saving record.");
			model.addAttribute("Training", training);
			return "trainingForm";
		}
		model.addAttribute("msg", "Training Added Successfully ...");
		// existing Training, call update

		return "redirect:/trainingForms";
	}
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception{
        binder.registerCustomEditor(Set.class,"grades", new CustomCollectionEditor(Set.class){
            protected Object convertElement(Object element){
                if (element instanceof String) {
                    Grade grade = gradeService.getGradeById(Integer.parseInt(element.toString()));

                    return grade;
                }
                return null;
            }
        });
    }
}