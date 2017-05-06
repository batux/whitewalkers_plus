package com.whitewalkers.tweet.service.provider;

import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.whitewalkers.common.adapter.document.TweetDocumentAdapter;
import com.whitewalkers.common.adapter.filter.TweetFilterAdapter;
import com.whitewalkers.common.dao.implementation.TweetDao;
import com.whitewalkers.common.model.SpatialQuery;
import com.whitewalkers.common.model.Tweet;
import com.whitewalkers.common.mongodb.MongoDbClientManager;
import com.whitewalkers.tweet.service.config.mongodb.MongoDBConfiguration;

@Component
public class TweetServiceProvider {

	private String databaseName = "twitter";
	private String collectionName = "tweets";
	
	private TweetDao tweetDao;
	private TweetFilterAdapter tweetFilterAdapter;
	private TweetDocumentAdapter tweetDocumentAdapter;
	private MongoDbClientManager mongoDbClientManager;
	
	@Autowired
	private MongoDBConfiguration configuration;
	
	public TweetServiceProvider() {
		
		this.tweetFilterAdapter = new TweetFilterAdapter();
		this.tweetDocumentAdapter = new TweetDocumentAdapter();
	}
	
	@PostConstruct
	public void postConstructionProcess() {
		
		this.databaseName = this.configuration.getMongoDbDatabaseName();
		this.collectionName = this.configuration.getMongoDbCollectionName();
		
		System.out.println("HOST IP >>>>>>>>>>> " + this.configuration.getMongoDbHost());
		System.out.println("HOST PORT NO >>>>>>>>>>> " + this.configuration.getMongoDbPortNo());
		System.out.println("DATABASE NAME >>>>>>>>>>> " + this.databaseName);
		System.out.println("COLLECTION NAME >>>>>>>>>>> " + this.collectionName);

		this.mongoDbClientManager = new MongoDbClientManager(this.configuration.getMongoDbHost(), this.configuration.getMongoDbPortNo())
				.connectToDatabase(this.databaseName)
				.connectToCollection(this.collectionName);

		this.tweetDao = new TweetDao(this.mongoDbClientManager.build());
	}
	
	public void saveTweet(String tweetAsJsonText) {
		
		Document tweetAsDocument = this.tweetDocumentAdapter.convertTextToDocument(tweetAsJsonText);
		
		this.tweetDao.insertDocument(tweetAsDocument);
	}
	
	public List<Tweet> getTweets(SpatialQuery spatialQuery) {
		
		Bson queryFilter = this.tweetFilterAdapter.convertSpatialQueryToBson(spatialQuery);
		
		FindIterable<Document> results = this.tweetDao.queryDocument(queryFilter);
		
		List<Tweet> tweets = this.tweetDocumentAdapter.convertDocumentsToTweetList(results);
		
		return tweets;
	}
	
	public List<Tweet> getTweets(String tweetType, int tweetMaximumLimit) {
		
		Bson queryFilter = "geo".equals(tweetType) ? Filters.ne("geoLocation", null) : null;
		
		FindIterable<Document> results = this.tweetDao.queryDocument(queryFilter);
		
		List<Tweet> tweets = this.tweetDocumentAdapter.convertDocumentsToTweetList(results);
		
		return tweets;
	}

	public TweetDao getTweetDao() {
		return tweetDao;
	}

	public void setTweetDao(TweetDao tweetDao) {
		this.tweetDao = tweetDao;
	}

	public TweetFilterAdapter getTweetFilterAdapter() {
		return tweetFilterAdapter;
	}

	public void setTweetFilterAdapter(TweetFilterAdapter tweetFilterAdapter) {
		this.tweetFilterAdapter = tweetFilterAdapter;
	}

	public TweetDocumentAdapter getTweetDocumentAdapter() {
		return tweetDocumentAdapter;
	}

	public void setTweetDocumentAdapter(TweetDocumentAdapter tweetDocumentAdapter) {
		this.tweetDocumentAdapter = tweetDocumentAdapter;
	}

	public MongoDbClientManager getMongoDbClientManager() {
		return mongoDbClientManager;
	}

	public void setMongoDbClientManager(MongoDbClientManager mongoDbClientManager) {
		this.mongoDbClientManager = mongoDbClientManager;
	}
}
