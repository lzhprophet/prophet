package com.prophet.web.framework.exception;

import com.prophet.web.framework.global.model.ResultEnum;


/**
 * <P>自定义运行时异常</P>
 * 
 * @author lvzhh
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
