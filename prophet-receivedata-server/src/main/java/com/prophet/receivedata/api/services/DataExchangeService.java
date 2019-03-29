package com.prophet.receivedata.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.prophet.receivedata.api.model.EventInfo;
import com.prophet.receivedata.api.util.AppConstant;
import com.prophet.receivedata.api.util.JacksonUtil;

@Service
public class DataExchangeService {
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    
    
    public void  sendKafka(EventInfo data) {
    	String topic = AppConstant.TENANCY_TOPIC_PANHAI;
    	String proTeancy = data.getProject().getTeancyCode().toUpperCase();
    	if(AppConstant.TENANCY_LCYP_CODE.equals(proTeancy)){
    		topic = AppConstant.TENANCY_TOPIC_LCYP;
    	}else if(AppConstant.TENANCY_JINGANG_CODE.equals(proTeancy)){
    		topic = AppConstant.TENANCY_TOPIC_JINGANG;
    	}else if(AppConstant.TENANCY_365TRAS_CODE.equals(proTeancy)){
    		topic = AppConstant.TENANCY_TOPIC_365TRAS;
    	}else if(AppConstant.TENANCY_JINZHOUPORT_CODE.equals(proTeancy)){
    		topic = AppConstant.TENANCY_TOPIC_JINZHOUPORT;
    	}else if(AppConstant.TENANCY_LGTRADE_CODE.equals(proTeancy)){
    		topic = AppConstant.TENANCY_TOPIC_LGTRADE;
    	}
    	
    	kafkaTemplate.send(topic,JacksonUtil.toJSon(data));
    }
}