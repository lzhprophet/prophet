package com.prophet.web.business.metadata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prophet.web.business.metadata.entity.ApiEntity;
import com.prophet.web.business.metadata.mapper.ApiDefineMapper;
import com.prophet.web.framework.global.model.QueryMap;

@Service
public class ApiDefineService {
	
	@Autowired
	private ApiDefineMapper apiDefineMapper;
	
	
	public Page<ApiEntity> apiDefineList(QueryMap query) {
		PageHelper.startPage(query.getPage(), query.getLimit());
		return apiDefineMapper.list(query);
	}
	
	
}
