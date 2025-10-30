package com.springcourse.DTO;

import java.util.ArrayList;
import java.util.List;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class UserUpdateDTO {
	@NotBlank(message = "Name required")
	private String name;
	@Email(message = "Invalid email")
	private String email;
	@Size(min = 7, max = 99, message = "Insert between 7 and 99 characters")
	private String password;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	
	public User transformToUser()
	{
		return new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
	}
}
