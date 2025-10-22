package com.springcourse.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HashUtilTest {
	@Test
	@Order(1)
	public void getSecureHashTest()
	{
		String password = "123";
		String hashedPassword = HashUtil.getSecureHash(password);
		System.out.println(hashedPassword);
		assertThat(hashedPassword.length()).isEqualTo(64);
	}
}
