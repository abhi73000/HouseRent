package com.virtusa.houserentBackend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.houserentBackend.Exceptions.UserFoundException;
import com.virtusa.houserentBackend.Exceptions.UserNotFoundException;
import com.virtusa.houserentBackend.entity.Role;
import com.virtusa.houserentBackend.entity.User;
import com.virtusa.houserentBackend.entity.UserRole;
import com.virtusa.houserentBackend.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// creating user
	@PostMapping("/register")
	public User createUser(@RequestBody User user) throws Exception {
		// encoding password with bcrypt password
		Set<UserRole> roles = new HashSet<UserRole>();
		Role role = new Role();
		role.setRoleName(user.getRole());

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		return this.userService.createUser(user, roles);
	}

	// get user by user name
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username) throws UserNotFoundException {
		User user = this.userService.getUser(username);
		if (user == null) {
			throw new UserNotFoundException("User not found with username: " + username);
		}
		return ResponseEntity.ok(user);
	}

	// update User
	@PutMapping("/update")
	public User updateUser(@RequestBody User newUser) {
		return this.userService.updateUser(newUser);
	}

	// delete user by userId
	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}

//	// get all User
//	@GetMapping("/")
//	public ResponseEntity<List<User>> getAllUser(){
//		return ResponseEntity.ok(this.userService.getAllUser()); 
//		
//	}
//	@GetMapping("/example/{uId}")
//	public ResponseEntity<?> yourControllerMethod(@PathVariable Long uId) {
//		return this.userRepository.findById(uId);
//	}

	// Update user ByUserId
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
		Optional<User> user = this.userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/getUsers/{role}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String role) {
		List<User> userList = this.userService.getUsersByRole(role);
		System.out.println(userList);
		return ResponseEntity.ok(userList);

	}

	@ExceptionHandler({ UserFoundException.class })
	public ResponseEntity<String> UserFoundException(UserFoundException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
