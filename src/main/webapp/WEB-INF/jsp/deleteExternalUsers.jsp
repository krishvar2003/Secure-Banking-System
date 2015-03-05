<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="hasRole('regularInternal')">
<div align=center class="textheight">

<form method="POST" action="/SecureBankingSystem/external/deleteExternalUsers">
<input type="text" name="loginID"></input>
<input type="submit" value="Submit" />
</form>

<form:form method="POST" action="/SecureBankingSystem/external/removeExternalUsers" commandName="ExternalUser">
<br>
<table border="1">
	
		<th>Name</th>
	
	<th>Gender</th>
	<th>Address</th>
	<th>Age</th>
	<th>Email</th>
	<th>Phoneno</th>
	<th>Role</th>
	<th>Attempts</th>
	
		<tr>
			<td><form:input path="name" value="${ExternalUser.name}"></form:input></td>
			
			<td><form:input  path="gender" value="${ExternalUser.gender}"></form:input></td>
			<td><form:input path="address" value="${ExternalUser.address}"></form:input></td>
<td><form:input path="age" value="${ExternalUser.age}"></form:input></td>
<td><form:input path="email" value="${ExternalUser.email}"></form:input></td>
<td><form:input path="phoneno" value="${ExternalUser.phoneno}"></form:input></td>
<td><form:input  path="role" value="${ExternalUser.role}"></form:input></td>
<td><form:input  path="attempts" value="${ExternalUser.attempts}"></form:input></td>
	
	
<td><input type="submit" name="action" value="Remove"></input></td>

		</tr>
	
</table>
</form:form>
<td><label name="lblResult" value="${lblResult}"></label></td>


</div>
</sec:authorize>
<h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
</body>
</html>