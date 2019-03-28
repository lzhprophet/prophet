package com.prophet.web.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P>service log 注解</P>
 * @author lvzhh
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface ServiceLog {

	String value() default "";
	int type() default 1;
	String desc() default "因为开发人员很懒所以没有写";
}
