package com.springcourse.DTO;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestStageSaveDTO {
	private String description;
	
	@NotNull(message="state required")
	private RequestState state;
	
	@NotNull(message="request required")
	private Request request;
	
	@NotNull(message="owner required")
	private User owner;
	
	public RequestStage transformToRequestStage()
	{
		return new RequestStage(null,this.description,null,this.state,this.request,this.owner);
	}
}
