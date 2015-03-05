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
@Table(name="merchantPayments")
public class MerchantPayments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	@Column
	private String customerLoginId;
	@Column
	private String merchantName;
	@Column
	private String amount;
	@Column
	private String PKI;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getCustomerLoginId() {
		return customerLoginId;
	}
	public void setCustomerLoginId(String customerLoginId) {
		this.customerLoginId = customerLoginId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPKI() {
		return PKI;
	}
	public void setPKI(String pKI) {
		PKI = pKI;
	}
	
}
