/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Aug 24, 2017 9:13:36 AM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.common.util;

/**
 * <P>获取hibernate校验信息</P>
 */
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


/**
 * 
 * <P>JSR303 消息解析</P>
 */
public class ValidatorMsgParser {
	
	private ValidatorMsgParser(){}
	/**
	 * 
	 * <p>从 BindingResult 中获取错误信息</p>
	 * @param br
	 * @return
	 */
	public static StringBuilder getErrorMsg(BindingResult br) {
		List<ObjectError> allErrors = br.getAllErrors();
		StringBuilder sb = new StringBuilder();
		for (ObjectError error : allErrors) {
			sb.append(error.getDefaultMessage() + " ");
		}
		return sb;
	}

}