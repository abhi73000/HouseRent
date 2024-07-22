package com.virtusa.houserentBackend.Exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super(" User is not found in DB !!");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
