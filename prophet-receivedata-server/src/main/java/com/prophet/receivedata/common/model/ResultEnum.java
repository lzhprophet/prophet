/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 18, 2017 4:25:08 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.model;

/**
 * <P>通用错误码</P>
 * 
 */
public enum ResultEnum {
	
	//common
	SUCCESS("00000", "操作成功"),
	ERR00000("ERR00000", "未知错误");


	private String msg;
	private String code;

	private ResultEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return msg;
	}

}
