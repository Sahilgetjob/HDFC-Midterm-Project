package com.gropp.fooddeliveryapp.exceptionhandler;

public class OrderStatusException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderStatusException(String message) {
		super(message);
	}
}
