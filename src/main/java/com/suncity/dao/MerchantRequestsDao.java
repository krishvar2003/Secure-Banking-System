package com.suncity.dao;

import java.util.List;

import com.suncity.model.MerchantPayments;
import com.suncity.model.MerchantRequests;

public interface MerchantRequestsDao {
	public String placeRequest(MerchantRequests merchantRequest);

	public java.util.List<MerchantRequests> getPaymentList(String username);

	public List<MerchantRequests> getUserPaymentsList(String username);

	public List<MerchantRequests> getLastRequestdaoimpl(String username);
	
	public void updateStatusdaoimpl(MerchantRequests merchant);

	public List<MerchantRequests> getMerchantRequests();

	public List<MerchantRequests> getMerchantRequest();

	public MerchantRequests getRequestById(int requestId);
}
