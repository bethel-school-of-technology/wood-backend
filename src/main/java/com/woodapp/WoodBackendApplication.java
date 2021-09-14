package com.woodapp;

import com.woodapp.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.woodapp.models.Role;
import com.woodapp.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;


@EnableJpaRepositories
@SpringBootApplication
public class WoodBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodBackendApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			//userService.saveUser(new User(null, "Tony", "Stark", "ironman", "stark@gmail.com", "stark", new ArrayList<>()));
			userService.saveUser(new User( "Black", "Widow", "scarlett", "widow@avengers.com", "avengers", "F", "10/01/85",
					5555555, "123 Avengers Way", "LA", "CA", 99999, LocalDate.of(2021, 9, 13), "unlimited", new ArrayList<>()));

			//userService.addRoleToUser("ironman", "ROLE_ADMIN");
			userService.addRoleToUser("scarlett", "ROLE_ADMIN");

		};

	}


}
