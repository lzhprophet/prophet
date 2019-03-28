package com.prophet.web.framework.aspect.model;

import lombok.Getter;
import lombok.Setter;

//TODO: 未完成
public class SystemErrorLog {
	@Getter @Setter
	private String username;
	@Getter @Setter
	private String 	type;
	@Getter @Setter
	private String 	url;
	@Getter @Setter
	private String method;
	@Getter @Setter
	private String params;
	@Getter @Setter
	private String requestip;
	@Getter @Setter
	private String description;
	@Getter @Setter
	private String detail;
	@Getter @Setter
	private String oper_date;
}
