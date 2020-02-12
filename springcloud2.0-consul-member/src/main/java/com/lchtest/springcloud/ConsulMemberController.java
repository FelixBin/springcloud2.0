package com.lchtest.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * springcloud使用consul作为注册中心
 * 启动服务后，http://localhost:8500/ui/dc1/services 查看consul注册中心上面的服务信息
 * @author pc
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulMemberController {
	
	@Value("${server.port}")
	private String port;

	@RequestMapping("/getmember")
	public String getmember() {
		return "this is member service.port=" + port;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsulMemberController.class, args);
	}

}
