/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Jul 9, 2017 12:54:43 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <P>系统主类</P>
 * 
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
