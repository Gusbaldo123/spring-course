package com.springcourse.resource.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springcourse.exception.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<APIError> handleNotFoundException(NotFoundException ex)
	{
		APIError apiError = new APIError(HttpStatus.NOT_FOUND, ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST, defaultMessage, new Date());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
}
