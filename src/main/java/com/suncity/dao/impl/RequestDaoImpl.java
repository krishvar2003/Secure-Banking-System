package com.suncity.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suncity.dao.RequestDao;
import com.suncity.model.request;
@Repository
public class RequestDaoImpl implements RequestDao {
	static Logger log = Logger.getLogger(RequestDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public String deleteRequest(String requestid) {
		try
		{
			session.getCurrentSession().delete(getRequest(requestid));
			log.info("Deleted Request :" +requestid);
			return "Success";
		}
		catch(Exception e)
		{
			log.error("ERROR : deleting Request : "+requestid);
			log.error(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public List getRequests() {
		try
		{
			log.info("Retreiving Request List");
		return session.getCurrentSession().createQuery("from request").list();
		}
		catch(Exception e)
		{
			log.error("ERROR : retreiving request list");
			log.error(e.getMessage());
			return null;
		}
		}

	@Override
	public List getDeleteRequests() {
		try
		{
			log.info("Retreiving Request Delete List");
		return session.getCurrentSession().createQuery("from request where requesttype='Account Delete'").list();
		}
		catch(Exception e)
		{
			log.error("ERROR : retreiving delete request list");
			log.error(e.getMessage());
			return null;
		}
		}
	
	@Override
	public request getRequest(String requestid) {
		try{
			log.info("Access Request for" +requestid);
		return (request)session.getCurrentSession().get(request.class, Integer.parseInt(requestid));
		}
		catch(Exception e)
		{
			log.error("ERROR : Retreiving Request for :" +requestid);
			log.error(e.getMessage());
			return null;
		}
		}

}
