<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Transition Tool - Logout</title>
<link href="${pageContext.request.contextPath}/assets/css/common.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
</head>
<body class="login-body">
        <div class="wrapper">
            <div class="container">
                <div class="logoutframe">
                	<div class="logoutframe-block">
                		<h1>You have been successfully logout from the application</h1>
                		<p class="text-center"><a href="${pageContext.request.contextPath}/role" class="btn-primary">Login</a></p>
                	</div>
                </div>
            </div>
        </div>        
<% session.invalidate(); %>
</body>
</html>

 
