package com.whitewalkers.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LoginServiceSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LoginServiceSpringApplication.class, args);
	}
	
}
