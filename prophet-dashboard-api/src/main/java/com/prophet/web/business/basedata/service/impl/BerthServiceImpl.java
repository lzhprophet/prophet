package com.prophet.web.business.basedata.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prophet.web.business.basedata.entity.BerthEntity;
import com.prophet.web.business.basedata.mapper.BerthMapper;
import com.prophet.web.business.basedata.service.BerthService;
import com.prophet.web.framework.annotation.ServiceLog;

@Service
public class BerthServiceImpl implements BerthService{
	
	@Autowired
	private BerthMapper berthMapper;
	
    @Override
    @ServiceLog(desc="查询泊位列表")
    public Page<BerthEntity> queryList(Integer page ,Integer limit) {
    	PageHelper.startPage(page, limit);
        return berthMapper.list();
    }

	@Override
    @ServiceLog(desc="更新泊位列表")
	public void updateBerth(BerthEntity berth) {
		berthMapper.update(berth);
	}

	@Override
	public List<BerthEntity> queryListAll() {
		return berthMapper.list();
	}

    
    
}
