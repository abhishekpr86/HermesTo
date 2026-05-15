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
		<h2>Search User</h2>
		<div class="formFields clearfix">
		<c:url var="searchAction" value="/userSearchCriteria" ></c:url>
		<c:url var="deleteAction" value="/deleteUserRole" />
		<c:url var="addAction" value="/addUserRole" />
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
		
		<form:form action="${searchAction}" commandName="users" method="POST" id="searchEmployee">
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
					<%-- <div class="col50per">
					
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
					</div> --%>
				
				
			<div class="text-center">
                <input type="submit" value="Search" title="Search" id="btnSubmit" class="btn-primary" />
               <!-- <input type="submit" value="Add Role" title="Add Role" id="btnAddRole" class="btn-primary" /> -->
            </div>
		</form:form>
		</div>
		
			
		<c:if test="${!empty userSearchList}">
		<h3 class="empLstsHD">User List</h3>
		<!-- <input type="button" id="btnExport" value="Excel Export"
				title="Excel Export" class="btn-primary" /> -->
			&nbsp;
			<br/>
			<br/>
		<table id="summaryTbl" class="summary-view-table dt-responsive">
			<thead>
				<tr>
				        
						<th width="350">User Name</th>
						<th width="350">Role Id</th>
						<th width="200">View/Delete</th>
						<!-- <th width="350">Enabled</th> -->
					
				</tr>
			<thead>
				<tbody>
				<c:forEach items="${userSearchList}" var="users"> 
					<tr>
					        <%--<td><a href="<c:url value='/users/${users.userName}' />" 
							title="Delete" class="editIcn"></a></td>
							 <td> <a href="#" onclick="userRoleObject(${users})" title="Delete" class="editIcn"> </a></td> --%>
							 <form:form action="${deleteAction}" commandName="users" method="POST" id="addemployee">
							 	
							 	
							 	 <input type="submit" title="Delete" id="btnSubmit" class="editIcn"/> 
							
							<td><form:hidden id="username" path="userName" value='${users.userName}'/>${users.userName}</td>
							<td><form:hidden id="roleId" path="role_id" value='${users.role_id.role_id}'/>${users.role_id.role_name}</td>
							<!-- <td><input type="submit" title="Delete" id="btnSubmit" class="editIcn"/> </td> -->
							<%-- <td>${users.enabled}</td> --%>
							 </form:form>
							
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
	                	 columns: [ 1, 2]
	                 }
	             },
	        ],
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
			 /* function() {
				
				$.getJSON(
								'${deleteUserRole}',
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
			});  */
		} 
	
		
		</script>
</body>
</html>
