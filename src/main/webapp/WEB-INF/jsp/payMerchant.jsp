<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="<c:url value="/resources/jsencrypt.js" />"> </script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>

</head>
<body>

	<sec:authorize access="hasRole('User')">
		<form:form method="POST" modelAttribute="user123">
			<table border="1">
				<th>Request Id</th>
				<th>Customer Id</th>
				<th>Amount</th>
				<th>Status</th>
				
				<tr>
					<td><input type="text" value="${requestList.requestId}"
						name="requestId" readonly /></td>
					<td><input type="text" id="custid" value="${requestList.merchantLoginId}"
						name="merchantLoginId" readonly /></td>
					<td><input type="text" id="amt" value="${requestList.amount}"
						name="amount" readonly /></td>
					<td><input type="text" value="${requestList.status}"
						name="status" readonly /></td>
					<!-- <input type="hidden" id="PKI" name="PKI" /> -->
				</tr>
			</table>
			<br>
			Enter signature here <input type="text" name="pki"></input>
			<br><br><br>
			<input type="submit" name="action" id="butn" value="Pay"  /><input type="submit" name="action"  value="Reject"  />
				<span id="errmsg">${nullerror}</span>
		</form:form>
		<h3></h3><a href="/SecureBankingSystem/">Go to Home page</a></h3>
		<br>
		<br>
		<!-- <label for="files">Select the private key: </label>
		<input id="fileInput" name="fileInput" type="file" required="required" />
		<output id="list"></output> -->
	</sec:authorize>
	
	<sec:authorize access="hasRole('regularInternal')">
		<form:form method="POST" modelAttribute="internalMerchant">
			<table border="1">
				<th>Request Id</th>
				<th>Merchant Id</th>
				<th>Customer Id</th>
				<th>Amount</th>
				<th>Status</th>
				<tr>
					<td><input type="text" value="${merchantTrans.requestId}"
						name="requestId" readonly /></td>
					<td><input type="text" value="${merchantTrans.merchantLoginId}"
						name="requestId" readonly /></td>
					<td><input type="text" value="${merchantTrans.customerLoginId}"
						name="merchantLoginId" readonly /></td>
					<td><input type="text" value="${merchantTrans.amount}"
						name="amount" readonly /></td>
					<td><input type="text" value="${merchantTrans.status}"
						name="status" readonly /></td>
				</tr>
			</table>
			<br>
			<br>
			<input type="submit" name="action" value="validate & Authorize" />&nbsp;&nbsp;&nbsp;&nbsp;
		</form:form>
	
		<h3><a href="/SecureBankingSystem/">Go to Home Page</a></h3><br />
	</sec:authorize>
	<script>
if(($("#errmsg")).html()!="")
{
 document.getElementById("butn").disabled = true;
}

</script>
	<!-- <script>
	function stripquotes(a) {
	    if (a.charAt(0) === '"' && a.charAt(a.length-1) === '"') {
	        return a.substr(1, a.length-2);
	    }
	    return a;
	}
	var text;var elem;
	$( document ).ready(function() {
		
		function handleFileSelect(evt) {
			//var encrypt = new JSEncrypt();
			var files = evt.target.files; // FileList object
			// files is a FileList of File objects. List some properties.
			var output = [];
			var f = files[0];
			output.push('Filename:', f.name, '<br>Size:', f.size,
					'bytes<br>Last modified:',
					f.lastModifiedDate ? f.lastModifiedDate
							.toLocaleDateString() : 'n/a');
			var reader = new FileReader();
			reader.onload = function(e) {	
				 text = e.target.result;
			
				
				// text=text.replace(/ /g,'');
				 var encrypt = new JSEncrypt();
				//text=stripquotes(text);
			      encrypt.setPrivateKey(text);
			      var customerIDtemp=document.getElementById("custid").value;
			      var amttemp=document.getElementById("amt").value;
			      var input1=customerIDtemp+amttemp;
			      var hash = CryptoJS.MD5(input1);

			      var encrypted = encrypt.encrypt(hash);
			     /*  var encrypted = CryptoJS.TripleDES.encrypt(input1, text);
			      var decrypted = CryptoJS.DES.decrypt(encrypted, text); */
			     var a="hello";
			     elem = document.getElementById("PKI");
				 elem.value=encrypted;
				
				// var decrypt = new JSEncrypt();
			     // decrypt.setPrivateKey(text);
			     // var uncrypted = decrypt.decrypt(eee);
			      var b="";
			}
			
			reader.readAsText(f);
			document.getElementById('list').innerHTML = '<ul>'
					+ output.join('') + '</ul>';
		}
		
		document.getElementById('fileInput').addEventListener('change',
				handleFileSelect, false);
	})
<<<<<<< HEAD
		
		/* function sendValue(){  
		    $.ajax({  
		        type : 'POST',  
		        url : "/external/payEachMerchant",  
		        data : "textValue="+ text ,
		        error: function(XMLHttpRequest, textStatus, errorThrown) { 
		            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
		            console.log(text);
		        }       ,
		        success : function(result) {  
		        	alert("result" + result);
		            console.log("Hello");
		        }  
		    });  
		}   */
	</script> -->

</body>
</html>
