package com.lchtest.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	/**
	 * 1. springcloud服务调用有两种方式：restTemplate 和Fegin
	 * 2. resttemplate是由springboot-web组件提供的，默认整合了ribbon负载均衡器，
	 * spring-cloud-starter-netflix-eureka-client的依赖jar包可以说明，并且底层依赖的是httpclient（依赖了httpclient jar）
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("getorder")
	@ResponseBody
	public String getOrder() {
//		String url = "http://127.0.0.1:8000/getmember";
		// 使用别名去注册中心获取对应服务调用地址 如果报错java.net.UnknownHostException: member，在restTemplate定义的地方加上@LoadBalanced 
		String url = "http://app-member/getmember";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println("订单服务调用会员服务:" + result);
		return "订单服务调用会员服务结果:" + result;
	}
}
