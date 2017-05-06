package com.whitewalkers.common.holder;

import java.io.Serializable;

public class ResponseHolder<T> implements Serializable {

	private static final long serialVersionUID = 2743668134220092073L;

	private T payload;

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

}
 