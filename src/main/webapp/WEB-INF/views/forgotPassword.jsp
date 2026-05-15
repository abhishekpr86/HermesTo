<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Onboarding Tool - Password Reset</title>
<%-- <link href="${pageContext.request.contextPath}/assets/css/common.css" rel="stylesheet"> --%>
<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
</head>
<body class="login-body">
        <div class="wrapper">
            <div class="container">
                <div class="logoutframe">
                	<div class="logoutframe-block">
                		<h1>Please provide username</h1>
             <c:url var="addAction" value="/sendDefaultPasswordMail"></c:url>
             <form:form action="${addAction}" commandName="user" method="POST" id="roleSelect"> 
              		
             <div class="form-group">
             	
				<div class="form-group">
						<!-- <label for="exampleInputEmail1" class="text-uppercase">Username</label> -->
						<%-- <form:hidden id="username" path="userName" /> --%>
						<input type="text" id="userName" name="userName" class="form-control" placeholder="">
						<%-- <form:input path="userName" id="userName" class="input-control" /> --%>
				</div>
			 </div>
			 	<input type="submit" value="Reset Password" title="Reset Password" id="btnSubmit" class="btn-primary" />
			 </form:form>
			
                		
                	</div>
                </div>
            </div>
        </div>        
<%-- <% session.invalidate(); %> --%>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.base64.js"></script>
<script type="text/javascript">
$(document)
.ready(
		function() {
			$("#btnSubmit").click(function() {
				var role = $("#role_id").val();
				if (role == ""){
					alert('Please select Role');
					return false;
				}else{
					return true;
				}
				
			});
		});

</script>

</body>
</html>