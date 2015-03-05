<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasAnyRole('admin', 'regularInternal')">
<div align=center class="textheight">

<form method="POST" action="/SecureBankingSystem/DeleteTransactions">
<input type="text" name="txtAccountNo"></input>
<input type="radio" name="status" value="Pending">Pending</input>
<input type="radio" name="status" value="Failed">Failed</input>
<input type="radio" name="status" value="Cleared">Cleared</input>
<input type="submit" value="Submit" />
</form>

</div>

</sec:authorize>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>




</body>
</html>