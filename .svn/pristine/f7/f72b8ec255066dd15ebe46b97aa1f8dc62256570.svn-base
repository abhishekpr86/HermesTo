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
<title>Resource OnBoarding Tool - Users List </title>
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
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
	
	<br/>
		<h2>Search User</h2>
		<div class="formFields clearfix">
		<c:url var="addAction" value="/addRoleToUserRoles" ></c:url>
		<c:url var="deleteAction" value="/deleteUserRole" />
		<c:url var="checkRoleList" value="/checkRoleList" />
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
		
		<form:form action="${addAction}" commandName="users" method="POST" id="searchEmployee">
		<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>User Name :
							</label>
						</div>
						<div class="inputField">
							<form:input path="userName" id="txtuserName"
								class="input-control" />
						</div>
					</div>
					<!-- <div class="col50per"> -->
					<div class="col50per" id="divRole" style="display: none;">
						<div class="fieldLbl">
							<label>Role Id :
							</label>
						</div>
						<div class="inputField">
							<form:select path="role_id" id="drproleId"
								class="select-control">
								<c:forEach items="${roleList}" var="role">
								
									<form:option value="${role.role_id}">${role.role_name}</form:option>
								</c:forEach>
							</form:select>
							
						</div>
					</div>
				
			</div>
			<div class="text-center">
                <input type="submit" value="Add" title="Add" id="btnSubmit" class="btn-primary" />
               <!--  <input type="Reset" value="Reset" title="Reset" id="btnReset" class="btn-primary" /> -->
            </div>
		</form:form>
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
			
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/dataTables.buttons.min.js"></script>			
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/buttons.flash.min.js"></script>			
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/buttons.html5.min.js"></script>
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jszip.min.js"></script>
			
			
	<script type="text/javascript">
		/* $(document).ready(function() {
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
	                	 columns: [ 1, 2]
	                 }
	             },
	        ],
		});
		}); */
		$(document)
		.ready(
				function() {
					
					$('#txtuserName')
							.change(
									function() {
										
										$("#divRole").css("display","");
										$.getJSON(
														'${checkRoleList}',
														{
															username : $(
																	'#txtuserName')
																	.val(),
															ajax : 'true'
														},
														function(data) {
															var html = '<option value="">-Select-</option>';
															roleList = data.addRoleList;
															
															var roleLength = roleList.length;
															for (var i = 0; i < roleLength; i++) {
																html += '<option value="' + roleList[i].role_id + '">'
																		+ roleList[i].role_name
																		+ '</option>';
																		
															}
															html += '</option>';
															$('#drproleId').html(html);
														});
									});
					
		});
		 /* $('#userRoleObject')
			.click(
					function() {
						
						alert(this.userName);
					}); */
		 function userRoleObject(users){
			alert(users);
			alert(users.userName);
			 
		} 
	
		
		</script>
</body>
</html>
