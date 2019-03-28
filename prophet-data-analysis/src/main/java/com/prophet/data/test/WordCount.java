package com.prophet.data.test;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
	
	
	public static class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] arryStr =  line.split(" ");
			for (String string : arryStr) {
				context.write(new Text(string), new IntWritable(1));
			}
		}
		
	}
	
	
	public static class WcReduce extends Reducer<Text,IntWritable,Text, IntWritable>{
		
		public int count = 0;
		
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
			for (IntWritable value : values) {
				 count =count + value.get();
			}
			context.write(new Text(key), new IntWritable(count));
		}
		
	}
	
	

	
	public static void main(String[] args) throws Exception {
		
		// 在代码中设置JVM系统参数，用于给job对象来获取访问HDFS的用户身份
		System.setProperty("HADOOP_USER_NAME", "root");
		
		
		Configuration conf = new Configuration();
		// 1、设置job运行时要访问的默认文件系统
		conf.set("fs.defaultFS", "hdfs://IT-00021:9000");
		// 2、设置job提交到哪去运行
		conf.set("mapreduce.framework.name", "yarn");
		conf.set("yarn.resourcemanager.hostname", "IT-00021");
		// 3、如果要从windows系统上运行这个job提交客户端程序，则需要加这个跨平台提交的参数
		conf.set("mapreduce.app-submission.cross-platform","true");
		
		Job job = Job.getInstance(conf);
		
		// 1、封装参数：jar包所在的位置
		job.setJar("E:\\gitrepo\\panhai\\Easr\\easr-data-clean\\target\\easr-data-clean-2.0.0-SNAPSHOT.jar");
		//job.setJarByClass(JobSubmitter.class);
		
		// 2、封装参数： 本次job所要调用的Mapper实现类、Reducer实现类
		job.setMapperClass(WcMapper.class);
		job.setReducerClass(WcReduce.class);
		
		// 3、封装参数：本次job的Mapper实现类、Reducer实现类产生的结果数据的key、value类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		
		Path output = new Path("/easr/test/result");
		FileSystem fs = FileSystem.get(new URI("hdfs://IT-00021:9000"),conf,"root");
		if(fs.exists(output)){
			fs.delete(output, true);
		}
		
		// 4、封装参数：本次job要处理的输入数据集所在路径、最终结果的输出路径
		FileInputFormat.setInputPaths(job, new Path("/easr/test"));
		FileOutputFormat.setOutputPath(job, output);  // 注意：输出路径必须不存在
		
		
		// 5、封装参数：想要启动的reduce task的数量
		job.setNumReduceTasks(2);
		
		// 6、提交job给yarn
		boolean res = job.waitForCompletion(true);
		
		System.exit(res?0:-1);
		
	}
	

}
