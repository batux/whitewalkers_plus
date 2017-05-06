package com.whitewalkers.common.holder;

import java.io.Serializable;
import java.util.Map;

public class RequestHolder<T> implements Serializable {

	private static final long serialVersionUID = 2743668134220092073L;

	private T payload;
	
	private Map<String, Object> extraParameters;

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public Map<String, Object> getExtraParameters() {
		return extraParameters;
	}

	public void setExtraParameters(Map<String, Object> extraParameters) {
		this.extraParameters = extraParameters;
	}

}
