package com.whitewalkers.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whitewalkers.common.holder.RequestHolder;
import com.whitewalkers.common.holder.ResponseHolder;
import com.whitewalkers.common.model.AuthenticationParameters;
import com.whitewalkers.common.model.User;
import com.whitewalkers.login.service.jpa.model.UserEntity;
import com.whitewalkers.login.service.jpa.repository.UserRepository;

@RestController
@EnableCircuitBreaker
@EnableAspectJAutoProxy
@CrossOrigin(origins="*")
@RequestMapping("/")
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;
	
	
	@HystrixCommand
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseHolder<AuthenticationParameters> login(@RequestBody RequestHolder<String> requestHolder) {
		
		String userName = requestHolder.getPayload();
		
		ResponseHolder<AuthenticationParameters> responseHolder = new ResponseHolder<>();
		
		UserEntity userEntity = userRepository.findByUserName(userName);
		
		User user = null;
		
		if(userEntity != null) {
			user = new User();
			user.setName(userEntity.getName());
			user.setSurname(userEntity.getSurname());
			user.setUserName(userEntity.getUserName());
			user.setAge(userEntity.getAge());
			user.setAuthenticationSecretKey(userEntity.getAuthenticationSecretKey());
			user.setEmail(userEntity.getEmail());
			user.setPassword(userEntity.getPassword());
			user.setTwoFactorAuthentication(userEntity.isTwoFactorAuthentication());
		}
		
		
		AuthenticationParameters authenticationParameters = null;
		
		if(user != null) {
			authenticationParameters = new AuthenticationParameters();
			authenticationParameters.setUserName(user.getUserName());
			authenticationParameters.setPassword(user.getPassword());
			authenticationParameters.setEmail(user.getEmail());
			authenticationParameters.setTwoFactorAuthentication(user.isTwoFactorAuthentication());
			authenticationParameters.setSecretKey(user.getAuthenticationSecretKey());
		}
		
		responseHolder.setPayload(authenticationParameters);
		
		return responseHolder;
	}
	
}
