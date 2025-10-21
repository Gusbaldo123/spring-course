package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserRepositoryTests {
	@Autowired UserRepository userRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		User userTest = new User(null, "test", "test@test.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(userTest);
		assertThat(createdUser.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void updateTest()
	{
		String newName = "test 2";
		User userTest = new User(1L, newName, "test@test.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser =userRepository.save(userTest);
		assertThat(updatedUser.getName()).isEqualTo(newName);
	}
	
	@Test
	@Order(3)
	public void getByIdTest()
	{
		Optional<User> foundTest = userRepository.findById(1L);
	    assertThat(foundTest.map(User::getEmail).orElse(null)).isEqualTo("test@test.com");
	}		
	
	@Test
	@Order(4)
	public void listTest()	
	{
		List<User> userList = userRepository.findAll();
		assertThat(userList.size()).isEqualTo(1);
	}
	
	@Test
	@Order(5)
	public void loginTest()
	{
		Optional<User> foundTest = userRepository.login("test@test.com", "123");
	    assertThat(foundTest.map(User::getId).orElse(null)).isEqualTo(1L);
	}
}
