package com.prophet.web.system.login.basic;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.web.framework.global.model.Result;
import com.prophet.web.framework.global.model.ResultEnum;
import com.prophet.web.system.login.entry.UserInfo;
import com.prophet.web.system.utils.SessionUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 登录模块
 * @author lvzhh
 *
 */
@RestController
@RequestMapping("/login")
public class LoginContorller {
	
	
	@Autowired
	private LoginService LoginService;
	
	@Value("${easr.login.success.redirect}")
	private String contentPage = "";


	/**
	 * 登录验证
	 */
	@GetMapping("")
	@ApiOperation(value = "登录",httpMethod = "GET")
	public Result<UserInfo> login(String username,String password) {
		//返回对象
		Result<UserInfo> result = new Result<UserInfo>();
		
		UserInfo user = LoginService.getUser(username);
		if(LoginService.userAuthentication(user, password)) {
			result.success();
		}
		
		
		if(result.isSuccess()) {
			//构建系统user信息,存储session
			this.createAndCacheUserByBasic(user);
		}		
		return result;

	}
	
	/**
	 * index页面跳转
	 */
	@GetMapping("/identification")
	@ApiOperation(value = "", notes = "用户页面跳转index页面", httpMethod = "GET")
	public void ssoIdentification(HttpServletResponse response) throws IOException {
		response.sendRedirect(contentPage);
	}
	
	/**
	 * 获取当前登录用户信息
	 */
	@GetMapping("/currentuser")
	@ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息", httpMethod = "GET")
	public Object currentUser() {
		//返回对象
		Result<Object> result = new Result<Object>();
		//构建系统user信息,存储session
		UserInfo user  =  SessionUtil.getUser();
		//设定返回值
		result.successInfo(ResultEnum.SUCCESS.getCode(), user);
		
		return result;

	}
	
	
	/**
	 * 通过basic方式用户信息构建并缓存
	 * @param request
	 * @return
	 */
	private  UserInfo createAndCacheUserByBasic(UserInfo user) {		
		//权限信息		
		user.setPermissions(LoginService.getPermsByUserId(user.getUserId()));
		// 角色信息
		user.setRoles(null);
		// session缓存
		SessionUtil.getSession().setAttribute(SessionUtil.SESSION_USER_KEY, user);
		return user;
	}
	
	
	
	
}
