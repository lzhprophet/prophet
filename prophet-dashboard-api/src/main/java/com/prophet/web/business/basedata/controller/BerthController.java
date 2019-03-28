package com.prophet.web.business.basedata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.prophet.web.business.BusinessResultEnum;
import com.prophet.web.business.basedata.entity.BerthEntity;
import com.prophet.web.business.basedata.service.BerthService;
import com.prophet.web.framework.global.model.Result;
import com.prophet.web.system.config.SystemConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Description: 泊位管理
 * @Author: lvzhh
 * @CreateDate: 2018/5/8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/berth")
@Api(value="Berth",tags= {"泊位管理"})
public class BerthController {
	
	
	@Autowired
	private BerthService berthService;
	
		
	/**
	 * 泊位列表
	 */
	@GetMapping("/list" )
	@ApiOperation(value = "泊位管理", notes = "获取泊位列表", httpMethod = "GET")
	public Result<BerthEntity> list(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = SystemConfig.PAGE_LIMIT_DEFAULT) Integer limit){		  

		//查询
		Page<BerthEntity> list = berthService.queryList(page, limit);	
		
		Result<BerthEntity> result = new Result<BerthEntity>();
		result.success();
		result.setDatas(list);
		result.setCount(list.getTotal());
		
	    return result;  
	    
	}

	
	
	/**
	 * 泊位字典
	 */
	@GetMapping("/dict" )
	@ApiOperation(value = "泊位管理", notes = "获取泊位字典", httpMethod = "GET")
	public Result<BerthEntity> list(){		  
		//查询
		List<BerthEntity> list = berthService.queryListAll();	
		Result<BerthEntity> result = new Result<BerthEntity>();
		result.success();
		result.setDatas(list);
	    return result;  
	    
	}	
	
	/**
	 * 更新泊位信息
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping("/update")
	@ApiOperation(value = "泊位管理", notes = "更新泊位信息", httpMethod = "PUT")
	public Result update(@ApiParam(name = "berth info", value = "泊位信息", required = true) @RequestBody  BerthEntity berth){	
		//更新
		try {
			berthService.updateBerth(berth);
			return new Result().success();
		}catch(DuplicateKeyException e) {
			//泊位简称重复
			return new Result().error(BusinessResultEnum.BUS00000.getCode());
		}
		
	}

	
	
	
}
