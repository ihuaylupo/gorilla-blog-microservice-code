package com.huaylupo.cognito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CognitoAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CognitoAuthenticationApplication.class, args);
	}


}

