package com.whitewalkers.proxy.service.security.authentication.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CaptchaAuthenticationToken extends AbstractAuthenticationToken{

	private static final long serialVersionUID = 1451632442464301362L;
	
	private String captchaCode;
	
	public CaptchaAuthenticationToken(String captchaCode) {
		super(null);
		this.captchaCode = captchaCode;
	}

	@Override
	public Object getCredentials() {
		return this.captchaCode;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

}
