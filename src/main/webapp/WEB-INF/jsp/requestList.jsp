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
<sec:authorize access="hasRole('Merchant')">
<form:form method="POST" modelAttribute="merchant">
<table border="1">
	<th>Request Id</th>
	<th>Customer Id</th>
	<th>Amount</th>
	<th>Status</th>
	<!--<c:forEach items="${requestList}" var="request">-->
		<tr>
			<td><input  type="text" value="${request.requestId}" name="requestId" readonly /></td>
			<td><input  type="text" value="${request.customerLoginId}" name="customerLoginId" readonly /></td>
			<td><input  type="text" value="${request.amount}" name="amount" readonly /></td>
			<td><input  type="text" value="${request.status}" name="status" readonly /></td>
		</tr>
	<!--</c:forEach>-->
</table>
<br><br>
<input type="submit" name="action" value="Submit to Bank" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="action" value="Reject" />
</form:form>
</sec:authorize>


<sec:authorize access="hasRole('regularInternal')">
<c:if test="${not empty validateStatus}">
   <h4>${validateStatus}</h4>   
</c:if>
<form:form method="POST" modelAttribute="merchant">
<table border="1">
	<th>Request Id</th>
	<th>Merchant Id</th>
	<th>Customer Id</th>
	<th>Amount</th>
	<th>Status</th>
	<c:forEach items="${internalMerchant}" var="merchant">
		<tr>
			<td><input  type="text" value="${merchant.requestId}" name="requestId" readonly /></td>
			<td><input  type="text" value="${merchant.merchantLoginId}" name="merchantLoginId" readonly /></td>
			<td><input  type="text" value="${merchant.customerLoginId}" name="customerLoginId" readonly /></td>
			<td><input  type="text" value="${merchant.amount}" name="amount" readonly /></td>
			<td><input  type="text" value="${merchant.status}" name="status" readonly /></td>
		</tr>
	</c:forEach>
</table>
<br><br>
<!-- <input type="submit" name="action" value="" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="action" value="Reject" />
 -->
 <a href="/SecureBankingSystem/internal/validate">Click to validate each request</a>
 <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br />
 </form:form>
</sec:authorize>


</body>
</html>