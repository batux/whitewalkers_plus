package com.whitewalkers.proxy.service.security.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.whitewalkers.proxy.service.security.authentication.filter.CaptchaAuthenticationFilter;
import com.whitewalkers.proxy.service.security.authentication.filter.LoginAuthenticationFilter;
import com.whitewalkers.proxy.service.security.authentication.filter.OTPAuthenticationFilter;
import com.whitewalkers.proxy.service.security.authentication.handler.LoginFailureHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.LoginSuccessHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.OTPFailureHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.OTPSuccessHandler;
import com.whitewalkers.proxy.service.security.authentication.provider.CaptchaAuthenticationProvider;
import com.whitewalkers.proxy.service.security.authentication.provider.LoginAuthenticationProvider;
import com.whitewalkers.proxy.service.security.authentication.provider.OTPAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		LoginAuthenticationProvider loginAuthenticationProvider = new LoginAuthenticationProvider();
		OTPAuthenticationProvider otpAuthenticationProvider = new OTPAuthenticationProvider(loginAuthenticationProvider);
		CaptchaAuthenticationProvider captchaAuthenticationProvider = new CaptchaAuthenticationProvider(otpAuthenticationProvider);
		
		authenticationManagerBuilder.authenticationProvider(loginAuthenticationProvider)
									.authenticationProvider(otpAuthenticationProvider)
									.authenticationProvider(captchaAuthenticationProvider);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter("/login");
		loginAuthenticationFilter.setAuthenticationManager(authenticationManager());
		loginAuthenticationFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
		loginAuthenticationFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
		loginAuthenticationFilter.setAllowSessionCreation(true);
		http.addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		OTPAuthenticationFilter otpAuthenticationFilter = new OTPAuthenticationFilter("/otp");
		otpAuthenticationFilter.setAuthenticationManager(authenticationManager());
		otpAuthenticationFilter.setAuthenticationSuccessHandler(new OTPSuccessHandler());
		otpAuthenticationFilter.setAuthenticationFailureHandler(new OTPFailureHandler());
		otpAuthenticationFilter.setAllowSessionCreation(true);
		http.addFilterBefore(otpAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter("/captcha");
		captchaAuthenticationFilter.setAuthenticationManager(authenticationManager());
		captchaAuthenticationFilter.setAuthenticationSuccessHandler(new OTPSuccessHandler());
		captchaAuthenticationFilter.setAuthenticationFailureHandler(new OTPFailureHandler());
		captchaAuthenticationFilter.setAllowSessionCreation(true);
		http.addFilterBefore(captchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		http.csrf().disable()
		.authorizeRequests()
			.anyRequest()
				.authenticated()
				.antMatchers("/**").permitAll()
				.and()
		        .requestCache()
		        .requestCache(new NullRequestCache());
		
	}
	
}
