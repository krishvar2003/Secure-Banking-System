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
@Table(name="UserOTP")
public class UserOTP {
	@Column
	@Id
	private String loginid;
	@Column
	private String otp;
	@Column
	private String timecreated;
	
	public String getLoginId() {
		return loginid;
	}
	public void setLoginId(String loginId) {
		this.loginid = loginId;
	}
	
	public String getOTP() {
		return otp;
	}
	public void setOTP(String otp) {
		this.otp = otp;
	}
	
	public String getOTPtime() {
		return timecreated;
	}
	public void setAccountopendate(String otptime) {
		this.timecreated = otptime;
	}

}
