package com.prophet.web.business.metadata.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.prophet.web.business.metadata.entity.ApiEntity;
import com.prophet.web.framework.global.model.QueryMap;

@Mapper
public interface ApiDefineMapper {
	
	public Page<ApiEntity> list(QueryMap query);
	
}