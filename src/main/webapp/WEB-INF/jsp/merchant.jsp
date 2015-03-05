<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>Merchant Account Management</title>
</head>
<body>
<br/>
<br/>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('Merchant')">
<div align=center class="textheight">
<h3><a href="/SecureBankingSystem/external/paymentList">Payment List</a></h3><br />
<h3><a href="/SecureBankingSystem/external/paymentRequest">Request Payment</a></h3><br />
<h3><a href="/SecureBankingSystem/external/transfer">Transfer Money</a></h3><br />
<h3><a href="/SecureBankingSystem/external/credit">CREDIT</a></h3><br />
<h3><a href="/SecureBankingSystem/external/debit">DEBIT</a></h3><br />
<h3><a href="/SecureBankingSystem/external/transfer">TRANSFER</a></h3><br />
<h3><a href="/SecureBankingSystem/external/resetpassword">RESET PASSWORD</a></h3><br />
<h3><a href="/SecureBankingSystem/external/viewbalance">VIEW BALANCE</a></h3><br />
<h3><a href="/SecureBankingSystem/external/modifypii">MODIFY PII</a></h3><br />
</div>
</sec:authorize>
<div class="topcorner">
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
</body>
</html>