package com.suncity.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.math.BigInteger;
import java.net.Authenticator;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.suncity.model.AccountClass;
import com.suncity.model.History;
import com.suncity.model.MerchantRequests;
import com.suncity.model.User;
import com.suncity.model.request;
import com.suncity.model.transaction;
import com.suncity.service.ExternalUserService;
import com.suncity.service.UserAccountService;
import com.suncity.service.internalUserService;
import com.suncity.service.MerchantService;
import com.suncity.service.internalUserService;
import com.suncity.service.impl.ExternalUserServiceImpl;


@Controller
public class UserController implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	private ExternalUserService externalUserService;
	@Autowired
	private internalUserService InternalUserService;
	@Autowired
	private UserAccountService accountService;
	
	org.springframework.security.authentication.encoding.PlaintextPasswordEncoder pass = new org.springframework.security.authentication.encoding.PlaintextPasswordEncoder();
	@Autowired
	private MerchantService merchantService;
	SecureRandom random = new SecureRandom();
	
	transaction obj = new transaction();
	User objforgotpass = new User();

	@RequestMapping("/external/credit")
	public String User_credit1(Model model)
	{	

		return "Creditbalance";

	}

	
	@RequestMapping(value="/external/credit", method=RequestMethod.POST)
	public String creditbal(@ModelAttribute User user, @RequestParam double amount, Model model)

	{   

		String msg = externalUserService.creditbalServiceImpl(user, amount);
		model.addAttribute("status", msg);
		return "external-user"; 
	}
	
	@RequestMapping(value="/internal/authorizeMerchant")
	public String authorizeMerchant(Model model)
	{   
		List<MerchantRequests> merchantList = InternalUserService.getMerchantRequests();
		model.addAttribute("internalMerchant", merchantList);
		return "requestList"; 
	}
	
	@RequestMapping(value="/internal/validate")
	public String validateMerchant(Model model)
	{   
		MerchantRequests merchantTrans = InternalUserService.getMerchantRequest();
		if(merchantTrans == null){
			return "noPayments";
		}
		model.addAttribute("merchantTrans", merchantTrans);
		return "internalMerchantPay"; 
	}
	
	@RequestMapping(value="/internal/validate", method=RequestMethod.POST)
	public String validateMerchantPost(@ModelAttribute MerchantRequests internal, @RequestParam String action,  Model model) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IOException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SignatureException, DecoderException
	{   
		if(action.equals("Validate & Authorize")){
			//decrypt the PKI field and get the hashed value using the public key of the user
			//hash the loginid and amount
			//if the values match
			
		/*    
		    byte[] encryptedMsg = "VCswmQ+lHtO0p/s5ypWRtV2LW+5sfPFCQbmy2BPlcp4VsOmmqhPmR3skIHBF07eRC7i5Np7qLlamBub1vUgC6nDh0M8Ge3FnV1o90M/Z+ROgSIoyPVMwvPu26DgWD71o9KZMJZFqpfAMjhiX8KYrbnNRaFBWWGtwum7V8A+iKnk=".getBytes();
		  
		    byte[] decryptedMsg = cipher.doFinal(encryptedMsg);
		    
		    System.out.println(decryptedMsg);
			*/
			//else
			//return "failure";
			
			String toVerifyMerchant = internal.getMerchantLoginId();
			String toVerifyCustomer = internal.getCustomerLoginId();
			double toVerifyAmt = internal.getAmount();
			String toVerifyStatus = internal.getStatus();
			int toVerifyRequestId = internal.getRequesttId();
			
			AccountClass userAccount = accountService.getAccountById(toVerifyCustomer);
			AccountClass merchantAccount = accountService.getAccountById(toVerifyMerchant);
			
			double userBalance = userAccount.getBalance();
			File f = new File("/root/pub" + userAccount.getLoginId());
		//	File f = new File("C:\\Users\\Krishnan\\Downloads\\pub" + userAccount.getLoginId());
		    FileInputStream fis = new FileInputStream(f);
		    DataInputStream dis = new DataInputStream(fis);
		    byte[] keyBytes = new byte[(int)f.length()];
		    dis.readFully(keyBytes);
		    dis.close();
		    X509EncodedKeySpec spec =
		      new X509EncodedKeySpec(keyBytes);
		    long accountNo = userAccount.getAccountno();
		    MerchantRequests dbRequest = merchantService.getUserPaymentById(internal.getRequestId());
		    String pki = dbRequest.getPKI();
		    System.out.println("PKI in internal is : " + pki);
		    String[] pkiList = pki.split("@");
		    String time = pkiList[0];
		    String actualPki = pkiList[1];
		    StringBuilder sb = new StringBuilder(time);
		    sb.append("@");
		    sb.append(accountNo);
		    System.out.println("sb is : " + sb);
		    KeyFactory kf = KeyFactory.getInstance("RSA");
		    PublicKey publicKey = kf.generatePublic(spec);
		    Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(publicKey);
			sig.update(sb.toString().getBytes());
			//byte[] realSig = "[B@4ed6a133".getBytes(Charset.forName("UTF-8"));
			char[] charArr = pkiList[1].toCharArray();
			byte[] realSig = Hex.decodeHex(charArr);
			System.out.println(realSig);
			boolean verifies = sig.verify(realSig);
			if(verifies == true){
				if(Long.parseLong(time) > userAccount.getPkiTime()){
					if(userBalance >= toVerifyAmt){
						userAccount.setBalance(userBalance - toVerifyAmt);
						merchantAccount.setBalance(merchantAccount.getBalance() + toVerifyAmt);
						userAccount.setPkiTime(Long.parseLong(time));
						accountService.editAccount(userAccount);
						accountService.editAccount(merchantAccount);
						internal.setStatus("Approved");
						merchantService.updateStatusserviceimpl(internal);
						model.addAttribute("validateStatus", "Authorized successfully");
					}
					else{
						internal.setStatus("Rejected");
						merchantService.updateStatusserviceimpl(internal);
						model.addAttribute("validateStatus", "Valid signature - Insufficient balance");
					}
				}
				else{
					internal.setStatus("Rejected");
					merchantService.updateStatusserviceimpl(internal);
					model.addAttribute("validateStatus", "Replay attack! suspected! Request not processed");
				}
			}
			else{
				internal.setStatus("Rejected");
				merchantService.updateStatusserviceimpl(internal);
				model.addAttribute("validateStatus", "Invalid signature - Transaction failed!");
			}
			
		}
		
		return "internalMerchantPay";
	}
	
	@RequestMapping("/external/debit")
	public String User_debit1(Model model)
	{	

		return "Debitbalance";

	}

	@RequestMapping(value="/external/debit", method=RequestMethod.POST)
	public String debitbal(@ModelAttribute User user12, @RequestParam double amount,Model model)

	{   
		try {
			String msg = externalUserService.debitbalServiceImpl(user12,amount);
			model.addAttribute("status", msg);
			return "external-user";

		}

		catch(InvalidParameterException ex){
			model.addAttribute("failure","Not Enough Balance to perform tansaction");
			return "external-user";
		}

	}
	/*@Autowired
	private StudentService studentService;
	 */
	
	@RequestMapping("/external/viewhistory")
	public String User_history(Model model)
	{	
		//FETCH ACCOUNT HISTORY FROM HISTORY TABLE and display in a tabular format 
		try{
		List<History> acchistory = externalUserService.fetchhistory();
		model.addAttribute("history", acchistory);
		System.out.println("List size is:" + acchistory.size());
		return "viewhistory";
		}
		catch(Exception e)
		{
		 model.addAttribute("his","No transactions to show");
		 return "viewhistory";
		}
		

	}

	@RequestMapping("/external/transfer")
	public String User_transfer1(Model model)
	{	

		return "Transfer";

	}

	@RequestMapping(value="/external/transfer", method=RequestMethod.POST)
	public String transfer(@ModelAttribute User user,@RequestParam("emailid") String email,  @RequestParam("amount") double amount, Model model)

	{ 
		String retmsg="";
		 String recvactno = externalUserService.getaccountnum(email);
	  	   if(recvactno.equals("invalid"))
	  	   {
	  		   model.addAttribute("failure", "Recipient Not Found! Try again!");
	  		   return "Transfer";
	  	   }
	  	   else //valid recipient
	  	   {
		try {
			if(amount<1000)
			{
			retmsg = externalUserService.TransferServiceImpl(user,email,amount);
			model.addAttribute("failure",retmsg);
			}
			else
			{
				obj.setAmount(String.valueOf(amount));
				obj.setStatus("pending");
				
				 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				 Date date = new Date();
			  	   //System.out.println(dateFormat.format(date));
			  	   String datenow = dateFormat.format(date);
			  	   obj.setDate(datenow);
			  	   obj.setReceiverAccountNo(recvactno);
			  	   //obj.setTransactionId(obj.getTransactionId());
			  	  System.out.println("Transfer amount exceeded critical amount");
				model.addAttribute("status", "Transfer amount exceeds critical amount! An OTP has been generated and sent to your e-mail ID! Please enter the same OTP below:");
				//Generate secure random number
				String str = new BigInteger(130, random).toString(32); 
				System.out.println(str);
				
				//Send e-mail using SMTP
				String to = externalUserService.getemailid();
				System.out.println("OTP Recipient email address is" + to);
				
				String senderacctno = externalUserService.getaccountnum(to);
				obj.setSenderAccountNo(senderacctno);
				 //String to = "swethaa.r11@gmail.com";//change accordingly

			      // Sender's email ID needs to be mentioned
			      String from = "group10suncitybank@gmail.com";//change accordingly
			      final String username = "group10suncitybank";//change accordingly
			      final String password = "5uW8Pkbl4T";//change accordingly

			      // Assuming you are sending email through relay.jangosmtp.net
			      String host = "smtp.gmail.com";

			      Properties props = new Properties();
			      props.put("mail.smtp.auth", "true");
			      props.put("mail.smtp.starttls.enable", "true");
			      props.put("mail.smtp.host", host);
			      props.put("mail.smtp.port", "587");

			      // Get the Session object.
			      Session session = Session.getInstance(props,
			      new javax.mail.Authenticator() {
			         protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);
			         }
			      });

			      try {
			         // Create a default MimeMessage object.
			         Message message = new MimeMessage(session);

			         // Set From: header field of the header.
			         message.setFrom(new InternetAddress(from));

			         // Set To: header field of the header.
			         message.setRecipients(Message.RecipientType.TO,
			         InternetAddress.parse(to));

			         // Set Subject: header field
			         message.setSubject("OTP for Critical Transfer");

			         // Now set the actual message
			         message.setText("Please enter the following One time Password to complete transfer ::"
			            +str);

			         // Send message
			         Transport.send(message);

			         System.out.println("Sent message successfully....");
			        
			  	

			      } catch (MessagingException e) {
			            //throw new RuntimeException(e);
			    	  model.addAttribute("status", "Invalid e-mail address of user, OTP was not sent! Please update email address first!");
			      }
			      
			      String otpins = externalUserService.insertotp(str);
			      System.out.println("Result of inserting OTP:" + otpins);
				 
				return "otp";
				//Need to generate OTP and send 
			}
			System.out.println("Checking return message in controller:" + retmsg);

		}

		catch(InvalidParameterException ex){
			model.addAttribute("failure",retmsg);
			return "Transfer";
		}
	  	   }

		return "Transfer"; 
	}




	@RequestMapping(value="/external/payMerchant")
	public String payMerchant(Model model)

	{ 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}
		
		List<MerchantRequests> payments = merchantService.getUserPaymentsList(userDetails.getUsername());
		model.addAttribute("paymentList", payments);
		return "paymentList"; 
	}

	@RequestMapping(value="/external/payEachMerchant")
	public String payMerchantPost(Model model, String action)

	{ 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}
		try{
		MerchantRequests paymentMer = merchantService.getUserPayment(userDetails.getUsername());
		model.addAttribute("requestList", merchantService.getUserPayment(userDetails.getUsername()));
		return "payMerchant"; 
		}
		catch(Exception e)
		{
			model.addAttribute("nullerror","No requests to show");
			return "payMerchant";
		}
		
	}

	@RequestMapping(value="/external/payEachMerchant", method=RequestMethod.POST)
	public String payMerchantPostSuccess(@ModelAttribute MerchantRequests user123, @RequestParam String action, @RequestParam String pki, Model model )

	{ 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
	//	String pay1 = request.getParameter("textValue");
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}
		switch(action.toLowerCase()){
		case "pay":
		{
			System.out.println("asdsdfsdf");
			System.out.println(user123.getRequestId());
			user123.setCustomerLoginId(userDetails.getUsername());
			user123.setStatus("CustomerApproved");
			user123.setPKI(pki);
			System.out.println("pki is "+ pki);
			System.out.println(user123.getStatus());
			merchantService.updateStatusserviceimpl(user123);
			System.out.println("enter");
			model.addAttribute("payStatus", "Payment Submitted");
			break;
		}
		case "reject":{
			user123.setCustomerLoginId(userDetails.getUsername());
			user123.setStatus("CustomerRejected");
			model.addAttribute("payStatus", "Payment Rejected");
			merchantService.updateStatusserviceimpl(user123);
		}
		}
		return "external-user"; 
	}


	@RequestMapping(value="/external/resetpassword", method=RequestMethod.POST)
	public String validateans(@ModelAttribute User user, @RequestParam("answer1") String answer1,@RequestParam("answer2") String answer2, @RequestParam("password") String pwd,  @RequestParam("passwordconfirm") String pwdconfirm, Model model)
	{   String msg = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	UserDetails userDetails = null;
	if (!(auth instanceof AnonymousAuthenticationToken)) {
	     userDetails = (UserDetails) auth.getPrincipal();
	}
	
	
	/*UserDetails userDetails =
			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	*/String role = userDetails.getAuthorities().isEmpty() ? null : userDetails.getAuthorities().toArray()[0]
            .toString();
		String retmsg;
		String getans = externalUserService.getans();
		String answers[] = getans.split("#");
		String hashedans1 = pass.encodePassword(answer1, null);
		String hashedans2 = pass.encodePassword(answer2, null);
		if(hashedans1.equals(answers[0]) && hashedans2.equals(answers[1]) )
		{
			System.out.println("Security Answers match!");
			if(pwd.equals(pwdconfirm))
			{
				System.out.println("Both passwords match");
				retmsg = externalUserService.resetpwd(pwd);
				model.addAttribute("status", retmsg);
				//return "external-user";
			}
			else
			{
				model.addAttribute("status", "Passwords dont match!");
				//return "external-user";
			}

		}
		else
		{
			model.addAttribute("status", "Security Answers don't match!");
			//return "external-user";
		}

		
		if(role.equals("User")){
			 msg ="external-user";
		}
		else if(role.equals("Merchant")){
			 msg ="merchant";
		}
		return msg;
	}

	@RequestMapping("/external/resetpassword")
	public String User_reset1(Model model)
	{	
		//have to get security questions and return to jsp page
		//call service Dao to return security questions and answers
		String retmsg="";
		retmsg = externalUserService.secqans();	
		//split retmsg to get both security questions
		String quesns[] = retmsg.split("#");
		System.out.println("Trying to print: " + quesns[0] + quesns[1]);
		model.addAttribute("question1", quesns[0]);
		model.addAttribute("question2", quesns[1]);

		return "resetpassword";

	}
	
	@RequestMapping("/forgotpass")
	public String User_forgotpwd(Model model)
	{
		System.out.println("Came into forgot password");

		return "forgotpassword";

	}
	
	@RequestMapping(value="/forgotpass", method=RequestMethod.POST)
	public String User_forgotpwd2(Model model, @RequestParam("loginid") String loginid)
	{	
		System.out.println("LoginID obtained is:" + loginid);
		objforgotpass.setLoginid(loginid);
		//need to check if login ID exists and generate OTP
		String check = externalUserService.checkUsername(loginid);
		System.out.println("Simply");
		System.out.println(check);
		if(check.equals("failure"))
		{
			model.addAttribute("status", "No user found with entered username!");
			return "login";
			
		}
		else
		{
			System.out.println("User found!");
			//have to generate OTP and send e-mail ID
			//String check has the user e-mail ID
			//Generate secure random number
			String str = new BigInteger(130, random).toString(32); 
			System.out.println(str);
		      String from = "group10suncitybank@gmail.com";
		      final String username = "group10suncitybank";
		      final String password = "5uW8Pkbl4T";

		      
		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");

		      // Get the Session object.
		      Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });

		      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(check));

		         // Set Subject: header field
		         message.setSubject("OTP for Critical Transfer");

		         // Now set the actual message
		         message.setText("Please enter the following One time Password to complete transfer ::"
		            + str);

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent message successfully....");
		        
		  	

		      } catch (MessagingException e) {
		            //throw new RuntimeException(e);
		    	  model.addAttribute("status", "Invalid e-mail address of user, OTP was not sent! Please update email address first!");
		      }
		      
		      String otpins = externalUserService.insertforgotpassotp(str, loginid);
			model.addAttribute("status", "An OTP has been generated and sent to your email ID. Please enter the same OTP to authenticate yourself and proceed!");
			return "otpforgotpass";
		}		

	}
	@RequestMapping("/otpforgotpass")
	public String User_forgotpwd6(Model model)
	{

		return "otpforgotpass";

	}
	
	@RequestMapping(value="/otpforgotpass", method=RequestMethod.POST)
	public String User_forgotpwd9(Model model, @RequestParam("otpval") String otptocheck, @RequestParam String action, @RequestParam("password") String pwd,  @RequestParam("passwordconfirm") String pwdconfirm)
	{
		System.out.println("Came into forgot password POSt method to update password");
		String loginidreqd = objforgotpass.getLoginid();
		switch(action.toLowerCase()){
		case "continue":
		{
			//validate OTP with the one in DB
			String msg = externalUserService.checkotpforgotpass(otptocheck, loginidreqd);
			System.out.println("Time elapsed:" + msg);
			String msgsplit[] = msg.split("#");
			if(Integer.parseInt(msgsplit[0]) <= 15)
			{
				System.out.println("OTP is still valid");
				if(msgsplit[1].equals("yes"))
						{
					System.out.println("OTP validated!");
					
					if(pwd.equals(pwdconfirm))
					{
						//proceed to RESET password now
						String retmsg = externalUserService.forgotpasswordchange(loginidreqd, pwd);						
						model.addAttribute("status", "Your password has been successfully changed!");
						return "login";
					}
					else
					{
						model.addAttribute("status", "Passwords don't match! Try again !");
						return "otpforgotpass";
					}
					
						}
				else
				{
					model.addAttribute("status", "Incorrect OTP entered! Please try again or choose to resend a new OTP");
					return "otpforgotpass";
				}
			}
			else
			{
				model.addAttribute("status", "OTP has expired! Please prompt for resending OTP to complete transfer!");
				return "otpforgotpass";
			}
			
		}
		case "resend":
		{
			//generate a new OTP and send 
			String str = new BigInteger(130, random).toString(32); 
			System.out.println(str);
			
			//Send e-mail using SMTP
			String to = externalUserService.checkUsername(loginidreqd);
			System.out.println("OTP Recipient email address is" + to);
			

		      // Sender's email ID needs to be mentioned
		      String from = "group10suncitybank@gmail.com";//change accordingly
		      final String username = "group10suncitybank";//change accordingly
		      final String password = "5uW8Pkbl4T";//change accordingly

		      // Assuming you are sending email through relay.jangosmtp.net
		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");

		      // Get the Session object.
		      Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });

		      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));

		         // Set Subject: header field
		         message.setSubject("OTP for Critical Transfer");

		         // Now set the actual message
		         message.setText("Please enter the following One time Password to complete transfer :"
		            + str);

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent message successfully....");
		        
		  	

		      } catch (MessagingException e) {
		            //throw new RuntimeException(e);
		    	  model.addAttribute("status", "Invalid e-mail address of user, OTP was not sent! Please update email address first!");
		      }
		      
		      String otpins = externalUserService.insertforgotpassotp(str, loginidreqd);
		      System.out.println("Result of inserting OTP:" + otpins);
			model.addAttribute("status", "A new OTP has been resent to you. Please try again! If this problem persists, please check and modify your e-mail address if required.");
			return "otpforgotpass";
			
		}
		}
		return "otpforgotpass";

	}
	
	@RequestMapping(value="/external/otpcheck", method=RequestMethod.POST)
	public String otpcheck(@ModelAttribute User user,@RequestParam("otpval") String otptocheck, @RequestParam String action, Model model)

	{   
		//System.out.println("Came into form handler for otp checking");
		switch(action.toLowerCase()){
		case "continue":
		{
			//validate OTP with the one in DB
			System.out.println("Need to complete transfer");
			String msg = externalUserService.validateotp(otptocheck);
			System.out.println("Time elapsed:" + msg);
			String msgsplit[] = msg.split("#");
			if(Integer.parseInt(msgsplit[0]) <= 15)
			{
				System.out.println("OTP is still valid");
				if(msgsplit[1].equals("yes"))
						{
					System.out.println("OTP validated!");
					//call service submit a request for transfer
					String transreq = externalUserService.submittransreq(obj);
					System.out.println("Transfer request insertion status:" + transreq);
					model.addAttribute("status", "Critical Transfer Request Submitted!");
					return "external-user";
						}
				else
				{
					model.addAttribute("status", "Incorrect OTP entered! Please try again or choose to resend a new OTP");
					return "otp";
				}
			}
			else
			{
				model.addAttribute("status", "OTP has expired! Please prompt for resending OTP to complete transfer!");
				return "otp";
			}
			
		}
		case "resend":
		{
			//generate a new OTP and send 
			String str = new BigInteger(130, random).toString(32); 
			System.out.println(str);
			
			//Send e-mail using SMTP
			String to = externalUserService.getemailid();
			System.out.println("OTP Recipient email address is" + to);
			
			String senderacctno = externalUserService.getaccountnum(to);
			obj.setSenderAccountNo(senderacctno);
			 //String to = "swethaa.r11@gmail.com";//change accordingly

		      // Sender's email ID needs to be mentioned
		      String from = "group10suncitybank@gmail.com";//change accordingly
		      final String username = "group10suncitybank";//change accordingly
		      final String password = "5uW8Pkbl4T";//change accordingly

		      // Assuming you are sending email through relay.jangosmtp.net
		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");

		      // Get the Session object.
		      Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });

		      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(to));

		         // Set Subject: header field
		         message.setSubject("OTP for Critical Transfer");

		         // Now set the actual message
		         message.setText("Please enter the following One time Password to complete transfer :"
		            + str);

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent message successfully....");
		        
		  	

		      } catch (MessagingException e) {
		            //throw new RuntimeException(e);
		    	  model.addAttribute("status", "Invalid e-mail address of user, OTP was not sent! Please update email address first!");
		      }
		      
		      String otpins = externalUserService.insertotp(str);
		      System.out.println("Result of inserting OTP:" + otpins);
			model.addAttribute("status", "A new OTP has been resent to you. Please try again! If this problem persists, please check and modify your e-mail address if required.");
			return "otp";
			
		}
		
		}
		return "otp";
		
	}

	@RequestMapping("/external/viewbalance")
	public String User_viewbal1(Model model)
	{	

		return "ViewBalance";

	}

	@RequestMapping(value="/external/viewbalance", method=RequestMethod.POST)
	public String viewbal(@ModelAttribute User user,Model model)

	{   String balance="";

	balance=externalUserService.viewbalServiceImpl(user);
	String msgsplit[] = balance.split("#");
	
	model.addAttribute("accno", msgsplit[0]);
	model.addAttribute("bal",msgsplit[1]);
	return "ViewBalance"; 
	}
	
	@RequestMapping("/external/modifypii")
	public String modifypii11(Model model)
	{	

		return "modifydetails";

	}

	@RequestMapping("/external/deleteacc")
	public String deleteacc()
	{
		
		return "deleteaccount";
	}
	
	@RequestMapping("/external/deletereq")
	public String deleteRequest(@ModelAttribute request req,Model model)
	{
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
				userDetails = (UserDetails) auth.getPrincipal();
			}
		String a=externalUserService.checkDeleteRequest(userDetails.getUsername());
		if(a=="null")
		{
			externalUserService.deleteRequest(userDetails.getUsername(),req);
			model.addAttribute("isempty", "Request successfully deleted");
		}
		else
			model.addAttribute("isempty", "Cannot place another delete request");
		
		return "requestMessage";
	}
	

	@RequestMapping(value="/external/modifypii", method=RequestMethod.POST)
	public String modifypii(@ModelAttribute User user,@RequestParam("address") String address,@RequestParam("phno") Long phno,Model model)

	{   

	externalUserService.modifypiiServiceImpl(user,address,phno);
	model.addAttribute("modify", "Updated Successfully");
	return "modifydetails"; 
	}


	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		 String userName = event.getAuthentication().getPrincipal().toString();
        // unamefail = userName.toString();
		 //update attempts in DB
		 String msg = externalUserService.updateloginattempts(userName);
		 System.out.println(msg);
        
         System.out.println("Failed login using USERNAME [" + userName + "]");
         //call another function to return 
         //badlogin();
		
	}


	

}

