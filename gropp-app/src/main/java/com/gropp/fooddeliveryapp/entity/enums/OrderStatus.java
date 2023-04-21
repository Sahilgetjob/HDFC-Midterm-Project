package com.gropp.fooddeliveryapp.entity.enums;

public enum OrderStatus {

	NOT_PLACED("Not Placed"),
	PLACED("Placed"),
	IN_TRANSIT("In Transit"),
	DELIVERED("Delivered"),
	CANCELLED("Cancelled"),
	FAILED("Failed");
	
	private final String status;
	
	OrderStatus(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
