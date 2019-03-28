package com.prophet.web.system.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.web.framework.global.model.QueryMap;
import com.prophet.web.framework.global.model.Result;
import com.prophet.web.framework.global.model.ResultEnum;
import com.prophet.web.framework.validator.ValidatorUtils;
import com.prophet.web.framework.validator.group.AddGroup;
import com.prophet.web.framework.validator.group.UpdateGroup;
import com.prophet.web.system.user.entity.SysUserEntity;
import com.prophet.web.system.user.service.SysUserService;
import com.prophet.web.system.utils.PageUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Description: 用户管理
 * @Author: Huang Chun
 * @CreateDate: 2018/4/9
 * @Version: 1.0
 */
@RestController
@Deprecated
@RequestMapping("/sys/user")
@Api(value = "sysUser", description = "用户管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	
	/**
	 * 用户列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "用户列表", notes = "获取用户列表", httpMethod = "POST")
	public Object list(@ApiParam(value = "json参数",  required = true) @RequestBody  Map<String, Object> params){
		Result<Object> result = new Result<>();
		//查询列表数据
		QueryMap query = new QueryMap(params);
		List<SysUserEntity> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);
		//翻页
		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
		//设定返回值
		result.successInfo(ResultEnum.SUCCESS.getCode(), pageUtil);
		return result;
	}

	/**
	 * 保存用户
	 */
	@PostMapping("/save")
	@ApiOperation(value = "保存用户", notes = "保存用户信息", httpMethod = "POST")
	public Object save(@ApiParam(value = "json参数",  required = true) @RequestBody SysUserEntity user){
		Result<Object> result = new Result<>();
		//校验
		ValidatorUtils.validateEntity(user, AddGroup.class);
		//保存用户
		sysUserService.save(user);
		//设定返回值
		result.successInfo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());

		return result;
	}
	
	/**
	 * 修改用户
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改用户", notes = "修改用户信息", httpMethod = "PUT")
	public Object update(@ApiParam(value = "json参数",  required = true) @RequestBody SysUserEntity user){
		Result<Object> result = new Result<>();
		//校验
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		//修改用户
		sysUserService.update(user);
		//设定返回值
		result.successInfo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());

		return result;
	}
	
	/**
	 * 删除用户
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value = "删除用户", notes = "删除用户信息", httpMethod = "DELETE")
	public Object delete(@ApiParam(value = "json参数",  required = true) @RequestBody Long[] userIds){
		Result<Object> result = new Result<>();
		//删除用户
		sysUserService.deleteBatch(userIds);
		//设定返回值
		result.successInfo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());

		return result;
	}
	
}
