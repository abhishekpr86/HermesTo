<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/newcommon.css" />
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="login-block">
	<div class="container">
		<div class="row">
			<div class="col-md-4 login-sec">
				<p class="text-center">
					<%-- <img
						src="${pageContext.request.contextPath}/resources/images/hermes.png"> --%>
						
						<img src="${pageContext.request.contextPath}/resources/images/newLogo.png">
				</p>
				<h2 class="text-center">Resource Onboarding Tool 12.0</h2>
				<%-- <a href="${pageContext.request.contextPath}/resetPassword" class="button" role="button">I forgot my password</a> --%>
				<form action="${loginUrl}"  method="post">

					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>
					<div class="form-group">
						<label for="exampleInputEmail1" class="text-uppercase">Username</label>
						<input type="text" id="username" name="ssoId" class="form-control"
							placeholder="">

					</div>

					<div class="form-group">
						<label for="exampleInputPassword1" class="text-uppercase">Password</label>
						<input type="password" id="password" name="password"
							class="form-control" placeholder="">
					</div>


					<c:if test="${param.error != null}">
						<div class="error-msg">
							<p>Invalid username and password.</p>
						</div>
					</c:if>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="clearfix">
						<A tabindex="10" href="${pageContext.request.contextPath}/resetPassword">Forgot Password?</A> 
						<button type="submit" class="btn btn-login float-right">Login</button>
					</div>
				
				</form>
				
				
		   
			</div>
			<div class="col-md-8 banner-sec">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner" role="listbox">
						
				</div>
			</div>
		</div>
	</div>
</section>
	  