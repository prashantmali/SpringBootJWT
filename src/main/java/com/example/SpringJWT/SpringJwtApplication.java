package com.example.SpringJWT;

import com.example.SpringJWT.domain.Role;
import com.example.SpringJWT.domain.User;
import com.example.SpringJWT.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return  args -> {
			userService.saveROle(new Role(null,"ROLE_USER"));
			userService.saveROle(new Role(null,"ROLE_ADMIN"));
			userService.saveROle(new Role(null,"ROLE_MANAGER"));
			userService.saveROle(new Role(null,"ROLE_SUPER_MANAGER"));

			userService.saveUser(new User(null,"ABC","ABC","ABC", new ArrayList<>()));
			userService.saveUser(new User(null,"ABC1","ABC1","ABC1", new ArrayList<>()));
			userService.saveUser(new User(null,"ABC2","ABC2","ABC2", new ArrayList<>()));
			userService.saveUser(new User(null,"ABC3","ABC3","ABC3", new ArrayList<>()));

			userService.addRoleToUser("ABC1","ROLE_USER");
			userService.addRoleToUser("ABC2","ROLE_ADMIN");
			userService.addRoleToUser("ABC2","ROLE_MANAGER");
		};
	}

}
