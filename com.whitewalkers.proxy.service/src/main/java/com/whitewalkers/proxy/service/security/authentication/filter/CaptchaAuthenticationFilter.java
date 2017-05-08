package com.whitewalkers.proxy.service.security.authentication.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.whitewalkers.proxy.service.security.authentication.token.CaptchaAuthenticationToken;

public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	private Class<?> previousAuthenticationTokenClass;
	
	public CaptchaAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(request.getParameter("captchacode") == null) {
			return null;
		}
		
		if(request.getHeader("X-Auth-Token") == null) {
			response.setStatus(401);
			return null;
		}
		
		Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
		
		if(authenticationToken == null) {
			response.setStatus(401);
			return null;
		}
		else {
			if(authenticationToken.getClass() == this.previousAuthenticationTokenClass) {
				
				if(!authenticationToken.isAuthenticated()) {
					response.setStatus(401);
					return null;
				}
				
				CaptchaAuthenticationToken captchaAuthenticationToken = new CaptchaAuthenticationToken(request.getParameter("captchacode"));
				captchaAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
				
				return this.getAuthenticationManager().authenticate(captchaAuthenticationToken);
			}
		}
		
		return this.getAuthenticationManager().authenticate(authenticationToken);
	}

	public Class<?> getPreviousAuthenticationTokenClass() {
		return previousAuthenticationTokenClass;
	}

	public void setPreviousAuthenticationTokenClass(Class<?> previousAuthenticationTokenClass) {
		this.previousAuthenticationTokenClass = previousAuthenticationTokenClass;
	}

}
