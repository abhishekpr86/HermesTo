<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Resource OnBoarding Tool - Add Resource</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/datatables.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />
</head>
<style>
.fieldLbl{
margin-bottom: 5px !important;
}
</style>
<body>
	<header class="main-header">
		<div class="pull-left">
			<h2 class="pull-left">Onboarding Tool</h2>
		</div>
		<nav class="pull-right">
			<ul class="nav-new">
				
				<li><a href="${pageContext.request.contextPath}/dashboard" title="DashBoard">DashBoard</a></li>
			  <c:if test="${checkUserType eq 'RM_PMO'}">
					<li class="reports"><a title="User Management">User Management</a>
					<ul class="reportsSubMenu">
						 <li><a href="${pageContext.request.contextPath}/userManagement" title="userManagement">Add User</a></li>
						<li><a href="${pageContext.request.contextPath}/addRole" title="userSearch">Add Role</a></li>
					</ul></li>
			</c:if>
			
			<c:if test="${checkUserType eq 'BundleEM'}">
				<%-- <li><a href="${pageContext.request.contextPath}/addPreOnboardRec" title="Resource Pre-Onboarding">Resource Pre-Onboarding</a></li> --%>
			<li><a href="${pageContext.request.contextPath}/addPreOnboardRec" title="Resource Pre-Onboarding">Onboarding Request</a></li>
			</c:if>
			
			<!-- <li class="active"><a href="javascript:void(0);" title="View/Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li> -->
			<li class="active"><a href="javascript:void(0);" title="View/Edit Pre-Onboarding">View/Edit Onboarding Requests</a></li>
			
			
			<c:if test="${(checkUserType eq 'BundleEM')}"><!-- EM -->
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View Resources</a></li>
			</c:if>
			
			<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO') or (checkUserType eq 'ASL')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View / Edit Resources</a></li>
			</c:if>
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="View List">View Resources</a></li>
			</c:if>
			
			<c:if test="${checkUserType eq 'RM_PMO'}">
					<li><a href="${pageContext.request.contextPath}/employeeSearch" title="Resource Off-boarding">Resource Off-boarding</a></li>
			</c:if>	
			<li class="reports"><a title="Reports">Reports</a>
					<ul  style="width:250px" class="reportsSubMenu">
					<c:if test="${checkUserType ne 'BundleEM'}">
						<li><a href="${pageContext.request.contextPath}/report" title="Report">Resource Report</a></li>
						<li><a href="${pageContext.request.contextPath}/dataInconsistencies" title="dataInconsistencies">Data Inconsistencies Report</a></li>
	 					<li><a href="${pageContext.request.contextPath}/orphanMgr" title="Orphan Report">Orphan Report</a></li>
						<li><a href="${pageContext.request.contextPath}/bisRotationReport" title="Res Rotation Report">Resource Rotation Report</a></li>
					</c:if>
						<li><a href="${pageContext.request.contextPath}/moodleUserEnrol">Moodle Enrolment</a></li>
						<li><a href="${pageContext.request.contextPath}/moodleUserEnrolData">Moodle Report</a></li>
						<c:if test="${(checkUserType eq 'RM') or (checkUserType eq 'RM_PMO')}">
							<li><a href="${pageContext.request.contextPath}/onboardingRequest" title="onboardingRequest">Onboarding Request</a></li>
						</c:if>
					</ul>
			</li>
				<li><a href="${pageContext.request.contextPath}/main"
					title="Change Password">Change Password</a></li>
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<br />
		<h2>Edit Resource</h2>
		<div class="reqText">* indicates required fields</div>
		<h1 style="color:#86A5F7">Resource Information:</h1>
			
		<div class="formFields clearfix" id="mainDIV" >
		
			<c:url var="editAction" value="/addPreOnboardRec/update"></c:url>
			
			<c:if test="${errorMsg ne null}">
				<div class="errorBox">
					<h3>${errorMsg}</h3>
				</div>
			</c:if>

			<c:if test="${successMsg ne null}">
				<div class="successMsg">
					<h3>${successMsg}</h3>
				</div>
			</c:if>
			<form:form action="${editAction}" commandName="employee" method="POST" id="editEmployee">
			<section>
			
			<br>
				 <div class="formRow">
					<div class="col50per">
						<div>
							
							<form:hidden id="createdBy" path="createdBy" />
							<form:hidden id="id" path="id" />	
							<form:hidden id="resourceStatus" path="resourceStatus" />
							<form:hidden id="EMSubmitDT"  path="EMSubmitDT" />
							<form:hidden id="creationdate" path="creationdate"/>
							<form:hidden id="isVLANmailDone" path="isVLANmailDone"/>
							<c:if test="${employee.requestor ne null}">
								<form:hidden id="requestor" path="requestor" />
							</c:if>
						</div>
						<div>
							<c:if test="${employee.empType == 'Internal'}">					
								<div class="col50per" id="empID" >
									<div class="fieldLbl">
										<label>Emp ID :
										</label>
									</div>
								<div class="inputField">${employee.empId}
								<form:hidden id="txtEmpID" path="empId" />
								</div>
								</div>
							</c:if>
						</div>
						
					</div>
					<div class="col50per" id="divEmpType">
						<div class="fieldLbl">
							<label>Resource Type :</label>
						</div>
						<div class="inputField">
						${employee.empType}
						<form:hidden path="empType" id="empType" />
						</div>
					</div>
				</div>
				
					<div class="formRow">
					<div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">CORP ID :
							</label>
						</div>
						<div class="inputField">
						${employee.corpId}
							<form:hidden id="txtCorpID" path="corpId" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Entity :</label>
						</div>
						<div class="inputField">
							${employee.cgEntity}
							<form:hidden path="cgEntity" id="drpCapEntity" />
						</div>
					</div>
					</div>
					
					<div class="formRow">
					<div class="col50per" id="divGGId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">GG ID :</label>
						</div>
						<div class="inputField">
								${employee.ggId}
						<form:hidden id="txtGGID" path="ggId" />
						</div>
					</div>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Role :
							</label>
						</div>
						<div class="inputField">
							${employee.roleid.ref_name}
							<form:hidden path="emprole" id="drpemprole"/>
						</div>
					</div>
					</div>
					
					<div class="formRow">
						<div class="col50per">
						<div class="fieldLbl">
							<label>First Name :
							</label>
						</div>
						<div class="inputField">
						${employee.firstName}
							<form:hidden id="txtFirstName" path="firstName" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Primary Skill :
							</label>
						</div>
						<div class="inputField">
						${employee.primaryTechnology.technologyName}
						<form:hidden path="primaryTechnology" id="drpPrimaryTechnology" />
						
						</div>
					</div>
					</div>
					
					<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Last Name :
							</label>
						</div>
						<div class="inputField">
						${employee.lastName}
							<form:hidden id="txtLastName" path="lastName" />
						</div>
							
					</div>
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Sex :
							</label>
						</div>
						<form:hidden path="empSex" id="rdEmpSex" />
						<c:if test="${employee.empSex == 'M'}">
         					Male
      					</c:if>
						<c:if test="${employee.empSex == 'F'}">
         					Female
      					</c:if>	
					</div> --%>
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Secondary Skill :
							</label>
						</div>
						<div class="inputField">
						${employee.secondaryTechnology.technologyName}
						<form:hidden path="secondaryTechnology" id="drpSecondaryTechnology" />
						</div>
					</div>
					
					
					</div>
					
					<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Country :
							</label>
						</div>
						<div class="inputField">
						
						${employee.country.countryName}
					<form:hidden path="country" id="drpCountry" />	
						</div>
					</div>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Location :</label>
						</div>
						<div class="inputField">
							${employee.location.stateName}
						<form:hidden path="location" id="drpLocation" />
						
						</div>
					</div>
					</div>
					
					<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Email :
							</label>
						</div>
						<div class="inputField">
						${employee.email}
							<form:hidden id="txtEmail" path="email" />
						</div>
					</div>
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Sex :
							</label>
						</div>
						<form:hidden path="empSex" id="rdEmpSex" />
						<c:if test="${employee.empSex == 'M'}">
         					Male
      					</c:if>
						<c:if test="${employee.empSex == 'F'}">
         					Female
      					</c:if>	
					</div>
					
					
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Secondary Skill :
							</label>
						</div>
						<div class="inputField">
						${employee.secondaryTechnology.technologyName}
						<form:hidden path="secondaryTechnology" id="drpSecondaryTechnology" />
						</div>
					</div> --%>
					
					<div class="col50per" id="divresourcemanager" style="display: none;">
							<div class="fieldLbl">
	
								<label>Resource Manager : 
								</label>
							</div>
							<div class="inputField">
								${employee.resourceManager.resourceMgrName}
								<form:hidden path="resourceManager" id="drpresourceManager" />
							
							</div>
					</div>
					</div>
					
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Global Grade :
							</label>
						</div>
						<div class="inputField">
						
						${employee.globalGrade.name}
						<form:hidden path="globalGrade" id="drpGlobalGrade" />
								<div class="col50per" id="divGrade">
					</div>
						</div>
					</div>
					<div class="col50per" id="divGrade">
						<div class="fieldLbl">
							<label>Local Grade :
							</label>
						</div>
						<div class="inputField">
						
						${employee.grade.name}
						<form:hidden path="grade" id="drpGrade" />
							
						</div>
					</div>
				</div>
				
			 <div class="formRow">
				 <div class="col50per">
						<div class="fieldLbl">
							<label>Manager :
							</label>
						</div>
						<div class="inputField">
						${employee.manager.firstName} ${employee.manager.lastName} (${employee.manager.corpId})
					<form:hidden path="manager" id="drpSupervisor" />	
							
						</div>
					</div> 
					<div class="col50per">
						<div class="fieldLbl">
							<label>Manager Email :
							</label>
						</div>
						<div class="inputField">
							${employee.manager.email}
					<form:hidden path="managerEmail" id="mgrEmail" />	
							
						</div>
					</div>
				</div> 
				
				
				<div class="formRow">
				 <div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy :
							</label>
						</div>
						<div class="inputField">
						<c:if test="${employee.buddy ne null}">	
						${employee.buddy.firstName} ${employee.buddy.lastName} (${employee.buddy.corpId})
						</c:if>
					<form:hidden path="manager" id="employeeId" />	
							
						</div>
					</div> 
					<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy Email :
							</label>
						</div>
						<div class="inputField">
							${employee.onboardingEmail}
					<form:hidden path="onboardingEmail" id="mgrEmails" />	
							
						</div>
					</div>
				</div>
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Manager <span class="req"></span>:
							</label>
						</div>
						<div class="inputField">
							${employee.capManager}
							<form:hidden path="capManager" id="capMgr" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Manager Email <span class="req"></span>:
							</label>
						</div>
						<div class="inputField">
							${employee.capManagerEmail}
							<form:hidden path="capManagerEmail" id="capMgrEmail" />
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per" id="divCapSup" style="display: none;">
						<div class="fieldLbl">
							<label>Capgemini Supervisor <span class="req"></span>:
							</label>
						</div>
						<div class="inputField">
							${employee.capSupervisor}
							<form:hidden path="capSupervisor" id="capSup" />
						</div>
					</div>
					<div class="col50per"  id="divCapSupEmail" style="display: none;">
						<div class="fieldLbl">
							<label>Capgemini Supervisor Email <span class="req"></span>:
							</label>
						</div>
						<div class="inputField">
							${employee.capSupervisorEmail}
							<form:hidden path="capSupervisorEmail" id="capSupEmail" />
						</div>
					</div>
				</div>
				
				<c:if test="${employee.empType == 'External'}">
					<div class="formRow">
						<div class="col50per" id="divVendor">
							<div class="fieldLbl">
								<label>Vendor :
								</label>
							</div>
							<div class="inputField">
							${employee.vendor.vendorName}
							<form:hidden path="vendor" id="drpVendor" />
							</div>
						</div>
					</div>
					
				</c:if>
				</section>
				</div>
				<br>
				<h1 style="color:#86A5F7">Project Information:</h1>
				<div class="formFields clearfix" id="mainDIV" >
				<section>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Primary Program : </label>
						</div>
						<div class="inputField">
							
							${employee.primaryprogram.primaryProgramName}
							
							<form:hidden id="drpprimprogram" path="primaryprogram" />
							
						</div>
					</div>
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Project : </label>
						</div>
						<div class="inputField">
							${employee.project}
							<form:hidden id="project" path="project" />
						</div>
					</div>
				</div>
					<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Bundle-Domain :
							</label>
						</div>
						<div class="inputField">
							${employee.bis.bis_Name}
							
							<form:hidden path="bis" id="drpbis" />
						</div>
					</div>
					
						<div class="col50per">
					<div class="fieldLbl">
							<label>Bundle EM :</label>
					</div>
					<div class="inputField">
						${employee.bundleEM.bundleEmName}
						<form:hidden path="bundleEM" id="drpbundleEM" />
					</div>
				</div>
				</div>
				 <div class="formRow">
				
					<div class="col50per" id="divworkingbis" >
						<div class="fieldLbl">
							<label>Working Domain <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							${employee.workingBis.bis_Name}
							<form:hidden path="workingBis" id="drpworkingbis" />
						</div>
					</div>
					</div>
					
					
				<div class="formRow">
				
 					<div class="col50per">
						<div class="fieldLbl">
							<label>EM :
							</label>
						</div>
						<div class="inputField">
						${employee.EM.firstName} ${employee.EM.lastName} (${employee.EM.corpId})
						<form:hidden path="EM" id="drpEM" />
					</div>
				</div>	
				
				
					<div class="col50per" style="display: block;">
						<div class="fieldLbl">
							<label>Staff JIRA # <span class="req">*</span>:
							</label>
						</div>
						<c:if test="${(checkUserType eq 'BundleEM')}">
						<div class="inputField">
							${employee.jiraNumber}
							<form:hidden path="jiraNumber" id="jiraNum" />
					</div>
					</c:if>
					<c:if test="${(checkUserType eq 'RM')}">
					<div class="inputField">
							<form:input path="jiraNumber" id="jiraNum" size="10" class="input-control" data-validation="number" data-validation-help="Please enter number"/>
						</div>
						</c:if>
				</div>
				</div>
			
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>On-boarding Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
						${employee.onboardingDate}
							<form:hidden path="onboardingDate" id="txtJoiningDate" />
						</div>
					</div>
					
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Staffing SO :</label>
						</div>
						<div class="inputField">
						${employee.staffingRR}
							<form:hidden path="staffingRR" id="staffrr" size="10"/>
						</div>
					</div> --%>
					<div class="col50per" id ="staffingrr" style="display: none;">
						<div class="fieldLbl">
							<label>GTD # <span class="req">*</span>:
							</label>
						</div>
						<c:if test="${(checkUserType eq 'BundleEM')}">
						<div class="inputField">
							${employee.staffingRR}
							<form:hidden path="staffingRR" id="staffrr" />
					</div>
					</c:if>
					<c:if test="${(checkUserType eq 'RM')}">
					<div class="inputField">
							<%-- <form:input path="staffingRR" id="staffrr" size="10" class="input-control" data-validation="number" data-validation-help="Please enter number"/> --%>
							<form:input path="staffingRR" id="staffrr" size="10" class="input-control" data-validation="alphanumeric" data-validation-help="Please enter alphanumeric data"/>
						</div>
						</c:if>
						
					</div> 
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Planned Off-boarding Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							
							${employee.offboardingDate}
							<form:hidden path="offboardingDate" id="txtEndDate" />
							
						</div>
					</div>
						 <%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Upper V Cycle : </label>
						</div>
						<div class="inputField">
							<form:hidden path="upperVCycle" value="false" />
							<c:if test="${employee.upperVCycle == true}">
         						yes
      						</c:if>
							<c:if test="${employee.upperVCycle == false}">
         						no
      						</c:if>
						</div>
					</div> --%>
				</div>
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Billing Start Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							
							${employee.billingStartDate}
							<form:hidden path="billingStartDate" id="txtBillingDate" />
							
						</div>
					</div>
					
					<%-- New Time And Material--%>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Time & Material : </label>
						</div>
						<div class="inputField">
							${employee.timeMat}
							<form:hidden path="timeMat" id="empTimeMat" />
						</div>
					</div>
					
					
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Time & Material : </label>
						</div>
						<div class="inputField">
							<form:hidden path="timeNMaterial" value="false" />
							<c:if test="${employee.timeNMaterial == true}">
         						yes
      						</c:if>
							<c:if test="${employee.timeNMaterial == false}">
         						no
      						</c:if>
						</div>
					</div> --%>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Type Of Demand : </label>
						</div>
						<div class="inputField">
							${employee.demandType.type}
							<form:hidden path="demandType" id="drpdemand" />
						</div>
					</div>
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Core Team : </label>
						</div>
						<div class="inputField">
							<form:hidden path="coreTeam" value="false" />
							<c:if test="${employee.coreTeam == true}">
         						yes
      						</c:if>
							<c:if test="${employee.coreTeam == false}">
         						no
      						</c:if>
						</div>
						</div> --%>
						
						
					<div class="col50per">
						<div class="fieldLbl">
							<label>Employee Criticality :
							</label>
						</div>
						<form:hidden path="criticality" id="empCriticality" />
						<c:if test="${employee.criticality == 'H'}">
         					High
      					</c:if>
						<c:if test="${employee.criticality == 'M'}">
         					Medium
      					</c:if>	
						<c:if test="${employee.criticality == 'L'}">
         					Low
      					</c:if>	
					</div>
						
						</div>
						<div class="formRow">
					<div class="col50per" id ="allocationDiv">
						<div class="fieldLbl">
							<label>Allocation % <span class="req"></span>:</label>
						</div>
						<div class="inputField">
							${employee.allocation}
							<form:hidden path="allocation" id="drpallocation" />
						</div>
					</div>
					
					<div class="col50per">
						<div class="fieldLbl">
						<label>VCO : </label>
						</div>
						<div class="inputField">
						<form:hidden path="vco" value="false" />
						<c:if test="${employee.vco == true}">
						yes
						</c:if>
						<c:if test="${employee.vco == false}">
						no
						</c:if>
						</div>
						</div>
					
				</div>
				
				<%-- <div class="formRow">
				
				<div class="col50per">
				</div>
				
					<div class="col50per">
						<div class="fieldLbl">
							<label>BI : </label>
						</div>
						<div class="inputField">
							<form:hidden path="bi" value="false" />
							<c:if test="${employee.bi == true}">
         						yes
      						</c:if>
							<c:if test="${employee.bi == false}">
         						no
      						</c:if>
						</div>
					</div>
				</div> --%>
				
				</section>
				</div>
				<br>
					<h1 style="color:#86A5F7">Technical Information:</h1>
				<div class="formFields clearfix">
				<section>
					<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Request for PSA ID : </label>
						</div>
						<div class="inputField">
							<form:hidden path="PSAIdReq" value="false" />
							<c:if test="${employee.PSAIdReq == true}">
         						yes
      						</c:if>
							<c:if test="${employee.PSAIdReq == false}">
         						no
      						</c:if>
						</div>
					</div>
					<c:if test="${employee.PSAIdReq == true}">
						<div class="col50per" id="divdob" style="display: none;">
						<div class="fieldLbl">
							<label id="dobLabel">Date of Birth (dd/mm/yyyy) :
							</label>
						</div>
						
						<div class="inputField">
							${employee.dobDate}
							<form:hidden path="dobDate" id="txtdob" /> 
      					</div>
						</div>
						</c:if>
			</div>
					<div class="formRow">
						
					<div class="col50per" id ="Tech">
						<div class="fieldLbl">
							<label>Technical / Development :
							</label>
						</div>
						<div class="inputField">
						
							<form:hidden path="type" id="drpTechDev"/>
							<c:if test="${employee.type == '0'}">
         						NA
      						</c:if>
							<c:if test="${employee.type == '1'}">
         						Industrialisator
      						</c:if>
      						<c:if test="${employee.type == '2'}">
         						Developer
      						</c:if>
      						<c:if test="${employee.type == '3'}">
         						Transversal
      						</c:if>
      						<c:if test="${employee.type == '4'}">
         						High Cycle
      						</c:if>
						</div>
					</div> 
					
					
					</div>
				
				
				
			
				
				<div class="formRow">
				
					<div class="col50per" id ="Indus">
						<div class="fieldLbl">
							<label>INDUS GOAL :
							</label>
						</div>
						<div class="inputField">
							<form:hidden path="indusGoals" id="drpIndusGoal"/>
								${employee.indus.indusGoalName}
						</div>
					</div>
					
						<div class="col50per">
						<div class="fieldLbl">
							<label>Request for VM :</label>
						</div>
						<div class="inputField">
							<form:hidden path="psaLibTypeID" id="drppsaLibType"/>
							${employee.psaLibType.psaLibName}
						</div>
						</div>
				</div>
				
				<div class="formRow">
				
				<div class="col50per" id ="RoleT">
						<div class="fieldLbl">
							<label>Role / Technology :
							</label>
						</div>
						<div class="inputField">
							<form:hidden path="roleTech" id="drpRoleTech"/>
							${employee.role.roleTechName}
						</div>
					</div>
				</div>
				
				<div class="formRow">
					<!-- <div class="col50per" id="PCDetails" style="display: none;"> -->
						<div class="col50per" style="display: block;">
							<div class="fieldLbl">
								<label>PC Hostname <!-- <span class='req'>*</span> -->:</label>
							</div>
							<c:if test="${(checkUserType eq 'BundleEM')}">
						<div class="inputField">
							<form:hidden path="pcSerialNumber" id="pcDetailsID" />
							${employee.pcSerialNumber}
							
					</div>
					</c:if>
					<c:if test="${(checkUserType eq 'RM')}">
									
							<div class="inputField">
							<form:input path="pcSerialNumber" id="pcDetailsID" class="input-control" style="width:168px; background-color: #ffffff;"/> <!-- data-validation="required" data-validation-help="Please enter value" -->
							</div>
						</c:if>
					</div>
					<%-- <div class="col50per" id="MACAddr" style="display: none;">
						<div class="fieldLbl">
							<label id="corpID">MAC Address :</label>
							
						</div>
						<div class="inputField">
							<form:hidden path="macAddress" id="macAddressID"/>
							${employee.macAddress}
						</div>
					</div> --%>
				</div>
				
				
				<!-- Engg - Start -->
				 <div class="formRow" id ="sdmDiv">
						<div class="col50per" style="display: block; background-color: #ffffff;">
									
									<div class="fieldLbl">
										<label>Stellantis Order Giver<span class="req">*</span>:</label>
									</div>
									
									<div class="inputField" >
									${employee.orderG}
									<form:hidden path="orderG" id="orderGid" />
									</div>
								
					   </div>
						<div class="col50per">
							<div class="fieldLbl">
								<label>SDM (CAE) : </label>
							</div>
							<div class="inputField">
							<form:hidden path="sdm" />
							<c:if test="${employee.sdm == true}">
         						yes
      						</c:if>
							<c:if test="${employee.sdm == false}">
         						no
      						</c:if>
							</div>
						</div>
						
				</div>
				
				
				
				<div class="formRow" id ="businessAp">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Business Application Services <span class="req">*</span>:</label>
						</div>
						<div class="inputField">
							<form:hidden path="businessAs" id="drpBusinessAs"/>
							${employee.businessAs.basName}
						</div>
					</div>
					
					<div class="col50per" style="display: block; background-color: #ffffff;">
							
							<div class="fieldLbl">
								<label>Other Application Services :</label>
							</div>
							
							<div class="inputField" >
							${employee.otherAs}
							<form:hidden id="otherAsId" path="otherAs" />
							</div>
						
					</div>
					
				</div>
				
				
				<div class="formRow" id ="cfaoDiv">
				
				<div class="col50per" style="display: block;">
						<div class="fieldLbl">
							<label>CFAO Group :
							</label>
						</div>
						<div class="inputField">
							<form:hidden path="cfao" id="drpCfao"/>
							${employee.cfao.cfaoName}
						</div>
					</div>
					
			</div>
				
				
				<!-- Engg - End -->
				
				</section>
				</div>
				<%-- <div class="formRow" style="margin-top:10px;">
						<div class="fieldLbl">
							<label>Clarity Code & Allocation :</label>
						</div>
						<div class="inputField">
						${employee.clarityCodeAllocation}
							<form:hidden path="clarityCodeAllocation" id="clarityCA" />
						</div>
				</div> --%>
			
				<div class="formRow" style="margin-top:10px;">
						<div class="fieldLbl">
							<label>Comment :</label>
						</div>
						<c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM')}">
							<div class="inputField">
								<form:textarea path="comment" maxlength="200" id="comment" class="textarea-control" />
							</div>
						</c:if>
						<c:if test="${checkUserType eq 'ViewMode'}">
							<div class="inputField">
								${employee.comment}
								<form:hidden path="comment" id="comment" />
							</div>
						</c:if>
				</div>
				
				</div>
				
			<c:if test="${checkUserType eq 'RM'}">
				<c:choose>
					<c:when test="${((rescourceStatus eq 'EMSubmitted') and (checkUserType eq 'RM')) or ((rescourceStatus eq 'RMRejected') and ((checkUserType eq 'BundleEM'))) }">
		                <div class="text-center">
					 	 <input type="submit" value="Approve" title="Approve" id="btnApprove" 
							class="btn-primary" />
						
					 	 <input type="submit" value="Reject" title="Reject" id="btnReject" 
							class="btn-primary" />
						</div>
					</c:when>
					<c:otherwise>
				       <div class="text-center">
							<a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Back" class="btn-primary" style="display:inline-block;">Back</a>
						</div>
			    	</c:otherwise>
			    </c:choose>
			</c:if>    
			    
				<c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'ViewMode') or (checkUserType eq 'RM_PMO')}">
					<div class="text-center">
						<a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Back" class="btn-primary" style="display:inline-block;">Back</a>
					</div>
				</c:if>
				
				
			</form:form>
		</div>
	</section>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.form-validator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/date.js"></script>
	<script type="text/javascript">
		
		
		$(document).ready(function() {
			
			//Engg - start
				 var pp = ${employee.primaryprogram.primaryProgramId};
				if (pp == "10"){
					$("#sdmDiv").css("display", "");
					$("#businessAp").css("display", "");
					$("#cfaoDiv").css("display", "");
					
					$("#RoleT").css("display", "none");
					$("#Tech").css("display", "none");
					$("#Indus").css("display", "none"); 
					
					//$("#drpIndusGoal").val("NA")
				}else{
					$("#sdmDiv").css("display", "none");
					$("#businessAp").css("display", "none");
					$("#cfaoDiv").css("display", "none");
				} 
			//Engg - End
			
			
			var locationId = '${employee.location.stateId}';
			var countryId = '${employee.country.countryId}';
			
			//$("#PCDetails").css("display","none");
			/* $("#MACAddr").css("display","none"); */
			
			if (countryId == "2") { 
				$("#staffingrr").css("display", "");
				$("#divCapSup").css("display", "");
				$("#divCapSupEmail").css("display", "");
			} 
			else if (countryId == "1" || countryId == "9") { //mehens
				$("#staffingrr").css("display", "");
				$("#divCapSup").css("display", "none");
				$("#divCapSupEmail").css("display", "none");
			}
			else { 
				$("#staffingrr").css("display", "none"); 
				$("#divCapSup").css("display", "none");
				$("#divCapSupEmail").css("display", "none");
			}
			
			
			/* $.each( ${VLANList}, function( vlanIndex, val ) {
				//alert("VLAN Check " +val);
				if(val == locationId){
					//$("#PCDetails").css("display","");
					$("#MACAddr").css("display","");
				}
			}); */
			
			 $("#editEmployee").submit(function() {
				 if($('#editEmployee').isValid()){
					 $("#btnApprove").attr("disabled", true);
				        $("#btnReject").attr("disabled", true);
				 	}
			       
			        return true;  
			 });
			 
			 $("#jiraNum").change(function(){
					var jira = $("#jiraNum").val();
					
					if(isNaN(jira)){
						alert("Jira Number should be only number");
						$("#jiraNum").val('${employee.jiraNumber}');
					}else if(jira.length > 10){
						alert("Jira Number length should be less than 10");
						$("#jiraNum").val('${employee.jiraNumber}');
					}
				});
			 
			
								$("#btnApprove").click(function() {
									
									$("#drpprimprogram").val('${employee.primaryprogram.primaryProgramId}');
									//$("#project").val('${employee.project}');
									var project =  "${employee.project}";
									project = project.replace(/'/, "\'");
									$("#project").val(project);
									
									$("#drpSupervisor").val('${employee.manager.id}');
									$("#drpCountry").val('${employee.country.countryId}');
									$("#drpLocation").val('${employee.location.stateId}');
									$("#drpGrade").val('${employee.grade.gradeId}');
									
									//Engg
									var pId = "${employee.primaryprogram.primaryProgramId}";
									if(pId == 10){
									$("#orderGid").val('${employee.orderG}');
									$("#drpBusinessAs").val('${employee.businessAs.basId}');
									$("#otherAsId").val('${employee.otherAs}');
									$("#drpCfao").val('${employee.cfao.cfaoId}');
									$("#sdm").val('${employee.sdm}');
									} 
									//Engg
									
									$("#drpemprole").val('${employee.roleid.ref_id}');
									if(${employee.dob ne null}){
										 $("#txtdob").val('${employee.dobDate}'); 
										}
									
									$("#drpGlobalGrade").val('${employee.globalGrade.globalGradeId}'); //new
									$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
									$("#drpEM").val('${employee.EM.id}');
									$("#drpresourceManager").val('${employee.resourceManager.resourceMgrId}');
									$("#drpPrimaryTechnology").val('${employee.primaryTechnology.technologyId}');
									$("#drpSecondaryTechnology").val('${employee.secondaryTechnology.technologyId}');//mehens
									$("#drpVendor").val('${employee.vendor.vendorId}');
									$("#txtJoiningDate").val('${employee.onboardingDate}');
									if(${employee.EMSubmitDT ne null}){
									 $("#EMSubmitDT").val(new Date('${employee.EMSubmitDT}')); 
									}
									if(${employee.creationdate ne null}){
									 $("#creationdate").val(new Date('${employee.creationdate}'));
									}
									if(${employee.cgEntity ne null}){
										$("#drpCapEntity").val('${employee.cgEntity}');
									}
									$("#txtEndDate").val('${employee.offboardingDate}'); 
									$("#txtBillingDate").val('${employee.billingStartDate}');
									
									$("#drpbis").val('${employee.bis.bis_id}');
									$("#rdEmpSex").val('${employee.empSex}');
									
									$("#PSAIdReq").val('${employee.PSAIdReq}');
									
									/* $("#timeNMaterial").val('${employee.timeNMaterial}'); */
									$("#timeMat").val('${employee.timeMat}');//mehens
									
									$("#drpTechDev").val('${employee.type}');
									$("#drpIndusGoal").val('${employee.indusGoals}');
									$("#drpRoleTech").val('${employee.roleTech}'); 
									$("#id").val('${employee.id}'); 
									$("#empType").val('${employee.empType}'); 
									$("#resourceStatus").val('Approve'); 
									
									
									$("#drppsaLibType").val('${employee.psaLibTypeID}');
									$("#txtGGID").val('${employee.ggId}'); 
									$("#requestor").val('${employee.requestor.id}');  
									$("#isVLANmailDone").val('${employee.isVLANmailDone}');
									//$("#pcDetailsID").val('${employee.pcSerialNumber}');
									/* $("#macAddressID").val('${employee.macAddress}'); */
									$("#drpdemand").val('${employee.demandType.id}');
									$("#empTimeMat").val('${employee.timeMat}');//new
									
									//Bhavna add new fields
									$("#drpallocation").val('${employee.allocation}');
									$("#drpworkingbis").val('${employee.workingBis.bis_id}');
									$("#capMgr").val('${employee.capManager}');
									$("#capMgrEmail").val('${employee.capManagerEmail}');
									$("#capSup").val('${employee.capSupervisor}');
									$("#capSupEmail").val('${employee.capSupervisorEmail}');
									//Bhavna add new fields - end
									
								});
								
								$("#btnReject").click(function() {
									
									$("#drpprimprogram").val('${employee.primaryprogram.primaryProgramId}');
									//$("#project").val('${employee.project}');
									var project =  "${employee.project}";
									project = project.replace(/'/, "\'");
									$("#project").val(project);
									
									$("#drpSupervisor").val('${employee.manager.id}');
									$("#drpCountry").val('${employee.country.countryId}');
									$("#drpLocation").val('${employee.location.stateId}');
									$("#drpGrade").val('${employee.grade.gradeId}');
									$("#drpemprole").val('${employee.roleid.ref_id}');
									
									//Engg
									var ppId = "${employee.primaryprogram.primaryProgramId}";
									if(ppId == 10){
										$("#orderGid").val('${employee.orderG}');
										$("#drpBusinessAs").val('${employee.businessAs}');
										$("#otherAsId").val('${employee.otherAs}');
										$("#drpCfao").val('${employee.cfao.cfaoId}');
										$("#sdm").val('${employee.sdm}');	
									}
									else{
										$("#orderGid").val("");
										$("#drpBusinessAs").val("");
										$("#otherAsId").val("");
										$("#drpCfao").val("");
									}
									//Engg
									
									if(${employee.dob ne null}){
										 $("#txtdob").val('${employee.dobDate}'); 
									}
									
									$("#drpGlobalGrade").val('${employee.globalGrade.globalGradeId}'); //new
									$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
									$("#drpEM").val('${employee.EM.id}');
									$("#drpresourceManager").val('${employee.resourceManager.resourceMgrId}');
									$("#drpPrimaryTechnology").val('${employee.primaryTechnology.technologyId}');
									$("#drpSecondaryTechnology").val('${employee.secondaryTechnology.technologyId}');//mehens
									$("#drpVendor").val('${employee.vendor.vendorId}');
									$("#txtJoiningDate").val('${employee.onboardingDate}');//
									$("#txtEndDate").val('${employee.offboardingDate}');
									$("#txtBillingDate").val('${employee.billingStartDate}');
									if(${employee.EMSubmitDT ne null}){
										$("#EMSubmitDT").val(new Date('${employee.EMSubmitDT}')); //new
									}
									if(${employee.creationdate ne null}){
									 $("#creationdate").val(new Date('${employee.creationdate}'));//new
									}
									if(${employee.cgEntity ne null}){
										$("#drpCapEntity").val('${employee.cgEntity}');
									}
							
									$("#drpbis").val('${employee.bis.bis_id}');
									$("#rdEmpSex").val('${employee.empSex}');
									
									$("#PSAIdReq").val('${employee.PSAIdReq}');
									
									/* $("#timeNMaterial").val('${employee.timeNMaterial}'); */
									$("#timeMat").val('${employee.timeMat}');//mehens
									
									$("#drpTechDev").val('${employee.type}');
									$("#drpIndusGoal").val('${employee.indusGoals}');
									$("#drpRoleTech").val('${employee.roleTech}'); 
									
									$("#id").val('${employee.id}'); 
									$("#empType").val('${employee.empType}'); 
									$("#resourceStatus").val('Reject'); 
									
									$("#drppsaLibType").val('${employee.psaLibTypeID}');
									$("#txtGGID").val('${employee.ggId}');
									
									if(${employee.requestor ne null}){
										$("#requestor").val('${employee.requestor.id}');
									}
									$("#isVLANmailDone").val('${employee.isVLANmailDone}');
									//$("#pcDetailsID").val('${employee.pcSerialNumber}');
									/* $("#macAddressID").val('${employee.macAddress}'); */
									$("#drpdemand").val('${employee.demandType.id}');
									//Bhavna add new fields
									$("#drpallocation").val('${employee.allocation}');
									$("#drpworkingbis").val('${employee.workingBis.bis_id}');
									$("#capMgr").val('${employee.capManager}');
									$("#capMgrEmail").val('${employee.capManagerEmail}');
									$("#capSup").val('${employee.capSupervisor}');
									$("#capSupEmail").val('${employee.capSupervisorEmail}');
									
									//Bhavna add new fields - end
								});
								if(${employee.PSAIdReq} == true) {
							 		$("#divdob").css("display", "");
								}
								else{
									$("#divdob").css("display", "none");
								} 
								function toDate(dateStr) {
									  var parts = dateStr.split("/")
									  return new Date(parts[2], parts[1] - 1, parts[0])
									}
								
		
		});
		
		var resMgrCheck = $("#drpCountry").val('${employee.country.countryId}');
		if(resMgrCheck.val()==1 || resMgrCheck.val()==6){
			$("#divresourcemanager").css("display", ""); 
		}
		else{
			$("#divresourcemanager").css("display", "none"); 
		}
	</script>
</body>
</html>
