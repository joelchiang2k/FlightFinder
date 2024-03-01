<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Passenger Form</title>
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
		<h1>Passenger List</h1>
	</div>
	<p></p>
	<p></p>
	<div class="container-sm" align="center">
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<td>Passenger Id</td>
					<td>Passenger First Name</td>
					<td>Passenger Last Name</td>
					<td>Passenger Email</td>
					<td>Passenger Phone No</td>
					<td>Gender</td>
					<td>Passenger DOB</td>
				</tr>
			</thead>
			<c:forEach items="${passengers}" var="passenger">

				<tr>
					<td>${passenger.getPassengerId() }</td>
					<td>${passenger.getPassengerFirstName()}</td>
					<td>${passenger.getPassengerLastName()}</td>
					<td>${passenger.getPassengerEmail()}</td>
					<td>${passenger.getPassengerPhoneNo()}</td>
					<td>${passenger.getPassengerGender()}</td>
					<td>${passenger.getPassengerDOB()}</td>
					

					<sec:authorize access="hasAuthority('Admin')">
						<td><a href="updatePassenger?passengerId=${passenger.getPassengerId()}">Update</a></td>
						<td><a href="deletePassenger?passengerId=${passenger.getPassengerId()}">Delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>