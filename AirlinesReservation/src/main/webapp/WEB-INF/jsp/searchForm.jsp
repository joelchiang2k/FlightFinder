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
<title>Search Form</title>
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
		<h1>Search Form</h1>
		
		<f:form action="saveSearch" modelAttribute="search">
			<table>
				
				<tr>
					<td>Depart From:</td>
					<td><f:input path="searchFlightDepartureCity" value="${search.searchFlightDepartureCity}"/></td>
					<td><f:errors path="searchFlightDepartureCity" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Arrive To:</td>
					<td><f:input path="searchFlightArrivalCity" value="${search.searchFlightArrivalCity}"/></td>
					<td><f:errors path="searchFlightArrivalCity" cssClass="error" /></td>
				</tr>

				
				<tr>
					<td>From:</td><td><f:input type="datetime-local"  path="searchFromDateTime" name="searchFromDateTime"  value="${search.getSearchFromDateTime()}"/></td>
					<td><f:errors path="searchFromDateTime" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>To:</td><td><f:input type="datetime-local"  path="searchToDateTime" name="searchToDateTime"  value="${search.getSearchToDateTime()}"/></td>
					<td><f:errors path="searchToDateTime" cssClass="error" /></td>
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
					<td><a href="passengerForm?flightId=${flight.getFlightId()}">Make A Reservation</a></td>

			
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>