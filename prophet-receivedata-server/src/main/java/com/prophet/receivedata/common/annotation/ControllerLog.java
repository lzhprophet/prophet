/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Jul 9, 2017 1:14:06 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P>controller层拦截注解</P>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface ControllerLog {

	String value() default "";
	int type() default 0;
	
}

