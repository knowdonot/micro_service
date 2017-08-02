package com.duo.gate;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class Myfilter extends ZuulFilter{

	private static Logger logger = LoggerFactory.getLogger(Myfilter.class);
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info(String.format("%s request to %s", request.getMethod(),request.getRequestURL().toString()));
//		Object accessToken = request.getParameter("accessToken");
//		if(accessToken == null){
//			logger.warn("access_token is empty");
//			ctx.setSendZuulResponse(false);
//			ctx.setResponseStatusCode(401);
//			return "no token";
//		}
		logger.info(request.getLocalAddr()+" access token ok");
		return "success";
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
