<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h1 class="pull-left">Onboarding Tool</h1>
		</div>
		<nav class="pull-right">
			<ul class="nav-new">
				<li class="active"><a href="javascript:void(0);"
					title="DashBoard">DashBoard</a></li>
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
	
		<c:url var="getEmployeeData" value="/getEmployeeData"></c:url>
	
		<br />
		<h2>Dashboard</h2>
		<div class="top_div">

			<div class="resource-type">
				<form:form modelAttribute="dashboard" method="POST" id="checkemployee">
					<div class="fieldLbl">
						<label>Resource Type : </label>
					</div>
					<form:radiobutton path="empType" id="rdAllEmp" value="All"
						name="rdoGroup" class="input-control" />
					<label>All Resources</label>
					<form:radiobutton path="empType" value="Active" id="rdActiveEmp"
						name="rdoGroup" class="input-control" checked="checked" />
					<label>Active Resources</label>
					<form:radiobutton path="empType" value="Billable" id="rdBillableEmp"
						name="rdoGroup" class="input-control" />
					<label>Billable Resources</label>
				</form:form>
			</div>

			<div id="divAll" class="clearfix">
				<%-- <div class="pull-left graph-wrapper" id="divGraphAll">
					<img
						src="${pageContext.request.contextPath}/resources/images/totalResource.png"
						alt=""> <img
						src="${pageContext.request.contextPath}/resources/images/internalResource.png"
						alt=""> <img
						src="${pageContext.request.contextPath}/resources/images/externalResource.png"
						alt="">
				</div> --%>
				<!-- <div class="pull-right" id="divGridAll"> -->
				<div class="pull-left" id="divGridBillable">
					<h2 class="dashboardListHD">All Resources Count</h2>
					<c:if test="${!empty dashboardList}">
			&nbsp;
			<br />
						<table id="dashboardTbl1"
							class="summary-dashBoard-view-table dt-responsive">
							<thead>
								<tr>
									<th width="50">Country</th>
									<th width="50">Internal</th>
									<th width="50">External</th>
									<th>Total</th>
								</tr>
							<thead>
							<tbody>
								<c:forEach items="${dashboardList}" var="dashboard">
									<tr>
										<td>${dashboard.countryNameDashBoard}</td>
										<td>${dashboard.totalInternal}</td>
										<td>${dashboard.totalExtrnal}</td>
										<td>${dashboard.totalEmployee}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>

			</div>

			<div id="divActive" class="clearfix">
				<%-- <div class="pull-left graph-wrapper" id="divGraphActive">
					<img
						src="${pageContext.request.contextPath}/resources/images/totalActiveResource.png"
						alt=""> <img
						src="${pageContext.request.contextPath}/resources/images/internalActiveResource.png"
						alt=""> <img
						src="${pageContext.request.contextPath}/resources/images/externalActiveResource.png"
						alt="">
				</div> --%>
				<!-- <div class="pull-right" id="divGridActive"> -->
				<div class="pull-left" id="divGridBillable">
					<h2 class="dashboardActiveListHD">Active Resources Count</h2>
					<c:if test="${!empty dashboardActiveList}">
			&nbsp;
			<br />
						<table id="dashboardTbl2"
							class="summary-dashBoard-view-table dt-responsive">
							<thead>
								<tr>
									<th width="50">Country</th>
									<th width="50">Internal</th>
									<th width="50">External</th>
									<th>Total</th>
								</tr>
							<thead>
							<tbody>
								<c:forEach items="${dashboardActiveList}" var="dashboardActive">
									<tr>
										<td>${dashboardActive.countryNameDashBoard}</td>
										<td>${dashboardActive.totalInternal}</td>
										<td>${dashboardActive.totalExtrnal}</td>
										<td>${dashboardActive.totalEmployee}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
			
		
			<div id="divBillable" class="clearfix">
				<%-- <div class="pull-left graph-wrapper" id="divGraphBillable">
					<img
						src="${pageContext.request.contextPath}/resources/images/totalBillableResource.png"
						alt=""> <img
						src="${pageContext.request.contextPath}/resources/images/internalBillableResource.png"
						alt=""> <img
						src="${pageContext.request.contextPath}/resources/images/externalBillableResource.png"
						alt="">
				</div> --%>
				<!-- <div class="pull-right" id="divGridBillable"> -->
				<div class="pull-left" id="divGridAll">
					<h2 class="dashboardBillableListHD">Billable Resources Count</h2>
					<c:if test="${!empty dashboardBillableList}">
			&nbsp;
			<br />
						<table id="dashboardTbl3"
							class="summary-dashBoard-view-table dt-responsive">
							<thead>
								<tr>
									<th width="50">Country</th>
									<th width="50">Internal</th>
									<th width="50">External</th>
									<th>Total</th>
								</tr>
							<thead>
							<tbody>
								<c:forEach items="${dashboardBillableList}" var="dashboardBillable">
									<tr>
										<td>${dashboardBillable.countryNameDashBoard}</td>
										<td>${dashboardBillable.totalInternal}</td>
										<td>${dashboardBillable.totalExtrnal}</td>
										<td>${dashboardBillable.totalEmployee}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			
			
			</div>
		</div>




		<div class="bottom_div clearfix">
			<form:form modelAttribute="dashboard" method="POST" id="countryDropdown">
				<div class="formRowDashBoard clearfix">
					<div class="col100per">
						<h2>Resource Pyramid Graph (Active Internals)</h2>
					</div>

					<div class="col100per">
						<div class="fieldLbl">
							<label>Country : </label>
						</div>
						<div class="inputfield">
							<form:select path="country" id="drpcountry"
								class="select-control">
								<option value="">(ALL)</option>
								<c:forEach items="${listCountry}" var="cntry">
									<form:option value="${cntry.countryId}">${cntry.countryName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>

			</form:form>

			<div class="pull-left" id="divPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/pyramidChart.png"
					alt="">
			</div>
			<div class="pull-left" id="divGridAllCpontry">
				<c:if test="${!empty pyramidList}">
			&nbsp;
			<br />
					<table id="dashboardAllcountry" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>


			


			<div class="pull-left graph-wrapper" id="divFrancePyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/FrancePyramid.png"
					alt="">
			</div>
			<div class="pull-left graph-wrapper" id="divIndiaPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/IndiaPyramid.png"
					alt="">
			</div>
			<div class="pull-left graph-wrapper" id="divChinaPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/ChinaPyramid.png"
					alt="">
			</div>
			<div class="pull-left graph-wrapper" id="divSpainPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/SpainPyramid.png"
					alt="">
			</div>
			<div class="pull-left graph-wrapper" id="divMoroccoPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/MoroccoPyramid.png"
					alt="">
			</div>
			 <div class="pull-left graph-wrapper" id="divGermanyPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/GermanyPyramid.png"
					alt="">
			</div>
			<div class="pull-left graph-wrapper" id="divUKPyramid">
				<img
					src="${pageContext.request.contextPath}/resources/images/UKPyramid.png"
					alt="">
			</div>
			<div class="pull-left" id="divGridFrance">
				<c:if test="${!empty francePyramidList}">
			&nbsp;
			<br />
					<table id="dashboardFrance" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${francePyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			
			<div class="pull-left" id="divGridIndia">
				<c:if test="${!empty IndiaPyramidList}">
			&nbsp;
			<br />
					<table id="dashboardIndia" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${IndiaPyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>

			<div class="pull-left" id="divGridChina">
				<c:if test="${!empty ChinaPyramidList}">
			&nbsp;
			<br />
					<table id="dashboardChina" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ChinaPyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>

			<div class="pull-left" id="divGridSpain">
				<c:if test="${!empty SpainPyramidList}">
			&nbsp;
			<br />
					<table id="dashboardSpain" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${SpainPyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			
			

			<div class="pull-left" id="divGridMorocco">
				<c:if test="${!empty MoroccoPyramidList}">
			&nbsp;
			<br />
					<table id="dashboardMorocco" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${MoroccoPyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>

			<div class="pull-left" id="divGridGermany">
				<c:if test="${!empty GermanyPyramidList}">
			&nbsp;
			<br />
					<table id="dashboardGermany" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${GermanyPyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			
			<div class="pull-left" id="divGridUK">
				<c:if test="${!empty UKPyramidList}">
			&nbsp;
			<br />
					<table id="dashboardUK" class="summary-pyramid-view-table">
						<thead>
							<tr>
								<th width="50">Global Grade</th>
								<th width="50">No Of Resources</th>
								<th width="50">Percentage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${UKPyramidList}" var="pyramidList">
								<tr>
									<td>${pyramidList.globalGradePyramid}</td>
									<td>${pyramidList.resourceCountGradeWise}</td>
									<td>${pyramidList.percentage}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			
		</div>
		<!-- </div> -->
		<!-- start -->
		
		<div class="bottom_div clearfix">
			<form:form modelAttribute="dashboard" method="POST" id="bisDropdown">
				<div class="formRowDashBoard clearfix">
					<div class="col100per">
						<h2 class="dashboardListHD">BIS Data (Active Resources)</h2>
					</div>

					<div class="col100per">
						<div class="fieldLbl">
							<label>BIS : </label>
						</div>
						<div class="inputfield">
							<form:select path="bis" id="drpbis"
								class="select-control">
								<option value="0">(ALL)</option>
								<c:forEach items="${fullListOfBis}" var="biss">
									<form:option value="${biss.bis_id}">${biss.bis_Name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>

			</form:form>
		 </div>
		
  <div class="pull-left">
  &nbsp;
  <br />
  <table id="employeeBIS" class="summary-dashBoard-view-table dt-responsive">
    <thead>
      <tr>
        <th width="50">Country</th>
        <th width="50">Internal</th>
        <th width="50">External</th>
        <th>Total </th>
      </tr>
    </thead>
    <tbody>
      <!-- Populate table with data from populateEmployeeTable() -->
    </tbody>
  </table> 
  </br></br>
  </div>
		<!-- end -->
	</section>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/datatables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#divAll").css("display", "none");
			$("#divBillable").css("display", "none");
			$("#divPyramid").css("display", "");
			$("#divGridAllCpontry").css("display", "");
			$("#divFrancePyramid").css("display", "none");
			$("#divIndiaPyramid").css("display", "none");
			$("#divChinaPyramid").css("display", "none");
			$("#divSpainPyramid").css("display", "none");
			$("#divMoroccoPyramid").css("display", "none");
			$("#divGermanyPyramid").css("display", "none");
			$("#divUKPyramid").css("display", "none");
			$("#divGridFrance").css("display", "none");
			$("#divGridIndia").css("display", "none");
			$("#divGridChina").css("display", "none");
			$("#divGridSpain").css("display", "none");
			$("#divGridMorocco").css("display", "none");
			$("#divGridGermany").css("display", "none");
			$("#divGridUK").css("display", "none");
			
			$('#dashboardTbl1').DataTable({
				"scrollY" : "400px",
				"scrollX" : true,
				"scrollCollapse" : true,
				"paging" : false,
				"responsive" : false,
				"bFilter" : false,
				"ordering" : false,
				"aoColumnDefs" : [ {
					"bSortable" : false,
					"aTargets" : [ 0, 1, 2, 3 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0, 1, 2, 3 ]
				} ]
			});
			
			
			$('#employeeBIS').DataTable({
				"scrollY" : "400px",
				"scrollX" : true,
				"scrollCollapse" : true,
				"paging" : false,
				"destroy" : true,
				"responsive" : false,
				"bFilter" : false,
				"ordering" : false,
				"info": false, // hide the "Showing x to y of z entries" message
				"aoColumnDefs" : [ {
					"bSortable" : false,
					"aTargets" : [ 0, 1, 2 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0, 1, 2 ]
				} ]
			});
			

			$('#dashboardTbl2').DataTable({
				"scrollY" : "400px",
				"scrollX" : true,
				"scrollCollapse" : true,
				"paging" : false,
				"responsive" : false,
				"bFilter" : false,
				"ordering" : false,
				"aoColumnDefs" : [ {
					"bSortable" : false,
					"aTargets" : [ 0, 1, 2, 3 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0, 1, 2, 3 ]
				} ]
			});
			
			$('#dashboardTbl3').DataTable({
				"scrollY" : "400px",
				"scrollX" : true,
				"scrollCollapse" : true,
				"paging" : false,
				"responsive" : false,
				"bFilter" : false,
				"ordering" : false,
				"aoColumnDefs" : [ {
					"bSortable" : false,
					"aTargets" : [ 0, 1, 2, 3 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0, 1, 2, 3 ]
				} ]
			});

			$('#dashboardAllcountry').DataTable({
				"scrollY" : "400px",
				"scrollX" : true,
				"scrollCollapse" : true,
				"paging" : false,
				"destroy" : true,
				"responsive" : false,
				"bFilter" : false,
				"ordering" : false,
				"aoColumnDefs" : [ {
					"bSortable" : false,
					"aTargets" : [ 0, 1, 2 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0, 1, 2 ]
				} ]
			});

			$("#rdActiveEmp").click(function() {
				$("#divAll").css("display", "none");
				$("#divActive").css("display", "");
				$("#divBillable").css("display", "none");
			});

			$("#rdAllEmp").click(function() {
				$("#divAll").css("display", "");
				$("#divActive").css("display", "none");
				$("#divBillable").css("display", "none");
			});
			
			$("#rdBillableEmp").click(function() {
				$("#divAll").css("display", "none");
				$("#divActive").css("display", "none");
				$("#divBillable").css("display", "");
			}); 
			

			$('#countryDropdown').change(function() {

				var countryId = $("#countryDropdown option:selected").text();
				if (countryId == "(ALL)") {
					$("#divPyramid").css("display", "");
					$("#divGridAllCpontry").css("display", "");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "none");
					$("#divChinaPyramid").css("display", "none");
					$("#divSpainPyramid").css("display", "none");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "none");
					

				} else if (countryId == "France") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "");
					$("#divGridFrance").css("display", "");
					$("#divIndiaPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divChinaPyramid").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divSpainPyramid").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "none");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "none");

					$('#dashboardFrance').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});
				}

				else if (countryId == "India") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "");
					$("#divGridIndia").css("display", "");
					$("#divChinaPyramid").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divSpainPyramid").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "none");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "none");

					$('#dashboardIndia').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});

				} else if (countryId == "China") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divChinaPyramid").css("display", "");
					$("#divGridChina").css("display", "");
					$("#divSpainPyramid").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "none");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "none");

					$('#dashboardChina').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"autoWidth" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});

				} else if (countryId == "Spain") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divChinaPyramid").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divSpainPyramid").css("display", "");
					$("#divGridSpain").css("display", "");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "none");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "none");
					$('#dashboardSpain').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});

				} else if (countryId == "Morocco") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divChinaPyramid").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divSpainPyramid").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divMoroccoPyramid").css("display", "");
					$("#divGridMorocco").css("display", "");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "none");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "none");
					$('#dashboardMorocco').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});
				} else if(countryId == "Germany") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divChinaPyramid").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divSpainPyramid").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGermanyPyramid").css("display", "");
					$("#divUKPyramid").css("display", "none");
					$("#divGridGermany").css("display", "");
					$("#divGridUK").css("display", "none");
					$('#dashboardGermany').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});
				}else if(countryId == "UK") {
					$("#divPyramid").css("display", "none");
					$("#divGridAllCpontry").css("display", "none");
					$("#divFrancePyramid").css("display", "none");
					$("#divGridFrance").css("display", "none");
					$("#divIndiaPyramid").css("display", "none");
					$("#divGridIndia").css("display", "none");
					$("#divChinaPyramid").css("display", "none");
					$("#divGridChina").css("display", "none");
					$("#divSpainPyramid").css("display", "none");
					$("#divGridSpain").css("display", "none");
					$("#divMoroccoPyramid").css("display", "none");
					$("#divGridMorocco").css("display", "none");
					$("#divGermanyPyramid").css("display", "none");
					$("#divUKPyramid").css("display", "");
					$("#divGridGermany").css("display", "none");
					$("#divGridUK").css("display", "");
					$('#dashboardUK').DataTable({
						"scrollY" : "400px",
						"scrollX" : true,
						"scrollCollapse" : true,
						"paging" : false,
						"destroy" : true,
						"responsive" : false,
						"bFilter" : false,
						"ordering" : false,
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0, 1, 2 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0, 1, 2 ]
						} ]
					});
				}
			});
			
			populateEmployeeTable(0);
				
		});
		
	 	
		// Function to populate employee table with query results for selected BIS
	function populateEmployeeTable(bisId) {
		  // Clear existing table rows
		  $('#employeeBIS tbody').empty();
		  
		  // Make AJAX call to server with query URL
				  $.getJSON('${getEmployeeData}',
							{
								    drpId : bisId,
									ajax : 'true'
								},
				  function(data) {

		    // Loop through query results and append rows to table
		    $.each(data, function(index, result) {
		      var country = result.countryNameDashBoard;
		      var internalCount = result.totalInternal;
		      var externalCount = result.totalExtrnal;
		      var totalCount = result.totalEmployee;
		      // Append row to table body
		      $('#employeeBIS tbody').append('<tr>' +
		        '<td>' + country + '</td>' +
		        '<td>' + internalCount + '</td>' +
		        '<td>' + externalCount + '</td>' +
		        '<td>' + totalCount + '</td>' +
		      '</tr>');
		    });
		    
		    // Replace last row's country cell with "Total" if using ROLLUP
		    if (data.length > 1) {
		      var lastRow = $('#employeeBIS tbody tr:last');
		      var lastCountryCell = lastRow.find('td:first');
		      lastCountryCell.text('Total');
		    }
		  });
		}

		// Bind change event to dropdown and populate table with selected BIS
		$('#drpbis').change(function() {
		  var bisId = $(this).val();
		  
		  if (bisId == 0){
			  populateEmployeeTable(0);  
		  }
		  else {
			  $('#employeeBIS tbody').empty();
			  
			  // Make AJAX call to server with query URL
					  $.getJSON('${getEmployeeData}',
								{
									    drpId : $(this).val(),
										ajax : 'true'
									},
					  function(data) {

			    // Loop through query results and append rows to table
			    $.each(data, function(index, result) {
			      var country = result.countryNameDashBoard;
			      var internalCount = result.totalInternal;
			      var externalCount = result.totalExtrnal;
			      var totalCount = result.totalEmployee;
			      // Append row to table body
			      $('#employeeBIS tbody').append('<tr>' +
			        '<td>' + country + '</td>' +
			        '<td>' + internalCount + '</td>' +
			        '<td>' + externalCount + '</td>' +
			        '<td>' + totalCount + '</td>' +
			      '</tr>');
			    });
			    
			    // Replace last row's country cell with "Total" if using ROLLUP
			    if (data.length > 1) {
			      var lastRow = $('#employeeBIS tbody tr:last');
			      var lastCountryCell = lastRow.find('td:first');
			      lastCountryCell.text('Total');
			    }
			  }); 
		  }
		 
		  
		});
		
		
	</script>
</body>