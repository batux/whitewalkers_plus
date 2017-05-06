package com.whitewalkers.proxy.service.security.authentication.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class OTPAuthenticationToken extends AbstractAuthenticationToken{

	private static final long serialVersionUID = -8747568727928246247L;

	private String verificationCode;
	
	public OTPAuthenticationToken(String verificationCode) {
		super(null);
		this.verificationCode = verificationCode;
	}

	@Override
	public Object getCredentials() {
		return this.verificationCode;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

}
