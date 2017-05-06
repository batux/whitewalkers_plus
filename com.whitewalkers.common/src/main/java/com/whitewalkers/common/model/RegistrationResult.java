package com.whitewalkers.common.model;

import java.io.Serializable;

public class RegistrationResult implements Serializable{

	private static final long serialVersionUID = -2150318898549779558L;
	
	private String qrBarcodeImageUrl;
	private boolean registrationCompleted;
	
	public String getQrBarcodeImageUrl() {
		return qrBarcodeImageUrl;
	}
	public void setQrBarcodeImageUrl(String qrBarcodeImageUrl) {
		this.qrBarcodeImageUrl = qrBarcodeImageUrl;
	}
	public boolean isRegistrationCompleted() {
		return registrationCompleted;
	}
	public void setRegistrationCompleted(boolean registrationCompleted) {
		this.registrationCompleted = registrationCompleted;
	}
	
}
