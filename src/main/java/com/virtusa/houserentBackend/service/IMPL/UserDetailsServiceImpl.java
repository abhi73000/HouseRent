package com.virtusa.houserentBackend.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.houserentBackend.entity.User;
import com.virtusa.houserentBackend.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUsername(username);
		if(user == null) {
			System.out.println("user Not found");
			throw new UsernameNotFoundException("No User Found With !!");
		}		
		return user;
	}

}
