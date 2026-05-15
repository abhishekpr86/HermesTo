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
			<h1 class="pull-left">Onboarding Tool</h1>
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
			<c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM') or (checkUserTypeforUM ne null)}">
				<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li> --%>
				<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Onboarding Requests</a></li>
			</c:if>

			<c:if test="${(checkUserType eq 'BundleEM')}"><!-- EM -->
					<li class="active"><a href="${pageContext.request.contextPath}/allResourceList" title="view List">View Resources</a></li>
			</c:if>
			
			<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO') or (checkUserType eq 'ASL')}">
					<li class="active"><a href="${pageContext.request.contextPath}/allResourceList" title="view List">View / Edit Resources</a></li>
			</c:if>
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM')}">
					<li class="active"><a href="${pageContext.request.contextPath}/allResourceList" title="View List">View Resources</a></li>
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
		<section>
		<div class="reqText">* indicates required fields</div>
		<h1 style="color:#86A5F7">Resource Information:</h1>
		<div class="formFields clearfix" id="mainDIV" >
			
			<c:url var="editAction" value="/employee/updatePSAID"></c:url>
			<c:url var="findCountryStateURL" value="/states" />
			<c:url var="findBIS" value="/bis" />
			<c:url var="checkCorpIdExistsEdit" value="/checkCorpIdExistsEdit" />
			<c:url var="checkPsaIdExistsEdit" value="/checkPsaIdExistsEdit" />
			<c:url var="checkEmployeeEmailExistsEdit" value="/checkEmployeeEmailExistsEdit" />
			<c:url var="getDetailsByCorpIDFromActiveDirectoryExits" value="/getDetailsByCorpIDFromActiveDirectoryExits" />
			<c:url var="checkPSAId" value="/checkPSAId" />
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
			<form:form action="${editAction}" commandName="employee"
				method="POST" id="editEmployee">
				<div class="formRow">
					<div class="col50per">
						<div>
							<form:hidden id="empType" path="empType" />
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<form:hidden id="EMReadOnly" path="EMReadOnly" /><!-- EM -->
							<form:hidden id="id" path="id" />	
							<%-- <form:hidden id="preonbemp" path="preonbemp" /> --%>
							<%-- <form:hidden id="archiveType" path="archiveType" />	 --%>	
							<form:hidden id="externalEmpId" path="externalEmpId" />	
							<c:if test="${employee.requestor ne null}">
								<form:hidden id="requestor" path="requestor" />
							</c:if>					
							<div class="fieldLbl" id="empID" style="display: block;">
								<label>Emp ID :
								</label>
							</div>
						
							<div class="inputField">
								${employee.empId}
							<form:hidden id="txtPSAID" path="empId" />
							
							</div>
						</div>
					</div>
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
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Entity :
							</label>
						</div>
						<div class="inputField">
							
							${employee.cgEntity}
							
						</div>
					</div>
					<div class="col50per">
					<div class="fieldLbl">
							<label>Role :
							</label>
						</div>
						<div class="inputField">
							<form:hidden path="emprole" id="drpemprole"/>
							${employee.roleid.ref_name}
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
							<label>Location :
							</label>
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
							<label>Secondary Skill :
							</label>
						</div>
						<div class="inputField">
						${employee.secondaryTechnology.technologyName}
						<form:hidden path="secondaryTechnology" id="drpSecondaryTechnology" />
						</div>
					</div>
					
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
				<div class="col50per" id="divGlobalGrade" style="display: block;">
						<div class="fieldLbl">
							<label id="GlobalGrade">Global Grade :
							</label>
						</div>
						<div class="inputField">
						
							${employee.globalGrade.name}
								<form:hidden path="globalGrade" id="drpGlobalGrade" />
						</div>
					</div>
						<div class="col50per">
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
						
							<c:if test="${employee.buddy !=null}">
         						${employee.buddy.firstName} ${employee.buddy.lastName} (${employee.buddy.corpId})
         						
      						</c:if>
						
					<form:hidden path="manager" id="drpSupervisor" />	
							
						</div>
					</div> 
					<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy Email :
							</label>
						</div>
						<div class="inputField">
						
							${employee.onboardingEmail}
					<form:hidden path="managerEmail" id="mgrEmail" />	
							
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
				
					</section>
				</div>
				<br>
				<h1 style="color:#86A5F7">Project Information:</h1>
					<div class="formFields clearfix">
					<section>
					
					<div class="formRow">
						<div class="col50per">
							<div class="fieldLbl">
								<label>Primary Program : </label>
							</div>
							<div class="inputField">
								
								${employee.primaryprogram.primaryProgramName}
								<form:hidden id="drpprimprogram" path="primaryprogram"/>
								
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
							<label>Bundle EM :
							</label>
						</div>
						<div class="inputField">
						${employee.bundleEM.bundleEmName}
						<form:hidden path="bundleEM" id="drpbundleEM" />
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
				<div class="col50per">
						<div class="fieldLbl">
							<label>Staff JIRA # :
							</label>
						</div>
						<div class="inputField">
							<form:hidden path="jiraNumber" id="jiraNum"/>
								${employee.jiraNumber}
						</div>
					</div>
					</div>
					<div class="formRow">
						<div class="col50per">
						<div class="fieldLbl">
							<label>On-boarding Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
						
						<fmt:formatDate value="${employee.joiningDate}" pattern="dd/MM/yyyy"/>
						
					<%-- 	<form:hidden path="joiningDate" id="txtJoiningDate" /> --%>
						</div>
					</div>
						<div class="col50per" id="staff">
						<div class="fieldLbl">
							<label>GTD # :
							</label>
						</div>
						<div class="inputField">
						
						${employee.staffingRR}
						<form:hidden path="staffingRR" id="staffrr" />
							
						</div>
					</div>
				</div>
					<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Planned Off-boarding Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							
							<fmt:formatDate value="${employee.endDate}" pattern="dd/MM/yyyy" />
							<%-- <form:hidden path="endDate" id="txtEndDate" /> --%>
						
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
							
							<fmt:formatDate value="${employee.billingDate}" pattern="dd/MM/yyyy" />
							<form:hidden  path="billingDate" id="txtBillDate" /> <!-- Engg -->
						
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
					<div class="formRow">
					
					 <div class="col50per" id="divOffShoreEm" style="display: none;">
						<div class="fieldLbl">
							<label>Offshore DM/EM :
							</label>
						</div>
						<div class="inputField">
						
							${employee.offshoreEm.offshoreEmName}
								<form:hidden path="offshoreEm" id="drpoffShoreEm" />
						</div>
					</div>
					
					
				 <%--  <div class="col50per">
						<div class="fieldLbl">
							<label>GitHub Copilot : </label>
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
					</div>  --%>
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>GitHub Copilot : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="bi" id="Chkbi"/>
							Yes
						</div>
					</div> 
					
					</div>
					
					<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Name :</label>
							<%-- <form:hidden id="spocId" path="spoc" /> --%>
						</div>
						<div class="inputField">
							<form:input path="spocName" id="txtSpocName" readonly="true" style="width: 250px;"/>
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Email :
							</label>
						</div>
						<div class="inputField">
							<form:input path="spocEmail" id="txtSpocEmail" readonly="true" style="width: 250px; font-size: 100%;"/>
						</div>
					</div>
					</div>
					
					</section>
				</div>
				
				<h1 style="color:#86A5F7">Technical Information:</h1>
				<div class="formFields clearfix">
				<section>
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Request for PSA ID : 
							</label>
						</div>
						<div class="inputField">
						
						<c:if test="${employee.PSAIdReq == false}">
         					No
      					</c:if>
						<c:if test="${employee.PSAIdReq == true}">
         					Yes
      					</c:if>
						<form:hidden id="PSAIdReqVal" path="PSAIdReq" />			
						</div>
					</div>
						<div class="col50per" id="divdob" style="display: none;">
						<div class="fieldLbl">
							<label>Date of Birth (dd/mm/yyyy) for PSA ID generation:
							</label>
						</div>
						<div class="inputField">
							<c:if test="${checkUserType ne 'BundleEM'}">
								<fmt:formatDate value="${employee.dob}" pattern="dd/MM/yyyy"/>
							</c:if>
							<%-- <form:hidden path="dob" id="txtDOB" /> --%>
						</div>
					</div>
				</div>
				
				<div class="formRow">
					<c:if test="${employee.PSAIdReq == true}">
						<div class="col50per">
								<div class="fieldLbl">
									<label id="psaIdLabel">PSA ID :
									</label>
								</div>
								<div class="inputField">
								 <form:input path="psaId" size="8" id="txtPSAIDExt"  data-validation="required alphanumeric" 
								data-validation-help="Please enter alphanumeric data" style="width: 120px;"
								class="input-control" /> 
								
								</div>
						</div>
					</c:if>
					<c:if test="${employee.PSAIdReq == false}">
						<div class="col50per">
								<div class="fieldLbl">
									<label id="psaIdLabel">PSA ID :
									</label>
								</div>
								<div class="inputField">
								<form:hidden path="psaId" id="txtPSAIDExt" /> NO PSA ID
								</div>
						</div>
					</c:if>
					<div class="col50per">&nbsp;</div>
				</div>
				
				<c:if test="${employee.PSAIdReq == true}">
					<div class="formRow" >
						<div class="col50per">
									
								<div class="fieldLbl">
									<label>PSA ID Requested On<span class="req" >*</span>:
									</label>
								</div>
								<div class="inputField">
									
									<c:if test="${(employee.requestedDate eq null) or (employee.requestedDate eq '')}">
	         							<form:input path="requestedDate" id="reqDate" style="width: 120px;"
										class="input-control" data-validation="date"
										data-validation-format="dd/mm/yyyy"
										data-validation-help="Please enter date in dd/mm/yyyy format" />								
	      							</c:if>
									<c:if test="${employee.requestedDate ne null}">
	         							<fmt:formatDate value="${employee.requestedDate}" pattern="dd/MM/yyyy"/>	
	         							<form:hidden path="requestedDate" id="reqDate" />
	      							</c:if>
									</div>
								
						</div>
						<div class="col50per">
							
								<div class="fieldLbl">
									<label id="psaGenLabel">PSA ID Generated On:
									</label>
								</div>
									<div class="inputField">
										
									<c:if test="${(employee.generatedDate eq null) or (employee.generatedDate eq '')}">
	         							<form:input path="generatedDate" id="genDate" style="width: 120px;"
										class="input-control" data-validation="date" 
										data-validation-format="dd/mm/yyyy" 
										data-validation-help="Please enter date in dd/mm/yyyy format" />								
	      							</c:if>
									<c:if test="${employee.generatedDate ne null}">
	         							<fmt:formatDate value="${employee.generatedDate}" pattern="dd/MM/yyyy"/>
	         							<form:hidden path="generatedDate" id="genDate" />
	      							</c:if>
									</div>
								
						</div>
					</div>
					
					
				<!-- #VMNUMBER -->
				<c:if test="${employee.psaLibTypeID != 0}">
					<div class="formRow" >
						<div class="col50per">
									
								<div class="fieldLbl">
									<label>VM Number Requested On:
									</label>
								</div>
								<div class="inputField">
								
	         							<form:input path="vmrequestedDate" id="vmreqDate"  pattern="\d{1,2}/\d{1,2}/\d{4}" placeholder="dd/mm/yyyy" style="width: 120px;"
										class="input-control"/>		
														
									</div>
								
						</div>
						<div class="col50per">
							
								<div class="fieldLbl">
									<label id="psaGenLabel">VM Number Generated On:
									</label>
								</div>
									<div class="inputField">
	         							<form:input path="vmgeneratedDate" id="vmgenDate" pattern="\d{1,2}/\d{1,2}/\d{4}" placeholder="dd/mm/yyyy" style="width: 120px;"
										class="input-control" />	
									</div>
						</div>
					</div>
					</c:if>
					<div class="formRow" >
						<c:if test="${employee.psaLibTypeID != 0}">
							<div class="col50per">
									<div class="fieldLbl">
										<label id="vmNumberLabel">VM Number :</label>
									</div>
									<div class="inputField">
										<form:input path="vmNumber" id="drpVmNumber" style="width: 120px;" 
										class="input-control" />
									</div>
									
							</div>
						</c:if> <!-- Engg -->
						<div class="col50per">
									
								<div class="fieldLbl">
									<label id="pcaEmailLabel">PCA Email :
									</label>
								</div>
								<div class="inputField">
									<form:input path="PCAEmail" id="drpPCAEmail" class="input-control" />
								</div>
						</div>
						<%-- </c:if> --%><!-- Engg -->
					</div>
				</c:if>
				
				<div class="formRow">
				<div class="col50per" id="IndusD">
					<div class="fieldLbl">
							<label>INDUS GOAL :</label>
					</div>
					<div class="inputField">
							<form:hidden path="indusGoals" id="drpIndusGoal"/>	
							${employee.indus.indusGoalName}	
					</div>
				</div>
				<div class="col50per" id="RoleT">
					<div class="fieldLbl">
							<label>Role / Technology :</label>
					</div>
					<div class="inputField">
							<form:hidden path="roleTech" id="drpRoleTech"/>			
							${employee.role.roleTechName}
					</div>
				</div>
			</div>
			
				<div class="formRow">
				
					<div class="col50per" id="divVendor" style="display: none;">
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
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Request for VM :</label>
						</div>
						<div class="inputField">
							<form:hidden path="psaLibTypeID" id="drppsaLibType"/>
							${employee.psaLibType.psaLibName}
								
						</div>
				</div>
				
				<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Hermes Remote Access Request :</label>
						</div>
						<div class="inputField">
						
							<c:if test="${employee.HRAReq == false}">
	         					No
	      					</c:if>
							<c:if test="${employee.HRAReq == true}">
	         					Yes
	      					</c:if>
							<form:hidden id="HRAReqVal" path="HRAReq" />			
						</div>
				</div> --%>
			</div>
			
				<div class="formRow">
					<div class="col50per">
						<div>
							
							<div class="fieldLbl" style="display: block;">
								<label>PC Hostname <span class='req'>*</span>:</label>
							</div>
							
							<div class="inputField">
									<form:hidden path="pcSerialNumber" id="pcDetailsID" />
									${employee.pcSerialNumber}
							</div>
						</div>
					</div>
					<%-- <div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">MAC Address :
							</label>
							
						</div>
						<div class="inputField">
		
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
				<br>
				
				
			    <div class="formRow" style="margin-top:10px;">
						<div class="fieldLbl">
							<label>Comment :</label>
						</div>
						<div class="inputField">
							<form:textarea path="comment" maxlength="200" id="comment"
								class="textarea-control" />
						</div>
				</div>
				
				</div>
				
				<div class="formRow">
				</div>
			
	                <div class="text-center">
	                <c:if test="${(checkUserType ne 'BundleEM')}"><!-- EM -->
				 	 <input type="submit" value="Save" title="Save" id="btnSubmit" 
						class="btn-primary" />
					</c:if>
					 <a href="${pageContext.request.contextPath}/allResourceList" title="Back" class="btn-primary" style="display:inline-block;">Back</a>
					</div>
			
				
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

