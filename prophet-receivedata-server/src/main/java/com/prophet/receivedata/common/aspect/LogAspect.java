/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Jul 9, 2017 1:41:15 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.prophet.receivedata.common.annotation.ControllerLog;
import com.prophet.receivedata.common.annotation.ServiceLog;


/**
 * <P>日志切面类</P>
 * 
 * @author #{李景川 15566909899}
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


	/**
	 * 
	 * <p>Service层切点</p>
	 * 
	 * @author #{李景川 15566909899}
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void defaultControllerAspect() {
		// Do nothing because of X and Y.
	}

	/**
	 * 
	 * <p>Service层切点</p>
	 * 
	 * @author #{李景川 15566909899}
	 */
	@Pointcut("@annotation(com.panhai.easr.receivedata.common.annotation.ServiceLog)")
	public void serviceAspect() {
		// Do nothing because of X and Y.
	}

	/**
	 * 
	 * <p>Controller层切点</p>
	 * 
	 * @author #{李景川 15566909899}
	 */
	@Pointcut("@annotation(com.panhai.easr.receivedata.common.annotation.ControllerLog)")
	public void controllerAspect() {
		// Do nothing because of X and Y.
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint 切点
	 */
	@Before("defaultControllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		getLog();
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "defaultControllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		getLog();
	}

	@SuppressWarnings("unused")
	private void getLog() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			ParamData params = new ParamData();
			//todo save  user oper record
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint 切点
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static ControllerLog getControllerAnnotation(JoinPoint joinPoint) throws ClassNotFoundException {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		ControllerLog logAnnotation = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					logAnnotation = method.getAnnotation(ControllerLog.class);
					break;
				}
			}
		}
		return logAnnotation;
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint 切点
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static ServiceLog getServiceAnnotation(JoinPoint joinPoint) throws ClassNotFoundException {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		ServiceLog logAnnotation = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					logAnnotation = method.getAnnotation(ServiceLog.class);
					break;
				}
			}
		}
		return logAnnotation;
	}

}
