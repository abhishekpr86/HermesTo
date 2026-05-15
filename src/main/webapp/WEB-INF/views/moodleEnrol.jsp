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
						<!-- <li><a href="${pageContext.request.contextPath}/userSearch" title="userSearch">Edit User</a></li> -->
						<li><a href="${pageContext.request.contextPath}/addRole" title="userSearch">Add Role</a></li>
					</ul></li>
			</c:if>
			<c:if test="${checkUserType eq 'BundleEM'}">
				<%-- <li><a href="${pageContext.request.contextPath}/addPreOnboardRec" title="Resource Pre-Onboarding">Resource Pre-Onboarding</a></li> --%>
				<li><a href="${pageContext.request.contextPath}/addPreOnboardRec" title="Resource Pre-Onboarding">Onboarding Request</a></li>
			</c:if>
			<c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM') or (checkUserTypeforUM ne null) or (checkUserType eq 'ReadOnlyUsers')}">
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
				
				<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change
						Password</a></li>
				<li><a href="${pageContext.request.contextPath}//logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<br />
		<h2>Moodle CSV Download</h2>
		
		<div class="formFields clearfix">
			<c:url var="searchAction" value="/moodleUserEnrol/download"></c:url>
			<c:url var="createfile" value="/createFile" />
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
			
			<div id="moodleCriteria" style="position: relative; text-align: center; font-size: 17px; top: 10px;">
				<div class="col50per">
				<p style="color:red"><spring:message code="label.moodle.download"/></p>
                <!-- <div class="fieldLbl"><label>Start From Date : </label></div>
                <div class="inputField">
					<input type="text" name="Start From Date " id="datepicker1" class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" > 
								<span>&nbsp;</span><span>&nbsp;</span>
				</div> -->
				</div>
				<!-- <div class="col50per">
                <div class="fieldLbl"><label>Upto Date: </label></div>
                <div class="inputField">
                	<input type="text" name="Upto Date" id="datepicker2" class="input-control" data-validation="date"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/yyyy format" >
                </div>
				</div> -->
                <!-- Button who generate the file -->
				<div class="text-center">
					
	                <button type="submit" id="downloadCohort" class="btn btn-primary">Download</button>
	                <!-- return confirm('Datos Recuperados'); -->
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
		<script type="text/javascript">
	
		$(document)
				.ready(
						function() {
							
					$("#downloadCohort").click(function(){	
							
								$.ajax({
							 		type : "GET",
							 		contentType : "application/json",
							 		url : "/onboarding/createFile",
							 		dataType : 'text',
							 		success : function(response) {	
							 			
							 			console.log(response);
						  	 		 window.location.href = 'downloadFile/' + response; 
							 		}
							 	});	<%--end of AJAX call--%>
							
					});
						
					$("#downloadCohort1").click(function(){
						
							if($('#datepicker1').val() == null){
								var startDate = undefined;
							}else{
								var startDate = $('#datepicker1').val();
							}
							if($('#datepicker2').val() == null){
								var endDate = undefined;
							}else{
								var endDate = $('#datepicker2').val();
							}
							
							$.getJSON('${createfile}',
									{
										
										startDate : startDate,
										endDate : null,
										ajax : 'true'
									},
									function(response){
										
								 			console.log(response);
							  	 		 window.location.href = 'downloadFile/' + response; 
								 		
										
									});
						
					});
						//document.getElementById('downloadCohort').addEventListener('click', download1, true);
							
		});
		
		</script>
		
</body>
</html>