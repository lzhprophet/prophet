package com.prophet.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.data.etl.clean.EasrDataCleanBootstrap;
import com.prophet.data.etl.load.EasrBaseStatisLoadInHiveRepository;
import com.prophet.data.etl.load.EasrBaseStatisLoadOutMysqlRepository;
import com.prophet.data.etl.model.BaseStatisBean;
import com.prophet.data.etl.transform.EasrDataHiveUtilsRepository;
import com.prophet.data.etl.transform.EasrDataTransformRepository;

@Service
public class EasrDataEtlServices {
	
	@Autowired
	private EasrDataCleanBootstrap clean;
	
	@Autowired
	private EasrDataTransformRepository transformRepository;
	
	@Autowired
	private EasrDataHiveUtilsRepository hiveUtilsRepository;

	@Autowired
	private EasrBaseStatisLoadInHiveRepository baseStatisLoadInRepository;
	
	@Autowired
	private EasrBaseStatisLoadOutMysqlRepository baseStatisLoadOutMysqlRepository;
	
	
	public boolean runExtract(String jarLocation,String day) throws Exception {		
		//每天先进行数据清洗
		long startTime = System.currentTimeMillis();
		boolean cleanResult = clean.dataCleanSubmit(jarLocation,day);
		long endTime = System.currentTimeMillis();    
		System.out.println("---->>> 数据清洗 --->>>> 完成 [耗时："+(endTime - startTime)/1000 +"秒]");
		
		
		if(cleanResult) {
			long startTime1 = System.currentTimeMillis();
			
			
			//装载清洗好的昨天的数据
			transformRepository.extractCleanData(day);
			hiveUtilsRepository.setDynamicPartition();
			//将数据抽出到基本数据表中，以便于后续统计分析
			transformRepository.daysBaseDataTransform(day);
			transformRepository.daysIpInfoTransform(day);
			transformRepository.daysUvInfoTransform(day);
			transformRepository.daysApiInfoTransform(day);
			transformRepository.daysNewUserInfoTransform(day);
			
			long endTime1 = System.currentTimeMillis();    
			System.out.println("---->>> 数据装载、数据抽取(IP/UV/API/NU) --->>>> 完成 [耗时："+(endTime1 - startTime1)/1000 +"秒]");
			
		}else{
			System.out.println("数据清洗失败..");
		};

		return false;
		
	}
	
	public void runLoad(String day) {
		long startTime = System.currentTimeMillis();
	
		List<BaseStatisBean> bsbList= baseStatisLoadInRepository.selectCount(day);
		for (BaseStatisBean bsBean : bsbList) {
			int isexist = baseStatisLoadOutMysqlRepository.isExist(bsBean);
			if(isexist > 0) {
				baseStatisLoadOutMysqlRepository.updateBaseStatis(bsBean);
			}else {
				baseStatisLoadOutMysqlRepository.insertBaseStatis(bsBean);
			}
		}
		
		long endTime = System.currentTimeMillis();    
		System.out.println("---->>> 核心指标(IP/UV/API/NU)分析结果导出 --->>>> 完成 [耗时："+(endTime - startTime)/1000 +"秒]");
		
	}
	
	public void runLoadTimeslot(String day) {
		long startTime = System.currentTimeMillis();
		List<BaseStatisBean> bsbList= baseStatisLoadInRepository.selectCountByTimeslot(day);
		for (BaseStatisBean bsBean : bsbList) {
			int isexist = baseStatisLoadOutMysqlRepository.isExistByTimeslot(bsBean);
			if(isexist > 0) {
				baseStatisLoadOutMysqlRepository.updateBaseStatisByTimeslot(bsBean);
			}else {
				baseStatisLoadOutMysqlRepository.insertBaseStatisByTimeslot(bsBean);
			}
		}
		long endTime = System.currentTimeMillis();    
		System.out.println("---->>> 核心指标(IP/UV/API/NU)按时间段分析结果导出 --->>>> 完成 [耗时："+(endTime - startTime)/1000 +"秒]");
	}
	
}
