package com.springcourse.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
	private @Getter @Setter String email;
	private @Getter @Setter String password;
}
