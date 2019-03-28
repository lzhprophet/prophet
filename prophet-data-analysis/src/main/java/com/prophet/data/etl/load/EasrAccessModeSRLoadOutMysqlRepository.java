package com.prophet.data.etl.load;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.data.etl.model.AccessModeSRBean;

@Mapper
public interface EasrAccessModeSRLoadOutMysqlRepository {
	
	public int isExist(AccessModeSRBean accessModeSRBean);
	
	public void insertAccressModeResult(AccessModeSRBean accessModeSRBean);
	
	public void updateAccressModeResult(AccessModeSRBean accessModeSRBean);
	
}
