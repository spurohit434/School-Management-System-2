package com.wg.Helper;

public class InvalidFeeAmountException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFeeAmountException(String message) {
		super(message);
	}
}
