<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/jsp/includes.jsp"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Displaying user account history</title>
</head>
<body>

<h1>Your user history is::</h1>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasAnyRole('User','Merchant')">

<div align=center class="textheight">
<table border="1">
	<th>Type</th>
	<th>Transaction Amount</th>
	<th>Old Balance</th>
	<th>New Balance</th>
	<th>Transaction Time</th>
	
	<c:forEach items="${history}" var="transac">
		<tr>
			<td><c:out value="${transac.type}"/></td>
			<td><c:out value="${transac.transamount}"/></td>
			<td><c:out value="${transac.balbefore}"/></td>
			<td><c:out value="${transac.balafter}"/></td>
			<td><c:out value="${transac.time}"/></td>
			
		</tr>
	</c:forEach>
	
</table>
<span>${his}</span>
<h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br />
</div>
</sec:authorize>
</body>
</html>