package com.whitewalkers.proxy.service.security.authentication.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.whitewalkers.proxy.service.security.authentication.token.OTPAuthenticationToken;

public class OTPAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	public OTPAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(request.getParameter("verificationcode") == null) {
			return null;
		}
		
		OTPAuthenticationToken otpAuthenticationToken = new OTPAuthenticationToken(request.getParameter("verificationcode"));
		otpAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));

		return this.getAuthenticationManager().authenticate(otpAuthenticationToken);
	}

}
