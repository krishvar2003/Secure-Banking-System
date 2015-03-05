<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Account</title>
</head>
<body>
<spring:message code="user.logged"/>:<sec:authentication property="name"/> <sec:authentication property="authorities"/><br />
<sec:authorize access="hasAnyRole('User','Merchant')">
<h3><a href="/SecureBankingSystem/external/deletereq">Click to delete your account</a></h3><br />
<span>${ispresent}</span>
<h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
</sec:authorize>
</body>
</html>