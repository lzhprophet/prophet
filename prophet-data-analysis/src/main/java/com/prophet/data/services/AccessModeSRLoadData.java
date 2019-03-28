package com.prophet.data.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.data.etl.load.EasrAccessModeSRLoadInHiveRepository;
import com.prophet.data.etl.load.EasrAccessModeSRLoadOutMysqlRepository;
import com.prophet.data.etl.model.AccessModeSRBean;

@Service
public class AccessModeSRLoadData {
	@Autowired
	private EasrAccessModeSRLoadInHiveRepository accessModeSRInRepository;
	
	@Autowired
	private EasrAccessModeSRLoadOutMysqlRepository accessModeSROutRepository;
	
	
	
	public void loadAccessModeSRData(String day) {
		
		long startTime = System.currentTimeMillis();   
		
		List<AccessModeSRBean> AMSRList= accessModeSRInRepository.accessModeStatis(day);
		for (AccessModeSRBean accessModeSRBean : AMSRList) {
			int isexist = accessModeSROutRepository.isExist(accessModeSRBean);
			if(isexist > 0) {
				accessModeSROutRepository.updateAccressModeResult(accessModeSRBean);
			}else {
				accessModeSROutRepository.insertAccressModeResult(accessModeSRBean);
			}
		}
		
		long endTime = System.currentTimeMillis();    
		System.out.println("---->>> 访问方式统计分析及分析结果导出 --->>>> 完成 [耗时："+(endTime - startTime)/1000 +"秒]");
	}
	
	
}
