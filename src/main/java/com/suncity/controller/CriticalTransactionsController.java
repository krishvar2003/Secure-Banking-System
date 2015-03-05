package com.suncity.controller;
import com.suncity.model.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.suncity.model.User;
import com.suncity.service.TransactionService;
import com.suncity.service.UserAccountService;

@Controller
public class CriticalTransactionsController {
	@Autowired
	private TransactionService transactionservice;
	
	@Autowired
	private UserAccountService useraccountservice;
	
	
	@RequestMapping("/ViewCriticalTransactions")
	public String showCriticalTransaction(Map<String, Object> map){
		
		if(transactionservice.getPendingTransactions().size()>0)
		{
		map.put("pendingTransactions", transactionservice.getPendingTransactions());
		return "ViewCriticalTransactions";
		}
		else
		{
			return "NoPendingTransactions";
		}
		
			
		
	}
	
	@RequestMapping(value="/ViewCriticalTransactions", method=RequestMethod.POST)
	public String User_SearchPOST(@RequestParam("checkedTransactions") String[] checkedTransactions, Map<String, Object> map)
	{
		
		ArrayList<transaction> transactionsObjects=new ArrayList<transaction>();
		
		ArrayList<transaction> ClearedTransactions=new ArrayList<transaction>();
		ArrayList<transaction> FailedTransaction=new ArrayList<transaction>();
		for(int i=0;i<checkedTransactions.length;i++)
		{
			
		
		transactionsObjects.add(transactionservice.getTransaction(checkedTransactions[i]));
		}
		
		for(transaction trans:transactionsObjects)
		{
			AccountClass senderAccount=useraccountservice.getAccount(trans.getSenderAccountNo());
			AccountClass receiverAccount=useraccountservice.getAccount(trans.getReceiverAccountNo());
			
			if(senderAccount.getBalance()-Double.parseDouble(trans.getAmount())<=10)
			{
				trans.setStatus("Failed");
				FailedTransaction.add(trans);
	
			}
			else
			{
				double newSenderBal=senderAccount.getBalance()-Double.parseDouble(trans.getAmount());
				senderAccount.setBalance(newSenderBal);
				
				
				double newReceiverBal=receiverAccount.getBalance()+Double.parseDouble(trans.getAmount());
				receiverAccount.setBalance(newReceiverBal);
				trans.setStatus("Cleared");
				ClearedTransactions.add(trans);
				
				useraccountservice.editAccount(senderAccount);
				useraccountservice.editAccount(receiverAccount);
				
				
				//modify sender and user account//
			}
			transactionservice.updateTransaction(trans);
		}
		
		map.put("clearedTransactions", ClearedTransactions);
		map.put("failedTransactions", FailedTransaction);
		return "ViewResultTransaction";
	}
	
	@RequestMapping("/addDeleteTransactions")
	public String addDeleteUserTransactions(Map<String, Object> map){
		
			
		return "addDeleteTransactions";
	}
	

	@RequestMapping(value="/DeleteTransactions", method=RequestMethod.POST)
	public String addDeleteUsrTransactions(@RequestParam("txtAccountNo") String AccountNo, @RequestParam("status") String status, Map<String, Object> map, HttpServletRequest request)
	{
		try
		{
		
		List<transaction> pendingTransactions=new ArrayList<transaction>(); 
		List<transaction> clearedTransactions=new ArrayList<transaction>(); 
		List<transaction> failedTransaction=new ArrayList<transaction>(); 
		
		ArrayList<transaction> transac=new ArrayList<transaction>(); 
		
		if(status.equals("Pending"))
		{
			pendingTransactions=transactionservice.getPendingTransactions();
 			for(transaction trans:pendingTransactions){
				
				if(trans.getSenderAccountNo().equals(AccountNo))
				{
					String accountno=trans.getSenderAccountNo();
					transac.add(trans);
				}
				
			}
		}
		
		else if(status.equals("Cleared"))
		{
			clearedTransactions=transactionservice.getClearedTransactions();
			for(transaction trans:clearedTransactions){
				
				if(trans.getSenderAccountNo().equals(AccountNo))
				{
					transac.add(trans);
				}
				
			}
		}
		
		else if(status.equals("Failed"))
		{
			failedTransaction=transactionservice.getFailedTransactions();
			for(transaction trans:failedTransaction){
				
				if(trans.getSenderAccountNo().equals(AccountNo))
				{
					transac.add(trans);
				}
				
			}
		}
		
		if(transac.size()>0)
		{
			map.put("pendingTransactions", transac);
			return "DeleteTransactionsList";
				
		}
		else
		{
			return "NoPendingTransactions";
		}
		}
		catch(Exception e)
		{
			map.put("Result", "Error in Input");
			return "ErrorPage";
		}
		//	return "DeleteTransactionsList";
	}
		
	
	@RequestMapping(value="/performDeletion", method=RequestMethod.POST)
	public String delete_Transactions(@RequestParam("checkedTransactions") String[] checkedTransactions, Map<String, Object> map)
	{
		
		//ArrayList<transaction> transactionsObjects=new ArrayList<transaction>();
		for(int i=0;i<checkedTransactions.length;i++)
		{
			String resultFunc=transactionservice.deleteTransaction(checkedTransactions[i]);
		
			if(resultFunc!="Success")
			{
			map.put("Result", resultFunc);
			
			return "ErrorPage";	
			}
		}
		
		return "DeleteTransactions";
	}

}
