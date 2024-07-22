package com.virtusa.houserentBackend.Exceptions;

@SuppressWarnings("serial")
public class UserFoundException extends Exception {

	public UserFoundException() {
		super(" User is already exist in DB !! please try another username");
	}

	public UserFoundException(String message) {
		super(message);
	}

}
