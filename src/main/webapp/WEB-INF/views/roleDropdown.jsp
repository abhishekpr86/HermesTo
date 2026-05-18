
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
<title>Onboarding Tool - Role</title>
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
                		<h1><spring:message code="label.role.select"/></h1>
               <c:if test="${isFirstLogin eq 'No'}">
             	 <c:url var="addAction" value="/dashboard"></c:url>
             	</c:if>
             	
              <c:if test="${isFirstLogin eq 'Yes'}">
             	 <c:url var="addAction" value="/main"></c:url>
              </c:if>
              
             <form:form action="${addAction}" modelAttribute="role" method="POST" id="roleSelect"> 
             
             <%-- <div class="inputField">
							<form:select path="role" id="role_id"
								class="select-control" data-validation="required">
								<option value="">-Select-</option>
								<c:forEach items="${roleList}" var="role">
									<form:option value="${role.role}">${role.role_name}</form:option>
								</c:forEach>
							</form:select>
			</div> --%>
              		
             <div class="form-group">
             	<select id="role_id" name="role_id" class="login-control">
             	 	<option value="" selected="true">-Select-</option>
	             	 <c:forEach items="${roleList}" var="role">
	             	 
	             	 	<option value="${role.role}">${role.role_name}</option>
	              				
				     </c:forEach>  
				</select> 
			 </div>
			 	<input type="submit" value="Add" title="On-board" id="btnSubmit" class="btn-primary" />
			 </form:form>
			 <%-- <p class="text-center"><a href="${pageContext.request.contextPath}/dashboard" class="btn-primary">Go</a></p> --%>
                		
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
					alert('Please select Role')
					return false;
				}else{
					return true;
				}
				
			});
		});

</script>

</body>
</html>

 
