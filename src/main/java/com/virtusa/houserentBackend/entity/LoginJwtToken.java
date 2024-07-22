package com.virtusa.houserentBackend.entity;

public class LoginJwtToken {
	String token;

	public LoginJwtToken() {
		super();
	}

	public LoginJwtToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
