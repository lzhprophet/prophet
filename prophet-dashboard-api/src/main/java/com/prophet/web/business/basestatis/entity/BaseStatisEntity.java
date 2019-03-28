package com.prophet.web.business.basestatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
public class BaseStatisEntity {
	
	private String pday;
	private String pcode;
	private Integer ipCount;
	private Integer uvCount;
	private Integer apiCount;
	private Integer newUserCount;
	private String timeSlot;
	
	private Integer maxIp;
	private Integer maxUv;
	private Integer maxApi;
	private Integer maxNu;
	
	private Integer avgIp;
	private Integer avgUv;
	private Integer avgApi;
	private Integer avgNu;
	
}
