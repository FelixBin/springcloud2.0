package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// @EnableEurekaClient 将当前服务注册到eureka上
@EnableEurekaClient
public class MemberApp {
	public static void main(String[] args) {
       SpringApplication.run(MemberApp.class, args);
	}
}
