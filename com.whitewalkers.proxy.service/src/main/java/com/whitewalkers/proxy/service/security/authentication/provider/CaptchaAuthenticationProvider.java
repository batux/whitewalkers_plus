package com.whitewalkers.proxy.service.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.whitewalkers.proxy.service.security.authentication.token.CaptchaAuthenticationToken;

public class CaptchaAuthenticationProvider implements AuthenticationProvider, AuthenticationTokenProvider {

	private AuthenticationProvider previousAuthenticationProvider;
	
	private CaptchaAuthenticationToken captchaAuthentication;
	
	
	public CaptchaAuthenticationProvider(AuthenticationProvider previousAuthenticationProvider) {
		this.previousAuthenticationProvider = previousAuthenticationProvider;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		CaptchaAuthenticationToken captchaAuthentication = (CaptchaAuthenticationToken)authentication;
		
		String captchaCode = (String) captchaAuthentication.getCredentials();
		
		
		Authentication authenticationToken = ((AuthenticationTokenProvider)previousAuthenticationProvider).getAuthenticationToken();
		
//		authenticationToken = previousAuthenticationProvider.authenticate(authenticationToken);
		
		if(authenticationToken == null || !authenticationToken.isAuthenticated()) {
			throw new BadCredentialsException("Login errror-3!");
		}
		
		if(!"1907TURK45".equals(captchaCode)) {
			throw new BadCredentialsException("Login errror-3!");
		}
		
		captchaAuthentication.setAuthenticated(true);
		
		this.captchaAuthentication = captchaAuthentication;
		
		return captchaAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		if ( authentication.isAssignableFrom( CaptchaAuthenticationToken.class ) ) {
            return true;
        }
		
		return false;
	}

	public AuthenticationProvider getPreviousAuthenticationProvider() {
		return previousAuthenticationProvider;
	}

	public void setPreviousAuthenticationProvider(AuthenticationProvider previousAuthenticationProvider) {
		this.previousAuthenticationProvider = previousAuthenticationProvider;
	}

	@Override
	public Authentication getAuthenticationToken() {
		return this.captchaAuthentication;
	}

}
