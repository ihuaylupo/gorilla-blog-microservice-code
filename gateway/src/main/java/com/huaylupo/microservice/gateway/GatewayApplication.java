package com.huaylupo.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.huaylupo.microservice.gateway.filter.JwtAuthenticationFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RegistrationBean jwtAuthFilterRegister(JwtAuthenticationFilter filter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}
}

