package com.whitewalkers.common.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -7189840610250226308L;
	
	private String name;
	private String surname;
	private String userName;
	private String email;
	private String password;
	private String authenticationSecretKey;
	
	private int age;
	
	private boolean twoFactorAuthentication;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAuthenticationSecretKey() {
		return authenticationSecretKey;
	}
	public void setAuthenticationSecretKey(String authenticationSecretKey) {
		this.authenticationSecretKey = authenticationSecretKey;
	}
	public boolean isTwoFactorAuthentication() {
		return twoFactorAuthentication;
	}
	public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
		this.twoFactorAuthentication = twoFactorAuthentication;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
