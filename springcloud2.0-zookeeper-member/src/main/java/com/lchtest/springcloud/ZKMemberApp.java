package com.lchtest.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 服务提供者
 */
@SpringBootApplication
// 如果注册中心是zookeeper或者consul,使用@EnableDiscoveryClient注解向注册中心注册服务
// 注意**win10系统下，zookeeper客户端和服务端的版本号要一致，否则会出现服务不能注册到注册中心的问题**
// 查看maven依赖的zookeeper的jar包的版本号，再去下载对应的zookeeper服务端
@EnableDiscoveryClient 
public class ZKMemberApp {
	public static void main(String[] args) {
       SpringApplication.run(ZKMemberApp.class, args);
	}
}
