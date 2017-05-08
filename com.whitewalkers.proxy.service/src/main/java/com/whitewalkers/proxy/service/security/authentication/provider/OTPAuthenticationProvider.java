package com.whitewalkers.proxy.service.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.whitewalkers.proxy.service.security.authentication.token.OTPAuthenticationToken;


public class OTPAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		OTPAuthenticationToken otpAuthentication = (OTPAuthenticationToken)authentication;
		
		String verificationCode = (String) otpAuthentication.getCredentials();
		
		if(!"123".equals(verificationCode)) {
			throw new BadCredentialsException("Login errror-2!");
		}
		
		otpAuthentication.setAuthenticated(true);
		
		return otpAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		if ( authentication.isAssignableFrom( OTPAuthenticationToken.class ) ) {
            return true;
        }
		
		return false;
	}

}
