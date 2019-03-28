package com.prophet.web.business.basestatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.web.business.basestatis.entity.BaseStatisEntity;

@Mapper
public interface BaseStatisMapper {

	BaseStatisEntity selectBaseStatis();
	
	BaseStatisEntity  selectBaseStatisByPcode(String pcode);
	
	List<BaseStatisEntity> selectTimesoleBaseStatisInfo(String pcode);
	
	BaseStatisEntity avgAndMixBaseStatis(String pcode);
	
}