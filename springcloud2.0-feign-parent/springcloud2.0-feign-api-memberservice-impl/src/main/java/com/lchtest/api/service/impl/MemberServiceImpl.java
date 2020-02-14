package com.lchtest.api.service.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchtest.api.entity.UserEntity;
import com.lchtest.api.service.IMemberService;
import com.lchtest.common.base.BaseApiService;
import com.lchtest.common.base.ResponseBase;
/**
 * memberService的业务实现
 * @author pc
 *
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

	@RequestMapping("/getMember")
	@Override
	public UserEntity getMember(String name) {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(name);
		return userEntity;
	}

	
	// 测试服务雪崩效应，这里模拟接口处理延迟
	@RequestMapping("/getUserInfo")
	@Override
	public ResponseBase getUserInfo() {
		try {
			// 会员服务接口产生1.5s延迟
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return setResultSuccess("订单服务接口调用会员服务接口成功.");
	}

}
