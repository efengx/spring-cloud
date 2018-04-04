package com.yangst.cloud.demo.filter;


import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter{
	
	/**
	 * 判断过滤器是否需要被执行
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 过滤器执行顺序
	 * 多个过滤器时 执行顺序
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 过滤器类型，决定在生命周期中哪一步执行  
	 * pre 请求被路由之前执行
	 * routing 在路由请求时被调用
	 * post 在routing和error过滤器之后被调用
	 * error 处理请求发生错误时调用
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest  request = ctx.getRequest();
		System.err.println("send "+request.getMethod()+"request to"+request.getRequestURL().toString());
		
		Object accessToken = request.getParameter("accessToken");
		if(null == accessToken) {
			System.err.println("access token is empty");
			ctx.setSendZuulResponse(false);//false表示过滤该请求,不对其路由
			ctx.setResponseStatusCode(401);//设置返回错误嘛
//			ctx.setResponseBody(""); 可以设置返回body
		}
		System.err.println("access token ok");
		return null;
	}

}
