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
<!-- <title>Resource OnBoarding Tool - OffBoarding Report</title> -->
<title> ${DynamicHeadingValue}</title>
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
		<h2>Data Inconsistencies Report</h2>
		<div class="formFields clearfix">
			<c:url var="searchAction" value="/dataInconsistencies/searchType"></c:url>
			<c:url var="dataInconsistenciesByCountry" value="/dataInconsistenciesByCountry" />
		
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
		
		
		<form:form action="${searchAction}" commandName="employee" method="POST"
				id="addemployee">
			<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Issue Type :
							</label>
						</div>
						<div class="inputField">
							<form:select path="issueType" id="drpissueType"
								class="select-control">					
								<option value="corpId" selected="true">Missing Corp ID</option>
								<option value="email">Missing Email ID</option>
								<option value="empId">Missing Employee ID</option>
								<option value="psaId">Missing PSA ID</option>
								<option value="firstName">Missing Employee First Name</option>
								<option value="lastName">Missing Employee Last Name</option>
								<option value="repCorpId">Missing Replacement Corp Id</option>
								<option value="Actual Offboarding">Missing Actual Offboarding</option>
								<option value="Local Grade">Inconsistent Local Grade</option>
								<option value="Location">Inconsistent Location</option>
								<option value="BIS PrimaryProgram">Inconsistent Primary Program-Bis</option>
								
							</form:select>
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Country :
							</label>
						</div>
						<div class="inputField">
							<form:select path="country" id="drpCountry"
								class="select-control" data-validation="required">
								<option value="" selected>-Select-</option>
								<c:forEach items="${listCountry}" var="cntry">
									<form:option value="${cntry.countryId}">${cntry.countryName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					
					</div>
		<div class="text-center">
			<input type="submit" value="Search" title="On-board" id="btnSubmit"
				class="btn-primary" />
		</div>
		</form:form>
		
		
		
	



		<c:if test="${!empty missingdata}">
			<h3 class="empLstsHD"> ${DynamicHeadingValue}</h3>
			<!-- <input type="button" id="btnExport" value="Excel Export"
				title="Excel Export" class="btn-primary" /> -->
			&nbsp;
			<br />
			<br />

			<table id="summaryTbl" class="summary-view-table dt-responsive">
				<thead>
					<tr>
					    <th width="80">View/Edit</th>
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
						<th width="50">Capgemini Entity</th>
						<th width="50">Primary Skill </th> 
						<th width="50">Vendor</th> 
						<th width="10">Grade</th>
						<th width="50">Global Grade</th>
						<th width="50">Manager</th>
						<!-- <th width="50">HRA Request</th> -->
						<th width="50">Staffing SO</th>
						<th width="100">On-boarding Date(DD-MM-YYYY)</th>
						<th width="100">Planned Off-boarding Date(DD-MM-YYYY)</th>
						<th width="100">Actual Off-boarding Date(DD-MM-YYYY)</th>
						<th width="60">Bundle EM</th>
						<th width="60">EM</th>
						<th width="50">Offshore EM</th>
				        <th width="50">BIS</th>
				        <th width="50">Replacement Required</th>
	     	        	<th width="50">Replacement Corp ID</th>
	     	        	<th width="50">Replacement Employee Name</th>
						<th width="50">Resource Manager</th>
						<th width="50">Decision validated</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${missingdata}" var="employee">

						<tr>  <td><a href="<c:url value='/employee/${employee.id}/dataInconsistencyPage' />"
							title="Edit" class="editIcn"></a></td>
					
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
							<td>${employee.bundleEM.bundleEmName}</td> 				
						    <td>${employee.EM.firstName}&nbsp;${employee.EM.lastName}</td> 
						    <td>${employee.offshoreEm.offshoreEmName}</td>
						    <td>${employee.bis.bis_Name}</td>
						    <td>${employee.replacementRequiredVal}</td>
					    	<td>${employee.repCorpId}</td>
					    	<td>${employee.repfirstName}</td>
						    <td>${employee.resourceManager.resourceMgrName}</td>
						    <td>${employee.decisionVal}</td>
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
	$('#drpissueType').change(
			function() {
				$.getJSON(
								'${dataInconsistenciesByCountry}',
									{
									countryId : $(this).val(),
											ajax : 'true'
										},
								
										function(data) {
				
											var html = '<option value="">-Select-</option>';
											countryList  = data.countryList;
											var countryLength = countryList.length;
											
											for (var i = 0; i < countryLength; i++) {
												
												html += '<option value="' + countryList[i].countryId + '">'+ countryList[i].countryName + '</option>';
												
											}
											html += '</option>';
										
											$('#drpCountry').html(html);
										});
				
		});

	
	
	
	$(document).ready(
			
			function() {				
				
				$('#summaryTbl').DataTable({
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
			   	                	 columns: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25]
			   	                 }
			   	             },
			   	        ],
				});
				
			});
	
/* 	$(document).ready(
			function() {
				
				$('#summaryTblReport').DataTable(
						{
							"scrollY" : "400px",
							"scrollX" : true,
							"scrollCollapse" : true,
							"paging" : false,
							"dom" : "Bfrtip"
							
							
						});
			
			});
	 */
		
		
		
		</script>
		</body>
		</html>
	