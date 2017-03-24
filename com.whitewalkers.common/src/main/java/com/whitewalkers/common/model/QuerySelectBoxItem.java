package com.whitewalkers.common.model;

import java.io.Serializable;

public class QuerySelectBoxItem implements Serializable {

	private static final long serialVersionUID = -363937723468624852L;
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
