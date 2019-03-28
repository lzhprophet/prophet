package com.prophet.web.business.basestatis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.web.business.basestatis.entity.BaseStatisEntity;
import com.prophet.web.business.basestatis.mapper.BaseStatisMapper;

@Service
public class BaseStatisService {
	
	@Autowired
	private BaseStatisMapper baseStatisMapper;
	
	
	public BaseStatisEntity getBaseStatisInfo() {
		return baseStatisMapper.selectBaseStatis();
	}
	
	public BaseStatisEntity avgAndMixBaseStatis(String pcode) {
		return baseStatisMapper.avgAndMixBaseStatis(pcode);
	}
	
	public BaseStatisEntity getBaseStatisInfoByPcode(String pcode) {
		return baseStatisMapper.selectBaseStatisByPcode(pcode);
	}
	
	public List<BaseStatisEntity> getTimesoleBaseStatisInfo(String pcode) {
		return baseStatisMapper.selectTimesoleBaseStatisInfo(pcode);
	}
}
