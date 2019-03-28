package com.prophet.web.business.metadata.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ProductEntity {
	private Integer id;
	private String productCode;
	private String productName;
	private String productAdmin;
	private String productSecret;
	private String productDesc;
	private String createUser;
	private String createTime;
}
