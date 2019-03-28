/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 18, 2017 3:36:33 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.web.framework.global.model;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import com.prophet.web.system.utils.ObjectUtils;


/**
 * <P>通用返回结果类</P>
 * 
 * @author lvzhh
 */
public class Result<T> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3764024604177142962L;

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

	
/*	public Result<T> getTogether(Page page) {
		Result<T>  result = new  Result<T>();
		result.setDatas(page);
		result.setCount(page.getTotal());
		return result;
	}*/
	public Result<T> error(String code) {
		this.code = code;
		return this;
	}
	
	public void buildErrorInfo(boolean success) {
		this.success = success;
	}
	
	public void buildErrorInfo(String code, String msg) {
		this.msg = msg;
		this.code = code;
	}
	
	public void buildErrorInfo(String code, boolean success) {
		this.code = code;
		this.success = success;
	}

	public void buildErrorInfo(String code, String msg, boolean success) {
		this.msg = msg;
		this.code = code;
		this.success = success;
	}

	public Result<T> success() {
		this.code = ResultEnum.SUCCESS.getCode();
		this.success = true;
		this.msg = ResultEnum.SUCCESS.getMsg();
		return this;
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
		this.count = ObjectUtils.isObjectEmpty(data)?0:data.size();
		this.datas = data;
	}
	
	public void successInfo(String code, Page<T> data) {
		this.code = code;
		this.success = true;
		this.count = ObjectUtils.isObjectEmpty(data)?0:data.getTotal();
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


}
