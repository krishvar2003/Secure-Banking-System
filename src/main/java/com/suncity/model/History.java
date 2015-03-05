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
@Table(name="history")
public class History {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int historyid;
	@Column
	private String loginid;
	@Column
	private double transamount;
	@Column
	private double balbefore;
	@Column
	private double balafter;
	@Column
	private String type;
	@Column
	private String time;
	
	public String getLoginId() {
		return loginid;
	}
	public void setLoginId(String loginId) {
		this.loginid = loginId;
	}
	
	public int gethistoryId() {
		return historyid;
	}
	public void sethistoryId(int historyId) {
		this.historyid = historyId;
	}
	
	public double gettransamount() {
		return transamount;
	}
	public void settransamount(double transamount) {
		this.transamount = transamount;
	}
	
	public double getbalbefore() {
		return balbefore;
	}
	public void setbalbefore(double balbefore) {
		this.balbefore = balbefore;
	}
	
	public double getbalafter() {
		return balafter;
	}
	public void setbalafter(double balafter) {
		this.balafter= balafter;
	}
	
	public String gettype() {
		return type;
	}
	public void settype(String type) {
		this.type = type;
	}
	
	public String gettime() {
		return time;
	}
	public void settime(String time) {
		this.time = time;
	}

}
