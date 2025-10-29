package com.springcourse.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.DTO.UserLoginDTO;
import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
	public ResponseEntity<PageModel<User>> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
	{
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
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
	public ResponseEntity<PageModel<Request>> listAllResourcesById(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
	{
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}
}
