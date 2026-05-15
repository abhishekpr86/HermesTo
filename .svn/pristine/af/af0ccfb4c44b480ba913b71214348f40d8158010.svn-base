<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>Resource OnBoarding Tool - Mandatory Training</title>
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/theme.css" />
	</head>
	<body>
	    <header class="main-header">
	        <div class="pull-left">
		  		<h1>HERMES Security Training Portal</h1>
	  		</div>
	    </header>
	    <section class="main-content">
			<c:choose>	    
		    	<c:when test="${isAlreadySaved}">
		    		<h2>You have already submitted form.</h2>
		    	</c:when>
		    	<c:when test="${message ne null }">
		    		<h2>${message}</h2>
		    	</c:when>
		    	<c:otherwise>
		    	<h2 class="pull-left">Training Name <%-- ${trainingCount} of ${totalTrainingCount} --%> : ${trainingName}</h2> 
		    	<br/>
		    	<br/>
		    	<br/>
		    	<br/>
		    	<br/>
		  		<h6>Please scroll through to read and understand the security training document below. 
		  		Once done, please check the check-box below certifying that you have read and understood the document, and click the Submit and Next button to move to the next applicable training.</h6>
		  		
		    		<c:url var="submitForm" value="/resourceView/${empId}/" ></c:url>
		    		<form method="post" action="${submitForm}" id="resourceForm">
				        <iframe src="${docUrl}" style="width: 100%;margin:25px 0 0;height:525px;border:1px solid #8a8a8a;"></iframe>
				        <div class="text-center">
				            <p class="acceptedNote">
				            	<input type="checkbox" id="chkAccepted" data-validation="required" data-validation-error-msg="Please read the training documents.">&nbsp;&nbsp;<label for="chkAccepted">I have read and understood the training document</label>
				            </p>
				            <input type="submit" name="btnSubmitAndExit" id="btnSubmitAndExit" onclick="javascript:confirmPopup();" class="btn-primary" value="Submit and Exit">
				            <input type="submit" name="btnSubmitAndNext" id="btnSubmitAndNext" onclick="javascript:confirmPopup();" class="btn-primary" value="Submit and Next">				            
				            <input type="submit" name="btnCancel" class="btn-primary" value="Cancel">
				        </div>
			        </form>
		    	</c:otherwise>
		    </c:choose>	
	    </section>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.form-validator.min.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() 
		    {
				$.validate({
				  form : '#resourceForm'
				});
		    });
		    
		    function confirmPopup(){
		    	 var status;
		    	 if($("#chkAccepted").is(":checked") == false) {
		    	    if (confirm("Please check the check-box before submitting. If you wish to continue at a later time, please click the Cancel button instead.") == true) {
		    	    } else {
		    	    	window.close();
		    	    }
		    	 }
		    	    return true;
		    }
		</script>
	</body>
</html>