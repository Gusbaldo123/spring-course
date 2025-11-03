package com.springcourse.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIErrorList extends APIError{

	private static final long serialVersionUID = 1L;
	
	private List<String> errors = new ArrayList<>();
	
	public APIErrorList(HttpStatus code, String message, Date date, List<String> errors)
	{
		super(code, message, date);
		this.errors = errors;
		
	}
}
