package com.suncity.service.impl;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.UserDao;
import com.suncity.model.History;
import com.suncity.model.User;
import com.suncity.model.UserReg;
import com.suncity.model.request;
import com.suncity.model.transaction;
import com.suncity.service.ExternalUserService;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ExternalUserServiceImpl implements ExternalUserService {

	@Autowired
	private UserDao user1;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Transactional
	public String addUser(User extUser) {
	try
	{
		String encodedPassword = passwordEncoder.encode(extUser.getPassword());
		extUser.setPassword(encodedPassword);
		return user1.addUser(extUser);
			
	}
	catch(Exception e)
	{
		return "Failed";
	}
	}
	

	@Transactional
	public User checkUser(String loginid) {
		return user1.findUser(loginid);
	}

	@Override
	@Transactional
	public String creditbalServiceImpl(User user, double amount)
			throws InvalidParameterException {

		String msg = user1.creditbalDaoImpl(user, amount);
		return msg;

	}

	@Override
	@Transactional
	public String debitbalServiceImpl(User user12, double amount)
			throws InvalidParameterException {
		// TODO Auto-generated method stub
		String msg = user1.debitbalDaoImpl(user12, amount);
		return msg;

	}

	@Override
	@Transactional
	public String AddressServiceImpl(String username) {
		// TODO Auto-generated method stub

		String u = user1.AddressDaoImpl(username);
		return u;

	}

	@Override
	@Transactional
	public String TransferServiceImpl(User user,String email, double amount)throws InvalidParameterException {
		// TODO Auto-generated method stub
		String ret="";
		ret = user1.TransferDaoImpl(user,email, amount);
		System.out.println("Printing from transfer service: " + ret);
		return ret;
		
	}

	@Transactional
	public String editUser(User extuser) {
		// cannot view entries of internal user//
		if (extuser.getRole().equals("User")
				| extuser.getRole().equals("Merchant")) {
			
			return user1.editUser(extuser);
			
			}
		
		return "Unauthorized User";

	}

	@Transactional
	public String deleteUser(String loginid) {

		return user1.deleteUser(loginid);
	}

	@Transactional
	public User findUser(String loginid) {
		// cannot view entries of internal user//
		User receivedUser = user1.findUser(loginid);
		if (receivedUser.getRole().equals("User")
				| receivedUser.getRole().equals("Merchant")) {
			return receivedUser;
		} else {
			return new User();
		}
	}

	@Override
	@Transactional
	public String resetpwd(String pwd) {
		// TODO Auto-generated method stub
		String ret;
		ret = user1.resetpwd(pwd);
		return ret;
	}

	@Override
	@Transactional
	public String viewbalServiceImpl(User user) {
		// TODO Auto-generated method stub
		String bal=user1.viewbalDaoImpl(user);
		return bal;
	}
	
	@Override
	@Transactional
	public String secqans() {
		// TODO Auto-generated method stub
		String msg;
		msg = user1.returnsecqans();
		return msg;
	}

	@Override
	@Transactional
	public String getans() {
		// TODO Auto-generated method stub
		String msg;
		msg = user1.getans();
		return msg;
	}

	@Transactional
	public User getExternalUser(String toVerifyCustomer) {
		return user1.findUser(toVerifyCustomer);
	}
	@Override
	@Transactional
	public void modifypiiServiceImpl(User user, String address, Long phno) {
		// TODO Auto-generated method stub
		user1.modifypiiDaoImpl(user,address, phno);
		
	}

	@Override
	@Transactional
	public String getemailid() {
		// TODO Auto-generated method stub
		String msg = user1.getemailidofuser();
		return msg;
	}

	@Override
	@Transactional
	public String insertotp(String otp) {
		// TODO Auto-generated method stub
		String msg = user1.putotp(otp);
		return msg;
	}

	@Override
	@Transactional
	public String validateotp(String otptocheck) {
		// TODO Auto-generated method stub
		String msg = user1.otpvalidate(otptocheck);
		return msg;
	}

	@Override
	@Transactional
	public String getaccountnum(String email) {
		// TODO Auto-generated method stub
		String msg = user1.getaccountnum(email);
		return msg;
	}

	@Override
	@Transactional
	public String submittransreq(transaction obj) {
		// TODO Auto-generated method stub
		String msg = user1.submittrans(obj);
		return msg;
	}

	@Override
	@Transactional
	public void addUserReg(UserReg user12) 
	{
		String encodedPassword = passwordEncoder.encode(user12.getPassword());
		user12.setPassword(encodedPassword);
		
		user1.addUserRegdao(user12);
	}
	
	@Transactional
	public String checkUsername(String loginid) {
		// TODO Auto-generated method stub
		String msg = user1.checkusername(loginid);
		return msg;
	}

	@Override
	@Transactional
	public void deleteRequest(String loginid, request delreq) {

			user1.deleteRequestdao(loginid,delreq);
	}
	
	@Transactional
	public String insertforgotpassotp(String str, String loginid) {
		String msg = user1.forgotpassputotp(str, loginid);
		return msg;
	}

	@Override
	@Transactional
	public String checkotpforgotpass(String otptocheck, String loginidreqd) {
		String msg = user1.otpforgotpassvalidate(otptocheck, loginidreqd);
		return msg;
	}

	@Override
	@Transactional
	public String forgotpasswordchange(String loginidreqd, String pwd) {
		String msg = user1.forgotpwdchange(loginidreqd, pwd);
		return msg;
	}

	@Override
	@Transactional
	public List<History> fetchhistory() {
		List<History> hist = user1.gethistory();
		return hist;
	}


	@Override
	@Transactional
	public String updateloginattempts(String userName) {
		String msg = user1.updatebadlogin(userName);
		return msg;
		
	}


	@Override
	@Transactional
	public String updatecorrectloginattempts() {
		// TODO Auto-generated method stub
		String msg = user1.updatecorrectlogin();
		return msg;
		
	}


	@Override
	@Transactional
	public String updateuserstatus() {
		// TODO Auto-generated method stub
		String msg = user1.updateuserStatus();
		return msg;
		
	}


	@Override
	@Transactional
	public String getstatus() {
		String msg = user1.getstatus();
		return msg;
	}

	@Override
		@Transactional
		public List getLockedAccounts() {
			return user1.getLockedAccounts();
		}


	@Override
	@Transactional
	public String checkDeleteRequest(String id) {
		return user1.checkDeleteRequestdao(id);
	}
	
	
}
