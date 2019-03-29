/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 17, 2017 11:13:25 AM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * <P>全局拦截器</P>
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		// Do nothing because of X and Y.

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// Do nothing because of X and Y.

	}

}
