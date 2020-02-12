package com.lchtest.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableDiscoveryClient  // 通过@EnableDiscoveryClient注解将服务注册到zookeeper注册中心
public class ZKOrderController {
	
	public static void main(String[] args) {
		SpringApplication.run(ZKOrderController.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/orderTomember")
	public String orderTomember() {
		String memberUrl = "http://zk-member/getmember";
		return restTemplate.getForObject(memberUrl, String.class);
	}
}
