package com.suncity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.MerchantPaymentsDao;
import com.suncity.dao.MerchantRequestsDao;
import com.suncity.model.MerchantPayments;
import com.suncity.model.MerchantRequests;
import com.suncity.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService{

	@Autowired
	private MerchantPaymentsDao merchantPaymentsDao;
	
	@Autowired
	private MerchantRequestsDao merchantRequestsDao;
	@Transactional
	public void addPayments(MerchantPayments merchantPayments) {
		merchantPaymentsDao.addPayments(merchantPayments);
	}
	@Transactional
	public String placeRequests(MerchantRequests merchantRequests) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		merchantRequests.setMerchantLoginId(userDetails.getUsername());
		merchantRequests.setStatus("MerchantInitiated");
		String returnVal = merchantRequestsDao.placeRequest(merchantRequests);
		return returnVal;
	}
	
	@Transactional
	public List<MerchantRequests> getPaymentsList(String username) {
		return merchantRequestsDao.getPaymentList(username);
		
	}
	
	@Transactional
	public List<MerchantRequests> getUserPaymentsList(String username) {
		return merchantRequestsDao.getUserPaymentsList(username);
	}
	
	@Transactional
	public MerchantRequests getUserPayment(String username) {
		List<MerchantRequests> paymentList =  merchantRequestsDao.getUserPaymentsList(username);
		System.out.println(paymentList.get(0));
		return paymentList.get(0);
	}
	
	@Transactional
	public List<MerchantRequests> getLastRequestserviceimpl(String username) {
		return merchantRequestsDao.getLastRequestdaoimpl(username);
	}

	@Transactional
	public void updateStatusserviceimpl(MerchantRequests merchant){
		merchantRequestsDao.updateStatusdaoimpl(merchant);
	}
	@Transactional
	public MerchantRequests getUserPaymentById(int requestId) {
		return merchantRequestsDao.getRequestById(requestId);
	}
}
