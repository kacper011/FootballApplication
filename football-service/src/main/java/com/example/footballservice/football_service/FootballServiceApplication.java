package com.example.footballservice.football_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FootballServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballServiceApplication.class, args);
	}

}
