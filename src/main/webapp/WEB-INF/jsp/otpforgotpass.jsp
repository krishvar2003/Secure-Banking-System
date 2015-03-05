<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Change your password here: </title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="<c:url value="/resources/jquery.plugin.js" />"></script>
<script src="<c:url value="/resources/jquery.keypad.js" />"></script>
<script>
$(function () {     
$('#otpval').keypad({target: $('.inlineTarget:first'), 
    layout: $.keypad.qwertyLayout}); 
});
var keypadTarget = null; 
$('.inlineTarget').focus(function() { 
    if (keypadTarget != this) { 
        keypadTarget = this; 
        $('#otpval').keypad('option', {target: this}); 
    } 
});
</script> 
<script>
$(document)
.ready(
        function() {
        	
            $("#password")
            .on(
                    'change focus keyup',
                    function() {
                        val = $(this).val();
                        var pass = /^(?=.*\d{2})(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[!@#$%^])[0-9a-zA-Z!@#$%]{8,}$/;
                        //var pass1= /^(?=.*[!@#$%])$/;
                        if (!pass.test(val) /* || !pass1.test(val) */) {
                            document
                                    .getElementById("errpwd").innerHTML = "";
                            document
                                    .getElementById("errpwd").innerHTML = "Invalid Password. Should contain atleast 1 uppercase,1 lowercase and 1 special character";
                            errflag = 1;
                        } else {
                            document
                                    .getElementById("errpwd").innerHTML = "";
                            document
                                    .getElementById("errpwd").innerHTML = "Valid Password";
                            errflag = 0;
                        }
                        checkerror(errflag);
                    })
                    
                        $("#passwordconfirm")
                                .on(
                                        'change focus keyup',
                                        function() {
                                            val = $(this).val();
                                            var pass = /^(?=.*\d{2})(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[!@#$%^])[0-9a-zA-Z!@#$%]{8,}$/;
                                            //var pass1= /^(?=.*[!@#$%])$/;
                                            if (!pass.test(val) /* || !pass1.test(val) */) {
                                                document
                                                        .getElementById("errpwd").innerHTML = "";
                                                document
                                                        .getElementById("errpwd").innerHTML = "Invalid Password. Should contain atleast 1 uppercase,1 lowercase and 1 special character";
                                                errflag = 1;
                                            } else {
                                                document
                                                        .getElementById("errpwd").innerHTML = "";
                                                document
                                                        .getElementById("errpwd").innerHTML = "Valid Password";
                                                errflag = 0;
                                            }
                                            checkerror(errflag);
                                        })
        })

</script>
</head>
<body>
<c:if test="${not empty status}">
   <h4>${status}</h4>   
</c:if>
<form:form method="POST" action="otpforgotpass" id='resetp'>

        <table>
        <tr><td>Enter your OTP here</td>
            <td><input type="text" name="otpval" id="otpval" required="required" /> 
            </tr>
            <tr>
                <td>Enter new password:</td>
                <td><input type="password" name="password" id="password" required="required"/> 
            </tr>
            
           <tr>
                <td>Confirm password:</td>
                <td><input type="password" name="passwordconfirm" id="passwordconfirm"  required="required" />
            </tr>
            
            <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button1" name="action" value="Continue" value="Complete Critical Transfer!"/></td>
            
                
            </tr>
             <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button2" name="action" value="Resend" value="Resend OTP!"/></td>
            
                
            </tr>
            <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br />
        </table>
    </form:form>

</body>
</html>