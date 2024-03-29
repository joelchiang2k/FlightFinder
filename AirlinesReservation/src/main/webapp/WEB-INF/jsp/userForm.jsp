<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">


<body>
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


<div align="center">

<h1>User Form</h1>

<sec:authorize access="hasAuthority('Admin')">
<f:form action="saveUser" modelAttribute="user">
<table>
	<tr>
		<td>User Id:</td>
		<td><f:input path="userId" value="${user.userId}"/></td>
		<td><f:errors path="userId" cssClass="error" /></td>
	</tr>

	<tr>
		<td>User Name</td>
		<td><f:input  path="username" value="${user.username}"/></td>
		<td><f:errors path="username" cssClass="error" /></td>
	</tr>

	<tr>
		<td>Password</td>
		<td><f:password   path="password" value="${user.password}"/></td>
		<td><f:errors path="password" cssClass="error" /></td>
	</tr>


	<tr>
		<td>Email</td>
		<td><f:input   path="email" value="${user.email}"/></td>
		<td><f:errors path="email" cssClass="error" /></td>
	</tr>

	<tr>
		<td>Roles</td>
		<td>
			<c:forEach items="${roles}" var="role">
				<c:if test="${retrievedRole.contains(role) }" >
					<f:checkbox path="roles" label="${role.name}" value="${role.roleId}" checked="true"/>
					</c:if>
					
					<c:if test="${!retrievedRole.contains(role) }" >
					<f:checkbox path="roles" label="${role.name}" value="${role.roleId}" />
				</c:if>
			</c:forEach>
		</td>
		<td><f:errors path="roles" cssClass="error" /></td>
	</tr>

<tr>
<td colspan="2" align="center"><input  type="submit" value="submit" class="btn btn-primary"/></td>
</tr>
</table>
</f:form>
</sec:authorize>
</div>

<div align="center">

<table border="1">
<thead>
<tr>
<td>User Id</td>
<td>Name</td>
<td>Email</td>
<td>Roles</td>
<td>Action</td>
</tr>
</thead>

<c:forEach items="${users}" var="user">
<tr>
<td>${user.getUserId() }</td>
<td>${user.getUsername() }</td>
<td>${user.getEmail() }</td>

<td>
<c:forEach items="${user.getRoles()}" var="role">
${role.getName()}

</c:forEach>
</td>
<sec:authorize access="hasAuthority('Admin')">
<td>
<a href="${pageContext.request.contextPath}/update?userId=${user.getUserId() }">Update</a>
<a href="${pageContext.request.contextPath}/delete?userId=${user.getUserId() }">Delete</a>
</td>
</sec:authorize>
</tr>
</c:forEach>

</table>
</div>
</body>
</html>