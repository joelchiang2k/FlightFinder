<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Airport Form</title>
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
		<h1>Airport Form</h1>
		<sec:authorize access="hasAuthority('Admin')">
		<f:form action="saveAirport" modelAttribute="airport">
			<table>
			
				<tr>
					<td>Airport Id:</td>
					<td><f:input path="airportId" value="${airport.airportId}"/></td>
					<td><f:errors path="airportId" cssClass="error" /></td>
				</tr> 
				
				
				<tr>
					<td>Airport Code:</td>
					<td><f:input path="airportCode" value="${airport.airportCode}"/></td>
					<td><f:errors path="airportCode" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Airport Name:</td>
					<td><f:input path="airportName" value="${airport.airportName}"/></td>
					<td><f:errors path="airportName" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Airport City:</td>
					<td><f:input path="airportCity" value="${airport.airportCity}"/></td>
					<td><f:errors path="airportCity" cssClass="error" /></td>
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
					<td>Airport Id</td>
					<td>Airport Code</td>
					<td>Airport Name</td>
					<td>Airport City</td>
					<td>Airport Flights</td>
				</tr>
			</thead>
			<c:forEach items="${airports}" var="airport">

				<tr>
					<td>${airport.getAirportId() }</td>
					<td>${airport.getAirportCode()}</td>
					<td>${airport.getAirportName()}</td>
					<td>${airport.getAirportCity()}</td>
					<td><a href="showArrivals?airportCode=${airport.getAirportCode()}">Show Flights</a></td>
					
					

					<sec:authorize access="hasAuthority('Admin')">
						<td><a href="updateAirport?airportId=${airport.getAirportId()}">Update</a></td>
						<td><a href="deleteAirport?airportId=${airport.getAirportId()}">Delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>