package com.prophet.web.system.dict.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.web.framework.global.model.Result;
import com.prophet.web.system.dict.entity.DictEntity;
import com.prophet.web.system.dict.service.impl.DictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Description: 字典
 * @Author: lvzhh
 * @CreateDate: 2018/5/8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sys/dict")
@Api(value="dict",tags= {"系统通用-字典"})
public class DictController {
	
	
	@Autowired
	private DictService dictService;
	
		
	/**
	 * 系统通用-字典
	 */
	@GetMapping("/list" )
	@ApiOperation(value = "系统通用-字典", notes = "获取字典列表", httpMethod = "GET")
	public Result<DictEntity> list(
			@ApiParam(name = "dictKey", value = "字典key", required = false) 
			@RequestParam(defaultValue = " ",required=true) String dictKey){		  

		//查询
		List<DictEntity>  dictList= dictService.queryList(dictKey.trim());
		Result<DictEntity> result = new Result<DictEntity>();
		result.success();
		result.setDatas(dictList);
		
		return result;  
	}

	
	
	
	
}
