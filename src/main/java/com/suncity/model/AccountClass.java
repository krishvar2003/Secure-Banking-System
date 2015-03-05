package com.suncity.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Account")
public class AccountClass {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long accountno;
	
	String loginid;
	@Column
	double balance=0;
	String accountopendate;
	@Column
	long pkiTime;
	
	public long getPkiTime() {
		return pkiTime;
	}
	public void setPkiTime(long pkiTime) {
		this.pkiTime = pkiTime;
	}
	public long getAccountno() {
		return accountno;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getAccountopendate() {
		return accountopendate;
	}
	public void setAccountopendate(String accountopendate) {
		this.accountopendate = accountopendate;
	}
	public String getLoginId() {
		return loginid;
	}
	public void setLoginId(String loginId) {
		this.loginid = loginId;
	}

	
	

}
