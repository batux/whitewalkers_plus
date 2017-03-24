package com.whitewalkers.common.adapter.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.geojson.Geometry;
import com.whitewalkers.common.adapter.util.TweetLocationAdapter;
import com.whitewalkers.common.model.QueryHolder;
import com.whitewalkers.common.model.QuerySelectBoxItem;
import com.whitewalkers.common.util.GeometryFactory;

public class QueryDocumentAdapter {
	
	private TweetLocationAdapter tweetLocationAdapter;
	
	public QueryDocumentAdapter() {
		this.tweetLocationAdapter = new TweetLocationAdapter();
	}

	@SuppressWarnings("unchecked")
	public Document convertQueryToDocument(Map<String, Object> queryParameters) {
		
		String name = (String) queryParameters.get("name");
		
		Map<String, Object> spatialQuery = (Map<String, Object>) queryParameters.get("query");
		
		List<List<Double>> coordinates = (List<List<Double>>) spatialQuery.get("coordinates");
		String searchKeyword = (String) spatialQuery.get("searchKeyword");
		
		List<Map<String, Double>> locations = (List<Map<String, Double>>) queryParameters.get("results");
		
		List<Geometry> queryResults = this.tweetLocationAdapter.convertLocationsToGeoJsonFormat(locations);
		
		Geometry geometry = GeometryFactory.createPolygonFromList(coordinates);
		
		Document queryDocument = new Document();
		queryDocument.put("name", name);
		queryDocument.put("searchKeyword", searchKeyword);
		queryDocument.put("queryResults", queryResults);
		queryDocument.put("geometry", geometry);
		
		return queryDocument;
	}
	
	public List<QuerySelectBoxItem> convertDocumentsToSelectboxItemList(FindIterable<Document> results) {
		
		List<QuerySelectBoxItem> querySelectBoxItems = new ArrayList<QuerySelectBoxItem>();
		
		MongoCursor<Document> resultsIterator = results.iterator();
		
		while(resultsIterator.hasNext()) {
			
			Document resultItem = resultsIterator.next();
			
			ObjectId objectId = (ObjectId) resultItem.get("_id");
			String id = objectId.toHexString();
			String name = (String) resultItem.get("name");
			
			QuerySelectBoxItem item = new QuerySelectBoxItem();
			item.setId(id);
			item.setName(name);
			querySelectBoxItems.add(item);
		}
		
		return querySelectBoxItems;
	}
	
	public QueryHolder convertDocumentToQueryHolder(FindIterable<Document> results) {
		
		MongoCursor<Document> resultIterator = results.iterator();
		 
		QueryHolder queryHolder = new QueryHolder();
		 
		while(resultIterator.hasNext()) {
			 
			 Document document = resultIterator.next();
			 String name = (String) document.get("name");
			 String searchKeyword = (String) document.get("searchKeyword");
			 @SuppressWarnings("unchecked")
			 List<Document> queryResults = (List<Document>) document.get("queryResults");
			 Document spatialQueryGeometry = (Document) document.get("geometry");
			 
			 queryHolder.setName(name);
			 queryHolder.setQueryResults(queryResults);
			 queryHolder.setSearchKeyword(searchKeyword);
			 queryHolder.setSpatialQueryGeometry(spatialQueryGeometry);
		}
		
		return queryHolder;
	}
	
}
