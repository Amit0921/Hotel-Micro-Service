package com.microservice.hotelservice.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String s){
		super(s);
	}
	
	public ResourceNotFoundException() {
		super("Resource ot found !!");
	}
}
