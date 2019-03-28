/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 18, 2017 4:25:08 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.web.business;

/**
 * <P>通用业务处理错误码</P>
 * 
 * @author #{lvzhh }
 */
public enum BusinessResultEnum {

	
	
	BUS00000("BUS00000","产品代码不能重复"),
	BUS00011("BUS00011","xxx");

	private String code;

	private String msg;

	BusinessResultEnum(String code, String msg) {
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
