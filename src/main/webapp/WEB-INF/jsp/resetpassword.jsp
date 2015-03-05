<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<body>
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
        }

</script>
<c:if test="${not empty status}">
   <h4>${status}</h4>   
</c:if>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasAnyRole('User','Merchant')">
<form:form method="POST" action='resetpassword' id='resetp'>

        <table>
        
        <tr>
                <td>${question1 }</td>
                
            </tr>
            <tr>
            <td><input type="text" name="answer1" id="answer1" /> 
            </tr>
            <tr>
            <td>${question2 }</td>
            </tr>
            <tr>
            <td><input type="text" name="answer2" id="answer2" /> 
            </tr> 
			<tr>
                <td>Enter new password:</td>
                <td><input type="password" name="password" id="password" /> 
            </tr>
            
           <tr>
                <td>Confirm password:</td>
                <td><input type="password" name="passwordconfirm" id="passwordconfirm"  required="required" />
            </tr>
            <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button" value="Change Password!"/></td>
            
                
            </tr>
             <tr>
            <td>  <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
            </td>
            </tr>
        </table>
    </form:form>
</sec:authorize>
</body>
</html>