/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Jul 9, 2017 1:19:50 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.prophet.receivedata.common.filter.XssFilter;
import com.prophet.receivedata.common.interceptor.GlobalInterceptor;

/**
 * <P>配置信息</P>
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	 @Autowired
	 private GlobalInterceptor globalInterceptor;
	
	/**
	 * 静态资源访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}


	/**
	 * 过滤器
	 * @return
	 */
	//@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		// filter只能配置"/*","/**"无法识别
		registration.addUrlPatterns("/*");
		registration.setName("xssFilter");
		return registration;
	}
	


}
