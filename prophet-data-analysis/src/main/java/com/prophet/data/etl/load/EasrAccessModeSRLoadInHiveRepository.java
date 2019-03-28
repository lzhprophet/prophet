package com.prophet.data.etl.load;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prophet.data.etl.model.AccessModeSRBean;

@Repository
public class EasrAccessModeSRLoadInHiveRepository {
	
	@Autowired
	@Qualifier("hiveDruidTemplate")
	private JdbcTemplate hiveDruidTemplate;
	
	
	public List<AccessModeSRBean> accessModeStatis(String day){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.pcode , a.access_mode,count(a.user_id) accessModeCount ,'"); 
		sql.append(day).append("' as pday from ");
		sql.append("(select  pcode , access_mode,user_id,count(user_id) from easr_uv_info where pday='");
		sql.append(day).append("' group by pcode,access_mode,user_id ) a ");
		sql.append("group by a.pcode,a.access_mode");
		
		List<AccessModeSRBean> accessModeList = hiveDruidTemplate.query(sql.toString(), new HiveAccessModeRSRowMapper());
		
		return accessModeList;
	}
	
	
}
