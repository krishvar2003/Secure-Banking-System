<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/src/main/resources/css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage External Users</title>
</head>
<body>
<sec:authorize access="hasRole('regularInternal')">

<div align=center class="textheight">

<a href="/SecureBankingSystem/external/searchExternalUsers"><h3>Search Users</h3></a>
<a href="/SecureBankingSystem/external/modifyExternalUsers"><h3>Edit Users</h3></a>
<a href="/SecureBankingSystem/external/deleteExternalUsers"><h3>Delete Users</h3></a>

</div>
<h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
</sec:authorize>

</body>
</html>