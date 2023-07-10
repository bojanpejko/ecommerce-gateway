package com.bojan.ecommercegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EcommerceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceGatewayApplication.class, args);
	}
}
