package com.virtusa.houserentBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.virtusa.houserentBackend.service.UserService;

@SpringBootApplication
public class HouserentBackendApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HouserentBackendApplication.class, args);
		// System.out.println("Code succefully run");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("starting code......");
//		try {
//			User user = new User();
//			user.setUsername("abhi123");
//			user.setName("Abhi");
//			user.setEmail("abhi@mail.com");
		//this.bCryptPasswordEncoder.encode("12345")
//			user.setPassword("12345");
//			user.setRole("admin");
//			Role role1 = new Role();
////			role1.setRoleId(44L);
//			role1.setRoleName("admin");
//
//			Set<UserRole> userRoleSet = new HashSet<UserRole>();
//			UserRole userRole = new UserRole();
//			userRole.setRole(role1);
//			userRole.setUser(user);
//
//			userRoleSet.add(userRole);
//
//			user.setPhone(123456789);
//
//			User user2 = this.userService.createUser(user, userRoleSet);
//			System.out.println(user2.getUsername());
//		} catch (UserFoundException e) {
//			e.printStackTrace();
//		}

	}
//
//	@Bean
//	public CommonsMultipartResolver commonsMultipartResolver() {
//		final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setMaxUploadSize(-1);
//		return commonsMultipartResolver;
//	}

}
