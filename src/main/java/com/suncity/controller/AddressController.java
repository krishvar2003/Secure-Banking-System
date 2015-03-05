package com.suncity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

import com.suncity.model.User;
import com.suncity.service.ExternalUserService;

@Controller
public class AddressController {
	
	@Autowired
	private ExternalUserService externalUserService;
	
	@RequestMapping("/external/address")
	public String User_address1(Model model)
	{	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		String addr = externalUserService.AddressServiceImpl(loginidreq);
		model.addAttribute("addr",addr);
		
		return "address";
	
	}
	/*
	@RequestMapping(value="/external/address", method=RequestMethod.POST)
	public String Address(@ModelAttribute User user,BindingResult result, @RequestParam String action, Model model)
	
	{   
		
		String addr = externalUserService.AddressServiceImpl(user);
		model.addAttribute("addr",addr);
		return "address"; 
	}
*/
}
