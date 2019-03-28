package com.prophet.data.etl.load;

import org.apache.ibatis.annotations.Mapper;

import com.prophet.data.etl.model.AccessModeSRBean;
import com.prophet.data.etl.model.BaseStatisBean;

@Mapper
public interface EasrBaseStatisLoadOutMysqlRepository {
	
	public int isExist(BaseStatisBean bsBean);
	
	public void insertBaseStatis(BaseStatisBean bsBeanList);
	
	public void updateBaseStatis(BaseStatisBean bsBeanList);
	
	
	public int isExistByTimeslot(BaseStatisBean bsBean);
	
	public void insertBaseStatisByTimeslot(BaseStatisBean bsBeanList);
	
	public void updateBaseStatisByTimeslot(BaseStatisBean bsBeanList);
	
	public void insertAccressModeResult(AccessModeSRBean accessModeSRBean);
	
}
