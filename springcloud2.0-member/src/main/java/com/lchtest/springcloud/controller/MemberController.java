package com.lchtest.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@Value("${server.port}")
	private String port;

	@RequestMapping("/getmember")
	public String getmember() {
		return "this is member service.port=" + port;
	}
}
