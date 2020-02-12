package com.lchtest.springcloud.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * 模拟ribbon本地负载均衡,要把eureka自我保护关掉，需要把order服务中的LoadBalance注解注释掉
 * @author pc
 *
 */
@RestController
public class ExtRibbonController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	private AtomicInteger reqCountter = new AtomicInteger(1);
	
	/**
	 * 启动eureka server，order服务和member服务的两个实例,多次访问http://localhost:8001/ribbonMember,查看结果
	 * @return
	 */
	@RequestMapping("/ribbonMember")
	public String ribbonMember() {
		// 拼接member服务的调用地址
		String instanceUrl = getServiceInstance() + "/getmember";
		System.out.println("instanceUrl = " + instanceUrl);
		// 调用member服务，获取响应
		String resp = restTemplate.getForObject(instanceUrl, String.class);
		return resp;
		
	}

	/**
	 * eureka客户端模拟本地负载均衡（相对nginx的服务器负载均衡来说）
	 * @return 将要处理请求的服务器的地址
	 */
	private String getServiceInstance() {
		// 从eureka注册中心获取member服务的集群信息
		List<ServiceInstance> instances = discoveryClient.getInstances("app-member");
		if(instances == null || instances.size() == 0) {
			return null;
		}
		int instanceSzie = instances.size();
		// 模拟负载均衡算法 请求总数对服务器数量取模
		int serviceIndex = reqCountter.getAndIncrement() % instanceSzie;
		return instances.get(serviceIndex).getUri().toString();
	}
}
