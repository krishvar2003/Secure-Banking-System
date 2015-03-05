package com.suncity.formbean;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserFormDisplayBean {
	
	private String name;
	
	private String loginid;
	private String gender;
	private String address;
	private int age;
	private String email;
	private long phoneno;
	private String role;
	private int attempts=5;
	
	public UserFormDisplayBean(){}
	public UserFormDisplayBean(String name, String loginid, String gender,
			String address, int age, String email, long phoneno, String role,
			int attempts) {
		super();
		this.name = name;
		this.loginid = loginid;
		this.gender = gender;
		this.address = address;
		this.age = age;
		this.email = email;
		this.phoneno = phoneno;
		this.role = role;
		this.attempts = attempts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	
	
}
