package com.suncity.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suncity.dao.MerchantPaymentsDao;
import com.suncity.dao.MerchantRequestsDao;
import com.suncity.model.AccountClass;
import com.suncity.model.MerchantPayments;
import com.suncity.model.MerchantRequests;

@Repository
public class MerchantRequestsDaoImpl implements MerchantRequestsDao {
	static Logger log = Logger.getLogger(UserDaoImpl.class.getName());
	@Autowired
	private SessionFactory session;
	
	@Override
	public String placeRequest(MerchantRequests merchantRequests) {
		try {
			log.info("Adding merchant request to request table");
			session.getCurrentSession().save(merchantRequests);
			return "success";
		}
		catch (Exception e ) {
				System.out.println(e.getMessage());
				return "error";
		}
	}

	@Override
	public List<MerchantRequests> getPaymentList(String username) {
		List<MerchantRequests> merchantList = ListUtils.lazyList(new ArrayList<MerchantRequests>(),FactoryUtils.instantiateFactory(MerchantRequests.class));
		
		merchantList = session.getCurrentSession().createQuery("from MerchantRequests mr where mr.merchantLoginId = :uname").setString("uname", username).list();
		
		return merchantList;
		
	}

	@Override
	public List<MerchantRequests> getUserPaymentsList(String username) {
		List<MerchantRequests> merchantList = ListUtils.lazyList(new ArrayList<MerchantRequests>(),FactoryUtils.instantiateFactory(MerchantRequests.class));
		
		merchantList = session.getCurrentSession().createQuery("from MerchantRequests mr where mr.customerLoginId = ? and mr.status = ?").setString(0, username).setParameter(1, "MerchantInitiated").list();
		
		return merchantList;
	}

public List<MerchantRequests> getLastRequestdaoimpl(String username) {
		
		List<MerchantRequests> merchantList = ListUtils.lazyList(new ArrayList<MerchantRequests>(),FactoryUtils.instantiateFactory(MerchantRequests.class));
		merchantList = session.getCurrentSession().createQuery("from MerchantRequests mr where mr.merchantLoginId = :uname and mr.status= 'CustomerApproved' order by requestId desc").setString("uname", username).setMaxResults(1).list();
		return merchantList;
	}

	public void updateStatusdaoimpl(MerchantRequests merchant)
	{
		session.getCurrentSession().update(merchant);
	}

	@Override
	public List<MerchantRequests> getMerchantRequests() {
		List<MerchantRequests> merchantList = ListUtils.lazyList(new ArrayList<MerchantRequests>(),FactoryUtils.instantiateFactory(MerchantRequests.class));
		merchantList = session.getCurrentSession().createQuery("from MerchantRequests mr").list();
		return merchantList;
	}

	@Override
	public List<MerchantRequests> getMerchantRequest() {
		List<MerchantRequests> merchantList = ListUtils.lazyList(new ArrayList<MerchantRequests>(),FactoryUtils.instantiateFactory(MerchantRequests.class));
		merchantList = session.getCurrentSession().createQuery("from MerchantRequests mr where mr.status= 'MerchantApproved'").list();
		return merchantList;
	}

	@Override
	public MerchantRequests getRequestById(int requestId) {
		return (MerchantRequests) session.getCurrentSession().get(MerchantRequests.class, requestId);
	}
}
