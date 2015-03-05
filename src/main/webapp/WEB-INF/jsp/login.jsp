<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Enter your credentials</h1>
	
 	<a href="/SecureBankingSystem/new/user_reg">New UseR?</a>
	<form action="j_spring_security_check" method="POST">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="j_username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<tr>
			
			
				<td colspan="5" align="left"><input type="submit" value="Login"/></td>
			
				
			</tr>
		</table>
	</form>
	<font color="red">
		<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
	
	 	<a href="/SecureBankingSystem/forgotpass">Forgot Password?</a>
	 	<c:if test="${not empty status}">
   <h4>${status}</h4>   
</c:if>
	
	
</body>
</html>