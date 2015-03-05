package com.suncity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.RequestDao;
import com.suncity.model.request;
import com.suncity.service.RequestService;
@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestDao requestdao;
	
	@Transactional
	public String deleteRequest(String requestid) {
	return requestdao.deleteRequest(requestid);	
	}

	@Transactional
	public List getAllRequests() {
		return requestdao.getRequests();
	}
	
	@Transactional
	public List getDeleteRequests() {
		return requestdao.getRequests();
	}

	@Transactional
	public request getRequest(String requestid) {
		return requestdao.getRequest(requestid);
	}

}
