package com.example.seriea_api;

import com.example.seriea_api.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SerieaApiApplication implements CommandLineRunner {

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(SerieaApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appConfig.printAppInfo();
	}
}
