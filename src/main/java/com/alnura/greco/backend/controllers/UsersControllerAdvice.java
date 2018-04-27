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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@ControllerAdvice(assignableTypes = UsersController.class)
public class UsersControllerAdvice extends ResponseEntityExceptionHandler{
	@Getter
	@Setter
	@AllArgsConstructor
	private class ErrorMsg {
		String code;
		String description;
		
		public String toJSON() {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			return gson.toJson(this); 
		}
	}
	
	 @ExceptionHandler(UserNotFoundException.class)
	 protected ResponseEntity<Object> handleNotFoundConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = new ErrorMsg("0001", "There is no user for that id.").toJSON();
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	 
	 @ExceptionHandler(UserAlreadyExistException.class) 
	 protected ResponseEntity<Object> handleAlreadyConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = new ErrorMsg("0002", "User already exist").toJSON();
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	 
}
