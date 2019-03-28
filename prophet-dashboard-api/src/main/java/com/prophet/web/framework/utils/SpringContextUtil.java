package com.prophet.web.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <P>
 * get spring ApplicationContext
 * </P>
 * 
 * @author #{lvzhh}
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
	
    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
	@SuppressWarnings("all")
    public static Object getBean(Class c){
        return applicationContext.getBean(c);
    }
    
}
