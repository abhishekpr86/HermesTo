<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Resource On-boarding Tool - Welcome!!!! Login</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
    </head>
 
    <body class="login-body">
    	<div class="wrapper">
    <div class="login-outer clearfix">
       <div class="loginframe">
        <h1 class="text-center welcome-text">Resource On-boarding Tool 7.1</h1>
    </div>
    <div class="form-container">
        <p class="text-center"><img src="${pageContext.request.contextPath}/resources/images/hermes.png"></p>
        <div class="form-content">
        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" onsubmit="return validateUser();" method="post">
                        
                        <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
              <div class="form-group">
                <input type="text" id="username" name="ssoId" class="login-control" placeholder="Your User Id...">
              </div>
              <div class="form-group">
                <input type="password" id="password" name="password" class="login-control" placeholder="Your Password...">
              </div>
              
              <c:if test="${param.error != null}">
                                <div class="error-msg">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
              
              <div class="clearfix">
                <button class="login-btn" id="btnLogin">Login</button><span class="forgot-password" style="display:none;"><a href="javascript:void(0);" title="Forgot Password">Forgot Password?</a></span>
              
              </div>
            </form>
            </div>
    </div> 
    </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
      <script type="text/javascript">
          function validateUser(){
                var username =  $("#username").val();
                var password =  $("#password").val();
                if(username=="" || username==null){
                      $("#username").addClass("input-error");
                      return false;
                }
                  else
                      $("#username").removeClass("input-error");          
                  if(password=="" || password==null){
                      $("#password").addClass("input-error");
                      return false;
                  }
                  else
                      $("#password").removeClass("input-error");
              };
      </script>     
    </body>
</html>