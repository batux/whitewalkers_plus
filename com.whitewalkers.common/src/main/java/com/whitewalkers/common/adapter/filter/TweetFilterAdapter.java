package com.whitewalkers.common.adapter.filter;

import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Geometry;
import com.whitewalkers.common.model.SpatialQuery;
import com.whitewalkers.common.util.GeometryFactory;

public class TweetFilterAdapter {

	public Bson convertSpatialQueryToBson(SpatialQuery spatialQuery) {
		
		Bson filter = null;
		
		Geometry polygon = GeometryFactory.createPolygonFromArray(spatialQuery.getCoordinates());
		
		if(StringUtils.isNotBlank(spatialQuery.getSearchKeyword())) {
			
			filter = Filters.and(Filters.regex("text", ".*" + spatialQuery.getSearchKeyword() + ".*"),Filters.ne("geoLocation", null));
			
			if(polygon != null && spatialQuery.getCoordinates() != null && spatialQuery.getCoordinates().size() > 0) {
				filter = Filters.and(Filters.geoWithin("locationAsGeoJSON", polygon),Filters.regex("text", ".*" + spatialQuery.getSearchKeyword() + ".*"),Filters.ne("geoLocation", null));
			}
			
		}
		else {
			
			if(polygon != null) {
				filter = Filters.and(Filters.geoWithin("locationAsGeoJSON", polygon),Filters.ne("geoLocation", null));
			}
			else {
				filter = Filters.ne("geoLocation", null);
			}
		}
		
		return filter;
	}
}
