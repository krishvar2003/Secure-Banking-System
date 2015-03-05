package com.suncity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.TransactionDao;
import com.suncity.model.transaction;
import com.suncity.service.TransactionService;
@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionDao transactiondao;
	
	@Transactional
	public List getClearedTransactions() {
		return transactiondao.getClearedTransactions();
	}

	@Transactional
	public List getPendingTransactions() {
		return transactiondao.getPendingTransactions();
	}

	@Transactional
	public List getFailedTransactions() {
		return transactiondao.getFailedTransactions();
		
	}

	@Override
	@Transactional
	public transaction getTransaction(String transactionId) {
		return transactiondao.getTransaction(transactionId);
	}

	@Override
	@Transactional
	public String updateTransaction(transaction trans) {
		return transactiondao.updateTransaction(trans);
		
		
	}
	
	@Override
	@Transactional
	public String deleteTransaction(String transactionId) {
		return transactiondao.deleteTransaction(transactionId);
		
		
	}

}
