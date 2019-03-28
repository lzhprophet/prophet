package com.prophet.web.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * <P>全局拦截器</P>
 * 
 * @author lvzhh
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public Object getBean(String name){
		return applicationContext.getBean(name);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) {
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
