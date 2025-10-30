package com.springcourse.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserLoginDTO {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password must not be blank or empty")
    private String password;
}
