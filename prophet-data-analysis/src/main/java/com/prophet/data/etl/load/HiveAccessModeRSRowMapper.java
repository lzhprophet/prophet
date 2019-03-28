package com.prophet.data.etl.load;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prophet.data.etl.model.AccessModeSRBean;

public class HiveAccessModeRSRowMapper implements RowMapper<AccessModeSRBean> {

	@Override
	public AccessModeSRBean mapRow(ResultSet resultSet, int i) throws SQLException {
		// 获取结果集中的数据
		String pday = resultSet.getString("pday");
		String pcode = resultSet.getString("pcode");
		String accessMode = resultSet.getString("access_mode");
		String amCount = resultSet.getString("accessmodecount");
		
		// 把数据封装成对象
		AccessModeSRBean accessModeSRBean = new AccessModeSRBean();
		accessModeSRBean.setPcode(pcode);
		accessModeSRBean.setPday(pday);
		accessModeSRBean.setAccessMode(accessMode);
		accessModeSRBean.setAmCount(amCount);
	
		return accessModeSRBean;
	}
	
	
}