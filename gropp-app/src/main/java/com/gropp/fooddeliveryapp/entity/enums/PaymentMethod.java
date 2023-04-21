package com.gropp.fooddeliveryapp.entity.enums;

public enum PaymentMethod {

	CREDIT_CARD("Credit Card"),
	DEBIT_CARD("Debit Card"),
	UPI("Upi"),
	PAYPAL("Paypal");
	
	private final String method;
	
	PaymentMethod(String method){
		this.method = method;
	}
	
	public String getMethod() {
		return method;
	}
}
