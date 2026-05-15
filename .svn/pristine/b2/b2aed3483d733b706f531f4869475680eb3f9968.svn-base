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
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />
</head>
<body>

	<header class="main-header">
		<div class="pull-left">
			<h1 class="pull-left">Resource On-boarding Tool</h1>
		</div>

		<nav class="pull-right">
			<ul class="nav-new">


				<li><a href="${pageContext.request.contextPath}/dashboard" title="DashBoard">DashBoard</a></li>

                <c:if test="${checkUserTypeforUM ne null}">
					
					<li class="reports"><a title="User Management">User Management</a>
					<ul class="reportsSubMenu">
						 <li><a href="${pageContext.request.contextPath}/userManagement" title="userManagement">Add User</a></li>
						<li><a href="${pageContext.request.contextPath}/userSearch" title="userSearch">Edit User</a></li>
					</ul></li>
					</c:if>


				<c:if test="${checkUserType ne null}">
					<li><a href="${pageContext.request.contextPath}/allResourceList"
						title="View / Edit Resource">View Resources</a></li>
				</c:if>

				<c:if test="${checkUserType eq null}">
					<li><a href="${pageContext.request.contextPath}/allResourceList"
						title="View / Edit Resource">View / Edit Resource</a></li>
				</c:if>
				<li class="active"><a href="javascript:void(0);"
					title="Resource On-boarding">Resource On-boarding</a></li>
				<li><a href="${pageContext.request.contextPath}/employeeSearch"
					title="Resource Off-boarding">Resource Off-boarding</a></li>
					
					
					
					
				<li class="reports"><a title="Reports">Reports</a>
					<ul style="width:250px" class="reportsSubMenu">
						<li><a href="${pageContext.request.contextPath}/report" title="Report">Resource
								Report</a></li>
						
						<li><a href="${pageContext.request.contextPath}/dataInconsistencies" 
							title="dataInconsistencies">Data Inconsistencies Report</a></li>
						<li><a href="${pageContext.request.contextPath}/orphanMgr" title="Orphan Report">Orphan Report</a></li>
						
						
					</ul></li>
					
				<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change
						Password</a></li>
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>

			</ul>
		</nav>

	</header>
	<section class="main-content">
		<br />
		<h2>On-board Resource</h2>
		<div class="formFields clearfix">
			<div class="reqText">* indicates required fields</div>
			<c:url var="addAction" value="/employee/add"></c:url>
			<c:url var="findCountryStateURL" value="/states" />
			<c:url var="findBIS" value="/bis" />
			<c:url var="checkEmployeeExists" value="/checkEmployeeExists" />
			<c:url var="checkCorpIdExists" value="/checkCorpIdExists" />
			<c:url var="checkPsaIdExists" value="/checkPsaIdExists" />
			<c:url var="checkEmployeeEmailExists" value="/checkEmployeeEmailExists" />
			<c:url var="getDetailsByCorpIDFromActiveDirectory" value="/getDetailsByCorpIDFromActiveDirectory" />
			
			
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

			<form:form action="${addAction}" commandName="employee" method="POST"
				id="addemployee">
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label> Resource Type : </label>
						</div>
						<form:radiobutton path="empType" id="rdIntEmpCheck"
							value="Internal" name="rdoGroup" class="input-control"
							checked="checked" />
						<label>Internal</label>
						<form:radiobutton path="empType" value="External"
							id="rdExtEmpCheck" name="rdoGroup" class="input-control" />
						<label>External</label>
					</div>
					<div class="col50per" id="divPSA" style="display: block;">
						<div class="fieldLbl">
							<label>PSA ID : </label>
						</div>
						<div class="inputField">
							<form:input path="psaId" size="8" id="txtPsaId"
								class="input-control" />
						</div>
					</div>
				</div>
				
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
				</div>
				
				
				<div class="formRow">
					<div class="col50per">


						<%-- <div id="empID">
						<div class="fieldLbl">
							<label>Emp ID <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="empId" size="8" id="txtEmpID"
								class="input-control" data-validation="number" />
							<form:hidden path="empId" />
						</div>
						</div> --%>
						<div>
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<div class="fieldLbl">
								<label id="commonID">Emp ID :<!-- <span class='req'>*</span>: -->
								</label>
							</div>
							<div class="inputField">
								<form:input path="empId" size="8" id="txtCommonID"
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
							<form:input path="corpId" id="txtCorpID" class="input-control"
								data-validation="required" />
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
							<form:input path="firstName" id="txtFirstName"
								class="input-control" data-validation="required" />
						</div>
					</div>
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
				</div>
				<div class="formRow">
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
							<label>Manager/Supervisor <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="manager" id="drpSupervisor"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listEmployees}" var="mgr">
									<c:set var="listEmployees" value="${listEmployees}" />
									
									<c:if test="${!empty mgr.email}">
										<c:if test="${!empty mgr.corpId}">
											<form:option info="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}  ( ${mgr.corpId} )</form:option>
										</c:if>
										<c:if test="${empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}</form:option>
									</c:if>
									</c:if>
									
									
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Type <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="heritageType" id="drpType"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<option value="1">Capgemini</option>
								<option value="2">FR HERMES</option>
								<option value="3">Others</option>
							</form:select>
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Sex <span class="req">*</span>:
							</label>
						</div>
						<form:radiobutton path="empSex" id="rdEmpSex" value="M"
							name="rdoGroup" class="input-control" checked="checked" />
						<label>Male</label>
						<form:radiobutton path="empSex" value="F" id="rdEmpSex"
							name="rdoGroup" class="input-control" />
						<label>Female</label>
					</div>
				</div>




				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Manager Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="managerEmail" id="mgrEmail"
								class="input-readonly" data-validation="required" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Entity <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="cgEntityDetail" id="drpCgEntity"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listCgEntity}" var="cgentity">
									<form:option value="${cgentity.cgEntityId}">${cgentity.cgEntityName}</form:option>
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
							<form:select path="country" id="drpCountry"
								class="select-control" data-validation="required">
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
							</form:select>
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Local Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="grade" id="drpGrade" class="select-control"
								data-validation="required">
								<option value="">-Select-</option>
							</form:select>
						</div>
					</div>
					<div class="col50per" id="divGlobalGrade" style="display: block;">
						<div class="fieldLbl">
							<label id="globalGrade">Global Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="globalGrade" id="drpGlobalGrade"
								class="select-control" data-validation="required" >
								<option value="">-Select-</option>
								<c:forEach items="${listGlobalGrades}" var="globalGrades">
									<form:option value="${globalGrades.globalGradeId}">${globalGrades.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					
					

					
					
					
					
				</div>
				<div class="formRow">
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
						
						
						
						<%-- <div class="inputField">
							<form:select path="bundleEM" id="drpbundleEM"
								class="select-control" data-validation="required">
								<option value="" selected="true">-Select-</option>
								<c:forEach items="${listEmployeesByBundleEm}" var="mgr">
									<c:set var="listEmployeesByBundleEm"
										value="${listEmployeesByBundleEm}" />
										<c:if test="${!empty mgr.email}">
									<c:if test="${!empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}  ( ${mgr.corpId} )</form:option>
									</c:if>
									<c:if test="${empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}</form:option>
									</c:if>
									</c:if>
								</c:forEach>
							</form:select>
						</div> --%>
						
						
						
					</div>
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
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}  ( ${mgr.corpId} )</form:option>
									</c:if>
									<c:if test="${empty mgr.corpId}">
										<form:option info="${mgr.email}" value="${mgr.id}">${mgr.firstName} ${mgr.lastName}</form:option>
									</c:if>
									</c:if>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				<div class="formRow">
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
					<div class="col50per" id="divphase" style="display: none;">
						<div class="fieldLbl">
							<label>Category <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="phase" id="drpphase" class="select-control"
								data-validation="required">
								<option value="1" selected="true">AppsTwo Delivery</option>
								<option value="2">Transition</option>
								<option value="3">AppsTwo Governance</option>
                                <option value="4">Loaned-In</option>
							</form:select>
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per" id="divresourcemanager"
						style="display: none;">
						<div class="fieldLbl">
							<label>Resource Manager<span class="req"></span>:</label>
						</div>
						<div class="inputField">

							<form:select path="resourceManager" id="drpresourceManager"
								class="select-control">
								<option value="">-Select-</option>
							</form:select>
						</div>
					</div>
				</div>
				<div class="formRow">
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
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Entity : </label>
						</div>
						<div class="inputField">
							<form:select path="entity" id="drpEntity" class="select-control">
								<option value="">-Select-</option>
								<c:forEach items="${listEntity}" var="entity">
									<form:option value="${entity.entityId}">${entity.entityName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Date of Birth (dd/mm/yyyy) : </label>
						</div>
						<div class="inputField">
							<form:input path="dob" id="txtDOB" class="input-control" />
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
					<div class="col50per">
						<div class="fieldLbl">
							<label>Planned Off-boarding Date (dd/mm/yyyy) <span
								class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="endDate" value="31/05/2021" id="txtEndDate"
								class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" />
						</div>
					</div>
				</div>
				
				
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Contact No. :</label>
						</div>
						<div class="inputField">
							<form:input path="contact" id="txtContact" class="input-control" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Security Training(s) completed <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:checkbox path="securityTraining" value="false" />
							Yes
						</div>
					</div>
				</div>
				<div class="formRow">
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
							<label>Onboarding Checklist(s) completed: </label>
						</div>
						<div class="inputField">
							<form:checkbox path="onboardingChecked" value="false" />
							Yes
						</div>
					</div>
				</div>

				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Email <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="spocEmail" id="txtSpocEmail" disabled="true"
								class="input-readonly" />
						</div>
					</div>
				<div class="col50per">
						<div class="fieldLbl">
							<label>BIS :
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
				</div>
				
					
				
				
				<div class="formRow">
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Decision validated : </label>
						</div>
						<div class="inputField">
							<form:checkbox path="decisionValByGP" value="false" />
							Yes
						</div>
					</div>
				</div>
				</div>
				
				
				
		 </div>
		<div class="formRow">
			<div class="fieldLbl">
				<label>Comment :</label>
			</div>
			<div class="inputField">
				<form:textarea path="comment" maxlength="200" id="comment"
					class="textarea-control" />
			</div>
		</div>
		<div class="text-center">
			<input type="submit" value="Add" title="On-board" id="btnSubmit"
				class="btn-primary" /> <input type="Reset" value="Reset"
				title="Reset" id="btnReset" class="btn-primary" />
		</div>
		</form:form>
		</div>

		<%-- <h3 class="empLstsHD">Employee List</h3>
		<c:if test="${!empty employeeList}">
		<input type="button" id="btnExport" value="Excel Export"
				title="Excel Export" class="btn-primary" />
			&nbsp;
			<br/>
			<br/>
		<table id="summaryTbl" class="summary-view-table dt-responsive">
			<thead>
				<tr>
					<th width="80">Emp ID</th>
					<th width="80">Corp ID</th>
					<th width="80">PSA ID</th>
					<th width="80">Emp Type</th>
					<th width="150">Emp Name</th>
					<th width="50">Country</th>
					<th width="10">Email</th>
					<th width="50">Grade</th>
					<th width="50">Global Grade</th>
					<th width="50">Manager</th>
					<th width="10">On-boarding Date</th>
					<th width="10">Planned Off-boarding Date</th>
					<th width="10">Actual Off-boarding Date</th>
					\
				</tr>
			<thead>
				<tbody>
				<c:forEach items="${employeeList}" var="employee">
					<tr>
						<td>${employee.empId}</td>
						<td>${employee.corpId}</td>
						<td>${employee.psaId}</td>
						<td>${employee.empType}</td>
						<td>${employee.firstName}&nbsp; ${employee.lastName}</td>
						<td>${employee.country.countryName}</td>
						<td>${employee.email}</td>
						<td>${employee.grade.name}</td>
						<td>${employee.globalGrade.name}</td>
						<td>${employee.manager.firstName}&nbsp; ${employee.manager.lastName}</td>
						<td>${employee.onboardingDate}</td>
						<td>${employee.offboardingDate}</td>
						<td>${employee.actualOffboardingDate}</td>
						<td><a href="<c:url value='/employee/${employee.id}' />"
							title="Edit" class="editIcn"></a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if> --%>
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
	<script type="text/javascript">
		
		
		$(document)
				.ready(
						function() {

							if ($("#userReadOnly").val() == 'true') {
								$("#btnSubmit").css("display", "none");
								/* $("#btnReset").css("display","none"); */

								$('div *').prop('disabled', true);
							}
							/* $('#summaryTbl').DataTable({
								"scrollY": "400px",
								"scrollX": true,
							    "scrollCollapse": true,
							    "paging": false,
							    "responsive": false,
							    "autoWidth": true,
							});
							$("#btnExport").click(function() {
								$('#summaryTbl').tableExport({
															type : 'excel',
															escape : 'false',
															ignoreColumn: [11]
														});
													}); */
													
													
													
													
							$('#drpSupervisor')
									.change(
											function() {
												$(
														"#drpSupervisor option:selected")
														.each(
																function() {
																	var info = $(
																			this)
																			.attr(
																					"info");
																	if (info != undefined) {
																		$(
																				"#mgrEmail")
																				.val(
																						info);
																	} else {
																		$(
																				"#mgrEmail")
																				.val(
																						"");
																	}
																});
											});
							$('#txtCommonID').change(
											function() {
												$
														.getJSON(
																'${checkEmployeeExists}',
																{
																	empId : $('#txtCommonID').val(),
																	extrnalSelectn : ($("#rdExtEmpCheck").is(':checked')),
																	internalSelectn : ($("#rdIntEmpCheck").is(':checked')),
																	ajax : 'true'
																},
																function(data) {
																	
																	if (data) {
																		
																		var extrnalSelectn = ($("#rdExtEmpCheck").is(':checked'));
																		var internalSelectn = ($("#rdIntEmpCheck").is(':checked'));
																		
																		if(extrnalSelectn == true) {
																			alert("Psa Id already exists . Please try another one . ");
																			$('#txtCommonID').val("");
																		}
																		else if(internalSelectn == true) {
																		alert("Emp Id already exists . Please try another one . ");
																		$('#txtCommonID').val("");
																	}
																	}

																});
											});
							
							 $('#txtCorpID')
								.change(
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
							
							$('#txtPsaId')
							.change(
									function() {
										$
												.getJSON(
														'${checkPsaIdExists}',
														{
															psaId : $(
																	'#txtPsaId')
																	.val(),
															ajax : 'true'
														},
														function(data) {
															if (data == true) {
																alert("Psa Id already exists . Please try another one . ");
																$('#txtPsaId').val("");
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
																alert("Email Id already exists . Please try another one . ");
																$('#txtEmail').val("");
															}

														});
									});	
							
							
							
							$('#drpCountry').change(
									function() {
										if ($(this).val() == "2") {
											$("#divOffShoreEm").css("display","");
											$("#divphase").css("display", "");
											
										} else {

											
											$("#divOffShoreEm").css("display",
													"none");
											$("#divphase").css("display",
													"none");
										}

										if ($(this).val() == "1" || $(this).val() == "6") {

											$("#divresourcemanager").css(
													"display", "");

										} else {

											$("#divresourcemanager").css(
													"display", "none");

										}

									});

							//pooja 
 							/* $('#drpbundleEM').change(
 								function() {
 									alert( $(this).val());
 								}		
 								); */
							
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
																	var resmgrhtml = '<option value="">-Select-</option>';
																	var gradehtml = '<option value="">-Select-</option>';
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
							$.validate({
								form : '#addemployee',
								modules : 'date'
							});
							$("#rdIntEmpCheck")
									.click(
											function() {
												removeValidation();
												$('#txtCommonID').removeAttr(
														'data-validation');
												$('#txtCommonID').get(0)
														.setAttribute('type',
																'number');
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
												$('#txtEndDate').val(
														"31/05/2021");
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
												$('#txtCommonID').get(0)
														.setAttribute('type',
																'');
											
												$("#commonID").html("PSA ID :");
												$('#txtCorpID').removeAttr(
														'data-validation');
												$("#corpID")
														.html(
																"CORP ID <span class='req'></span>:");
												
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
												$("#divPSA").css("display",
														"none");
												$("#txtPsaId").attr(
														"data-validation",
														"number");
											})

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

		function removeValidation() {
			//first remove all the validation
			$('.form-error').remove();
			$('.error').removeAttr('style');
			$('.error').removeClass('error');
		}
	</script>
</body>
</html>
