package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 *  zuul��̬����-�ֲ�ʽ��������ConfigServer
 *  1.git�ϴ��������ļ�
 *  2.��������eurekaע�����ģ�AppGatewayConfigServer
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
