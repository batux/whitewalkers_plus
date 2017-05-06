package com.whitewalkers.proxy.service.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.whitewalkers.proxy.service.redis.RedisConfiguration;

@Configuration
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class SessionConfiguration {

	@Autowired
	private RedisConfiguration redisConfiguration;
	
	@Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
	
	@Bean
	public LettuceConnectionFactory connectionFactory() {
	    return new LettuceConnectionFactory(redisConfiguration.getRedisHost(), redisConfiguration.getRedisPortNo()); 
	}
	
}
