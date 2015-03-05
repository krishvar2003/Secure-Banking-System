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
<script src="../resources/registration.js" type="javascript"></script>
<script>
    function checkerror(flag) {
        if (flag != 0)
            document.getElementById("button").disabled = true;
        else
        	document.getElementById("button").disabled = false;
        
    }

    function failure(item) {
        if (!(item)) {
            document.getElementById("emptyField").innerHTML = "";
            document.getElementById("emptyField").innerHTML = "No fields can be blank";
            document.getElementById("button").disabled = true;
        }
    }
    
    $(document)
	.ready(
			function() {
				var errflag = 0;
				
    $("#phno").on('input', function(event) {
        this.value = this.value.replace(/[^0-9]/g, '');
    });
    $("#phno")
            .on(
                    'change focus keyup',
                    function() {
                        val = $(this).val();
                        if (val.length != 10) {
                            document
                                    .getElementById("errphn").innerHTML = "";
                            document
                                    .getElementById("errphn").innerHTML = "Phone number should be ten digits";
                            errflag = 1;
                        } else {
                            document
                                    .getElementById("errphn").innerHTML = "";
                            document
                                    .getElementById("errphn").innerHTML = "Valid Phone number";
                            errflag = 0;
                        }
                        checkerror(errflag);
                    })
                    
			})

</script>
<body>
<c:if test="${not empty modify}">
   <h4>${modify}</h4>   
</c:if>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasAnyRole('User','Merchant')">
<form:form method="POST" action='modifypii' modelAttribute="user">

        <table>
        
        <%-- <tr>
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
            </tr>  --%>
            
			<tr>
                <td>Update Address:</td>
                <td><input type="text" name="address" id="address" required='required' /> 
            </tr>
            
           <tr>
                <td>Update Phone Number:</td>
                <td><input type="text" name="phno" id="phno" required='required' />&nbsp;&nbsp;&nbsp;<span
                id="errphn"></span></td>
            </tr>
            <tr>
            
                <td colspan="5" align="left"><input type="submit" id="button" value="Modify Information!"/></td>
            
                
            </tr>
            
               
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