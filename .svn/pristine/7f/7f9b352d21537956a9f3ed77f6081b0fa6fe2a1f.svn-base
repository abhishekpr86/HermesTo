<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
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
	href="${pageContext.request.contextPath}/resources/css/autocompleteData.css" />
  
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/dialog.css">

<style type="text/css">
    .bs-example{
    	margin: 20px;
    }
</style>
</head>
<body>

<APPLET CODE="preOnboardingController.class"
		         NAME="myApplet"
		         HEIGHT=0 WIDTH=0>
		</APPLET>
		
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
						<li><a href="${pageContext.request.contextPath}/userSearch" title="userSearch">Edit User</a></li>
					</ul></li>
				</c:if>
				
				<li class="active"><a href="javascript:void(0);" title="Resource Pre-Onboarding">Onboarding Request</a></li>
					<!-- title="Resource Pre-Onboarding">Resource Pre-Onboarding</a></li> -->
				
				<c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM') or (checkUserTypeforUM ne null)}">
				<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li> --%>
				<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Onboarding Requests</a></li>
				</c:if>

				<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="View List">View Resources</a></li>
				</c:if>

				<c:if test="${(checkUserType eq 'BundleEM')}"><!-- EM -->
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View Resources</a></li>
				</c:if>

				<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO') or (checkUserType eq 'ASL')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View / Edit Resources</a></li>
				</c:if>
				<c:if test="${checkUserType eq 'ViewMode'}">
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
					</ul>
			</li>
				
				<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change
						Password</a></li>
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>

			</ul>
		</nav>

	</header>
	<section class="main-content">
		<br />
		<!-- <h2 style="color:#86A5F7">Pre-Onboard Resource</h2> -->
			<h2 style="color:#86A5F7">Onboarding / Re-Onboarding Request</h2>
			<div class="reqText">* indicates required fields</div>
			<c:url var="addAction" value="/addPreOnboardRec/add"></c:url>
			<c:url var="findCountryStateURL" value="/PreOnbStates" />
			<c:url var="findBIS" value="/PreOnbBis" />
			<c:url var="findBundleEMfromBIS" value="/findBundleEMfromBIS"/>
			<c:url var="checkPreOnBEmployeeExists" value="/checkPreOnBEmployeeExists" />
			<c:url var="checkPlannedEndDate" value="/checkPlannedEndDate" />
			<c:url var="checkBillingDate" value="/checkBillingDate" />
			<c:url var="checkDob" value="/checkDob" />
			<c:url var="newCountryStateMailRequest" value="/newCountryStateMailRequest" />
			<c:url var="checkCorpIdExists" value="/checkCorpIdExistsInPreOnb" />
			<c:url var="getBisList" value="/getBisList" /> 
			<c:url var="checkEmployeeEmailExists" value="/checkPreObEmployeeEmailExists" />
			<c:url var="getDetailsByCorpIDFromActiveDirectory" value="/getDetailsByCorpIDFromActiveDirectoryPreOnb" />
			<c:url var="checkPlannedJoiningDate" value="/checkPlannedJoiningDate" />
			<c:url var="managermails" value="/managermails" />
			<c:url var="getEMList" value="/getEMList" />
			<c:url var="getManagerListNew" value="/getManagerListNew" />  
			
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

			<form:form action="${addAction}" commandName="preonbemployee" method="POST" id="addemployee" autocomplete="off">
			<section>
			<h1 style="color:#86A5F7">Resource Information:</h1>
			<div class="formFields clearfix">
			
				<div class="formRow">
					<div>
						<form:hidden id="id" path="id" />	
						<form:hidden id="resourceStatus" path="resourceStatus" />
						<form:hidden id="EMSubmitDT"  path="EMSubmitDTString" />
						<form:hidden id="creationdate" path="creationdateString"/>
						<form:hidden id="isVLANmailDone" path="isVLANmailDone"/>
						
						<%-- <form:hidden id="RMSubmitDT" path="RMSubmitDT"/>  --%>
					</div>
					<p style="color:red"><spring:message code="label.em.corpid"/></p>
					
					<div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">CORP ID <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="corpId" id="txtCorpID" class="input-control"
								data-validation="required" />
						</div>
					</div>
				<%-- <div class="col50per">
						<div>
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<div class="fieldLbl">
								<label id="ResourceType">Resource Type :</label>
								
							</div>
							<div class="inputField">
								<form:input path="empType" size="20" id="rdEmpCheck" class="input-readonly" />
							</div>
						</div>
					</div> --%> 
					<div class="col50per">
						<div class="fieldLbl">
							<label> Resource Type : </label>
						</div>
						<form:radiobutton path="empType" id="rdIntEmpCheck"
							value="Internal" name="rdoGroup" class="input-control" /> <!-- checked="checked" -->
						<label>Internal</label>
						<form:radiobutton path="empType" value="External"
							id="rdExtEmpCheck" name="rdoGroup" class="input-control" checked="checked" />
						<label>External</label>
						
						<form:hidden id="id" path="id" />
					</div>
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div>
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<div class="fieldLbl">
								<label id="commonID">Emp ID :<span class='req'>*</span>
								</label>
							</div>
							<div class="inputField">
								<form:input path="empId" size="20" id="txtCommonID" class="input-readonly" />
								
							</div>
						</div>
					</div>
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Entity :</label>
						</div>
						<div class="inputField">
							<form:input path="cgEntity" id="drpCapEntity" class="input-readonly" />
						</div>
					</div>
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div>
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<div class="fieldLbl">
								<label id="ggID">GG ID :<span class='req'>*</span>
								</label>
							</div>
							<div class="inputField">
								<form:input path="ggId" size="8" id="txtggID" class="input-readonly" data-validation="required"/>
								
							</div>
						</div>
					</div>
					
						<div class="col50per">
						<div class="fieldLbl">
							<label>Role<span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="emprole" id="drpemprole" class="select-control" data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${listEmployeeRoles}" var="emproles">
								<form:option value="${emproles.ref_id}">${emproles.ref_name}</form:option>

								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>First Name <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="firstName" id="txtFirstName" class="input-readonly" data-validation="required" />
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
								<option value="">-Select-</option>
								<c:forEach items="${listTechnology}" var="technology">
									<form:option value="${technology.technologyId}">${technology.technologyName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Last Name <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="lastName" id="txtLastName" class="input-readonly" data-validation="required" />
						</div>
					</div>
						<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Sex <span class="req">*</span>:
							</label>
						</div>
						
						<div class="inputField">
							<form:select path="empSex" id="rdEmpSex" class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
									<form:option value="M">Male</form:option>
									<form:option value="F">Female</form:option>
							</form:select>
						</div>
					</div> --%>
					
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Secondary Skill:
							</label>
						</div>
						<div class="inputField">
							<form:select path="secondaryTechnology" id="drpSecondaryTechnology"
								class="select-control">
								<option value="">-Select-</option>
								<c:forEach items="${listTechnology}" var="technology">
									<form:option value="${technology.technologyId}">${technology.technologyName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					
				</div>
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Country <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="country" id="drpCountry" class="input-readonly" data-validation="required">
								<option value="">-Select-</option>
								
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
								<c:forEach items="${listState}" var="state">
								  
									<form:option value="${state.stateId}">${state.stateName}</form:option>
								 </c:forEach> 
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="email" id="txtEmail" class="input-readonly" data-validation="email" />
						</div>
					</div>
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Sex <span class="req">*</span>:
							</label>
						</div>
						
						<div class="inputField">
							<form:select path="empSex" id="rdEmpSex" class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
									<form:option value="M">Male</form:option>
									<form:option value="F">Female</form:option>
							</form:select>
						</div>
					</div>
					
					
					
					
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Secondary Skill:
							</label>
						</div>
						<div class="inputField">
							<form:select path="secondaryTechnology" id="drpSecondaryTechnology"
								class="select-control">
								<option value="">-Select-</option>
								<c:forEach items="${listTechnology}" var="technology">
									<form:option value="${technology.technologyId}">${technology.technologyName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div> --%>
					
				<div class="col50per" id="divresourcemanager" style="display: block;">
						<div class="fieldLbl">
							<label>Resource Manager<span class="req" style="display: block;"></span>:</label>
						</div>
						<div class="inputField">

							<form:select path="resourceManager" id="drpresourceManager"
								class="select-control">
								<option value="">-Select-</option>
								<c:forEach items="${listResourceManager}" var="resmngr">
									<form:option value="${resmngr.resourceMgrId}">${resmngr.resourceMgrName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				
				<div class="formRow">
					<div class="col50per" id="divGlobalGrade" style="display: block;">
						<div class="fieldLbl">
							<label id="GlobalGrade">Global Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="globalGrade" id="drpGlobalGrade" class="input-readonly" data-validation="required"  style="width: 168px;">
								<c:forEach items="${listGlobalGrades}" var="globalGrades">
									<form:option value="${globalGrades.globalGradeId}">${globalGrades.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="col50per" id="divGrade" style="display: none;">
						<div class="fieldLbl">
							<label>Local Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="grade" id="drpGrade" class="select-control"
								data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${listGrades}" var="grd">
									<form:option info="${grd.globalGradeId}" value="${grd.gradeId}">${grd.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div> 
				</div>
				
				<%-- <div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Hermes Manager <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="manager" id="drpSupervisor" class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listEmployees}" var="mgr">
									<c:set var="listEmployees" value="${listEmployees}" />
									
									<c:if test="${!empty mgr.email}">
										<c:if test="${!empty mgr.corpId}">
											<form:option info="${mgr.email}" value="${mgr.id}">${mgr.lastName} ${mgr.firstName}  ( ${mgr.corpId} )</form:option>
										</c:if>
										<c:if test="${empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.lastName} ${mgr.firstName}</form:option>
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
							<form:input path="managerEmail" id="mgrEmail" class="input-readonly" data-validation="required" />
						</div>
					</div>
				</div> --%>
				 <div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy <!-- <span class="req">*</span> -->:
							</label>
						</div>
						

                    <%--  <form autocomplete="off" action="/action_page.php">
						<div class="inputField">
						 <div class="autocomplete" style="width:300px;">
						
							<input  id="employeeId"  type="text"  name="myCountry"  value= "${listofEmployees}" placeholder="Country">
							</div>
							 <input type="submit">
							</div>
							</form> --%>
							
                           <div class="autocomplete" style="width:430px; border:1px solid #b9b9b9; height:30px">
                        <input path="buddy" id="employeeId" type="text" name="listofEmployees"  style="background-color: #ffffff; width:428px; height:28px" > <!-- data-validation="required" -->
                         </div>
                   
                       
						</div>	
				<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding buddy Email <!-- <span class="req">*</span> -->:
							</label>
						</div>
						<div class="inputField">
							<form:input path="onboardingEmail" id="mgrEmails" class="input-readonly"  /> <!-- data-validation="required"  -->
						</div>
					</div>
             </div>
           
          
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Manager <span class="req">*</span>:
							</label>
						</div>
						
						<div class="inputField">
							<form:input path="capManager" id="capMgr" class="input-readonly" data-validation="required" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Manager Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="capManagerEmail" id="capMgrEmail" class="input-readonly" data-validation="required" />
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
							<form:input path="capSupervisor" id="capSup" style="background-color: #ffffff;" class="input-control" />
						</div>
					</div>
					<div class="col50per"  id="divCapSupEmail" style="display: none;">
						<div class="fieldLbl">
							<label>Capgemini Supervisor Email <span class="req"></span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="capSupervisorEmail" id="capSupEmail" style="background-color: #ffffff;" class="input-control" />
						</div>
					</div>
				</div>
				
				<div class="formRow">
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
				
					</section>
					
					
					
					<h1 style="color:#86A5F7">Project Information:</h1>
					<div class="formFields clearfix">
					<section>
					
					<div class="formRow">
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
							<form:input path="project" id="project" class="input-control"  style="background-color: #ffffff;"/>
						</div>
						
					</div> 
				</div>
						<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Bundle-Domain <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="bis" id="drpbis" class="select-control"  data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${fullListOfBis}" var="bis">
									<form:option value="${bis.bis_id}">${bis.bis_Name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				
					
						<div class="col50per" id="divBundleEM" style="display: block;">
					<div class="fieldLbl">
							<label>Bundle EM :</label>
					</div>
					<div class="inputField">
						<form:select path="bundleEM"  id="drpbundleEM" class="input-readonly" data-validation="required">
								
						
								<c:forEach items="${listBundleEm}" var="bundleem" >
									<form:option value="${bundleem.bundleEmId}">${bundleem.bundleEmName} </form:option>
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
				
				<!-- Engg - start -->
				<div class="formRow">
				<div class="col50per">
						<div class="fieldLbl">
							<label>Manager <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="manager" id="drpSupervisor" class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listEmployees}" var="mgr">
									<c:set var="listEmployees" value="${listEmployees}" />
									
									<c:if test="${!empty mgr.email}">
										<c:if test="${!empty mgr.corpId}">
											<form:option info="${mgr.email}" value="${mgr.id}">${mgr.lastName} ${mgr.firstName}  ( ${mgr.corpId} )</form:option>
										</c:if>
										<c:if test="${empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.lastName} ${mgr.firstName}</form:option>
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
							<form:input path="managerEmail" id="mgrEmail" class="input-readonly" data-validation="required" />
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
							<form:select path="EM" id="drpEM" class="select-control"
								data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listEmployeesByEm}" var="mgr">
									<c:set var="listEmployeesByEm" value="${listEmployeesByEm}" />
									<c:if test="${!empty mgr.email}">
									<c:if test="${!empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.lastName} ${mgr.firstName} ( ${mgr.corpId} )</form:option>
									</c:if>
									<c:if test="${empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.lastName} ${mgr.firstName}</form:option>
									</c:if>
									</c:if>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					
					
					<div class="col50per" id ="sjira" style="display: block;">
						<div class="fieldLbl">
							<label>Staff JIRA # <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="jiraNumber" id="jiraNum" size="10" class="input-control" data-validation="number" data-validation-help="Please enter number" style="background-color: #ffffff"/>
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
							<form:input path="onboardingDate" id="txtJoiningDate"
								class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" style="background-color: #ffffff;"/>
						</div>
					</div>
					
					
					<div class="col50per" id ="staffingrr" style="display: none;">
						<div class="fieldLbl">
							<label>GTD # <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<%-- <form:input path="staffingRR" id="staffRR" size="10" style="background-color: #ffffff;" class="input-control" data-validation="number" data-validation-help="Please enter number"/> --%>
							<form:input path="staffingRR" id="staffRR" size="10" style="background-color: #ffffff;" class="input-control" data-validation="alphanumeric" data-validation-help="Please enter alphanumeric data"/>
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
							<form:input path="offboardingDate" id="txtEndDate"
								class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" style="background-color: #ffffff;"/>
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
							<form:input path="billingStartDate" id="billingDate"
								class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" style="background-color: #ffffff;"/>
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
									<form:option value="Fixed Price" selected="true">Fixed Price</form:option>
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
							<label>Type Of Demand <span class="req">*</span>:</label>
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
							<label>Employee Criticality<span class="req">*</span>:
							</label>
						</div>
						
						<div class="inputField">
							<form:select path="criticality" id="empCriticality" class="select-control" data-validation="required">
								<option value="">-Select-</option>
									<form:option value="L" selected="true">Low</form:option>
									<form:option value="M">Medium</form:option>
									<form:option value="H">High</form:option>
							</form:select>
						</div>
					</div>
					
					</div>
			
					<div class="formRow">
					
					<div class="col50per" id ="allocationDiv">
						<div class="fieldLbl">
							<label>Allocation % <span class="req">*</span>:</label>
						</div>
						<div class="inputField" style="margin-top:8px;">
							<form:input path="allocation" id="allocation" size="3" class="input-control" data-validation="number" data-validation-help="Please enter number" style="width:70px; background-color: #ffffff;"/>
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
				</div>
				
				
				<%-- <div class="formRow">
				
				<div class="col50per">
						
				</div>
				
				
				<div class="col50per">
						<div class="fieldLbl">
							<label>BI : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="bi" id="Chkbi"/>
							Yes
						</div>
					</div>
				</div> --%>
				
		</div>
				
				
					<!-- </section> -->
				</div>
			
					
				
					
					
					<h1 style="color:#86A5F7">Technical Information:</h1>
				<div class="formFields clearfix">
				<section>
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Request for PSA ID : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="PSAIdReq" id="ChkPSAIdReq" onclick="calc(); "/>
							Yes
							
						</div>
					
					</div>

				<div class="col50per" id="dobdate"
						style="display: block;">
									
								<div class="fieldLbl"  >
									<label>Date of Birth(dd/mm/yyyy) mandatory for PSA-ID creation<span class="req">*</span>:
									</label>
								</div>
								<div class="inputField">
	         							<%-- <form:input path="dobDate" id="txtdob"  pattern="\d{1,2}/\d{1,2}/\d{4}" style="width: 120px;"
										class="input-control" data-validation="required"/> --%>		
										<!-- mehens -->	
 										<form:input path="dobDate" id="txtDob" style="background-color: #ffffff;"
										class="input-control" data-validation="date"
										data-validation-format="dd/mm/yyyy"
										data-validation-help="Please enter date in dd/mm/yyyy format"/>
										
									</div>
								
						</div>
						
				</div>                                                                    
					<div class="formRow">
					 <div class="col50per" id ="Tech" style="display: block;">
						<div class="fieldLbl">
							<label>Technical / Development <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="type" id="drpTechDev"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								
								<option value="1">Industrialisator</option>
								<option value="2">Developer</option>
								<option value="3">Transversal</option>
								<option value="4">High Cycle</option>
								<option value="0">NA</option>
							</form:select>
						</div>
					</div> 
				
					
				</div>
				
				<div class="formRow">
					<div class="col50per" id ="Indus" style="display:block;">
						<div class="fieldLbl">
							<label>INDUS GOAL <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="indusGoals" id="drpIndusGoal"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								
								  <c:forEach items="${listIndusGoals}" var="indusgoals">
								  
									<form:option value="${indusgoals.indusGoalId}">${indusgoals.indusGoalName}</form:option>
								 </c:forEach>  
							</form:select>
						</div>
					</div>
					
					<div class="col50per" id="vmReq">
						<div class="fieldLbl">
							<label>Request for VM <span class="req">*</span>:</label>
						</div>
						<div class="inputField">
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
				
				<div class="col50per" id ="RoleT" style="display: block;">
						<div class="fieldLbl">
							<label>Role / Technology <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="roleTech" id="drpRoleTech"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
								 <c:forEach items="${listRoleTech}" var="roleTech">
									<form:option value="${roleTech.roleTechId}">${roleTech.roleTechName}</form:option>
								 </c:forEach> 
							</form:select>
						</div>
					</div>
				
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>Hermes Remote Access Request : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="HRAReq" id="ChkHRAReq"/>
							Yes
						</div>
					</div> --%>
					
			</div>
				
				<div class="formRow">
					<!-- <div class="col50per" id="PCDetails" style="display: none;"> -->
						<div class="col50per" style="display: block; background-color: #ffffff;">
							
							<div class="fieldLbl">
								<label>PC Hostname<!-- <span class="req">*</span> -->:</label>
							</div>
							
							<div class="inputField" >
							<%-- <form:input path="pcSerialNumber" id="pcDetailsID" class="input-control" data-validation="alphanumeric" data-validation-help="Please enter alphanumeric data" style="width:168px; background-color: #ffffff;"/> --%>
							<form:input path="pcSerialNumber" id="pcDetailsID" class="input-control"  style="width:168px; background-color: #ffffff;"/> <!-- data-validation="required" data-validation-help="Please enter value" -->
							</div>
						
					</div>
					<%-- <div class="col50per" id="MACAddr" style="display: none;">
						<div class="fieldLbl">
							<label id="corpID">MAC Address :</label>
							
						</div>
						<div class="inputField">
		
							<form:input path="macAddress" id="macAddressID" class="input-control"  style="width: 168px;background-color: #ffffff;" data-validation="required"/>
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
				
       
		 
		 <%-- <div class="formRow">
			<div class="fieldLbl">
				<label>Clarity Code & Allocation <span class="req">*</span>:</label>
			</div>
			<div class="inputField">
				<form:textarea path="clarityCodeAllocation" maxlength="200" minlength="1" id="clarityCA"
					class="textarea-control" data-validation="required" data-validation-help="Please add Clarity Code & Allocation %"/>
			</div>
		 </div> --%>
		 <div class="formFields clearfix">
		 <section>
		 <div class="formRow">
			<div class="fieldLbl">
				<label>Comment :</label>
			</div>
			<div class="inputField">
				<form:textarea path="comment" maxlength="200" id="comment"
					class="textarea-control" />
			</div>
		</div>
		</section>
		</div>
		</section> <!-- new - mehens -->

		<div class="formRow"></div>
		
		<div class="text-center">
			<input type="submit" value="Add" title="On-board" id="btnSubmit" class="btn-primary" /> 
			<input type="Reset" value="Reset" title="Reset" id="btnReset" class="btn-primary" />
		</div>
		</form:form>
		
		<!-- Modal HTML -->
		    <div id="myModal" class="modal fade">
		        <div class="modal-dialog">
		            <div class="modal-content">
		                <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                    <h4 class="modal-title">Please raise request for new Country/ Location below</h4>
		                </div>
		                <div class="modal-body">
		                    <form>
		                        <div class="form-group">
		                            <label for="newRequest">Request</label>
		                            <textarea class="form-control" id="newRequest" rows="4"></textarea>
		                        </div>
		                    </form>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		                    <button type="button" id="MailSubmit" class="btn btn-primary">Send</button>
		                </div>
		            </div>
		        </div>
		    </div>
		    
		    
		</div>

	</section>
	
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/datatables.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.form-validator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/tableExportEmp.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.base64.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<!-- <script>
     autocomplete(document.getElementById("myInput"), countries);
    </script> -->
	
	
	
	<script type="text/javascript">
	
	
	//const listofEmployees= "${listOfEmployees}";
	//alert("listofEmployees " +"${empLists}");
	/*  /*   var listofEmployees =  $("#employeeId"); */
		//$("#employeeId").val('${preonbemployee.vendor.vendorId}');
		/* var listofEmployees =document.myApplet.listOfEmployees();
	  alert("hiiii" +listofEmployees); */ 
	  <%-- <%= listofEmployees.toJSONString() %> --%>
	//autocomplete(document.getElementById("employeeId"), listofEmployees);
	
		$(document)
				.ready(
						function() { calc();
							
							 if($('#rdExtEmpCheck').is(':checked')){


								$("#divVendor").css("display",
								"");} 
							
							$("#myModal").on('shown.bs.modal', function(){
						        $(this).find('textarea').focus();
						    });
							$("#drpCountry").val('${preonbemployee.country.countryId}');
							
							/* if('${preonbemployee.empType}' == "External"){
								$("#divVendor").css("display","");
								
								$("#divVendor").css("display","");
								
							}  */
							$("#pcDetailsID").val('${preonbemployee.pcSerialNumber}');
							$("#drpVendor").val('${preonbemployee.vendor.vendorId}');
							$("#empSex").val('${preonbemployee.empSex}');
							$("#criticality").val('${preonbemployee.criticality}');//mehens-new
							
							$("#timeMat").val('${preonbemployee.timeMat}');//mehens
							
							$("#drpbundleEM").val('${preonbemployee.bundleEM.bundleEmId}');
							$("#drpprimprogram").val('${preonbemployee.primaryprogram.primaryProgramId}');
							$("#project").val('${preonbemployee.project}');
							$("#mgrEmail").val('${preonbemployee.manager.email}');
							$("#drpEM").val('${preonbemployee.EM.id}');
							$("#drpbis").val('${preonbemployee.bis.bis_id}');
							$("#drpSupervisor").val('${preonbemployee.manager.id}');
							$("#employeeId").val('${preonbemployee.buddy.id}');
							
							//Engg
							$("#orderGid").val('${preonbemployee.orderG}');
							$("#drpBusinessAs").val('${preonbemployee.businessAs.basId}');
							$("#otherAsId").val('${preonbemployee.otherAs}');
							$("#drpCfao").val('${preonbemployee.cfao.cfaoId}');
							//Engg
							
							//Bhavna add new fields
							
								$("#allocation").val('${preonbemployee.allocation}');
								$("#drpworkingbis").val('${preonbemployee.workingBis.bis_id}');
								$("#capMgr").val('${preonbemployee.capManager}');
								$("#capMgrEmail").val('${preonbemployee.capManagerEmail}');
								$("#capSup").val('${preonbemployee.capSupervisor}');
								$("#capSupEmail").val('${preonbemployee.capSupervisorEmail}');
								
							
							//Bhavna add new fields - end
							$("#drpresourceManager").val('${preonbemployee.resourceManager.resourceMgrId}');
							$("#drpLocation").val('${preonbemployee.location.stateId}');
							
							
							$("#drpGrade").val('${preonbemployee.grade.gradeId}');
							$("#drpGlobalGrade").val('${preonbemployee.globalGrade.globalGradeId}');
							$("#drpemprole").val('${preonbemployee.emprole}');
							$("#drpPrimaryTechnology").val('${preonbemployee.primaryTechnology.technologyId}');
							$("#drpSecondaryTechnology").val('${preonbemployee.secondaryTechnology.technologyId}');//mehens
							$("#drpIndusGoal").val('${preonbemployee.indus.indusGoalId}');
							$("#drpTechDev").val('${preonbemployee.type}');
							$("#txtCommonID").val('${preonbemployee.empId}');
							$("#jiraNum").val('${preonbemployee.jiraNumber}');
							$("#staffRR").val('${preonbemployee.staffingRR}');
							
							$("#drpCapEntity").val('${preonbemployee.cgEntity}');
							$("#drpRoleTech").val('${preonbemployee.roleTech}');
							$("#ChkPSAIdReq").val('${preonbemployee.PSAIdReq}');
							
							$("#id").val('${preonbemployee.id}');
							$("#drpdemand").val('${preonbemployee.demandType.id}');
							$("#resourceStatus").val('${preonbemployee.resourceStatus}');
							$("#isVLANmailDone").val('${preonbemployee.isVLANmailDone}');
							$("#EMSubmitDT").val('${preonbemployee.EMSubmitDTString}');
							$("#creationdate").val('${preonbemployee.creationdateString}');
							calc();
							$("#drppsaLibType").val('${preonbemployee.psaLibType.psaLibId}');
							
							if($('#drpbis').val() == 5){
								$("#divworkingbis").css("display","");
							}else{
								$("#divworkingbis").css("display", "none");
							}
							
							 $("#btnSubmit").click(function() {
								 
								 
							}); 
							 rejectfn();
								

							 $("#MailSubmit").click(function(){ //mailSubmit
								//alert("mailSubmit");
							 	
								 $.getJSON('${newCountryStateMailRequest}',
											{
									 newRequest : $('#newRequest').val(),
									 corpid : $('#txtCorpID').val(),
									 country : $('#drpCountry').val(),
										ajax : 'true'
									},
									function(data) {
										
										if (data) {
											alert("Mail sent to Security team");
										}
									});
								 $('#myModal').modal('hide'); 
								 
							 });
							 
							  
							 
							 $("#addemployee").submit(function() {
								if($('#addemployee').isValid()){
							        $("#btnSubmit").attr("disabled", true);
							 	}
							        return true;  
							 });

							if ($("#userReadOnly").val() == 'true') {
								$("#btnSubmit").css("display", "none");
								/* $("#btnReset").css("display","none"); */

								$('div *').prop('disabled', true);
							}
							
													
													
							$('#allocation').change(
									function() {
										if($('#allocation').val() > 100 || $('#allocation').val() < 0){
											$('#allocation').val("");
											alert ("Allocation % should be between 0-100")
										}
							});
													
							$('#drpSupervisor')
									.change(
											function() {
												$("#drpSupervisor option:selected").each(
																function() {
																	var info = $(this).attr("info");
																	if (info != undefined) {
																		$("#mgrEmail").val(info);
																	} else {
																		$("#mgrEmail").val("");
																	}
																});
											});
							
						
							$('#billingDate').change(
									function(){
										
										$.getJSON('${checkBillingDate}',
											{
												billingDateString : $('#billingDate').val(),
												plannedEndDate : $('#txtEndDate').val(),
												joiningDate : $('#txtJoiningDate').val(),
												ajax : 'true'
											},
											function(data){
												
												if(!data){
													alert("Billing Date should be in between Onboarding Date and Planned Offboarding Date");
													$('#billingDate').val('');
												} 
											});
									});

							
							$('#project').change(
									function(){
									var v = $('#project').val();
										if( v.length > 50){
											alert("Project must not exceed 50 charcaters !");
											$('#project').val('');
										}
									});
							
							$('#txtEndDate').change(
									function(){
										$.getJSON('${checkPlannedEndDate}',
											{
												plannedEndDate : $('#txtEndDate').val(),
												joiningDate : $('#txtJoiningDate').val(),
												ajax : 'true'
											},
											function(data){
												if(!data){
													alert ("Planned Offboarding Date should be greater than Onboarding Date");
													$('#txtEndDate').val('');
												}
											});
										
								});
							
							//start
							$('#txtDob').change(
									function(){
										$.getJSON('${checkDob}',
											{
												dobDate : $('#txtDob').val(),
												ajax : 'true'
											},
											function(data){
												if(!data){
													alert ("DOB should be before 18 years");
													$('#txtDob').val('');
												}
											});
										
								});
							//end
							
							$('#txtJoiningDate').change(
									function(){
										$.getJSON('${checkPlannedJoiningDate}',
											{
												
												joiningDate : $('#txtJoiningDate').val(),
												ajax : 'true'
											},
											function(data){
												if(!data){
													alert ("Onboarding date cannot be in future");
													$('#txtJoiningDate').val('');
												}
											});
										
								});
							$('#txtCommonID').change(
											function() {
												$.getJSON('${checkPreOnBEmployeeExists}',
																{
																	empId : $('#txtCommonID').val(),
																	extrnalSelectn : ($("#rdExtEmpCheck").is(':checked')),
																	internalSelectn : ($("#rdIntEmpCheck").is(':checked')),
																	ajax : 'true'
																},
																function(data) {
																	
																	if (data) {
																		
																		/* var extrnalSelectn = ($("#rdExtEmpCheck").is(':checked')); */
																		var internalSelectn = ($("#rdIntEmpCheck").is(':checked'));
																		
																		/* if(extrnalSelectn == true) {
																			alert("Psa Id already exists . Please try another one . ");
																			$('#txtCommonID').val("");
																		} 
																		else*/ if(internalSelectn == true) {
																		alert("Emp Id already exists . Please try another one . ");
																		$('#txtCommonID').val("");
																	}
																	}

																});
											});
							
							 $('#txtCorpID').change(
										function() {
											$
													.getJSON(
															'${getDetailsByCorpIDFromActiveDirectory}',
															{
																corpId : $('#txtCorpID').val(),
																ajax : 'true'
															},
															function(data) {
															 if(data) {
																 if(data.email!=null){
																	/* alert(data); */
																	$('#txtFirstName').val(data.firstName);
																	$('#txtLastName').val(data.lastName);
																	$('#txtEmail').val(data.email);	 
																	$('#txtCommonID').val(data.empId);
																	$('#txtggID').val(data.ggId);
																	if(data.country!=null){
																	$('#drpCountry').val(data.country.countryId);
																	}
																	$('#drpLocation').val(data.location);
																	$('#drpGlobalGrade').val(data.globalGrade);
																	$('#drpCapEntity').val(data.capgemEntity);
																	$('#capMgr').val(data.manager);
																	$('#capMgrEmail').val(data.managerEmail);
																	$("#pcDetailsID").val(data.pcSerialNumber);
																	countryChangeFn();
																	if(data.employeeTypes == 'Employee')
																		{
																		removeValidation();
																	    $("#rdExtEmpCheck").prop("checked",false);
																		$("#rdIntEmpCheck").prop("checked",true);
																		/* $('#txtCommonID').removeAttr(
																				'data-validation'); */
																				$('#txtCommonID').attr("data-validation","required");
																		$('#txtCommonID').get(0)
																				.setAttribute('type',
																						'text');
																		$("#commonID").css("display",
																		"");
																		$("#txtCommonID").css("display","");
																		$("#commonID")
																				.html(
																						"Emp ID <span class='req'></span>:");
																		$("#corpID")
																				.html(
																						"CORP ID <span class='req'>*</span>:");
																		$("#txtCorpID").attr("data-validation","required");
																		$("#globalGrade")
																		.html(
																				"Global Grade <span class='req'>*</span>:");
																		$("#drpGlobalGrade").attr("data-validation","required");
																		
																		$("#divVendor").css("display",
																				"none");
																		$('#txtEndDate').val("");
																		$("#divCorpId").css("display",
																				"");
																		
																		$("#divGlobalGrade").css("display",
																		"");
																		
																		$("#divPSA").css("display", "");
																		}
																	/* else if(data.employeeTypes == 'Subcontractor') */
																	else if(data.employeeTypes != 'Employee')
																		{
																		removeValidation();
																		$("#rdIntEmpCheck").prop("checked",false);
																		$("#rdExtEmpCheck").prop("checked",true);
																		$('#txtCommonID').css("display",
																		"none"); 
																		$('#commonID').css("display",
																		"none");
																	
																		 /*$("#commonID").html("PSA ID :");*/
																		/* $('#txtCorpID').removeAttr(
																				'data-validation');
																		$("#corpID")
																				.html(
																						"CORP ID <span class='req'></span>:"); */
																		
																		$('#drpGlobalGrade').removeAttr(
																		'data-validation');
															
																		$("#globalGrade")
																		.html(
																				"Global Grade <span class='req'></span>:");
																		
																		
																		$("#divVendor").css("display",
																				"");
																		$("#divCorpId").css("display",
																				"");
																		
																		
																		$("#divGlobalGrade").css("display",
																		"");
																		
																	
																		
																		
																		$('#txtEndDate').val("");
																		/*$("#divPSA").css("display",
																				"none");
																		 $("#txtPsaId").attr(
																				"data-validation",
																				"number"); */
																		}
																		 
																		
																		
																	
																	
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
							
							 
							 $.getJSON('${findCountryStateURL}',{
							 countryId : $('#drpCountry').val(),
							 ajax : 'true'
							 },

							 function(data) {

							 var vendorhtml = '<option value="">-Select-</option>';
							 vendorList = data.vendorList;
							 var vendorLength = vendorList.length;


							 if('${preonbemployee.vendor.vendorId}'){

							 let vendorHtml = "";
							 for (var i = 0; i < vendorLength; i++) {


							 if(vendorList[i].vendorId == '${preonbemployee.vendor.vendorId}'){

							 vendorHtml += '<option selected value="' + vendorList[i].vendorId + '">'
							 + vendorList[i].vendorName
							 + '</option>';

							 }
							 else{
							 vendorHtml += '<option value="' + vendorList[i].vendorId + '">'
							 + vendorList[i].vendorName
							 + '</option>';
							 }

							 }
							 vendorHtml += '</option>';
							 $('#drpVendor').html(vendorHtml);
							 }
							 else{
							 for (var i = 0; i < vendorLength; i++) {
							 vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
							 + vendorList[i].vendorName
							 + '</option>';

							 }
							 vendorhtml += '</option>';
							 $('#drpVendor').html(vendorhtml);
							 }
							})							
							 
							 function rejectfn() {
								 //one
								 if ($('#drpCountry').val() == "2") {
									 $("#divGrade").css("display", "");
									 $("#staffingrr").css("display", "");
									 $("#divCapSup").css("display", "");
									 $("#divCapSupEmail").css("display", "");
								 }
								 else if ($('#drpCountry').val() == "1" || $('#drpCountry').val() == "9") {//mehens
									 $("#staffingrr").css("display", "");
									 $("#divGrade").css("display", "none");
									 $("#divCapSup").css("display", "none");
									 $("#divCapSupEmail").css("display", "none");
								 }
								 else{
									 $("#divGrade").css("display", "none");
									 $("#staffingrr").css("display", "none");
									 $("#divCapSup").css("display", "none");
									 $("#divCapSupEmail").css("display", "none");
									 }
								
								 }
							 
							     if($('#ChkPSAIdReq').val() == 'false'){
							    	 $("#dobdate").css("display", "none");
							    	 $("#drppsaLibType").val(0);
							     }
							     
							     
							     
							     /* $('#drppsaLibType').change(function() {
							    	 if($('#drppsaLibType').val() == 0){
							    		 $("#ChkHRAReq"). prop("checked", false);
							    		 $("#ChkHRAReq").attr("disabled", true);
							    	 }else{
							    		 $("#ChkHRAReq"). prop("checked", true);
							    		 $("#ChkHRAReq").attr("disabled", false);
							    	 }
							     }); */
							 
						
							 function countryChangeFn() {
								 //alert("one");
								 if ($('#drpCountry').val() == "2") {
									 
									 if($("#drpprimprogram").val() != 10){
											$("#sjira").css("display", "");
											$("#staffingrr").css("display", "");
										}	
									 
										$("#divOffShoreEm").css("display","");
										$("#divphase").css("display", "");
										$("#divGrade").css("display", "");
										//$("#staffingrr").css("display", "");
										$("#divCapSup").css("display", "");
										$("#divCapSupEmail").css("display", "");
										
										
									} 
								 else if ($('#drpCountry').val() == "1" || $('#drpCountry').val() == "9") {//mehens
									 
									 if($("#drpprimprogram").val() != 10){
											$("#sjira").css("display", "");
											$("#staffingrr").css("display", "");
										}	
									 
									 //$("#staffingrr").css("display", "");
									 $("#divOffShoreEm").css("display","none");
									 $("#divphase").css("display","none");
									 $("#divGrade").css("display", "none");
									 $("#divCapSup").css("display", "none");
									 $("#divCapSupEmail").css("display", "none");
								 }
								 else {
	
									 if($('#drpprimprogram').val() != 10){
											$("#sjira").css("display", "");
											$("#staffingrr").css("display", "none");
										}
									 
										$("#divOffShoreEm").css("display","none");
										$("#divphase").css("display","none");
										$("#divGrade").css("display", "none");
										//$("#staffingrr").css("display", "none");
										$("#divCapSup").css("display", "none");
										$("#divCapSupEmail").css("display", "none");
									}

									if ($('#drpCountry').val() == "1" || $('#drpCountry').val() == "6") {

										$("#divresourcemanager").css("display", "");

									} else {

										$("#divresourcemanager").css("display", "none");

									}
									
									//two
									$.getJSON(
											'${findCountryStateURL}',
											{
												countryId : $('#drpCountry').val(),
												ajax : 'true'
											},

											function(data) {
												var html = '<option value="">-Select-</option>';
												var vendorhtml = '<option value="">-Select-</option>';
												var resmgrhtml = '<option value="">-Select-</option>';
												var gradehtml = '<option value="">-Select-</option>';
												$("#txtSpocName").val(data.spoc.spocName);
												$("#txtSpocEmail").val(data.spoc.spocEmail);
												$("#spocId").val(data.spoc.spocId);
												stateList = data.stateList;
												
												var stateLength = stateList.length;
												for (var i = 0; i < stateLength; i++) {
													html += '<option value="' + stateList[i].stateId + '">'
															+ stateList[i].stateName
															+ '</option>';
															
												}
												html += '</option>';
												$('#drpLocation').html(html);

												resourceMgrList = data.resourceMgrList;
												var resMgrLength = resourceMgrList.length;
												for (var i = 0; i < resMgrLength; i++) {

													resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '">'
															+ resourceMgrList[i].resourceMgrName
															+ '</option>';

												}
												resmgrhtml += '</option>';
												$('#drpresourceManager').html(resmgrhtml);

												vendorList = data.vendorList;
												var vendorLength = vendorList.length;
												for (var i = 0; i < vendorLength; i++) {
													vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
															+ vendorList[i].vendorName
															+ '</option>';
												}
												vendorhtml += '</option>';
												$('#drpVendor').html(vendorhtml);
												gradeList = data.gradeList;
												var gradeLength = gradeList.length;
												for (var i = 0; i < gradeLength; i++) {
													gradehtml += '<option info="' + gradeList[i].globalGradeId + '" value="' + gradeList[i].gradeId + '">'
															+ gradeList[i].name
															+ '</option>';
												}
												gradehtml += '</option>';
												$('#drpGrade')
														.html(
																gradehtml);
											});
									
							 }
							
							
							
							
							$('#txtCorpID')
							.change(
									function() {
										$
												.getJSON(
														'${checkCorpIdExists}',
														{
															corpId : $(
																	'#txtCorpID')
																	.val(),
															ajax : 'true'
														},
														function(data) {
															if (data == true) {
																alert("Corp Id already exists . Please try another one . ");
																$('#txtCorpID').val("");
																$('#txtFirstName').val("");
																$('#txtLastName').val("");
																$('#txtEmail').val("");
																
															}
															

														});
									});
							
							
							
							$('#txtEmail')
							.change(
									function() {
										$
												.getJSON(
														'${checkEmployeeEmailExists}',
														{
															email : $(
																	'#txtEmail')
																	.val(),
															ajax : 'true'
														},
														function(data) {
															if (data == true) {
																alert("Resource with this email id already exist.");
																$('#txtEmail').val("");
															}

														});
									});	
							
							
							
							$('#drpCountry').change(
									
									function() {
										if ($(this).val() == "2") {
											$("#divOffShoreEm").css("display","");
											$("#divphase").css("display", "");
											$("#divCapSup").css("display", "");
											$("#divCapSupEmail").css("display", "");
											$("#divGrade").css("display", "");
											$("#staffingrr").css("display", "");
											
											
										}
										else if ($(this).val() == "1" || $(this).val() == "9") {//mehens
											$("#staffingrr").css("display", "");
											$("#divOffShoreEm").css("display","none");
											$("#divphase").css("display","none");
											$("#divCapSup").css("display", "none");
											$("#divCapSupEmail").css("display", "none");
											$("#divGrade").css("display", "none");
										}
										else {

											
											$("#divOffShoreEm").css("display","none");
											$("#divphase").css("display","none");
											$("#divCapSup").css("display", "none");
											$("#divCapSupEmail").css("display", "none");
											$("#divGrade").css("display", "none");
											$("#staffingrr").css("display", "none");
										}

										if ($(this).val() == "1" || $(this).val() == "6") {

											$("#divresourcemanager").css("display", "");

										} else {

											$("#divresourcemanager").css("display", "none");

										}
								
									});

							$('#drpLocation').change(function() { //bhavna
								
								
								var locationId = $('#drpLocation').val();
								
								//$("#PCDetails").css("display","none");
								//$("#MACAddr").css("display","none");
								
								/* if(${VLANList} == undefined){
									alert("change");
								} */
								/* $.each(${VLANList}, function( vlanIndex, val ) {
									//alert("VLAN Check " +val);
									if(val == locationId){
										//$("#PCDetails").css("display","");
										$("#MACAddr").css("display","");
									}
								}); */
								
								/* if($('#drpCountry').val() == 2){
									//$("#PCDetails").css("display","none");
									$("#MACAddr").css("display","none");
								} */
								
								$.each( ${newStateList}, 
										function( index, val ) {
									//alert("New Site Check " +val);
									if(val == locationId){
										$('#myModal').modal('show'); 
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
															if($('#drpbis').val() == 5 || $('#drpbis').val() == 6){
																$("#divworkingbis").css("display","");
															}else{
																$("#divworkingbis").css("display", "none");
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
																	var resmgrhtml = '<option value="">-Select-</option>';
																	var gradehtml = '<option value="">-Select-</option>';
																	$("#txtSpocName").val(data.spoc.spocName);
																	$("#txtSpocEmail").val(data.spoc.spocEmail);
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
																	$('#drpresourceManager').html(resmgrhtml);

																	vendorList = data.vendorList;
																	var vendorLength = vendorList.length;
																	for (var i = 0; i < vendorLength; i++) {
																		vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
																				+ vendorList[i].vendorName
																				+ '</option>';
																	}
																	vendorhtml += '</option>';
																	$('#drpVendor').html(vendorhtml);
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
											} );
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
							$.validate({
								form : '#addemployee',
								modules : 'date'
							});
							$("#rdIntEmpCheck")
									.click(
											function() {
												removeValidation();
												/* $('#txtCommonID').removeAttr(
														'data-validation'); */
														$('#txtCommonID').attr("data-validation","required");
												$('#txtCommonID').get(0)
														.setAttribute('type',
																'text');
												$("#commonID").css("display",
												"");
												$("#txtCommonID").css("display","");
												$("#commonID")
														.html(
																"Emp ID <span class='req'></span>:");
												$("#corpID")
														.html(
																"CORP ID <span class='req'>*</span>:");
												$("#txtCorpID").attr("data-validation","required");
												$("#globalGrade")
												.html(
														"Global Grade <span class='req'>*</span>:");
												$("#drpGlobalGrade").attr("data-validation","required");
												
												$("#divVendor").css("display",
														"none");
												$('#txtEndDate').val("");
												$("#divCorpId").css("display",
														"");
												
												$("#divGlobalGrade").css("display",
												"");
												
												$("#divPSA").css("display", "");
											})

							$("#rdExtEmpCheck")
									.click(
											function() {
												removeValidation();
												$('#txtCommonID').css("display",
												"none"); 
												$('#commonID').css("display",
												"none");
											
												 /*$("#commonID").html("PSA ID :");*/
												/* $('#txtCorpID').removeAttr(
														'data-validation');
												$("#corpID")
														.html(
																"CORP ID <span class='req'></span>:"); */
												
												$('#drpGlobalGrade').removeAttr(
												'data-validation');
									
												$("#globalGrade")
												.html(
														"Global Grade <span class='req'></span>:");
												
												
												$("#divVendor").css("display",
														"");
												$("#divCorpId").css("display",
														"");
												
												
												$("#divGlobalGrade").css("display",
												"");
												
											
												
												
												$('#txtEndDate').val("");
												/*$("#divPSA").css("display",
														"none");
												 $("#txtPsaId").attr(
														"data-validation",
														"number"); */
											});

							$("#btnSubmit")
									.click(
											function() {
												var offshoreemidval = $('#drpoffShoreEm').val();
												var countryVal = $("#drpCountry").val();
												if (offshoreemidval == 0 && countryVal == 2){
										
												    alert("Please select correct value for OffshoreEm");
												    return false;
												}
												    else{
												    	return true;
												    }
												 
												
												if ($("#rdIntEmpCheck").is(
														":checked") == true) {
													if ($("#txtEndDate").val() != "") {
														$("#txtEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													}
													if ($("#txtDob").val() != "") {
														$("#txtDob")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													}
												}
												if ($("#rdExtEmpCheck").is(
														":checked") == true) {
													$("#txtEndDate")
															.attr(
																	{
																		"data-validation" : "date",
																		"data-validation-format" : "dd/mm/yyyy",
																		"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																	});
													if ($("#txtDob").val() != "") {
														$("#txtDob")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
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
						var html = '<option value="">-Select-</option>';
						for(var bisEle=0; bisEle < data.length; bisEle++){
							html += '<option value="' + data[bisEle].bis_id + '">'
							+ data[bisEle].bis_Name
							+ '</option>';
						}
						$('#drpbis').html(html);
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
			enggCountry();
			
			if(pp != 10){
				$("#sdmDiv").css("display", "none");
				$("#businessAp").css("display", "none");
				$("#cfaoDiv").css("display", "none");
				
				$("#orderGid").val("");
				$("#drpBusinessAs").val("");
				$("#otherAsId").val("");
				$("#drpCfao").val("");
				
				/* $("#RoleT").css("display", "");
				$("#Tech").css("display", "");
				$("#Indus").css("display", ""); */
				
				//new  - 14 May
				if (document.getElementById('ChkPSAIdReq').checked) 
			  {
				  $("#dobdate").css("display", "");
				  $("#Tech").css("display", "");
				  $("#Indus").css("display", "");
				  $("#drppsaLibType").val("");
				  $("#RoleT").css("display", "");
				  $("#drppsaLibType").attr("disabled", false);
				  //$("#ChkHRAReq").attr("disabled", false);
				//Engg - Start
				  $("#sdmDiv").css("display", "none");
				  $("#businessAp").css("display", "none");
				  $("#cfaoDiv").css("display", "none");
			      //Engg - End
				
			  } else {
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
				  
				  //Engg - Start
				  $("#sdmDiv").css("display", "none");
				  $("#businessAp").css("display", "none");
				  $("#cfaoDiv").css("display", "none");
			      //Engg - End
			  }
				//new - 14 May
			}
			if(pp == 10){
				
				/* $("#sdmDiv").css("display", "");
				$("#businessAp").css("display", "");
				$("#cfaoDiv").css("display", "");
			
				$("#RoleT").css("display", "none");
				$("#Tech").css("display", "none");
				$("#Indus").css("display", "none"); */
				if (document.getElementById('ChkPSAIdReq').checked) 
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
		
		
		function enggCountry(){
			 if ($('#drpCountry').val() == "2" || $('#drpCountry').val() == "1" || $('#drpCountry').val() == "9") {
			 
			 if($("#drpprimprogram").val() != 10){
				$("#sjira").css("display", "");
				$("#staffingrr").css("display", "");
			}	
			 else {
				 $("#sjira").css("display", "none");
					$("#staffingrr").css("display", "none");
			 }
				
			} 

		 else {

			 if($('#drpprimprogram').val() != 10){
					$("#sjira").css("display", "");
					$("#staffingrr").css("display", "none");
				}	
			 else {
				 $("#sjira").css("display", "none");
					$("#staffingrr").css("display", "none");
			 }
			 
			}
		}
		
		
		function removeValidation() {
			//first remove all the validation
			$('.form-error').remove();
			$('.error').removeAttr('style');
			$('.error').removeClass('error');
		}
			
			var offShoreValueCheck = $("#drpCountry").val('${employee.country.countryId}');
			if(offShoreValueCheck.val()==2){
			
				$("#staffingrr").css("display", "");
				$("#divCapSup").css("display", "");
				$("#divCapSupEmail").css("display", "");
				
			}
			else if(offShoreValueCheck.val()==1 || offShoreValueCheck.val()==9){//mehens
				$("#staffingrr").css("display", "");
				$("#divCapSup").css("display", "none");
				$("#divCapSupEmail").css("display", "none");
			}
				else{
					
					$("#staffingrr").css("display", "none");
					$("#divCapSup").css("display", "none");
					$("#divCapSupEmail").css("display", "none");
			}
			
			
			
			// $("#ChkPSAIdReq").attr("data-validation","required");
			// $("#ChkPSAIdReqNo").attr("data-validation","required");
			function calc()
			{
			  if (document.getElementById('ChkPSAIdReq').checked) 
			  {
				  $("#dobdate").css("display", "");
				  $("#Tech").css("display", "");
				  $("#Indus").css("display", "");
				  $("#drppsaLibType").val("");
				  $("#RoleT").css("display", "");
				  $("#drppsaLibType").attr("disabled", false);
				  //$("#ChkHRAReq").attr("disabled", false);
				 
				  //reject
				  var pid = $('#drpprimprogram').val();
				  if (pid != 10){
					  $("#sdmDiv").css("display", "none");
					  $("#businessAp").css("display", "none");
					  $("#cfaoDiv").css("display", "none");
					  
					    $("#orderGid").val("");
						$("#drpBusinessAs").val("");
						$("#otherAsId").val("");
						$("#drpCfao").val("");
				  }//reject
				
			  } else {
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
				  
				  //Engg - Start
				  $("#sdmDiv").css("display", "none");
				  $("#businessAp").css("display", "none");
				  $("#cfaoDiv").css("display", "none");
			      //Engg - End
			  }
			  
			  //Engg - start
			  var ppid = $('#drpprimprogram').val();
			  if (ppid == 10){
				  
				    $("#RoleT").css("display", "none");
					$("#Tech").css("display", "none");
					$("#Indus").css("display", "none");
					
					if (document.getElementById('ChkPSAIdReq').checked) 
					  {
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
			}
			
			function autocomplete(inp, arr) {
				  
				  debugger;
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
					  debugger;
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
			
			
			const employeee ="${listOfEmployees}";
			
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
