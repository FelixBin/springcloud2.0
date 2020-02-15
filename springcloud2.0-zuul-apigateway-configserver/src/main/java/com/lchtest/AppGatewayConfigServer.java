package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 *  zuul动态网关-分布式配置中心ConfigServer
 *  1.git上创建配置文件
 *  2.依次启动eureka注册中心，AppGatewayConfigServer
 * @author pc
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class AppGatewayConfigServer {
	public static void main(String[] args) {
		SpringApplication.run(AppGatewayConfigServer.class, args);
	}
}
