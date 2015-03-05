package com.suncity.controller;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*@Controller
public class loginAttemptsContoller implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> 
 {

	@Override
	@RequestMapping(value="/fail2login", method = RequestMethod.GET) 
	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {
		  Object userName = event.getAuthentication().getPrincipal();	
		  System.out.println("heeeeeeeeeeeeeeeeeeeeeeeee "+userName);
			  model.addAttribute("error", "true");  
			  return "denied";  
	}

}*/

/*public interface AuthenticationFailureHandler {

    void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,

            AuthenticationException exception) throws IOException, ServletException;
            {
    	
            }*/

//}