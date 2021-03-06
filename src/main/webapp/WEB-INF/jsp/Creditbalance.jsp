
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
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
											
						$("#amount").on('input', function(event) {
							this.value = this.value.replace(/[^0-9\.]/g, '');
						});						
						
						$("#amount")
								.on(
										'change focus keyup',
										function() {
											val = $(this).val();
											//var email = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
										//var check = /[0-9]*\.[0-9]*$/;
										var check=/^(\d*\.?\d*)$/;
											if (!check.test(val)) {
												document
														.getElementById("erramt").innerHTML = "";
												document
														.getElementById("erramt").innerHTML = "Enter a valid amount!";
												errflag = 1;
											} else {
												document
														.getElementById("erramt").innerHTML = "";
												document
														.getElementById("erramt").innerHTML = "Valid amount";
												errflag = 0;
											}
											checkerror(errflag);
										})
					})
					</script>
</head>
<body>


<c:if test="${not empty failure}">
   <h4>${failure}</h4>   
</c:if>
<spring:message code="user.logged"/>: <sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />
<sec:authorize access="hasAnyRole('User','Merchant')">

<form method="POST" modelAttribute="user">
        <table>
            <tr>
                <td>Amount to be Credited:</td>
                <td><input type="text" name="amount" id="amount" required="required" />&nbsp;&nbsp;&nbsp;<span id="erramt"></span></td>
            </tr>
            <tr>
            
            
                <td colspan="5" align="left"><input type="submit" id="button" value="Credit to Balance"/></td>
            
                
            </tr>
            
           <tr>
            <td>  <h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br /> 
            </td>
            </tr>
           
        </table>
    </form>
</sec:authorize>
</body>

</html>
