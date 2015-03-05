<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cleared and Failed Transaction</title>
</head>
<body>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasAnyRole('admin', 'regularInternal')">
<div align=center class="textheight">

<h2>Cleared Transactions</h2>
<table border="1">
	<tr>
		<td>Status</td>
		<td>Date</td>
		<td>Sender Account No</td>
		<td>Receiver Account No</td>
		<td>Amount</td>
		</tr>
	<c:forEach items="${clearedTransactions}" var="clearedTransactions">
		
		<tr>
		
		<td>${clearedTransactions.status}</td>
			<td>${clearedTransactions.date}</td>
			<td>${clearedTransactions.senderAccountNo}</td>
			<td>${clearedTransactions.receiverAccountNo}</td>
			<td>${clearedTransactions.amount}</td>
		
		</tr>
	</c:forEach>
</table>

<h2>Failed Transactions</h2>
<table border="1">
	<tr>
		<td>Status</td>
		<td>Date</td>
		<td>Sender Account No</td>
		<td>Receiver Account No</td>
		<td>Amount</td>
		</tr>
	<c:forEach items="${failedTransactions}" var="failedTransactions">
		
		<tr>
		<td>${failedTransactions.status}</td>
			<td>${failedTransactions.date}</td>
			<td>${failedTransactions.senderAccountNo}</td>
			<td>${failedTransactions.receiverAccountNo}</td>
			<td>${failedTransactions.amount}</td>
		
		</tr>
	</c:forEach>
	
	<tr>
            <td>  <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
            </td>
            </tr>
</table>
</div>
</sec:authorize>

<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>


</body>
</html>
