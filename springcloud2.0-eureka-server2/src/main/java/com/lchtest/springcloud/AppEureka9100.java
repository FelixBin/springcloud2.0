package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eurekaע�����Ĵ
 * springcloud eurekaע������2
 * @author pc
 *
 */
@SpringBootApplication
// @EnableEureakServer��ʾ����ע������
@EnableEurekaServer
public class AppEureka9100 {
	
	public static void main(String[] args) {
		SpringApplication.run(AppEureka9100.class, args);
	}

}
