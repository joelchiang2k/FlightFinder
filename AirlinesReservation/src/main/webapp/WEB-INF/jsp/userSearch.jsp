<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information Form</title>
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

<div class="container" align="center">
    <h1>Search User</h1>
    <form action="saveUserInfo" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>
        <input type="submit" value="Submit">
    </form>
</div>

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
					<td>Check-In</td>
					<td>Cancel</td>
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
					

					
					<td><a href="reservationForm?passengerId=${passenger.getPassengerId()}">Check-In</a></td>
					<td><a href="deletePassenger?passengerId=${passenger.getPassengerId()}">Cancel Flight</a></td>
					
				</tr>
			</c:forEach>

		</table>
	</div>


</body>
</html>
