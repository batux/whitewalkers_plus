package com.whitewalkers.proxy.service.security.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.whitewalkers.proxy.service.security.authentication.filter.CaptchaAuthenticationFilter;
import com.whitewalkers.proxy.service.security.authentication.filter.OTPAuthenticationFilter;
import com.whitewalkers.proxy.service.security.authentication.handler.CaptchaFailureHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.CaptchaSuccessHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.LoginFailureHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.LoginSuccessHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.LogoutSuccessHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.OTPFailureHandler;
import com.whitewalkers.proxy.service.security.authentication.handler.OTPSuccessHandler;
import com.whitewalkers.proxy.service.security.authentication.provider.CaptchaAuthenticationProvider;
import com.whitewalkers.proxy.service.security.authentication.provider.LoginAuthenticationProvider;
import com.whitewalkers.proxy.service.security.authentication.provider.OTPAuthenticationProvider;
import com.whitewalkers.proxy.service.security.authentication.token.OTPAuthenticationToken;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		LoginAuthenticationProvider loginAuthenticationProvider = new LoginAuthenticationProvider();
		OTPAuthenticationProvider otpAuthenticationProvider = new OTPAuthenticationProvider();
		CaptchaAuthenticationProvider captchaAuthenticationProvider = new CaptchaAuthenticationProvider();
		
		authenticationManagerBuilder.authenticationProvider(loginAuthenticationProvider)
									.authenticationProvider(otpAuthenticationProvider)
									.authenticationProvider(captchaAuthenticationProvider);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		OTPAuthenticationFilter otpAuthenticationFilter = new OTPAuthenticationFilter("/otp");
		otpAuthenticationFilter.setAuthenticationManager(authenticationManager());
		otpAuthenticationFilter.setAuthenticationSuccessHandler(new OTPSuccessHandler());
		otpAuthenticationFilter.setAuthenticationFailureHandler(new OTPFailureHandler());
		otpAuthenticationFilter.setAllowSessionCreation(true);
		otpAuthenticationFilter.setPreviousAuthenticationTokenClass(UsernamePasswordAuthenticationToken.class);
		
		
		CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter("/captcha");
		captchaAuthenticationFilter.setAuthenticationManager(authenticationManager());
		captchaAuthenticationFilter.setAuthenticationSuccessHandler(new CaptchaSuccessHandler());
		captchaAuthenticationFilter.setAuthenticationFailureHandler(new CaptchaFailureHandler());
		captchaAuthenticationFilter.setAllowSessionCreation(true);
		captchaAuthenticationFilter.setPreviousAuthenticationTokenClass(OTPAuthenticationToken.class);

		
		http.csrf().disable()
		.authorizeRequests()
			.anyRequest()
				.authenticated()
				.antMatchers("/**").permitAll()
				.and()
		        .requestCache()
		        .requestCache(new NullRequestCache())
		    .and()
			    .formLogin()
			        .loginPage("/login")
			        .successHandler(new LoginSuccessHandler())
			        .failureHandler(new LoginFailureHandler())
		    .and()
			    .logout()
			        .logoutUrl("/logout")
			        .logoutSuccessHandler(new LogoutSuccessHandler()).invalidateHttpSession(true)
		    .and()
		    	.addFilterBefore(otpAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(captchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
}
