<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Place request</title>
</head>
<body>
<br/>
<br/>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('Merchant')">
	<form:form method="POST" modelAttribute="merchantRequests">
		<table cellpadding="5" cellspacing="5">

			<tr>
				<td>Customer LoginId:
				<td><form:input path="customerLoginId" htmlEscape="true"
						required="required" />&nbsp;&nbsp;&nbsp;<span
					id="errname"></span></td>
			</tr>

			
			<tr>
				<td>Amount:
				<td><form:input path="amount"
						required="required" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</tr>
			
			<tr>
				<td colspan="12"><input type="submit" name="action" id="button"
					value="Place request" /></td>
			</tr>
		</table>
	</form:form>
</sec:authorize>
<div class="topcorner">
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
</body>
</html>