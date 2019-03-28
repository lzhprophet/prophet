package com.prophet.data.etl.clean;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prophet.data.entity.EventInfo;
import com.prophet.data.utils.DateUtils;

public class EasrDataCleanMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	ObjectMapper mapper ;
	NullWritable nullwritable;
	Text outKey ;
	//多路输出
	MultipleOutputs<Text, NullWritable> muop ; 
	public final String DATA_SEPARATOR="\t";
	public List<String> list = Arrays.asList("web","h5","android","weixinapp","ios");
	public Map<String,String> tempMap = new HashMap<String,String>();
	
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		 mapper = new ObjectMapper();
		 nullwritable =NullWritable.get();
		 outKey = new Text();
		 muop = new MultipleOutputs<Text, NullWritable>(context);
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String lineStr = value.toString();
		EventInfo event = new EventInfo();
		
		try {
			event = mapper.readValue(lineStr, EventInfo.class);
		}catch(Exception e) {
			return;
		}
		
		String userId = "null";
		String accessMode = event.getProject().getProjectModelCode();
		
		if(!event.getDistinctId().equals(event.getProperties().getDevice_id())) {
			userId = event.getDistinctId();
		}
		if(accessMode == null || "".equals(accessMode) ) {
			accessMode =  "Unknown";
		}
		
		//造数据
		if(!"null".equals(userId)) {
			
			Integer accessModeKey = 0; 
			try {
				if(!tempMap.containsKey(userId)) {
					accessModeKey = Integer.parseInt(userId);
				    int index = (int) (Math.random()* list.size());
				    accessMode = list.get(index);
				}else {
					accessMode = tempMap.get(userId);
				}
				
			}catch(NumberFormatException e){
				
			}
			tempMap.put(userId, accessMode);
			
		}
		
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(event.getProject().getProjectCode()).append(this.DATA_SEPARATOR)
			.append(event.getProject().getProjectModelVersion()).append(this.DATA_SEPARATOR)
			.append(event.getLib().getLib()).append(this.DATA_SEPARATOR)
			.append(event.getIp()).append(this.DATA_SEPARATOR)
			.append(userId).append(this.DATA_SEPARATOR)
			.append(event.getEvent()).append(this.DATA_SEPARATOR)
			.append(event.getProperties().getUrl_path()).append(this.DATA_SEPARATOR)
			.append(event.getProperties().getDevice_id()).append(this.DATA_SEPARATOR)
			.append(event.getProperties().getRt()).append(this.DATA_SEPARATOR)
			.append(accessMode).append(this.DATA_SEPARATOR)
			//.append(DateUtils.stampToDateTime(event.getUseClientTrackTime())).append(" ") 客户端事件发生时间,目前看没啥用处
			.append(DateUtils.changeTimeSlot(event.getTrackTime())).append(this.DATA_SEPARATOR)
			.append(DateUtils.stampToDateTime(event.getTrackTime()));

		outKey.set(sb.toString());
		
		
		//日志时间
		String trackDate = DateUtils.stampToDate(event.getTrackTime());
				
		/*if(event.getProject().getProjectCode().equals("dm")) {
			muop.write(outKey, nullwritable, "dm/dm-"+trackDate);
		}else if(event.getProject().getProjectCode().equals("phmm"))  {
			muop.write(outKey, nullwritable, "phmm/phmm-"+trackDate);
		}else{
			muop.write(outKey, nullwritable, "other/other-"+trackDate);
		}*/
		//if(trackDate.equals("2019-01-27")) 
		if(trackDate.equals(DateUtils.getNowDate("yyyy-MM-dd"))) 
			return;
		muop.write(outKey, nullwritable, "data");
		
	
		//context.write(outKey, nullwritable);
	}

	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		muop.close();
	}
	

}
