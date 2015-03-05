package com.suncity.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suncity.dao.UserRegDao;
import com.suncity.model.UserReg;

@Repository
public class UserRegDaoImpl implements UserRegDao {

	static Logger log = Logger.getLogger(UserRegDaoImpl.class.getName());
	
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public List getAllRegRequests() {
		try
		{
			
			log.info("Trying to get all User Registration Requests");
	return session.getCurrentSession().createQuery("from UserReg").list();
		}
		catch(Exception e)
		{
			log.error("ERROR Received trying to retreive all registration requests");
			log.error("ERROR :"+e.getMessage());
			return null;
		}
	}

	@Override
	public UserReg getRegRequest(String loginid) {

		try
		{
			log.info("Trying to get User Registration Request" +loginid);
	return (UserReg)session.getCurrentSession().get(UserReg.class, loginid);
		}
		catch(Exception e)
		{
			log.error("ERROR Received trying to retreive registration request : "+loginid);
			log.error("ERROR :"+e.getMessage());
			return null;
		}
	}

	@Override
	public String deleteRegRequest(String loginid) {
	
		try
		{
			session.getCurrentSession().delete(getRegRequest(loginid));
			log.info("Deleted User Registration Request" +loginid);
			return "Success";
		}
		catch(Exception e)
		{
			log.error("ERROR Deleting registration request : "+loginid);
			log.error("ERROR :"+e.getMessage());
			return e.getMessage();
		}
	}

}
