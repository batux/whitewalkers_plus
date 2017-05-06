package com.whitewalkers.twitter.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TwitterStreamSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterStreamSpringApplication.class, args);
	}

}
