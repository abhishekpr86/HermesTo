<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${DynamicHeadingValue}</title>
<%-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/datatables.min.css" /> --%>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttons.dataTables.min.css" />
</head>
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
			
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM_PMO')}">
					<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Pre-Onboarding</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Onboarding Requests</a></li>
			</c:if>
			
			<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO')  or (checkUserType eq 'ASL')}">
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
				<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change Password</a></li>
				<li><a href="${pageContext.request.contextPath}//logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<br />
		<h2>Reporting</h2>
		<div class="formFields clearfix">
			<c:url var="searchAction" value="/bisRotationReport/search"></c:url>

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

			<form:form action="${searchAction}" modelAttribute="employee"
				method="POST" id="bisRotationReport">
				<div class="formRow">
					<div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">CORP ID :
							</label>
						</div>
						<div class="inputField">
							<form:input path="corpId" id="txtCorpID" class="input-control" />
						</div>
					</div>
				
					<%-- <div class="col50per">
						<div class="fieldLbl">
							<label>BIS :
							</label>
						</div>
						<div class="inputField">
							<form:select path="bis" id="drpbis" class="select-control" >
								<option value="">-Select-</option>
								<c:forEach items="${listBis}" var="bis">
									<form:option value="${bis.bis_id}">${bis.bis_Name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div> --%>
					
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Rotation Date From (dd/mm/yyyy) : </label>
						</div>
						<div class="inputField">
							<form:input path="joiningDate" id="txtJoiningDate" class="input-control" />
						</div>
					</div>
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Rotation Date To (dd/mm/yyyy) : </label>
						</div>
						<div class="inputField">
							<form:input path="actualEndDate" id="txtActEndDate" class="input-control" />
						</div>
					</div>
					
				</div>
					

				
				
				
				<div class="text-center">
					<input type="submit" value="Search" title="Search" id="btnSubmit" class="btn-primary" /> 
					<input type="Reset" value="Reset" title="Reset" id="btnReset" class="btn-primary" />
					<button type="button" id="downloadCohort" class="btn btn-primary">Download</button>
				</div>
			</form:form>
		</div>

		<c:if test="${!empty bisRotationSearchList}">
			<h3 class="empLstsHD">${DynamicHeadingValue}</h3>
			<!-- <input type="button" id="btnExport" value="Excel Export"
				title="Excel Export" class="btn-primary" /> -->
			&nbsp;
			<br />
			<br />

			<table id="summaryTbl" class="summary-view-table dt-responsive">
				<thead>
					<tr>		
					    
						<th width="80">Corp ID</th>
						<th width="80">First Name</th>
						<th width="80">Last Name</th>
						<th width="80">Country</th>
						<th width="80">Location</th>
						<th width="80">Grade</th>
						<th width="80">Primary Program</th>
						<th width="80">Role</th>
						<th width="80">Old Bundle</th>
						<th width="80">New Bundle</th>
						<th width="80">Rotation Date</th>
						
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bisRotationSearchList}" var="employee">

						<tr>	
        				    
							<td>${employee.corpId}</td>
							<td>${employee.firstName}</td>  
							<td>${employee.lastName}</td> 
							<td>${employee.country.countryName}</td>
							<td>${employee.location.stateName}</td>
							<td>${employee.grade.name}</td> 
							<td>${employee.primaryProgram.primaryProgramName}</td>
							<td>${employee.role.ref_name}</td>  
							<td>${employee.prevBundle.bis_Name}</td>
							<td>${employee.newBundle.bis_Name}</td>
							<td class="tableexport-date">${employee.rotationDate}</td>  
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</section>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/datatables.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.form-validator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/date.js"></script>
	<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/tableExport.js"></script> --%>
		
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/TableExport/4.0.3/js/tableexport.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.base64.js"></script>
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/dataTables.buttons.min.js"></script>			
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/buttons.flash.min.js"></script>			
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/buttons.html5.min.js"></script>
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jszip.min.js"></script>
			
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#summaryTbl').DataTable({
								"scrollY" : "400px",
								"scrollX" : true,
								"scrollCollapse" : true,
								"paging" : false,
								"autoWidth" :true,
								"dom": "lBfrtip",
						        "buttons": [
						             'excel'
						        ]
							});
							
							$("#downloadCohort").click(function(){	
								
								$.ajax({
							 		type : "GET",
							 		contentType : "application/json",
							 		url : "/onboarding/bisRotationExcelExport",
							 		dataType : 'text',
							 		data: { fromdate : $("#txtJoiningDate").val(), todate : $("#txtActEndDate").val(), corpid : $("#txtCorpID").val()},
							 		success : function(response) {	
							 			
							 			console.log(response);
						  	 		 window.location.href = '/onboarding/downloadReportFile/' + response; 
							 		}
							 	});	<%--end of AJAX call--%>
							
					});
						
							$("#btnSubmit")
									.click(
											function() {
												if ($("#txtJoiningDate").val() != "") {
													$("#txtJoiningDate")
															.attr(
																	{
																		"data-validation" : "date",
																		"data-validation-format" : "dd/mm/yyyy",
																		"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																	});
												}
												
											});

							$.validate({
								form : '#bisRotationReport',
								modules : 'date'
							});
						});
	</script>
</body>
</html>
