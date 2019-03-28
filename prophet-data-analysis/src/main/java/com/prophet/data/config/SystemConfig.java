package com.prophet.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置信息  
 * @author lvzhh
 */
@Component
public class SystemConfig {

	@Value("${hdfs.name.node}")
	public  String hdfsNameNode = "hdfs://IT-00021:9000";
	
	@Value("${yarn.host.name}")
	public  String yarnHostName = "IT-00021";
	
	@Value("${hadoop.user.name}")
	public  String hadoopUsername = "root";
	
	@Value("${local.runing.mode.jar}")
	public  String localRunModeJar = "";
}

