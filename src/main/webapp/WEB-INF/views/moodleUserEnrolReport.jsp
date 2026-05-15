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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/datatables.min.css" />
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
				<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change
						Password</a></li>
				<li><a href="${pageContext.request.contextPath}//logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<br />
		<h2>Reporting</h2>
		<div class="formFields clearfix">
			<c:url var="searchAction" value="/moodleDataSearch"></c:url>

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

			<form:form action="${searchAction}" commandName="moodle" method="POST" id="moodleReport">
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Username : </label>
						</div>
						<div class="inputField">
							<form:input path="username" id="username" class="input-control" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Resource Status : </label>
						</div>
						<div class="inputField">
							<input type="radio" name="resStatus" id="resStatusAll" value="all" checked="checked" /> All Resources &nbsp;&nbsp;
							<input type="radio" name="resStatus" id="resStatusActive" value="active" /> Active Resources
						</div>
					</div>
				</div>
				
				<div class="text-center">
					<input type="submit" value="Search" title="Search" id="btnSubmit" class="btn-primary" /> 
					<input type="Reset" value="Reset" title="Reset" id="btnReset" class="btn-primary" />
					<button type="button" id="downloadMoodleReport" class="btn btn-primary">Download</button>
				</div>
			</form:form>
		</div>

		<c:if test="${!empty moodleSearchList}">
			<h3 class="empLstsHD">${DynamicHeadingValue}</h3>
			<!-- <input type="button" id="btnExport" value="Excel Export"
				title="Excel Export" class="btn-primary" /> -->
			&nbsp;
			<br />
			<br />

			<table id="summaryTbl" class="summary-view-table dt-responsive">
				<thead>
					<tr>		
					    <th width="80">User Name</th>
						<th width="80">First Name</th>
						<th width="80">Last Name</th>
						<th width="80">Course Name</th>
						<th width="150">Onboarding Date</th>
						<th width="150">Actual Offboarding Date</th>
						<th width="80">User Enrolment Date</th>
						<th width="80">Course Completion Date</th>
						<th width="150">Status</th>
						<th width="150">Bundle</th>
						<th width="150">Manager</th>
						<th width="150">EM</th>
						<th width="150">Bundle EM</th>
						<th width="150">Primary Program</th>
						
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${moodleSearchList}" var="moodle">

						<tr>	
        				    <td>${moodle.username}</td>
							<td>${moodle.firstName}</td>
							<td>${moodle.lastname}</td>
							<td>${moodle.coursename}</td>
							<td>${moodle.onboardingDate}</td>
							<td>${moodle.offboardingDate}</td>
							<td>${moodle.enrolDateString}</td>
							<td>${moodle.completionDateString}</td>
							<td>${moodle.status}</td>
							<td>${moodle.bis}</td>
							<td>${moodle.manager}</td>
							<td>${moodle.EM}</td>
							<td>${moodle.BM}</td>
							<td>${moodle.primaryProgram}</td>
							
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/tableExport.js"></script>
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
								/* "paging" : true, */
								"pagingType": "full_numbers",
								"dom": "lBfrtip",
						        "buttons": ['excel']
					        
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
												if ($("#txtActEndDate").val() != "") {
													$("#txtActEndDate")
															.attr(
																	{
																		"data-validation" : "date",
																		"data-validation-format" : "dd/mm/yyyy",
																		"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																	});
												}
											});

							$.validate({
								form : '#moodleReport',
								modules : 'date'
							});
							var btn = document.getElementById("downloadMoodleReport");
							
							$("#downloadMoodleReport").click(function(){	
								$("#downloadMoodleReport").attr("disabled", true);
								btn.innerHTML = 'Downloading';
								
								$.ajax({
							 		type : "GET",
							 		contentType : "application/json",
							 		url : "/onboarding/MoodleReportExcelExport",
							 		dataType : 'text',
						 		data: { username : $("#username").val(), resStatus : $("input[name='resStatus']:checked").val() },
							 		success : function(response) {	
							 			
							 			console.log(response);
						  	 		 window.location.href = '/onboarding/downloadReportFile/' + response; 
						  	 		$("#downloadMoodleReport").attr("disabled", false);
								 	btn.innerHTML = 'Download';
							 		}
							 });	<%--end of AJAX call--%>
							
					});
							
						});
	</script>
</body>
</html>
