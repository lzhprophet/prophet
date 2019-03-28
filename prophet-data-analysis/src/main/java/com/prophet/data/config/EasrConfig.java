package com.prophet.data.config;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;


public class EasrConfig {

	public static final String YARN_RUNNING_MODE = "yarn";
	public static final String LOCAL_RUNNING_MODE = "local";
	

	
	public Configuration configuration = new Configuration();
	public Job job;
	
	public EasrConfig(String hdfsAddress,String yarnHostname,String hadoopUsername) throws IOException {
		job = this.initJob(YARN_RUNNING_MODE, hdfsAddress,yarnHostname,hadoopUsername);
	}

	
	public EasrConfig(String runningMode,String hdfsAddress,String yarnHostname,String hadoopUsername) throws IOException {
		job = this.initJob(runningMode, hdfsAddress,yarnHostname,hadoopUsername);
	}

	private Job initJob(String runningMode,String hdfsAddress,String yarnHostname,String hadoopUsername) throws IOException {
		
		// 在代码中设置JVM系统参数，用于给job对象来获取访问HDFS的用户身份
		System.setProperty("HADOOP_USER_NAME", hadoopUsername);
		
		if(runningMode.equals(YARN_RUNNING_MODE)){
			configuration.set("mapreduce.framework.name", YARN_RUNNING_MODE);
			configuration.set("yarn.resourcemanager.hostname", yarnHostname);
			configuration.set("mapreduce.app-submission.cross-platform", "true");
		}else if(runningMode.equals(LOCAL_RUNNING_MODE)){
			configuration.set("mapreduce.framework.name", LOCAL_RUNNING_MODE);
		}else{
			return null;
		}
		

		configuration.set("fs.defaultFS", hdfsAddress);

		Job job = Job.getInstance(configuration);
		return job;
	}
	
	public  Configuration getConfiguration() {
		return configuration;
	}
	
	public  Job getJob() {
		return job;
	}
	
}
