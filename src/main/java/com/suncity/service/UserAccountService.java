package com.suncity.service;

import com.suncity.model.AccountClass;
import com.suncity.model.User;

public interface UserAccountService {
public void addUserAccountServiceImpl(AccountClass acc);
public AccountClass getAccount(String accountno);

public AccountClass getAccountById(String toVerifyCustomer);
public void editAccount(AccountClass userAccount);

public String deleteUserAccount(String accountno);

}
