package com.prophet.web.framework.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.prophet.web.framework.global.model.Result;

	
/**
 * <P>全局异常处理</P>
 * 
 * @author lvzhh
 */
@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	 * 全局异常捕捉处理
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String>  errorHandler(Exception ex) {
		log.error("未知异常>> ", ex);
		return new ResponseEntity<String>("未知异常", HttpStatus.INTERNAL_SERVER_ERROR);  
		
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
