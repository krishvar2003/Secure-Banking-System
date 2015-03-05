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
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('admin')">
<div align=center class="textheight">
<form method="POST" action="/SecureBankingSystem/ViewCriticalTransactions">
<table border="1">
	<tr>
		<td>Select</td>
		<td>Date</td>
		<td>Sender Account No</td>
		<td>Receiver Account No</td>
		<td>Amount</td>
		</tr>
	<c:forEach items="${pendingTransactions}" var="transaction">
		
		<tr>
		<td><input type="checkbox" name="checkedTransactions" value="${transaction.transactionId}"></input></td>
			<td>${transaction.date}</td>
			<td>${transaction.senderAccountNo}</td>
			<td>${transaction.receiverAccountNo}</td>
			<td>${transaction.amount}</td>
		
		</tr>
	</c:forEach>
</table>
<input type="submit" value="Submit" />
</form>
</div>

</sec:authorize>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>

</body>
</html>