package com.whitewalkers.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.PolygonCoordinates;
import com.mongodb.client.model.geojson.Position;
import com.whitewalkers.common.model.GeoLocation;

public class GeometryFactory {

	@SuppressWarnings("unchecked")
	public static Geometry createPolygonFromArray(List<Double[]> coordinates) {
		
		Geometry polygon = null;
		
		List<Position> positions = new ArrayList<Position>();
		
		for(Double[] coordinatesInDouble : coordinates) {
			
			Position pos = new Position(Arrays.asList(coordinatesInDouble));
			positions.add(pos);
		}
		
		if(positions.size() > 0) {
			PolygonCoordinates polygonCoordinates = new PolygonCoordinates(positions);
			polygon = new Polygon(polygonCoordinates);
		}
		
		return polygon;
	}
	
	@SuppressWarnings("unchecked")
	public static Geometry createPolygonFromList(List<List<Double>> coordinates) {
		
		Geometry polygon = null;
		
		List<Position> positions = new ArrayList<Position>();
		
		for(List<Double> coordinatesInDouble : coordinates) {
			
			Position pos = new Position(coordinatesInDouble);
			positions.add(pos);
		}
		
		if(positions.size() > 0) {
			PolygonCoordinates polygonCoordinates = new PolygonCoordinates(positions);
			polygon = new Polygon(polygonCoordinates);
		}
		
		return polygon;
	}
	
	public static Geometry createPoint(GeoLocation geoLocation) {
		
		Double[] locationsInDouble = { geoLocation.getLongitude(), geoLocation.getLatitude() };
		
		Position position = new Position(Arrays.asList(locationsInDouble));
		
		Point point = new Point(position);
		
		return point;
	}
	
	public static Geometry createPoint(Map<String, Double> location) {
		
		Double latitude = location.get("latitude");
		Double longitude = location.get("longitude");
		
		Double[] locationsInDouble = { longitude, latitude };
		
		Position position = new Position(Arrays.asList(locationsInDouble));
		
		Point point = new Point(position);
		
		return point;
	}
	
}
