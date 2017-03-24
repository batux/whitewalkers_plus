package com.whitewalkers.common.stream.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whitewalkers.common.adapter.document.TweetDocumentAdapter;
import com.whitewalkers.common.dao.implementation.TweetDao;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TweetListener implements StatusListener {

	private int storedTweetCount = 0;
	
	private ObjectMapper objectMapper;
	
	private TweetDao tweetDao;
	
	private TweetDocumentAdapter tweetDocumentAdapter;
	
	public TweetListener() {
		super();
		this.objectMapper = new ObjectMapper();
	}
	
	public TweetListener(TweetDao tweetDao, TweetDocumentAdapter tweetDocumentAdapter) {
		this();
		this.setTweetDao(tweetDao);
		this.setTweetDocumentAdapter(tweetDocumentAdapter);
	}
	
	public void onException(Exception exception) {
		System.out.println(exception.getMessage());
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		System.out.println(statusDeletionNotice.toString());
	}

	public void onScrubGeo(long arg0, long arg1) {
		System.out.println("X: " + arg0 + ", Y: " + arg1);
	}

	public void onStallWarning(StallWarning stallWarning) {
		System.out.println("Code: " + stallWarning.getCode() + ", Message: " + stallWarning.getMessage());
	}

	public void onStatus(Status status) {
		
		if(status != null) {
			
			try {
				
				String tweetAsJsonText = objectMapper.writeValueAsString(status);
				
				org.bson.Document tweetAsDocument = this.tweetDocumentAdapter.convertTextToDocument(tweetAsJsonText);
				
				this.tweetDao.insertDocument(tweetAsDocument);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void onTrackLimitationNotice(int arg0) {
		System.out.println("TrackLimitation: " + arg0);
	}

	public int getStoredTweetCount() {
		return storedTweetCount;
	}

	public void setStoredTweetCount(int storedTweetCount) {
		this.storedTweetCount = storedTweetCount;
	}

	public TweetDocumentAdapter getTweetDocumentAdapter() {
		return tweetDocumentAdapter;
	}

	public void setTweetDocumentAdapter(TweetDocumentAdapter tweetDocumentAdapter) {
		this.tweetDocumentAdapter = tweetDocumentAdapter;
	}
	
	public TweetDao getTweetDao() {
		return tweetDao;
	}

	public void setTweetDao(TweetDao tweetDao) {
		this.tweetDao = tweetDao;
	}
}
