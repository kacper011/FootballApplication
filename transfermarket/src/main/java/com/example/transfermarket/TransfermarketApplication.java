package com.example.transfermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;

@SpringBootApplication(exclude = {LoadBalancerAutoConfiguration.class})
public class TransfermarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfermarketApplication.class, args);
	}

}
