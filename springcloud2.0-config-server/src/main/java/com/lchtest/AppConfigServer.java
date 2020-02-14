package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * 分布式配置中心的ConfigServer
 * @author pc
 *  1.@EnableConfigServer注解开启config server
 *  2.先启动eureka注册中心，再启动AppConfigServer
 *  3.如何把配置文件存放到git上:
      在git上创建配置文件名称规范：服务名称-环境.properties/yml/yaml  
      如member-dev.yml表示会员服务开发环境的配置，member-prod.yml表示会员服务生产环境的配置  
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer  
public class AppConfigServer {
	public static void main(String[] args) {
		SpringApplication.run(AppConfigServer.class, args);
	}

}
