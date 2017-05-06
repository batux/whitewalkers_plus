package com.whitewalkers.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whitewalkers.common.holder.RequestHolder;
import com.whitewalkers.common.holder.ResponseHolder;
import com.whitewalkers.common.model.RegistrationResult;
import com.whitewalkers.common.model.User;
import com.whitewalkers.user.registration.service.jpa.model.UserEntity;
import com.whitewalkers.user.registration.service.jpa.repository.UserRepository;

@EnableFeignClients
@RestController
@EnableCircuitBreaker
@EnableAspectJAutoProxy
@CrossOrigin(origins="*")
@RequestMapping("/userregistrationservice")
public class UserRegistrationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@HystrixCommand
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseHolder<RegistrationResult> saveUser(@RequestBody RequestHolder<User> requestHolder) {
		
		User user = requestHolder.getPayload();
		
		UserEntity userEntity = new UserEntity();
		userEntity.setName(user.getName());
		userEntity.setSurname(user.getSurname());
		userEntity.setUserName(user.getUserName());
		userEntity.setAge(user.getAge());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setTwoFactorAuthentication(user.isTwoFactorAuthentication());

		
		userRepository.save(userEntity);
		
		ResponseHolder<RegistrationResult> responseHolder = new ResponseHolder<RegistrationResult>();
		
		RegistrationResult registrationResult = new RegistrationResult();
		registrationResult.setRegistrationCompleted(true);
		
		responseHolder.setPayload(registrationResult);
		
		return responseHolder;
	}
	
	@HystrixCommand
	@RequestMapping(value = "/quick/user", method = RequestMethod.GET)
	public ResponseHolder<RegistrationResult> saveUser() {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setName("eteration");
		userEntity.setSurname("eteration");
		userEntity.setUserName("eteration");
		userEntity.setAge(15);
		userEntity.setEmail("eteration@eteration.com");
		userEntity.setPassword("1");
		userEntity.setTwoFactorAuthentication(false);

		userRepository.deleteAll();
		userRepository.save(userEntity);
		
		ResponseHolder<RegistrationResult> responseHolder = new ResponseHolder<RegistrationResult>();
		
		RegistrationResult registrationResult = new RegistrationResult();
		registrationResult.setRegistrationCompleted(true);
		
		responseHolder.setPayload(registrationResult);
		
		return responseHolder;
	}
	
	@HystrixCommand
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ResponseHolder<User> getUser(@PathVariable("username") String username) {
		
		UserEntity userEntity = userRepository.findByUserName(username);
		
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
		
		ResponseHolder<User> responseHolder = new ResponseHolder<User>();
		responseHolder.setPayload(user);
		
		return responseHolder;
	}
	
}
