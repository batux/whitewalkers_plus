package com.whitewalkers.twitter.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.whitewalkers.twitter.stream.service.provider.TweetStreamProvider;

@SpringBootApplication
@RestController
@RequestMapping("/streamservice")
@EnableEurekaClient
public class TwitterStreamSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterStreamSpringApplication.class, args);
	}

	@Autowired
	private TweetStreamProvider tweetStreamProvider;

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
}
