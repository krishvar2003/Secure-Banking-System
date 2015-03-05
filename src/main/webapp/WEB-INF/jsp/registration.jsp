<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>User Registration</title>
</head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
    function checkerror(flag) {
        if (flag != 0)
            document.getElementById("button").disabled = true;
        
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
                        var isClick = false;
                        $("#age").val("");
                        $("#phoneno").val("");
                        $("#checkUser").click(function() {
                            isClick = true;
                            //alert("Clicked");
                            
                            
                        })
                        if(isClick==false)
                            document.getElementById("button").disabled = true;
                        $("#name")
                                .on(
                                        'change focus keyup',
                                        function() {
                                            val = $(this).val();
                                            var name = new RegExp(/^[a-zA-Z ]+$/);
                                            if (!name.test(val)) {
                                                document
                                                        .getElementById("errname").innerHTML = "";
                                                document
                                                        .getElementById("errname").innerHTML = "Invalid name. No special characters/numbers/Blank permitted";
                                                errflag = 1;
                                            } else {
                                                document
                                                        .getElementById("errname").innerHTML = "";
                                                document
                                                        .getElementById("errname").innerHTML = "Valid name";
                                                errflag = 0;
                                            }
                                            checkerror(errflag);
                                        })

                        $("#age").on('input', function(event) {
                            this.value = this.value.replace(/[^0-9]/g, '');
                        });
                        $("#age")
                                .on(
                                        'change focus keyup',
                                        function() {
                                            val = $(this).val();
                                            if (val > 100 || val < 18) {
                                                document
                                                        .getElementById("errage").innerHTML = "";
                                                document
                                                        .getElementById("errage").innerHTML = "Age should be less than 100 and greater than 18";
                                                errflag = 1;
                                            } else {
                                                document
                                                        .getElementById("errage").innerHTML = "";
                                                document
                                                        .getElementById("errage").innerHTML = "Valid age";
                                                errflag = 0;
                                            }
                                            checkerror(errflag);
                                        })
                        $("#ssn").on('input', function(event) {
                            this.value = this.value.replace(/[^0-9]/g, '');
                        });
                        $("#ssn")
                                .on(
                                        'change focus keyup',
                                        function() {
                                            val = $(this).val();
                                            if (val.length != 10) {
                                                document
                                                        .getElementById("errssn").innerHTML = "";
                                                document
                                                        .getElementById("errssn").innerHTML = "SSN should be ten digits";
                                                errflag = 1;
                                            } else {
                                                document
                                                        .getElementById("errssn").innerHTML = "";
                                                document
                                                        .getElementById("errssn").innerHTML = "Valid SSN";
                                                errflag = 0;
                                            }
                                            checkerror(errflag);
                                        })
                        $("#phoneno").on('input', function(event) {
                            this.value = this.value.replace(/[^0-9]/g, '');
                        });
                        $("#phoneno")
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

                        $("#email")
                                .on(
                                        'change focus keyup',
                                        function() {
                                            val = $(this).val();
                                            var email = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                                            if (!email.test(val)) {
                                                document
                                                        .getElementById("erremail").innerHTML = "";
                                                document
                                                        .getElementById("erremail").innerHTML = "Invalid Email";
                                                errflag = 1;
                                            } else {
                                                document
                                                        .getElementById("erremail").innerHTML = "";
                                                document
                                                        .getElementById("erremail").innerHTML = "Valid Email";
                                                errflag = 0;
                                            }
                                            checkerror(errflag);
                                        })

                        $("#pin").on('input', function(event) {
                            this.value = this.value.replace(/[^0-9]/g, '');
                        });
                        $("#pin")
                                .on(
                                        'change focus keyup',
                                        function() {
                                            val = $(this).val();
                                            if (val.length != 4) {
                                                document
                                                        .getElementById("errpin").innerHTML = "";
                                                document
                                                        .getElementById("errpin").innerHTML = "Pin should be 4 digits";
                                                errflag = 1;
                                            } else {
                                                document
                                                        .getElementById("errpin").innerHTML = "";
                                                document
                                                        .getElementById("errpin").innerHTML = "Valid Pin number";
                                                errflag = 0;
                                            }
                                            checkerror(errflag);
                                        })

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

                                    	$("#loginid")
        								.on(
        										'change focus keyup',
        										function() {
        											document.getElementById("usr").innerHTML = "";
        											val = $(this).val();
        											var name = new RegExp(/^[a-zA-Z]+[0-9]*-*[0-9a-zA-Z]*_*[0-9a-zA-Z]*$/);
        											if (!name.test(val)) {
        												
        												errflag = 1;
        											} else {
        												
        												errflag = 0;
        											}
        											document.getElementById("button").disabled = false;
        											checkerror(errflag);
        										})
                    })
