package com.capgemini.onboarding;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import javax.imageio.ImageIO;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.security.core.authority;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.service.BisService;
import com.capgemini.onboarding.service.CountryService;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.GlobalGradeService;

import ChartDirector.Chart;
import ChartDirector.LegendBox;
import ChartDirector.PyramidChart;

@Controller
/*@SessionAttributes("RoleName")*/
public class DashboardController {

       private Logger logger = Logger.getLogger(DashboardController.class);

       /*@Autowired
   	   private SessionFactory sessionFactory;*/
       
       @Autowired(required = true)
       private EmployeeService employeeService;

       @Autowired(required = true)
   	   private BisService bisService;
       
       @Autowired(required = true)
       private CountryService countryService;
       
       @Autowired(required = true)
       private GlobalGradeService globalGradeService;
       
       private HttpSession session;

       ArrayList<Integer> arrayListOfEmployeeSize = new ArrayList<Integer>();
       int rangeOfXAxis = 0;
       
       @RequestMapping(value = {  "/", "/dashboard" }, method = RequestMethod.GET)
       public String dashBoard(Model model, HttpServletRequest request, String rdAllEmp) throws IOException {
    	   listAllEmployeesChart(model, request, OnboardingConstants.EMP_RESOURCE_ALL);
    	   listActiveEmployeesChart(model, request, OnboardingConstants.EMP_RESOURCE_ACTIVE);
    	   listBillableEmployeeChart(model, request, OnboardingConstants.EMP_RESOURCE_BILLABLE);
			model.addAttribute("listCountry", this.countryService.listCountry());   
			createPyramidChart(model,request);
			getTotalBISListData(model, request, OnboardingConstants.EMP_RESOURCE_ACTIVE);//mehens-BIS
			model.addAttribute("dashboard", new Employee());
			 return "dashboard";
       }
       
       @RequestMapping(value = {  "/", "/dashboard" }, method = RequestMethod.POST)
   		public String dashBoard(Model model, HttpServletRequest request, String rdAllEmp, @ModelAttribute("role") Role role) {
    	   //Employee emp =new Employee();
     /* List<Employee> empcheck = employeeService.getDataInconsistenciesOffboardingReport();
      logger.info(empcheck);*/
    	  /* int country_id = 0;
    	   int bis_id = 0;
    	   
    	   if(emp.getCountry()!= null){
    		   country_id = emp.getCountry().getCountryId();
    	   }
    	   if(emp.getBis()!=null){
    		   bis_id = emp.getBis().getBis_id();
    	   }
    	   List<Employee> empCheck = employeeService.emailForIndiaEmployee(country_id, bis_id);
    	   logger.info(empCheck);*/
    	   
           try {
        	   logger.info("dashboard Role" +role.getRole_id());
        	   session = request.getSession();
        	   session.setAttribute("RoleName", role.getRole_id());
        	   //this.getRoleName(role);
				listAllEmployeesChart(model, request, OnboardingConstants.EMP_RESOURCE_ALL);//OnboardingConstants.True
				listActiveEmployeesChart(model, request, OnboardingConstants.EMP_RESOURCE_ACTIVE);//OnboardingConstants.False
				listBillableEmployeeChart(model, request, OnboardingConstants.EMP_RESOURCE_BILLABLE);//true
				model.addAttribute("listCountry", this.countryService.listCountry()); 
				createPyramidChart(model,request);
				//listActiveEmployeesByBIS(model, request, OnboardingConstants.EMP_RESOURCE_ACTIVE);//mehens
				getTotalBISListData(model, request, OnboardingConstants.EMP_RESOURCE_ACTIVE);
				model.addAttribute("dashboard", new Employee());
				
           } catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(" Some Problem occurs :- "+e.getMessage());
			}
            
