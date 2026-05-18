<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	href="${pageContext.request.contextPath}/resources/css/autocompleteData.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />
</head>
<body>
	<header class="main-header">
		<div class="pull-left">
			<h3 class="pull-left">Onboarding Tool</h3>
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
			
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM_PMO')}">
				<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Pre-Onboarding</a></li> --%>
				<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Onboarding Requests</a></li>
			</c:if>
				
			<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO')  or (checkUserType eq 'ASL')}">
				<li class="active"><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View / Edit Resources</a></li>
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
		<br/>
		<h2>Edit Resource</h2>
		<section>
		<div class="reqText">* indicates required fields</div>
			<h1 style="color:#86A5F7">Resource Information:</h1>
		<div class="formFields clearfix" id="mainDIV" >
			
			<c:url var="editAction" value="/employee/edit"></c:url>
			<c:url var="findCountryStateURL" value="/states" />
			<c:url var="findBIS" value="/bis" />
			<c:url var="checkCorpIdExistsEdit" value="/checkCorpIdExistsEdit" />
			<c:url var="checkPsaIdExistsEdit" value="/checkPsaIdExistsEdit" />
			<c:url var="checkEmployeeEmailExistsEdit" value="/checkEmployeeEmailExistsEdit" />
			<c:url var="checkPlannedEndDate" value="/checkPlannedEndDateChange" />
			<c:url var="checkJoiningDate" value="/checkJoiningDate" />
			<c:url var="checkBillingDate" value="/checkBillingDate" />
			<c:url var="findBundleEMfromBIS" value="/findBundleEMfromBIS"/>
			<c:url var="getDetailsByCorpIDFromActiveDirectoryExits" value="/getDetailsByCorpIDFromActiveDirectoryExits" />
			<c:url var="getBisList" value="/getBisList" /> 
			<%-- <c:url var="listOfEmployeedata" value="/listOfEmployeedata"/> --%>
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
						<div>
							<form:hidden id="empType" path="empType" />
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<form:hidden id="id" path="id" />
							<c:if test="${employee.archiveType ne null}">	
								<form:hidden id="archiveType" path="archiveType" />		
							</c:if>
							<c:if test="${employee.welcomeEmailFlag ne null}">	
								<form:hidden id="welcomeEmailFlag" path="welcomeEmailFlag" />		
							</c:if>
							<form:hidden id="externalEmpId" path="externalEmpId" />	
							<form:hidden id="rollOffType" path="rollOffTypeVal" />	
							<c:if test="${employee.preonbemp ne null}">
								<form:hidden id="preonbemp" path="preonbemp" />
							</c:if>
							<form:hidden id="replacementType" path="replacementTypeVal" />	
							<c:if test="${employee.requestor ne null}">
								<form:hidden id="requestor" path="requestor" />
							</c:if>
												
							<div class="fieldLbl" id="empID" style="display: none;">
								<label>Emp ID <span class="req" >*</span>:
								</label>
							</div>
							<div class="fieldLbl" id="psaID" style="display: none;">
								<label>PSA ID <span class="req">*</span>:
								</label>
							</div>
							<div class="inputField">
								<form:input path="empId" size="8" id="txtPSAID" 
									class="input-control" />
								<%-- <form:hidden path="empId" /> --%>
							</div>
						</div>
					</div>
					<div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">CORP ID <span class="req">*</span>:
							</label>
							
						</div>
						<div class="inputField">
							<form:input path="corpId" id="txtCorpID" class="input-control" data-validation="required"/>
							<form:hidden  path="hiddenCorpIdValue" id="txthiddenCorpID" />
						</div>
					</div>
				</div>
				<div class="formRow" >
					<div class="col50per" id="internalToPSA" style="display:none;">
						<div class="fieldLbl">
							<label>GG ID :</label>
						</div>
						<div class="inputField">
							<form:input path="ggId" id="ggId" class="input-control" />
						</div>
					
					</div>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Entity :
							</label>
						</div>
						<div class="inputField">
							<form:input path="cgEntity" id="drpCgEntity" class="input-control" data-validation="required" />
						</div>
					</div>
				</div>
				
				<div class="formRow" >
					<div class="col50per">
					<div class="fieldLbl">
							<label>Role :
							</label>
						</div>
						<div class="inputField">
							<form:select path="emprole" id="drpemprole"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${listEmployeeRoles}" var="emproles">
								<form:option value="${emproles.ref_id}">${emproles.ref_name}</form:option>

								</c:forEach>
							</form:select>
						</div>
				</div>
				</div>
				
				<div class="formRow" >
				<div class="col50per">
						<div class="fieldLbl">
							<label>First Name <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="firstName" id="txtFirstName"
								class="input-control" data-validation="required" />
						</div>
					</div>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Primary Skill <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="primaryTechnology" id="drpPrimaryTechnology"
								class="select-control" data-validation="required">
								<c:forEach items="${listTechnology}" var="technology">
									<form:option value="${technology.technologyId}">${technology.technologyName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow" >
				<div class="col50per">
						<div class="fieldLbl">
							<label>Last Name <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="lastName" id="txtLastName"
								class="input-control" data-validation="required" />
						</div>
					</div>
						<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Sex <span class="req">*</span>:
							</label>
						</div>
						<form:radiobutton path="empSex" id="rdEmpSex"
							value="M" name="rdoGroup" class="input-control"
							checked="checked" />
						<label>Male</label>
						<form:radiobutton path="empSex" value="F"
							id="rdEmpSex" name="rdoGroup" class="input-control" />
						<label>Female</label>
					</div> --%>
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Secondary Skill:
							</label>
						</div>
						<div class="inputField">
							<form:select path="secondaryTechnology" id="drpSecondaryTechnology"
								class="select-control" >
								<c:forEach items="${listTechnology}" var="technology">
									<form:option value="${technology.technologyId}">${technology.technologyName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					
				</div>
				
				<div class="formRow" >
					<div class="col50per">
						<div class="fieldLbl">
							<label>Country <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="country" id="drpCountry"
								class="select-control">
								<c:forEach items="${listCountry}" var="cntry">
									<form:option value="${cntry.countryId}">${cntry.countryName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Location <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="location" id="drpLocation"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow" >
				<div class="col50per">
						<div class="fieldLbl">
							<label>Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="email" id="txtEmail" class="input-control"
								data-validation="email" />
						</div>
					</div>
					
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Sex <span class="req">*</span>:
							</label>
						</div>
						<form:radiobutton path="empSex" id="rdEmpSex"
							value="M" name="rdoGroup" class="input-control"
							checked="checked" />
						<label>Male</label>
						<form:radiobutton path="empSex" value="F"
							id="rdEmpSex" name="rdoGroup" class="input-control" />
						<label>Female</label>
					</div>
					
					
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Secondary Skill:
							</label>
						</div>
						<div class="inputField">
							<form:select path="secondaryTechnology" id="drpSecondaryTechnology"
								class="select-control" >
								<c:forEach items="${listTechnology}" var="technology">
									<form:option value="${technology.technologyId}">${technology.technologyName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div> --%>
					
					
					<div class="col50per" id="divresourcemanager" style="display: none;">
						<div class="fieldLbl">

							<label>Resource Manager <span class="req"></span>: 
							</label>
						</div>
						<div class="inputField">
							<form:select path="resourceManager" id="drpresourceManager" 
								class="select-control">
								<option value="">-Select-</option>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow" >
					<div class="col50per" id="divGlobalGrade" style="display: block;">
						<div class="fieldLbl">
							<label id="GlobalGrade">Global Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="globalGrade" id="drpGlobalGrade"
								class="select-control" data-validation="required" >
								<c:forEach items="${listGlobalGrades}" var="globalGrades">
									<form:option value="${globalGrades.globalGradeId}">${globalGrades.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="col50per" id="divLocalGrade" style="display: none;">
						<div class="fieldLbl">
							<label>Local Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="grade" id="drpGrade" class="select-control"
								data-validation="required">
								<c:forEach items="${listGrades}" var="grd">
									<form:option info="${grd.globalGradeId}" value="${grd.gradeId}">${grd.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
			
				</div>
				
				<%-- <div class="formRow" >
					<div class="col50per">
						<div class="fieldLbl">
							<label>Hermes Manager <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">

							<form:select path="manager" id="drpSupervisor"
								class="select-control" data-validation="required">
								<c:forEach items="${listEmployees}" var="mgr">
								
								<c:if test="${!empty mgr.email}">
									<c:if test="${!empty mgr.corpId}">
										<form:option   infomanager="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}  ( ${mgr.corpId} )</form:option>
									</c:if>
									<c:if test="${empty mgr.corpId}">
										<form:option infomanager="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}</form:option>
									</c:if>
									</c:if>
									
								</c:forEach>
							</form:select>
						</div>
					</div>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Hermes Manager Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="managerEmail" id="mgrEmail"  readonly="true"
								class="input-readonly" data-validation="required"/>
						</div>
					</div>
				</div> --%>
				 <div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy <!-- <span class="req"></span> -->:
							</label>
						</div>
                           <div class="autocomplete" style="width:430px;">
                        <input id="employeeId" type="text" name="listOfEmployees" >
                         </div>
						</div>	
						
				<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy Email <!-- <span class="req"></span> -->:
							</label>
						</div>
						<div class="inputField">
							<form:input path="onboardingEmail" id="mgrEmails" class="input-readonly" />
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
							<form:input path="capSupervisor" id="capSup" class="input-control" />
						</div>
					</div>
					<div class="col50per"  id="divCapSupEmail" style="display: none;">
						<div class="fieldLbl">
							<label>Capgemini Supervisor Email <span class="req"></span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="capSupervisorEmail" id="capSupEmail" class="input-control" />
						</div>
					</div>
				</div>
				
				<!-- </section> -->
				<!-- </div> -->
				
				<br>
				<h1 style="color:#86A5F7">Project Information:</h1>
				<div class="formFields clearfix">
					<section>
					<div class="formRow" >
						<div class="col50per">
							<div class="fieldLbl">
								<label>Primary Program <span class="req">*</span>: </label>
							</div>
							<div class="inputField">
							<form:select path="primaryprogram" id="drpprimprogram"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${listPrimaryProgram}" var="primprogram">
									<form:option value="${primprogram.primaryProgramId}">${primprogram.primaryProgramName}</form:option>
								</c:forEach>
							</form:select>
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
					<div class="formRow" >
							<div class="col50per">
						<div class="fieldLbl">
							<label>Bundle-Domain :
							</label>
						</div>
						<div class="inputField">
							<form:select path="bis" id="drpbis"
								class="select-control">
								<c:forEach items="${fullListOfBis}" var="bis">
									<form:option value="${bis.bis_id}">${bis.bis_Name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					<div class="col50per">
					<div class="fieldLbl">
							<label>Bundle EM <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="bundleEM" id="drpbundleEM"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listBundleEm}" var="bundleEm">
									<form:option value="${bundleEm.bundleEmId}">${bundleEm.bundleEmName}</form:option>
								</c:forEach>
							</form:select>
						</div>
						</div>
					</div>
					
					<div class="formRow">
						<div class="col50per" id="divworkingbis" style="display: none;">
							<div class="fieldLbl">
								<label>Working Domain <span class="req">*</span>:
								</label>
							</div>
							<div class="inputField">
								<form:select path="workingBis" id="drpworkingbis" class="select-control"  data-validation="required">
									<option value="">-Select-</option>
									<c:forEach items="${fullListOfBis}" var="bis">
										<form:option value="${bis.bis_id}">${bis.bis_Name}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>
				</div>
					
					<!-- Engg - Start -->
					<div class="formRow" >
					<div class="col50per">
						<div class="fieldLbl">
							<label>Manager <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">

							<form:select path="manager" id="drpSupervisor"
								class="select-control" data-validation="required">
								<c:forEach items="${listEmployees}" var="mgr">
								
								<c:if test="${!empty mgr.email}">
									<c:if test="${!empty mgr.corpId}">
										<form:option   infomanager="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}  ( ${mgr.corpId} )</form:option>
									</c:if>
									<c:if test="${empty mgr.corpId}">
										<form:option infomanager="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}</form:option>
									</c:if>
									</c:if>
									
								</c:forEach>
							</form:select>
						</div>
					</div>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Manager Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="managerEmail" id="mgrEmail"  readonly="true"
								class="input-readonly" data-validation="required"/>
						</div>
					</div>
				</div>
					<!-- Engg - End -->
					
					
					<div class="formRow">
						 <div class="col50per">
						<div class="fieldLbl">
							<label>EM <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="EM" id="drpEM"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listEmployeesByEm}" var="mgr">	
								<c:if test="${!empty mgr.email}">								
									<c:if test="${!empty mgr.id}">
										<form:option value="${mgr.id}">${mgr.firstName} ${mgr.lastName}  ( ${mgr.corpId} )</form:option>
									</c:if>
									<c:if test="${empty mgr.id}">
										<form:option  value="${mgr.id}">${mgr.firstName} ${mgr.lastName}</form:option>
									</c:if>
									</c:if>
								</c:forEach>
							</form:select>
					</div>
				</div>
				<div class="col50per">
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
							<label>On-boarding Date (dd/mm/yyyy) <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="joiningDate" id="txtJoiningDate"   
								class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" />
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
							<label>Planned Off-boarding Date (dd/mm/yyyy) <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="endDate" id="txtEndDate"
								class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" />
						</div>
					</div>
				<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Upper V Cycle : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="upperVCycle" id="ChkUpperVCycle"/>
							Yes
						</div>
					</div> --%>
				</div>
					
					<div class="formRow">
				
						<div class="col50per">
							<div class="fieldLbl">
								<label>Billing Start Date (dd/mm/yyyy) <span class="req">*</span>:
								</label>
							</div>
							<div class="inputField">
								<form:input path="billingDate" id="txtBillingDate"
									class="input-control" data-validation="date"
									data-validation-format="dd/mm/yyyy"
									data-validation-help="Please enter date in dd/mm/yyyy format" />
							</div>
						</div>
						
						<%-- New Time And Material--%>
						<div class="col50per">
						<div class="fieldLbl">
							<label>Time & Material : </label>
						</div>
						
						<div class="inputField">
							<form:select path="timeMat" id="empTimeMat" class="select-control" data-validation="required">
								<option value="">-Select-</option>
									<form:option value="Fixed Price">Fixed Price</form:option>
									<form:option value="Co-piloted Agile">Co-piloted Agile</form:option>
									<form:option value="Time & Material">Time & Material</form:option>
							</form:select>
						</div>
					</div>
						
						
						<%-- <div class="col50per">
							<div class="fieldLbl">
								<label>Time & Material : </label>
							</div>
							<div class="inputField">
								<form:checkbox path="timeNMaterial" id="ChkTimeNMaterial"/>
								Yes
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
							<form:checkbox path="coreTeam" id="ChkCoreTeam"/>
							Yes
						</div> 
					</div> --%>
					
					
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Employee Criticality <span class="req">*</span>:
							</label>
						</div>
						<form:select path="criticality" id="empCriticality" class="select-control" data-validation="required">
						<c:if test="${employee.criticality == ''}">
									<option value="" selected="true">-Select-</option>
									<option value="L">Low</option>
									<option value="M">Medium</option>
									<option value="H">High</option>

						</c:if>			
			
						<c:if test="${employee.criticality == 'H'}">
						    <option value="L">Low</option>
						    <option value="M">Medium</option>
         					<option value="H" selected="true">High</option>
      					</c:if>
						
						<c:if test="${employee.criticality == 'M'}">
							<option value="L">Low</option>
							<option value="M"  selected="true">Medium</option>
							<option value="H">High</option>
      					</c:if>
						
						<c:if test="${employee.criticality == 'L'}">
						    <option value="L" selected="true">Low</option>
						    <option value="M">Medium</option>
         					<option value="H">High</option>
						</c:if>
						</form:select>
					</div>
					
					
					
					
					</div>
						
					<div class="formRow" >
					<div class="col50per" id ="allocationDiv">
						<div class="fieldLbl">
							<label>Allocation % <span class="req">*</span>:</label>
						</div>
						<div class="inputField">
							<form:input path="allocation" id="allocation" size="3" class="input-control" data-validation="number" data-validation-help="Please enter number"/>
						</div>
					</div>
				
				
				
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
							<form:select path="offshoreEm" id="drpoffShoreEm"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listOffshoreEm}" var="offshoreem">
									<form:option value="${offshoreem.offshoreEmId}">${offshoreem.offshoreEmName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div> --%>
				</div>
				
				<div class="formRow" >
				 <div class="col50per" id="divOffShoreEm" style="display: none;">
						<div class="fieldLbl">
							<label>Offshore DM/EM<span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="offshoreEm" id="drpoffShoreEm"
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
					
				</div>
				
					
						<div class="formRow" >
							<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Name :</label>
							<form:hidden id="spocId" path="spoc" />
						</div>
						<div class="inputField">
							<form:input path="spocName" id="txtSpocName" disabled="true"
								class="input-readonly" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Email :
							</label>
						</div>
						<div class="inputField">
							<form:input path="spocEmail" id="txtSpocEmail" disabled="true"
								class="input-readonly" />
						</div>
					</div>
					</div>
				</section>
				</div>
				
				<br>
				<h1 style="color:#86A5F7">Technical Information:</h1>
				<div class="formFields clearfix">
				<section>
					<div class="formRow" >
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
				</div>
				
				<div class="formRow" >
				<div class="col50per" id="internalToPSA" style="display:none;">
							<div class="fieldLbl">
								<label>PSA ID :</label>
							</div>
							<div class="inputField">
								<form:input path="psaId" size="8" id="txtPSAIDExt" class="input-control" />
							</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label id="dobLabel">Date of Birth (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							<form:input path="dob" id="txtDOB" class="input-control" />
						</div>
					</div>
				</div>
				
				<div class="formRow" >
				<div class="col50per">
						<div class="col50per" id="staff">
						<div class="fieldLbl">
							<label>VM Number :
							</label>
						</div>
						<div class="inputField">
						
						${employee.vmNumber}
						<form:hidden path="vmNumber" id="vm" />
							
						</div>
					</div>
					</div>
						<div class="col50per" id="divVendor" style="display: none;">
						<div class="fieldLbl">
							<label>Vendor <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="vendor" id="drpVendor" class="select-control"
								data-validation="required">
								<option value="">-Select-</option>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow" >
				
				<c:if test="${checkUserType ne 'RM_PMO'}">
				<div class="col50per" id ="Indus">
						<div class="fieldLbl">
								<label>INDUS GOAL :</label>
						</div>
						<div class="inputField">
								<form:hidden path="indusGoals" id="drpIndusGoal"/>	
								${employee.indus.indusGoalName}	
						</div>
					</div>
				</c:if>	
				<c:if test="${checkUserType eq 'RM_PMO'}">
				<div class="col50per" id ="Indus">
						<div class="fieldLbl">
								<label>INDUS GOAL :</label>
						</div>
						<div class="inputField">
								<%-- <form:input path="indusGoals" id="drpIndusGoal"/> --%>	
								<%-- ${employee.indus.indusGoalName}	 --%>
								<form:select path="indusGoals" id="drpIndusGoal"
								class="select-control" data-validation="required">
								<!-- <option value="" selected="true">-Select-</option> -->
								
								  <c:forEach items="${listIndusGoals}" var="indusgoals">
								  
									<form:option value="${indusgoals.indusGoalId}">${indusgoals.indusGoalName}</form:option>
								 </c:forEach>  
							</form:select>
						</div>
					</div>
				</c:if>
					
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
				
				<div class="formRow" >
				<c:if test="${checkUserType ne 'RM_PMO'}">
					<div class="col50per" id ="RoleT">
						<div class="fieldLbl">
								<label>Role / Technology :</label>
						</div>
						<div class="inputField">
								<form:hidden path="roleTech" id="drpRoleTech"/>			
								${employee.role.roleTechName}
						</div>
					</div>
				</c:if>
				<c:if test="${checkUserType eq 'RM_PMO'}">
					<div class="col50per" id ="RoleT">
						<div class="fieldLbl">
								<label>Role / Technology :</label>
						</div>
						<div class="inputField">
								<%-- <form:hidden path="roleTech" id="drpRoleTech"/> --%>			
								<form:select path="roleTech" id="drpRoleTech"
								class="select-control" data-validation="required">
								<!-- <option value="">-Select-</option> -->
								 <c:forEach items="${listRoleTech}" var="roleTech">
									<form:option value="${roleTech.roleTechId}">${roleTech.roleTechName}</form:option>
								 </c:forEach> 
							</form:select>
						</div>
					</div>
				</c:if>
					
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
				
				<div class="formRow" >
				<div class="col50per" id="PCDetails" style="display: block;">
							<div class="fieldLbl">
								<label>PC Hostname <!-- <span class='req'>*</span> -->:</label>
							</div>
							
							<div class="inputField">
							<form:input path="pcSerialNumber" id="pcDetailsID" class="input-control" style="width:168px; background-color: #ffffff;"/> <!-- data-validation="required" data-validation-help="Please enter alphanumeric data" -->
							</div>
					</div>
					<%-- <div class="col50per" id="MACAddr" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">MAC Address :</label>
						</div>
						<div class="inputField">
							<form:input path="macAddress" id="macAddressID" class="input-control"  style="width: 168px;"/>
						</div>
					</div> --%>
				</div>
				
				<!-- Engg - Start -->
				<div class="formRow" id ="sdmDiv" >
						
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
				
				<!-- Engg - End -->
				
				</section>
				</div>
				
				<br>
				<div class="formRow">
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
					
					<c:if test="${CheckReportType eq null}">
					 <a href="${pageContext.request.contextPath}/allResourceList" title="Back" class="btn-primary" style="display:inline-block;">Back</a>
				 	</c:if>
				 	<c:if test="${CheckReportType ne null}">
				 	 <a href="${pageContext.request.contextPath}/dataInconsistencies" title="Back" class="btn-primary" style="display:inline-block;">Back</a> 
					</c:if> 
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
	
	//Engg - start
	var ppId = ${employee.primaryprogram.primaryProgramId};
	var psaIdChk = ${employee.PSAIdReq};
	
	if (ppId == "10" && psaIdChk == true){
		$("#sdmDiv").css("display", "");
		$("#businessAp").css("display", "");
		$("#cfaoDiv").css("display", "");
		
		$("#RoleT").css("display", "none");
		//$("#Tech").css("display", "none");
		$("#Indus").css("display", "none");
		
		//$("#drpIndusGoal").val("NA")
	}else{
		$("#sdmDiv").css("display", "none");
		$("#businessAp").css("display", "none");
		$("#cfaoDiv").css("display", "none");
	}
    //Engg - End
	
	/* if(${employee.location.isVLANReq} == null || ${employee.location.isVLANReq} == false){
		
		//$("#PCDetails").css("display","none");
		$("#MACAddr").css("display","none");
	} */
	 $("#btnSubmit").submit(function () {   //editEmployee
		 if($("#pcDetailsID").val() != ""){
		 		$("#pcDetailsID").attr({"data-validation":"alphanumeric", "data-validation-help":"Please enter alphanumeric data"});
		 		
		 }
	 
	       
	        return true;
	    });
	 
	 $("#editEmployee").submit(function() {
		 if($('#editEmployee').isValid()){

			 $("#btnSubmit").attr("disabled", true);
			 
		 	}
	       
	        return true;  
	 });
	
	
	 /*if(${employee.PSAIdReq} == true) {
			//alert(1);
			$("#dobLabel").html("Date of Birth (dd/mm/yyyy) <span class='req'>*</span>:");
			$("#txtDOB").attr("data-validation","date");
			$("#txtDOB").attr("data-validation-help","Please enter date in dd/mm/yyyy format");
		}
	   else{
			$("#dobLabel").html("Date of Birth (dd/mm/yyyy) <span></span>:");
			$('#txtDOB').removeAttr('data-validation');
			//alert(3);
		}  */
	
	var offShoreValueCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(offShoreValueCheck.val()==2){
		$("#divOffShoreEm").css("display", "");
		
		$("#divLocalGrade").css("display", "");
				
	}
		else{
			$("#divOffShoreEm").css("display", "none");
			$("#divLocalGrade").css("display", "none");
		    
		    
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
			$("#drpCgEntity").val('${employee.cgEntity}');
			$("#drpoffShoreEm").val('${employee.offshoreEm.offshoreEmId}');
			$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
			$("#drpprimprogram").val('${employee.primaryprogram.primaryProgramId}');
			//$("#project").val('${employee.project}');
			var project =  "${employee.project}";
			project = project.replace(/'/, "\'");
			$("#project").val(project);
			
			$("#criticality").val('${employee.criticality}');//mehens-new
			$("#pcDetailsID").val('${employee.pcSerialNumber}');
			$("#drpIndusGoal").val('${employee.indus.indusGoalId}');
			$("#drpRoleTech").val('${employee.role.roleTechId}'); 
			$("#drpemprole").val('${employee.roleid.ref_id}');

			$("#drppsaLibType").val('${employee.psaLibType.psaLibId}');
			//Bhavna add new fields
			$("#drpallocation").val('${employee.allocation}');
			$("#drpworkingbis").val('${employee.workingBis.bis_id}');
			
			//Engg
			$("#orderGid").val('${employee.orderG}');
			$("#drpBusinessAs").val('${employee.businessAs.basId}');
			$("#otherAsId").val('${employee.otherAs}');
			$("#drpCfao").val('${employee.cfao.cfaoId}');
			$("#sdm").val('${employee.sdm}');
			//Engg
			
			//$("#capMgr").val('${employee.capManager}');//Changes for single quote in Cap manager name - mehens
			var capM =  "${employee.capManager}";
			capM = capM.replace(/'/, "\'");
			$("#capMgr").val(capM);
			
			$("#capMgrEmail").val('${employee.capManagerEmail}');
			$("#capSup").val('${employee.capSupervisor}');
			$("#capSupEmail").val('${employee.capSupervisorEmail}');
			//Bhavna add new fields - end
			if ($('#drpCountry').val() == "2") {
				 $("#divCapSup").css("display", "");
				 $("#divCapSupEmail").css("display", "");
			 }
			 else{
				 $("#divCapSup").css("display", "none");
				 $("#divCapSupEmail").css("display", "none");
			}
			
			
			
			$("#jiraNum").val('${employee.jiraNumber}');
			$("#staffrr").val('${employee.staffingRR}');
			$("#vm").val('${employee.vmNumber}');
			$("#PSAIdReqVal").val('${employee.PSAIdReq}');
			
			$("#ggId").val('${employee.ggId}'); 
			
			$("#bi").val('${employee.bi}'); //mehens
			/* $("#timeNMaterial").val('${employee.timeNMaterial}'); */
			$("#timeMat").val('${employee.timeMat}'); 
			
			$("#drpdemand").val('${employee.demandType.id}');//new
			if(${employee.archiveType ne null}){
				$("#archiveType").val('${employee.archiveType}');
			}
			if(${employee.welcomeEmailFlag ne null}){
				$("#welcomeEmailFlag").val('${employee.welcomeEmailFlag}');
			}
			if(${employee.preonbemp ne null}){
				$("#preonbemp").val('${employee.preonbemp.id}');
			}
			
			if(${employee.requestor ne null}){
				$("#requestor").val('${employee.requestor.id}');
			}
			
			
			
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
			
			$('#allocation').change(
					function() {
						if($('#allocation').val() > 100 || $('#allocation').val() < 0){
							$('#allocation').val("");
							alert ("Allocation % should be between 0-100")
						}
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
			
			$('#txtBillingDate').change(
					function(){
						
						$.getJSON('${checkBillingDate}',
							{
								billingDateString : $('#txtBillingDate').val(),
								plannedEndDate : $('#txtEndDate').val(),
								joiningDate : $('#txtJoiningDate').val(),
								ajax : 'true'
							},
							function(data){
								
								if(!data){
									alert("Billing Date should be in between Onboarding Date and Planned Offboarding Date");
									$('#txtBillingDate').val('');
								} 
							});
					});

			$('#txtEndDate').change(
					function(){
						$.getJSON('${checkPlannedEndDate}',
							{
								billingDateString : $('#txtBillingDate').val(),
								plannedEndDate : $('#txtEndDate').val(),
								joiningDate : $('#txtJoiningDate').val(),
								ajax : 'true'
							},
							function(data){
								if(!data){
									alert ("Planned Offboarding Date should be greater than Onboarding Date and Billing Date");
									$('#txtEndDate').val('');
								}
							});
						
				});
			
			
			  $('#txtJoiningDate').change(function(){
				$.getJSON('${checkJoiningDate}',
						{
							billingDateString : $('#txtBillingDate').val(),
							plannedEndDate : $('#txtEndDate').val(),
							joiningDate : $('#txtJoiningDate').val(),
							ajax : 'true'
						},
						function(data){
							if(!data){
								alert ("Planned Offboarding Date should be greater than Onboarding Date and Billing Date");
								$('#txtEndDate').val('');
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
							$("#divLocalGrade").css("display", "");
							$("#divCapSup").css("display", "");
							 $("#divCapSupEmail").css("display", "");
						}else{
							$("#divOffShoreEm").css("display", "none");
							$("#divLocalGrade").css("display", "none");
							$("#divCapSup").css("display", "none");
							 $("#divCapSupEmail").css("display", "none");
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
			
			$('#drpbis').change(
					 
					function() {
						$.getJSON(
								'${findBundleEMfromBIS}',
								{
								id : $(this).val(),
										ajax : 'true'
									},
							
									function(data) {
										
										$("#divBundleEM").css("display","");
										 	
										var bundleEMhtml = '<option value="' + data.bundleEM.bundleEmId + '">'
											+ data.bundleEM.bundleEmName
											+ '</option>';
											bundleEMhtml += '</option>';
											$('#drpbundleEM').html(bundleEMhtml);
											
											if($('#drpbis').val() == 5){
												$("#divworkingbis").css("display","");
												
												 var html = '<option value="">-Select-</option>';
													var bisListLength = data.bisList;
												 for(var bisEle=0; bisEle < bisListLength.length; bisEle++){
														html += '<option value="' + data.bisList[bisEle].bis_id + '">'
														+ data.bisList[bisEle].bis_Name
														+ '</option>';
														$('#drpworkingbis').html(html);
													}  

												
											}else{
												$("#divworkingbis").css("display", "none");
												$("#drpworkingbis").val('');
												
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
							$("#drpPrimaryTechnology").val('${employee.primaryTechnology.technologyId}');
							var secondaryTechnology = ('${employee.secondaryTechnology.technologyId}');
							if(secondaryTechnology != null && secondaryTechnology !=''){
								$("#drpSecondaryTechnology").val('${employee.secondaryTechnology.technologyId}');//mehens	
							}
							else{
								$("#drpSecondaryTechnology").val('');//mehens	
								}
							
							$("#drpSupervisor").val('${employee.manager.id}');
							$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
							$("#drpEM").val('${employee.EM.id}');
							$("#drpbis").val('${employee.bis.bis_id}');
						    var buddy=('${employee.buddy}');
							if(buddy != null && buddy!='')
								{
							$("#employeeId").val('${employee.buddy.firstName} ${employee.buddy.lastName} (${employee.buddy.corpId})');
								}
							if($('#drpbis').val() == 5){
								$("#divworkingbis").css("display","");
							}else{
								$("#divworkingbis").css("display", "none");
								$("#drpworkingbis").val('');
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

							$("#btnSubmit")
									.click(
											function() {
												
												/* var man = $('drpSupervisor').val();
												alert("Manager "+man);
												alert("Manager2 "+${employee.manager.id});
												 if (man == null){
													
												    alert("Manager cannot be empty !");
												    return false;
												} */ 
												
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
		
		
		 $('#drpprimprogram').change(function() {
				//$("#drpprimprogram").val('${preonbemployee.primaryprogram.primaryProgramId}'); ${listPrimaryProgram}
		
				$.getJSON('${getBisList}',
						{
							ppid : $(this).val(),
							ajax : 'true'
						},
						function(data) {
							//alert(" ");
							var html = '<option value="">-Select-</option>';
							for(var bisEle=0; bisEle < data.length; bisEle++){
								html += '<option value="' + data[bisEle].bis_id + '">'
								+ data[bisEle].bis_Name
								+ '</option>';
							}
							$('#drpbis').html(html);
							//$('#divworkingbis').html(html);
							
						});
				
				
				//Engg - start
				
				
				$.getJSON('${getEMList}',
						{
							ppid : $(this).val(),
							ajax : 'true'
						},
						function(data) {
							var html = '<option value="">-Select-</option>';
							for(var emL=0; emL < data.length; emL++){
								html += '<option value="' + data[emL].id + '">'
								+ data[emL].lastName +" "+data[emL].firstName +" "+" (" + data[emL].corpId +")"
								+ '</option>';
							}
							$('#drpEM').html(html);
						});
				
				
				$.getJSON('${getManagerListNew}',
						{
							ppid : $(this).val(),
							ajax : 'true'
						},
						function(data) {
							var html = '<option value="">-Select-</option>';
							for(var mgrL=0; mgrL < data.length; mgrL++){
								html += '<option info="' + data[mgrL].email + '" value="' + data[mgrL].id + '">'
								+ data[mgrL].lastName +" "+data[mgrL].firstName +" "+" (" + data[mgrL].corpId +")"
								+ '</option>';
							}
							$('#drpSupervisor').html(html);
						});
				
				
				var pp = $(this).val();
			 
				if(pp != 10){
					$("#sdmDiv").css("display", "none");
					$("#businessAp").css("display", "none");
					$("#cfaoDiv").css("display", "none");
					
					$("#orderGid").val("");
					$("#drpBusinessAs").val("");
					$("#otherAsId").val("");
					$("#drpCfao").val("");
					
					$("#RoleT").css("display", "");
					//$("#Tech").css("display", "");
					$("#Indus").css("display", "");
				}
				if(pp == 10){
					
					/* $("#sdmDiv").css("display", "");
					$("#businessAp").css("display", "");
					$("#cfaoDiv").css("display", "");
				
					$("#Role").css("display", "none");
					$("#Tech").css("display", "none");
					$("#Indus").css("display", "none"); */
					
					var psaIdCheck = ${employee.PSAIdReq};
		
					if (PSAIdReq == true) 
					  {
						$("#dobdate").css("display", "");
						$("#drppsaLibType").attr("disabled", false);
						//$("#ChkHRAReq").attr("disabled", false);
						  
					    $("#sdmDiv").css("display", "");
						$("#businessAp").css("display", "");
						$("#cfaoDiv").css("display", "");
						
						$("#RoleT").css("display", "none");
						$("#Tech").css("display", "none");
						$("#Indus").css("display", "none");
						
						$("#drpIndusGoal").val("NA")
						
			          }
					else{
						
						$("#dobdate").css("display", "none");
						  $("#Tech").css("display", "none");
						  $("#drpIndusGoal").val("NA")
						  $("#Indus").css("display", "none");
						  $("#drpIndusGoal").val("NA")
						  $("#RoleT").css("display", "none");
						  $("#drppsaLibType").val(0);
						  //$("#ChkHRAReq"). prop("checked", false);
						 // $("#ChkHRAReq").attr('disabled', !$("#ChkHRAReq").attr('disabled'));
						  //$("#ChkHRAReq").attr("disabled", true);
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
				
			});
		// Commented for testing purpose
		
		/*  $('#txtPSAIDExt').change(function() {
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
		
		
		
		var  employeeList  ="${listOfEmployeedata}";
		/* function arrayToObject(employeeList) {
		    var obj = {};
		    for (var i = 0; i < employeeList.length; ++i){
		        obj[i] = employeeList[i];
		    }
		    return obj;
		}
		var colorsObj=arrayToObject(employeeList); 
		alert("hiiiii" +colorsObj); */
		var employees;
		
		//alert("hiii" +employeeList);
		
		
		const employeee ="${listOfEmployeedata}";
		
		const string=employeee.split(",");
		console.log("hiiiiiii" +string.length);
		//alert("hiiiiiii" +employeee);
		//alert("hiiiiiiiiiiii" +string);
		/* for( var i=0; i<=string.length; i++)
			{
			employees =employees+string[i];
			} */
		//alert("hiiiiiiiii" +employees);
		//var countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central Arfrican Republic","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cuba","Curacao","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar","Namibia","Nauro","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan","Turks &amp; Caicos","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"];
		//debugger;
		//alert("hiii" +countries);
		//console.log("hiiiii " +employeeList);
		autocomplete(document.getElementById("employeeId"), string);
		
		
	</script>
</body>
</html>
