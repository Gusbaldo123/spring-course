package com.springcourse.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageRequestModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private int page;
	private int size;
}