$(document).ready(function() 
	    {
	    //$("#drpVmNumber").val('${employee.vmNumber}');
	if ($("#EMReadOnly").val() == 'true') {//EM
		$("#btnSubmit").css("display","none");
		$('div *').prop('disabled',true);
	}
	
	//Engg - start
	 var pp = ${employee.primaryprogram.primaryProgramId};
		if (pp == "10"){
			$("#sdmDiv").css("display", "");
			$("#businessAp").css("display", "");
			$("#cfaoDiv").css("display", "");
			
			$("#RoleT").css("display", "none");
			//$("#Tech").css("display", "none");
			$("#IndusD").css("display", "none"); 
			
			//$("#drpIndusGoal").val("NA")
		}else{
			$("#sdmDiv").css("display", "none");
			$("#businessAp").css("display", "none");
			$("#cfaoDiv").css("display", "none");
		} 
    //Engg - End
	
	
	 $("#editEmployee").submit(function () {
	     
		 if($('#editEmployee').isValid()){
			 $("#btnSubmit").attr("disabled", true);
		 	}
	       
	        return true;
	    });
	
	$("#btnSubmit").click(function() {
		//$("#drpVmNumber").val('${employee.vmNumber}');
		$("#txtJoiningDate").val(new Date('${employee.joiningDate}'));
		$("#txtEndDate").val(new Date('${employee.endDate}'));
		$("#drpIndusGoal").val('${employee.indus.indusGoalId}');
		$("#drpRoleTech").val('${employee.role.roleTechId}');
		$("#drpemprole").val('${employee.roleid.ref_id}');
		$("#drppsaLibType").val('${employee.psaLibType.psaLibId}');
		
		$("#jiraNum").val('${employee.jiraNumber}');
		$("#bi").val('${employee.bi}');//mehens
		/* $("#timeNMaterial").val('${employee.timeNMaterial}'); */
		$("#drpdemand").val('${employee.demandType.id}'); //new
		
		$("#timeMat").val('${employee.timeMat}');//new
		
		//Engg
		$("#orderGid").val('${employee.orderG}');
		$("#drpBusinessAs").val('${employee.businessAs.basId}');
		$("#otherAsId").val('${employee.otherAs}');
		$("#drpCfao").val('${employee.cfao.cfaoId}');
		//Engg
		
		$("#drpbis").val('${employee.bis.bis_id}');
		$("#pcDetailsID").val('${employee.pcSerialNumber}');
		
		if(${employee.requestor ne null}){
			$("#requestor").val('${employee.requestor.id}');
		}
	});
	
	var offShoreValueCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(offShoreValueCheck.val()==2){
		$("#divOffShoreEm").css("display", "");
		$("#staffingrr").css("display", "");
		$("#divphase").css("display", "");
		$("#divCapSup").css("display", "");
		$("#divCapSupEmail").css("display", "");
		
	}
	else if(offShoreValueCheck.val()==1 || offShoreValueCheck.val()==9){//mehens
		$("#staffingrr").css("display", "");
		$("#divOffShoreEm").css("display", "none");
		$("#divphase").css("display", "none");
		$("#divCapSup").css("display", "none");
		$("#divCapSupEmail").css("display", "none");
	}
	else{
		$("#divOffShoreEm").css("display", "none");
		$("#divphase").css("display", "none");
		$("#staffingrr").css("display", "none"); 
		$("#divCapSup").css("display", "none");
		$("#divCapSupEmail").css("display", "none");
	}
	
	var resMgrCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(resMgrCheck.val()==1){
		$("#divresourcemanager").css("display", ""); 
	}
	else{
		$("#divresourcemanager").css("display", "none"); 
	}
	
	
			if ($("#userReadOnly").val() == 'true') {
				$("#btnSubmit").css("display","none");
				$('div *').prop('disabled',true);
			}
			
			if ($("#empType").val() == 'Internal') {
				$("#empID").css("display","");
				$("#psaID").css("display","none");
				$("#divVendor").css("display","none");
				$("#internalToPSA").css("display","");
				$("#corpID")
				.html(
						"CORP ID <span class='req'>*</span>:");
				$("#txtCorpID").attr("data-validation","required");
				$("#GlobalGrade")
				.html(
						"Global Grade :");
				$("#drpGlobalGrade").attr("data-validation","required");
			}
			if($("#empType").val() == 'External') {
				$("#psaID").css("display","");
				$("#internalToPSA").css("display","none");
				$("#empID").css("display","none");				
				$("#divVendor").css("display","");
				$("#corpID")
				.html(
						"CORP ID <span></span>:");
				$("#txtCorpID").removeAttr('data-validation');
				$("#GlobalGrade")
				.html(
						"Global Grade <span></span>:");
				$('#drpGlobalGrade').removeAttr('data-validation');
	
			} 
			$("#drpCountry").val('${employee.country.countryId}');
			$("#drpEntity").val('${employee.entity.entityId}');
			$("#empSex").val('${employee.empSex}');
			$("#criticality").val('${employee.criticality}');//mehens-new
			$("#drpCgEntity").val('${employee.cgEntity}');
			$("#drpoffShoreEm").val('${employee.offshoreEm.offshoreEmId}');
			$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
			$("#drpprimprogram").val('${employee.primaryprogram.primaryProgramId}');
			//$("#project").val('${employee.project}');
			var project =  "${employee.project}";
			project = project.replace(/'/, "\'");
			$("#project").val(project);
			
			//$("#drpVmNumber").val('${employee.vmNumber}');
			$("#drpPCAEmail").val('${employee.PCAEmail}');
			
			$("#mgrEmail").val('${employee.manager.email}');
			//Bhavna add new fields
			$("#drpallocation").val('${employee.allocation}');
			$("#drpworkingbis").val('${employee.workingBis.bis_id}');
			$("#capMgr").val('${employee.capManager}');
			$("#capMgrEmail").val('${employee.capManagerEmail}');
			$("#capSup").val('${employee.capSupervisor}');
			$("#capSupEmail").val('${employee.capSupervisorEmail}');
			
			
			//Bhavna add new fields - end
			
			loadLocation();
			$('#drpSupervisor').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			});
			/* $('#drpbundleEM').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			}); */
			$('#drpEM').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			});
			
			/* 	$('#drpOffShoreEm').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			});  */
			
			
			$('#drpCountry')
			.change(
					 function() {
		
						if ($(this).val() == "2") {
							
							$("#divOffShoreEm").css("display", "");
							$("#divphase").css("display", "");
						
						}else{
							$("#divOffShoreEm").css("display", "none");
							$("#divphase").css("display", "none");
						}
						
		                if ($(this).val() == "1") {

									$("#divresourcemanager").css("display", "");
								} else {
									$("#divresourcemanager").css("display", "none");
								}

		                if ($(this).val() == "1") {

							$("#divresourcemanager").css(
									"display", "");

						} else {

							$("#divresourcemanager").css(
									"display", "none");

						}
		                
							});
			
			$('#drpbundleEM').change(
					 
					function() {
						$.getJSON(
										'${findBIS}',
											{
											id : $(this).val(),
													ajax : 'true'
												},
										
												function(data) {
						
													bisList = data.bisList;
													
													var bisListLength = bisList.length;
													if(bisListLength > 0 ){
														$('#drpbis').val(bisList[0].bis_id).prop('selected', true);
													}
													else{
														$('#drpbis').val('1').prop('selected', true);
													}
													
												});
					});
						
							$('#drpCountry')
									.change(
											function() {
												$
														.getJSON(
																'${findCountryStateURL}',
																{
																	countryId : $(
																			this)
																			.val(),
																	ajax : 'true'
																},
																function(data) {
																	var html = '<option value="">-Select-</option>';
																	var vendorhtml = '<option value="">-Select-</option>';
																	var gradehtml = '<option value="">-Select-</option>';
																	var resmgrhtml = '<option value="">-Select-</option>';
																	$(
																			"#txtSpocName")
																			.val(
																					data.spoc.spocName);
																	$(
																			"#txtSpocEmail")
																			.val(
																					data.spoc.spocEmail);
																	$("#spocId")
																			.val(
																					data.spoc.spocId);
																	stateList = data.stateList;
																	var stateLength = stateList.length;
																	for (var i = 0; i < stateLength; i++) {
																		html += '<option value="' + stateList[i].stateId + '">'
																				+ stateList[i].stateName
																				+ '</option>';
																	}
																	html += '</option>';
																	$(
																			'#drpLocation')
																			.html(
																					html);

																	resourceMgrList = data.resourceMgrList;
																	var resMgrLength = resourceMgrList.length;
																	for (var i = 0; i < resMgrLength; i++) {

																		resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '">'
																				+ resourceMgrList[i].resourceMgrName
																				+ '</option>';
																	}
																	resmgrhtml += '</option>';
																	$(
																			'#drpresourceManager')
																			.html(
																					resmgrhtml);

																	vendorList = data.vendorList;
																	var vendorLength = vendorList.length;
																	for (var i = 0; i < vendorLength; i++) {
																		vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
																				+ vendorList[i].vendorName
																				+ '</option>';
																	}
																	vendorhtml += '</option>';

																	$(
																			'#drpVendor')
																			.html(
																					vendorhtml);

																	gradeList = data.gradeList;
																	var gradeLength = gradeList.length;
																	for (var i = 0; i < gradeLength; i++) {
																		gradehtml += '<option info="' + gradeList[i].globalGradeId + '" value="' + gradeList[i].gradeId + '">'
																				+ gradeList[i].name
																				+ '</option>';
																	}
																	gradehtml += '</option>';

																	$(
																			'#drpGrade')
																			.html(
																					gradehtml);
																});
											});
							$('#drpGrade').change(
									function() {
										var info = $("option:selected", this)
												.attr("info");
										if (info != undefined && info != 0) {
											$("#drpGlobalGrade").val(info);
										} else {
											$("#drpGlobalGrade").val("");
										}
									});
							$("#drpGrade").val('${employee.grade.gradeId}');
							$("#drpGlobalGrade").val(
									'${employee.globalGrade.globalGradeId}');
							$("#drpPrimaryTechnology")
									.val(
											'${employee.primaryTechnology.technologyId}');
							$("#drpSecondaryTechnology").val('${employee.secondaryTechnology.technologyId}');//mehens
							$("#drpSupervisor").val('${employee.manager.id}');
							$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
							$("#drpEM").val('${employee.EM.id}');
							$("#drpbis").val('${employee.bis.bis_id}');
							
							
							
							
							$("#drpoffShoreEm")
									.val('${employee.offshoreEm.offshoreEmId}');
							$("#drpresourceManager")
									.val(
											'${employee.resourceManager.resourceMgrId}');
							$("#drpLocation").val(
									'${employee.location.stateId}');
							$("#drpVendor").val('${employee.vendor.vendorId}');

							$.validate({
								form : '#editEmployee',
								modules : 'date'
							});

							$("#btnSubmit")
									.click(
											function() {
												$("#PSAIdReqVal").val('${employee.PSAIdReq}');
												
												if($("#drppsaLibType").val() != 0){
													
												}
												if($("#PSAIdReqVal").val() != "false"){
													/* alert($("#txtPSAIDExt").val()); */
												
													if ($("#txtPSAIDExt").val() != "" || $("#genDate").val() != "" || $("#drpVmNumber").val() != "" /* || $("#drpPCAEmail").val() != "" */) {
														
														$("#txtPSAIDExt").attr("required", "true").attr("pattern", "^[A-Za-z0-9]+$");
														
														const $txt = $("#txtPSAIDExt");

														// Set a friendly message when invalid
														$txt.on("invalid", function () {
														  this.setCustomValidity("Only alphanumeric characters are allowed and no spaces.");
														});

														// Clear the custom message as the user types
														$txt.on("input", function () {
														  // Live-clean: remove spaces and non-alphanumeric characters
														  //const cleaned = this.value.replace(/[^A-Za-z0-9]/g, "");
														  //if (this.value !== cleaned) this.value = cleaned;

														  // Clear any prior custom validity so browser can re-check pattern
														  this.setCustomValidity("");
														});
														
														//$("#txtPSAIDExt").attr("data-validation", "alphanumeric");
														//$("#txtPSAIDExt").attr("pattern", "^[A-Za-z0-9]+$").attr("title", "Only alphanumeric characters are allowed and no spaces.");
														//$("#txtPSAIDExt").attr("data-validation-help" , "Please enter alphanumeric data");
														$("#genDate").attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
														
														
														$("#vmNumberLabel").html("VM Number <span class='req'>*</span>:");
														
														$("#pcaEmailLabel").html("PCA Email <span class='req'></span>:"); //*
														//$("#drpPCAEmail").attr("required","true");
													
														$("#psaIdLabel").html("PSA ID <span class='req'>*</span>:");
														$("#psaGenLabel").html("PSA ID Generated On<span class='req'>*</span>:");
														
														
													}
													
													else{
														$("#txtPSAIDExt").removeAttr('required');
														$("#txtPSAIDExt").removeAttr('data-validation');
														$("#genDate").removeAttr('data-validation');
													
														$("#drpVmNumber").removeAttr("required", "true");
														$("#drpVmNumber").removeAttr('data-validation');
														$("#drpPCAEmail").removeAttr("required", "true");	
														$("#vmNumberLabel").html("VM Number :");
														$("#pcaEmailLabel").html("PCA Email :");
																
														$("#psaIdLabel").html("PSA ID :");
														$("#psaGenLabel").html("PSA ID Generated On:");
													//	$("#drpPCAEmail").removeAttr('data-validation');
														
													}
												}
												else{
													
													$("#txtPSAIDExt").val('NO PSA ID');
													
												}
												var offshoreemidval = $('#drpoffShoreEm').val();
												var countryVal = $("#drpCountry").val();
												if (offshoreemidval == 0 && countryVal == 2){
										
												    alert("Please select correct value for OffshoreEm ");
												    return false;
												}
												    else{
												    	return true;
												    }
												
												
												if ($("#empType").val() == 'Internal') {
													if ($("#txtEndDate").val() != "") {
														$("#txtEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													}
													if ($("#txtActEndDate")
															.val() != "") {
														$("#txtActEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													}

												}
												if ($("#empType").val() == 'External') {
													$("#txtEndDate")
															.attr(
																	{
																		"data-validation" : "date",
																		"data-validation-format" : "dd/mm/yyyy",
																		"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																	});
													if ($("#txtActEndDate")
															.val() != "") {
														$("#txtActEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													} else {
														$("#txtActEndDate")
																.val(
																		$(
																				"#txtEndDate")
																				.val());
													}

												}
											});

							$("#btnback").click(function() {
								/* alert("history.back()"); */
							});

						});
						
						

		$('#txtCorpID')
				.change(
						function() {
							$
									.getJSON(
											'${checkCorpIdExistsEdit}',
											{
												corpId : $('#txtCorpID').val(),
												empType  : $('#empType').val(),
												txtPSAID : $('#txtPSAID').val(),
												txthiddenCorpID : $('#txthiddenCorpID').val(),
												ajax : 'true'
											},
											function(data) {
												if (data) {
													if ($("#empType").val() == 'Internal' ) {
														alert("Corp Id already exists . Please try another one . ");
														$('#txtCorpID').val("");
													}
													else {
													alert("Corp Id already exists . Please try another one . ");
													$('#txtCorpID').val("");
												}
												}
											});
						});
		
		
		//check PSA ID format - Start
		$('#txtPSAIDExt').change(
									function(){
										$.getJSON('${checkPSAId}',
											{
												psaId : $('#txtPSAIDExt').val(),
												ajax : 'true'
											},
											function(data){
												if(!data){
													alert ("PSA ID should contain only alphabets and numbers and no spaces");
													$('#txtPSAIDExt').val('${employee.psaId}');
													//$('#txtPSAIDExt').val("");
												}
											});	
				});
		//check PSA ID format - End
		
		
		 $('#txtCorpID')
			.change(
					function() {
						$
								.getJSON(
										'${getDetailsByCorpIDFromActiveDirectoryExits}',
										{
											corpId : $('#txtCorpID').val(),
											empType  : $('#empType').val(),
											txtPSAID : $('#txtPSAID').val(),
											txthiddenCorpID : $('#txthiddenCorpID').val(),
											
											ajax : 'true'
										},
										function(data) {
										 if(data) {
											 if(data.email!=null){
												 
													$('#txtFirstName').val(data.firstName);
													$('#txtLastName').val(data.lastName);
													$('#txtEmail').val(data.email);	 
												 }
												 else{
														
														alert("Corp Id does not exists in Active Directory. Please try another one . ");
														$('#txtCorpID').val("");
														 $('#txtFirstName').val("");
														$('#txtLastName').val("");
														$('#txtEmail').val(""); 
													}
												}  
										});
					});
		
	
		// Commented for testing purpose
		
		 /* $('#txtPSAIDExt').change(function() {
			$.getJSON('${checkPsaIdExistsEdit}', {
				psaId : $('#txtPSAIDExt').val(),
				empType : $('#empType').val(),
				
				txtPSAID : $('#txtPSAID').val(),
				ajax : 'true'
			}, function(data) {
				if (data) {
					
					if($("#empType").val() == 'Internal'){
						alert("Psa Id already exists . Please try another one . ");
						$('#txtPSAIDExt').val("");
					}
					else{
						alert("Psa Id already exists . Please try another one . ");
						$('#txtPSAIDExt').val("");
					}
				
				}

			});
		});  */

		$('#txtEmail')
				.change(
						function() {
							$
									.getJSON(
											'${checkEmployeeEmailExistsEdit}',
											{
												email : $('#txtEmail').val(),
												
												empType : $('#empType').val(),
												
												txtPSAID : $('#txtPSAID').val(),
												ajax : 'true'
											},
											function(data) {
												if (data) {
													if($("#empType").val() == 'Internal'){
														alert("Email Id already exists . Please try another one . ");
														$('#txtEmail').val("");
													}
													else{
														alert("Email Id already exists . Please try another one . ");
														$('#txtEmail').val("");
													}
													
												}

											});
						});

		if(${employee.PSAIdReq} == true) {
	 		$("#divdob").css("display", "");
		}
		else{
			$("#divdob").css("display", "none");
		} 
		function loadLocation() {
			var currentLocCode = '${employee.location.stateId}';
			var currentVenCode = '${employee.vendor.vendorId}';
			var currentResMgrCode = '${employee.resourceManager.resourceMgrId}';

			$.getJSON('${findCountryStateURL}',
							{
								countryId : $("#drpCountry").val(),
								ajax : 'true'
							},
							function(data) {
								var html = '<option value="">-Select-</option>';
								var vendorhtml = '<option value="">-Select-</option>';
								var resmgrhtml = '<option value="">-Select-</option>';
								$("#txtSpocName").val(data.spoc.spocName);
								$("#txtSpocEmail").val(data.spoc.spocEmail);
								$("#spocId").val(data.spoc.spocId);

								stateList = data.stateList;
								var stateLength = stateList.length;
								for (var i = 0; i < stateLength; i++) {
									if (stateList[i].stateId == currentLocCode) {
										html += '<option value="' + stateList[i].stateId + '" selected>'
												+ stateList[i].stateName
												+ '</option>';
									} else {
										html += '<option value="' + stateList[i].stateId + '">'
												+ stateList[i].stateName
												+ '</option>';
									}
								}
								html += '</option>';
								$('#drpLocation').html(html);

								resourceMgrList = data.resourceMgrList;
								var resMgrLength = resourceMgrList.length;
								for (var i = 0; i < resMgrLength; i++) {
									if (resourceMgrList[i].resourceMgrId == currentResMgrCode) {
										resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '" selected>'
												+ resourceMgrList[i].resourceMgrName
												+ '</option>';
									} else {
										resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '">'
												+ resourceMgrList[i].resourceMgrName
												+ '</option>';
									}
								}
								resmgrhtml += '</option>';

								$('#drpresourceManager').html(resmgrhtml);

								vendorList = data.vendorList;
								var vendorLength = vendorList.length;
								for (var i = 0; i < vendorLength; i++) {
									if (vendorList[i].vendorId == currentVenCode) {
										vendorhtml += '<option value="' + vendorList[i].vendorId + '" selected>'
												+ vendorList[i].vendorName
												+ '</option>';
									} else {
										vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
												+ vendorList[i].vendorName
												+ '</option>';
									}
								}
								vendorhtml += '</option>';

								$('#drpVendor').html(vendorhtml);
							})
		};
	</script>
</body>
</html>
