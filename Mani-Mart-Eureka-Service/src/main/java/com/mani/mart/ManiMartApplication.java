package com.mani.mart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ManiMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManiMartApplication.class, args);
	}

}
