package com.lchtest.api.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchtest.api.entity.UserEntity;
import com.lchtest.api.fallback.MemberServiceFallback;
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
	
	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/")
	public String index(HttpServletRequest req) {
		System.out.println("我是首页.....");
		return "我是order服务" + serverPort;
	}

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
	 * 没有com.lchtest.api.fallback.MemberServiceFallback这个类的情况下
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
	 * (1)通过@HystrixCommand注解形式，fallbackMethod表示服务降级执行；
	 * 注解@HystrixCommand默认是线程池隔离，注意，这个注解是对orderToMemberUserInfoHystrix方法开启一个新的线程池
	 * 我们期望的是orderToMemberUserInfoHystrix应该在主线程里面，而它调用的memberServiceFeign的方法才去开启一个线程池去处理
	 * 注解@HystrixCommand完成三件事：服务隔离，熔断，服务降级 
	 * 注解@HystrixCommand缺点：
	 *   1.最大的缺点就是加了@HystrixCommand注解的整个方法都会开启一个新的线程池去处理，这样不合理 
	 *   2.如果有多个方法需要进行服务降级，@HystrixCommand注解要写很多次，服务降级方法也要指定很多个，不太好
	 * 
	 * (2)通过类的方式，参见orderToMemberUserInfoHystrix2()方法
	 * 
	 * @return
	 */
	@RequestMapping("/orderToMemberUserInfoHystrix")
	@HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallback")
	public ResponseBase orderToMemberUserInfoHystrix() {
		/*
		 * 启动eureka8100，member服务和order服务，浏览器访问http://localhost:8005/
		 * orderToMemberUserInfoHystrix，
		 * 返回{"rtnCode":200,"msg":"返回友好提示：服务降级，服务器忙，稍后重试","data":null}
		 */
		System.out.println("orderToMemberUserInfoHystrix: 当前线程池名称" + Thread.currentThread().getName());
		// 此处代表业务逻辑代码

		// 希望仅针对memberServiceFeign.getUserInfo()接口做服务降级，在一个独立的线程池里面执行，而不是把上面的业务逻辑代码也给降级
		return memberServiceFeign.getUserInfo();
	}

	public ResponseBase orderToMemberUserInfoHystrixFallback() {
		System.out.println("orderToMemberUserInfoHystrix-服务降级调用的方法");
		return setResultSuccess("返回友好提示：服务降级，服务器忙，稍后重试");
	}

	/**
	 * Hystrix 第二种写法：使用类定义统一的fallback接口————推荐使用该方式
	 * (1)套路就是新建一个类实现Feign客户端接口，并加上@Component注解让spring托管
	 * (2)MemberServiceFeign上面的注解@FeignClient("app-member")修改为@FeignClient(name =
	 * "app-member", fallback = MemberServiceFallback.class) 即，指定一个类，这个类里面的方法是服务降级是要调用的方法
	 *   测试方法：
	 *   把orderservice-impl服务的配置中Hystrix超时配置hystrix.command.default.execution.timeout.enabled注释掉， 分别启动eureka server服务，
	 *  memberservice-impl服务，orderservice-impl服务，然后调用该接口http://localhost:8005/orderToMemberUserInfoHystrix2，
	 *    该接口通过Feign客户端调用memberservice的getUserInfo接口，getUserInfo接口有1.5s延时，而Feign客户端和hystrix默认超时时间是1s，
	 *    因此通过Feign接口调用memberServiceFeign.getUserInfo时会触发服务降级处理，
	 *    返回{"rtnCode":500,"msg":"服务器繁忙，请稍后再试! 以类的方式进行服务降级","data":null}
	 *
	 * @return
	 */
	@RequestMapping("/orderToMemberUserInfoHystrix2")
	public ResponseBase orderToMemberUserInfoHystrix2() {
		/*
		 * 启动eureka8100，member服务和order服务，浏览器访问http://localhost:8005/
		 * orderToMemberUserInfoHystrix，
		 * 返回{"rtnCode":200,"msg":"返回友好提示：服务降级，服务器忙，稍后重试","data":null}
		 */
		System.out.println("orderToMemberUserInfoHystrix2: 当前线程池名称" + Thread.currentThread().getName());
		// 此处代表业务逻辑代码

		// 希望仅针对memberServiceFeign.getUserInfo()接口做服务降级，在一个独立的线程池里面执行，而不是把上面的业务逻辑代码也给降级了
		return memberServiceFeign.getUserInfo();
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
