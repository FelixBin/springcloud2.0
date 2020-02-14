package com.lchtest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//�����ø���ʱ������@RefreshScope��Bean���õ����⴦������Ч����
@RefreshScope 
public class TestController {

	//��configserver��ȡ����
	@Value("${testconfigInfo}")
	private String testconfigInfo;
	
	// http://localhost:8882/getConfigValue
	@RequestMapping("/getConfigValue")
	public String getConfigValue() {
		return "configClient��ȡ�������ã�testconfigInfo=" + testconfigInfo;
	}
}
