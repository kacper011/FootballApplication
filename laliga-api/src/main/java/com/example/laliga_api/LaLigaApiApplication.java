package com.example.laliga_api;

import com.example.laliga_api.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaLigaApiApplication implements CommandLineRunner {

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(LaLigaApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appConfig.printAppInfo();
	}
}
