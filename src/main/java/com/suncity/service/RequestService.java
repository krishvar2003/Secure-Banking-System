package com.suncity.service;

import java.util.List;

import com.suncity.model.request;

public interface RequestService {
	public String deleteRequest(String requestid);
	public List getAllRequests();
	public List getDeleteRequests();
	public request getRequest(String requestid);

}
