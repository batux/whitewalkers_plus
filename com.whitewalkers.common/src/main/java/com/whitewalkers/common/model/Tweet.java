package com.whitewalkers.common.model;

public class Tweet {

	private String userFullName;
	
	private String text;
	private long creationDateTime;
	
	private GeoLocation geoLocation;

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(long creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
}
