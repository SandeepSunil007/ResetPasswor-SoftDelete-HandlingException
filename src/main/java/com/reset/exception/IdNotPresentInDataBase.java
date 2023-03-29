package com.reset.exception;

@SuppressWarnings("serial")
public class IdNotPresentInDataBase extends RuntimeException {
	public IdNotPresentInDataBase(String message) {
		super(message);
	}

}
