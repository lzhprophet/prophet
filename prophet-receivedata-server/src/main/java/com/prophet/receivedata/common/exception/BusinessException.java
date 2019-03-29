/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 17, 2017 11:26:07 AM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.exception;

import com.prophet.receivedata.common.model.ResultEnum;

/**
 * <P>TODO</P>
 */
public class BusinessException extends RuntimeException {
	

	private static final long serialVersionUID = 2997553303343059244L;


	public BusinessException(String code, String msg) {
		super(msg);
        this.code = code;
        this.msg = msg;
    }
	
	public BusinessException(String code, String msg,Throwable cause) {
		super(msg,cause);
        this.code = code;
        this.msg = msg;
    }
	
	
	public BusinessException(ResultEnum msg){
		this(msg.getCode(),msg.getMsg());
	}
	
	public BusinessException(ResultEnum msg,Throwable cause){
		this(msg.getCode(),msg.getMsg(),cause);
	}
	
	private String code;
    private String msg;
    

    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
