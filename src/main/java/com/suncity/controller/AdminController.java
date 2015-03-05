package com.suncity.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.suncity.model.User;
import com.suncity.service.internalUserService;

@Controller
public class AdminController {
	
	@Autowired
	private internalUserService InternalUserService;
	
	@RequestMapping("/internaluser")
	public String User_Access(Map<String, Object> map)
	{
		User user=new User();
		
		map.put("InternalUser",user);
		return "internalUserOperations";
	}
	
	@RequestMapping(value="/internaluser.do", method=RequestMethod.POST)
	public String doActions(@ModelAttribute User InternalUser, BindingResult result, @RequestParam String action, Map<String, Object> map)
	{
		User newInternalUser=new User();
		
		switch(action.toLowerCase())
		{
		case "add":
			InternalUserService.addUser(InternalUser);
			newInternalUser=InternalUser;
			break;
			
		case "modify":
			InternalUserService.editUser(InternalUser);
			newInternalUser=InternalUser;
			break;
			
		case "delete":
			InternalUserService.deleteUser(InternalUser.getLoginid());
			newInternalUser = new User();
			break;
			
		case "search":
	User searchedInternalUser=InternalUserService.findUser(InternalUser.getLoginid());
		newInternalUser = searchedInternalUser!=null ? searchedInternalUser : new User();
		break;			
		}
		
		map.put("InternalUser", newInternalUser);
		return "internalUserOperations";	
	}

}
