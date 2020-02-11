package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient 将当前服务注册到eureka�?
@EnableEurekaClient
public class OrderApp {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}
	
	// ��RestTemplate����spring��������controller��ע�벢ʹ��
	@Bean
	// ���ʹ��rest��ʽ�Ա�����ʽ���÷����ṩ�ߵĽӿڣ���Ҫ����ribbon���ؾ�������	@LoadBalanced ��ʾ����ribbon���ؾ���
	@LoadBalanced 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
