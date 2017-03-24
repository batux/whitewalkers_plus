package com.whitewalkers.common.model;

import java.io.Serializable;
import java.util.List;

import org.bson.Document;

public class QueryHolder implements Serializable{

	private static final long serialVersionUID = -8227608878079471307L;

	private String name;
	private String searchKeyword;
	private List<Document> queryResults;
	private Document spatialQueryGeometry;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public List<Document> getQueryResults() {
		return queryResults;
	}
	public void setQueryResults(List<Document> queryResults) {
		this.queryResults = queryResults;
	}
	public Document getSpatialQueryGeometry() {
		return spatialQueryGeometry;
	}
	public void setSpatialQueryGeometry(Document spatialQueryGeometry) {
		this.spatialQueryGeometry = spatialQueryGeometry;
	}
	
}
