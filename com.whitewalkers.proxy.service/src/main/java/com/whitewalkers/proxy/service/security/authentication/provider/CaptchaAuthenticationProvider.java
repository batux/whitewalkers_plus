package com.whitewalkers.proxy.service.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.whitewalkers.proxy.service.security.authentication.token.CaptchaAuthenticationToken;

public class CaptchaAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		CaptchaAuthenticationToken captchaAuthentication = (CaptchaAuthenticationToken)authentication;
		
		String captchaCode = (String) captchaAuthentication.getCredentials();
		
		if(!"1907TURK45".equals(captchaCode)) {
			throw new BadCredentialsException("Login errror-3!");
		}
		
		captchaAuthentication.setAuthenticated(true);
		
		return captchaAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		if ( authentication.isAssignableFrom( CaptchaAuthenticationToken.class ) ) {
            return true;
        }
		
		return false;
	}

}
