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
<title>Resource OnBoarding Tool - Resource List </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/datatables.min.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/theme.css" />
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
			
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM_PMO')}">
				<!-- <li class="active"><a href="javascript:void(0);" title="View Pre-Onboarding">View Pre-Onboarding</a></li> -->
				<li class="active"><a href="javascript:void(0);" title="View Pre-Onboarding">View Onboarding Requests</a></li>
			</c:if>
			
			<c:if test="${(checkUserType ne 'ViewMode') and (checkUserType ne 'RM_PMO')}">
				<!-- <li class="active"><a href="javascript:void(0);" title="View/Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li> -->
				<li class="active"><a href="javascript:void(0);" title="View/Edit Pre-Onboarding">View/Edit Onboarding Requests</a></li>
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
			<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change Password</a></li>
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
	
	<br/>
		<h2>Search Resource</h2>
		<div class="formFields clearfix">
		<c:url var="searchAction" value="/preOnbSearch" ></c:url>
		
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
		
		<form:form action="${searchAction}" modelAttribute="employee" method="POST" id="searchEmployee">
		<div class="formRow">
                <div class="col50per">
                    <div class="fieldLbl"><label>Emp ID :</label></div>
                    <div class="inputField">
                     	<form:input path="empId" size="8" id="txtEmpID"  class="input-control" data-validation="number" 
                     	style="width: 168px;"/>
						
                    </div>
                </div>
                <div class="col50per">
                    <div class="fieldLbl"><label>CORP ID :</label></div>
                    <div class="inputField">
                        <form:input path="corpId" id="txtCorpID" class="input-control"/>
                	</div>
                </div>
        </div>
        <div class="formRow">
                <div class="col50per">
                     <div class="fieldLbl"><label>First Name :</label></div>
                     <div class="inputField">
                         <form:input path="firstName" id="txtFirstName" class="input-control"/>
                    </div>
                </div>
                <div class="col50per">
                    <div class="fieldLbl"><label>Last Name :</label></div>
                    <div class="inputField">
                        <form:input path="lastName" id="txtLastName" class="input-control"/>
                	</div>
                </div>
            </div>
            <c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM')}">
	            <div class="formRow">
	            	<div class="col50per">
	                     <div class="fieldLbl"><label>Resource Status :</label></div>
	                     <div class="inputField">
	                         <%-- <form:input path="resourceStatus" id="resourceStatus" class="input-control"/> --%>
	                         <form:select path="resourceStatus" id="resourceStatus" class="select-control" data-validation="required">
	                         <option value="">-Select-</option>
                             <option value="EMSubmitted">EM Submitted</option>
                             <option value="RMApproved">RM Approved</option>
                             <option value="RMRejected">RM Rejected</option>
                             <option value="BISPMOSubmitted">BIS-PMO Submitted</option>
                             <option value="VMInProccess">VM in Process</option>
                             <option value="OnboardingCompleted">Onboarding Completed</option>
                             </form:select>
	                    </div>
	                </div>
	            </div>
            </c:if>
			<div class="text-center">
                <input type="submit" value="Search" title="Search" id="btnSubmit" class="btn-primary" />
                <input type="Reset" value="Reset" title="Reset" id="btnReset" class="btn-primary" onclick="this.form.reset();"/>
            </div>
		</form:form>
		</div>
		
			
		<c:if test="${!empty employeeList}">
		<h3 class="empLstsHD">Employee List</h3>
	
			&nbsp;
			<br/>
			<br/>
			
			<c:url var="editOnboard" value="/onboarding/editPreOnBoardingPage" ></c:url>
		<table id="summaryTbl" class="summary-view-table dt-responsive">
			<thead>
				<tr>
					<th width="40">View/Edit</th>
					<th width="80">Primary Program</th>
					<th width="80">Emp ID</th>
					<th width="80">Corp ID</th>
					<th width="80">Emp Type</th>
					<th width="150">Emp First Name</th>
					<th width="150">Emp Last Name</th>
					<th width="150">Requestor</th>
					<th width="150">Resource Status</th>
					<th width="50">Country</th>
					<th width="50">Location</th>
					<th width="10">Email</th>
					<th width="50">Primary Skill </th>
					<th width="50">Role </th>
					<th width="50">Vendor</th> 
					<th width="50">Grade</th>
					<th width="50">Manager</th>
					<th width="50">On-boarding Date(YYYY-MM-DD)</th>
					<th width="50">Billing Date(YYYY-MM-DD)</th>
					<th width="50">Planned Off-boarding Date(YYYY-MM-DD)</th>
					<th width="50">Bundle EM</th>
					<th width="50">EM</th>
					<th width="50">BIS</th>
					<th width="50">Resource Manager</th>
					<th width="50">Creation Date</th>
					<th width="50">EM Submit DT</th>
					<th width="50">RM Submit DT</th>
					<th width="50">BIS PMO Submit DT</th>
					<th width="50">RM PMO Submit DT</th>
					
				</tr>
			<thead>
				<tbody>
				
				<c:forEach items="${employeeList}" var="employee">
					<tr>
					
				
						<td><a href="<c:url value='/editPreOnBoardingPage/${employee.corpId}' />"
							title="Edit" class="editIcn"></a></td>	
						<td>${employee.primaryprogram.primaryProgramName}</td>
						<td>${employee.empId}</td>
						<td>${employee.corpId}</td>
						<td>${employee.empType}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<c:choose>
    						<c:when test="${employee.requestor ne null}">
								<td>${employee.requestor.firstName}&nbsp;${employee.requestor.lastName}</td>
							</c:when> 
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
						<c:if test="${employee.resourceStatus == 'EMSubmitted'}">
         					<td>EM Submitted</td>
      					</c:if>
						<c:if test="${employee.resourceStatus == 'RMApproved'}">
         					<td>RM Approved</td>
      					</c:if>
      					<c:if test="${employee.resourceStatus == 'RMRejected'}">
         					<td>RM Rejected</td>
      					</c:if>
      					<c:if test="${employee.resourceStatus == 'BISPMOSubmitted'}">
         					<td>BIS-PMO Submitted</td>
      					</c:if>
      					<c:if test="${employee.resourceStatus == 'VMInProccess'}">
         					<td>VM in Process</td>
      					</c:if>
						<c:if test="${employee.resourceStatus == 'OnboardingCompleted'}">
         					<td>Onboarding Completed</td>
      					</c:if>
      					<c:if test="${employee.resourceStatus == 'OnboardingInitiated'}">
         					<td>Onboarding Initiated</td>
      					</c:if>
      					
      					<c:if test="${(employee.resourceStatus eq null) or (employee.resourceStatus eq '')}">
         					<td>-</td>
      					</c:if>
						
						<td>${employee.country.countryName}</td>
						<td>${employee.location.stateName}</td>
						<td>${employee.email}</td>
						<td>${employee.primaryTechnology.technologyName}</td>
						<td>${employee.roleid.ref_name}</td>
						<td>${employee.vendor.vendorName}</td>
						<td>${employee.grade.name}</td>
						<td>${employee.manager.firstName}&nbsp;${employee.manager.lastName}</td>
						<td>${employee.onboardingDate}</td>
						<td>${employee.billingStartDate}</td>
						<td>${employee.offboardingDate}</td>
						<td>${employee.bundleEM.bundleEmName}</td> 						
						<td>${employee.EM.firstName}&nbsp;${employee.EM.lastName}</td>
						<td>${employee.bis.bis_Name}</td>
						<td>${employee.resourceManager.resourceMgrName}</td>
						<c:choose>
    						<c:when test="${employee.creationdate ne null}">
								<td>${employee.creationdate}</td>
							</c:when> 
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
    						<c:when test="${employee.EMSubmitDT ne null}">
								<td>${employee.EMSubmitDT}</td>
							</c:when> 
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
    						<c:when test="${employee.RMSubmitDT ne null}">
								<td>${employee.RMSubmitDT}</td>
							</c:when> 
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
    						<c:when test="${employee.BISPMOSubmitDT ne null}">
								<td>${employee.BISPMOSubmitDT}</td>
							</c:when> 
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
    						<c:when test="${employee.RMPMOSubmitDT ne null}">
								<td>${employee.RMPMOSubmitDT}</td>
							</c:when> 
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
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
			src="${pageContext.request.contextPath}/resources/js/tableExportEmp.js"></script>
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
		$(document).ready(function() {
			
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
	                	 columns: [ 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28]
	                 }
	             },
	        ],
		});
		
		});
		</script>
</body>
</html>
