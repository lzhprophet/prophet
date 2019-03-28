package com.prophet.web.system.login.basic;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prophet.web.framework.utils.ObjectUtil;
import com.prophet.web.system.login.entry.UserInfo;

@Service
public class LoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	public UserInfo getUser(String userId) {
		return loginMapper.getUser(StringUtils.trim(userId));
	}
	
	
	
	public boolean userAuthentication(UserInfo user ,String password) {
		String pass = StringUtils.trim(password);
		boolean  isAuthentication = false;
		
		if(!ObjectUtil.isObjectEmpty(user)) {
			isAuthentication = user.getPassword().equals(pass)?true:false;
		}
		
		return isAuthentication;
	}
	
	
	public Set<String> getPermsByUserId(String userId){
		return loginMapper.getPermsByUserId(userId);
	}
	
}
