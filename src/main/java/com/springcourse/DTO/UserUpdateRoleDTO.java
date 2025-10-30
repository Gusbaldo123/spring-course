package com.springcourse.DTO;

import com.springcourse.domain.enums.Role;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoleDTO {
	private @NotNull(message = "Role required") Role role;
}
