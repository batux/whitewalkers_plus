package com.whitewalkers.tweet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.whitewalkers.common.model.SpatialQuery;
import com.whitewalkers.common.model.Tweet;
import com.whitewalkers.tweet.service.provider.TweetServiceProvider;

@SpringBootApplication
@RestController
@RequestMapping("/tweetservice")
@EnableEurekaClient
public class TweetServiceSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TweetServiceSpringApplication.class, args);
	}
	
	@Autowired
	private TweetServiceProvider tweetServiceProvider;

	@RequestMapping(value = "/tweets", method = RequestMethod.GET)
	public List<Tweet> getTweetsWhichContainsGeoLocation() {
		
		List<Tweet> tweets = this.tweetServiceProvider.getTweets("geo", -1);
		
		return tweets;
	}
	
	
	@RequestMapping(value = "/tweets", method = RequestMethod.POST)
	public List<Tweet> getTweetsWithinSpatialQuery(@RequestBody SpatialQuery spatialQuery) {
		
		List<Tweet> tweets = this.tweetServiceProvider.getTweets(spatialQuery);
		
		return tweets;
	}
}
