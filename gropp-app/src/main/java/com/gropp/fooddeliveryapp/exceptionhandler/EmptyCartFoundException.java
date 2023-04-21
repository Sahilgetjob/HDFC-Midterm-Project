package com.gropp.fooddeliveryapp.exceptionhandler;


public class EmptyCartFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyCartFoundException(String message) {
		super(message);
	}

}
