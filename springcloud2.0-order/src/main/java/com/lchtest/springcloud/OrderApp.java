package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient 将当前服务注册到eureka注册中心
@EnableEurekaClient
public class OrderApp {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}
	
	// 将RestTemplate交给spring容器管理，controller中注入并使用
	@Bean
	// 如果使用rest方式以别名方式调用服务提供者的接口，需要依赖ribbon负载均衡器，@LoadBalanced注解启用ribbon负载均衡
	@LoadBalanced 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
