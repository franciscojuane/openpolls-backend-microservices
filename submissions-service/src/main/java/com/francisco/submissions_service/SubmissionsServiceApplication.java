package com.francisco.submissions_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubmissionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubmissionsServiceApplication.class, args);
	}

}
