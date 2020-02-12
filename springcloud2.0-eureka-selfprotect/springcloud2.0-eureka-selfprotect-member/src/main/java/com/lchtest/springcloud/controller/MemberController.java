package com.lchtest.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/getmember")
	public String getMember() {
		return "member service.port=" + port;
	}
	
	@RequestMapping("/member/getmember")
	public String getMemberByFeign() {
		return "member service port=" + port;
	}

}
