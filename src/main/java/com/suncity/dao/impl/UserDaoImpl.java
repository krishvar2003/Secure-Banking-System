package com.suncity.dao.impl;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.suncity.dao.UserDao;
import com.suncity.model.AccountClass;
import com.suncity.model.History;
import com.suncity.model.User;
import com.suncity.model.UserOTP;
import com.suncity.model.UserReg;
import com.suncity.model.request;
import com.suncity.model.transaction;

@Repository
public class UserDaoImpl implements UserDao {
	
	 static Logger log = Logger.getLogger(UserDaoImpl.class.getName());
	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private SessionFactory session;
	
	@Override
	public String addUser(User User) {
		int a=2;
		
		
		try
		{
		session.getCurrentSession().save(User);
		log.info("User Successfully Added : LoginID" +User.getLoginid());
		return "Success";
		}
		catch(Exception e)
		{
		log.error("User could not be added : LoginID" +User.getLoginid());	
		log.error("ERROR :"+e.getMessage());
		return "Failed";
		}
	}

	@Override
	public String editUser(User User) {
		try
		{
		session.getCurrentSession().update(User);
		log.info("User Information Successfully Updated : LoginID" +User.getLoginid());
		return "Success";
		}
		catch(Exception e)
		{
			log.error("User could not be updated : LoginID" +User.getLoginid());	
			log.error("ERROR :"+e.getMessage());
			return "Failed";
		}
	}

	@Override
	public String deleteUser(String loginid) {
		try
		{
		session.getCurrentSession().delete(findUser(loginid));
		log.info("User Information Successfully Deleted : LoginID" +loginid);
		return "Success";
		}
		catch(Exception e)
		{
			log.error("User could not be deleted : LoginID" +loginid);
			log.error("ERROR :"+e.getMessage());
			return "Failed";
		}
	}

	@Override
	public User findUser(String loginid) {
		try
		{
			
			log.info("Successfully found User : LoginID" +loginid);
		return (User) session.getCurrentSession().get(User.class, loginid);
		}
		catch(Exception e)
		{
			log.error("User could not be searched : LoginID" +loginid);
			log.error("ERROR :"+e.getMessage());
			
			return null;
		}
	}

