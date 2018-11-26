package com.lgcns.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BananaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BananaApplication.class, args);
	}
}
