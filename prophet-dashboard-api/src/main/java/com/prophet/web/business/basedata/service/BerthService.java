package com.prophet.web.business.basedata.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.prophet.web.business.basedata.entity.BerthEntity;

public interface BerthService {
	
	Page<BerthEntity> queryList(Integer page ,Integer limit);
	
	List<BerthEntity> queryListAll();
    
	void updateBerth(BerthEntity berth) ;
}
