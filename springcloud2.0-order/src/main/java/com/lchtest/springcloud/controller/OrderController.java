package com.lchtest.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
/**
 * ��������-member�����������
 * @author pc
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	/**
	 * 1. springcloud������������ַ�ʽ��restTemplate ��Fegin
	 * 2. resttemplate����springboot-web����ṩ�ģ�Ĭ��������ribbon���ؾ�������
	 * spring-cloud-starter-netflix-eureka-client������jar������˵�������ҵײ���������httpclient��������httpclient jar��
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("getorder")
	@ResponseBody
	public String getOrder() {
//		String url = "http://127.0.0.1:8000/getmember";
		// ʹ�ñ���ȥע�����Ļ�ȡ��Ӧ������õ�ַ �������java.net.UnknownHostException: member����restTemplate����ĵط�����@LoadBalanced 
		String url = "http://member/getmember";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println("����������û�Ա����:" + result);
		return "����������û�Ա������:" + result;
	}

}
