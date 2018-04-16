package com.alnura.greco.backend.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alnura.greco.backend.exceptions.UserAlreadyExistException;
import com.alnura.greco.backend.exceptions.UserNotFoundException;


@ControllerAdvice(assignableTypes = UsersController.class)
public class UsersControllerAdvice extends ResponseEntityExceptionHandler{
	 @ExceptionHandler(UserNotFoundException.class)
	 protected ResponseEntity<Object> handleNotFoundConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = "Can't find that user";
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	 
	 @ExceptionHandler(UserAlreadyExistException.class) 
	 protected ResponseEntity<Object> handleAlreadyConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = "User already exist";
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	 
}
