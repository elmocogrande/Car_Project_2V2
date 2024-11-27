package com.packt.cardatabase.web;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e){
		
		ErrorResponse errorResponse = ErrorResponse.create(e, HttpStatus.NOT_FOUND, e.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
				
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e){
		
		ErrorResponse errorResponse = ErrorResponse.create(e, HttpStatus.NOT_FOUND, e.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
				
	}
 
}
