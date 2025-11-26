package com.springcourse.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springcourse.service.util.HashUtil;

public class CustomPasswordEncoder implements PasswordEncoder
{

	@Override
	public String encode(CharSequence rawPassword)
	{
		String hashed = HashUtil.getSecureHash(rawPassword.toString());
		return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		String hashed = HashUtil.getSecureHash(rawPassword.toString());
		return hashed.equals(encodedPassword);
	}

}
