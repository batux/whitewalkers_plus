package com.whitewalkers.proxy.service.security.authentication.provider;

import org.springframework.security.core.Authentication;

public interface AuthenticationTokenProvider {

	public Authentication getAuthenticationToken();
}
