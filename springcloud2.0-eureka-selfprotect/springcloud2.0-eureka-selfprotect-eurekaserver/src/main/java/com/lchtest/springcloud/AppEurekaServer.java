package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author pc
 *
 */
@SpringBootApplication
//@EnableEureakServer表示开启注册中心
@EnableEurekaServer
public class AppEurekaServer {
	public static void main(String[] args) {
		SpringApplication.run(AppEurekaServer.class, args);
	}
}