           return "dashboard";
       }
       
       /*@ModelAttribute("RoleName")
       public String getRoleName(Role role) {
    	   return role.getRole_name();
       }*/
       
       private void getTotalBISListData(Model model, HttpServletRequest request, String empResourceActive)throws IOException {
    	   List<Bis> fullListOfBis = this.bisService.fullListOfBis();
    	   model.addAttribute("fullListOfBis",fullListOfBis);
	}

	@GetMapping("/getBISData")
	public List<Bis> getBISData() {
    	   List<Bis> fullListOfBis = this.bisService.fullListOfBis();
    	   return fullListOfBis;
       }
       
	
       @RequestMapping(value = "/getEmployeeData", method = RequestMethod.GET)
       public @ResponseBody List<Employee> getBISActiveEmpList(@RequestParam (value="drpId", required = true) int drpId) throws IOException {
		//new method for BIS Filter
   		/*String queryString = "SELECT country_id, SUM(CASE WHEN emp_type = 'Internal' THEN 1 ELSE 0 END), "
   	            + "SUM(CASE WHEN emp_type = 'External' THEN 1 ELSE 0 END), COUNT(*) "
   	            + "FROM employee WHERE emp_id != 'NA' AND bis_id = :bisId AND "
   	            + "(actual_end_date IS NULL OR actual_end_date >= CURRENT_DATE) "
   	            + "GROUP BY country_id WITH ROLLUP"
   	            + "HAVING country_id IS NOT NULL OR internal_count IS NOT NULL OR external_count IS NOT NULL";
   		TypedQuery<Employee> query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
   	    query.setParameter("bisId", drpId);
   	    List<Employee> employeeList = query.getResultList();
    	List<Employee> employeeList  = employeeService.getBISActiveEmpList(drpId);   
    	logger.info("size of employeeList "+employeeList.size());
   	    for (Employee emp : employeeList) {
   	        if (emp.getCountryNameDashBoard() != null) {
   	            if (emp.getCountryNameDashBoard().equals("0")) {
   	                emp.setCountryNameDashBoard("Total");
   	            } else {
   	                Country countryById = this.countryService.getCountryById(Integer.parseInt(emp.getCountryNameDashBoard()));
   	                emp.setCountryNameDashBoard(countryById.getCountryName());
   	            }
   	        } else {
   	            emp.setCountryNameDashBoard("Grand Total");
   	        }
   	    }*/
    	   
	    	List<Employee> employeeList  = employeeService.getBISActiveEmpList(drpId);   
	       	logger.info("size of employeeList "+employeeList.size());   
	       	List<Employee> dashActiveBISList = new ArrayList<>();
	        Iterator itr = employeeList.iterator();
	        while (itr.hasNext()) {
                Employee emp = new Employee();
                Object[] obj = (Object[]) itr.next();
                String countryVal = String.valueOf(obj[0]);
                if (!countryVal.equalsIgnoreCase("0")) {
                      Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                      emp.setCountryNameDashBoard(countryById.getCountryName());
                } else {
                      emp.setCountryNameDashBoard("Total");
                }
                //emp.setTotalInternal(Integer.parseInt(String.valueOf(obj[1])));
                //emp.setTotalExtrnal(Integer.parseInt(String.valueOf(obj[2])));
                if (obj[1] == null)
              	  emp.setTotalInternal(0);
                else
                emp.setTotalInternal(Integer.parseInt(String.valueOf(obj[1])));
                
                if (obj[2] == null)
              	  emp.setTotalExtrnal(0);  
                else  
                emp.setTotalExtrnal(Integer.parseInt(String.valueOf(obj[2])));
                
                emp.setTotalEmployee(Integer.parseInt(String.valueOf(obj[3])));
                dashActiveBISList.add(emp);
         }   
	      logger.info("dashActiveBISList size: "+dashActiveBISList.size());
   	    return dashActiveBISList;
       }

	public void listBillableEmployeeChart(Model model, HttpServletRequest request, String rdBillableEmp) {
    	   
    	   Employee e = new Employee();
    	   String folderPath = request.getSession().getServletContext().getRealPath("/resources/images");
    	   List<Employee> totalBillableEmpCountrywise = employeeService.getBillableEmployeesCountryWise();
           generateTotalResourceBarChart(totalBillableEmpCountrywise, folderPath, rdBillableEmp);
           List<Employee> internalBillableEmpCountrywise = employeeService.getBillableInternalEmployeesCountryWise();
           generateInternalResourceBarChart(internalBillableEmpCountrywise, folderPath, rdBillableEmp);
           List<Employee> externalBillableEmpCountrywise = employeeService.getBillableExternalEmployeesCountryWise();
           generateExternalResourceBarChart(externalBillableEmpCountrywise, folderPath, rdBillableEmp);
           List<Employee> dashboardBillableList = employeeService.getDashBoardBillableEmpList();
           List<Employee> dashBillableList = new ArrayList<>();
           Iterator itr = dashboardBillableList.iterator();
           while (itr.hasNext()) {
               Employee emp = new Employee();
               Object[] obj = (Object[]) itr.next();
               String countryVal = String.valueOf(obj[0]);
               if (!countryVal.equalsIgnoreCase("0")) {
                     Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                     emp.setCountryNameDashBoard(countryById.getCountryName());
               } else {
                     emp.setCountryNameDashBoard("Total");
               }
               emp.setTotalInternal(Integer.parseInt(String.valueOf(obj[1])));
               emp.setTotalExtrnal(Integer.parseInt(String.valueOf(obj[2])));
               emp.setTotalEmployee(Integer.parseInt(String.valueOf(obj[3])));
               dashBillableList.add(emp);
        }
        model.addAttribute("dashboardBillableList", dashBillableList);
        logger.info(model.asMap());
       }
       
       public void listAllEmployeesChart(Model model, HttpServletRequest request, String rdAllEmp) throws IOException {
              Employee e = new Employee();
              model.addAttribute("dashboard", e);
              model.addAttribute("listCountry", this.countryService.listCountry()); 
              String folderPath = request.getSession().getServletContext().getRealPath("/resources/images");
              List<Employee> totalEmpCountrywise = employeeService.getAllEmployeesByCountry(); // 
              generateTotalResourceBarChart(totalEmpCountrywise, folderPath, rdAllEmp);
              List<Employee> internalEmpCountrywise = employeeService.getInternalEmployeesCountryWise();
              generateInternalResourceBarChart(internalEmpCountrywise, folderPath, rdAllEmp);
              List<Employee> externalEmpCountrywise = employeeService.getExternalEmployeesCountryWise();
              generateExternalResourceBarChart(externalEmpCountrywise, folderPath, rdAllEmp);
              List<Employee> dashboardList = employeeService.getDashBoardList();
              List<Employee> dashList = new ArrayList<>();
              Iterator itr = dashboardList.iterator();
              while (itr.hasNext()) {
                     Employee emp = new Employee();
                     Object[] obj = (Object[]) itr.next();
                     String countryVal = String.valueOf(obj[0]);
                     if (!countryVal.equalsIgnoreCase("0")) {
                           Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                           emp.setCountryNameDashBoard(countryById.getCountryName());
                     } else {
                           emp.setCountryNameDashBoard("Total");
                     }
                     emp.setTotalInternal(Integer.parseInt(String.valueOf(obj[1])));
                     emp.setTotalExtrnal(Integer.parseInt(String.valueOf(obj[2])));
                     emp.setTotalEmployee(Integer.parseInt(String.valueOf(obj[3])));
                     
                     
                     dashList.add(emp);
              }
              //String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
             /* List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority> (SecurityContextHolder.getContext().getAuthentication().getAuthorities());
              authorities.clear();
              authorities.add(new GrantedAuthorityImpl("ROLE_2"));*/
              session = request.getSession();
              String role_id = (String) session.getAttribute("RoleName");
              if(role_id.equalsIgnoreCase(OnboardingConstants.ReadOnlyUsers)) {
                  e.setUserReadOnly(true);
                  model.addAttribute("checkUserType", "ViewMode");
              }else if(role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
      			  model.addAttribute("checkUserTypeforUM", "UserManagement");
      			  e.setUserManagement(true);
      		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.Bundle_EM)) {
      			  e.setEMReadOnly(true);//EM
      			  model.addAttribute("checkUserType", "BundleEM");
      		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM)) {
      			  model.addAttribute("checkUserType", "RM");
      		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.RM_PMO)) {
      			  model.addAttribute("checkUserType", "RM_PMO");
      		  }else if(role_id.equalsIgnoreCase(OnboardingConstants.ASL)) {
      			  model.addAttribute("checkUserType", "ASL");
      		  }
              
              model.addAttribute("dashboardList", dashList);
       }


       @RequestMapping(value = "/checkEmployeeType", method = RequestMethod.GET)
       public @ResponseBody boolean checkEmployeeType(@RequestParam(value = "rdAllEmp", required = true) String rdAllEmp,
                     @RequestParam(value = "rdActiveEmp", required = true) String rdActiveEmp, Model model,
                     HttpServletRequest request) {
                           boolean check = true;             
                           try {
                                  listAllEmployeesChart(model, request, OnboardingConstants.True);
                                  listActiveEmployeesChart(model, request, OnboardingConstants.False);
                           } catch (IOException e) {
                                  logger.error(" Some Problem occurs :- "+e.getMessage());
                           }
                           
                           return check;
       }

       public void listActiveEmployeesChart(Model model, HttpServletRequest request, String rdActiveEmp)
                     throws IOException {
              Employee e = new Employee();
              model.addAttribute("dashboard", e);
              model.addAttribute("listCountry", this.countryService.listCountry());
              String folderPath = request.getSession().getServletContext().getRealPath("/resources/images");
              List<Employee> totalEmpCountrywise = employeeService.getActiveEmployeesCountryWise();
              generateTotalResourceBarChart(totalEmpCountrywise, folderPath, rdActiveEmp);
              List<Employee> internalEmpCountrywise = employeeService.getActiveInternalEmployeesCountryWise();
              generateInternalResourceBarChart(internalEmpCountrywise, folderPath, rdActiveEmp);
              List<Employee> externalEmpCountrywise = employeeService.getActiveExternalEmployeesCountryWise();
              generateExternalResourceBarChart(externalEmpCountrywise, folderPath, rdActiveEmp);
              List<Employee> dashboardActiveList = employeeService.getDashBoardActiveEmpList();
              List<Employee> dashActiveList = new ArrayList<>();
              Iterator itr = dashboardActiveList.iterator();
              while (itr.hasNext()) {
                     Employee emp = new Employee();
                     Object[] obj = (Object[]) itr.next();
                     String countryVal = String.valueOf(obj[0]);
                     if (!countryVal.equalsIgnoreCase("0")) {
                           Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                           emp.setCountryNameDashBoard(countryById.getCountryName());
                     } else {
                           emp.setCountryNameDashBoard("Total");
                     }
                     emp.setTotalInternal(Integer.parseInt(String.valueOf(obj[1])));
                     emp.setTotalExtrnal(Integer.parseInt(String.valueOf(obj[2])));
                     emp.setTotalEmployee(Integer.parseInt(String.valueOf(obj[3])));
                     dashActiveList.add(emp);
              }
              model.addAttribute("dashboardActiveList", dashActiveList);
              logger.info(model.asMap());
       }

       public void generateTotalResourceBarChart(List<Employee> totalEmpCountrywise, String folderPath,
                     String empTypeDashboard) {
              try {
                     Iterator itr = totalEmpCountrywise.iterator();
                     DefaultCategoryDataset dataSetAllEmp = new DefaultCategoryDataset();
                     while (itr.hasNext()) {
                           Object[] obj = (Object[]) itr.next();
                           String count = String.valueOf(obj[0]);
                           arrayListOfEmployeeSize.add(Integer.parseInt(count));
                           String countryVal = String.valueOf(obj[1]);
                           Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                           dataSetAllEmp.setValue(Integer.parseInt(count), "", countryById.getCountryName());
                     }
                     JFreeChart chart = ChartFactory.createBarChart3D("Total Resources", "", "", dataSetAllEmp,
                                  PlotOrientation.VERTICAL, false, true, false);
                     CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

                     renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                     renderer.setBaseItemLabelsVisible(true);
                     ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
                     renderer.setBasePositiveItemLabelPosition(position);
                     // Changing the bar color
                     CategoryPlot plot = chart.getCategoryPlot();
                     
                     NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
                     rangeOfXAxis =  (((Collections.max(arrayListOfEmployeeSize) / 100 ) + 1 )*100);
                     rangeAxis.setRange(0, rangeOfXAxis);
                     
                     BarRenderer rndr = (BarRenderer) plot.getRenderer();
                     // set the color (r,g,b) or (r,g,b,a)
                     Color color = new Color(79, 129, 189);
                     rndr.setSeriesPaint(0, color);
                     rndr.setMaximumBarWidth(.20);

                     if (empTypeDashboard == null || empTypeDashboard.equals("")
                                  || empTypeDashboard.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ALL)) {
                           ChartUtilities.saveChartAsPNG(new File(folderPath + "\\totalResource.png"), chart, 350, 430);
                     } else if(empTypeDashboard.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)){
                           ChartUtilities.saveChartAsPNG(new File(folderPath + "\\totalActiveResource.png"), chart, 350, 430);
                     }else {
                    	 ChartUtilities.saveChartAsPNG(new File(folderPath + "\\totalBillableResource.png"), chart, 350, 430);
                     }

              } catch (IOException e1) {
                     System.err.println("Problem occurred creating chart.");
              }
       }

       public void generateInternalResourceBarChart(List<Employee> internalEmpCountrywise, String folderPath,
                     String empTypeDashboard) {

              logger.info(internalEmpCountrywise);
              Iterator itr = internalEmpCountrywise.iterator();
              DefaultCategoryDataset dataSetInternal = new DefaultCategoryDataset();
              while (itr.hasNext()) {
                     Object[] obj = (Object[]) itr.next();
                     String count = String.valueOf(obj[0]);
                     String countryVal = String.valueOf(obj[1]);
                     Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                     dataSetInternal.setValue(Integer.parseInt(count), "", countryById.getCountryName());
              }
              JFreeChart chart = ChartFactory.createBarChart3D("Internals", "", "", dataSetInternal, PlotOrientation.VERTICAL,
                           false, true, false);
              CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

              renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
              renderer.setBaseItemLabelsVisible(true);
              ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
              renderer.setBasePositiveItemLabelPosition(position);
              // Changing the bar color
              CategoryPlot plot = chart.getCategoryPlot();
              BarRenderer rndr = (BarRenderer) plot.getRenderer();
              
              NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
              rangeAxis.setRange(0, rangeOfXAxis);
              
              // set the color (r,g,b) or (r,g,b,a)
              Color color = new Color(79, 129, 189);
              rndr.setSeriesPaint(0, color);
              rndr.setMaximumBarWidth(.20);

              try {
                     if (empTypeDashboard == null || empTypeDashboard.equals("")
                                  || empTypeDashboard.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ALL)) {
                           ChartUtilities.saveChartAsPNG(new File(folderPath + "\\internalResource.png"), chart, 350, 430);
                     } else if(empTypeDashboard.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)){
                           ChartUtilities.saveChartAsPNG(new File(folderPath + "\\internalActiveResource.png"), chart, 350, 430);
                     }else {
                    	   ChartUtilities.saveChartAsPNG(new File(folderPath + "\\internalBillableResource.png"), chart, 350, 430);
                     }

              } catch (IOException e1) {
                     System.err.println("Problem occurred creating chart.");
              }
       }

       public void generateExternalResourceBarChart(List<Employee> externalEmpCountrywise, String folderPath,
                     String empTypeDashboard) {

              logger.info(externalEmpCountrywise);
              Iterator itr = externalEmpCountrywise.iterator();
              DefaultCategoryDataset dataSetExtrnl = new DefaultCategoryDataset();
              while (itr.hasNext()) {
                     Object[] obj = (Object[]) itr.next();
                     String count = String.valueOf(obj[0]);
                     String countryVal = String.valueOf(obj[1]);
                     Country countryById = this.countryService.getCountryById(Integer.parseInt(countryVal));
                     dataSetExtrnl.setValue(Integer.parseInt(count), "", countryById.getCountryName());
              }
              JFreeChart chart = ChartFactory.createBarChart3D("Externals", "", "", dataSetExtrnl, PlotOrientation.VERTICAL,
                           false, true, false);
              CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

              renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
              renderer.setBaseItemLabelsVisible(true);
              ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
              renderer.setBasePositiveItemLabelPosition(position);
              // Changing the bar color
              CategoryPlot plot = chart.getCategoryPlot();
              
              NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
              rangeAxis.setRange(0, rangeOfXAxis);
              
              BarRenderer rndr = (BarRenderer) plot.getRenderer();
              // set the color (r,g,b) or (r,g,b,a)
              Color color = new Color(79, 129, 189);
              rndr.setSeriesPaint(0, color);
              rndr.setMaximumBarWidth(.20);
              try {
                     if (empTypeDashboard == null || empTypeDashboard.equals("")
                                  || empTypeDashboard.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ALL)) {
                           ChartUtilities.saveChartAsPNG(new File(folderPath + "\\externalResource.png"), chart, 260, 430);
                     } else if(empTypeDashboard.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)){
                           ChartUtilities.saveChartAsPNG(new File(folderPath + "\\externalActiveResource.png"), chart, 260, 430);
                     }else {
                    	 ChartUtilities.saveChartAsPNG(new File(folderPath + "\\externalBillableResource.png"), chart, 260, 430);
                     }
              } catch (IOException e1) {
                     System.err.println("Problem occurred creating chart.");
              }
       }
       public void createPyramidChart(Model model, HttpServletRequest request){
              
              try {
                     createPyramidChartCountryWise(model,request,OnboardingConstants.France);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.India);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.China);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.Spain);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.Morocco);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.Germany);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.UK);
                     createPyramidChartCountryWise(model,request,OnboardingConstants.AllCountry);
              } catch (IOException e) {
                     // TODO Auto-generated catch block
                     logger.error(" Some Problem occurs :- "+e.getMessage());
              }
              
              
       }
       
       public String getGlobalGrade(String gradeId){
              String grade = null;
              GlobalGrade globalgrade = new GlobalGrade();
              if(gradeId!=null && !gradeId.equalsIgnoreCase("")){
                      globalgrade = globalGradeService.getGlobalGradeById(Integer.parseInt(gradeId));
                      grade = globalgrade.getName();
              }      
              return grade;
       }
       public void createPyramidChartCountryWise(Model model, HttpServletRequest request,int countryId) throws IOException {
              
              List<Employee> totalEmpCountrywise = employeeService.getPyramidChart(countryId);
              String folderPath = request.getSession().getServletContext().getRealPath("/resources/images");
              Iterator itr = totalEmpCountrywise.iterator();
              List gradeIDList = new ArrayList<>();
              List countValue = new ArrayList<>();
              double totalResourceCount = 0;
              while (itr.hasNext()) {
                     Object[] obj = (Object[]) itr.next();
                     String globalGradeId = String.valueOf(obj[0]);
                     String count = String.valueOf(obj[1]);
                     totalResourceCount = totalResourceCount + Double.parseDouble(count);
                     gradeIDList.add(globalGradeId);
                     countValue.add(count);
              }
              
              List<Employee> pyramidList = new ArrayList<>();
              Iterator itrgrid = totalEmpCountrywise.iterator();
              while (itrgrid.hasNext()) {
                     Employee emp = new Employee();
                     Object[] obj = (Object[]) itrgrid.next();
                     String gradeName = getGlobalGrade(String.valueOf(obj[0]));
                     emp.setGlobalGradePyramid(gradeName);
                     emp.setResourceCountGradeWise(Integer.valueOf(String.valueOf(obj[1])));
                     double countCountryWise= Double.parseDouble(String.valueOf(obj[1]));
                     int percentage = (int)((countCountryWise/totalResourceCount)*100);
//                     logger.info("before" +percentage);
              /*     DecimalFormat df2 = new DecimalFormat("###.##");
                     percentage = Double.valueOf(df2.format(percentage));
                     logger.info("percentage is :" + percentage);*/
                     emp.setPercentage(percentage+"%");
                     pyramidList.add(emp);
              }
              double[] dataCount = new double[7];
              for (int i = 0; i < countValue.size(); i++) {
                     double dataval = (Double.parseDouble((String) countValue.get(i)));
                     dataCount[i] = dataval;
              }
              String[] gradeId = new String[7];
              for (int i = 0; i < gradeIDList.size(); i++) {
                     String dataval = ((String) gradeIDList.get(i));
                     GlobalGrade global_grade_name = this.globalGradeService.getGlobalGradeById(Integer.parseInt(dataval));
                     gradeId[i] = global_grade_name.getName();
              }
              int[] colors = { 0x4f75c5, 0xac4b56, 0x5ea14f, 0x4c2f60, 0x51d3df, 0xea9513 };
              PyramidChart c = new PyramidChart(450, 450);
              c.setPyramidSize(220, 180, 150, 300);
              c.setViewAngle(15, 15);
              LegendBox legendBox = c.addLegend(320, 60);
              legendBox.setBackground(0xeeeeee, 0x888888);
              legendBox.setRoundedCorners(10, 0, 10, 0);
              c.setData(dataCount, gradeId);
              c.setColors2(Chart.DataColor, colors);
              c.setLayerGap(0.01);
              c.setCenterLabel("{value}", "Arial Bold");
              /*viewer.setChart(c);*/
              if (countryId == 0) {
                     c.addTitle("Resource Pyramid - ALL Countries");
                     c.makeChart((folderPath + "\\pyramidChart.png"));

                     Image src = ImageIO.read(new File(folderPath + "\\pyramidChart.png"));
                     int x = 0, y = 0, w = 420, h = 400;
                     BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                     dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                     ImageIO.write(dst, "png", new File(folderPath+"\\pyramidChart.png"));
                     model.addAttribute("pyramidList", pyramidList);
              }
              if (countryId == 1) {
                     c.addTitle("Resource Pyramid - France");
                     c.makeChart((folderPath + "\\FrancePyramid.png"));
                     
                     Image src = ImageIO.read(new File(folderPath + "\\FrancePyramid.png"));
                     int x = 0, y = 0, w = 420, h = 400;
                     BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                     dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                     ImageIO.write(dst, "png", new File(folderPath+"\\FrancePyramid.png"));
                     model.addAttribute("francePyramidList", pyramidList);
              }
              if (countryId == 2) {
                     c.addTitle("Resource Pyramid - India");
                     c.makeChart((folderPath + "\\IndiaPyramid.png"));
                     Image src = ImageIO.read(new File(folderPath + "\\IndiaPyramid.png"));
                     int x = 0, y = 0, w = 420, h = 400;
                     BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                     dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                     ImageIO.write(dst, "png", new File(folderPath+"\\IndiaPyramid.png"));
                     model.addAttribute("IndiaPyramidList", pyramidList);
              }
              if (countryId == 3) {
                     c.addTitle("Resource Pyramid - China");
                     c.makeChart((folderPath + "\\ChinaPyramid.png"));
                     Image src = ImageIO.read(new File(folderPath + "\\ChinaPyramid.png"));
                     int x = 0, y = 0, w = 420, h = 400;
                     BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                     dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                     ImageIO.write(dst, "png", new File(folderPath+"\\ChinaPyramid.png"));
                     model.addAttribute("ChinaPyramidList", pyramidList);
              }
              if (countryId == 4) {
                     c.addTitle("Resource Pyramid - Spain");
                     c.makeChart((folderPath + "\\SpainPyramid.png"));
                     Image src = ImageIO.read(new File(folderPath + "\\SpainPyramid.png"));
                     int x = 0, y = 0, w = 420, h = 400;
                     BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                     dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                     ImageIO.write(dst, "png", new File(folderPath+"\\SpainPyramid.png"));
                     model.addAttribute("SpainPyramidList", pyramidList);
              }
              if (countryId == 5) {
                     c.addTitle("Resource Pyramid - Morocco");
                     c.makeChart((folderPath + "\\MoroccoPyramid.png"));
                     Image src = ImageIO.read(new File(folderPath + "\\MoroccoPyramid.png"));
                     int x = 0, y = 0, w = 420, h = 400;
                     BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                     dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                     ImageIO.write(dst, "png", new File(folderPath+"\\MoroccoPyramid.png"));
                     model.addAttribute("MoroccoPyramidList", pyramidList);
              }
              
              if (countryId == 6) {
                  c.addTitle("Resource Pyramid - Germany");
                  c.makeChart((folderPath + "\\GermanyPyramid.png"));
                  Image src = ImageIO.read(new File(folderPath + "\\GermanyPyramid.png"));
                  int x = 0, y = 0, w = 420, h = 400;
                  BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                  dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                  ImageIO.write(dst, "png", new File(folderPath+"\\GermanyPyramid.png"));
                  model.addAttribute("GermanyPyramidList", pyramidList);
           }
              
              if (countryId == 7) {
                  c.addTitle("Resource Pyramid - UK");
                  c.makeChart((folderPath + "\\UKPyramid.png"));
                  Image src = ImageIO.read(new File(folderPath + "\\UKPyramid.png"));
                  int x = 0, y = 0, w = 420, h = 400;
                  BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                  dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
                  ImageIO.write(dst, "png", new File(folderPath+"\\UKPyramid.png"));
                  model.addAttribute("UKPyramidList", pyramidList);
           }
       }
}






