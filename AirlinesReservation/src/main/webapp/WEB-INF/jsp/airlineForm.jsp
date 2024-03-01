<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Airlines Form</title>
<style type="text/css">
.error
(
color
:red
;
)
</style>
</head>
<div align="center">
	<table>
	<tr>
	<td><a href="home">Home</a></td><td>|</td>
	<td><a href="userForm">User Form</a></td><td>|</td>
	<td><a href="roleForm">Role Form</a></td><td>|</td>
	<td><a href="airportForm">Airport Form</a></td><td>|</td>
	<td><a href="airlineForm">Airline Form</a></td><td>|</td>
	<td><a href="flightForm">Flight Form</a></td><td>|</td>
	<td><a href="searchForm">Search Form</a></td><td>|</td>
	<td><a href="searchUserInfoForm">Search User Form</a></td><td>|</td>
	<td><a href="passengerList">Passenger List</a></td><td>|</td>
	<td><a href="reservationList">Reservation List</a></td>
	
	
	<sec:authorize access="isAuthenticated()">
	<td>|</td>
		<br> loggedInUser: ${loggedInUser}
		<br>Granted Authorities: <sec:authentication property="principal.authorities"/>
		
		<td><a href="logout">Logout</a></td>
	</sec:authorize>
	<td></td>
	<td></td>
	</tr>
	</table>
</div>



<body>
	<div align="center">
		<h1>Airlines Form</h1>
		<sec:authorize access="hasAuthority('Admin')">
		<f:form action="saveAirline" modelAttribute="airline">
			<table>
			
				<tr>
					<td>Airline Id:</td>
					<td><f:input path="airlineId" value="${airline.airlineId}"/></td>
					<td><f:errors path="airlineId" cssClass="error" /></td>
				</tr> 
				
				<tr>
					<td>Airline Name:</td>
					<td><f:input path="airlineName" value="${airline.airlineName}"/></td>
					<td><f:errors path="airlineName" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Airline Code:</td>
					<td><f:input path="airlineCode" value="${airline.airlineCode}"/></td>
					<td><f:errors path="airlineCode" cssClass="error" /></td>
				</tr>	
				 			
			</table>
			<tr>
				<td colspan="2" align="center"><input type="submit"   value="Submit" /></td>
			</tr>
		</f:form>
		</sec:authorize>
	</div>
	<p></p>
	<p></p>
	<div class="container-sm" align="center">
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<td>Airline Id</td>
					<td>Airline Name</td>
					<td>Airline Code</td>
				</tr>
			</thead>
			<c:forEach items="${airlines}" var="airline">

				<tr>
					<td>${airline.getAirlineId() }</td>
					<td>${airline.getAirlineName()}</td>
					<td>${airline.getAirlineCode()}</td>
			
					

					<sec:authorize access="hasAuthority('Admin')">
						<td><a href="updateAirline?airlineId=${airline.getAirlineId()}">Update</a></td>
						<td><a href="deleteAirline?airlineId=${airline.getAirlineId()}">Delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>