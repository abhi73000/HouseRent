package com.virtusa.houserentBackend.entity;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// ...
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", nullable = false, unique = true)
	private Long userId;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String role;
	@Column(nullable = false)
	private long phone;
	

	// user many roles

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<UserRole>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ListProperty> properties;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HouseRentRequest> rentalRequests;


	public User() {
		super();
	}

	public User(Long userId, String username, String name, String email, String password, String role, long phone,
			Set<UserRole> userRoles) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phone = phone;
		this.userRoles = userRoles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		System.out.println("comes!!!!!!!!!!!!!!!!!!!!!!!");
//		Set<GrantedAuthority> authorities = new HashSet<>();
//		this.userRoles.forEach(userRole -> {
//			authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
//		});
//		System.out.println(authorities.toString());
//		return authorities;
//	}
//	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> authorities = new HashSet<>();//
	    String[] roles = getRole().split(","); 
	    for (String role : roles) {	       
	        authorities.add(new Authority(role.trim()));
	    }	    
	    return authorities;
	}

//		String[] roles = getRole().split(","); // Split roles by comma
//		for (String role : roles) {
//			authorities.add(new Authority(role.trim()));
//		}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
