package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka注册中心搭建
 * springcloud eureka注册中心2
 * @author pc
 *
 */
@SpringBootApplication
// @EnableEureakServer表示开启注册中心
@EnableEurekaServer
public class AppEureka9100 {
	
	public static void main(String[] args) {
		SpringApplication.run(AppEureka9100.class, args);
	}

}
