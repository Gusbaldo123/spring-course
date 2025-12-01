package com.springcourse.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.DTO.UserLoginDTO;
import com.springcourse.DTO.UserSaveDTO;
import com.springcourse.DTO.UserUpdateDTO;
import com.springcourse.DTO.UserUpdateRoleDTO;
import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.JwtManager;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="users")
public class UserResource {
	@Autowired private UserService userService;

	@Autowired private RequestService requestService;
	
	@Autowired private AuthenticationManager authManager;
	
	@Autowired private JwtManager jwtManager;

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
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDTO userSaveDTO)
	{
		User createdUser = userService.save(userSaveDTO.transformToUser());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO)
	{
		User dtoUser = userUpdateDTO.transformToUser();
		dtoUser.setId(id);
		User updatedUser = userService.update(dtoUser);
		return ResponseEntity.ok(updatedUser);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO dto)
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
		Authentication auth = authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.toList());
		
		String jwt = jwtManager.createToken(email, roles); 
		
		return ResponseEntity.ok(jwt);
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllResourcesById(@PathVariable Long id,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size)
	{
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}

	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@RequestBody @Valid UserUpdateRoleDTO dto, @PathVariable Long id)
	{
		User user = new User();
		user.setId(id);
		user.setRole(dto.getRole());
		userService.updateRole(user);
		return ResponseEntity.ok().build();
	}
}
