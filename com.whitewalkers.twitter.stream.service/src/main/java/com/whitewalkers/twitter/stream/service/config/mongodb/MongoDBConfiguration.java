package com.whitewalkers.twitter.stream.service.config.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConfiguration {

	@Value("${mongodb.host}")
	private String mongoDbHost;
	
	@Value("${mongodb.port}")
	private int mongoDbPortNo;
	
	@Value("${mongodb.database.name}")
	private String mongoDbDatabaseName;
	
	@Value("${mongodb.collection.name}")
	private String mongoDbCollectionName;
	

	public String getMongoDbCollectionName() {
		return mongoDbCollectionName;
	}

	public void setMongoDbCollectionName(String mongoDbCollectionName) {
		this.mongoDbCollectionName = mongoDbCollectionName;
	}

	public int getMongoDbPortNo() {
		return mongoDbPortNo;
	}

	public void setMongoDbPortNo(int mongoDbPortNo) {
		this.mongoDbPortNo = mongoDbPortNo;
	}

	public String getMongoDbHost() {
		return mongoDbHost;
	}

	public void setMongoDbHost(String mongoDbHost) {
		this.mongoDbHost = mongoDbHost;
	}
	
	public String getMongoDbDatabaseName() {
		return mongoDbDatabaseName;
	}

	public void setMongoDbDatabaseName(String mongoDbDatabaseName) {
		this.mongoDbDatabaseName = mongoDbDatabaseName;
	}
	
}
