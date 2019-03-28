package com.prophet.web.business.basedata.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DuplicateKeyException;

import com.github.pagehelper.Page;
import com.prophet.web.business.basedata.entity.BerthEntity;

@Mapper
public interface BerthMapper {

	Page<BerthEntity> list();
	
	void  update(BerthEntity berth) throws DuplicateKeyException;
}