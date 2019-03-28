package com.prophet.web.business.metadata.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ApiEntity {
	private Integer id;
	private String name;
	private String productName;
	private String urlPath;
	private String remark;
	private String identTime;
	private Integer identStatusl;
	private Integer version;
	private String updateTime;
	private String createTime;
}
