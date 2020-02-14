package com.lchtest.api.fallback;

import org.springframework.stereotype.Component;

import com.lchtest.api.entity.UserEntity;
import com.lchtest.api.feign.MemberServiceFeign;
import com.lchtest.common.base.BaseApiService;
import com.lchtest.common.base.ResponseBase;

/**
 *  服务降级
 * 只要调用MemberServiceFeign中定义的接口，就开启一个独立的线程池去处理接口请求
 * 服务降级也只是针对MemberServiceFeign中定义的接口进行服务降级处理
 * 这里继承BaseApiService仅仅是为了方便设置响应setResultError
 * @author pc
 *
 */

@Component
public class MemberServiceFallback extends BaseApiService implements MemberServiceFeign{

	@Override
	public UserEntity getMember(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	// 这个方法相当于com.lchtest.api.service.impl.OrderServiceImpl类的orderToMemberUserInfoHystrixFallback方法
	@Override
	public ResponseBase getUserInfo() {
		return setResultError("服务器繁忙，请稍后再试! 以类的方式进行服务降级");
	}

}
