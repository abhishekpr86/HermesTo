<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
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
<body>

<section class="main-content">
	<form:form  commandName="dashboard" method="POST" id="countryDropdown">
					<div class="pull-left">
						<div class="fieldLbl">
							<label>Country : </label>
						</div>
						<div class="inputfield">
							<form:select path="country" id="drpcountry"
								class="select-control">
								<option value="">-select-</option>
								<c:forEach items="${listCountry}" var="cntry">
									<form:option value="${cntry.countryId}">${cntry.countryName}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>

			</form:form>
		<div class="pull-right graph-wrapper" id="divPyramid">
			<img src="${pageContext.request.contextPath}/resources/images/pyramidChart.png" alt="">
		</div> 
</section>
</body>
</html>
