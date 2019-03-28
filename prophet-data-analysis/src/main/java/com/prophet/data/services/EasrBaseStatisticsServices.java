package com.prophet.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.data.etl.statistics.BaseStatisticsRepository;
import com.prophet.data.etl.transform.EasrDataHiveUtilsRepository;

@Service
public class EasrBaseStatisticsServices {
	@Autowired
	private EasrDataHiveUtilsRepository hiveUtilsRepository;
	@Autowired
	private BaseStatisticsRepository statisticsRepository;
	
	public void runBaseStatisByDay(String day) throws Exception {
		long startTime = System.currentTimeMillis();   
		
		hiveUtilsRepository.setDynamicPartition();
		//IP分析
		statisticsRepository.ipStatisticsByday(day);
		//活跃用户
		statisticsRepository.uvStatisticsByday(day);
		//接口调用量
		statisticsRepository.apiStatisticsByday(day);
		//新增用户调用量
		statisticsRepository.newUserStatisticsByday(day);
		
		long endTime = System.currentTimeMillis();    
		System.out.println("---->>> 核心指标(IP/UV/API/NU)统计分析  --->>>> 完成 [耗时："+(endTime - startTime)/1000 +"秒]");
	}
	
	public void runBaseStatisByTimeslot(String day) throws Exception {
		long startTime = System.currentTimeMillis();   
		
		hiveUtilsRepository.setDynamicPartition();
		//全部核心指标按事件段统计
		statisticsRepository.baseAllStatisticsByTimeslot(day);
		
		long endTime = System.currentTimeMillis();    
		System.out.println("---->>> 核心指标(IP/UV/API/NU) 按时段统计分析 --->>>> 完成 [耗时："+(endTime - startTime)/1000 +"秒]");
	}
	
}
