package com.suncity.dao;

import java.util.List;

import com.suncity.model.request;

public interface RequestDao {

	public String deleteRequest(String requestid);
	public List getRequests();
	public request getRequest(String requestid);
	public List getDeleteRequests();
	
}
