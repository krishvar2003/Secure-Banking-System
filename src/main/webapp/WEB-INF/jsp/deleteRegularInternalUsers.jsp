<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/src/main/resources/css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Regular Internal User</title>
</head>
<body>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('admin')">
<div align=center class="textheight">

<form method="POST" action="/SecureBankingSystem/internal/deleteRegularInternalUsers">
<input type="text" name="loginID"></input>
<input type="submit" value="Submit" />
</form>

<form:form method="POST" action="/SecureBankingSystem/internal/removeInternalUsers" commandName="InternalUser">
<br>
<table border="1">
	
				<tr>
		<td>Name</td>
			<td><form:input path="name" value="${InternalUser.name}"></form:input></td>
</tr>
<tr>
<td>Loginid</td>
			<td><form:input  path="loginid" value="${InternalUser.loginid}"></form:input></td>
	</tr>
		<tr><td>Gender</td>
			<td><form:input  path="gender" value="${InternalUser.gender}"></form:input></td>
			</tr>
			<tr><td>Address</td>
			<td><form:input path="address" value="${InternalUser.address}"></form:input></td>
</tr>
<tr>
<td>Age</td><td><form:input path="age" value="${InternalUser.age}"></form:input></td>
</tr>
<tr>
<td>Email</td>
<td><form:input path="email" value="${InternalUser.email}"></form:input></td>
</tr>
<tr>
<td>Phoneno</td>
<td><form:input path="phoneno" value="${InternalUser.phoneno}"></form:input></td>
</tr>
<tr>
<td>Role</td>
<td><form:input  path="role" value="${InternalUser.role}"></form:input></td>
</tr>
<tr>
<td>Attempts</td>
<td><form:input  path="attempts" value="${InternalUser.attempts}"></form:input></td>
</tr>
<tr>
<td><input type="submit" name="action" value="Remove"></input></td>

</tr>
<tr>
<td>
<p>${Result}</p>
</td>
</tr>

	
</table>

</form:form>
<td><label name="lblResult" value="${Result}"></label></td>


</div>
</sec:authorize>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</body>
</html>

