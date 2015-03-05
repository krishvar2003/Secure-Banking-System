<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<h1>Successful Deletion</h1>
</sec:authorize>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>

</body>
</html>