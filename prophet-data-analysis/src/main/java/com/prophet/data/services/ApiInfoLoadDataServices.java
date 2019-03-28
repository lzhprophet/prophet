package com.prophet.data.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.prophet.data.etl.load.EasrApiLoadInHiveRepository;
import com.prophet.data.etl.load.EasrApiLoadOutMysqlRepository;
import com.prophet.data.etl.model.ApiInfoBean;

@Service
public class ApiInfoLoadDataServices {
	@Autowired
	private EasrApiLoadInHiveRepository apiLoadIn;
	
	@Autowired
	private EasrApiLoadOutMysqlRepository apiLoadOut;
	
	
	
	public void loadApiInfo(String day) {
		
		long startTime = System.currentTimeMillis();   
		
		List<ApiInfoBean> ApiInfoList= apiLoadIn.getNewApiByDay(day);
		int repeatCount = 0;
		for (ApiInfoBean apiInfo : ApiInfoList) {
			
			try {
				apiLoadOut.insertApiInfo(apiInfo);
			} catch (DataAccessException e) {
				repeatCount++;
			}
		}
		
		long endTime = System.currentTimeMillis();    
		StringBuilder sb = new StringBuilder();
		sb.append("---->>> 接口基本信息同步MySql完成,共计:").append(ApiInfoList.size()).append("条数据 ,");
		sb.append("主键冲突:").append(repeatCount).append("条数据 !");
		sb.append(" --->>>> 完成 [耗时：").append((endTime - startTime)/1000 ).append("秒]");
		
		System.out.println(sb.toString());
	}
	
	
}
