<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align=center class="textheight">

<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('admin')">
<form method="POST" action="/SecureBankingSystem/internal/viewUserRegistrationRequest">
<table border="1">
	<tr>
		<td>Select</td>
		<td>Name</td>
		<td>Login ID</td>
		<td>Gender</td>
		<td>Address</td>
		<td>Age</td>
		<td>Email</td>
		<td>Phone No</td>
		<td>Role</td>
		<td>Attempts</td>
		</tr>
	<c:forEach items="${userRegReq}" var="UserRegistrationRequest">
		
		<tr>
		<td><input type="checkbox" name="checkedRequests" value="${UserRegistrationRequest.loginid}"></input></td>
			<td>${UserRegistrationRequest.name}</td>
			<td>${UserRegistrationRequest.loginid}</td>
			<td>${UserRegistrationRequest.gender}</td>
			<td>${UserRegistrationRequest.address}</td>
			<td>${UserRegistrationRequest.age}</td>
			<td>${UserRegistrationRequest.email}</td>
			<td>${UserRegistrationRequest.phoneno}</td>
			<td>${UserRegistrationRequest.role}</td>
			<td>${UserRegistrationRequest.attempts}</td>
			
		
		</tr>
	</c:forEach>
</table>
<input type="submit" value="Submit" />
</form>
</sec:authorize>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
</body>
</html>