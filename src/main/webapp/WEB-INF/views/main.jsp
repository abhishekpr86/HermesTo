<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>On Boarding</title>

<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.3.min.js"></script>
</head>
<body>
	<header class="main-header">
		<div class="pull-left">
			<h1 class="pull-left">Onboarding Tool</h1>
		</div>
		<nav class="pull-right">
			<ul class="nav-new">
	
				<c:if test="${checkUserType ne 'FirstLogin'}">
					<li><a href="${pageContext.request.contextPath}/dashboard" title="DashBoard">DashBoard</a></li>
				</c:if>
				
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

			<c:if test="${(checkUserType eq 'BundleEM')}"><!-- EM -->
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View Resources</a></li>
			</c:if>
			
			<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO') or (checkUserType eq 'ASL')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View / Edit Resources</a></li>
			</c:if>
			
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM_PMO')}">
				<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Pre-Onboarding</a></li> --%>
				<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Onboarding Requests</a></li>
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
				<li class="active"><a href="${pageContext.request.contextPath}/main" title="Change Password">Change Password</a></li>
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="wrapper">
		<div class="container">

			<div id="dvChangePassword" class="changePasswordBox">
				<c:if test="${checkUserType ne 'FirstLogin'}">
					<h3>Change Password</h3>
				</c:if>
				<c:if test="${checkUserType eq 'FirstLogin'}">
					<h3>First Login - Please Change Password</h3>
				</c:if>
				<div>
					<img
						src="${pageContext.request.contextPath}/resources/images/change-password-img.png"
						class="pull-left" alt="Change Password">
					<div class="changePasswordForm">
						<s:form name="ChangePasswordForm" method="post" modelAttribute="changePwd"
						modelAttribute="changePwd"
							action="${pageContext.request.contextPath}/changePassword.htm"
							OnSubmit="return fncSubmit();">

							<div class="formRow">
								<label>Old Password</label> <input name="oldPassword"
									type="password" id="OLDpwd" size="20" class="form-control">
								<span id="errorOldPassword" class="errorMsg"
									style="display: none;">Please input old password</span>
							</div>

							<div class="formRow">
								<label>New Password</label> <input name="newpassword"
									type="password" id="newpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" class="form-control" 
									title="Must contain at least one number and one uppercase and one lowercase letter, and length minimum 5" required>
								<span id="errorNewPassword" class="errorMsg"
									style="display: none;">Please input new Password</span>
							</div>

							<div class="formRow">
								<label>Confirm Password</label> <input name="conpassword"
									type="password" id="conpassword" class="form-control">
								<span id="errorConfPassword" class="errorMsg"
									style="display: none;">Please input Confirm Password</span> <span
									id="passwordMatch" class="errorMsg" style="display: none;">New
									&amp; Confirm Password doesn't match</span>
							</div>
							<input type="submit" name="Submit" value="Save"
								class="btn-primary"> <input type="reset" name="Reset"
								value="Reset" class="btn-primary"> <input type="hidden"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
						</s:form>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
	

	<script language="javascript">
		function fncSubmit() {
			if (document.ChangePasswordForm.oldPassword.value == "") {
				$("#errorOldPassword").css("display", "block");
				$("#OLDpwd").addClass("inputError");
				document.ChangePasswordForm.oldPassword.focus();
				return false;
			} else {
				$("#errorOldPassword").css("display", "none");
				$("#OLDpwd").removeClass("inputError");
			}

			if (document.ChangePasswordForm.newpassword.value == "") {
				$("#errorNewPassword").css("display", "block");
				$("#newpassword").addClass("inputError");
				document.ChangePasswordForm.newpassword.focus();
				return false;
			} else {
				$("#errorNewPassword").css("display", "none");
				$("#newpassword").removeClass("inputError");
			}

			if (document.ChangePasswordForm.conpassword.value == "") {
				$("#errorConfPassword").css("display", "block");
				$("#conpassword").addClass("inputError");
				document.ChangePasswordForm.conpassword.focus();
				return false;
			} else {
				$("#errorConfPassword").css("display", "none");
				$("#conpassword").removeClass("inputError");
			}

			if (document.ChangePasswordForm.newpassword.value != document.ChangePasswordForm.conpassword.value) {
				$("#passwordMatch").css("display", "block");
				$("#newpassword, #conpassword").addClass("inputError");
				document.ChangePasswordForm.newpassword.focus();
				return false;
			} else {
				$("#passwordMatch").css("display", "none");
				$("#newpassword, #conpassword").removeClass("inputError");
			}

			document.ChangePasswordForm.submit();
		}
	</script>


</body>
</html>