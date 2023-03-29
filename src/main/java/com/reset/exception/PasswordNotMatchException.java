package com.reset.exception;

@SuppressWarnings("serial")
public class PasswordNotMatchException extends RuntimeException {
	public PasswordNotMatchException(String message) {
		super(message);
	}

}
