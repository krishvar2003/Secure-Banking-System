package com.suncity.controller;
	import org.apache.log4j.Logger;
import org.apache.log4j.Logger;

import java.io.FileInputStream;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.suncity.model.AccountClass;
import com.suncity.model.User;
import com.suncity.model.UserReg;
import com.suncity.model.request;
import com.suncity.service.ExternalUserService;
import com.suncity.service.UserAccountService;

@Controller


public class LoginController extends SimpleFormController {

	 static Logger log = Logger.getLogger(LoginController.class.getName());
	@Autowired
	private ExternalUserService externalUserService;
	@Autowired
	private UserAccountService usracc;
	org.springframework.security.authentication.encoding.PlaintextPasswordEncoder pass = new org.springframework.security.authentication.encoding.PlaintextPasswordEncoder();
	private User a;
//	@Autowired
//	private Account acc;
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    binder.registerCustomEditor(String.class, new HtmlEscapeStringEditor());
	}
	
	@RequestMapping("/new/user_reg")
	public String User_Registration(Model model)
	{	
		model.addAttribute("user", new User());
		return "registration";
	
	}
	
	
	@RequestMapping("/login")
	public String doLogin(Model model){
		
		return "login";
	}


	@SuppressWarnings("deprecation")
	@RequestMapping(value="/new/user_reg", method=RequestMethod.POST)
	public String doActions( @ModelAttribute @Valid User user, BindingResult errors, @RequestParam String action, Model model, HttpServletResponse response) throws NoSuchAlgorithmException, IOException{
		 if (errors.hasErrors()) {
			System.out.println("entered in error");
			 return "registration";
		 }
		AccountClass acc=new AccountClass();
		String message=null;
		switch(action.toLowerCase()){	//only in Java7 you can put String in switch
		case "register":
			//user.setStartdate("");
			//String password = user.getPassword();
			//user.setPassword(passwordEncoder.encodePassword(password, user.getLoginid()));
			String answer1 = user.getAnswer1();
			String answer2 = user.getAnswer2();
			
			String hashedans1 =  pass.encodePassword(answer1, null);
			String hashedans2 = pass.encodePassword(answer2, null);
			user.setAnswer1(hashedans1);
			user.setAnswer2(hashedans2);
			externalUserService.addUser(user);
			acc.setLoginId(user.getLoginid());
			acc.setBalance(0);
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			acc.setAccountopendate(date);
			usracc.addUserAccountServiceImpl(acc);
			
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);
			KeyPair keyPair = keyGen.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			OutputStream ospub = new FileOutputStream("/root/pub" + user.getLoginid());

			try {
			  ospub.write(publicKey.getEncoded());
			  ospub.flush();
			} finally {
			  ospub.close();
			}
			//remove the code starting from the above comment

			InputStream is = new ByteArrayInputStream(privateKey.getEncoded());
			//InputStream is = new FileInputStream("C:\\Users\\prashanth\\Desktop\\priv" + user.getLoginid() + ".pem");

			response.setContentType("application/pem");      
			response.setHeader("Content-Disposition", "attachment; filename=" + user.getLoginid());
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
			is.close();
			model.addAttribute("regdone","Registration Done.Login again");
			
			break;
		case "check user name":
			String loginiduser=user.getLoginid();
			//System.out.println("login id is "+user.getLoginid());
			 a=externalUserService.checkUser(loginiduser);
			if(a==null)
				 message="Valid user name";
			else
				message="User name already exists.Try another one";
			break;
			
		}
		
		model.addAttribute("usrname", message);
		System.out.println("Done");
		return "registration";
	}
	
	
	@RequestMapping({"/", "/index"})
	public String setupForm(Map<String, Object> map, HttpSession session, SessionStatus status, Model model,HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		//update attempts- for successful login
		//String succlogin = externalUserService.updatecorrectloginattempts();
		//updating userStatus to active
		//String usestat = externalUserService.updateuserstatus();
		
		/*UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		*/
		//check status
		String role = userDetails.getAuthorities().isEmpty() ? null : userDetails.getAuthorities().toArray()[0]
                .toString();
		

		log.info("USER TRYING TO LOG IN - UserName : "+userDetails.getUsername()+", User Role : "+ role);
		//get user status
		String statcheck = externalUserService.getstatus();
		if(statcheck.equals("locked"))
		{
			System.out.println("Came here - user shouldnt login");
			status.setComplete();
			System.out.println("User should have been logged out");
			request.getSession().invalidate();
			SecurityContextHolder.getContext().setAuthentication(null);
			model.addAttribute("status", "Your account has been locked due to more than 5 consecutive bad login attempts! Contact an internal user to unlock account");
			return "login";
			
		}
		//else attempts must be updated for successful attempts of user account is not unlocked
		String succlogin = externalUserService.updatecorrectloginattempts();
		System.out.println(succlogin);
		
		if(role.equals("User")){
			log.info("User logged in");
			return "external-user";
		}
		else if(role.equals("Merchant")){
			log.info("Merchant logged in");
			return "merchant";
		}
		else if(role.equals("admin")){
			log.info("Admin logged in");
			return "internal-user-admin";
		}
		else if(role.equals("regularInternal")){
			log.info("Regular Internal User logged in");
			return "internal-user-regular";
		}
		
		return "norolefound";
	}
	

}
