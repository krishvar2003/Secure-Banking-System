package com.suncity.dao;

import java.util.List;

import com.suncity.model.transaction;

public interface TransactionDao {
	
	public List getPendingTransactions();
	public List getClearedTransactions();
	public List getFailedTransactions();
	public transaction getTransaction(String transactionId);
	public String updateTransaction(transaction trans);
	public String deleteTransaction(String transactionId);
	
	
}
