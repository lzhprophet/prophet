package com.prophet.web.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 配置信息
 * 读取src/main/resources/properties/xxx.properties
 * 
 * @author lvzhh
 */
@Component
public class SystemConfig {

	
	@Getter @Setter
	@Value("${spring.security.excludeUrls}")
	private String springSecurityExcludeUrls;
	
	@Getter @Setter
	@Value("${spring.security.swagger}")
	private boolean swagger = false;
	
	
	public static final String PAGE_LIMIT_DEFAULT = "50";


}

