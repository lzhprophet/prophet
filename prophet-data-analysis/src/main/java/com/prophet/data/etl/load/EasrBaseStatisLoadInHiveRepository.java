package com.prophet.data.etl.load;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prophet.data.etl.model.BaseStatisBean;

@Repository
public class EasrBaseStatisLoadInHiveRepository {
	
	@Autowired
	@Qualifier("hiveDruidTemplate")
	private JdbcTemplate hiveDruidTemplate;
	
	
	public List<BaseStatisBean> selectCount(String day){
		StringBuilder sql = new StringBuilder();
		sql.append("select ip.pday,ip.pcode,ip.ip_count,uv.uv_count,api.api_count,usernew.usernew_count "); 
		sql.append("from easr_statistics_result_ip ip ");
		sql.append("LEFT JOIN easr_statistics_result_uv uv  on ip.pcode = uv.pcode and ip.pday = uv.pday ");
		sql.append("LEFT JOIN easr_statistics_result_api api  on ip.pcode = api.pcode and ip.pday = api.pday ");
		sql.append("LEFT JOIN easr_statistics_result_usernew usernew  on ip.pcode = usernew.pcode and ip.pday = usernew.pday ");
		sql.append("where ip.pday='");
		sql.append(day);
		sql.append("'");
		
		List<BaseStatisBean> bsbList = hiveDruidTemplate.query(sql.toString(), new HiveBaseStatisRowMapper());
		return bsbList;
	}
	
	public List<BaseStatisBean> selectCountByTimeslot(String day){
		StringBuilder sql = new StringBuilder();
		sql.append("select pday,pcode,timeslot,ip_count,uv_count,api_count,usernew_count from easr_statistics_result_baseall_timeslot ");
		sql.append("where  pday='");
		sql.append(day);
		sql.append("'");
		
		List<BaseStatisBean> bsbList = hiveDruidTemplate.query(sql.toString(), new HiveBaseStatisRowMapper());
		return bsbList;
	}
}
