package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
	
	public PageModel<User> listAllOnLazyMode(PageRequestModel pr)
	{
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<User> page = userRepository.findAll(pageable); 
		return new PageModel<>((int)page.getTotalElements(),page.getSize(),page.getTotalPages(), page.getContent());
	}
	
	public int updateRole(User user)
	{
		return userRepository.updateRole(user.getId(), user.getRole());
	}
}
