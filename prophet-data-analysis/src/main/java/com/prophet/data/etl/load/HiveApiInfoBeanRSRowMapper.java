package com.prophet.data.etl.load;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prophet.data.etl.model.ApiInfoBean;

public class HiveApiInfoBeanRSRowMapper implements RowMapper<ApiInfoBean> {

	@Override
	public ApiInfoBean mapRow(ResultSet resultSet, int i) throws SQLException {
		// 获取结果集中的数据
		String urlPath = resultSet.getString("url_path");
		String pcode = resultSet.getString("pcode");
		
		// 把数据封装成对象
		ApiInfoBean api = new ApiInfoBean();
		api.setPcode(pcode);
		api.setUrlPath(urlPath);
	
	
		return api;
	}
	
	
}