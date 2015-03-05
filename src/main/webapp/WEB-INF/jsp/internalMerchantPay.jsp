<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>

	<sec:authorize access="hasRole('regularInternal')">
		<form:form method="POST" modelAttribute="internal">
			<table border="1">
				<th>Request Id</th>
				<th>merchant Id</th>
				<th>Customer Id</th>
				<th>Amount</th>
				<th>Status</th>
				<tr>
					<td><input type="text" value="${merchantTrans.requestId}"
						name="requestId" readonly /></td>
					<td><input type="text" value="${merchantTrans.merchantLoginId}"
						name="merchantLoginId" readonly /></td>
					<td><input type="text" value="${merchantTrans.customerLoginId}"
						name="customerLoginId" readonly /></td>
					<td><input type="text" value="${merchantTrans.amount}"
						name="amount" readonly /></td>
					<td><input type="text" value="${merchantTrans.status}"
						name="status" readonly /></td>
				</tr>
			</table>
			<br>
			<br>
			<input type="submit" name="action" value="Validate & Authorize" />&nbsp;&nbsp;&nbsp;&nbsp;
		</form:form>
		<c:if test="${not empty validateStatus}">
   <h4>${validateStatus}</h4>   
</c:if>
<h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
	</sec:authorize>

</body>
</html>