package com.lchtest.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	/**
	 * 通过DiscoveryClient获取注册中心上的服务列表信息  http://localhost:8003/getServiceInfo
	 * {"serviceId":"zk-order","host":"windows10.microdone.cn","port":8003,"secure":false,"uri":"http://windows10.microdone.cn:8003","metadata":{},"serviceInstance":{"name":"zk-order","id":"bd4d9b0f-24dc-45bd-9a85-d99a248dd332","address":"windows10.microdone.cn","port":8003,"sslPort":null,"payload":{"id":"application-1","name":"zk-order","metadata":{}},"registrationTimeUTC":1581493274959,"serviceType":"DYNAMIC","uriSpec":{"parts":[{"value":"scheme","variable":true},{"value":"://","variable":false},{"value":"address","variable":true},{"value":":","variable":false},{"value":"port","variable":true}]},"enabled":true},"scheme":null}
	 * @return
	 */
	@RequestMapping("/getServiceInfo")
	public Object getServiceInfo() {
		List<ServiceInstance> instances = discoveryClient.getInstances("zk-order");
		return instances.get(0);  //服务集群情况下返回的是多个服务的集合
	}
	
}
