package com.whitewalkers.twitter.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whitewalkers.twitter.stream.service.provider.TweetStreamProvider;

@RestController
@RequestMapping("/streamservice")
@EnableCircuitBreaker
public class TwitterStreamService {

	@Autowired
	private TweetStreamProvider tweetStreamProvider;

	@HystrixCommand(fallbackMethod = "getTweetsFallback")
	@RequestMapping(value = "/tweetstream/{action}", method = RequestMethod.GET)
	public String manageTweetStream(@PathVariable("action") String streamAction) {
		
		String operationResultMessage = "Unsupported operation type!";
		
		if("open".equals(streamAction)) {
			
			Thread tweetStreamThread = new Thread(new Runnable() {
				
				public void run() {
					tweetStreamProvider.startToListenTweetStream();
				}
			});
			
			tweetStreamThread.start();
			
			operationResultMessage = "Tweet Stream was started!";
		}
		else if("close".equals(streamAction)) {
			this.tweetStreamProvider.stopToListenTweetStream();
			operationResultMessage = "Tweet Stream was stoped!";
		}
		
		return operationResultMessage;
	}
	
	public String manageTweetStreamFallback() {
		return "Service is unreachable now!";
	}
	
}
