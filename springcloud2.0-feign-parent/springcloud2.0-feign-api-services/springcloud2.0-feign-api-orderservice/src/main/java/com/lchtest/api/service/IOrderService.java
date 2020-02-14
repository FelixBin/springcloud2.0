package com.lchtest.api.service;

import org.springframework.web.bind.annotation.RequestMapping;

import com.lchtest.common.base.ResponseBase;

public interface IOrderService {
	// 订单服务调用会员服务接口
	@RequestMapping("/orderToMember")
	public String orderToMember(String name);

	// 订单服务调用会员服务接口
	@RequestMapping("/orderToMemberUserInfo")
	public ResponseBase orderToUserInfo();
	
	@RequestMapping("/orderInfo")
	public ResponseBase orderInfo();

}
