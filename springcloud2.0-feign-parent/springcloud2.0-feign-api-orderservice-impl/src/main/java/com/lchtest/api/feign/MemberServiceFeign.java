package com.lchtest.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lchtest.api.entity.UserEntity;
import com.lchtest.api.service.IMemberService;
/**
 * Feign客户端定义，继承IMemberService，这样就可以避免类似下面这样的重复代码了：
 * @RequestMapping("/getMember")
	public UserEntity getMember(String name);
 * @author pc
 *
 */
@FeignClient("app-member")
public interface MemberServiceFeign extends IMemberService {
	
	/* 下面这个代码放到IMemberService中，这样orderservice和memberservice都可以共用，省的两个工程中都要写一样的代码，
	 * 由于orderservice调用memberService的getMember接口时带了请求参数，因此IMemberService的getMember方法要使用@RequestParam注解接收请求参数！！！
	 * @RequestMapping("/getMember") public UserEntity
	 * getMember(@RequestParam("name") String name);
	 */

}
