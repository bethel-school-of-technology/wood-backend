package com.woodapp.woodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WoodBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodBackendApplication.class, args);
	}

}
