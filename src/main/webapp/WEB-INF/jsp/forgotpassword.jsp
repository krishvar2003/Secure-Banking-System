<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>You can reset your password after entering the OTP sent to your email ID::</title>
</head>
<body>
<c:if test="${not empty status}">
   <h4>${status}</h4>   
</c:if>
<form:form method="POST" id='resetp'>

        <table>
        
        
            <tr>
            <td>Enter your Login ID to proceed</td>
            <td><input type="text" name="loginid" id="loginid" /> 
            </tr>
            <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button1" name="action" value="Change Password!" /></td>
            
                
            </tr>
            <tr>
            <td>  <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
            </td>
            </tr>
            
            
        </table>
    </form:form>


</body>
</html>