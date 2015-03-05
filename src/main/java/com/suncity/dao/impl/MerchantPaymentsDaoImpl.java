package com.suncity.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suncity.dao.MerchantPaymentsDao;
import com.suncity.model.MerchantPayments;

@Repository
public class MerchantPaymentsDaoImpl implements MerchantPaymentsDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void addPayments(MerchantPayments merchantPayments) {
		try {
			session.getCurrentSession().save(merchantPayments);
		}
		catch (Exception e ) {
				System.out.println(e.getMessage());
		}
	}

}
