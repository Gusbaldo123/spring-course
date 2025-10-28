package com.springcourse.resource.exception;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIError implements Serializable{
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
	private String message;
	private Date date;
}
