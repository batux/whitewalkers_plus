package com.whitewalkers.common.adapter.document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.whitewalkers.common.model.GeoLocation;
import com.whitewalkers.common.model.Tweet;

public class TweetDocumentAdapter {

	public Document convertTextToDocument(String rawTweetAsText) {
		
		Document tweetRecordAsDocument = Document.parse(rawTweetAsText);
		
		Point point = null;
		
		Document geoLocation = (Document) tweetRecordAsDocument.get("geoLocation");
		
		if(geoLocation != null && geoLocation.get("longitude") != null && geoLocation.get("latitude") != null) {
			double longitude = (Double) geoLocation.get("longitude");
			double latitude = (Double) geoLocation.get("latitude");
			Double[] location = { longitude, latitude };
			
			Position position = new Position(Arrays.asList(location));
			point = new Point(position);
		}
		
		tweetRecordAsDocument.put("locationAsGeoJSON", point);
		
		return tweetRecordAsDocument;
	}
	
	public List<Tweet> convertDocumentsToTweetList(FindIterable<Document> results) {
		
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		MongoCursor<Document> resultsIterator = results.iterator();
		
		while(resultsIterator.hasNext()) {
			
			Document resultItem = resultsIterator.next();
			
			Tweet tweet = new Tweet();
			
			String text = (String) resultItem.get("text");
			tweet.setText(text);
			
			long creationDateTime = (Long) resultItem.get("createdAt");
			tweet.setCreationDateTime(creationDateTime);
			
			Document user = (Document) resultItem.get("user");
			
			String userFullName = (String) user.get("name");
			tweet.setUserFullName(userFullName);
			
			Document geoLocation = (Document) resultItem.get("geoLocation");
			Double latitude = (Double) geoLocation.get("latitude");
			Double longitude = (Double) geoLocation.get("longitude");
			
			GeoLocation geoLocationItem = new GeoLocation();
			geoLocationItem.setLatitude(latitude);
			geoLocationItem.setLongitude(longitude);
			tweet.setGeoLocation(geoLocationItem);
			
			tweets.add(tweet);
		}
		
		return tweets;
	}
	
}
