package com.prophet.web.system.dict.entity;

import lombok.Getter;
import lombok.Setter;

public class DictEntity {
	@Getter @Setter
    private Integer id;
	@Getter @Setter
    private String dictCode;
	@Getter @Setter
    private String dictKey;
	@Getter @Setter
    private String dictValue;
	@Getter @Setter
    private String dictName;
	@Getter @Setter
    private String dictDesc;
	@Getter @Setter
    private Integer deleteFlag;
	@Getter @Setter
    private String updateUser;
	@Getter @Setter
    private String updateTime;
	@Getter @Setter
    private String createUser;
	@Getter @Setter
    private String createTime;

}