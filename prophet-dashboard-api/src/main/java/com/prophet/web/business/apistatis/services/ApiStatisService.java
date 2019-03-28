package com.prophet.web.business.apistatis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.web.business.apistatis.entity.ApiStatisEntity;
import com.prophet.web.business.apistatis.mapper.ApiStatisMapper;

@Service
public class ApiStatisService {
	
	@Autowired
	private ApiStatisMapper apiStatisMapper;
	
	
	public List<ApiStatisEntity> selectWeekApiList(){
		return apiStatisMapper.selectWeekApiList();
	}
}
