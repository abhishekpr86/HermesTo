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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/autocompleteData.css" />
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
				<!-- <li class="active"><a href="javascript:void(0);" title="View/Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li> -->
				<li class="active"><a href="javascript:void(0);" title="View/Edit Pre-Onboarding">View/Edit Onboarding Requests</a></li>
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
		<section>
		<div class="reqText">* indicates required fields</div>
			<h1 style="color:#86A5F7">Resource Information:</h1>
		<div class="formFields clearfix" id="mainDIV" >
			
			<c:url var="editAction" value="/employee/insert"></c:url>
			<c:url var="findCountryStateURL" value="/states" />
			<c:url var="findBIS" value="/bis" />
			<c:url var="checkCorpIdExistsEdit" value="/checkCorpIdExistsEdit" />
			<c:url var="checkPsaIdExistsEdit" value="/checkPsaIdExistsEdit" />
			<c:url var="checkEmployeeEmailExistsEdit" value="/checkEmployeeEmailExistsEdit" />
			<c:url var="getDetailsByCorpIDFromActiveDirectoryExits" value="/getDetailsByCorpIDFromActiveDirectoryExits" />
			<c:url var="managermails" value="/managermails" />
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
			<form:form action="${editAction}" modelAttribute="employee"
				method="POST" id="editEmployee">
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Emp Type : </label>
						</div>
						<div class="inputField">
							
							${employee.empType}

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
							<%-- <form:input path="corpId" id="txtCorpID" readonly="true" class="input-readonly"/> --%>
						</div>
					</div>
					
				</div>
				
				<div class="formRow">
					<div class="col50per">
						<div>
							<form:hidden id="empType" path="empType" />
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<form:hidden id="id" path="id" />	
							<form:hidden id="isVLANmailDone" path="isVLANmailDone"/>	
							<form:hidden id="externalEmpId" path="externalEmpId" />
							<form:hidden id="preonbemp" path="preonbemp" />
							<c:if test="${employee.requestor ne null}">
								<form:hidden id="requestor" path="requestor" />			
							</c:if>
							<div class="fieldLbl" id="empID" style="display: none;">
								<label>Emp ID :
								</label>
							</div>
						
							<div class="inputField">
							<%-- <span class="input-control" >${employee.empId}</span> --%>
								${employee.empId}
							<form:hidden id="txtPSAID" path="empId" />
							
							</div>
						</div>
					</div>
					<div class="col50per" id="divGGId" style="display: block;">
						<div class="fieldLbl">
							<label id="ggID">GG ID :</label>
						</div>
						<div class="inputField">
								${employee.ggId}
							<form:hidden id="txtGGID" path="ggId" />
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
							<label>Last Name :</label>
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
						<div class="fieldLbl" >
							<label>Capgemini Entity :</label>
						</div>
						<div class="inputField">
							${employee.cgEntity}
							<form:hidden id="drpCgEntity" path="cgEntity" />
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
							<label>Country :</label>
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

							<label>Resource Manager <span class="req"></span>: 
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
							<label id="GlobalGrade">Global Grade :</label>
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
							<label>Onboarding buddy <!-- <span class="req"></span> -->:
							</label>
						</div>
                           <div class="autocomplete" style="width:430px; background-color: #ffffff;">
	                        <input path ="buddy" id="employeeId" type="text" name="listOfEmployees">
                         </div>
						</div>	
						<!-- </div>
                           <div class="autocomplete" style="width:300px;">
                        <input id="employeeId" type="text" name="listofEmployees" placeholder="FirstName" >
                         </div> -->
						
				<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy Email <!-- <span class="req"></span> -->:
							</label>
						</div>
						<div class="inputField">
							<form:input path="onboardingEmail" id="mgrEmails" class="input-readonly" />
						</div>
						<%-- <div class="inputField">
							<form:input path="onboardingEmail" id="mgrEmails" class="input-readonly" />
						</div> --%>
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
							<form:hidden id="drpprimprogram" path="primaryprogram" />
							
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Project : </label>
						</div>
						<div class="inputField">
							<form:input path="project" id="project" class="input-control" />
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
							<form:hidden path="bis" id="drpbis"/>	
							${employee.bis.bis_Name}
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
							<label>Staff JIRA # :
							</label>
						</div>
						<div class="inputField">
							<form:input path="jiraNumber" id="jiraNum" size="10" 
								class="input-control" data-validation="number" data-validation-help="Please enter number" />
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
						${employee.onboardingDate}
							<form:hidden path="onboardingDate" id="txtJoiningDate" />
						</div>
					</div>
					<div class="col50per" id="staff">
						<div class="fieldLbl">
							<label>GTD # :
							</label>
						</div>
						<div class="inputField">
							<form:input path="staffingRR" id="staffrr"
								class="input-control" data-validation="required" />
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
							<form:select path="demandType" id="drpdemand" class="select-control"  data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${demandTypeList}" var="demand">
									<form:option value="${demand.id}">${demand.type}</form:option>
									
								</c:forEach>
						</form:select>
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
				
				
				
					 <%-- <div class="col50per" id="divOffShoreEm" style="display: none;">
						<div class="fieldLbl">
							<label>Offshore DM/EM<span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="offshoreEm" id="drpoffShoreEm"  style="width: 168px;"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listOffshoreEm}" var="offshoreem">
									<form:option value="${offshoreem.offshoreEmId}">${offshoreem.offshoreEmName}</form:option>
								</c:forEach>
							</form:select>
						</div>
				</div> --%>
				
				
				<div class="col50per">
					<div class="fieldLbl">
					<label>VCO : </label>
					</div>
					<div class="inputField">
					<form:checkbox path="vco" id="ChkVco"/>
					Yes
					</div>
				</div>
				
				
				<%-- <div class="col50per" id="divOffShoreEm" style="display: none;">
						<div class="fieldLbl">
							<label>Offshore DM/EM<span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="offshoreEm" id="drpoffShoreEm"  style="width: 168px;"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listOffshoreEm}" var="offshoreem">
									<form:option value="${offshoreem.offshoreEmId}">${offshoreem.offshoreEmName}</form:option>
								</c:forEach>
							</form:select>
						</div>
				</div> --%>
				
				
				
				<%-- <div class="col50per" id ="allocationDiv">
						<div class="fieldLbl">
							<label>Allocation % <span class="req"></span>:</label>
						</div>
						<div class="inputField">
							${employee.allocation}
							<form:hidden path="allocation" id="drpallocation" />
						</div>
					</div> --%>
					
					
				<%-- <div class="col50per">
					<div class="fieldLbl">
					<label>VCO : </label>
					</div>
					<div class="inputField">
					<form:checkbox path="vco" id="ChkVco"/>
					Yes
					</div>
				</div> --%>
					
					
				</div>
				
				
				<div class="formRow">
					<div class="col50per" id="divOffShoreEm" style="display: none;">
						<div class="fieldLbl">
							<label>Offshore DM/EM<span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="offshoreEm" id="drpoffShoreEm"  style="width: 168px;"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listOffshoreEm}" var="offshoreem">
									<form:option value="${offshoreem.offshoreEmId}">${offshoreem.offshoreEmName}</form:option>
								</c:forEach>
							</form:select>
						</div>
				</div>
				
				
				<div class="col50per">
						<div class="fieldLbl">
							<label>GitHub Copilot : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="bi" id="Chkbi"/>
							Yes
						</div>
				</div> 
				
				<%-- <div class="col50per">
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
					</div> --%>
				
				</div>
				
				
				<div class="formRow">
						<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Name :</label>
							<form:hidden id="spocId" path="spoc" />
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
				<br>
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
						
						<%-- <c:if test="${employee.PSAIdReq == false}">
         					No
      					</c:if>
						<c:if test="${employee.PSAIdReq == true}">
         					Yes
      					</c:if>
						<form:hidden id="PSAIdReqVal" path="PSAIdReq" />	 --%>	
						<form:checkbox path="PSAIdReq" id="PSAIdReqVal" onclick="calc(); "/>	
						Yes		
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
							<form:hidden path="dobDate" id="txtDate" />
							
						</div>
						
					</div>
					</c:if>
				</div>
				
				<div class="formRow">
				<div class="col50per" id="divVendor" style="display: none;">
						<div class="fieldLbl">
							<label>Vendor <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
						${employee.vendor.vendorName}
						<form:hidden path="vendor" id="drpVendor" />
						
						</div>
					</div>
				</div>
				
				<div class="formRow">
				<div class="col50per" id ="IndusD">
					<div class="fieldLbl">
							<label>INDUS GOAL :</label>
					</div>
					<div class="inputField">
					<form:select path="indusGoals" id="drpIndusGoal"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								
								  <c:forEach items="${listIndusGoals}" var="indusgoals">
								  
									<form:option value="${indusgoals.indusGoalId}">${indusgoals.indusGoalName}</form:option>
								 </c:forEach>  
							</form:select>
							<%-- <form:hidden path="indusGoals" id="drpIndusGoal"/>	
							${employee.indus.indusGoalName}	 --%>
					</div>
				</div>
				<div class="col50per">
						<div class="fieldLbl">
							<label>Request for VM :</label>
						</div>
						<%-- <div class="inputField">
							<form:hidden path="psaLibTypeID" id="drppsaLibType"/>
							${employee.psaLibType.psaLibName}
								
						</div>
				</div> --%>
				
						<div class="inputField">
							<%-- <form:hidden path="psaLibTypeID" id="drppsaLibType"/>
							${employee.psaLibType.psaLibName} --%>
							
							<form:select path="psaLibTypeID" id="drppsaLibType" class="select-control"  data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listPSALib}" var="psaLibType">
									<form:option value="${psaLibType.psaLibId}">${psaLibType.psaLibName}</form:option>
								</c:forEach>
							</form:select>	
						</div>
				</div>
				</div>
				
				<div class="formRow">
					<div class="col50per" id ="RoleT">
					<div class="fieldLbl" >
							<label>Role / Technology :</label>
					</div>
					<div class="inputField">
					<form:select path="roleTech" id="drpRoleTech"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
								 <c:forEach items="${listRoleTech}" var="roleTech">
									<form:option value="${roleTech.roleTechId}">${roleTech.roleTechName}</form:option>
								 </c:forEach> 
							</form:select>
							<%-- <form:hidden path="roleTech" id="drpRoleTech"/>			
							${employee.role.roleTechName} --%>
					</div>
				</div>
				
				</div>
				
				<div class="formRow">
				<div class="col50per" style="display: block;">
						
							
							<div class="fieldLbl">
								<label>PC Hostname <!-- <span class='req'>*</span> -->:</label>
							</div>
							
							<div class="inputField">
							<form:input path="pcSerialNumber" id="pcDetailsID" class="input-control"  style="width:168px; background-color: #ffffff;"/> <!-- data-validation="required" data-validation-help="Please enter value" -->
							</div>
						
					</div>
					<%-- <div class="col50per" id="MACAddr" style="display: block;">
						<div class="fieldLbl">
							<label id="MacAddr">MAC Address <span class='req'>*</span>:</label>
							
						</div>
						<div class="inputField">
		
							<form:input path="macAddress" id="macAddressID" class="input-control"  style="width: 168px;"/>
						</div>
					</div> --%>
				</div>
				
				
				
				<%-- Engg --%>
				<div class="formRow" id ="sdmDiv">
						<div class="col50per" style="display: block; background-color: #ffffff;">
							
							<div class="fieldLbl">
								<label>Stellantis Order Giver<span class="req">*</span>:</label>
							</div>
							
							<div class="inputField" >
							<form:input path="orderG" id="orderGid" class="input-control" data-validation="required" style="width:168px; background-color: #ffffff;"/>
							</div>
						
					</div>
					
					<div class="col50per">
						<div class="fieldLbl">
						<label>SDM (CAE) : </label>
						</div>
						<div class="inputField">
						<form:checkbox path="sdm" id="chkSdm"/>
						Yes
						</div>
					</div>
				</div>
				
				
				
				
				<div class="formRow" id ="businessAp">
				
				<div class="col50per"  style="display: block;">
						<div class="fieldLbl">
							<label>Business Application Services <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="businessAs" id="drpBusinessAs"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
								 <c:forEach items="${basList}" var="bas">
									<form:option value="${bas.basId}">${bas.basName}</form:option>
								 </c:forEach> 
							</form:select>
						</div>
					</div>
				
					<div class="col50per" style="display: block; background-color: #ffffff;">
							
							<div class="fieldLbl">
								<label>Other Application Services :</label>
							</div>
							
							<div class="inputField" >
							<form:input path="otherAs" id="otherAsId" class="input-control" style="width:168px; background-color: #ffffff;"/>
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
							<form:select path="cfao" id="drpCfao"
								class="select-control" >
								<option value="">-Select-</option>
								 <c:forEach items="${cfaoList}" var="cf">
									<form:option value="${cf.cfaoId}">${cf.cfaoName}</form:option>
								 </c:forEach> 
							</form:select>
						</div>
					</div>
					
			</div>
			
			
				<%-- Engg --%>
				
				
				
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
				 	 <input type="submit" value="Save" title="Save" id="btnSubmit" 
						class="btn-primary" />
					
					 <a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Back" class="btn-primary" style="display:inline-block;">Back</a>
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
	if((${employee.location.isVLANReq} == null || ${employee.location.isVLANReq} === false) || ${employee.country.countryId} == 2){
	
		$("#PCDetails").css("display","none");
		//$("#MACAddr").css("display","none");
	}
	
	
	//Engg - start
	var ppId = ${employee.primaryprogram.primaryProgramId};
	var psaIdChk = ${employee.PSAIdReq};
	
	if (ppId == "10" && psaIdChk == true){
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

	$("#orderGid").val('${employee.orderG}');
	$("#drpBusinessAs").val('${employee.businessAs.basId}');
	$("#otherAsId").val('${employee.otherAs}');
	$("#drpCfao").val('${employee.cfao.cfaoId}');
    //Engg - End
	
	$("#timeMat").val('${employee.timeMat}');//mehens
	$("#pcDetailsID").val('${employee.pcSerialNumber}');
	$("#drpdemand").val('${employee.demandType.id}');
	$("#drpallocation").val('${employee.allocation}');
	$("#drpworkingbis").val('${employee.workingBis.bis_id}');
	$("#capMgr").val('${employee.capManager}');
	$("#capMgrEmail").val('${employee.capManagerEmail}');
	$("#capSup").val('${employee.capSupervisor}');
	$("#capSupEmail").val('${employee.capSupervisorEmail}');
	$("#drppsaLibType").val('${employee.psaLibType.psaLibId}');
	$("#drpIndusGoal").val('${employee.indus.indusGoalId}');
	$("#drpRoleTech").val('${employee.role.roleTechId}'); 
	 $("#btnSubmit").submit(function () {   //editEmployee
		 if($("#pcDetailsID").val() != ""){
		 		$("#pcDetailsID").attr({"data-validation":"alphanumeric", "data-validation-help":"Please enter alphanumeric data"});
		 		$("#pcDetailsID").attr({"data-validation":"length"}); //, "data-validation-length"="max20"
		 }
	       /*  $("#btnSubmit").attr("disabled", true);
	        return true; */
	    });
	 $("#editEmployee").submit(function() {
		
		     
		 if($('#editEmployee').isValid()){
			 $("#btnSubmit").attr("disabled", true);
		 }
		        return true;  
		 });
	
	$("#btnSubmit").click(function() {
		$("#txtJoiningDate").val('${employee.onboardingDate}');//
		$("#txtEndDate").val('${employee.offboardingDate}');
		//$("#drpdemand").val('${employee.demandType.id}');
		
		if(${employee.dobDate ne null}){
			 $("#txtDate").val('${employee.dobDate}'); 
			}
		
		if(${employee.dobDate eq null}){
			$("#txtBillingDate").val('${employee.billingStartDate}');
			}
		$("#txtBillingDate").val('${employee.billingStartDate}');
		//$("#jiraNum").val('${employee.jiraNumber}');
		$("#drpbis").val('${employee.bis.bis_id}');
		//$("#staffrr").val('${employee.staffingRR}');
		$("#drpIndusGoal").val('${employee.indus.indusGoalId}');
		$("#drpRoleTech").val('${employee.role.roleTechId}'); 
		$("#drpemprole").val('${employee.roleid.ref_id}');
		$("#preonbemp").val('${employee.preonbemp.id}');
		$("#drppsaLibType").val('${employee.psaLibType.psaLibId}');
		$("#isVLANmailDone").val('${employee.isVLANmailDone}');
		//Engg
		$("#orderGid").val('${employee.orderG}');
		$("#drpBusinessAs").val('${employee.businessAs.basId}');
		$("#otherAsId").val('${employee.otherAs}');
		$("#drpCfao").val('${employee.cfao.cfaoId}');
		//Engg
		
		if(${employee.requestor ne null}){
			$("#requestor").val('${employee.requestor.id}');
		} 
		
	});
	
 	if(${employee.PSAIdReq} == true) {
 		$("#divdob").css("display", "");
	}
	else{
		$("#divdob").css("display", "none");
	} 
	
	
	/* if (${employee.country.countryId} == 1 || ${employee.country.countryId} == 4 || ${employee.country.countryId} == 5 || ${employee.country.countryId} == 6) {
    	
    	$("#PCDetails")
		.html("PC Hostname : <span class='req'>*</span>:");
		$("#pcDetailsID").attr("data-validation","required");
		
		$("#MacAddr")
		.html("MAC Address : <span class='req'>*</span>:");
		$("#macAddressID").attr("data-validation","required");
    	
    }else{
    	
    	$("#PCDetails")
		.html(
				"PC Hostname : <span></span>:");
		$('#pcDetailsID').removeAttr('data-validation');
		
		
		$("#MacAddr")
		.html("MAC Address : <span></span>:");
		$("#macAddressID").removeAttr('data-validation');
    } */
	
	var offShoreValueCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(offShoreValueCheck.val()==2){
		$("#divOffShoreEm").css("display", "");
		/* $("#divphase").css("display", ""); */
		$("#staff").css("display", "");
		
	}
	else if(offShoreValueCheck.val()==1){//mehens
		$("#staff").css("display", "");
		$("#divOffShoreEm").css("display", "none");
	}
		else{
			$("#divOffShoreEm").css("display", "none");
		    /* $("#divphase").css("display", "none"); */
		    $("#staff").css("display", "none");

	}
	
	var resMgrCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(resMgrCheck.val()==1 || resMgrCheck.val()==6){
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
						"Global Grade <span class='req'>*</span>:");
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
			$("#drpType").val('${employee.heritageType}');
			
			$("#empSex").val('${employee.empSex}');
			$("#criticality").val('${employee.criticality}');//mehens-new
			$("#drpCgEntity").val('${employee.cgEntity}');
			$("#drpoffShoreEm").val('${employee.offshoreEm.offshoreEmId}');
			$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
			$("#drpprimprogram").val('${employee.primaryprogram.primaryProgramId}');
			//$("#project").val('${employee.project}');//new
			var project =  "${employee.project}";
			project = project.replace(/'/, "\'");
			$("#project").val(project);
			
			$("#PSAIdReqVal").val('${employee.PSAIdReq}');
			
			$("#drpGlobalGrade").val('${employee.globalGrade.globalGradeId}'); 
			$("#txtGGID").val('${employee.ggId}');
			
			/* $("#timeNMaterial").val('${employee.timeNMaterial}'); */
			
			
			/* alert('${employee.offshoreEm.offshoreEmId}'); */
			
			
			
			/* $("#drpOffShoreEm").val('${employee.offshoreEm.offshoreEmId}'); */
			
			
			$("#mgrEmail").val('${employee.manager.email}');
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
							/* $("#divphase").css("display", ""); */
							
						}else{
							$("#divOffShoreEm").css("display", "none");
							/* $("#divphase").css("display", "none"); */
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
		                
		               /*  if ($(this).val() == "1" || $(this).val() == "4" || $(this).val() == "5" || $(this).val() == "6") {
		                	
		                 	$("#PCDetails")
		    				.html("PC Hostname : <span class='req'>*</span>:");
		    				$("#pcDetailsID").attr("data-validation","required");
		    				 
		    				$("#MacAddr")
		    				.html("MAC Address : <span class='req'>*</span>:");
		    				$("#macAddressID").attr("data-validation","required");
		                	
		                }else{
		                	
		                	 $("#PCDetails")
		    				.html(
		    						"PC Hostname : <span></span>:");
		    				$('#pcDetailsID').removeAttr('data-validation');
		    				 
		    				
		    				$("#MacAddr")
		    				.html("MAC Address : <span></span>:");
		    				$("#macAddressID").removeAttr('data-validation');
		                } */
					 
					 
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
							$("#drpSecondaryTechnology").val('${employee.secondaryTechnology.technologyId}');
							$("#drpSupervisor").val('${employee.manager.id}');
							$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
							$("#drpEM").val('${employee.EM.id}');
							$("#drpbis").val('${employee.bis.bis_id}');
							var buddy=('${employee.buddy}');
							if(buddy != null && buddy!='')
								{
							
							$("#employeeId").val('${employee.buddy.firstName} ${employee.buddy.lastName} (${employee.buddy.corpId})');
								}
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
							$("#btnSubmit")
									.click(
											function() {
												
												if ($("#txtDOB").val() != "") {
													//alert(2);
													$("#txtDOB")
															.attr(
																	{
																		"data-validation" : "date",
																		"data-validation-format" : "dd/mm/yyyy",
																		"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																	});
												}
												else if($("#txtDOB").val() == "" &&  ${employee.PSAIdReq} == false){
													$("#txtDOB").removeAttr('data-validation');
													//alert(4);
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
		 $('#txtPSAIDExt').change(function() {
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
		}); 

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

		function loadLocation() {
			var currentLocCode = '${employee.location.stateId}';
			var currentVenCode = '${employee.vendor.vendorId}';
			var currentResMgrCode = '${employee.resourceManager.resourceMgrId}';

			$
					.getJSON(
							'${findCountryStateURL}',
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
		/* $('#drppsaLibType').change(function() {
		   	 if($('#drppsaLibType').val() == 0){
		   		 $("#HRAReqVal"). prop("checked", false);
		   		 $("#HRAReqVal").attr("disabled", true);
		   	 }else{
		   		 $("#HRAReqVal"). prop("checked", true);
		   		 $("#HRAReqVal").attr("disabled", false);
		   	 }
	   		}); */
			
			
	

		function calc()
		{
		if (document.getElementById('PSAIdReqVal').checked)
		{
		$("#divdob").css("display", "");
		$("#Tech").css("display", "");
		$("#Indus").css("display", "");
		$("#drppsaLibType").val("");
		$("#Role").css("display", "");
		$("#drpRoleTech").val("");
		$("#drpRoleTech").attr("disabled", false);
		$("#drpIndusGoal").val("");
		$("#drpIndusGoal").attr("disabled", false);
		$("#drppsaLibType").attr("disabled", false);
		//$("#HRAReqVal").attr("disabled", false);
		} else {
		$("#divdob").css("display", "none");
		$("#Tech").css("display", "none");
		$("#drpIndusGoal").val("NA");
		$("#Indus").css("display", "none");
		$("#drpIndusGoal").val("NA");
		$("#drpRoleTech").val("NA");
		$("#Role").css("display", "none");
		$("#drppsaLibType").val(0);
		//$("#HRAReqVal"). prop("checked", false);
		// $("#ChkHRAReq").attr('disabled', !$("#ChkHRAReq").attr('disabled'));
		//$("#HRAReqVal").attr("disabled", true);
		$("#drppsaLibType").attr("disabled", true);
		
		//Engg - Start
		  $("#sdmDiv").css("display", "none");
		  $("#businessAp").css("display", "none");
		  $("#cfaoDiv").css("display", "none");
	      //Engg - End
		
		}
		
		 //Engg - start
		  var ppid = $('#drpprimprogram').val();
		 alert(ppid);
		  if (ppid == 10){
			  
			    $("#Role").css("display", "none");
				$("#Tech").css("display", "none");
				$("#Indus").css("display", "none");
				
				if (document.getElementById('PSAIdReqVal').checked) 
				  {alert("checked");
					$("#dobdate").css("display", "");
				    $("#sdmDiv").css("display", "");
					$("#businessAp").css("display", "");
					$("#cfaoDiv").css("display", "");
					$("#drppsaLibType").attr("disabled", false);
					//$("#ChkHRAReq").attr("disabled", false);
		          }
				else{
					
					$("#dobdate").css("display", "none");
					  $("#Tech").css("display", "none");
					  $("#drpIndusGoal").val("NA")
					  $("#Indus").css("display", "none");
					  $("#drpIndusGoal").val("NA")
					  $("#Role").css("display", "none");
					  $("#drppsaLibType").val(0);
					  //$("#ChkHRAReq"). prop("checked", false);
					 // $("#ChkHRAReq").attr('disabled', !$("#ChkHRAReq").attr('disabled'));
					 // $("#ChkHRAReq").attr("disabled", true);
					  $("#drppsaLibType").attr("disabled", true);
					  
					  $("#sdmDiv").css("display", "none");
					  $("#businessAp").css("display", "none");
					  $("#cfaoDiv").css("display", "none");
					  
					    $("#orderGid").val("");
						$("#drpBusinessAs").val("");
						$("#otherAsId").val("");
						$("#drpCfao").val("");
				}
				
		  }
		//Engg - End
		
		}


			
		
		
		function autocomplete(inp, arr) {
			
			  var currentFocus;
			 
			  inp.addEventListener("input", function(e) {
			      var a, b, i, val = this.value;
			    
			      closeAllLists();
			      if (!val) { return false;}
			      currentFocus = -1;
			     
			      a = document.createElement("DIV");
			      a.setAttribute("id", this.id + "autocomplete-list");
			      a.setAttribute("class", "autocomplete-items");
			    
			      this.parentNode.appendChild(a);
			
			      for (i = 0; i < arr.length; i++) {
			      
			        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
			         
			          b = document.createElement("DIV");
			          
			          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
			          b.innerHTML += arr[i].substr(val.length);
			          
			          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
			          
			          b.addEventListener("click", function(e) {
			             debugger;
			            // alert("Madhavi");
			              inp.value = this.getElementsByTagName("input")[0].value;
			              
			            $.getJSON('${managermails}',
									{
							// newRequest : $('#newRequest').val(),
							 corpid :   inp.value ,
							 //country : $('#drpCountry').val(),
								ajax : 'true'
							},
							function(data) {
									//alert("hiiiiiiiiii");
									//alert("meghna"+inp.value);
									 $('#mgrEmails').val(data.email);
									
							}); 
			              
			              
			              closeAllLists();
			          });
			          a.appendChild(b);
			        }
			      }
			  });
			 
			  inp.addEventListener("keydown", function(e) {
				
			      var x = document.getElementById(this.id + "autocomplete-list");
			      if (x) x = x.getElementsByTagName("div");
			      if (e.keyCode == 40) {
			       
			        currentFocus++;
			       
			        addActive(x);
			      } else if (e.keyCode == 38) { //up
			        
			        currentFocus--;
			       
			        addActive(x);
			      } else if (e.keyCode == 13) {
			       
			        e.preventDefault();
			        if (currentFocus > -1) {
			          
			          if (x) x[currentFocus].click();
			        }
			      }
			  });
			  function addActive(x) {
			    
			    if (!x) return false;
			    
			    removeActive(x);
			    if (currentFocus >= x.length) currentFocus = 0;
			    if (currentFocus < 0) currentFocus = (x.length - 1);
			   
			    x[currentFocus].classList.add("autocomplete-active");
			  }
			  function removeActive(x) {
			    
			    for (var i = 0; i < x.length; i++) {
			      x[i].classList.remove("autocomplete-active");
			    }
			  }
			  function closeAllLists(elmnt) {
			    
			    var x = document.getElementsByClassName("autocomplete-items");
			    for (var i = 0; i < x.length; i++) {
			      if (elmnt != x[i] && elmnt != inp) {
			        x[i].parentNode.removeChild(x[i]);
			      }
			    }
			  }
			  
			  document.addEventListener("click", function (e) {
			      closeAllLists(e.target);
			  });
			}
		
		
		
		var  employeeList  ="${listOfEmployees}";
		var employees;
		const employeee ="${listOfEmployees}";
		const string=employeee.split(",");
		console.log("hiiiiiii" +string.length);
		autocomplete(document.getElementById("employeeId"), string);
	</script>
</body>
</html>
