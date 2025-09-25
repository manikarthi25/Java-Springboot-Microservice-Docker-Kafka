package com.service.kafka.exception;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4487277874271549662L;
	/**
	 * 
	 */
	private String code;
	private String message;

	public AppException() {
		super();
	}

	public AppException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public AppException(String message) {
		super(message);
		this.message = message;
	}
}
