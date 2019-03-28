package com.prophet.web.system.dict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.web.system.dict.entity.DictEntity;
import com.prophet.web.system.dict.mapper.DictMapper;
import com.prophet.web.system.dict.service.impl.DictService;
@Service
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<DictEntity> queryList(String dictKey) {
	
		return dictMapper.queryList(dictKey);
	}
	
}
