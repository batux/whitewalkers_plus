package com.whitewalkers.proxy.service.security.authentication.token;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuthenticationToken extends org.springframework.security.authentication.UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = 1694431509727235150L;

	public UsernamePasswordAuthenticationToken(Object userName, Object password) {
		super(userName, password);
	}
	
	public UsernamePasswordAuthenticationToken(Object userName, Object password, Collection<? extends GrantedAuthority> authorities) {
		super(userName, password, authorities);
	}
}
