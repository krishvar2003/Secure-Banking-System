package com.suncity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.UserAccountdao;
import com.suncity.dao.UserDao;
import com.suncity.model.AccountClass;
import com.suncity.model.User;
import com.suncity.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountdao auser;
	
	@Override
	@Transactional
	public void addUserAccountServiceImpl(AccountClass acc) {
	
		auser.addUserAccountDaoImpl(acc);

	}

	@Override
	@Transactional
	public AccountClass getAccount(String accountno) {
	return auser.getAccount(accountno);

	}


	@Transactional
	public AccountClass getAccountById(String toVerifyUser) {
		return auser.getAccountId(toVerifyUser);
	}

	@Transactional
	public void editAccount(AccountClass userAccount) {
		auser.editAccount(userAccount);

	}

	@Override
	@Transactional
	public String deleteUserAccount(String accountno) {
		return auser.deleteUserAccount(accountno);
	}

}
