package com.prophet.web.business.basedata.entity;

import lombok.Getter;
import lombok.Setter;

public class BerthEntity {
	
	@Getter @Setter
	private Integer id;
	@Getter @Setter
    private String name;
	@Getter @Setter
    private String abbreviation;
	@Getter @Setter
    private String number;
	@Getter @Setter
    private Byte status;
	@Getter @Setter
    private String width;
	@Getter @Setter
    private String height;
	@Getter @Setter
    private String tongrade;
	@Getter @Setter
    private String abilitytopass;
	@Getter @Setter
    private String mainUse;
	@Getter @Setter
    private String remarks;
	
	@Getter @Setter
    private Byte deleteFlag;
	
	@Getter @Setter
    private String updateUser;
	@Getter @Setter
    private String updateTime;
	
	@Getter @Setter
    private String createUser;
	@Getter @Setter
    private String createTime;


}
