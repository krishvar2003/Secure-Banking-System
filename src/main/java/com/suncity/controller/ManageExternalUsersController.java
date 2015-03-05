package com.suncity.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.suncity.formbean.UserFormDisplayBean;
import com.suncity.model.User;
import com.suncity.service.ExternalUserService;
import com.suncity.service.internalUserService;

@Controller
@RequestMapping("/external")
public class ManageExternalUsersController {
	@Autowired
	private ExternalUserService externalUserService;
	
	
	@RequestMapping({"/RedirectManageExternalUsers", "/"})
	public String redirectManageExternalUsers(Map<String, Object> map)
	{
		
		return "ManageExternalUsers";
	}
	
	@RequestMapping("/searchExternalUsers")
	public String User_Search(Map<String, Object> map)
	{	map.put("ExternalUser", new User());
		return "searchExternalUsers";
	}
	
	@RequestMapping(value="/searchExternalUsers", method=RequestMethod.POST)
	public String User_SearchPOST(@RequestParam("loginID") String loginID, Map<String, Object> map)
	{
		User user=externalUserService.findUser(loginID);
		
		if(user==null)
		{
			map.put("Result", "No User Found");
			return "ErrorPage";
		}
		//check if user role is external//
		//dont return if the role is admin or regular internal as regular employee accessing this page//
		else
		{
		map.put("ExternalUser", user);
		return "searchExternalUsers";
		}
	}
	
	
	@RequestMapping("/deleteExternalUsers")
	public String User_Delete(Map<String, Object> map)
	{	map.put("ExternalUser", new User());
	String lblResult="";
	map.put("lblResult", lblResult);
		return "deleteExternalUsers";
	}
	

	@RequestMapping(value="/deleteExternalUsers", method=RequestMethod.POST)
	public String User_DeletePOST(@RequestParam("loginID") String loginID, Map<String, Object> map, HttpServletRequest request)
	{
		//this is only for find//
		User user=externalUserService.findUser(loginID);
		if(user==null)
		{
			map.put("Result", "No User Found");
			return "ErrorPage";
		}
		else
		{
			
		
		HttpSession session = request.getSession();
		session.setAttribute("loginId", user.getLoginid());
	
		//check if user role is external//
				//dont return if the role is admin or regular internal as regular employee accessing this page//
				
		map.put("ExternalUser", user);
		String lblResult="";
		map.put("lblResult", lblResult);
		return "deleteExternalUsers";
		}
	}
	
	@RequestMapping(value="/removeExternalUsers", method=RequestMethod.POST)
	public String Remove_UserPOST(@ModelAttribute UserFormDisplayBean FormBeanExternalUser, BindingResult result, @RequestParam String action, Map<String, Object> map, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String loginId=session.getAttribute("loginId").toString();
		
		
		//actual deletion here//
		String resultFunc=externalUserService.deleteUser(loginId);
		if(resultFunc.equals("Success"))
		{
			String lblResult="Entry Successfully Deleted";
			map.put("lblResult", lblResult);
			User ExternalUser=new User();
			map.put("InternalUser", ExternalUser);
			
			return "deleteExternalUsers";
			
		}
		else
		{
			map.put("Result", resultFunc);
			return "ErrorPage";
		}
			
	}

	@RequestMapping("/modifyExternalUsers")
	public String User_Modify(Map<String, Object> map)
	{	
		map.put("ExternalUser", new User());
		
	String lblResult="";
	map.put("lblResult", lblResult);
		return "modifyExternalUsers";
	}
	
		
	
	@RequestMapping(value="/modifyExternalUsers", method=RequestMethod.POST)
	public String User_ModifyPOST(@RequestParam("loginID") String loginID, Map<String, Object> map, HttpServletRequest request)
	{
		User user=externalUserService.findUser(loginID);
		if(user==null)
		{
			map.put("Result", "User Not Found");
			return "ErrorPage";
			
		}
		else
		{
		//store login id in session var//
		HttpSession session = request.getSession();
		session.setAttribute("loginId", user.getLoginid());
	
		map.put("ExternalUser", user);
		String lblResult="";
		map.put("lblResult", lblResult);
		return "modifyExternalUsers";
		}
	}
	
	@RequestMapping(value="/updateExternalUsers", method=RequestMethod.POST)
	public String Update_UserPOST(@ModelAttribute UserFormDisplayBean ExternalUserFormBean, BindingResult result, @RequestParam String action, Map<String, Object> map, HttpServletRequest request)
	{
		
		HttpSession session = request.getSession();
		String loginId=session.getAttribute("loginId").toString();
		
		User ExternalUser=externalUserService.findUser(loginId);
		if(ExternalUser==null)
		{
			map.put("Result", "No User Found");
			return "ErrorPage";
		
		}
		else
		{
		ExternalUser.setName(ExternalUserFormBean.getName());
		ExternalUser.setGender(ExternalUserFormBean.getGender());
		ExternalUser.setAddress(ExternalUserFormBean.getAddress());
		ExternalUser.setAge(ExternalUserFormBean.getAge());
		ExternalUser.setEmail(ExternalUserFormBean.getEmail());
		ExternalUser.setPhoneno(ExternalUserFormBean.getPhoneno());
		ExternalUser.setRole(ExternalUserFormBean.getRole());
		ExternalUser.setAttempts(ExternalUserFormBean.getAttempts());
		
		String resultFunc=externalUserService.editUser(ExternalUser);
		if(resultFunc.equals("Success"))
		{
			String lblResult="Entry Successfully Modified";
			map.put("lblResult", lblResult);
			map.put("ExternalUser", ExternalUser);
			
			return "modifyExternalUsers";
			
		}
		else
		{
			map.put("Result", resultFunc);
			return "ErrorPage";
		}
				
	}
	
	
	
	}
}
