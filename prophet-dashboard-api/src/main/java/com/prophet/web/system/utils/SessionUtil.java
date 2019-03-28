/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Sep 19, 2017 7:26:45 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.web.system.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.prophet.web.system.login.entry.UserInfo;

/**
 * <P>
 * session util
 * </P>
 * 
 * @author #{吕忠厚 15566909899}
 */
public final class SessionUtil {

	public static final String SESSION_USER_KEY = "user";
	
	
	private SessionUtil() {}
	
	/**
	 * 从session获取user
	 * @return
	 */
	public static UserInfo getUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();		
		UserInfo userInfo = null;
		if (hasUser(request)) {
			userInfo = (UserInfo) request.getSession().getAttribute(SessionUtil.SESSION_USER_KEY);
		} 
		return userInfo;
	}
	
	
	
	/**
	 * 判断是否登录,user信息是否在session中存在
	 * @param request
	 * @return
	 */
	private static boolean hasUser(HttpServletRequest request) {
		return request.getSession().getAttribute(SessionUtil.SESSION_USER_KEY) == null ? false : true;
	}

	
	
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();		
		return request;
	}
	
	public static HttpSession getSession() {
		return SessionUtil.getRequest().getSession();
	}
}
