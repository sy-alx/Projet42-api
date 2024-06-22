package com.exemple.Projet42_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.exemple.Projet42_api.entities")
public class Projet42ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet42ApiApplication.class, args);
	}
}
