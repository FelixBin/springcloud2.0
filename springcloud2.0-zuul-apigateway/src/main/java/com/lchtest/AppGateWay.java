package com.lchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * ΢��������
 * @EnableZuulProxy �������ش���
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
