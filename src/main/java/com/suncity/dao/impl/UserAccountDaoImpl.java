package com.suncity.dao.impl;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suncity.dao.UserAccountdao;
import com.suncity.model.AccountClass;
import com.suncity.model.User;

@Repository
public class UserAccountDaoImpl implements UserAccountdao {

	static Logger log = Logger.getLogger(UserAccountDaoImpl.class.getName());
	@Autowired
	private SessionFactory session;
	
	@Override
	public void addUserAccountDaoImpl(AccountClass d) {
		
		
		try {
			log.info("Successful Addition of user");
		session.getCurrentSession().save(d);
		}
		catch (Exception e ) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	@Override
	public AccountClass getAccount(String accountno)	{
		
		return (AccountClass)session.getCurrentSession().get(AccountClass.class, Long.parseLong(accountno));
		
	}
	
	@Override
	public void editUserAccount(AccountClass d) {
		 session.getCurrentSession().update(d);
	}
	@Override
	public AccountClass getAccountId(String toVerifyUser) {
		Criteria criteria = session.getCurrentSession().createCriteria(AccountClass.class);
		criteria.add(Restrictions.eq("loginid", toVerifyUser));		
		return (AccountClass) criteria.uniqueResult();
	}
	@Override
	public void editAccount(AccountClass userAccount) {
		session.getCurrentSession().update(userAccount);
		
	}
	@Override
	public String deleteUserAccount(String accountno) {
		try
		{
			session.getCurrentSession().delete(getAccount(accountno));
			return "Success";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}

}
