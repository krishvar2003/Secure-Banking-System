package com.suncity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.UserRegDao;
import com.suncity.model.UserReg;
import com.suncity.service.UserRegService;
@Service
public class UserRegServiceImpl implements UserRegService {

	@Autowired
	private UserRegDao userregdao;
	
	@Transactional
	@Override
	public List getAllRegRequests() {
	return userregdao.getAllRegRequests();
	}
	@Transactional
	@Override
	public UserReg getRegRequest(String loginid) {
		return userregdao.getRegRequest(loginid);
	}
	@Transactional
	@Override
	public String deleteRegRequest(String loginid) {
		return userregdao.deleteRegRequest(loginid);
	}

}
