package com.lchtest.filter;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/**
 * 网关服务过滤器
 * @author pc
 * @Component注解，将@Component注入到spring容器
 */
@Component
public class TokenFilter extends ZuulFilter{
	
	/**
	 * 过滤器类型
	 * pre：可以在请求被路由之前调用
	   routing：在路由请求时候被调用
	   post：在routing和error过滤器之后被调用
	   error：处理请求时发生错误时被调用
	 */
	private static final String FILTE_RTYPE = "pre";
	
	/**
	 * 过滤器是否生效
	 */
	private static final boolean SHOULD_FILTER_EFFECTIVE  = true;
	
	/**
	 * 接口鉴权失败
	 */
	private static final int UNAUTHORIZED = 401;

	/**
	 * 判断过滤器是否生效
	 */
	public boolean shouldFilter() {
		return SHOULD_FILTER_EFFECTIVE;
	}

	/**
	 * 过滤器业务逻辑
	 */
	public Object run() throws ZuulException {
		//拦截所有服务接口，判断服务接口上是否有传递userToken参数
		//1.获取上下文
		RequestContext currentContext = RequestContext.getCurrentContext();
		
		//2.获取request对象
		HttpServletRequest request = currentContext.getRequest();
		
		//3.获取token token一般都放在请求头里面发送请求，因此获取token也是从请求头里面获取
//		String token = request.getHeader("token");
		// 这里模拟从请求参数中获取token
		String token = request.getParameter("userToken");
		
		if(StringUtils.isEmpty(token)) {
			// 不往下执行，由网关服务直接响应客户端
			currentContext.setSendZuulResponse(false);
			// 返回错误提示
			currentContext.setResponseBody("userToken is missed.");
			currentContext.setResponseStatusCode(UNAUTHORIZED);
			return null;
		} 
		
		// 接口鉴权通过，正常调用其他服务接口
		return null;
	}

	/**
	 * 过滤器类型：
	 * pre:在请求处理之前执行
	 */
	@Override
	public String filterType() {
		return FILTE_RTYPE;
	}

	/**
	 * 过滤器的执行顺序(优先级)
	 * 一个请求在同一阶段存在多个过滤器的时候，存在过滤器的执行顺序
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}


}
