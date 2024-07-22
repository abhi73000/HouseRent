package com.virtusa.houserentBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virtusa.houserentBackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	@Query("select u from User u where u.role=?1")
	public List<User> getAllByUserRole(String role);

	// public User UpdateByUserById(User user, Integer userId);

}
