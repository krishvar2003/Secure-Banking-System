package com.suncity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="request")
public class request {
	@Column
	private long accountno;
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int requestid;
	@Column
	private String requesterLoginId;
	@Column
	private String requesttype;
	@Column
	private String requeststatus;
	@Column
	private String timestamp;
	@Column
	private String requestlevel;
	@Column
	private String requestmsg;
	public long getAccountno() {
		return accountno;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	public int getRequestid() {
		return requestid;
	}
	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	public String getRequesterLoginId() {
		return requesterLoginId;
	}
	public void setRequesterLoginId(String requesterLoginId) {
		this.requesterLoginId = requesterLoginId;
	}
	public String getRequesttype() {
		return requesttype;
	}
	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}
	public String getRequeststatus() {
		return requeststatus;
	}
	public void setRequeststatus(String requeststatus) {
		this.requeststatus = requeststatus;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getRequestlevel() {
		return requestlevel;
	}
	public void setRequestlevel(String requestlevel) {
		this.requestlevel = requestlevel;
	}
	public String getRequestmsg() {
		return requestmsg;
	}
	public void setRequestmsg(String requestmsg) {
		this.requestmsg = requestmsg;
	}
	
}
