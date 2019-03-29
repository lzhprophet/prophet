/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 17, 2017 11:25:35 AM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.prophet.receivedata.common.exception.BusinessCheckedException;
import com.prophet.receivedata.common.exception.BusinessException;
import com.prophet.receivedata.common.model.Result;


/**
 * <P>全局异常处理</P>
 * 
 * @author #{李景川 15566909899}
 */
@ControllerAdvice
public class GlobalControllerAdvice {

	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * hibernate validator异常捕捉处理
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = ConstraintViolationException.class)
	public Result<String> constraintViolationExceptionHandler(ConstraintViolationException ex) {
		log.error("校验异常>> ", ex);
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		Result<String> result = new Result<>();
		result.buildErrorInfo(null, message, false);
		return result;
	}

	/**
	 * 全局异常捕捉处理
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Result<String> errorHandler(Exception ex) {
		log.error("未知异常>> ", ex);
		Result<String> result = new Result<>();
		result.buildErrorInfo(null, "系统发生未知业务异常!", false);
		return result;
	}

	/**
	 * 拦截捕捉自定义异常 BusinessException.class
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = BusinessException.class)
	public Result<String> businessExceptionHandler(BusinessException ex) {
		log.error("业务异常>> ", ex);
		Result<String> result = new Result<>();
		result.buildErrorInfo(ex.getCode(), ex.getMsg(), false);
		return result;
	}

	/**
	 * 拦截捕捉自定义异常 BusinessCheckedException.class
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = BusinessCheckedException.class)
	public Result<String> businessCheckedExceptionHandler(BusinessCheckedException ex) {
		log.error("业务异常>> ", ex);
		Result<String> result = new Result<>();
		result.buildErrorInfo(ex.getCode(), ex.getMsg(), false);
		return result;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
