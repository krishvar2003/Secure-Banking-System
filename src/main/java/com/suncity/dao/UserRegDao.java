package com.suncity.dao;

import java.util.List;

import com.suncity.model.UserReg;

public interface UserRegDao {
	public List getAllRegRequests();
	public UserReg getRegRequest(String loginid);
	public String deleteRegRequest(String loginid);
	
	

}
