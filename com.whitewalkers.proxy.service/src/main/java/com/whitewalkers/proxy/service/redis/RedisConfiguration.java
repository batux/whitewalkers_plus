package com.whitewalkers.proxy.service.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Value("${spring.redis.port}")
	private int redisPortNo;
	
	
	public int getRedisPortNo() {
		return redisPortNo;
	}

	public void setRedisPortNo(int redisPortNo) {
		this.redisPortNo = redisPortNo;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}
}
