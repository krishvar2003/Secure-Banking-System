<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Regular User Account Management</title>
</head>
<body>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('regularInternal')">
<div align=center class="textheight">
<a href="/SecureBankingSystem/external/RedirectManageExternalUsers"><h3>Manage External Users</h3></a>
<a href="/SecureBankingSystem/internal/authorizeMerchant"><h3>Authorize Merchant Payments</h3></a>
<a href="/SecureBankingSystem/ViewCriticalTransactions"><h3>View Critical Transaction Requests</h3></a>
<a href="/SecureBankingSystem/addDeleteTransactions"><h3>Add / Delete Transactions</h3></a>

</div>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
</sec:authorize>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>


</body>
</html>