package com.NevernoteRESTAPI.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3894835909667588564L;
	
	public CustomException(String exception) {
		super(exception);
	}

}
