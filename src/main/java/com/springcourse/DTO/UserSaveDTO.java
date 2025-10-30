package com.springcourse.DTO;

import java.util.ArrayList;
import java.util.List;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSaveDTO {
	@NotBlank(message = "Name required")
	private String name;
	@Email(message = "Invalid email")
	private String email;
	@Size(min = 7, max = 99, message = "Insert between 7 and 99 characters")
	private String password;
	@NotNull
	private Role role;
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	
	public User transformToUser()
	{
		return new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
	}
}
