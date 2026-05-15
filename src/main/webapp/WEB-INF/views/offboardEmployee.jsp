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
<title>Resource OnBoarding Tool - Add Resource</title>
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
		<nav class="pull-right">
			<ul class="nav-new">


				<li><a href="${pageContext.request.contextPath}/dashboard" title="DashBoard">DashBoard</a></li>

				<c:if test="${checkUserType eq 'RM_PMO'}">
					<li class="reports"><a title="User Management">User
							Management</a>
						<ul class="reportsSubMenu">
							<li><a href="${pageContext.request.contextPath}/userManagement"
								title="userManagement">Add User</a></li>
							<li><a href="${pageContext.request.contextPath}/addRole" title="userSearch">Add
									Role</a></li>
						</ul></li>
				</c:if>

				<c:if test="${checkUserType eq 'BundleEM'}">
					<li><a href="${pageContext.request.contextPath}/addPreOnboardRec" title="Resource Pre-Onboarding">Onboarding Request</a></li>
						<!-- title="Resource Pre-Onboarding">Resource Pre-Onboarding</a></li> -->
				</c:if>
				<c:if
					test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM') or (checkUserTypeforUM ne null)}">
					<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Onboarding Requests</a></li>
				</c:if>
				<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM_PMO')}">
					<%-- <li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Pre-Onboarding</a></li> --%>
					<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="View Pre-Onboarding">View Onboarding Requests</a></li>
				</c:if>

				<c:if
					test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="Edit List">View
							/ Edit Resources</a></li>
				</c:if>
				<c:if
					test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM')}">
					<li><a href="${pageContext.request.contextPath}/allResourceList" title="View List">View
							Resources</a></li>
				</c:if>

				<c:if test="${checkUserType eq 'RM_PMO'}">
					<li class="active"><a href="javascript:void(0);"
						title="Resource Off-boarding">Resource Off-boarding</a></li>
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
				<li><a href="${pageContext.request.contextPath}/main" title="Change Password">Change
						Password</a></li>
				<li><a href="${pageContext.request.contextPath}//logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<c:choose>
			<c:when test="${errorMsg ne null}">
				<div class="errorBox">
					<h3>${errorMsg}</h3>
				</div>
			</c:when>
			<c:when test="${successMsg ne null}">
				<h2>${successMsg}</h2>
				<a href="${pageContext.request.contextPath}/employeeSearch" title="Back"
					class="btn-primary" style="display: inline-block;">Done</a>
			</c:when>
			<c:otherwise>
				<div class="formFields clearfix">
					<c:url var="offboardAction" value="/employee/offboard"></c:url>
					<c:url var="checkRepCorpIdExists" value="/checkRepCorpIdExists" />
					<c:url var="getReplacementNameByCorpID" value="/getReplacementNameByCorpID" />
					<c:url var="checkActualDate" value="/checkActualDate" />

					<form:form action="${offboardAction}" commandName="employee" method="POST" id="offboardEmployee">
						<div class="formRow">
							<div class="col50per">
								<div class="fieldLbl">
									<form:hidden id="userReadOnly" path="userReadOnly" />
									<label>Emp ID : </label>
								</div>
								<div class="inputField">
									<form:input path="empId" size="8" id="txtEmpID"
										class="input-readonly" disabled="true" />
									<form:hidden path="empId" />
								</div>
							</div>
							<div class="col50per">
								<div class="fieldLbl">
									<label>CORP ID : </label>
								</div>
								<div class="inputField">
									<form:input path="corpId" id="txtCorpID" disabled="true"
										class="input-readonly" />
								</div>
							</div>
						</div>
						<div class="formRow">
							<div class="col50per">
								<div class="fieldLbl">
									<form:hidden id="id" path="id" />
								</div>
								<div class="fieldLbl">
									<label>First Name : </label>
								</div>
								<div class="inputField">
									<form:input path="firstName" disabled="true" id="txtFirstName"
										class="input-readonly" />
								</div>
							</div>
							<div class="col50per">
								<div class="fieldLbl">
									<label>Last Name : </label>
								</div>
								<div class="inputField">
									<form:input path="lastName" disabled="true" id="txtLastName"
										class="input-readonly" />
								</div>
							</div>
						</div>
						<div class="formRow">
							<div class="col50per">
								<div class="fieldLbl">
									<label>On-boarding Date (dd/mm/yyyy) : </label>
								</div>
								<div class="inputField">
									<form:input path="joiningDate" disabled="true"
										id="txtJoiningDate" class="input-readonly" />
								</div>
							</div>

							<div class="col50per">
								<div class="fieldLbl">
									<label>Billing Date (dd/mm/yyyy) : </label>
								</div>
								<div class="inputField">
									<form:input path="billingDate" id="txtBillingDate" disabled="true"
										class="input-readonly" />
								</div>
							</div>
						</div>
						<div class="formRow">
							<div class="col50per">
								<div class="fieldLbl">
									<label>Planned Off-boarding Date (dd/mm/yyyy) : </label>
								</div>
								<div class="inputField">
									<form:input path="endDate" id="txtEndDate" disabled="true"
										class="input-readonly" />
								</div>
							</div>
							<div class="col50per">
								<div class="fieldLbl">
									<label id="actualDateLabel">Actual Off-boarding Date
										(dd/mm/yyyy) <span class="req">*</span>:
									</label>
								</div>
								<div class="inputField">
									<form:input path="actualEndDate" id="txtActEndDate"
										class="input-control" data-validation="date"
										data-validation-format="dd/mm/yyyy"
										data-validation-help="Please enter date in dd/mm/yyyy format" />
									<div id="endDateError" style="display: none;"
										class="form-error"></div>
								</div>
							</div>
						</div>
						<div class="formRow">
							

							<div class="col50per">
								<div class="fieldLbl">
									<label>Onboarding Checklist(s) completed: </label>
								</div>
								<div class="inputField">
									<form:checkbox path="onboardingChecked" value="false" id="onbChecklist" />
									Yes
								</div>
							</div>
						</div>

						<div class="formRow">
							<div class="col50per">
								<div class="fieldLbl">
									<label id="rollOffLabel">Reason for Roll-off<span
										class="req">*</span>:
									</label>
								</div>
								<div class="inputField">
								<form:select path="rollOffTypeVal" id="drpRollOffType" class="select-control"  data-validation="required">
									<!-- <option value="" selected="true">-Select-</option> -->
									
									<c:forEach items="${listRollOffList}" var="rollOff">
									
										<form:option value="${rollOff.id}">${rollOff.type}</form:option>
									
									</c:forEach>
								</form:select>
									
									<div id="rollOffError" style="display: none;"
										class="form-error"></div>
								</div>
							</div>

							<div class="col50per">
								<div class="fieldLbl">
									<label>Replacement Required: </label>
								</div>
								<div class="inputField">
									<form:checkbox path="replacementRequired" id="checklist" />
									Yes
								</div>
							</div>

						</div>

						<div class="formRow">
							<div class="col50per" id="divReplacementReq">
								<div class="fieldLbl">
									<label id="replcmntTypeLabel">Reason for No Replacement<span
										class="req">*</span>:
									</label>
								</div>
								<div class="inputField">
									<form:select path="replacementTypeVal" id="drpReplacementType" class="select-control"  data-validation="required">
										<!-- <option value="" selected="true">-Select-</option> -->
										<c:forEach items="${listReplacementType}" var="replace">
											<form:option value="${replace.id}">${replace.type}</form:option>
										</c:forEach>
									</form:select>
									<div id="repLocError" style="display: none;" class="form-error"></div>
								</div>
							</div>

						</div>



						<div class="formRow">
							<div class="col50per" id="divReplacementLoc"
								style="display: none;">
								<div class="fieldLbl">
									<label>Replacement location<span class="req">*</span>:
									</label>
								</div>
								<div class="inputField">
									<form:select path="country" id="drpCountry"
										class="select-control" data-validation="required">
										<option value="">-Select-</option>
										<c:forEach items="${listCountry}" var="cntry">
											<form:option value="${cntry.countryId}">${cntry.countryName}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="col50per" id="divRrNo" style="display: none;">
								<div class="fieldLbl">
									<label id="commonID">Replacement JIRA :<!-- <span class='req'>*</span>: -->
									</label>
								</div>
								<div class="inputField">
									<form:input path="rr_no" size="8" id="txtRrNo"
										class="input-control" />
									<%-- <form:hidden path="empId" /> --%>
								</div>
							</div>
						</div>



						<div class="formRow">
							<div class="col50per" id="divRepCorpId" style="display: none;">
								<div class="fieldLbl">
									<label id="corpID">Replacement CORP ID: </label>
								</div>
								<div class="inputField">
									<form:input path="repCorpId" id="txtRepCorpID"
										class="input-control" />
								</div>
							</div>

							<div class="col50per" id="divRepFirstName" style="display: none;">
								<div class="fieldLbl">
									<label>Replacement Employee Name: </label>
								</div>
								<div class="inputField">
									<form:input path="repfirstName" id="txtRepFirstName"
										class="input-readonly" />
								</div>
							</div>
						</div>

						<div class="formRow">
							<div class="col50per" id="divcomment">
								<div class="fieldLbl">
									<label>Comment :</label>
								</div>
								<div class="inputField">
									<form:textarea path="offboardcomment" maxlength="200" id="comment"
										class="textarea-control" />
								</div>

							</div>
						</div></div>





				<div class="text-center">
					<a href="${pageContext.request.contextPath}/employeeSearch" title="Back"
						class="btn-primary" style="display: inline-block;">Back</a>
					<!-- <input type="submit" value="Back" title="Back" id="btnback" onclick="history.go(-2)" class="btn-primary"> -->
					&nbsp;&nbsp;<input type="submit" value="Off-board"
						title="Off-board" id="btnSubmit" class="btn-primary" />
					&nbsp;&nbsp;

				</div>
				</form:form>
				</div>
			</c:otherwise>
		</c:choose>
	</section>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/datatables.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.form-validator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/tableExportEmp.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.base64.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		
		
		if(${employee.actualEndDate eq null}){
			
		}else{
    		
				$.getJSON(
						'${checkActualDate}',
						{
						actualDate : $("#txtActEndDate").val(),
								ajax : 'true'
							},
					
							function(data) {
								if (data == true) {
									$('#txtActEndDate').attr("class","input-readonly");
					    			
					    			$('#drpReplacementType').attr("class","input-readonly");
					    			$('#drpRollOffType').attr("class","input-readonly"); 
					    			$("#onbChecklist").attr("disabled", true);
					    			if($("#drpRollOffType").val() != 3){
					    			$("#btnSubmit").attr("disabled", true);
					    			$("#btnSubmit").css("display","none");
					    			$("#checklist").attr("disabled", true);
					    			
					    			}
								}	
							});
				
			
		} 
		if ($('#checklist').is(':checked')){
			$("#divReplacementReq").css("display","none");
		    $("#divReplacementLoc").css("display", "");
			$("#divRrNo").css("display", "");
			$("#divRepCorpId").css("display", "");
			$("#divRepFirstName").css("display", "");
	
		} else{
			
			$("#divReplacementReq").css("display","");
			 $("#divReplacementLoc").css("display", "none");
			$("#divRrNo").css("display", "none");
			$("#divRepCorpId").css("display", "none");
			$("#divRepFirstName").css("display", "none");
		}
		
		

		
		if ($("#userReadOnly").val() == 'true') {
			$("#btnSubmit").css("display","none");
			$('div *').prop('disabled',true);
		
		}
		
		
		
		
		
		$('#checklist').click(
				function() {
					
					
					if ($('#checklist').is(':checked')){
						$("#divReplacementReq").css("display","none");
					    $("#divReplacementLoc").css("display", "");
						$("#divRrNo").css("display", "");
						$("#divRepCorpId").css("display", "");
						$("#divRepFirstName").css("display", "");
				
					} else{
						
						$("#divReplacementReq").css("display","");
						 $("#divReplacementLoc").css("display", "none");
						$("#divRrNo").css("display", "none");
						$("#divRepCorpId").css("display", "none");
						$("#divRepFirstName").css("display", "none");
					}
					

				});
		  $('#txtRepCorpID')
		.change(
				function() {
					$
							.getJSON(
									'${checkRepCorpIdExists}',
									{
										repCorpId : $('#txtRepCorpID').val(),
										ajax : 'true'
									},
									function(data) {
										
										if (data == false) {
											alert("Please complete the onboarding process for this resource . ");
											$('#txtRepCorpID').val("");
											$('#txtRepFirstName').val("");
										}
										
										
									});
				}); 
		  
		 
		 $('#txtRepCorpID')
			.change(
					function() {
						$
								.getJSON(
										'${getReplacementNameByCorpID}',
										{
											repCorpId : $('#txtRepCorpID').val(),
											ajax : 'true'
										},
										function(data) {
										 if(data.repFirstName.length) {
												//alert("Please complete the onboarding process for this resource . ");
												$('#txtRepFirstName').val(data.repFirstName);
											}  
											
											 
											 
											 
											 
										});
					}); 
		 
		
		
			



	$("#btnSubmit").click(function() {
		
		
		var startDate = $("#txtJoiningDate").val();
		var endDate = $("#txtActEndDate").val();
		var billingDate = $("#txtBillingDate").val();
		var startDateValue = startDate.split('/');
		var endDateValue = endDate.split('/');
		var billingDateValue = billingDate.split('/');
		var firstDate=new Date();
		firstDate.setFullYear(startDateValue[2],(startDateValue[1] - 1 ),startDateValue[0]);
		var secondDate=new Date();
		secondDate.setFullYear(endDateValue[2],(endDateValue[1] - 1 ),endDateValue[0]);
		var billDate=new Date();
		billDate.setFullYear(billingDateValue[2],(billingDateValue[1] - 1 ),billingDateValue[0]);
		var rrNoVal = $('#txtRrNo').val();
		var replacementCorpId = $('#txtRepCorpID').val();
		var today = new Date();
		
		
		
		if (firstDate > secondDate)
		{
			$("#endDateError").css("display", "");
			$("#endDateError").text("Actual Off-boarding date should be >= On-boarding date");
			return false;
		}
		
		if(billDate > secondDate){
			$("#endDateError").css("display", "");
			$("#endDateError").text("Actual Off-boarding date should be >= Billing Date");
			return false;
		}
	
		if(secondDate < today){
			$("#endDateError").css("display", "");
			$("#endDateError").text("Actual Off-boarding date cannot be in past");
			return false;
			
		}
		
		
		$.getJSON(
				'${checkActualDate}',
				{
				actualDate : $("#txtActEndDate").val(),
						ajax : 'true'
					},
			
					function(data) {
						if (data == true) {
							$("#endDateError").css("display", "");
							
							$("#endDateError").text("Offboarding mails are already sent for today. Cannot set today's date.");
							return false;
						}	
					});
		/* if(planDate.getFullYear() === secondDate.getFullYear() && planDate.getMonth() === secondDate.getMonth() && planDate.getDate() === secondDate.getDate()){
			//then record saved
		}else{
			$("#endDateError").css("display", "");
			$("#endDateError").text("Actual Off-boarding date should be same as planned date");
			return false;
		} */
		 if ($("#txtActEndDate").val() != "") {
			if ($("#drpRollOffType").val() == 0 || $("#drpRollOffType").val() == null) {
				$("#rollOffError").css("display", "");
				$("#rollOffError").text("Reason for roll-off should not be blank");
				return false;
			}
			
			
		} else{
			if ($("#drpRollOffType").val() != 0 ) {
				$("#rollOffError").css("display", "");
				$("#rollOffError").text("Reason for roll-off should be blank");
				return false;
			}
			
			if ($("#drpReplacementType").val() != 0 ) {
				$("#repLocError").css("display", "");
				$("#repLocError").text("Reason for no replacement should be blank");
				return false;
			}
			
			if ($('#checklist').is(':checked')){
				alert("Since actual end date is blank uncheck the replacement flag");
				return false;
			}
		}
		
		
	/* 	if ($("#drpReplacementType").val() == 0 || $("#drpReplacementType").val() == null) {
			$("#repLocError").css("display", "");
			$("#repLocError").text("Reason for no replacement should not be blank");
			return false;
		} */
		
		
		
		if ($('#checklist').is(':checked')){
			
		if (rrNoVal == 0 && replacementCorpId == 0) {
			alert("Since replacement is required, Please revisit to update Replacement RR / Corp id");
			//return false;
		}
	}else{ if ($("#txtActEndDate").val() != ""){
			if ($("#drpReplacementType").val() == 0 || $("#drpReplacementType").val() == null) {
				$("#repLocError").css("display", "");
				$("#repLocError").text("Reason for no replacement should not be blank");
				return false;
			}
		   }
	}
			return true;
	})

	
	if (${employee.actualEndDate eq null}){
    	
    	$("#actualDateLabel").html("Actual Off-boarding Date (dd/mm/yyyy) <span class='req'>*</span>:");
		$("#txtActEndDate").attr("data-validation","required");
		$("#txtActEndDate").attr({"data-validation-help":"Please select"});
		$("#rollOffLabel").html("Reason for Roll-off<span class='req'>*</span>:");
		$('#drpRollOffType').attr("data-validation","required");
		$("#drpRollOffType").attr({"data-validation-help":"Please select"});
		$("#replcmntTypeLabel").html("Reason for No Replacement <span class='req'>*</span>:");
		$('#drpReplacementType').attr("data-validation","required");
		$("#drpReplacementType").attr({"data-validation-help":"Please select"});
		
    }else{
    	
    	$("#actualDateLabel").html("Actual Off-boarding Date (dd/mm/yyyy) <span></span>:");
    	$('#txtActEndDate').removeAttr('data-validation');
    	$("#rollOffLabel").html("Reason for Roll-off<span></span>:");
    	$('#drpRollOffType').removeAttr('data-validation');
    	$("#replcmntTypeLabel").html("Reason for No Replacement <span class='req'></span>:");
    	$('#drpReplacementType').removeAttr('data-validation');
    }
	
	 $("#btnSubmit").click(function() {
			
			
			
		   // $("#drpRollOffType").val('${employee.rollOffType}');
		   // $("#drpReplacementType").val('${employee.replacementType}');
		    /* $("#drpCountry").val('${employee.country.countryId}');
		    $("#txtRepCorpID").val('${employee.repCorpId}');
		    $("#txtRepFirstName").val('${employee.repfirstName}');
		    $("#txtRrNo").val('${employee.rr_no}'); 
		    $("#checklist").val('${employee.replacementRequired}'); */
		    
	}); 
	
	
	
	
			
    $("#drpRollOffType").val('${employee.rollOffType.id}');
    $("#drpReplacementType").val('${employee.replacementType.id}');
    $("#drpCountry").val('${employee.country.countryId}');
    $("#txtRepCorpID").val('${employee.repCorpId}');
    $("#txtRepFirstName").val('${employee.repfirstName}');
    $("#txtRrNo").val('${employee.rr_no}'); 
  
    
});
	
</script>
</body>
</html>
