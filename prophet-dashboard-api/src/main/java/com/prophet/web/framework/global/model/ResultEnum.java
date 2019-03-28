/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 18, 2017 4:25:08 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.web.framework.global.model;

/**
 * <P>通用错误码</P>
 * 
 * @author lvzhh
 */
public enum ResultEnum {

	SYS00000("SYS00000","系统异常"),
	SYS00001("SYS00001","包含非法字符"),

	BUS00000("BUS00000","业务异常"),

	SUCCESS("200", "成功"),
	ERROR500("500", "内部错误"),
	ERROR404("404", "客户端错误"),
	CREATE("201", "请求已经被实现"),
	ACCEPTED("202 ", "服务器已接受请求");

	private String code;

	private String msg;

	ResultEnum(String code, String msg) {
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
