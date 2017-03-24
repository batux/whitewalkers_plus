package com.whitewalkers.common.dao.implementation;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.whitewalkers.common.dao.InsertionDao;

public class QueryDao implements InsertionDao, com.whitewalkers.common.dao.QueryDao {

	private MongoCollection<Document> mongoCollection;
	
	public QueryDao(MongoCollection<Document> mongoCollection) {
		this.setMongoCollection(mongoCollection);
	}
	
	public void insertDocument(Document record) {
		this.getMongoCollection().insertOne(record);
	}

	public MongoCollection<Document> getMongoCollection() {
		return mongoCollection;
	}

	public void setMongoCollection(MongoCollection<Document> mongoCollection) {
		this.mongoCollection = mongoCollection;
	}

	public FindIterable<Document> queryDocument(Bson filter) {
		
		FindIterable<Document> results = (filter == null) ? this.mongoCollection.find() : this.mongoCollection.find(filter);
		return results;
	}

}
