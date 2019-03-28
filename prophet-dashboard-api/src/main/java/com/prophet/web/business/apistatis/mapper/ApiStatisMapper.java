package com.prophet.web.business.apistatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.web.business.apistatis.entity.ApiStatisEntity;

@Mapper
public interface ApiStatisMapper {
	public List<ApiStatisEntity> selectWeekApiList();
}
