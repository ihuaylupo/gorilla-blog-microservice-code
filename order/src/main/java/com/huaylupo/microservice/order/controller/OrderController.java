/**
 * 
 */
package com.huaylupo.microservice.order.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author ihuaylupo
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	private static final String LOCAL_SERVER_PORT = "local.server.port";

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@RequestMapping(method = POST)
	public ResponseEntity<String> getOrder(){
		return ResponseEntity.ok("Order Controller, Port: " + environment.getProperty(LOCAL_SERVER_PORT));
	}
	
	@RequestMapping(method = GET)
	public ResponseEntity<String> getOrderWithProducts(){
		logger.info("Get Order with products data ... ");
		//We use the restTemplate to call another service in this case the product-service.
		//Remember that we are using the spring.application.name we defined for the product on the
		//application.properties of the product microservice.
		String product = restTemplate.getForObject("http://product-service/product", String.class);
		logger.info("Returning data ... ");
		return ResponseEntity.ok("Order Controller, Port: " + environment.getProperty(LOCAL_SERVER_PORT) + " " + product );
		
	}

}
