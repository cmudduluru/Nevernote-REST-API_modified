package com.NevernoteRESTAPI.ExceptionHandling;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	 
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		  ExceptionResponse eR = new ExceptionResponse((java.sql.Date) new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(eR, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  @ExceptionHandler(CustomException.class)
	  public final ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex, WebRequest request) {
		  ExceptionResponse eR = new ExceptionResponse((java.sql.Date) new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(eR, HttpStatus.BAD_REQUEST);
	  }

	  @ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		  ExceptionResponse eR = new ExceptionResponse((java.sql.Date) new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(eR, HttpStatus.NOT_FOUND);
	  }
}
