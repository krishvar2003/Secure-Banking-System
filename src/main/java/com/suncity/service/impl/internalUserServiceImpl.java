package com.suncity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.MerchantRequestsDao;
import com.suncity.dao.UserDao;
import com.suncity.model.MerchantRequests;
import com.suncity.model.User;

import com.suncity.service.internalUserService;
@Service
public class internalUserServiceImpl implements internalUserService {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private MerchantRequestsDao merchantRequestsDao;
	
	@Transactional
	public String addUser(User user) {
     return userdao.addUser(user);
	}

	@Transactional
	public String editUser(User user) {
		
		return userdao.editUser(user);
		
	}

	@Transactional
	public String deleteUser(String loginid) {
		
		return userdao.deleteUser(loginid);
		
	}

	@Transactional
	public User findUser(String loginid) {	
		
	return userdao.findUser(loginid);
	}

	@Transactional
	public List<MerchantRequests> getMerchantRequests() {
		return merchantRequestsDao.getMerchantRequests();
	}

	@Transactional
	public MerchantRequests getMerchantRequest() {
		return merchantRequestsDao.getMerchantRequest().get(0);
	}

}
