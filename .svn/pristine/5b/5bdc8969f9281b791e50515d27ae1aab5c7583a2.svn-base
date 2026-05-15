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
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<br />
		<h2>Off-Boarding Report (Resources with Off-Boarding date within the next 6 weeks)</h2>
		<div class="formFields clearfix">
			<c:url var="searchAction" value="/reportoffboardingSearch"></c:url>

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

			<form:form action="${searchAction}" commandName="employee"
				method="POST" id="offBoardingReport">
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Country : </label>
						</div>
						<div class="inputField">
							<form:select path="country" id="drpCountry"
								class="select-control">
								<option value="">-Select-</option>
								<c:forEach items="${listCountry}" var="cntry">
									<form:option value="${cntry.countryId}">${cntry.countryName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>

					<div class="col50per">
						<input type="submit" value="Search" title="Search" id="btnSubmit"
							class="btn-primary" /> <input type="Reset" value="Reset"
							title="Reset" id="btnReset" class="btn-primary" />
					</div>

				</div>
			</form:form>
		</div>



		<c:if test="${!empty offBoardReportList}">
			<h3 class="empLstsHD">${DynamicHeadingValue}</h3>
			<!-- <input type="button" id="btnExport" value="Excel Export"
				title="Excel Export" class="btn-primary" /> -->
			&nbsp;
			<br />
			<br />

			<table id="summaryTblReport" class="summary-view-table dt-responsive">
				<thead>
					<tr>
					    <th width="60">Off-board</th>
					    <th width="80">Primary Program</th>
						<th width="80">Emp ID</th>
						<th width="80">Corp ID</th>
						<th width="80">PSA ID</th>
						<th width="80">Emp Type</th>
						<th width="150">Emp First Name</th>
						<th width="150">Emp Last Name</th>
						<th width="50">Country</th>
						<th width="50">Location</th>
						<th width="10">Email</th>
						<th width="10">Type</th>
						<th width="50">Capgemini Entity</th>
						<th width="50">Primary Skill </th> 
						<!-- <th width="50">Category</th> -->
						<th width="50">Vendor</th> 
						<th width="10">Grade</th>
						<th width="50">Global Grade</th>
						<th width="50">Manager</th>
						<!-- <th width="50">HRA Request</th> -->
						<th width="50">Staffing SO</th>
						<th width="100">On-boarding Date(YYYY-MM-DD)</th>
						<th width="100">Planned Off-boarding Date(YYYY-MM-DD)</th>
						<th width="100">Actual Off-boarding Date(YYYY-MM-DD)</th>
						<th width="60">Training Status</th>
						<th width="60">Bundle EM</th>
						<th width="60">EM</th>
						<th width="50">Offshore EM</th>
						<th width="50">BIS</th>
						<th width="50">Resource Manager</th>
						<th width="50">Replacement Required</th>
						<th width="50">Reason for Roll Off</th>
						<th width="50">Reason for No Replacement</th>
						<th width="50">Replacement Corp ID</th>
	     	        	<th width="50">Replacement Employee Name</th>
	     	        	<th width="50">RR No</th>
	     	        	<th width="50">Comment</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${offBoardReportList}" var="employee">

						<tr>
						    <td><a href="<c:url value='/offboardEmployee/${employee.id}' />"
								title="Off-board" class="offboardIcn"></a></td>
	                        <td>${employee.primaryprogram.primaryProgramName}</td>
							<td>${employee.empId}</td>
							<td>${employee.corpId}</td>
							<td>${employee.psaId}</td>
							<td>${employee.empType}</td>
							<td>${employee.firstName}</td>
							<td>${employee.lastName}</td>
							<td>${employee.country.countryName}</td>
							<td>${employee.location.stateName}</td>
							<td>${employee.email}</td>
							<td>${employee.heritageValue}</td>
							<td>${employee.cgEntity}</td>
							<td>${employee.primaryTechnology.technologyName}</td>
							<td>${employee.vendor.vendorName}</td>
							<td>${employee.grade.name}</td>
							<td>${employee.globalGrade.name}</td>
							<td>${employee.manager.firstName}&nbsp;${employee.manager.lastName}</td>
							<%-- <td>${employee.HRAReq}</td> --%>
							<td>${employee.staffingRR}</td>
							<td>${employee.onboardingDate}</td>
							<td>${employee.offboardingDate}</td>
							<td>${employee.actualOffboardingDate}</td>
							<td>${employee.isTrainingCompleted}</td>
							<td>${employee.bundleEM.bundleEmName}</td> 							
						    <td>${employee.EM.firstName}&nbsp;${employee.EM.lastName}</td> 
						    <td>${employee.offshoreEm.offshoreEmName}</td>
						    <td>${employee.bis.bis_Name}</td>
						    <td>${employee.resourceManager.resourceMgrName}</td>
						    <c:choose>
	    						<c:when test="${employee.replacementRequired}">
									<td>Yes</td>
								</c:when> 
								<c:otherwise>
									<td>No</td>
								</c:otherwise>
							</c:choose>
						    <td>${employee.rollOffType.type}</td>
						    <td>${employee.replacementType.type}</td>
						    <td>${employee.repCorpId}</td>
					    	<td>${employee.repfirstName}</td>
					    	<td>${employee.rr_no}</td>
					    	<td>${employee.offboardcomment}</td>
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
		src="${pageContext.request.contextPath}/resources/js/tableExportOffBoardingReport.js"></script>
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
	/* 	$(document).ready(function() {
			$('#summaryTblReport').DataTable({
				"scrollY" : "400px",
				"scrollX" : true,
				"scrollCollapse" : true,
				"paging" : false,
				"dom": "Bfrtip",
		        "buttons": [
		        	 {
		                 extend: 'excelHtml5',
		                 exportOptions: {
		                	 columns: [ 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28]
		                 }
		             },
		        ],
		        "ignoreColumn": [15]
			}); */
			
			
			$(document).ready(
					
					function() {				
						
						$('#summaryTblReport').DataTable({
							"scrollY": "400px",
							"scrollX": true,
					        "scrollCollapse": true,
					        "paging": false,
					        "responsive": false,
					        "autoWidth": true ,
					        "dom": "Bfrtip",
					        "buttons": [
					   	        	 {
					   	                 extend: 'excelHtml5',
					   	                 exportOptions: {
					   	                	 columns: [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 
					   	                	           27,28,29,30,31,32,33,34]
					   	                 }
					   	             },
					   	        ],
						});
					
			
			
			
			/*  $("#btnExport").click(function() {
				$('#summaryTblReport').tableExport({
					type : 'excel',
					escape : 'false',
					ignoreColumn: [15]
				});
			}); */ 
		});
		</script>
		</body>
		</html>
	