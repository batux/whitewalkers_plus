package com.whitewalkers.common.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbClientManager {

	private String hostName = "NO_HOST";
	private int portNo = -1;
	
	private MongoClient mongoClient;
	private MongoDatabase selectedMongoDatabase;
	private MongoCollection<Document> selectedMongoCollection;
	
	public MongoDbClientManager() {
		
		if(this.mongoClient == null) {
			this.mongoClient = new MongoClient(this.hostName, this.portNo);
		}
	}
	
	public MongoDbClientManager(String hostName, int portNo) {
		
		this.hostName = hostName;
		this.portNo = portNo;
		
		if(this.mongoClient == null) {
			this.mongoClient = new MongoClient(this.hostName, this.portNo);
		}
	}
	
	public MongoDbClientManager connectToDatabase(String databaseName) {

		this.selectedMongoDatabase = this.getMongoClient().getDatabase(databaseName);
		return this;
	}
	
	public MongoDbClientManager connectToCollection(String collectionName) {
		
		this.selectedMongoCollection = this.getSelectedMongoDatabase().getCollection(collectionName);
		return this;
	}
	
	public MongoCollection<Document> build() {
		return this.selectedMongoCollection;
	}
	
	public MongoDatabase getSelectedMongoDatabase() {
		return this.selectedMongoDatabase;
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
}
