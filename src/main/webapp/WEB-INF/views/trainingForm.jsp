<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Resource OnBoarding Tool - Add Employee Training</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />
</head>
<body>
	<header class="main-header">
		<div class="pull-left">
			<h1 class="pull-left">Onboarding Tool</h1>
		</div>
	</header>



	<section class="main-content">
		<br />
		<h2>Add Training</h2>
		<div class="formFields clearfix">
			<div class="reqText">* indicates required fields</div>
			<c:url var="addAction" value="/training/add"></c:url>

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

			<form:form action="${addAction}" modelAttribute="training" method="POST"
				id="registration">
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Training Name <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="name" id="txtTrainingName"
								class="input-control" data-validation="required" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Training Type <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="type" id="txtTrainingType"
								class="input-control" data-validation="required" />
						</div>
					</div>
				</div>


				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Training Order <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="order" id="txtTrainingOrder"
								class="input-control" data-validation="number" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Document URL <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="docURL" id="txtDocURL" class="input-control"
								data-validation="required" />
						</div>
					</div>
				</div>

				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Start Date <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="startDate" id="txtStartDate"
								class="input-control" data-validation="birthdate"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/dddd format" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>End Date <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="endDate" id="txtEndDate" class="input-control"
								data-validation-format="dd/mm/yyyy"
								data-validation-help="Please enter date in dd/mm/dddd format" />
						</div>
					</div>
				</div>

				<!--  -->
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Description <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:input path="description" id="txtDescription"
								class="input-control" data-validation="required" />
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Mandatory <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:checkbox path="mandatory" value="True" />
							Yes
						</div>
					</div>
				</div>
				<!--  -->

				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Grade <span class="req">*</span>:
							</label>
						</div>
						<div class="inputField">
							<form:select path="grades" multiple="true" id="drpGrade"
								class="select-control" style="height:100px;width:168px;">
								<c:forEach items="${listGrades}" var="grd">
									<form:option value="${grd.gradeId}">${grd.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
		</div>



		<div class="text-center">
			<input type="submit" value="Submit" title="Submit" id="btnSubmit"
				class="btn-primary" /> <input type="submit" value="Reset"
				title="Reset" id="btnReset" class="btn-primary" />
		</div>

		</form:form>
	</section>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.23/jquery.form-validator.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#drpGrade").val('${employee.grade}');
		//	$("#drpPrimaryTechnology").val('${employee.primaryTechnology}');
			$.validate({
				form : '#registration',
				modules : 'date'
			});
		});
	</script>
</body>
</html>
