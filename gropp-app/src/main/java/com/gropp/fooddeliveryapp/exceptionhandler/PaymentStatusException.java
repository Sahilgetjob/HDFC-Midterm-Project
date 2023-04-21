package com.gropp.fooddeliveryapp.exceptionhandler;

public class PaymentStatusException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentStatusException(String message) {
		super(message);
	}
}
