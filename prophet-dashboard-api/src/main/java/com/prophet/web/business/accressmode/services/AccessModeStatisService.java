package com.prophet.web.business.accressmode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.web.business.accressmode.entity.AccessModeStatisEntity;
import com.prophet.web.business.accressmode.mapper.AccessModeStatisMapper;
import com.prophet.web.business.accressmode.vo.AccessModeStatisVo;

@Service
public class AccessModeStatisService {
	
	@Autowired
	private AccessModeStatisMapper accessModeStatisMapper;
	
	public AccessModeStatisVo getAccessModeStatisInfo() {
		AccessModeStatisVo  accessModeStatisVo= new AccessModeStatisVo();
		List<String> legendList= accessModeStatisMapper.selectAccessModeLegendList();
		accessModeStatisVo.setAccessModeLegendList(legendList);
		List<AccessModeStatisEntity> accessModeDataList =accessModeStatisMapper.selectAccessModeStatisList();
		accessModeStatisVo.setAccessModeDataList(accessModeDataList);
		return accessModeStatisVo;
	}
	
}
