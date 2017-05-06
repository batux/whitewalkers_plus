package com.whitewalkers.proxy.service.security.authentication.provider;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.whitewalkers.proxy.service.security.authentication.token.UsernamePasswordAuthenticationToken;

public class LoginAuthenticationProvider implements AuthenticationProvider, AuthenticationTokenProvider {

	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken loginAuthentication = (UsernamePasswordAuthenticationToken) authentication;
		
		String userName = (String) loginAuthentication.getPrincipal();
		String password = (String) loginAuthentication.getCredentials();
		
		if(!("batux".equals(userName) && "batux".equals(password))) {
			throw new BadCredentialsException("Login error-1!");
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

		this.usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginAuthentication.getPrincipal(), loginAuthentication.getCredentials(), grantedAuthorities);
		return this.usernamePasswordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		if ( authentication.isAssignableFrom( UsernamePasswordAuthenticationToken.class ) ) {
            return true;
        }
		
		return false;
	}

	@Override
	public Authentication getAuthenticationToken() {
		return this.usernamePasswordAuthenticationToken;
	}

}
