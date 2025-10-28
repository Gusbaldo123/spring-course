package com.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.DTO.UserLoginDTO;
import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value="users")
public class UserResource {
	@Autowired private UserService userService;
	
	@Autowired RequestService requestService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id)
	{
		User foundUser = userService.getById(id);
		if(foundUser != null)
		return ResponseEntity.ok(foundUser);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> list()
	{
		List<User> listUser = userService.listAll();
		return ResponseEntity.ok(listUser);
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user)
	{
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user)
	{
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO dto)
	{
		User loggedUser = userService.login(dto.getEmail(), dto.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllResourcesById(@PathVariable Long id)
	{
		List<Request> list = requestService.listByAllByOwnerId(id);
		return ResponseEntity.ok(list);
	}
}
