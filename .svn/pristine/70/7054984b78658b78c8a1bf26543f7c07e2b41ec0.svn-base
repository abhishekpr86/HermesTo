package com.capgemini.onboarding;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.BisRotation;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.PreOnbEmployee;

public class GenericExcel<T> {

	private Logger logger = Logger.getLogger(GenericExcel.class);
	//private T t;
	
	//XSSFWorkbook workbook = new XSSFWorkbook();
    //XSSFSheet sheet = workbook.createSheet("Reviews");
    
    public void writeHeaderLine(Sheet sheet, Class<T> class1, String[] columnList) {
		Row headerRow = sheet.createRow(0);
		int columncount = 0;
		if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.model.BisRotation")) {
			Cell headerCell = headerRow.createCell(0);
			headerCell.setCellValue("Corp Id");
			headerCell = headerRow.createCell(1);
			headerCell.setCellValue("First Name");
			headerCell = headerRow.createCell(2);
			headerCell.setCellValue("Last Name");
			headerCell = headerRow.createCell(3);
			headerCell.setCellValue("Country");
			headerCell = headerRow.createCell(4);
			headerCell.setCellValue("Location");
			headerCell = headerRow.createCell(5);
			headerCell.setCellValue("Grade");
			headerCell = headerRow.createCell(6);
			headerCell.setCellValue("Primary Program");
			headerCell = headerRow.createCell(7);
			headerCell.setCellValue("Role");
			headerCell = headerRow.createCell(8);
			headerCell.setCellValue("Old Bundle");
			headerCell = headerRow.createCell(9);
			headerCell.setCellValue("New Bundle");
			headerCell = headerRow.createCell(10);
			headerCell.setCellValue("Rotation Date");
		}else if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.model.Employee")) {
			columncount = 0;
			Cell headerCell = null;
			for (int count=0; count<columnList.length; count++) {
				headerCell = headerRow.createCell(count);
				headerCell.setCellValue(columnList[count]);
			}
			
		}
		
		else if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.model.PreOnbEmployee")) {
			columncount = 0;
			Cell headerCell = null;
			for (int count=0; count<columnList.length; count++) {
				headerCell = headerRow.createCell(count);
				headerCell.setCellValue(columnList[count]);
			}
			
		}
		
		else if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.dto.MoodleEnrolDataDTO")) {
			columncount = 0;
			Cell headerCell = null;
			for (int count=0; count<columnList.length; count++) {
				headerCell = headerRow.createCell(count);
				headerCell.setCellValue(columnList[count]);
			}
		}
		
	}
    
    public void writeDataLines(List<T> result, SXSSFWorkbook workbook, Sheet sheet, Class<T> class1)  {
		int rowCount = 1;
		T bisrot = null;
		
		
		if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.model.BisRotation")) {
			List<BisRotation> reportResult = (List<BisRotation>) result;
			Iterator<BisRotation> iter = reportResult.iterator();
			BisRotation bisrotation = null;
			while (iter.hasNext()) {
				bisrotation = (BisRotation)iter.next(); 
				Row row = sheet.createRow(rowCount++);
				
				int columnCount = 0;
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getCorpId());
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getFirstName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getLastName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getCountry().getCountryName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getLocation().getStateName());
				cell = row.createCell(columnCount++);
				cell.setCellValue((bisrotation.getGrade() != null)? bisrotation.getGrade().getName() :"");
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getPrimaryProgram().getPrimaryProgramName());
				cell = row.createCell(columnCount++);
				cell.setCellValue((bisrotation.getRole() != null)?bisrotation.getRole().getRef_name() : "");
				//cell.setCellValue(bisrotation.getRole().getRef_name());
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getPrevBundle().getBis_Name());
				cell = row.createCell(columnCount++);
				cell.setCellValue(bisrotation.getNewBundle().getBis_Name());
				cell = row.createCell(columnCount++);
				CellStyle cellStyle = workbook.createCellStyle();
				CreationHelper creationHelper = workbook.getCreationHelper();
				cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
				
				cell.setCellStyle(cellStyle);
				cell.setCellValue(java.sql.Date.valueOf(bisrotation.getRotationDate()));
				//cell.setCellValue(bisrotation.getRotationDate());
				
				}
		}else if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.model.Employee")) {
			List<Employee> reportResult = (List<Employee>) result;
			Iterator<Employee> iter = reportResult.iterator();
			Employee emp = null;
			CellStyle cellStyle = workbook.createCellStyle();
			CellStyle cellStyleTimestamp = workbook.createCellStyle();
			CellStyle cellStyleFloat = workbook.createCellStyle();
			CreationHelper creationHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
			cellStyleTimestamp.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
			cellStyleFloat.setDataFormat(creationHelper.createDataFormat().getFormat("#.##"));
			try {
			while (iter.hasNext()) {
				emp = (Employee)iter.next(); 
				Row row = sheet.createRow(rowCount++);
				
				int columnCount = 0;
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getPrimaryprogram().getPrimaryProgramName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getEmpId());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getGgId());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getCorpId());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getPsaId());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getVmNumber());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getEmpType());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getFirstName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getLastName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getCountry().getCountryName());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getLocation().getStateName());
				cell = row.createCell(columnCount++);
			    cell.setCellValue(emp.getEmail());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getCgEntity());
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getPrimaryTechnology().getTechnologyName());
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getRoleid() != null)?emp.getRoleid().getRef_name() : "");
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getVendor() != null)?emp.getVendor().getVendorName():"");
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getGrade() != null)?emp.getGrade().getName():(emp.getGlobalGrade() != null)?emp.getGlobalGrade().getName() :"");
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getGlobalGrade() != null)?emp.getGlobalGrade().getName() :"");
				cell = row.createCell(columnCount++);
				
				cell.setCellValue(emp.getManager().getFirstName()+ " "+emp.getManager().getLastName());
				cell = row.createCell(columnCount++);
				
				cell.setCellValue(emp.getManagerEmail());
				cell = row.createCell(columnCount++);
			
				/*if((emp.getHRAReq()!= null))
				cell.setCellValue(emp.getHRAReq());*/
				cell.setCellValue((emp.getIndus() != null)? emp.getIndus().getIndusGoalName() : null);
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getJiraNumber()!= null)?emp.getJiraNumber():null);
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getStaffingRR()!= null)?emp.getStaffingRR():null);
				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(emp.getJoiningDate());
				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				
				cell.setCellValue(emp.getBillingDate());
				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				
				cell.setCellValue(emp.getEndDate());
				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				
				cell.setCellValue((emp.getActualEndDate() != null)?emp.getActualEndDate() : null);
				cell = row.createCell(columnCount++);
				if(emp.getBundleEM() != null)
					
					cell.setCellValue(emp.getBundleEM().getBundleEmName());
				cell = row.createCell(columnCount++);
			
				if(emp.getEM() != null)  //mehens
				cell.setCellValue(emp.getEM().getFirstName()+" "+emp.getEM().getLastName());
				cell = row.createCell(columnCount++);
				
				if(emp.getOffshoreEm() != null && emp.getOffshoreEm().getOffshoreEmName()!=null)
					
				cell.setCellValue(emp.getOffshoreEm().getOffshoreEmName());
				cell = row.createCell(columnCount++);
			
				cell.setCellValue(emp.getBis().getBis_Name());
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getResourceManager() != null)?emp.getResourceManager().getResourceMgrName():null);
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getDemandType() != null)?emp.getDemandType().getType():null);
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.isReplacementRequired()?"Yes":"No")); //Replacement Required
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getRollOffType()!= null)?emp.getRollOffType().getType() : null); //getRollOffTypeVal() //Reason for Roll Off
				cell = row.createCell(columnCount++);
				
				cell.setCellValue((emp.getReplacementType()!= null)? emp.getReplacementType().getType() : null); //Reason for No Replacement
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getRepCorpId()); //Replacement Corp ID
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getRepfirstName()); //Replacement Employee Name
				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				cell.setCellValue((emp.getRequestedDate() != null) ? emp.getRequestedDate() : null);
				cell = row.createCell(columnCount++);
				cell.setCellStyle(cellStyle);
				cell.setCellValue((emp.getGeneratedDate() != null) ? emp.getGeneratedDate() : null);
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.isUpperVCycle());
				cell = row.createCell(columnCount++);
				//cell.setCellValue(emp.isTimeNMaterial());
				cell.setCellValue(emp.getTimeMat());//mehens
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getOffboardcomment());
				
				if(emp.getPreonbemp() != null) {
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue((emp.getPreonbemp().getEMSubmitDT() != null) ? emp.getPreonbemp().getEMSubmitDT() : null);
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue((emp.getPreonbemp().getRMSubmitDT()!= null) ? emp.getPreonbemp().getRMSubmitDT() : null);
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue((emp.getPreonbemp().getBISPMOSubmitDT()!= null) ? emp.getPreonbemp().getBISPMOSubmitDT() : null);
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue((emp.getPreonbemp().getRMPMOSubmitDT()!= null) ? emp.getPreonbemp().getRMPMOSubmitDT() : null);
					
				}else {
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue("");
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue("");
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue("");
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					cell.setCellValue("");
					
				}
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getEmpSex() != null) ? emp.getEmpSex() : "");
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getCapManager() != null) ? emp.getCapManager() : "");
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getCapManagerEmail() != null) ? emp.getCapManagerEmail() : "");
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getCapSupervisor() != null) ? emp.getCapSupervisor() : "");
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getCapSupervisorEmail() != null) ? emp.getCapSupervisorEmail() : "");
				cell = row.createCell(columnCount++);
				cell.setCellValue(emp.getAllocation());
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getWorkingBis() != null) ? emp.getWorkingBis().getBundle_name() : emp.getBis().getBundle_name());
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getProject() != null) ? emp.getProject():"");
				cell = row.createCell(columnCount++);
				cell.setCellValue((emp.getRr_no() != null) ? emp.getRr_no():"");
				/*cell = row.createCell(columnCount++); //Core Team
				cell.setCellValue(emp.isCoreTeam());*/
				
				/*cell = row.createCell(columnCount++);
                if(emp.getBuddy()!=null) {
                    cell.setCellValue(emp.getBuddy().getFirstName()+" "+emp.getBuddy().getLastName()+" ("+emp.getBuddy().getCorpId()+")");
                    }
                cell = row.createCell(columnCount++); //Onboarding Email
                cell.setCellValue((emp.getOnboardingEmail() != null) ? emp.getOnboardingEmail() : "");*/
				cell = row.createCell(columnCount++);//mehens-new
				if(emp.getCriticality() != null) {
					cell.setCellValue(emp.getCriticality());
				}
				
				cell = row.createCell(columnCount++);//mehens-new
				if(emp.getSecondaryTechnology() != null) {
					cell.setCellValue(emp.getSecondaryTechnology().getTechnologyName());
				}
				
				cell = row.createCell(columnCount++);//mehens-new-VCO
				if(emp.getVco() != null) {
					cell.setCellValue(emp.getVco());
				}
				
				cell = row.createCell(columnCount++);//mehens-new-BI
				cell.setCellValue(emp.isBi());
				
				cell = row.createCell(columnCount++);
                if(emp.getBuddy()!=null) {
                    cell.setCellValue(emp.getBuddy().getFirstName()+" "+emp.getBuddy().getLastName()+" ("+emp.getBuddy().getCorpId()+")");
                    }
                cell = row.createCell(columnCount++); //Onboarding Email
                cell.setCellValue((emp.getOnboardingEmail() != null) ? emp.getOnboardingEmail() : "");
                
                cell = row.createCell(columnCount++);//mehens
                cell.setCellValue((emp.getPcSerialNumber() != null) ? emp.getPcSerialNumber():"");
                
                cell = row.createCell(columnCount++);//mehens
                cell.setCellValue((emp.getPCAEmail() != null) ? emp.getPCAEmail():"");
                
                cell = row.createCell(columnCount++);//mehens
                cell.setCellValue((emp.getComment() != null) ? emp.getComment():"");//Comment
				
			}
			}catch(Exception e) {
				logger.info("log" +e.getMessage());
				logger.info(e.toString());
				e.printStackTrace();
			}
		}else if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.dto.MoodleEnrolDataDTO")) {
			//logger.info("Inside workbook write"+result);
			List<MoodleEnrolDataDTO> reportResult = (List<MoodleEnrolDataDTO>) result;
			if(result != null) {
				Iterator<MoodleEnrolDataDTO> iter = reportResult.iterator();
				MoodleEnrolDataDTO dto = null;
				CellStyle cellStyle = workbook.createCellStyle();
				CellStyle cellStyleTimestamp = workbook.createCellStyle();
				CreationHelper creationHelper = workbook.getCreationHelper();
				cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
				cellStyleTimestamp.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
				while (iter.hasNext()) {
					dto = (MoodleEnrolDataDTO)iter.next(); 
					Row row = sheet.createRow(rowCount++);
					
					int columnCount = 0;
					Cell cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getUsername());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getFirstName());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getLastname());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getCoursename());
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(java.sql.Date.valueOf(dto.getOnboardingDate()));
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					if(dto.getOffboardingDate() != null) {
					cell.setCellValue(java.sql.Date.valueOf(dto.getOffboardingDate()));
					}
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					if(dto.getUserEnrolDateConvert() !=null) {
						cell.setCellValue(java.sql.Timestamp.valueOf(dto.getUserEnrolDateConvert()));
					}
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyleTimestamp);
					if(dto.getCourseCompletionDateConvert() !=null) {
						cell.setCellValue(java.sql.Timestamp.valueOf(dto.getCourseCompletionDateConvert()));
					}
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getStatus());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getBis());
					cell = row.createCell(columnCount++);
					/*cell.setCellValue(dto.getManager());*/
					cell.setCellValue(dto.getManagerEmail());//mehens - added for manager email
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getEM());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getBM());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getPrimaryProgram());
					cell = row.createCell(columnCount++);
					cell.setCellValue(dto.getUserEmail());//mehens - adding  email in moodle report 
				}
			}
		}
			
		
	}
    
    
    //new method
    public void writeDataLinesPreOnb(List<T> result, SXSSFWorkbook workbook, Sheet sheet, Class<T> class1)  {
		int rowCount = 1;
		
		if(class1.getName().equalsIgnoreCase("com.capgemini.onboarding.model.PreOnbEmployee")) {
			List<PreOnbEmployee> reportResult = (List<PreOnbEmployee>) result;
			Iterator<PreOnbEmployee> iter = reportResult.iterator();
			PreOnbEmployee emp = null;
			CellStyle cellStyle = workbook.createCellStyle();
			CellStyle cellStyleTimestamp = workbook.createCellStyle();
			CellStyle cellStyleFloat = workbook.createCellStyle();
			CreationHelper creationHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
			cellStyleTimestamp.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
			cellStyleFloat.setDataFormat(creationHelper.createDataFormat().getFormat("#.##"));
			try {
				while (iter.hasNext()) {
					emp = (PreOnbEmployee)iter.next(); 
					Row row = sheet.createRow(rowCount++);
					int columnCount = 0;
					Cell cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getPrimaryprogram().getPrimaryProgramName());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getEmpId());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getGgId());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getCorpId());
					cell = row.createCell(columnCount++);
					/*cell.setCellValue(emp.getPsaId());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getVmNumber());
					cell = row.createCell(columnCount++);*/
					cell.setCellValue(emp.getEmpType());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getFirstName());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getLastName());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getCountry().getCountryName());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getLocation().getStateName());
					cell = row.createCell(columnCount++);
				    cell.setCellValue(emp.getEmail());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getCgEntity());
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getPrimaryTechnology().getTechnologyName());
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getRoleid() != null)?emp.getRoleid().getRef_name() : "");
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getVendor() != null)?emp.getVendor().getVendorName():"");
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getGrade() != null)?emp.getGrade().getName():(emp.getGlobalGrade() != null)?emp.getGlobalGrade().getName() :"");
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getGlobalGrade() != null)?emp.getGlobalGrade().getName() :"");
					cell = row.createCell(columnCount++);
					
					cell.setCellValue(emp.getManager().getFirstName()+ " "+emp.getManager().getLastName());
					cell = row.createCell(columnCount++);
					
					cell.setCellValue(emp.getManagerEmail());
					//cell = row.createCell(columnCount++);
				
					//if((emp.getHRAReq()!= null))
					//cell.setCellValue(emp.getHRAReq());
					
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getJiraNumber()!= null)?emp.getJiraNumber():null);
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getStaffingRR()!= null)?emp.getStaffingRR():null);
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(emp.getJoiningDate());
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					
					cell.setCellValue(emp.getBillingDate());
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					
					cell.setCellValue(emp.getEndDate());
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					
					/*cell.setCellValue((emp.getActualEndDate() != null)?emp.getActualEndDate() : null);
					cell = row.createCell(columnCount++);*/
					
					if(emp.getBundleEM() != null)
						
						cell.setCellValue(emp.getBundleEM().getBundleEmName());
					cell = row.createCell(columnCount++);
				
					if(emp.getEM() != null)  //mehens
					cell.setCellValue(emp.getEM().getFirstName()+" "+emp.getEM().getLastName());
					cell = row.createCell(columnCount++);
					
					/*if(emp.getOffshoreEm() != null && emp.getOffshoreEm().getOffshoreEmName()!=null)
						
					cell.setCellValue(emp.getOffshoreEm().getOffshoreEmName());
					cell = row.createCell(columnCount++);*/
				
					cell.setCellValue(emp.getBis().getBis_Name());
					cell = row.createCell(columnCount++);
					
					/*cell.setCellValue((emp.getResourceManager() != null)?emp.getResourceManager().getResourceMgrName():null);
					cell = row.createCell(columnCount++);*/
					
					cell.setCellValue((emp.getDemandType() != null)?emp.getDemandType().getType():null);
					cell = row.createCell(columnCount++);
					
					/*cell.setCellValue((emp.isReplacementRequired()?"Yes":"No")); //Replacement Required
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getRollOffType()!= null)?emp.getRollOffType().getType() : null); //getRollOffTypeVal() //Reason for Roll Off
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getReplacementType()!= null)? emp.getReplacementType().getType() : null); //Reason for No Replacement
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getRepCorpId()); //Replacement Corp ID
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getRepfirstName()); //Replacement Employee Name
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					cell.setCellValue((emp.getRequestedDate() != null) ? emp.getRequestedDate() : null);
					cell = row.createCell(columnCount++);
					cell.setCellStyle(cellStyle);
					cell.setCellValue((emp.getGeneratedDate() != null) ? emp.getGeneratedDate() : null);
					cell = row.createCell(columnCount++);*/
					/*cell.setCellValue(emp.isUpperVCycle());
					cell = row.createCell(columnCount++);*/
					//cell.setCellValue(emp.isTimeNMaterial());
					cell.setCellValue(emp.getTimeMat());//mehens
					cell = row.createCell(columnCount++);
					//cell.setCellValue(emp.getOffboardcomment());
					
					//if(emp.getPreonbemp() != null) {
						//cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue((emp.getEMSubmitDT() != null) ? emp.getEMSubmitDT() : null);
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue((emp.getRMSubmitDT()!= null) ? emp.getRMSubmitDT() : null);
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue((emp.getBISPMOSubmitDT()!= null) ? emp.getBISPMOSubmitDT() : null);
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue((emp.getRMPMOSubmitDT()!= null) ? emp.getRMPMOSubmitDT() : null);
						
					/*}else {
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue("");
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue("");
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue("");
						cell = row.createCell(columnCount++);
						cell.setCellStyle(cellStyleTimestamp);
						cell.setCellValue("");
						
					}*/
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getResourceStatus() != null) ? emp.getResourceStatus() : "");
					cell = row.createCell(columnCount++);
					
					cell.setCellValue((emp.getEmpSex() != null) ? emp.getEmpSex() : "");
					cell = row.createCell(columnCount++);
					/*cell.setCellValue((emp.getCapManager() != null) ? emp.getCapManager() : "");
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getCapManagerEmail() != null) ? emp.getCapManagerEmail() : "");
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getCapSupervisor() != null) ? emp.getCapSupervisor() : "");
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getCapSupervisorEmail() != null) ? emp.getCapSupervisorEmail() : "");
					cell = row.createCell(columnCount++);*/
					cell.setCellValue(emp.getAllocation());
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getWorkingBis() != null) ? emp.getWorkingBis().getBundle_name() : emp.getBis().getBundle_name());
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getProject() != null) ? emp.getProject():"");
					cell = row.createCell(columnCount++);
					//cell.setCellValue((emp.getRr_no() != null) ? emp.getRr_no():"");
					/*cell = row.createCell(columnCount++); //Core Team
					cell.setCellValue(emp.isCoreTeam());*/
					
					/*cell = row.createCell(columnCount++);
	                if(emp.getBuddy()!=null) {
	                    cell.setCellValue(emp.getBuddy().getFirstName()+" "+emp.getBuddy().getLastName()+" ("+emp.getBuddy().getCorpId()+")");
	                    }
	                cell = row.createCell(columnCount++); //Onboarding Email
	                cell.setCellValue((emp.getOnboardingEmail() != null) ? emp.getOnboardingEmail() : "");*/
					//cell = row.createCell(columnCount++);//mehens-new
					if(emp.getCriticality() != null) {
						cell.setCellValue(emp.getCriticality());
					}
					
					cell = row.createCell(columnCount++);//mehens-new
					if(emp.getSecondaryTechnology() != null) {
						cell.setCellValue(emp.getSecondaryTechnology().getTechnologyName());
					}
					
					cell = row.createCell(columnCount++);
					if(emp.getVco() != null) {
						cell.setCellValue(emp.getVco());
					}
					
					cell = row.createCell(columnCount++);
	                if(emp.getBuddy()!=null) {
	                    cell.setCellValue(emp.getBuddy().getFirstName()+" "+emp.getBuddy().getLastName()+" ("+emp.getBuddy().getCorpId()+")");
	                    }
	                cell = row.createCell(columnCount++); 
	                cell.setCellValue((emp.getOnboardingEmail() != null) ? emp.getOnboardingEmail() : "");
	                
	                cell = row.createCell(columnCount++);
	                cell.setCellValue((emp.getPcSerialNumber() != null) ? emp.getPcSerialNumber():"");
	                
	                
	                cell = row.createCell(columnCount++);
	                cell.setCellValue((emp.getComment() != null) ? emp.getComment():"");//Comment
	                
	                cell = row.createCell(columnCount++);
	                cell.setCellValue((emp.getRequestor() != null) ? emp.getRequestor().getEmail():"");//Requestor email
	                
	                cell = row.createCell(columnCount++);
					cell.setCellValue(emp.isPSAIdReq());
					
					cell = row.createCell(columnCount++);
					//cell.setCellValue((emp.getDob() != null)? emp.getDob() : null);
					cell.setCellValue((emp.getDobDate() != null)? emp.getDobDate() : null);
					
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getPsaLibType() != null)? emp.getPsaLibType().getPsaLibName() : null);
					
					
					cell = row.createCell(columnCount++);
					//cell.setCellValue(emp.getType());//Technical/Development
					if(emp.getType()==0)
						cell.setCellValue("NA");
					else if(emp.getType()==1)
						cell.setCellValue("Industrialisator");
					else if(emp.getType()==2)
						cell.setCellValue("Developer");
					else if(emp.getType()==3)
						cell.setCellValue("Transversal");
					else if(emp.getType()==4)
						cell.setCellValue("High Cycle");
					else cell.setCellValue(""); 
						
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getIndus() != null)? emp.getIndus().getIndusGoalName() : null);
					
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getRole() != null)? emp.getRole().getRoleTechName() : null);
					
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.getOrderG());
					
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getBusinessAs() != null)? emp.getBusinessAs().getBasName() : null);
					
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getCfao() != null)? emp.getCfao().getCfaoName() : null);
					
					cell = row.createCell(columnCount++);
					cell.setCellValue(emp.isSdm());
					
					cell = row.createCell(columnCount++);
					cell.setCellValue((emp.getOtherAs() != null)? emp.getOtherAs() : null);
				}
				}catch(Exception e) {
					logger.info("log" +e.getMessage());
					logger.info(e.toString());
					e.printStackTrace();
				}
		}
		
		}
    //end
}
