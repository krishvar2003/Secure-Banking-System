<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<c:url value="/resources/jquery.plugin.js" />"></script>
<script src="<c:url value="/resources/jquery.keypad.js" />"></script>
<script>
$(function () {     
$('#otpval').keypad({target: $('.inlineTarget:first'), 
    layout: $.keypad.qwertyLayout}); 
//$('#inlineKeypad').keypad({onClose: function() {
//alert($(this).val());    
//}});
});
</script>
<script>
var keypadTarget = null; 
$('.inlineTarget').focus(function() { 
    if (keypadTarget != this) { 
        keypadTarget = this; 
        $('#otpval').keypad('option', {target: this}); 
    } 
});
</script> 
</head>
<body>
<c:if test="${not empty status}">
   <h4>${status}</h4>   
</c:if>
<form:form method="POST" action='otpcheck' id='resetp'>

        <table>
        
        
            <tr>
            <td><input type="text" name="otpval" id="otpval" /> 
            </tr>
            
            <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button1" name="action" value="Continue" value="Complete Critical Transfer!"/></td>
            
                
            </tr>
             <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button2" name="action" value="Resend" value="Resend OTP!"/></td>
            
                
            </tr>
            <tr>
            <td>
           <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
            </td>
            </tr>
        </table>
    </form:form>


</body>
</html>