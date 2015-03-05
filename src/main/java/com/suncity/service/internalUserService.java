package com.suncity.service;


import java.util.List;

import com.suncity.model.User;
import com.suncity.model.MerchantRequests;
import com.suncity.model.User;

public interface internalUserService {

	public String addUser(User user);
	public String editUser(User user);
	public String deleteUser(String loginid);
	public User findUser(String loginid);
	public List<MerchantRequests> getMerchantRequests();
	public MerchantRequests getMerchantRequest();
	
	
}
