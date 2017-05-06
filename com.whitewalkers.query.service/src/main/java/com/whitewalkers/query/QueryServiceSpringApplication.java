package com.whitewalkers.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QueryServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryServiceSpringApplication.class, args);
	}
}
