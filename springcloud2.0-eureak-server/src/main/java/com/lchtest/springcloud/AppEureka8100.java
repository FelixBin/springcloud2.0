package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * springcloud eureka注册中心
 * @author pc
 *
 */
@SpringBootApplication
// @EnableEureakServer表示开启注册中心
@EnableEurekaServer
public class AppEureka8100 {
	
	public static void main(String[] args) {
		SpringApplication.run(AppEureka8100.class, args);
	}

}
