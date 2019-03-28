package com.prophet.web.system.dict.service.impl;

import java.util.List;

import com.prophet.web.system.dict.entity.DictEntity;

public interface DictService {
	List<DictEntity> queryList(String dictKey);
}
