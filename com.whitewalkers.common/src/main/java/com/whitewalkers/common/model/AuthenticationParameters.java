package com.whitewalkers.common.model;

import java.io.Serializable;

public class AuthenticationParameters implements Serializable {

	private static final long serialVersionUID = 4668052980996977261L;
	
	private String userName;
	private String password;
	private String email;
	private String secretKey;
	private boolean twoFactorAuthentication;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isTwoFactorAuthentication() {
		return twoFactorAuthentication;
	}
	public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
		this.twoFactorAuthentication = twoFactorAuthentication;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
}
