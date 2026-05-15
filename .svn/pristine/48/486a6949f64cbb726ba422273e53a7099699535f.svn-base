<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>On Boarding - Change Password</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<body class="login-body">
	<div class="wrapper">
		<div class="container">
			<div class="logoutframe">
				<div class="logoutframe-block">
					<c:choose>
						<c:when test="${passwordChangeStatus}">
							<h1>Password changed successfully</h1>
							<p class="text-center">
							<a href="${pageContext.request.contextPath}/login"
							class="btn-primary">Click here to Login with your new password</a>
							</p>
						</c:when>
						<c:otherwise>
							<h1>Password change failed</h1>
							<p class="text-center">
							<a href="${pageContext.request.contextPath}/login"
							class="btn-primary">Click here to return to Login Page</a>
							</p>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>