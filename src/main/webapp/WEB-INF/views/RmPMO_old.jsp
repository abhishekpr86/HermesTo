<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Resource OnBoarding Tool - Add Resource</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/datatables.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/theme.css" />
</head>
<style>
.fieldLbl{
margin-bottom: 5px !important;
}
</style>
<body>
	<header class="main-header">
		<div class="pull-left">
			<h1 class="pull-left">Onboarding Tool</h1>
		</div>
		<nav class="pull-right">
			<ul class="nav-new">
				
				<li><a href="${pageContext.request.contextPath}/dashboard" title="DashBoard">DashBoard</a></li>
			 <c:if test="${checkUserTypeforUM ne null}">
					<li class="reports"><a title="User Management">User Management</a>
					<ul class="reportsSubMenu">
						 <li><a href="${pageContext.request.contextPath}/userManagement" title="userManagement">Add User</a></li>
						<li><a href="${pageContext.request.contextPath}/userSearch" title="userSearch">Delete Role</a></li>
					</ul></li>
			</c:if>
			
			<c:if test="${checkUserType eq 'BundleEM'}">
				<li><a href="${pageContext.request.contextPath}/addPreOnboardRec" title="Resource Pre-Onboarding">Resource Pre-Onboarding</a></li>
			</c:if>
			<c:if test="${(checkUserType eq 'BundleEM') or (checkUserType eq 'RM') or (checkUserTypeforUM ne null)}">
				<li><a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Edit Pre-Onboarding">View/Edit Pre-Onboarding</a></li>
			</c:if>

			
			<c:if test="${(checkUserTypeforUM ne null) or (checkUserType eq 'RM_PMO')}">
					<li class="active"><a href="${pageContext.request.contextPath}/allResourceList" title="view List">View / Edit Resources</a></li>
			</c:if>
			<c:if test="${(checkUserType eq 'ViewMode') or (checkUserType eq 'RM')}">
					<li class="active"><a href="${pageContext.request.contextPath}/allResourceList" title="View List">View Resources</a></li>
			</c:if>
			
			<c:if test="${checkUserTypeforUM ne null}">
					<li><a href="${pageContext.request.contextPath}/employeeSearch" title="Resource Off-boarding">Resource Off-boarding</a></li>
			</c:if>		
				
				<li class="reports"><a  title="Reports">Reports</a>
					<ul style="width:250px" class="reportsSubMenu">
						<li><a href="${pageContext.request.contextPath}/report" title="Report"> Resource Report</a></li>
						<li><a href="${pageContext.request.contextPath}/reportOffBoarding" title="Off Boarding Report">Off Boarding Report</a></li>
						<li><a href="${pageContext.request.contextPath}/hc&fteReport" title="HC&FTEReport">FTE Report</a></li>
						<li><a href="${pageContext.request.contextPath}/dataInconsistencies" title="dataInconsistencies">Data Inconsistencies Report</a></li>
						<li><a href="${pageContext.request.contextPath}/orphanMgr" title="Orphan Report">Orphan Report</a></li>
                        <li><a href="${pageContext.request.contextPath}/replacementEmployees" title="Replacement Employees">Replacement Employees Report</a></li>						
					</ul>
				</li>
				<li><a href="${pageContext.request.contextPath}/main"
					title="Change Password">Change Password</a></li>
				<li><a href="${pageContext.request.contextPath}/logout" title="Logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<section class="main-content">
		<br />
		<h2>Edit Resource</h2>
		<div class="formFields clearfix" id="mainDIV" >
			<div class="reqText">* indicates required fields</div>
			<c:url var="editAction" value="/employee/updatePSAID"></c:url>
			<c:url var="findCountryStateURL" value="/states" />
			<c:url var="findBIS" value="/bis" />
			<c:url var="checkCorpIdExistsEdit" value="/checkCorpIdExistsEdit" />
			<c:url var="checkPsaIdExistsEdit" value="/checkPsaIdExistsEdit" />
			<c:url var="checkEmployeeEmailExistsEdit" value="/checkEmployeeEmailExistsEdit" />
			<c:url var="getDetailsByCorpIDFromActiveDirectoryExits" value="/getDetailsByCorpIDFromActiveDirectoryExits" />
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
			<form:form action="${editAction}" commandName="employee"
				method="POST" id="editEmployee">
				<div class="formRow">
					<div class="col50per">
						<div>
							<form:hidden id="empType" path="empType" />
							<form:hidden id="userReadOnly" path="userReadOnly" />
							<form:hidden id="id" path="id" />		
							<form:hidden id="externalEmpId" path="externalEmpId" />						
							<div class="fieldLbl" id="empID" style="display: block;">
								<label>Emp ID :
								</label>
							</div>
						
							<div class="inputField">
							
								${employee.empId}
							<form:hidden id="txtPSAID" path="empId" />
							
							</div>
						</div>
					</div>
					<div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">CORP ID :
							</label>
							
						</div>
						<div class="inputField">
						
						${employee.corpId}
							<form:hidden id="txtCorpID" path="corpId" />
							
						</div>
					</div>
				</div>
				
				<div class="formRow">
					<div class="col50per">
							<div class="fieldLbl">
								<label>PSA ID <span class="req">*</span>:
								</label>
							</div>
							<div class="inputField">
							<form:input path="psaId" size="8" id="txtPSAIDExt"  data-validation="required alphanumeric" 
							data-validation-help="Please enter alphanumeric data" style="width: 120px;"
							class="input-control" />
							</div>
					</div>
					<div class="col50per">&nbsp;</div>
				</div>
				<div class="formRow" >
					<div class="col50per">
								
							<div class="fieldLbl">
								<label>PSA ID Requested On<span class="req" >*</span>:
								</label>
							</div>
							<div class="inputField">
									<form:input path="requestedDate" size="8" id="reqDate" 
										class="input-control" style="width: 120px;"/>								
								</div>
							
					</div>
					<div class="col50per">
						
							<div class="fieldLbl">
								<label>PSA ID Generated On<span class="req">*</span>:
								</label>
							</div>
								<div class="inputField">
									<form:input path="generatedDate" id="genDate" class="input-control" style="width: 120px;"/>
								</div>
							
					</div>
				</div>
				
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Primary Program : </label>
						</div>
						<div class="inputField">
							
							${employee.primaryprogram.primaryProgramName}
							
							
						</div>
					</div>
				</div>
				
				
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>First Name :
							</label>
						</div>
						<div class="inputField">
						${employee.firstName}
							<form:hidden id="txtFirstName" path="firstName" />
							
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Last Name :
							</label>
						</div>
						<div class="inputField">
						${employee.lastName}
							<form:hidden id="txtLastName" path="lastName" />
						
							
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Email :
							</label>
						</div>
						<div class="inputField">
						${employee.email}
							<form:hidden id="txtEmail" path="email" />
						
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Manager/Supervisor :
							</label>
						</div>
						<div class="inputField">
						${employee.manager.firstName} ${employee.manager.lastName} (${employee.manager.corpId})
					<form:hidden path="manager" id="drpSupervisor" />	
							
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Type :
							</label>
						</div>
						<div class="inputField" >
							
							
							${employee.heritageValue}
			
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Sex :
							</label>
						</div>
						<form:hidden path="empSex" id="rdEmpSex" />	
			
						<c:if test="${employee.empSex == 'M'}">
         					Male
      					</c:if>
						<c:if test="${employee.empSex == 'F'}">
         					Female
      					</c:if>
						
					
					</div>
				</div>
				
				
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Hermes Manager Email :
							</label>
						</div>
						<div class="inputField">
							${employee.manager.email}
					<form:hidden path="managerEmail" id="mgrEmail" />	
							
						
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Capgemini Entity :
							</label>
						</div>
						<div class="inputField">
							
							${employee.cgEntityDetail.cgEntityName}
							
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Country :
							</label>
						</div>
						<div class="inputField">
						
						${employee.country.countryName}
					<form:hidden path="country" id="drpCountry" />	
					
						</div>
					</div>

					<div class="col50per">
						<div class="fieldLbl">
							<label>Location :
							</label>
						</div>
						<div class="inputField">
							${employee.location.stateName}
						<form:hidden path="location" id="drpLocation" />
						
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Local Grade :
							</label>
						</div>
						<div class="inputField">
						
						${employee.grade.name}
						<form:hidden path="grade" id="drpGrade" />
						
							
						</div>
					</div>
			
					<div class="col50per" id="divGlobalGrade" style="display: block;">
						<div class="fieldLbl">
							<label id="GlobalGrade">Global Grade :
							</label>
						</div>
						<div class="inputField">
						
							${employee.globalGrade.name}
								<form:hidden path="globalGrade" id="drpGlobalGrade" />
						</div>
					</div>
					</div>
				
				
				   	 <div class="formRow">
					<div class="col50per">
					<div class="fieldLbl">
							<label>Bundle EM :
							</label>
						</div>
						<div class="inputField">
						${employee.bundleEM.bundleEmName}
						<form:hidden path="bundleEM" id="drpbundleEM" />
						
						
						</div>
						
						
						</div>
		
					 <div class="col50per">
						<div class="fieldLbl">
							<label>EM :
							</label>
						</div>
						<div class="inputField">
						${employee.EM.firstName} ${employee.EM.lastName} (${employee.EM.corpId})
						<form:hidden path="EM" id="drpEM" />
						
					</div>
				</div>
			</div> 
				
				
				<div class="formRow">
			 <div class="col50per" id="divOffShoreEm" style="display: none;">
						<div class="fieldLbl">
							<label>Offshore DM/EM :
							</label>
						</div>
						<div class="inputField">
						
							${employee.offshoreEm.offshoreEmName}
								<form:hidden path="offshoreEm" id="drpoffShoreEm" />
						</div>
						
						
					
				</div>
				
				
		<div class="col50per" id="divphase" style="display: none;">
						<div class="fieldLbl">
							<label>Category :
							</label>
						</div>
						<div class="inputField">
						
							${employee.phasevalue}
					
						</div>
					</div>
				</div>
				<div class="formRow">
				<div class="col50per" id="divresourcemanager" style="display: none;">
						<div class="fieldLbl">

							<label>Resource Manager : 
							</label>
						</div>
						<div class="inputField">
						${employee.resourceManager.resourceMgrName}
						<form:hidden path="resourceManager" id="drpresourceManager" />
						
						
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Primary Skill :
							</label>
						</div>
						<div class="inputField">
						${employee.primaryTechnology.technologyName}
						<form:hidden path="primaryTechnology" id="drpPrimaryTechnology" />
						
							
						</div>
					</div>
					<div class="col50per" id="divVendor" style="display: none;">
						<div class="fieldLbl">
							<label>Vendor :
							</label>
						</div>
						<div class="inputField">
						${employee.vendor.vendorName}
						<form:hidden path="vendor" id="drpVendor" />
						
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Entity :
							</label>
						</div>
						<div class="inputField">
						
							${employee.entity.entityName}
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Date of Birth (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							<fmt:formatDate value="${employee.dob}" pattern="dd/MM/yyyy"/>
							<%-- <form:hidden path="dob" id="txtDOB" /> --%>
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>On-boarding Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
						
						<fmt:formatDate value="${employee.joiningDate}" pattern="dd/MM/yyyy"/>
						
					<%-- 	<form:hidden path="joiningDate" id="txtJoiningDate" /> --%>
						
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Planned Off-boarding Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							
							<fmt:formatDate value="${employee.endDate}" pattern="dd/MM/yyyy" />
							<%-- <form:hidden path="endDate" id="txtEndDate" /> --%>
						
						</div>
					</div>
				</div>
				
						<div class="formRow">
					<div class="col50per" id="txtshadowStartDate" style="display: none;">
						<div class="fieldLbl">
							<label>Shadow Start Date (dd/mm/yyyy) :
							</label>
						</div>
						<div class="inputField">
							<fmt:formatDate value="${employee.shadowStartDate}" pattern="dd/MM/yyyy"/>
						<%-- 	<form:hidden path="shadowStartDate" id="shadowStartDate" /> --%>
						</div>
					</div>
					</div>
				
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>Contact No. :</label>
						</div>
						<div class="inputField">
							${employee.contact}
						</div>
					</div>
					<div class="col50per">
						<div class="fieldLbl">
							<label>Security Training(s) completed :
							</label>
						</div>
						<div class="inputField">
						
						<c:if test="${employee.securityTraining eq false}">
         					No
      					</c:if>
						<c:if test="${employee.securityTraining eq true}">
         					Yes
      					</c:if>
							
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Name :</label>
							<%-- <form:hidden id="spocId" path="spoc" /> --%>
						</div>
						<div class="inputField">
							<form:input path="spocName" id="txtSpocName" readonly="true" style="width: 250px;"/>
						</div>
					</div>
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Onboarding Checklist(s) completed :
							</label>
						</div>
						<div class="inputField">
							
	
							<c:if test="${employee.onboardingChecked eq false}">
         					No
      					</c:if>
						<c:if test="${employee.onboardingChecked eq true}">
         					Yes
      					</c:if>
						</div>
					</div>
				</div>
				 <div class="formRow">
					<div class="col50per">
						<div class="fieldLbl">
							<label>SPOC Email :
							</label>
						</div>
						<div class="inputField">
							<form:input path="spocEmail" id="txtSpocEmail" readonly="true" style="width: 250px; font-size: 100%;"/>
						</div>
					</div>
				<div class="col50per">
						<div class="fieldLbl">
							<label>BIS :
							</label>
						</div>
						<div class="inputField">
							
							${employee.bis.bis_Name}
						</div>
					</div>
				</div>
				
				
				
						
				<div class="formRow">
					
					
					<div class="col50per">
						<div class="fieldLbl">
							<label>Decision validated : </label>
						</div>
						<div class="inputField">
							${employee.decisionVal}
						</div>
					</div>
				</div>
				<div class="formRow">
					<div class="col50per">
						<div>
							
							<div class="fieldLbl" id="empID" style="display: block;">
								<label>PC Hostname 
								</label>
							</div>
							
							<div class="inputField">
								${employee.pcSerialNumber}
							</div>
						</div>
					</div>
					<div class="col50per" id="divCorpId" style="display: block;">
						<div class="fieldLbl">
							<label id="corpID">MAC Address :
							</label>
							
						</div>
						<div class="inputField">
		
							${employee.macAddress}
						
						</div>
					</div>
				</div>
				
				
				</div>
				
				<div class="formRow" style="margin-top:10px;">
						<div class="fieldLbl">
							<label>Comment :</label>
						</div>
						<div class="inputField">
							<form:textarea path="comment" maxlength="200" id="comment"
								class="textarea-control" />
						</div>
				</div>
				
			
	                <div class="text-center">
				 	 <input type="submit" value="Save" title="Save" id="btnSubmit" 
						class="btn-primary" />
					
					 <a href="${pageContext.request.contextPath}/preOnboardingSearch" title="Back" class="btn-primary" style="display:inline-block;">Back</a>
					</div>
			
				
			</form:form>
		</div>
	</section>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.form-validator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/date.js"></script>
	<script type="text/javascript">

$(document).ready(function() 
	    {
	$("#btnSubmit").click(function() {
		$("#txtJoiningDate").val(new Date('${employee.joiningDate}'));//
		$("#txtEndDate").val(new Date('${employee.endDate}'));
	});
	
	var offShoreValueCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(offShoreValueCheck.val()==2){
		$("#divOffShoreEm").css("display", "");
		$("#divphase").css("display", "");
		$("#txtshadowStartDate").css("display", "");

		
	}
		else{
			$("#divOffShoreEm").css("display", "none");
		    $("#divphase").css("display", "none");
		    $("#txtshadowStartDate").css("display", "none");
	}
	
	var resMgrCheck = $("#drpCountry").val('${employee.country.countryId}');
	if(resMgrCheck.val()==1){
		$("#divresourcemanager").css("display", ""); 
	}
	else{
		$("#divresourcemanager").css("display", "none"); 
	}
	
	
			if ($("#userReadOnly").val() == 'true') {
				$("#btnSubmit").css("display","none");
				$('div *').prop('disabled',true);
			}
			if ($("#empType").val() == 'Internal') {
				$("#empID").css("display","");
				$("#psaID").css("display","none");
				$("#divVendor").css("display","none");
				$("#internalToPSA").css("display","");
				$("#corpID")
				.html(
						"CORP ID <span class='req'>*</span>:");
				$("#txtCorpID").attr("data-validation","required");
				$("#GlobalGrade")
				.html(
						"Global Grade :");
				$("#drpGlobalGrade").attr("data-validation","required");
			}
			if($("#empType").val() == 'External') {
				$("#psaID").css("display","");
				$("#internalToPSA").css("display","none");
				$("#empID").css("display","none");				
				$("#divVendor").css("display","");
				$("#corpID")
				.html(
						"CORP ID <span></span>:");
				$("#txtCorpID").removeAttr('data-validation');
				$("#GlobalGrade")
				.html(
						"Global Grade <span></span>:");
				$('#drpGlobalGrade').removeAttr('data-validation');
	
			} 
			$("#drpCountry").val('${employee.country.countryId}');
			$("#drpEntity").val('${employee.entity.entityId}');
			$("#drpType").val('${employee.heritageType}');
			$("#drpphase").val('${employee.phase}');
			$("#empSex").val('${employee.empSex}');
			$("#drpCgEntity").val('${employee.cgEntityDetail.cgEntityId}');
			$("#drpoffShoreEm").val('${employee.offshoreEm.offshoreEmId}');
			$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
			$("#drpprimprogram").val('${employee.primaryprogram.primaryProgramId}');
			
		
			
			/* alert('${employee.offshoreEm.offshoreEmId}'); */
			
			
			
			/* $("#drpOffShoreEm").val('${employee.offshoreEm.offshoreEmId}'); */
			
			
			$("#mgrEmail").val('${employee.manager.email}');
			loadLocation();
			$('#drpSupervisor').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			});
			/* $('#drpbundleEM').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			}); */
			$('#drpEM').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			});
			
			/* 	$('#drpOffShoreEm').change(
					function() {
						$("select option:selected").each(function () {
			                var info = $(this).attr("infomanager");
			                if(info != undefined){
								$("#mgrEmail").val(info);
							}
			              });
			});  */
			
			
			$('#drpCountry')
			.change(
					 function() {
		
						if ($(this).val() == "2") {
							
							$("#divOffShoreEm").css("display", "");
							$("#divphase").css("display", "");
							$("#txtshadowStartDate").css("display", "");
						
						}else{
							$("#divOffShoreEm").css("display", "none");
							$("#divphase").css("display", "none");
							$("#txtshadowStartDate").css("display", "none");
						}
						
		                if ($(this).val() == "1") {

									$("#divresourcemanager").css("display", "");
								} else {
									$("#divresourcemanager").css("display", "none");
								}

		                if ($(this).val() == "1") {

							$("#divresourcemanager").css(
									"display", "");

						} else {

							$("#divresourcemanager").css(
									"display", "none");

						}
		                
							});
			
			$('#drpbundleEM').change(
					 
					function() {
						$.getJSON(
										'${findBIS}',
											{
											id : $(this).val(),
													ajax : 'true'
												},
										
												function(data) {
						
													bisList = data.bisList;
													
													var bisListLength = bisList.length;
													if(bisListLength > 0 ){
														$('#drpbis').val(bisList[0].bis_id).prop('selected', true);
													}
													else{
														$('#drpbis').val('1').prop('selected', true);
													}
													
												});
					});
						
							$('#drpCountry')
									.change(
											function() {
												$
														.getJSON(
																'${findCountryStateURL}',
																{
																	countryId : $(
																			this)
																			.val(),
																	ajax : 'true'
																},
																function(data) {
																	var html = '<option value="">-Select-</option>';
																	var vendorhtml = '<option value="">-Select-</option>';
																	var gradehtml = '<option value="">-Select-</option>';
																	var resmgrhtml = '<option value="">-Select-</option>';
																	$(
																			"#txtSpocName")
																			.val(
																					data.spoc.spocName);
																	$(
																			"#txtSpocEmail")
																			.val(
																					data.spoc.spocEmail);
																	$("#spocId")
																			.val(
																					data.spoc.spocId);
																	stateList = data.stateList;
																	var stateLength = stateList.length;
																	for (var i = 0; i < stateLength; i++) {
																		html += '<option value="' + stateList[i].stateId + '">'
																				+ stateList[i].stateName
																				+ '</option>';
																	}
																	html += '</option>';
																	$(
																			'#drpLocation')
																			.html(
																					html);

																	resourceMgrList = data.resourceMgrList;
																	var resMgrLength = resourceMgrList.length;
																	for (var i = 0; i < resMgrLength; i++) {

																		resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '">'
																				+ resourceMgrList[i].resourceMgrName
																				+ '</option>';
																	}
																	resmgrhtml += '</option>';
																	$(
																			'#drpresourceManager')
																			.html(
																					resmgrhtml);

																	vendorList = data.vendorList;
																	var vendorLength = vendorList.length;
																	for (var i = 0; i < vendorLength; i++) {
																		vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
																				+ vendorList[i].vendorName
																				+ '</option>';
																	}
																	vendorhtml += '</option>';

																	$(
																			'#drpVendor')
																			.html(
																					vendorhtml);

																	gradeList = data.gradeList;
																	var gradeLength = gradeList.length;
																	for (var i = 0; i < gradeLength; i++) {
																		gradehtml += '<option info="' + gradeList[i].globalGradeId + '" value="' + gradeList[i].gradeId + '">'
																				+ gradeList[i].name
																				+ '</option>';
																	}
																	gradehtml += '</option>';

																	$(
																			'#drpGrade')
																			.html(
																					gradehtml);
																});
											});
							$('#drpGrade').change(
									function() {
										var info = $("option:selected", this)
												.attr("info");
										if (info != undefined && info != 0) {
											$("#drpGlobalGrade").val(info);
										} else {
											$("#drpGlobalGrade").val("");
										}
									});
							$("#drpGrade").val('${employee.grade.gradeId}');
							$("#drpGlobalGrade").val(
									'${employee.globalGrade.globalGradeId}');
							$("#drpPrimaryTechnology")
									.val(
											'${employee.primaryTechnology.technologyId}');
							$("#drpSupervisor").val('${employee.manager.id}');
							$("#drpbundleEM").val('${employee.bundleEM.bundleEmId}');
							$("#drpEM").val('${employee.EM.id}');
							$("#drpbis").val('${employee.bis.bis_id}');
							
							
							
							
							$("#drpoffShoreEm")
									.val('${employee.offshoreEm.offshoreEmId}');
							$("#drpresourceManager")
									.val(
											'${employee.resourceManager.resourceMgrId}');
							$("#drpLocation").val(
									'${employee.location.stateId}');
							$("#drpVendor").val('${employee.vendor.vendorId}');

							$.validate({
								form : '#editEmployee',
								modules : 'date'
							});

							$("#btnSubmit")
									.click(
											function() {
												
												var offshoreemidval = $('#drpoffShoreEm').val();
												var countryVal = $("#drpCountry").val();
												if (offshoreemidval == 0 && countryVal == 2){
										
												    alert("Please select correct value for OffshoreEm ");
												    return false;
												}
												    else{
												    	return true;
												    }
												
												
												if ($("#empType").val() == 'Internal') {
													if ($("#txtEndDate").val() != "") {
														$("#txtEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													}
													if ($("#txtActEndDate")
															.val() != "") {
														$("#txtActEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													}

												}
												if ($("#empType").val() == 'External') {
													$("#txtEndDate")
															.attr(
																	{
																		"data-validation" : "date",
																		"data-validation-format" : "dd/mm/yyyy",
																		"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																	});
													if ($("#txtActEndDate")
															.val() != "") {
														$("#txtActEndDate")
																.attr(
																		{
																			"data-validation" : "date",
																			"data-validation-format" : "dd/mm/yyyy",
																			"data-validation-help" : "Please enter date in dd/mm/yyyy format"
																		});
													} else {
														$("#txtActEndDate")
																.val(
																		$(
																				"#txtEndDate")
																				.val());
													}

												}
											});

							$("#btnback").click(function() {
								/* alert("history.back()"); */
							});

						});
						
						

		$('#txtCorpID')
				.change(
						function() {
							$
									.getJSON(
											'${checkCorpIdExistsEdit}',
											{
												corpId : $('#txtCorpID').val(),
												empType  : $('#empType').val(),
												txtPSAID : $('#txtPSAID').val(),
												txthiddenCorpID : $('#txthiddenCorpID').val(),
												ajax : 'true'
											},
											function(data) {
												if (data) {
													if ($("#empType").val() == 'Internal' ) {
														alert("Corp Id already exists . Please try another one . ");
														$('#txtCorpID').val("");
													}
													else {
													alert("Corp Id already exists . Please try another one . ");
													$('#txtCorpID').val("");
												}
												}
											});
						});
		 $('#txtCorpID')
			.change(
					function() {
						$
								.getJSON(
										'${getDetailsByCorpIDFromActiveDirectoryExits}',
										{
											corpId : $('#txtCorpID').val(),
											empType  : $('#empType').val(),
											txtPSAID : $('#txtPSAID').val(),
											txthiddenCorpID : $('#txthiddenCorpID').val(),
											
											ajax : 'true'
										},
										function(data) {
										 if(data) {
											 if(data.email!=null){
												 
													$('#txtFirstName').val(data.firstName);
													$('#txtLastName').val(data.lastName);
													$('#txtEmail').val(data.email);	 
												 }
												 else{
														
														alert("Corp Id does not exists in Active Directory. Please try another one . ");
														$('#txtCorpID').val("");
														 $('#txtFirstName').val("");
														$('#txtLastName').val("");
														$('#txtEmail').val(""); 
													}
												}  
										});
					});
		
		
		// Commented for testing purpose
		 $('#txtPSAIDExt').change(function() {
			$.getJSON('${checkPsaIdExistsEdit}', {
				psaId : $('#txtPSAIDExt').val(),
				empType : $('#empType').val(),
				
				txtPSAID : $('#txtPSAID').val(),
				ajax : 'true'
			}, function(data) {
				if (data) {
					
					if($("#empType").val() == 'Internal'){
						alert("Psa Id already exists . Please try another one . ");
						$('#txtPSAIDExt').val("");
					}
					else{
						alert("Psa Id already exists . Please try another one . ");
						$('#txtPSAIDExt').val("");
					}
				
				}

			});
		}); 

		$('#txtEmail')
				.change(
						function() {
							$
									.getJSON(
											'${checkEmployeeEmailExistsEdit}',
											{
												email : $('#txtEmail').val(),
												
												empType : $('#empType').val(),
												
												txtPSAID : $('#txtPSAID').val(),
												ajax : 'true'
											},
											function(data) {
												if (data) {
													if($("#empType").val() == 'Internal'){
														alert("Email Id already exists . Please try another one . ");
														$('#txtEmail').val("");
													}
													else{
														alert("Email Id already exists . Please try another one . ");
														$('#txtEmail').val("");
													}
													
												}

											});
						});

		function loadLocation() {
			var currentLocCode = '${employee.location.stateId}';
			var currentVenCode = '${employee.vendor.vendorId}';
			var currentResMgrCode = '${employee.resourceManager.resourceMgrId}';

			$
					.getJSON(
							'${findCountryStateURL}',
							{
								countryId : $("#drpCountry").val(),
								ajax : 'true'
							},
							function(data) {
								var html = '<option value="">-Select-</option>';
								var vendorhtml = '<option value="">-Select-</option>';
								var resmgrhtml = '<option value="">-Select-</option>';
								$("#txtSpocName").val(data.spoc.spocName);
								$("#txtSpocEmail").val(data.spoc.spocEmail);
								$("#spocId").val(data.spoc.spocId);

								stateList = data.stateList;
								var stateLength = stateList.length;
								for (var i = 0; i < stateLength; i++) {
									if (stateList[i].stateId == currentLocCode) {
										html += '<option value="' + stateList[i].stateId + '" selected>'
												+ stateList[i].stateName
												+ '</option>';
									} else {
										html += '<option value="' + stateList[i].stateId + '">'
												+ stateList[i].stateName
												+ '</option>';
									}
								}
								html += '</option>';
								$('#drpLocation').html(html);

								resourceMgrList = data.resourceMgrList;
								var resMgrLength = resourceMgrList.length;
								for (var i = 0; i < resMgrLength; i++) {
									if (resourceMgrList[i].resourceMgrId == currentResMgrCode) {
										resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '" selected>'
												+ resourceMgrList[i].resourceMgrName
												+ '</option>';
									} else {
										resmgrhtml += '<option value="' + resourceMgrList[i].resourceMgrId + '">'
												+ resourceMgrList[i].resourceMgrName
												+ '</option>';
									}
								}
								resmgrhtml += '</option>';

								$('#drpresourceManager').html(resmgrhtml);

								vendorList = data.vendorList;
								var vendorLength = vendorList.length;
								for (var i = 0; i < vendorLength; i++) {
									if (vendorList[i].vendorId == currentVenCode) {
										vendorhtml += '<option value="' + vendorList[i].vendorId + '" selected>'
												+ vendorList[i].vendorName
												+ '</option>';
									} else {
										vendorhtml += '<option value="' + vendorList[i].vendorId + '">'
												+ vendorList[i].vendorName
												+ '</option>';
									}
								}
								vendorhtml += '</option>';

								$('#drpVendor').html(vendorhtml);
							})
		};
	</script>
</body>
</html>
