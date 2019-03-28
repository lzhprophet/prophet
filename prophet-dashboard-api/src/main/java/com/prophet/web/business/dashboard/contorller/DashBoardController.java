package com.prophet.web.business.dashboard.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.web.business.accressmode.services.AccessModeStatisService;
import com.prophet.web.business.accressmode.vo.AccessModeStatisVo;
import com.prophet.web.business.apistatis.entity.ApiStatisEntity;
import com.prophet.web.business.apistatis.services.ApiStatisService;
import com.prophet.web.business.basestatis.entity.BaseStatisEntity;
import com.prophet.web.business.basestatis.services.BaseStatisService;
import com.prophet.web.framework.global.model.Result;
import com.prophet.web.framework.global.model.ResultEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: dashboard
 * @Author: lvzhh
 * @CreateDate: 2018/11/8
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dashboard")
@Api(value="dashboard basestatis",tags= {"dashboard basestatis"})
public class DashBoardController {

	@Autowired
	private BaseStatisService bss;
	
	@Autowired
	private AccessModeStatisService amss;
	
	@Autowired
	private ApiStatisService ass;
	
	
	/**
	 * bashboard基础统计信息查询(ip/uv/newuser/api)
	 */
	@GetMapping("/count")
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<BaseStatisEntity> baseStatisInfo(){
		Result<BaseStatisEntity> result = new Result<BaseStatisEntity>();
		BaseStatisEntity bse= bss.getBaseStatisInfo();
		result.successInfo(ResultEnum.SUCCESS.getCode(), bse);
		return result;
	}

	
	/**
	 * bashboard  访问方式查询(ip/uv/newuser/api)
	 */
	@GetMapping("/accress-mode" )
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<AccessModeStatisVo> accressMode(){
		Result<AccessModeStatisVo> result = new Result<AccessModeStatisVo>();
		AccessModeStatisVo accessModeStatisVo=amss.getAccessModeStatisInfo();
		result.successInfo(ResultEnum.SUCCESS.getCode(), accessModeStatisVo);
		return result;
	}
	
	/**
	 * bashboard  一周接口调用量
	 */
	@GetMapping("/api-week-count" )
	@ApiOperation(value = "", notes = "", httpMethod = "GET")
	public Result<ApiStatisEntity> apiWeekCount(){
		Result<ApiStatisEntity> result = new Result<ApiStatisEntity>();
		List<ApiStatisEntity> apiWeekList= ass.selectWeekApiList();
		result.successInfo(ResultEnum.SUCCESS.getCode(), apiWeekList);
		return result;
	}
	
	
}
