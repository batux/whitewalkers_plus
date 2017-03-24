package com.whitewalkers.common.dao;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;

public interface QueryDao {

	public FindIterable<Document> queryDocument(Bson filter);
	
}
