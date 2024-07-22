package com.virtusa.houserentBackend.service.IMPL;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.houserentBackend.Exceptions.UserFoundException;
import com.virtusa.houserentBackend.entity.User;
import com.virtusa.houserentBackend.entity.UserRole;
import com.virtusa.houserentBackend.repository.RoleRepository;
import com.virtusa.houserentBackend.repository.UserRepository;
import com.virtusa.houserentBackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// creating User

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User localuser = this.userRepository.findByUsername(user.getUsername());
		if (localuser != null) {
			System.out.println("User is already exist !!");
			throw new UserFoundException();

		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localuser = this.userRepository.save(user);
		}

		return localuser;
	}

	// getting user by username

	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	// delete UserByUsername
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);

	}

	@Override
	public Optional<User> getUserById(Long userId) {
		return this.userRepository.findById(userId);

	}

	@Override
	public User updateUser(User updatedUser) {

		// agr id purani h to update kr dega
		return this.userRepository.save(updatedUser);
	}

	@Override
	public List<User> getUsersByRole(String role) {
		System.out.println(role);
		List<User> list = this.userRepository.getAllByUserRole(role);
//		List<User> result = new ArrayList<User>();
//		for (User obj : list) {
//			System.out.println(obj.getRole());
//			if (obj.getRole() == role) {
//				result.add(obj);
//			}
//		}
		System.out.println(list);
		return list;
	}

}
