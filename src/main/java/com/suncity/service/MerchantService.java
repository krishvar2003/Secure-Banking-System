package com.suncity.service;

import java.util.List;

import com.suncity.model.MerchantPayments;
import com.suncity.model.MerchantRequests;


public interface MerchantService {

	public void addPayments(MerchantPayments merchantPayments);

	public String placeRequests(MerchantRequests merchantRequests);
	
	public List<MerchantRequests> getPaymentsList(String username);

	public List<MerchantRequests> getUserPaymentsList(String username);

	public MerchantRequests getUserPayment(String username);
	
	public List<MerchantRequests> getLastRequestserviceimpl(String username);
	
	public void updateStatusserviceimpl(MerchantRequests merchant);

	public MerchantRequests getUserPaymentById(int requesttId);
}
