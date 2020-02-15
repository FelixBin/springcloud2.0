package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * Î¢·þÎñÍø¹Ø
 * @author pc
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class AppServiceZuul {
	public static void main(String[] args) {
		SpringApplication.run(AppServiceZuul.class, args);
	}
}
