package com.reset.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.reset.exception.NameNotPresentInDB;
import com.reset.exception.PasswordNotMatchException;
import com.reset.response.UserResponse;

@RestControllerAdvice
public class UserExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NameNotPresentInDB.class)
	public ResponseEntity<UserResponse> handleNameNotPresentInDB(NameNotPresentInDB ex) {
		UserResponse response = new UserResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PasswordNotMatchException.class)
	public ResponseEntity<UserResponse> handlePasswordNotMatchException(PasswordNotMatchException ex) {
		UserResponse response = new UserResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
