package com.reset.exception;


@SuppressWarnings("serial")
public class NameNotPresentInDB extends RuntimeException {
	public NameNotPresentInDB(String message) {
		super(message);
	}

}
