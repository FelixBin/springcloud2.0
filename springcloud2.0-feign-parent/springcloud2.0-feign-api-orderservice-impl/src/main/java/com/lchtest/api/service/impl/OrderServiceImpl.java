package com.lchtest.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchtest.api.entity.UserEntity;
import com.lchtest.api.feign.MemberServiceFeign;
import com.lchtest.api.service.IOrderService;
import com.lchtest.common.base.BaseApiService;
import com.lchtest.common.base.ResponseBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 订单服务继承会员服务接口，用来实现Feign客户端，减少重复接口代码！
 * 
 * @author pc
 *
 */
@RestController
public class OrderServiceImpl extends BaseApiService implements IOrderService {

	@Autowired
	private MemberServiceFeign memberServiceFeign;

	/**
	 * http://localhost:8005/orderToMember?name=admin
	 */
	@RequestMapping("/orderToMember")
	@Override
	public String orderToMember(String name) {
		UserEntity user = memberServiceFeign.getMember(name);
		return user == null ? "no user find" : user.toString();
	}

	/**
	 * 测试服务雪崩效应 -没有解决雪崩效应 对该接口在member服务中的实现设置延迟时间,假设该接口需要1.5s才能处理完
	 * 
	 * @return
	 */
	@RequestMapping("/orderToMemberUserInfo")
	public ResponseBase orderToUserInfo() {
		System.out.println("orderToMemberUserInfo: 当前线程池名称" + Thread.currentThread().getName());
		return memberServiceFeign.getUserInfo();
	}

	/**
	 * 使用hystrix服务熔断方式解决服务雪崩效应： 1.
	 * application.yml开启Hystrix服务熔断：feign.hystrix.enabled=true 2.
	 * 主启动类加上@EnableHystrix 注解开启服务熔断 3. hystrix有两种方式配置保护服务:
	 * (1)通过@HystrixCommand注解形式，fallbackMethod表示服务降级执行；@HystrixCommand默认是线程池隔离
	 * 注解@HystrixCommand完成三件事：服务隔离，熔断，服务降级
	 * 
	 * @return
	 */
	@RequestMapping("/orderToMemberUserInfoHystrix")
	@HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallback")
	public ResponseBase orderToMemberUserInfoHystrix() {
		/*
		 * 启动eureka8100，member服务和order服务，浏览器访问http://localhost:8005/orderToMemberUserInfoHystrix，
		 * 返回{"rtnCode":200,"msg":"返回友好提示：服务降级，服务器忙，稍后重试","data":null}
		 */
		System.out.println("orderToMemberUserInfo: 当前线程池名称" + Thread.currentThread().getName());
		return memberServiceFeign.getUserInfo();
	}

	public ResponseBase orderToMemberUserInfoHystrixFallback() {
		System.out.println("orderToMemberUserInfoHystrix-服务降级调用的方法");
		return setResultSuccess("返回友好提示：服务降级，服务器忙，稍后重试");
	}

	@RequestMapping("/getOrderInfo")
	public String getOrderInfo() {
		System.out.println("getOrderInfo: 当前线程池名称" + Thread.currentThread().getName());
		return "getOrderInfo success.";
	}

	// 订单服务接口
	@Override
	public ResponseBase orderInfo() {
		return setResultSuccess();
	}

}
