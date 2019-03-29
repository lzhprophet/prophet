/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 17, 2017 2:31:39 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.exception;

import com.prophet.receivedata.common.model.ResultEnum;


/**
 * <P>TODO</P>
 * 
 */
public class BusinessCheckedException extends Exception {

	private static final long serialVersionUID = -6478733092361487278L;

	private final String code;
	private final String msg;

	public BusinessCheckedException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public BusinessCheckedException(String code, String msg,Throwable cause) {
		super(msg,cause);
		this.code = code;
		this.msg = msg;
	}

	public BusinessCheckedException(ResultEnum error) {
		this(error.getCode(),error.getMsg());
	}
	
	public BusinessCheckedException(ResultEnum error,Throwable cause) {
		this(error.getCode(),error.getMsg(),cause);
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
