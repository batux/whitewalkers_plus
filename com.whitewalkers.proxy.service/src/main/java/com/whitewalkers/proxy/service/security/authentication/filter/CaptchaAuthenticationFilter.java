package com.whitewalkers.proxy.service.security.authentication.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.whitewalkers.proxy.service.security.authentication.token.CaptchaAuthenticationToken;

public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	public CaptchaAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(request.getParameter("captchacode") == null) {
			return null;
		}
		
		CaptchaAuthenticationToken captchaAuthenticationToken = new CaptchaAuthenticationToken(request.getParameter("captchacode"));
		captchaAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));

		return this.getAuthenticationManager().authenticate(captchaAuthenticationToken);
	}

}
