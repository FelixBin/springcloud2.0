package com.lchtest.filter;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/**
 * ���ط��������
 * @author pc
 * @Componentע�⣬��@Componentע�뵽spring����
 */
@Component
public class TokenFilter extends ZuulFilter{
	
	/**
	 * ����������
	 */
	private static final String FILTE_RTYPE = "pre";
	
	/**
	 * �������Ƿ���Ч
	 */
	private static final boolean SHOULD_FILTER_EFFECTIVE  = true;
	
	/**
	 * �ӿڼ�Ȩʧ��
	 */
	private static final int UNAUTHORIZED = 401;

	/**
	 * �жϹ������Ƿ���Ч
	 */
	public boolean shouldFilter() {
		return SHOULD_FILTER_EFFECTIVE;
	}

	/**
	 * ������ҵ���߼�
	 */
	public Object run() throws ZuulException {
		//�������з���ӿڣ��жϷ���ӿ����Ƿ��д���userToken����
		//1.��ȡ������
		RequestContext currentContext = RequestContext.getCurrentContext();
		
		//2.��ȡrequest����
		HttpServletRequest request = currentContext.getRequest();
		
		//3.��ȡtoken tokenһ�㶼��������ͷ���淢��������˻�ȡtokenҲ�Ǵ�����ͷ�����ȡ
//		String token = request.getHeader("token");
		// ����ģ�����������л�ȡtoken
		String token = request.getParameter("userToken");
		
		if(StringUtils.isEmpty(token)) {
			// ������ִ�У������ط���ֱ����Ӧ�ͻ���
			currentContext.setSendZuulResponse(false);
			// ���ش�����ʾ
			currentContext.setResponseBody("userToken is missed.");
			currentContext.setResponseStatusCode(UNAUTHORIZED);
			return null;
		} 
		
		// �ӿڼ�Ȩͨ��������������������ӿ�
		return null;
	}

	/**
	 * ���������ͣ�
	 * pre:��������֮ǰִ��
	 */
	@Override
	public String filterType() {
		return FILTE_RTYPE;
	}

	/**
	 * ��������ִ��˳��(���ȼ�)
	 * һ��������ͬһ�׶δ��ڶ����������ʱ�򣬴��ڹ�������ִ��˳��
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}


}
