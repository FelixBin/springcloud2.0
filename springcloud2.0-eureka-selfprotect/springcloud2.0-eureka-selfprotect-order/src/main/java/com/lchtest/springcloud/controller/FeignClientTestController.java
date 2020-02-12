package com.lchtest.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchtest.springcloud.feignclient.MemberFeign;

@RestController
@RequestMapping("/orderfeign")
public class FeignClientTestController {
	@Autowired
	private MemberFeign memberFeign;
	
	/**
	 * 通过feign客户端调用member服务：http://localhost:8001/orderfeign/getmemberByFeign  
	 * @return
	 */
	@RequestMapping("/getmemberByFeign")
	public String getmember() {
		return "通过Feign客户端调用member服务,响应是：" + memberFeign.getmember();
	}
	
	

}
