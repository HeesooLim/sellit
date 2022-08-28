package com.project.sellit;

import com.project.sellit.model.Role;
import com.project.sellit.model.User;
import com.project.sellit.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SellitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellitApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.addRole(new Role(null, "user"));
			userService.addRole(new Role(null, "admin"));
//			private String username;
//			@NotNull
//			private String email;
//			@NotNull
//			private String firstName;
//			@NotNull
//			private String lastName;
//			private String password;
			userService.add(new User(null, "Heesoolim", "heesoo@gmail.com", "Heesoo", "Lim", "1234", new ArrayList<>()));
			userService.add(new User(null, "JoseAguilar", "jose@gmail.com", "Jose", "Aguilar", "1234", new ArrayList<>()));
			userService.add(new User(null, "Cookie", "cookie@gmail.com", "Cookie", "Adorable", "1234", new ArrayList<>()));

			userService.addRoleToUser("Heesoolim", "user");
			userService.addRoleToUser("Heesoolim", "admin");
			userService.addRoleToUser("JoseAguilar", "user");
			userService.addRoleToUser("JoseAguilar", "admin");
			userService.addRoleToUser("Cookie", "user");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
