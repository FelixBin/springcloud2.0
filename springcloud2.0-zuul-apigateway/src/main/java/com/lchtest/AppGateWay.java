package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * 微服务网关
 * @EnableZuulProxy 开启网关代理！
 * @author pc
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppGateWay {
	public static void main(String[] args) {
		SpringApplication.run(AppGateWay.class, args);
	}
}
