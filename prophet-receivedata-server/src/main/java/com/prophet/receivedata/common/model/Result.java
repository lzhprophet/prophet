/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 18, 2017 3:36:33 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.model;

import java.io.Serializable;
import java.util.List;


/**
 * <P>通用返回结果类</P>
 * 
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1707551799023668525L;

	// 数据
	private T data;

	// 数据
	private List<T> datas;

	// 是否成功标志
	private boolean success;

	// 信息
	private String msg;

	// 信息码
	private String code;

	// 数据总数
	private long count;

	public void buildErrorInfo(String code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public void buildErrorInfo(String code, String msg, boolean success) {
		this.msg = msg;
		this.code = code;
		this.success = success;
	}
	
	
	public void successInfo(String code,String msg) {
		this.code = code;
		this.success = true;
		this.msg = msg;
	}

	public void successInfo(String code, T data) {
		this.code = code;
		this.success = true;
		this.data = data;
	}

	public void successInfo(String code, List<T> data) {
		this.code = code;
		this.success = true;
		this.datas = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String isCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
