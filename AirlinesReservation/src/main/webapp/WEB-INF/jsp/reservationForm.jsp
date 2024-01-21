<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Reservation Form</title>
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
		<h1>Reservation Form</h1>
		<f:form action="saveReservation" modelAttribute="reservation">
			<table>
			
				<tr>
					<td>Reservation Id:</td>
					<td><f:input path="reservationId" value="${reservation.reservationId}"/></td>
					<td><f:errors path="reservationId" cssClass="error" /></td>
				</tr>
				
				<input type="hidden" name="passengerId" value="${passengerId}" />
				<input type="hidden" name="flightId" value="${flightId}" />
				
				<tr>
					<td>Checked Bags:</td>
					<td><f:input path="checkedBags" value="${reservation.checkedBags}"/></td>
					<td><f:errors path="checkedBags" cssClass="error" /></td>
				</tr>
				
				<tr>
				    <td>CheckedIn:</td>
				    <td>
				        <c:forEach items="${checkedIns}" var="c">
				            <c:choose>
				                <c:when test="${selectedCheckedIn==c}">
				                    <f:radiobutton path="checkedIn" name="checkedIn"  value="${c}" label="${c}" checked="true"/>
				                </c:when>
				                <c:otherwise>
				                    <f:radiobutton path="checkedIn" name="checkedIn"  value="${c}" label="${c}" />
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				    </td>
				    <td><f:errors path="checkedIn" cssClass="error" /></td>
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
					<td>Reservation Id</td>
					<td>Passenger</td>
					<td>Flight</td>
					<td>Checked Bags</td>
					<td>Checked In?</td>
				</tr>
			</thead>
			<c:forEach items="${reservations}" var="reservation">

				<tr>
					<td>${reservation.getReservationId() }</td>
					<td>${reservation.getPassenger().getPassengerFirstName()}</td>
   					<td>${reservation.getFlight().getFlightNumber()}</td>
					<td>${reservation.getCheckedBags()}</td>
					<td>${reservation.getCheckedIn()}</td>
					
					
					

					<sec:authorize access="hasAuthority('Admin')">
						<td><a href="updateReservation?reservationId=${reservation.getReservationId()}">Update</a></td>
						<td><a href="deleteReservation?reservationId=${reservation.getReservationId()}">Delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>