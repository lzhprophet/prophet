/*******************************************************************************
 * Project Key : #{projectName}
 * Create on Sep 11, 2017 7:40:41 PM
 * Copyright (c) 磐海数据有限公司版权所有. 
 * 注意：本内容仅限于磐海数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.prophet.receivedata.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prophet.receivedata.api.model.EventInfo;
import com.prophet.receivedata.api.services.DataExchangeService;
import com.prophet.receivedata.api.util.HttpInfoUtils;
import com.prophet.receivedata.api.util.JacksonUtil;
import com.prophet.receivedata.common.util.StringFilter;

@RestController
@RequestMapping("/prophet")
public class ProphetRecevivedataController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProphetRecevivedataController.class);

	@Autowired
	private DataExchangeService dataExchangeService;

	/**
	 * <p>
	 *  数据接收
	 * </p>
	 * 
	 * @param String data jssdk发送的数据
	 * @return
	 * @author #{吕忠厚 18909847071}
	 */
	@RequestMapping("/*")
	public String receivedata(String data,HttpServletRequest request) {
		String decodeData= StringFilter.decodingString(data);
		String ip = HttpInfoUtils.getRemortIP(request);
		EventInfo eventInfo = JacksonUtil.readValue(decodeData, EventInfo.class);  
		eventInfo.setIp(ip);
		eventInfo.setUserAgent(HttpInfoUtils.getUserAgent(request));
		
		dataExchangeService.sendKafka(eventInfo);	
			
		logger.info(data);
		return null;
	}
	
	
	


}
