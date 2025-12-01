package com.springcourse.security;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtManager
{
	@Value("${JWT_EXP_DAYS}")
	private int expDays;

	@Value("${JWT_API_KEY}")
	private String apiKey;

	@Value("${JWT_PROVIDER}")
	private String provider;

	@Value("${JWT_ROLE}")
	private String role;

	public String createToken(String email, List<String> roles)
	{
		Date expDate = Date.from(LocalDate.now().plusDays(expDays).atStartOfDay(ZoneId.systemDefault()).toInstant());

		String jwt = Jwts.builder().subject(email).expiration(expDate).claim(role, roles)
		        .signWith(Keys.hmacShaKeyFor(apiKey.getBytes()), Jwts.SIG.HS256).compact();

		return jwt;
	}
}