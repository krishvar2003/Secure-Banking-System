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

<form method="POST" action="/SecureBankingSystem/internal/viewLockedAccounts">
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
	<c:forEach items="${LockedAccounts}" var="LckAcc">
		
		<tr>
		<td><input type="checkbox" name="checkedRequests" value="${LckAcc.loginid}"></input></td>
			<td>${LckAcc.name}</td>
			<td>${LckAcc.loginid}</td>
			<td>${LckAcc.gender}</td>
			<td>${LckAcc.address}</td>
			<td>${LckAcc.age}</td>
			<td>${LckAcc.email}</td>
			<td>${LckAcc.phoneno}</td>
			<td>${LckAcc.role}</td>
			<td>${LckAcc.attempts}</td>
			
		
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