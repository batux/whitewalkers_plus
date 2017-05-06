package com.whitewalkers.proxy.service.security.authentication.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.whitewalkers.proxy.service.security.authentication.token.UsernamePasswordAuthenticationToken;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	public LoginAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(request.getParameter("username") == null && request.getParameter("password") == null) {
			return null;
		}
		
		
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"));
        usernamePasswordAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
        
		return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
	}
	
}
