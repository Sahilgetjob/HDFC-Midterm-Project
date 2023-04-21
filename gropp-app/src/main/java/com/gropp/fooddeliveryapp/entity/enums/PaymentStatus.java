package com.gropp.fooddeliveryapp.entity.enums;

public enum PaymentStatus {

	PENDING("Pending"),
	PROCESSING("Processing"),
	COMPLETED("Completed"),
	FAILED("Failed");
	
	private final String status;

	PaymentStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
