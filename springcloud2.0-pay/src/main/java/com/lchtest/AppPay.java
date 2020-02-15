package com.lchtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class AppPay {
	
	public static void main(String[] args) {
		SpringApplication.run(AppPay.class, args);
	}
	
	@Value("${server.port}")
	private String serverPort;
	
	@GetMapping("/")
	public String index() {
		return "Pay service port=" + serverPort;
	}

}
