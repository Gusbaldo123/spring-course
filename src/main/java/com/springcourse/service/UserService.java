package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.util.HashUtil;

@Service
public class UserService {
	@Autowired UserRepository userRepository;
	
	public User save(User user)
	{
		user.setPassword(HashUtil.getSecureHash(user.getPassword()));
		User createdUser = userRepository.save(user);
		return createdUser;
	}

	public User update(User user) {
		user.setPassword(HashUtil.getSecureHash(user.getPassword()));
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	public User getById(Long id) // deal with null pointer later
	{
		Optional<User> foundUser = userRepository.findById(id);
		
		return foundUser.orElseThrow(() -> new NotFoundException("There are not users with id "+ id));
	}
	
	public List<User> listAll()
	{
		List<User> list = userRepository.findAll();
		return list;
	}
	
	public User login(String email, String password) // deal with null pointer later
	{
		String hashPassword = HashUtil.getSecureHash(password);
		Optional<User> foundUser = userRepository.login(email, hashPassword);
		return foundUser.get();
	}
}
