package com.whitewalkers.twitter.stream.service.provider;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whitewalkers.common.stream.listener.TweetListener;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TweetStreamProvider {
	
	private final String consumerKey = "U2McHWYh4YiYeuM9OnYAvlD9E";
	private final String consumerSecret = "lkdIU6URMKw3pN7RcU0BTskdaeiDeIzCidlQMosSoSt0w7yzpE";
	
	private final String accessKey = "117547846-n113HVDoHh5p424DRuRqqFqY9hlIHpzG4wUyy57J";
	private final String accessSecret = "3MNRCFos9BjMmzJGg2lrJvBiMFS1GH30LQrFDzTlV6f1R";
	
	private TwitterStream twitterStream;
	private TweetListener tweetListener;
	
	@Autowired
	private TweetServiceProvider tweetServiceProvider;
	
	private volatile boolean continueToListen;
	
	public TweetStreamProvider() {
		
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true)
							.setOAuthConsumerKey(consumerKey)
					        .setOAuthConsumerSecret(consumerSecret)
					        .setOAuthAccessToken(accessKey)
					        .setOAuthAccessTokenSecret(accessSecret);

		
		this.twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
		this.continueToListen = true;
	}
	
	@PostConstruct
	public void postConstructionProcess() {
		
		this.tweetListener = new TweetListener(this.tweetServiceProvider.getTweetDao(), this.tweetServiceProvider.getTweetDocumentAdapter());
		this.twitterStream.addListener(this.tweetListener);
	}
	
	public void startToListenTweetStream() {
		
		this.getTwitterStream().sample();
		
		while(this.continueToListen) {}
	}
	
	public void stopToListenTweetStream() {
		
		this.continueToListen = false;
		this.getTwitterStream().shutdown();
	}
	
	public TwitterStream getTwitterStream() {
		return this.twitterStream;
	}
	
	public TweetListener getTweetListener() {
		return this.tweetListener;
	}
}
