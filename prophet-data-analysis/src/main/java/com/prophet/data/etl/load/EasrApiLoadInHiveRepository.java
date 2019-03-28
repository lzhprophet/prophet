package com.prophet.data.etl.load;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prophet.data.etl.model.ApiInfoBean;

@Repository
public class EasrApiLoadInHiveRepository {
	
	@Autowired
	@Qualifier("hiveDruidTemplate")
	private JdbcTemplate hiveDruidTemplate;
	
	
	public List<ApiInfoBean> getNewApiByDay(String day){

		StringBuilder sql = new StringBuilder();
		sql.append("select a.url_path,a.pcode from easr_api_info a where a.pday='");
		sql.append(day);
		sql.append("'and a.url_path not in(select url_path from easr_api_info where pday < '");
		sql.append(day);
		sql.append("')  group by a.pcode,a.url_path ");
		
		List<ApiInfoBean> bsbList = hiveDruidTemplate.query(sql.toString(), new HiveApiInfoBeanRSRowMapper());
		return bsbList;
	}
}
