package com.main.ProductApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiGatewayApplication.class, args);
	}

}
