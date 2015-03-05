<%@ include file="/WEB-INF/jsp/includes.jsp"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Account Management</title>
</head>
<body>
<br/>
<br/>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasRole('User')">
<div align=center class="textheight">

<c:if test="${not empty status}">
   <h4>${status}</h4>   
</c:if>

<c:if test="${not empty payStatus}">
   <h4>${payStatus}</h4>   
</c:if>

<h3><a href="/SecureBankingSystem/external/credit">CREDIT</a></h3><br />
<h3><a href="/SecureBankingSystem/external/debit">DEBIT</a></h3><br />
<h3><a href="/SecureBankingSystem/external/transfer">TRANSFER</a></h3><br />
<h3><a href="/SecureBankingSystem/external/payMerchant">Pay Merchant</a></h3><br />
<h3><a href="/SecureBankingSystem/external/resetpassword">RESET PASSWORD</a></h3><br />
<h3><a href="/SecureBankingSystem/external/viewbalance">VIEW BALANCE</a></h3><br />
<h3><a href="/SecureBankingSystem/external/modifypii">MODIFY PII</a></h3><br />
<h3><a href="/SecureBankingSystem/external/viewhistory">VIEW ACCOUNT HISTORY</a></h3><br />
<h3><a href="/SecureBankingSystem/external/deleteacc">Delete Account</a></h3><br />

</div>
</sec:authorize>
<div class="topcorner">
<h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
<a href="/SecureBankingSystem/logout"><spring:message code="user.logout"/></a>
</div>
</body>
</html>
