package com.prophet.web.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * <P>swagger2配置</P>
 * 
 * @author lvzhh
 */
//TODO:这个工程Swagger的没啥价值，暂时没有用
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	
	
	@Bean
	public Docket webApi() {

		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("prophet dashboard api")
		.apiInfo(apiInfo())
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.dashboard"))
		.paths(PathSelectors.any()).build();

	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
		.title("prophet 管理端")
		.description("后端服务")
		.termsOfServiceUrl("")
		.contact(new Contact("lvzhh", "", "lv_zhonghou@163.com"))
		.version("1.0").build();

	}

}
