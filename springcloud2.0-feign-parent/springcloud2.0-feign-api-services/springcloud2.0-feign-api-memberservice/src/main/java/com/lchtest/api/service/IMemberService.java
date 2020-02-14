package com.lchtest.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lchtest.api.entity.UserEntity;
import com.lchtest.common.base.ResponseBase;

/**
 * memberservice接口定义
 * @author pc
 *
 */
public interface IMemberService {

	// 实体类放在接口项目里面比较好，可能其他项目也会依赖该实体类,代码实现放到接口的实现里面
	@RequestMapping("/getMember")
	public UserEntity getMember(@RequestParam("name") String name);
	
	@RequestMapping("/getUserInfo")
	public ResponseBase getUserInfo();
}
