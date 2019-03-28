package com.prophet.web.system.dict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.web.system.dict.entity.DictEntity;

@Mapper
public interface DictMapper {
	List<DictEntity> queryList(String dictKey);
}