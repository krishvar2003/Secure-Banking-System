<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/src/main/resources/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Internal Users</title>
</head>
<body>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('admin')">

<div align=center class="textheight">
<a href="/SecureBankingSystem/internal/addRegularInternalUsers"><h3>Add Users</h3></a>
<a href="/SecureBankingSystem/internal/searchRegularInternalUsers"><h3>Search Users</h3></a>
<a href="/SecureBankingSystem/internal/modifyRegularInternalUsers"><h3>Edit Users</h3></a>
<a href="/SecureBankingSystem/internal/deleteRegularInternalUsers"><h3>Delete Users</h3></a>
</div>
</sec:authorize>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>


</body>
</html>