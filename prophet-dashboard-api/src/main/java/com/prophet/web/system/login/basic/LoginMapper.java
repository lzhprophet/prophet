package com.prophet.web.system.login.basic;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.web.system.login.entry.UserInfo;

@Mapper
public interface LoginMapper {
	
	UserInfo getUser(String userId);
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	Set<String> getPermsByUserId(String userId);
}
