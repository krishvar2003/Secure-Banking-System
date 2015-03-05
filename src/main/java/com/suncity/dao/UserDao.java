package com.suncity.dao;

import java.util.List;

import com.suncity.model.History;
import com.suncity.model.User;
import com.suncity.model.UserReg;
import com.suncity.model.request;
import com.suncity.model.transaction;


public interface UserDao {
	void addUserRegdao(UserReg user1);
	void deleteRequestdao(String loginid,request req);
	String addUser(User User);
	List getLockedAccounts();
	String checkDeleteRequestdao(String id);
	String editUser(User User);
	String deleteUser(String loginid);
	User findUser(String loginid);
User findUserByName(String Username);
String debitbalDaoImpl(User user12, double amount);
String creditbalDaoImpl(User user, double amount);
String TransferDaoImpl(User user, String email, double amount);
String AddressDaoImpl(String username);

String viewbalDaoImpl(User user);
String resetpwd(String pwd);

String returnsecqans();
String getans();
void modifypiiDaoImpl(User user, String address, Long phno);
String getemailidofuser();
String putotp(String otp);
String otpvalidate(String otptocheck);
String getaccountnum(String email);
String submittrans(transaction obj);
String checkusername(String loginid);
String forgotpassputotp(String str, String loginid);
String forgotpwdchange(String loginidreqd, String pwd);
String otpforgotpassvalidate(String otptocheck, String loginidreqd);
List<History> gethistory();
String updatebadlogin(String userName);
String updatecorrectlogin();
String updateuserStatus();
String getstatus();
}
