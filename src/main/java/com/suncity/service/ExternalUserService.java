package com.suncity.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.suncity.model.History;
import com.suncity.model.AccountClass;
import com.suncity.model.User;
import com.suncity.model.UserReg;
import com.suncity.model.request;
import com.suncity.model.transaction;


public interface ExternalUserService {
	public String addUser(User user);
	public User checkUser(String loginid);
	String creditbalServiceImpl(User user, double amount);
	public List getLockedAccounts();
	public String debitbalServiceImpl(User user12, double amount);
	public void addUserReg(UserReg user1);
	public String TransferServiceImpl(User user, String email, double amount)
			throws InvalidParameterException;
	public String AddressServiceImpl(String loginidreq);
	public User findUser(String loginID);
	public String checkDeleteRequest(String id);
	public void deleteRequest(String loginid,request delreq);
	public String deleteUser(String loginId);
	public String editUser(User externalUser);

	public String viewbalServiceImpl(User user);
	public String resetpwd(String pwd);
	public String secqans();
	public String getans();
	public User getExternalUser(String toVerifyCustomer);
	public void modifypiiServiceImpl(User user,String address,Long phno);
	public String getemailid();
	public String insertotp(String otp);
	public String validateotp(String otptocheck);
	public String getaccountnum(String email);
	public String submittransreq(transaction obj);
	public String checkUsername(String loginid);
	public String insertforgotpassotp(String str, String loginid);
	public String checkotpforgotpass(String otptocheck, String loginidreqd);
	public String forgotpasswordchange(String loginidreqd, String pwd);
	public List<History> fetchhistory();
	public String updateloginattempts(String userName);
	public String updatecorrectloginattempts();
	public String updateuserstatus();
	public String getstatus();
}
