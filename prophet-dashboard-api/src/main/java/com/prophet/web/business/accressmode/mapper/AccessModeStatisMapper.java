package com.prophet.web.business.accressmode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.web.business.accressmode.entity.AccessModeStatisEntity;

@Mapper
public interface AccessModeStatisMapper {

	public List<String> selectAccessModeLegendList(); 
	
	public List<AccessModeStatisEntity> selectAccessModeStatisList(); 
}