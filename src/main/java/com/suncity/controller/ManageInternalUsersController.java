package com.suncity.controller;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;

import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.*;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suncity.formbean.UserFormDisplayBean;
import com.suncity.model.AccountClass;
import com.suncity.model.User;
import com.suncity.model.UserReg;
import com.suncity.model.request;
import com.suncity.model.transaction;
import com.suncity.service.ExternalUserService;
import com.suncity.service.RequestService;
import com.suncity.service.UserAccountService;
import com.suncity.service.UserRegService;
import com.suncity.service.internalUserService;

@Controller
@RequestMapping("/internal")
public class ManageInternalUsersController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	@Autowired
	private internalUserService InternalUserService;
	
	@Autowired
	private ExternalUserService externaluserservice;
	
	
	@Autowired
	private UserAccountService useraccountservice;
	
	@Autowired
	private RequestService requestservice;
	
	@Autowired
	private UserRegService userregservice;
	
	@RequestMapping({"/ViewSystemLogs", "/"})
	public String viewSystemLogs(Map<String, Object> map)
	{
		
		return "ViewSystemLogs";
	}

	
	
	@RequestMapping(value="/ViewSystemLogs", method=RequestMethod.POST)
	public String View_LogsPOST(@RequestParam("txtDay") String day, @RequestParam("txtMonth") String month, @RequestParam("txtYear") String year, Map<String, Object> map) throws IOException
	{
		ArrayList<String> logs=new ArrayList<String>();
		String val="";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("E:/Log/log.out"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if(line.contains(year+"-"+month+"-"+day))
				{
					val=val+line+"<br>";
					//logs.add(line);
				}
			
		}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		map.put("val", val);
		//map.put("logs", logs);
	return "DisplayLogs";	
	}

	
	
	@RequestMapping({"/viewRequest", "/"})
	public String viewRequests(Map<String, Object> map)
	{
		return "viewRequest";
	}
	

	@RequestMapping({"/viewDeleteRequest", "/"})
	public String viewDeleteRequests(Map<String, Object> map)
	{
		map.put("DeleteRequest", requestservice.getDeleteRequests());
		return "viewDeleteRequest";
	}
	
	
	
	@RequestMapping(value="/viewDeleteRequest", method=RequestMethod.POST)
	public String DeleteRequests(@RequestParam("checkedRequests") String[] checkedRequests, Map<String, Object> map, HttpServletRequest request)
	{
		
	for(int i=0;i<checkedRequests.length;i++)
	{
	request req=requestservice.getRequest(checkedRequests[i]);
	
	
	String res=useraccountservice.deleteUserAccount(String.valueOf(req.getAccountno()));
	
	AccountClass receiverAccount=useraccountservice.getAccount(String.valueOf(req.getAccountno()));
	
	if(res.equals("Success"))
	{
		
	}
	else
	{
		map.put("Result", "Could Not delete User with Account No :" +req.getAccountno());
		return "ErrorPage";
	}
	
	
	res=InternalUserService.deleteUser(req.getRequesterLoginId());
	if(res.equals("Success"))
	{
		
	}
	else
	{
		map.put("Result", "Could Not delete User with LoginId :" +req.getRequesterLoginId());
		return "ErrorPage";
	}
	
	
		//delete user and account here//
		
	requestservice.deleteRequest(checkedRequests[i]);
	}
	return "SuccessfulDeletion";
	}
	
	@RequestMapping({"/viewUserRegistrationRequest", "/"})
	public String viewUserRegistrationRequests(Map<String, Object> map)
	{
		map.put("userRegReq", userregservice.getAllRegRequests());
		return "viewUserRegistrationRequest";
	}
	
	
	@RequestMapping(value="/viewUserRegistrationRequest", method=RequestMethod.POST)
	public String UserRegRequests(@RequestParam("checkedRequests") String[] checkedRequests, Map<String, Object> map, HttpServletRequest request)
	{
		for(int i=0;i<checkedRequests.length;i++)
		{
			UserReg newuser=userregservice.getRegRequest(checkedRequests[i]);
		
			
			User externaluser=new User();
			
			externaluser.setName(newuser.getName());
			
			externaluser.setLoginid(newuser.getLoginid());
			
			externaluser.setGender(newuser.getGender());
			externaluser.setAddress(newuser.getAddress());
			externaluser.setAge(newuser.getAge());
			externaluser.setEmail(newuser.getEmail());
			externaluser.setSsn(newuser.getEmail());
			externaluser.setPhoneno(newuser.getPhoneno());
			externaluser.setPin(newuser.getPin());
			externaluser.setRole(newuser.getRole());
			externaluser.setPassword(newuser.getPassword());
			externaluser.setAttempts(newuser.getAttempts());
			externaluser.setQuestion1(newuser.getQuestion1());
			externaluser.setAnswer1(newuser.getAnswer1());
			externaluser.setQuestion2(newuser.getQuestion2());
			externaluser.setAnswer2(newuser.getAnswer2());
			
			//add external user//
			externaluserservice.addUser(externaluser);
			//delete from user reg table//
			userregservice.deleteRegRequest(checkedRequests[i]);
			
			
		}
	//make this page to display Successfully Resistered//	
		return "AccountRegistered";
	}
	
	
	@RequestMapping({"/RedirectManageInternalUsers", "/"})
	public String redirectManageInternalUsers(Map<String, Object> map)
	{
		return "ManageInternalUsers";
	}
	
	
	@RequestMapping("/searchRegularInternalUsers")
	public String User_Search(Map<String, Object> map)
	{	map.put("InternalUser", new User());
	String res="";
	map.put("Result", res);
		return "searchRegularInternalUsers";
	}
	
	@RequestMapping(value="/searchRegularInternalUsers", method=RequestMethod.POST)
	public String User_SearchPOST(@RequestParam("loginID") String loginID, Map<String, Object> map)
	{
		
		User user=InternalUserService.findUser(loginID);
		
		if(user==null)
		{
			map.put("InternalUser", new User());
			String res="No Such Entry Found";
			map.put("Result", res);
		}
		else
		{
		map.put("InternalUser", user);
		String res="";
		map.put("Result", res);
		}
		
		
		return "searchRegularInternalUsers";
	}
	
	@RequestMapping("/deleteRegularInternalUsers")
	public String User_Delete(Map<String, Object> map)
	{	map.put("InternalUser", new User());
	String res="";
	map.put("Result", res);
		return "deleteRegularInternalUsers";
	}
	

	@RequestMapping(value="/deleteRegularInternalUsers", method=RequestMethod.POST)
	public String User_DeletePOST(@RequestParam("loginID") String loginID, Map<String, Object> map, HttpServletRequest request)
	{
		User user=InternalUserService.findUser(loginID);
		if(user==null)
		{
			map.put("InternalUser", new User());
			String res="Entry Not found";
			map.put("Result", res);
		}
		else
		{
		HttpSession session = request.getSession();
		session.setAttribute("loginId", user.getLoginid());
	
		map.put("InternalUser", user);
		String res="";
		map.put("Result", res);
		}
		return "deleteRegularInternalUsers";
	}
	
	
	
	
	@RequestMapping("/viewLockedAccounts")
	public String ViewLockedAcc(Map<String, Object> map)
	{	
		
		
		
		map.put("LockedAccounts", externaluserservice.getLockedAccounts());

	
		return "viewLockedAccounts";
	}
	
	
	

	@RequestMapping(value="/viewLockedAccounts", method=RequestMethod.POST)
	public String UnlockAccounts(@RequestParam("checkedRequests") String[] checkedRequests, Map<String, Object> map, HttpServletRequest request)
	{
		
	for(int i=0;i<checkedRequests.length;i++)
	{
		
		User extUser=externaluserservice.findUser(checkedRequests[i]);
	
	
		extUser.setStatus("Unlocked");
		
		
		externaluserservice.editUser(extUser);
		
		
	}
	return "UnlockedAccounts";
	}
	@RequestMapping("/modifyRegularInternalUsers")
	public String User_Modify(Map<String, Object> map)
	{	map.put("InternalUser", new User());
	String res="";
	map.put("Result", res);
	
	
		return "modifyRegularInternalUsers";
	}
	
	@RequestMapping(value="/modifyRegularInternalUsers", method=RequestMethod.POST)
	public String User_ModifyPOST(@RequestParam("loginID") String loginID, Map<String, Object> map, HttpServletRequest request)
	{
		
		//store login id in session var//
		
	
		User user=InternalUserService.findUser(loginID);
		if(user==null)
		{
			map.put("InternalUser", new User());
			String res="Entry Not found";
			map.put("Result", res);
		}
		else
		{
		HttpSession session = request.getSession();
		session.setAttribute("loginId", user.getLoginid());
	
		
		map.put("InternalUser", user);
		String res="";
		map.put("Result", res);
		}
		return "modifyRegularInternalUsers";
	}
	
	@RequestMapping("/addRegularInternalUsers")
	public String User_Add(Model model)
	{	
		model.addAttribute("InternalUser", new User());
	 String res="";
	 model.addAttribute("Result", res);
		return "addRegularInternalUsers";
	
	}
	
	
	@RequestMapping(value="/addRegularInternalUsers", method=RequestMethod.POST)
	public String User_AddPOST(@ModelAttribute User InternalUser, BindingResult result, @RequestParam String action, Map<String, Object> map, HttpServletRequest request)
	{
		
		if(InternalUser.getRole().equals("regularInternal"))
		{
			
		
		String passwd=InternalUser.getPassword();
		String encodedpassword=passwordEncoder.encode(passwd);
		InternalUser.setPassword(encodedpassword);
		try
		{
			
		
		String resultFunc=InternalUserService.addUser(InternalUser);
		
		if(resultFunc.equals("Success"))
		{
			
		User newInternalUser=new User();
		//newInternalUser=InternalUser;
		//map.put("InternalUser", newInternalUser);
		String res="User Successfully Added";
		map.put("Result", res);
		map.put("InternalUser", new User());
		
		return "addRegularInternalUsers";	
		
		}
		else
		{
			map.put("Result", resultFunc);
			return "ErrorPage";
		}
		
		}
		
		catch(Exception e)
		{
			
			map.put("Result", e.getMessage());
			
			 
		}
		map.put("Result", "Error Occured!");
		
		return "ErrorPage";	
		}	
		else
		{
			map.put("Result", "Cannot Add such a User");
			
			return "ErrorPage";	
		}
			
	}
	
	@RequestMapping(value="/updateInternalUsers", method=RequestMethod.POST)
	public String Update_UserPOST(@ModelAttribute UserFormDisplayBean InternalUserFormBean, BindingResult result, @RequestParam String action, Map<String, Object> map, HttpServletRequest request)
	{
		
		HttpSession session = request.getSession();
		String loginId=session.getAttribute("loginId").toString();
		
		
		try
		{
		User InternalUser=InternalUserService.findUser(loginId);
		if(InternalUser==null)
		{
			map.put("Result", "User Not Found");
			return "ErrorPage";
		}
		else
		{
		InternalUser.setName(InternalUserFormBean.getName());
		InternalUser.setGender(InternalUserFormBean.getGender());
		InternalUser.setAddress(InternalUserFormBean.getAddress());
		InternalUser.setAge(InternalUserFormBean.getAge());
		InternalUser.setEmail(InternalUserFormBean.getEmail());
		InternalUser.setPhoneno(InternalUserFormBean.getPhoneno());
		InternalUser.setRole(InternalUserFormBean.getRole());
		InternalUser.setAttempts(InternalUserFormBean.getAttempts());
		
		
		String resultFunc=InternalUserService.editUser(InternalUser);
	
		if(resultFunc.equals("Success"))
		{
		String res="Entry Successfully Modified";
		map.put("Result", res);
		map.put("InternalUser", InternalUser);
		return "modifyRegularInternalUsers";
		}
		else
		{
			map.put("Result", resultFunc);
			return "ErrorPage";
		}
		}
		}
		catch(Exception e)
		{
			map.put("Result", e.getMessage());
			
		}
		
		//map.put("Result", "ERROR Occured");
		return "ErrorPage";
		}

	@RequestMapping(value="/removeInternalUsers", method=RequestMethod.POST)
	public String Remove_UserPOST(@ModelAttribute UserFormDisplayBean FormBeanExternalUser, BindingResult result, @RequestParam String action, Map<String, Object> map, HttpServletRequest request)
	{
		try
		{
		HttpSession session = request.getSession();
		String loginId=session.getAttribute("loginId").toString();
		
		User user=InternalUserService.findUser(loginId);
		if(user.getRole().equals("admin"))
		{
			map.put("Result", "Not Authorized to Delete This User");
			
		return "ErrorPage";	
		}
		else
		{
		String resultFunc=InternalUserService.deleteUser(loginId);
		if(resultFunc.equals("Success"))
		{
			String lblResult="Entry Successfully Deleted";
			map.put("lblResult", lblResult);
			User InternalUser=new User();
			map.put("InternalUser", InternalUser);
			return "deleteRegularInternalUsers";
			
				
		}
		else
		{
			map.put("Result", resultFunc);
			return "ErrorPage";
		}
		}
		}
		catch(Exception e)
		{
			map.put("Result", e.getMessage());
			
		}
		
		return "ErrorPage";
			
	}
	
}