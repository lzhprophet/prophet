package com.prophet.web.business.basestatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.web.business.basestatis.entity.BaseStatisEntity;
import com.prophet.web.business.basestatis.services.BaseStatisService;
import com.prophet.web.framework.global.model.Result;
import com.prophet.web.framework.global.model.ResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: basestatis
 * @Author: lvzhh
 * @CreateDate: 2018/11/8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/basestatis")
@Api(value="basestatis",tags= {"basestatis"})
public class BaseStatisController {

	@Autowired
	private BaseStatisService bss;
	
	
	/**
	 * 按项目查询基础统计信息(ip/uv/newuser/api)
	 */
	@GetMapping("/count-by-pcode" )
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<BaseStatisEntity> baseStatisInfoByPcode(@RequestParam(name="pcode",required=true) String pcode){
		Result<BaseStatisEntity> result = new Result<BaseStatisEntity>();
		BaseStatisEntity bse= bss.getBaseStatisInfoByPcode(pcode);
		result.successInfo(ResultEnum.SUCCESS.getCode(), bse);
		return result;
	}
	
	/**
	 * max/avg基础统计信息(ip/uv/newuser/api)
	 */
	@GetMapping("/maxavg-by-pcode" )
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<BaseStatisEntity> baseStatisMaxAvgByPcode(@RequestParam(name="pcode",required=true) String pcode){
		Result<BaseStatisEntity> result = new Result<BaseStatisEntity>();
		BaseStatisEntity bse= bss.avgAndMixBaseStatis(pcode);
		result.successInfo(ResultEnum.SUCCESS.getCode(), bse);
		return result;
	}
	
	
	/**
	 * 关键指标/昨日/时段
	 */
	@GetMapping("/base-statis-info-timesole" )
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<BaseStatisEntity> timesoleBaseStatisInfo(@RequestParam(name="pcode",required=true) String pcode){
		Result<BaseStatisEntity> result = new Result<BaseStatisEntity>();
		List<BaseStatisEntity> bseList= bss.getTimesoleBaseStatisInfo(pcode);
		result.successInfo(ResultEnum.SUCCESS.getCode(), bseList);
		return result;
	}
	
	
	
	
	
}