	@Override
	public User findUserByName(String username) {
		
		try
		{
		Criteria criteria = session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("loginid", username));		
		return (User) criteria.uniqueResult();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String debitbalDaoImpl(User user12, double amount) {
		
		History obj = new History();
		
		
		
		
			//get login ID of user logged in 
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = null;
			if (!(auth instanceof AnonymousAuthenticationToken)) 
			{
			     userDetails = (UserDetails) auth.getPrincipal();
			}
			String loginidreq = userDetails.getUsername();
			List<AccountClass> accountobjlist = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
			
			 accountobjlist= session.getCurrentSession().createQuery("from AccountClass ac where ac.loginid = :uname").setString("uname", loginidreq).list();
			//accountobjlist = query.setParameter("dummy", loginidreq).list(); //get user's loginID
			// = query.list();
			 
			AccountClass accountobjreq = accountobjlist.get(0);
			double oldbal, newbal=0.0;
			oldbal = accountobjreq.getBalance();
			if(amount<=oldbal)
			{ 
			newbal = oldbal - amount;
			
			
			accountobjreq.setBalance(newbal);
			//update query for balance credited based on loginID
			try
			{
			
			Query query2 = session.getCurrentSession().createQuery("update AccountClass set balance = :baltemp" +
					" where loginid = :loginidtemp");
			query2.setParameter("baltemp", newbal);
			query2.setParameter("loginidtemp", loginidreq);
			int result = query2.executeUpdate();
			System.out.println("Update query status:" + result);
			
			//Updating in history table
			obj.setLoginId(loginidreq);
			obj.setbalafter(newbal);
			obj.setbalbefore(oldbal);
			obj.settype("Debit");
			obj.settransamount(amount);
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  	   //get current date time with Date()
		  	   Date date = new Date();
		  	   System.out.println(dateFormat.format(date));
		  	   String datetoput = dateFormat.format(date);
		  	   obj.settime(datetoput);
			
			session.getCurrentSession().save(obj);
			
			
			
			return "Debit successful!";
			}
			catch(Exception e)
			{
				return "Account not found!";
			}
			//UPDATE 
			
			
			}
			else {
				//throw new InvalidParameterException("invlid");
				return "Insufficient balance for debit!";
			}
		
	}

	
	@SuppressWarnings("unchecked")
	public String creditbalDaoImpl(User user, double amount) {
		
		History obj = new History();
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		List<AccountClass> accountobjlist = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
		
		 accountobjlist= session.getCurrentSession().createQuery("from AccountClass ac where ac.loginid = :uname").setString("uname", loginidreq).list();
		//accountobjlist = query.setParameter("dummy", loginidreq).list(); //get user's loginID
		// = query.list();
		 
		AccountClass accountobjreq = accountobjlist.get(0);
		double oldbal, newbal;
		oldbal = accountobjreq.getBalance();
		newbal = oldbal + amount;
		accountobjreq.setBalance(newbal);
		//update query for balance credited based on loginID
		try
		{
		Query query2 = session.getCurrentSession().createQuery("update AccountClass set balance = :baltemp" +
				" where loginid = :loginidtemp");
		query2.setParameter("baltemp", newbal);
		query2.setParameter("loginidtemp", loginidreq);
		int result = query2.executeUpdate();
		System.out.println("Update query status:" + result);
		obj.setLoginId(loginidreq);
		obj.setbalafter(newbal);
		obj.setbalbefore(oldbal);
		obj.settype("Credit");
		obj.settransamount(amount);
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  	   //get current date time with Date()
	  	   Date date = new Date();
	  	   System.out.println(dateFormat.format(date));
	  	   String datetoput = dateFormat.format(date);
	  	   obj.settime(datetoput);
		
		session.getCurrentSession().save(obj);
		return "Credit succesful!";
		}
		catch(Exception e)
		{
			return "Account not found!";
		}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public String AddressDaoImpl(String username) {
	
		List<User> userobjlist = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 userobjlist= session.getCurrentSession().createQuery("from User u where u.loginid = :uname").setString("uname", username).list();
		
		User us = userobjlist.get(0);
		String addr = us.getAddress();
		System.out.println("hello" + addr);
		return addr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String TransferDaoImpl(User user,String email, double amount) {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		List<User> useraccobjlist = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		useraccobjlist= session.getCurrentSession().createQuery("from User u where u.loginid = :uname").setString("uname", loginidreq).list();
		
		List<User> useraccobjlist1 = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		useraccobjlist1= session.getCurrentSession().createQuery("from User u where u.email = :uname1").setString("uname1", email).list();
		
		 if(useraccobjlist1.isEmpty())
		 {
			 System.out.println("Invalid Email ID");
		//throw new InvalidParameterException("invlid");
			 return "Invalid_E-mail_ID!";
		 }
		 
		 else
		 {
		
		User us1 = useraccobjlist.get(0);
		User us2 = useraccobjlist1.get(0);
		
		String senderlogin = us1.getLoginid();
		String receiverlogin = us2.getLoginid();
		
		List<AccountClass> accountobjlistsender = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
		List<AccountClass> accountobjlistreceiver = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
		 accountobjlistsender= session.getCurrentSession().createQuery("from AccountClass ac where ac.loginid = :sender").setString("sender", senderlogin).list();
		 accountobjlistreceiver= session.getCurrentSession().createQuery("from AccountClass acc where acc.loginid = :receiver").setString("receiver", receiverlogin).list();
		
			
		
		AccountClass from = accountobjlistsender.get(0);
		AccountClass to = accountobjlistreceiver.get(0);
		double senderbal ,recvbal , newsenderbal=0.0 , newrecvbal=0.0;
		senderbal = from.getBalance();
		System.out.println(senderbal);
		
		
		recvbal=to.getBalance();
		System.out.println(recvbal);
		if(amount < senderbal)
		{
			
		newsenderbal = senderbal - amount;
		newrecvbal=recvbal + amount;
		
		from.setBalance(newsenderbal);
		to.setBalance(newrecvbal);
		
		//update query for balance credited based on loginID
		
		Query querysender = session.getCurrentSession().createQuery("update AccountClass set balance = :bal" +
				" where loginid = :loginidtempsender");
		Query queryrecv = session.getCurrentSession().createQuery("update AccountClass set balance = :baltemp" +
				" where loginid = :loginidtemprecv");
		querysender.setParameter("bal", newsenderbal);
		querysender.setParameter("loginidtempsender", senderlogin);
		queryrecv.setParameter("baltemp",newrecvbal);
		queryrecv.setParameter("loginidtemprecv",receiverlogin);
		
		int result = querysender.executeUpdate();
		int res =queryrecv.executeUpdate();
		
		System.out.println("Update query status:" + result);
		System.out.println("Update query status:" + res);
		return "Transfer_done_successfully";
		
		
		}
		else
		{
			//throw new InvalidParameterException("");
			return "Insufficient_balance!";
			
			
		}
	
	}
		// aadd return stt

	}

	@SuppressWarnings("unchecked")
	@Override
	public String resetpwd(String pwd) {
		String msg="";
		
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		String encodedPasswordold = 
		          passwordEncoder.encode(pwd);
		System.out.println(encodedPasswordold);
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 User acnt = accountobjlistsender.get(0);
		
		
		String encodedPassword = 
		          passwordEncoder.encode(pwd); //new hashed password
	
		
		Query queryrecv = session.getCurrentSession().createQuery("update User set password = :pass" +
					" where loginid = :loginidtemprecv");
		
		queryrecv.setParameter("pass",encodedPassword);
		queryrecv.setParameter("loginidtemprecv",loginidreq);
		
		int result = queryrecv.executeUpdate();
		if(result ==1)
		{
			System.out.println("Updated successfully");
			msg = "Password_Updated!";
		}
		else
		{
			msg = "Password not updated!";
		
	}
		 // If Old password is incorrect 
	return msg;
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public String viewbalDaoImpl(User user) {
		// TODO Auto-generated method stub
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		System.out.println(loginidreq);
		
		List<AccountClass> accountobjlistsender = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from AccountClass ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 AccountClass acnt = accountobjlistsender.get(0);
		
	
		 double viewbal= acnt.getBalance();
		 long accno = acnt.getAccountno();
		 String toret = String.valueOf(viewbal) + "#" + String.valueOf(accno);
		 return toret;
	}



	@Override
	public String returnsecqans() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		System.out.println(loginidreq);
		
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 User acnt = accountobjlistsender.get(0);
		 String q1 = acnt.getQuestion1();
		 String q2 = acnt.getQuestion2();
		 String finstring = q1+"#" + q2;
		 return finstring;
		 
		
	}

	@Override
	public String getans() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		System.out.println(loginidreq);
		
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 User acnt = accountobjlistsender.get(0);
		 String q1 = acnt.getAnswer1();
		 String q2 = acnt.getAnswer2();
		 String finstring = q1+"#" + q2;
		 return finstring;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modifypiiDaoImpl(User user, String address, Long phno) {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		List<User> accountobjlist = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlist= session.getCurrentSession().createQuery("from User ac where ac.loginid = :uname").setString("uname", loginidreq).list();
		//accountobjlist = query.setParameter("dummy", loginidreq).list(); //get user's loginID
		// = query.list();
		 
		User accountobjreq = accountobjlist.get(0);
		
		
		
		accountobjreq.setAddress(address);
		accountobjreq.setPhoneno(phno);
		
		//update query for balance credited based on loginID
		
		Query query2 = session.getCurrentSession().createQuery("update User set address = :addr" +
				" where loginid = :loginidtemp");
		
		
		Query query3 = session.getCurrentSession().createQuery("update User set phoneno = :phno" +
				" where loginid = :loginidtemp");
		
		query2.setParameter("addr", address);
		query2.setParameter("loginidtemp", loginidreq);
		query3.setParameter("phno", phno);
		query3.setParameter("loginidtemp", loginidreq);
		int result = query2.executeUpdate();
		int result1 = query3.executeUpdate();
		
		System.out.println("Update query status:" + result + result1);
	}

	@Override
	public String getemailidofuser() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		System.out.println(loginidreq);
		
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 User acnt = accountobjlistsender.get(0);
		 String q1 = acnt.getEmail();
		 return q1;
	}

	@Override
	public String putotp(String otp) {
		// TODO Auto-generated method stub
	//HQL query to insert OTP
		UserOTP otpobj = new UserOTP();
		otpobj.setOTP(otp);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		otpobj.setLoginId(loginidreq);
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  	   //get current date time with Date()
	  	   Date date = new Date();
	  	   System.out.println(dateFormat.format(date));
	  	   String datetoput = dateFormat.format(date);
	  	   otpobj.setAccountopendate(datetoput);
	  	   
	  	 List<UserOTP> accountobjlistsender = ListUtils.lazyList(new ArrayList<UserOTP>(),FactoryUtils.instantiateFactory(UserOTP.class));
			
		 accountobjlistsender= session.getCurrentSession().createQuery("from UserOTP ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 //UserOTP acnt = accountobjlistsender.get(0);
		 if(accountobjlistsender.size() > 0)
		 {
			 //need to update OTP entry in table
			 Query query3 = session.getCurrentSession().createQuery("update UserOTP set otp = :otp, timecreated = :timecreated" +
						" where loginid = :loginidtemp");
				
				query3.setParameter("otp", otp);
				query3.setParameter("loginidtemp", loginidreq);
				query3.setParameter("timecreated", datetoput);
				//int result = query2.executeUpdate();
				int result1 = query3.executeUpdate();
				System.out.println("Updated OTP entry successfully");
		 }
		 else
		 {
			//for first time OTP generation for user
			 session.getCurrentSession().save(otpobj);
		 }
		
		
		
		return "OTP inserted";
		
	}

	@Override
	public String otpvalidate(String otptocheck) {
		// TODO Auto-generated method stub
		String msg="";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		
		System.out.println(loginidreq);
		
		List<UserOTP> accountobjlistsender = ListUtils.lazyList(new ArrayList<UserOTP>(),FactoryUtils.instantiateFactory(UserOTP.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from UserOTP ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 
		 UserOTP acnt = accountobjlistsender.get(0);
		 String actualotp = acnt.getOTP();
		 String timecreated = acnt.getOTPtime();
		 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  	   //get current date time with Date()
	  	   Date date = new Date();
	  	   System.out.println(dateFormat.format(date));
	  	   String datenow = dateFormat.format(date);
	  	  
	     //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
	     Date currdate = null, otpdate = null;
	     try {
	         currdate = dateFormat.parse(datenow);
	         otpdate = dateFormat.parse(timecreated);
	     }
	     catch(ParseException e)
	     {
	    	 System.out.println("DateFormat not processed properly");
	     }
	  	   
	  	 long duration  = currdate.getTime() - otpdate.getTime();

	  	long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
	  	long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
	  	long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		 
		String minselapsed = Objects.toString(diffInMinutes, null);
		msg+=minselapsed + "#";
		if(actualotp.equals(otptocheck))
		{
			msg+= "yes";
		}
		else
			msg+= "no";
		return msg;
		
	}

	@Override
	public String getaccountnum(String email) {
		//get login ID from User table based on emailID
		
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.email = :sender").setString("sender", email).list();
		 
		 if(accountobjlistsender.size()>0)
		 {		 
		 User acnt = accountobjlistsender.get(0);
		 
		 String loginidusr = acnt.getLoginid();

		
		// get Account number from Account table based on login ID
		 
		 List<AccountClass> accountobjlist = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
			
		 accountobjlist= session.getCurrentSession().createQuery("from AccountClass ac where ac.loginid = :sender").setString("sender", loginidusr).list();
		 
		 AccountClass acnts = accountobjlist.get(0);
		 long accno = acnts.getAccountno();
		 
		 String acctnum = String.valueOf(accno);
		 return acctnum;
		 }
		 else
		 {
			 return "invalid";
			 
		 }
	}

	@Override
	public String submittrans(transaction obj) {
		
		 session.getCurrentSession().save(obj);
		 
		return "submitted";
	}

	@Override
	public void addUserRegdao(UserReg user1) {
		session.getCurrentSession().save(user1);
	}

	@Override
	public void deleteRequestdao(String loginid, request req) {
		List<AccountClass> accountobjlistsender = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
		String hql = "FROM AccountClass A WHERE A.loginid = :loginid";
		accountobjlistsender = session.getCurrentSession().createQuery(hql).setParameter("loginid",loginid).list();
		//session.getCurrentSession().save(req);
		if(accountobjlistsender.size() > 0)
		{
			AccountClass obj = accountobjlistsender.get(0);
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			req.setAccountno(obj.getAccountno());
			req.setTimestamp(date);
			req.setRequesterLoginId(loginid);
			req.setRequestmsg("Request to Delete this Account");
			req.setRequestlevel("critical");
			req.setRequesttype("Account Delete");
			req.setRequeststatus("Pending");
			session.getCurrentSession().save(req);
		}
		else
		{
			System.out.println("Failed");
		}
	
	
	
	}
	public String checkusername(String loginid) {
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", loginid).list();
		 String msg="";
		if(accountobjlistsender.size() > 0)
		{
			//msg+="found";
			User obj = accountobjlistsender.get(0);
			String username = obj.getEmail();
			msg+=username;
			return msg;
			
			
		}
			
		else
			return "failure";
		
	}

	@Override
	public String forgotpassputotp(String otp, String loginid) {
		UserOTP otpobj = new UserOTP();
		otpobj.setOTP(otp);
		otpobj.setLoginId(loginid);
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  	   //get current date time with Date()
	  	   Date date = new Date();
	  	   System.out.println(dateFormat.format(date));
	  	   String datetoput = dateFormat.format(date);
	  	   otpobj.setAccountopendate(datetoput);
	  	   
	  	 List<UserOTP> accountobjlistsender = ListUtils.lazyList(new ArrayList<UserOTP>(),FactoryUtils.instantiateFactory(UserOTP.class));
			
		 accountobjlistsender= session.getCurrentSession().createQuery("from UserOTP ac where ac.loginid = :sender").setString("sender", loginid).list();
		 
		 //UserOTP acnt = accountobjlistsender.get(0);
		 if(accountobjlistsender.size() > 0)
		 {
			 //need to update OTP entry in table
			 Query query3 = session.getCurrentSession().createQuery("update UserOTP set otp = :otp, timecreated = :timecreated" +
						" where loginid = :loginidtemp");
				
				query3.setParameter("otp", otp);
				query3.setParameter("loginidtemp", loginid);
				query3.setParameter("timecreated", datetoput);
				//int result = query2.executeUpdate();
				int result1 = query3.executeUpdate();
				System.out.println("Updated OTP entry successfully");
		 }
		 else
		 {
			//for first time OTP generation for user
			 session.getCurrentSession().save(otpobj);
		 }
		
		
		
		return "OTP inserted";
		
		
		
		
	}

	@Override
	public String forgotpwdchange(String loginidreqd, String pwd) {
		String msg="";
		String encodedPassword = 
		          passwordEncoder.encode(pwd); //new hashed password
	
		
		Query queryrecv = session.getCurrentSession().createQuery("update User set password = :pass" +
					" where loginid = :loginidtemprecv");
		
		queryrecv.setParameter("pass",encodedPassword);
		queryrecv.setParameter("loginidtemprecv",loginidreqd);
		
		int result = queryrecv.executeUpdate();
		if(result ==1)
		{
			System.out.println("Updated successfully");
			msg = "Password_Updated!";
		}
		else
		{
			msg = "Password not updated!";
		
	}
		return msg;
	
	}

	@Override
	public String otpforgotpassvalidate(String otptocheck, String loginidreqd) {
		String msg="";
		List<UserOTP> accountobjlistsender = ListUtils.lazyList(new ArrayList<UserOTP>(),FactoryUtils.instantiateFactory(UserOTP.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from UserOTP ac where ac.loginid = :sender").setString("sender", loginidreqd).list();
		 
		 UserOTP acnt = accountobjlistsender.get(0);
		 String actualotp = acnt.getOTP();
		 String timecreated = acnt.getOTPtime();
		 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  	   //get current date time with Date()
	  	   Date date = new Date();
	  	   System.out.println(dateFormat.format(date));
	  	   String datenow = dateFormat.format(date);
	  	  
	     //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
	     Date currdate = null, otpdate = null;
	     try {
	         currdate = dateFormat.parse(datenow);
	         otpdate = dateFormat.parse(timecreated);
	     }
	     catch(ParseException e)
	     {
	    	 System.out.println("DateFormat not processed properly");
	     }
	  	   
	  	 long duration  = currdate.getTime() - otpdate.getTime();

	  	long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
	  	long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
	  	long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		 
		String minselapsed = Objects.toString(diffInMinutes, null);
		msg+=minselapsed + "#";
		if(actualotp.equals(otptocheck))
		{
			msg+= "yes";
		}
		else
			msg+= "no";
		return msg;
		
	}
	
	@Override
	public List<History> gethistory() {
		
		try{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		List<History> accountobjlistsender = ListUtils.lazyList(new ArrayList<History>(),FactoryUtils.instantiateFactory(History.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from History ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 System.out.println(accountobjlistsender.size());
		 History obj = accountobjlistsender.get(0);
		 System.out.println("Checking:" + obj.getbalbefore());
		 return accountobjlistsender;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public String updatebadlogin(String userName) {
		//check attempts in table
		
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", userName).list();
		 if(accountobjlistsender.size() >0)
		 {
			 User obj = accountobjlistsender.get(0);
			 int attempts = obj.getAttempts();
			 String role = obj.getRole();
			 if(!role.equals("admin"))
			 {
			 if(attempts>0)
			 {
				 attempts-=1;
				 Query queryrecv = session.getCurrentSession().createQuery("update User set attempts = :pass" +
							" where loginid = :loginidtemprecv");
				
				queryrecv.setParameter("pass",attempts);
				queryrecv.setParameter("loginidtemprecv",userName);
				int result = queryrecv.executeUpdate();
				return "Incorrect login! Your account will be locked after a total of 5 bad login attempts!";
				
				 
				 
			 }
			 else
			 {
				 //have to update user status as locked
				 Query queryrecv = session.getCurrentSession().createQuery("update User set userStatus = :pass" +
							" where loginid = :loginidtemprecv");
				 queryrecv.setParameter("pass","locked");
					queryrecv.setParameter("loginidtemprecv",userName);
					int result = queryrecv.executeUpdate();
				 return "You have already incorrectly logged in 5 times! Please contact an internal user to unlock your account";
			 }
			 
		 }
			 else
			 {
				 return "Admin bad login attempt!";
			 }
		 }
		 else
		 {
			 return "User not found!";
		 }
	}

	@Override
	public String updatecorrectlogin() {
		//NEED TO RESET LOGIN  ATTEMPTS TO 5
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		 Query queryrecv = session.getCurrentSession().createQuery("update User set attempts = :pass" +
					" where loginid = :loginidtemprecv");
		 int attempts = 5;
		
		queryrecv.setParameter("pass",attempts);
		queryrecv.setParameter("loginidtemprecv",loginidreq);
		try
		{
		int result = queryrecv.executeUpdate();
		return "Attempts reset";
		}
		catch(Exception e)
		{
			return "Updating failed";
		}
		
		
	}


	@Override
	public String getstatus() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		     userDetails = (UserDetails) auth.getPrincipal();
		}
		String loginidreq = userDetails.getUsername();
		List<User> accountobjlistsender = ListUtils.lazyList(new ArrayList<User>(),FactoryUtils.instantiateFactory(User.class));
		
		 accountobjlistsender= session.getCurrentSession().createQuery("from User ac where ac.loginid = :sender").setString("sender", loginidreq).list();
		 User obj = accountobjlistsender.get(0);
		 String stat = obj.getStatus();
		 return stat;
		 
	}

	@Override
	public String updateuserStatus() {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
		public List getLockedAccounts() {
			try
			{
			return session.getCurrentSession().createQuery("from User where userStatus='locked'").list();
			}
			catch(Exception e)
			{
	
				
				return null;
			}
		}

	@Override
	public String checkDeleteRequestdao(String id) {
		List<AccountClass> reqobject = ListUtils.lazyList(new ArrayList<AccountClass>(),FactoryUtils.instantiateFactory(AccountClass.class));
		String hql = "FROM request r WHERE r.requesterLoginId = :loginid";
		reqobject = session.getCurrentSession().createQuery(hql).setParameter("loginid",id).list();
		if(reqobject.isEmpty())
		{
			return "null";
		}
		else
		{
			return "present";
		}
	
	}
	



}
	

	




