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

			userService.saveUser(new User( "Cameron", "Tomlin", "ctvaan", "ctvaan@gmail.com", "theflash", "M", "11/01/90",
					9221404, "10090 Silver Stirrup Dr", "Colorado Springs", "CO", 80925, LocalDate.of(2021, 9, 13), "unlimited", new ArrayList<>()));
			userService.saveUser(new User( "Audrey", "Clark", "aclark", "audrey@gmail.com", "doggos", "F", "01/13/93",
					9193779, "3138 Apperly Way", "Rancho Cordova", "CA", 95670, LocalDate.of(2021, 9, 13), "unlimited", new ArrayList<>()));


			userService.addRoleToUser("ctvaan", "ROLE_ADMIN");
			userService.addRoleToUser("aclark", "ROLE_ADMIN");

		};

	}

}
