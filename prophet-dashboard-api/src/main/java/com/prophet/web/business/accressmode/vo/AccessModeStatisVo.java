package com.prophet.web.business.accressmode.vo;

import java.util.List;

import com.prophet.web.business.accressmode.entity.AccessModeStatisEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
public class AccessModeStatisVo {
	//对应页面 yAxis.data 
	private  List<String> pcodeList;
	//对应页面 legend.data 
	private  List<String> accessModeLegendList;
	
	//对应页面series
	private  List<AccessModeStatisEntity> accessModeDataList;
	
}
