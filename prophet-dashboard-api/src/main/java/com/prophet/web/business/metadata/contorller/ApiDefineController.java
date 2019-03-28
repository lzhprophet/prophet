package com.prophet.web.business.metadata.contorller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.prophet.web.business.metadata.entity.ApiEntity;
import com.prophet.web.business.metadata.services.ApiDefineService;
import com.prophet.web.framework.global.model.QueryMap;
import com.prophet.web.framework.global.model.Result;
import com.prophet.web.framework.global.model.ResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: meta api
 * @Author: lvzhh
 * @CreateDate: 2019/01/05
 * @Version: 1.0
 */
@RestController
@RequestMapping("/meta/apidefine")
@Api(value="meta api define",tags= {"meta api define"})
public class ApiDefineController {

	@Autowired
	private ApiDefineService apiDefineService;
	
	/**
	 * 接口列表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<ApiEntity> list(@RequestParam Map<String,Object> params){
		QueryMap query = new  QueryMap(params);
		Result<ApiEntity> result = new Result<ApiEntity>();
		Page<ApiEntity> apiDefineList = apiDefineService.apiDefineList(query);
		result.successInfo(ResultEnum.SUCCESS.getCode(), apiDefineList);
		return result;
	}
	
	
	
	
	
}
