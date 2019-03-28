package com.prophet.web.framework.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.prophet.web.framework.annotation.ControllerLog;
import com.prophet.web.framework.annotation.ServiceLog;
import com.prophet.web.system.utils.SessionUtil;



/**
 * <P>日志切面类</P>
 * 
 * @author lvzhh
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
	@Pointcut("@annotation(com.prophet.web.framework.annotation.ServiceLog)")
	public void serviceAspect() {
		// Do nothing because of X and Y.
	}

	/**
	 * 
	 * <p>Controller层切点</p>
	 * 
	 * @author #{李景川 15566909899}
	 */
	@Pointcut("@annotation(com.prophet.web.framework.annotation.ControllerLog)")
	public void controllerAspect() {
		// Do nothing because of X and Y.
	}

	/**
	 * @param joinPoint 切点
	 */
	@Before("serviceAspect()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			LogAspect.logService(joinPoint);
		} catch (ClassNotFoundException e) {
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", e);
		}
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "defaultControllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		
		try {
			LogAspect.execeptionLog(joinPoint, e);
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex);
		}
		
	}
	

	// TODO:  发生业务异常，直接存到数据库里面  ，没写完
/*	private void getLog() {
		System.out.println();
	}*/

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
	
	
	
	
	/**
	 * 记录service 日志
	 * 
	 * @param joinPoint 切点
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes"})
	public static ServiceLog logService(JoinPoint joinPoint) throws ClassNotFoundException {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		ServiceLog logAnnotation = null;
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					logAnnotation = method.getAnnotation(ServiceLog.class);
					break;
				}
			}
		}
		logger.info("[ "  +SessionUtil.getUser().getUserName() + "==>>> 调用  "+targetName+"."+methodName
				 + " 执行  ==>>> " + logAnnotation.desc() +"==>>> 操作成功 ]");
		
		return logAnnotation;
	}
	
	
	
	/**
	 * 记录exeception日志
	 * 
	 */
	public static void execeptionLog(JoinPoint joinPoint,Throwable e) throws ClassNotFoundException {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arguments.length; i++) {
			Object object = arguments[i];
			sb.append("第"+i+"参数:").append(object.toString());
		}
		
		logger.error("[ "  + SessionUtil.getUser().getUserName() + "==>>> 调用   "+targetName+" 中执行方法: "+methodName+" 参数  "+sb.toString()+" 出现异常]");
		logger.error("[ 异常信息： " +e+" ]");
		
	}
	
	

}
