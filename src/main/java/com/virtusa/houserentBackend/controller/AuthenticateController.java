package com.virtusa.houserentBackend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.houserentBackend.Exceptions.UserNotFoundException;
import com.virtusa.houserentBackend.config.JwtUtils;
import com.virtusa.houserentBackend.entity.Login;
import com.virtusa.houserentBackend.entity.LoginJwtToken;
import com.virtusa.houserentBackend.entity.User;
import com.virtusa.houserentBackend.service.IMPL.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtils jwtUtils;

	// generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody Login login) throws Exception {

		try {

			authenticate(login.getUsername(), login.getPassword());

		} catch (UserNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("user not found");
		}

		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(login.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new LoginJwtToken(token));

	}

	
	//Login Method

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User Disable");
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credientials" + e.getMessage());
		}
	}

	// return the details of current loggedin user
	@GetMapping("/currentUser")
	public User getCurrentUser(Principal principal) {
		return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
	}
}
