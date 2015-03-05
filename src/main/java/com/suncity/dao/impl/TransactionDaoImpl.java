package com.suncity.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suncity.dao.TransactionDao;
import com.suncity.model.transaction;
@Repository
public class TransactionDaoImpl implements TransactionDao{
	
	static Logger log = Logger.getLogger(TransactionDaoImpl.class.getName());
	@Autowired
	private SessionFactory session;
	@Override
	public List getPendingTransactions() {
		try
		{
		log.info("Pending Transactions List Successfully Retreived");
		
		return session.getCurrentSession().createQuery("from transaction where status='Pending'").list();
		}
		catch(Exception e)
		{
			log.error("Pending Transactions List Could Not be Retreived");
			log.error("ERROR "+e.getMessage());
		return null;	
		}
		}
	@Override
	public List getClearedTransactions() {
		try
		{
		log.info("Cleared Transactions List Successfully Retreived");
		
		return session.getCurrentSession().createQuery("from transaction where status='Cleared'").list();
		}
		catch(Exception e)
		{
			log.error("Cleared Transactions List Could Not be Retreived");
			log.error("ERROR "+e.getMessage());
		return null;		
		}
		}
	@Override
	public List getFailedTransactions() {
		try
		{
		log.info("Failed Transactions List Successfully Retreived");
		
		return session.getCurrentSession().createQuery("from transaction where status='Failed'").list();
		}
		catch(Exception e)
		{
			log.error("Failed Transactions List Could Not be Retreived");
			log.error("ERROR "+e.getMessage());
		return null;
		}
		}
	@Override
	public transaction getTransaction(String transactionId) {
		try
		{
		log.info("Trasaction Retreived Transaction ID : "+transactionId);
			return (transaction)session.getCurrentSession().get(transaction.class, Integer.parseInt(transactionId));
		}
		catch(Exception e)
		{
			log.error("Transaction Could Not be Retreived Transaction ID: "+transactionId);
			log.error("ERROR "+e.getMessage());
		return null;
		}
		}
	@Override
	public String updateTransaction(transaction trans) {
		try
		{
		session.getCurrentSession().update(trans);
		log.info("Transaction Sucessfully Updated Transaction ID: "+trans.getTransactionId());
		return "Success";
		}
		catch(Exception e)
		{
			log.error("Transaction Could Not be Updated Transaction ID" + trans.getTransactionId());
			log.error("ERROR "+e.getMessage());
			return e.getMessage();
		
		}
	}
	
	public String deleteTransaction(String transactionId)
	{
		try
		{
			
		session.getCurrentSession().delete(getTransaction(transactionId));
		log.info("Transaction Deleted : Transaction ID "+transactionId);
		return "Success";
		}
		catch(Exception e)
		{
			log.error("Transaction Could Not be Deleted Transaction ID" + transactionId);
			log.error("ERROR "+e.getMessage());	
			return e.getMessage();
		}
		}
	
}
