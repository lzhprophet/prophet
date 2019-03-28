package com.prophet.web.framework.exception;

import com.prophet.web.framework.global.model.ResultEnum;

/**
 * <P>自定义运行时异常</P>
 * @author lvzhh
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
