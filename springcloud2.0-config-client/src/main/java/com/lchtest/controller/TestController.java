package com.lchtest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//当配置更改时，标有@RefreshScope的Bean将得到特殊处理来生效配置
@RefreshScope 
public class TestController {

	//从configserver读取配置
	@Value("${testconfigInfo}")
	private String testconfigInfo;
	
	// http://localhost:8882/getConfigValue
	@RequestMapping("/getConfigValue")
	public String getConfigValue() {
		return "configClient读取到的配置：testconfigInfo=" + testconfigInfo;
	}
}
