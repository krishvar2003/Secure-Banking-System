package com.suncity.dao;

import com.suncity.model.AccountClass;
import com.suncity.model.User;

public interface UserAccountdao {
	
	public void addUserAccountDaoImpl(AccountClass d);
	
	public AccountClass getAccount(String accountno);

	public AccountClass getAccountId(String toVerifyUser);

	public void editAccount(AccountClass userAccount);
	
	public String deleteUserAccount(String accountno);
	
	void editUserAccount(AccountClass d);
	

}
