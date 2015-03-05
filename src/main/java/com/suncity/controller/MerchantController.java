package com.suncity.controller;

import java.util.List;
import java.util.Map;

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

import com.suncity.model.MerchantRequests;
import com.suncity.model.User;
import com.suncity.service.MerchantService;

@Controller
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@RequestMapping(value="/external/paymentList")
	public String paymentList(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		model.addAttribute("paymentList", merchantService.getPaymentsList(userDetails.getUsername()));
		return "paymentList"; 
	}

	@RequestMapping(value="/external/paymentList/request")
	public String lastrequest(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}

		model.addAttribute("requestList", merchantService.getLastRequestserviceimpl(userDetails.getUsername()));
		return "requestList"; 
	}

	@RequestMapping(value="/external/paymentList/request", method=RequestMethod.POST )
	public String paymentprocess(@ModelAttribute MerchantRequests merchant,String action,Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}
		switch(action.toLowerCase()){
		case "submit to bank":
		{
			/*System.out.println("asdsdfsdf");
			System.out.println(merchant.getRequestId());*/
			//merchantService.setStatusserviceimpl(merchant.getRequestId());
			MerchantRequests existing = merchantService.getUserPaymentById(merchant.getRequesttId());
			merchant.setMerchantLoginId(userDetails.getUsername());
			merchant.setStatus("MerchantApproved");
			merchant.setRequestId(merchant.getRequesttId());
			merchant.setAmount(merchant.getAmount());
			merchant.setCustomerLoginId(merchant.getCustomerLoginId());
			merchant.setPKI(existing.getPKI());
			System.out.println("merchant approving pki" + merchant.getPKI());
			merchantService.updateStatusserviceimpl(merchant);
			model.addAttribute("validateStatus","Payment submitted for bank approval");
		}
		}
		return "paymentList";
	}
	@RequestMapping(value="/external/paymentRequest")
	public String paymentRequest(Model model){
		//merchantService.getPaymentList(userDetails.getUsername());

		model.addAttribute("merchantRequests", new MerchantRequests());
		return "paymentRequest";
	}

	@RequestMapping(value="/external/paymentRequest", method=RequestMethod.POST)
	public String paymentRequestPost(@ModelAttribute MerchantRequests merchantRequests, BindingResult result, @RequestParam String action, Map<String, Object> map){
		String returnVal = merchantService.placeRequests(merchantRequests);
		if(returnVal == "success"){
			return "paymentRequestSuccess"; 
		}
		else{
			return "paymentRequestFailed";
		}
	}
}
