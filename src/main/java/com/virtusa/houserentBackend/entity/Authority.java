package com.virtusa.houserentBackend.entity;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class Authority implements GrantedAuthority {

	public String authority;

	public Authority() {
		super();
	}

	@Override

	public String getAuthority() {
		return this.authority;
	}

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

}