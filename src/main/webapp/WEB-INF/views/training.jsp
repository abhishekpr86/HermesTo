<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	"Hello!!"
</body>
</html> --%>
<!--  -->





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
    <title>Resource OnBoarding Tool - Add Training</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/theme.css" />
</head>
<body>
	<header class="main-header">
        <div class="pull-left">
	  		<h1 class="pull-left">Onboarding Tool</h1>
		</div>
    </header>
	<section class="main-content">
		<br/>
		<h2>Add Training</h2>
		<div class="formFields clearfix">
		<div class="reqText">* indicates required fields</div>
		<c:url var="addAction" value="/training/add" ></c:url>
		
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
		
		<form:form action="${addAction}" commandName="employee" method="POST" id="registration">
		<div class="formRow">
                <div class="col50per">
                    <div class="fieldLbl"><label>Emp ID <span class="req">*</span>:</label></div>
                    <div class="inputField">
                     	<form:input path="empId" size="8" id="txtEmpID"  class="input-control" data-validation="number" />
						<form:hidden path="empId" />
                    </div>
                </div>
                <div class="col50per">
                    <div class="fieldLbl"><label>CORP ID <span class="req">*</span>:</label></div>
                    <div class="inputField">
                        <form:input path="corpId" id="txtCorpID" class="input-control" data-validation="required" />
                	</div>
                </div>
        </div>
        <div class="formRow">
                <div class="col50per">
                     <div class="fieldLbl"><label>First Name <span class="req">*</span>:</label></div>
                     <div class="inputField">
                         <form:input path="firstName" id="txtFirstName" class="input-control" data-validation="required" />
                    </div>
                </div>
                <div class="col50per">
                    <div class="fieldLbl"><label>Last Name <span class="req">*</span>:</label></div>
                    <div class="inputField">
                        <form:input path="lastName" id="txtLastName" class="input-control" data-validation="required" />
                	</div>
                </div>
            </div>
             <div class="formRow">
                <div class="col50per">
                        <div class="fieldLbl"><label>Email <span class="req">*</span>:</label></div>
                        <div class="inputField">
                            <form:input path="email" id="txtEmail" class="input-control" data-validation="email" />
                    </div>
                </div>
                <div class="col50per">
                        <div class="fieldLbl"><label>Grade <span class="req">*</span>:</label></div>
                        <div class="inputField">
                            <form:select path="grade" id="drpGrade" class="select-control" name="grade" data-validation="required">
                                 <option value="" selected="true">-Select-</option>
                                 <option value="P0">P0</option>
                                 <option value="P1">P1</option>
                                 <option value="P2">P2</option>
                                 <option value="P3">P3</option>
                                 <option value="P4">P4</option>
                                 <option value="P5">P5</option>
                                 <option value="M1">M1</option>
                                 <option value="M2">M2</option>
                                 <option value="M3">M3</option>
                                 <option value="M4">M4</option>
                                 <option value="M5">M5</option>
                                 <option value="M6">M6</option>
                            </form:select>
                        </div>
                </div>
            </div>
            <div class="formRow">
                <div class="col50per">
                        <div class="fieldLbl"><label>Primary Skill <span class="req">*</span>:</label></div>
                        <div class="inputField">
                            <form:select path="primaryTechnology" id="drpPrimaryTechnology" class="select-control" data-validation="required">
                                    <option value="" selected="true">-Select-</option>
                                    <option value="Java">Java</option>
                                    <option value="LAMP">LAMP</option>
                                    <option value="Java/MQ-Series">Java/MQ-Series</option>
                                    <option value="COBOL">COBOL</option>
                                    <option value="COTS-SO">COTS-SO</option>
                                    <option value="SAP BO/BW">SAP BO/BW</option>
                                    <option value="COTS">COTS</option>
                                    <option value="SAP">SAP</option>
                                    <option value="NOMAD">NOMAD</option>
                                    <option value="Lotus Notes">Lotus Notes</option>
                                    <option value="Vision Builder">Vision Builder</option>
                                    <option value="VB">VB</option>
                                    <option value="Tandem COBOL">Tandem COBOL</option>
                                    <option value="Progiciel">Progiciel</option>
                                    <option value="HRACCESS">HRACCESS</option>
                                    <option value="PROGRESS">PROGRESS</option>
                                    <option value="COTS-SECTOR">COTS-SECTOR</option>
                                    <option value="Essbase">Essbase</option>
                                    <option value="MSBI">MSBI</option>
                                    <option value="C, C++">C, C++</option>
                                    <option value="Dotnet">Dotnet</option>
                                    <option value="EDI">EDI</option>
                                    <option value="PL1">PL1</option>
                                    <option value="SAAS">SAAS</option>
                                    <option value="Interface">Interface</option>
                                    <option value="Huron/ObjectStar">Huron/ObjectStar</option>
                                    <option value="MS Navision">MS Navision</option>
                                    <option value="Oracle">Oracle</option>
                                    <option value="QlikView">QlikView</option>
                                    <option value="BI Cognos">BI Cognos</option>
                                    <option value="HP IDOL">HP IDOL</option>
                                    <option value="UNIFACE">UNIFACE</option>
                                    <option value="Sharepoint">Sharepoint</option>
                                    <option value="Mobility">Mobility</option>
                            </form:select>
                        </div>
                </div>
                <div class="col50per">
                     <div class="fieldLbl"><label>Start Date <span class="req">*</span>:</label></div>
                     <div class="inputField">
                         <form:input path="joiningDate" id="txtJoiningDate" class="input-control" data-validation="birthdate" data-validation-format="dd/mm/yyyy" data-validation-help="Please enter date in dd/mm/dddd format" />
                     </div>
                </div>
                
                <div class="col50per">
                     <div class="fieldLbl"><label>End Date <span class="req">*</span>:</label></div>
                     <div class="inputField">
                         <form:input path="endingDate" id="txtEndingDate" class="input-control" data-validation="birthdate" data-validation-format="dd/mm/yyyy" data-validation-help="Please enter date in dd/mm/dddd format" />
                     </div>
                </div>
                
            </div>
            <div class="formRow">
                <div class="col50per">
                        <div class="fieldLbl"><label>Contact No. :</label></div>
                        <div class="inputField">
                            <form:input path="contact"  id="txtContact" class="input-control" />
                        </div>
                </div>
                <div class="col50per">&nbsp;</div>
            </div>
			<div class="text-center">
                <input type="submit" value="Submit" title="Submit" id="btnSubmit" class="btn-primary" />
                <input type="submit" value="Reset" title="Reset" id="btnReset" class="btn-primary" />
            </div>
		</form:form>
		</div>
	</section>
	<%-- <br>
	<h3>Employee List</h3>
	<c:if test="${!empty listEmployees}">
		<table class="tg">
		<tr>
			<th width="80">Person ID</th>
			<th width="120">Person Name</th>
			<th width="120">Person Country</th>
			<th width="60">Edit</th>
			<th width="60">Delete</th>
		</tr>
		<c:forEach items="${listEmployees}" var="employee">
			<tr>
				<td>${employee.empId}</td>
				<td>${employee.firstName}</td>
				<td>${employee.lastName}</td>
				<td><a href="<c:url value='/edit/${employee.empId}' />" >Edit</a></td>
				<td><a href="<c:url value='/remove/${employee.empId}' />" >Delete</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if> --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.23/jquery.form-validator.min.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() 
		    {
		    		$("#drpGrade").val('${employee.grade}');
		    		$("#drpPrimaryTechnology").val('${employee.primaryTechnology}');
		    		 $.validate({
		    			  form : '#registration',
		    			         modules : 'date'
		    	});
		   });
		</script>
	</body>
</html>