</script>
<body>
    <spring:htmlEscape defaultHtmlEscape="true" />
    <br />
    <form:form method="POST" modelAttribute="user">
        <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />

        <table cellpadding="5" cellspacing="5">


            <tr>
                <td>Name:
                <td><form:input path="name" htmlEscape="true"
                        placeholder="Enter your Full name" required="required" />&nbsp;&nbsp;&nbsp;<span
                    id="errname"></span></td>
            </tr>

            <tr>
                <td>Gender
                <td>Male<form:radiobutton path="gender" value="M"
                        checked="checked" /> Female<form:radiobutton path="gender"
                        value="F" /></td>
            </tr>
            <tr>
                <td>Address:
                <td><form:input path="address" placeholder="Enter your Address"
                        required="required" /><form:errors path="address"></form:errors></td>
            </tr>
            <tr>
                <td>Age:
                <td><form:input path="age" placeholder="Enter your age"
                        type="number" required="required" />&nbsp;&nbsp;&nbsp;<span
                    id="errage"></span></td>
            </tr>
            <tr>
                <td>SSN:
                <td><form:input path="ssn" placeholder="Enter your SSN"
                        type="password" required="required" />&nbsp;&nbsp;&nbsp;<span
                    id="errssn"></span></td>
            </tr>
            <tr>
                <td>Phone Number:
                <td><form:input path="phoneno"
                        placeholder="Enter your Phone num" value="" required="required" />&nbsp;&nbsp;&nbsp;<span
                    id="errphn"></span></td>
            </tr>
            <tr>
                <td>Email:
                <td><form:input path="email" placeholder="Enter your Email id"
                        required="required" />&nbsp;&nbsp;&nbsp;<span id="erremail"></span></td>
            </tr>
            <tr>
                <td>Pin:
                <td><form:input path="pin" placeholder="Enter your PIN"
                        type="password" required="required" /><span id="errpin"></span></td>
            </tr>
            <tr>
                <td>Password:
                <td><form:input path="password"
                        placeholder="Enter your password" type="password"
                        required="required" /><span id="errpwd"></span></td>
            </tr>
            <tr>
                <td>Role
                <td>User<form:radiobutton path="role" value="User"
                        checked="checked" /> Merchant<form:radiobutton path="role"
                        value="Merchant" /></td>
            </tr>
            <tr>
                <td>Security Question1:
                <td><form:input path="question1"
                        placeholder="First security question" required="required" /></td>
            </tr>
            <tr>
                <td>Answer1:
                <td><form:input path="answer1"
                        placeholder="1st security Question Answer" type="password"
                        required="required" /></td>
            </tr>
            <tr>
                <td>Security Question2:
                <td><form:input path="question2"
                        placeholder="Second security question" required="required" /></td>
            </tr>
            <tr>
                <td>Answer2:
                <td><form:input path="answer2"
                        placeholder="2nd security Question Answer" type="password"
                        required="required" /></td>
            </tr>
            <tr>
                <td>User Name:
                <td><form:input path="loginid"
                        placeholder="Enter a unique username" required="required" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                    type="submit" name="action" value="Check User Name" id="checkUser" />&nbsp;&nbsp;&nbsp;<span id="usr">${usrname}</span><span
					id="errlogin"></span></td>
            </tr>
            <tr>
                <td colspan="12"><input type="submit" name="action" id="button"
                    value="Register" /></td>
            </tr>
            <tr>
            <td><span>${regdone}</span><a href="/SecureBankingSystem/login">Click here to Login</a></td>
            </tr>
        </table>
    </form:form>
    <span>${error}</span>
</body>
</html>
