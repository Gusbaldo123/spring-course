package com.springcourse.DTO;

import java.util.ArrayList;
import java.util.List;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestUpdateDTO {
	
	@NotBlank(message="subject required")
	private String subject;
	private String description;
	@NotNull(message="state required")
	private RequestState state;
	@NotNull(message="owner required")
	private User owner;
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	public Request transformToRequest()
	{
		return new Request(null, this.subject, this.description, null, this.state, this.owner, this.stages);
	}
}
