package com.whitewalkers.proxy.service.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.whitewalkers.proxy.service.security.authentication.token.OTPAuthenticationToken;

public class OTPAuthenticationProvider implements AuthenticationProvider, AuthenticationTokenProvider {

	private AuthenticationProvider previousAuthenticationProvider;
	
	private OTPAuthenticationToken otpAuthentication;
	
	
	public OTPAuthenticationProvider(AuthenticationProvider previousAuthenticationProvider) {
		this.previousAuthenticationProvider = previousAuthenticationProvider;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		OTPAuthenticationToken otpAuthentication = (OTPAuthenticationToken)authentication;
		
		String verificationCode = (String) otpAuthentication.getCredentials();
		
		
		Authentication authenticationToken = ((AuthenticationTokenProvider)previousAuthenticationProvider).getAuthenticationToken();
		
//		authenticationToken = previousAuthenticationProvider.authenticate(authenticationToken);
		
		if(authenticationToken == null || !authenticationToken.isAuthenticated()) {
			throw new BadCredentialsException("Login errror-2!");
		}
		
		if(!"123".equals(verificationCode)) {
			throw new BadCredentialsException("Login errror-2!");
		}
		
		otpAuthentication.setAuthenticated(true);
		
		this.otpAuthentication = otpAuthentication;
		
		return otpAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		if ( authentication.isAssignableFrom( OTPAuthenticationToken.class ) ) {
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
		return this.otpAuthentication;
	}

}
