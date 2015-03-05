<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Merchant Payments List</title>
</head>
<body>
<br/>
<br/>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('Merchant')">
<div align=center class="textheight">
<table border="1">
	<th>Request Id</th>
	<th>Customer Id</th>
	<th>Amount</th>
	<th>Status</th>
	<c:forEach items="${paymentList}" var="payment">
		<tr>
			<td>${payment.requestId}</td>
			<td>${payment.customerLoginId}</td>
			<td>${payment.amount}</td>
			<td>${payment.status}</td>
		</tr>
	</c:forEach>
	
</table>
<br><br>
<h3><a href="/SecureBankingSystem/external/paymentList/request">Click to pay each request</a></h3><br />
</div>
</sec:authorize>

<sec:authorize access="hasRole('User')">
<div align=center class="textheight">
<table border="1">
	<th>Request Id</th>
	<th>Merchant Id</th>
	<th>Amount</th>
	<th>Status</th>
	<c:forEach items="${paymentList}" var="payment">
		<tr>
			<td>${payment.requestId}</td>
			<td>${payment.merchantLoginId}</td>
			<td>${payment.amount}</td>
			<td>${payment.status}</td>
		</tr>
	</c:forEach>
</table>
</div>
<h3><a href="/SecureBankingSystem/external/payEachMerchant">Click to pay each request</a></h3><br />
<c:if test="${not empty validateStatus}">
   <h4>${validateStatus}</h4>   
</c:if>
</sec:authorize>
<div class="topcorner">
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
</body>
</html>