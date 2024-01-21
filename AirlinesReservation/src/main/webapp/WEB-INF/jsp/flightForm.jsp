<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Flight Form</title>
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
		<f:form action="saveFlight" modelAttribute="flight">
			<table>
			
				<tr>
					<td>Flight Id:</td>
					<td><f:input path="flightId" value="${flight.flightId}"/></td>
					<td><f:errors path="flightId" cssClass="error" /></td>
				</tr> 
				
				<tr>
					<td>Flight Airport Code:</td>
					<td><f:input path="flightAirportCode" value="${flight.flightAirportCode}"/></td>
					<td><f:errors path="flightAirportCode" cssClass="error" /></td>
				</tr> 
				
				
				<tr>
					<td>Flight Arrival City:</td>
					<td><f:input path="flightArrivalCity" value="${flight.flightArrivalCity}"/></td>
					<td><f:errors path="flightArrivalCity" cssClass="error" /></td>
				</tr>
				
				<tr>
				    <td>Flight Arrival Date:</td>
				    <td><f:input type="date" path="flightArrivalDate" name="flightArrivalDate" value="${flight.getFlightArrivalDate()}"/></td>
				    <td><f:errors path="flightArrivalDate" cssClass="error" /></td>
				</tr>
				
				<tr>
				    <td>Flight Arrival Time:</td>
				    <td><f:input type="time" path="flightArrivalTime" name="flightArrivalTime" value="${flight.getFlightArrivalTime()}"/></td>
				    <td><f:errors path="flightArrivalTime" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Flight Capacity:</td>
					<td><f:input path="flightCapacity" value="${flight.flightCapacity}"/></td>
					<td><f:errors path="flightCapacity" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Flight Departure City:</td>
					<td><f:input path="flightDepartureCity" value="${flight.flightDepartureCity}"/></td>
					<td><f:errors path="flightDepartureCity" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Flight Departure Date:</td><td><f:input type="date"  path="flightDepartureDate" name="flightDepartureDate"  value="${flight.getFlightDepartureDate()}"/></td>
					<td><f:errors path="flightDepartureDate" cssClass="error" /></td>
				</tr>
				
				<tr>
				    <td>Flight Departure Time:</td>
				    <td><f:input type="time" path="flightDepartureTime" name="flightDepartureTime" value="${flight.getFlightDepartureTime()}"/></td>
				    <td><f:errors path="flightDepartureTime" cssClass="error" /></td>
				</tr>
				
		
				
				<tr>
					<td>Flight Number:</td>
					<td><f:input path="flightNumber" value="${flight.flightNumber}"/></td>
					<td><f:errors path="flightNumber" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Flight Price:</td>
					<td><f:input path="flightPrice" value="${flight.flightPrice}"/></td>
					<td><f:errors path="flightPrice" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Flight Seats Booked:</td>
					<td><f:input path="flightSeatsBook" value="${flight.flightSeatsBook}"/></td>
					<td><f:errors path="flightSeatsBook" cssClass="error" /></td>
				</tr>
				
				 			
			</table>
			<tr>
				<td colspan="2" align="center"><input type="submit"   value="Submit" /></td>
			</tr>
		</f:form>
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