/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Sep 19, 2017 10:34:08 AM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.web.system.login.entry;

import java.io.Serializable;

/**
 * <P>角色信息</P>
 * @author #{李景川 15566909899}
 */
public class RoleInfo implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7765295875898814141L;
	//角色id
	private String roleId;
	//角色名称
	private String roleName;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
