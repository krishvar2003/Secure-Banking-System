package com.suncity.service;

import java.util.List;

import com.suncity.model.transaction;

public interface TransactionService {
	public List getClearedTransactions();
	public List getPendingTransactions();
	public List getFailedTransactions();
	public transaction getTransaction(String transactionId);
	public String updateTransaction(transaction trans);
	String deleteTransaction(String transactionId);
}
