package com.lchtest.springcloud.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 声明式Feign客户端调用服务
 * 
 * @author pc
 *
 */
@FeignClient(value = "app-member")
public interface MemberFeign {

	@RequestMapping("/member/getmember")
	public String getmember();

}
