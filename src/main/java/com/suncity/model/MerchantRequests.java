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
@Table(name="merchantRequest")
public class MerchantRequests {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int requestId;
	@Column
	private String merchantLoginId;
	@Column
	private String customerLoginId;
	@Column
	private int amount;
	@Column
	private String status;
	@Column
	private String PKI;
	public String getPKI() {
		return PKI;
	}
	public void setPKI(String pKI) {
		PKI = pKI;
	}
	public int getRequesttId() {
		return requestId;
	}
	public void setRequesttId(int requesttId) {
		this.requestId = requesttId;
	}
	public String getMerchantLoginId() {
		return merchantLoginId;
	}
	public void setMerchantLoginId(String merchantLoginId) {
		this.merchantLoginId = merchantLoginId;
	}
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getCustomerLoginId() {
		return customerLoginId;
	}
	public void setCustomerLoginId(String customerLoginId) {
		this.customerLoginId = customerLoginId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}	
	
}
