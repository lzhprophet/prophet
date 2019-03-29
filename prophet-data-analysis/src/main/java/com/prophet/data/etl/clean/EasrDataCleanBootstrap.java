package com.prophet.data.etl.clean;

import java.net.URI;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prophet.data.config.EasrConfig;
import com.prophet.data.config.SystemConfig;

@Component
public class EasrDataCleanBootstrap {

	@Autowired
	private SystemConfig sc;

	public boolean dataCleanSubmit(String jarLocation,String day) throws Exception {
		EasrConfig ec = null;
		Job job =null;

		// 1.服务器上任务调度提交
		// 2.jar包所在的位置 本地提交
		if ("local".equals(jarLocation)) {
			ec = new EasrConfig(sc.hdfsNameNode,sc.yarnHostName,sc.hadoopUsername);
			job = ec.getJob();
			job.setJar(sc.localRunModeJar);
		} else {
			ec = new EasrConfig(EasrConfig.LOCAL_RUNNING_MODE,sc.hdfsNameNode,sc.yarnHostName,sc.hadoopUsername);
			job = ec.getJob();
			job.setJarByClass(com.prophet.data.Application.class);
		}

		// 2、封装参数： 本次job所要调用的Mapper实现类、Reducer实现类
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		job.setMapperClass(EasrDataCleanMapper.class);
		
		
		Path output = new Path("/prophet/data/out/cleandata/"+day);
		//Path output = new Path("/prophet/data/out/clean"+DateUtils.getNowDate("yyyyMMdd"));
		FileSystem fs = FileSystem.get(new URI(sc.hdfsNameNode), ec.getConfiguration(),sc.hadoopUsername);
		if (fs.exists(output)) {
			fs.delete(output, true);
		}

		// 4、封装参数：本次job要处理的输入数据集所在路径、最终结果的输出路径
		FileInputFormat.setInputPaths(job, new Path("/prophet/demo/"+day+".log"));
		FileOutputFormat.setOutputPath(job, output); // 注意：输出路径必须不存在
		// 不输出默认文件
		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
		// 5、封装参数：想要启动的reduce task的数量
		job.setNumReduceTasks(0);

		// 6、提交job给yarn
		boolean res = job.waitForCompletion(true);

		return res;
	}

}
