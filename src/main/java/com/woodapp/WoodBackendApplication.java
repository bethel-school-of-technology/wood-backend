package com.woodapp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableEncryptableProperties
public class WoodBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodBackendApplication.class, args);
	}


}
