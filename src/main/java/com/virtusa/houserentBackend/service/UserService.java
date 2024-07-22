package com.virtusa.houserentBackend.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.virtusa.houserentBackend.entity.User;
import com.virtusa.houserentBackend.entity.UserRole;

public interface UserService {

	// create User
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	// get user
	public User getUser(String username);
	
	// get user by user Id
	public Optional<User> getUserById(Long userId);
	
	// update user by userName
	public User updateUser(User user);

	// delete user by userName
	public void deleteUser(Long userId);
	
	// get user BY Role	
	public List<User> getUsersByRole(String role);

	

	


	

	// Update user by UserId
	//public User updateUser(User user, Integer userId);
}
