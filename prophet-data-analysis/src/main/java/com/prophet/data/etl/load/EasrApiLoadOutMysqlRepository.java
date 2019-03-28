package com.prophet.data.etl.load;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.prophet.data.etl.model.ApiInfoBean;

@Mapper
public interface EasrApiLoadOutMysqlRepository {
	

	
	public void insertApiInfo(ApiInfoBean ApiBean) throws DataAccessException;
	
	
}
