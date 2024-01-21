<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Flight List</title>
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
	<td><a href="passengerForm">Passenger Form</a></td><td>|</td>
	<td><a href="reservationList">Reservation List</a></td>
	
	<sec:authorize access="isAuthenticated()">
	<td>|</td>
		<br> loggedInUser: ${loggedInUser}
		<td><a href="logout">Logout</a></td>
	</sec:authorize>
	<td></td>
	<td></td>
	</tr>
	</table>
</div>



<body>
	<div align="center">
		<h1>Flight List</h1>
	</div>
	<p></p>
	<p></p>
	<div class="container-sm" align="center">
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<td>Flight Id</td>
					<td>Flight Airport Code</td>
					<td>Flight Arrival City</td>
					<td>Flight Arrival Date</td>
					<td>Flight Arrival Time</td>
					<td>Flight Capacity</td>
					<td>Flight Departure City</td>
					<td>Flight Departure Date</td>
					<td>Flight Departure Time</td>
					<td>Flight Number</td>
					<td>Flight Price</td>
					<td>Flight Seats Booked</td>
				</tr>
			</thead>
			<c:forEach items="${flights}" var="flight">

				<tr>
					<td>${flight.getFlightId() }</td>
					<td>${flight.getFlightAirportCode()}</td>
					<td>${flight.getFlightArrivalCity()}</td>
					<td>${flight.getFlightArrivalDate()}</td>
					<td>${flight.getFlightArrivalTime()}</td>
					<td>${flight.getFlightCapacity()}</td>
					<td>${flight.getFlightDepartureCity()}</td>
					<td>${flight.getFlightDepartureDate()}</td>
					<td>${flight.getFlightDepartureTime()}</td>
					<td>${flight.getFlightNumber()}</td>
					<td>${flight.getFlightPrice()}</td>
					<td>${flight.getFlightSeatsBook()}</td>
					<td><a href="passengerForm?flightId=${flight.getFlightId()}">Make A Reservation</a></td>

					<sec:authorize access="hasAuthority('Admin')">
						<td><a href="updateFlight?flightId=${flight.getFlightId()}">Update</a></td>
						<td><a href="deleteFlight?flightId=${flight.getFlightId()}">Delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>