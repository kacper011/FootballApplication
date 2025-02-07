package com.example.transfermarket;

import com.example.transfermarket.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;

@SpringBootApplication(exclude = {LoadBalancerAutoConfiguration.class})
public class TransfermarketApplication implements CommandLineRunner {

	@Autowired
	public AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(TransfermarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appConfig.printAppInfo();
	}
}
