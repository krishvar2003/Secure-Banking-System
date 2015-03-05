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
<a href="/SecureBankingSystem/internal/viewDeleteRequest"><h3>View Delete Requests</h3></a>
<!-- <a href="http://localhost:8080/SecureBankingSystem/internal/viewUserRegistrationRequest"><h3>View User Registration Requests</h3></a> -->
<a href="/SecureBankingSystem/internal/viewLockedAccounts"><h3>View Locked Accounts</h3></a>
</div>

</sec:authorize>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
</body>
</html>