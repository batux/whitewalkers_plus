package com.whitewalkers.query.service.provider;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.whitewalkers.common.adapter.document.QueryDocumentAdapter;
import com.whitewalkers.common.dao.implementation.QueryDao;
import com.whitewalkers.common.model.QueryHolder;
import com.whitewalkers.common.model.QuerySelectBoxItem;
import com.whitewalkers.common.mongodb.MongoDbClientManager;
import com.whitewalkers.query.service.config.mongodb.MongoDBConfiguration;

@Component
public class QueryServiceProvider {

	private String databaseName = "twitter";
	private String collectionName = "queries";
	
	private QueryDao queryDao;
	private QueryDocumentAdapter queryDocumentAdapter;
	private MongoDbClientManager mongoDbClientManager;
	
	@Autowired
	private MongoDBConfiguration configuration;
	
	public QueryServiceProvider() {
		
		this.queryDocumentAdapter = new QueryDocumentAdapter();
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

		this.queryDao = new QueryDao(this.mongoDbClientManager.build());
	}
	
	public void saveQuery(Map<String, Object> queryParameters) {
		
		Document record = this.queryDocumentAdapter.convertQueryToDocument(queryParameters);
		
		this.queryDao.insertDocument(record);
	}
	
	public QueryHolder getQueryById(String queryIdAshexString) {
		
		FindIterable<Document> results = this.queryDao.queryDocument(Filters.eq("_id", new ObjectId(queryIdAshexString)));
		
		QueryHolder queryHolder = this.queryDocumentAdapter.convertDocumentToQueryHolder(results);
		
		return queryHolder;
	}
	
	public List<QuerySelectBoxItem> getQuerySelectboxItems() {
		
		FindIterable<Document> results = this.queryDao.queryDocument(null).sort(new Document("_id", -1)).limit(10);
		
		List<QuerySelectBoxItem> selectboxItemList = this.queryDocumentAdapter.convertDocumentsToSelectboxItemList(results);
		
		return selectboxItemList;
	}
	
}
