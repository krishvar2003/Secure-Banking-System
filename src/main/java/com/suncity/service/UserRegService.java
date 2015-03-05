package com.suncity.service;

import java.util.List;

import com.suncity.model.UserReg;

public interface UserRegService {

	public List getAllRegRequests();
	public UserReg getRegRequest(String loginid);
	public String deleteRegRequest(String loginid);
	
}
