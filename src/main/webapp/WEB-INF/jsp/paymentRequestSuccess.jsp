<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/src/main/resources/css/style.css">
<title>Payment Request Success</title>
</head>
<body>
<br/>
<br/>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('Merchant')">
Payment Request submitted!
<h3><a href="/SecureBankingSystem">Go to Home Page</a></h3><br />
</div>
</sec:authorize>
<div class="topcorner">
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
</body>
</html>