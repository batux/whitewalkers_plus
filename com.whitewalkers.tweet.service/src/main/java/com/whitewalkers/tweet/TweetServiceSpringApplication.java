package com.whitewalkers.tweet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TweetServiceSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TweetServiceSpringApplication.class, args);
	}
	
}
